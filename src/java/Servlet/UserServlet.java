package Servlet;

import DAO.UserDAO;
import Model.User;
import Utilities.HashUtil;
import Utilities.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String fromIndex = request.getParameter("fromIndex");
        String fromList = request.getParameter("fromList");

       
        if ("true".equals(fromIndex)) {
            session.setAttribute("fromIndex", "true");
            session.removeAttribute("fromList");
        }
        if ("true".equals(fromList)) {
            session.setAttribute("fromList", "true");
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getServletPath();
        
         //Acciones CRUD
         try {
            switch (action) {
                case "/insert":
                    insertUser(request, response);
                    return;
                case "/delete":
                    deleteUser(request, response);
                    return;
              
                case "/update":
                    updateUser(request, response);
                    return;
  
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }



        if (session == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        if (!"granted".equals(session.getAttribute("access"))) {
            response.sendRedirect("index.jsp");
            return;
        }

        boolean isAccessAllowed = false;
        
        if ("/list".equals(action) && "true".equals(session.getAttribute("fromIndex"))) {
            isAccessAllowed = true;
             session.removeAttribute("fromList");
            session.removeAttribute("fromIndex");
         
        } else if (("/new".equals(action) || "/edit".equals(action)) && "true".equals(session.getAttribute("fromList"))) {
            isAccessAllowed = true;
              session.removeAttribute("fromList");
            session.removeAttribute("fromIndex");
           
        }
        if (!isAccessAllowed) {
          
            response.sendRedirect("index.jsp");
            return;
        }

       
    //Vistas
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/list":
                    listUser(request, response);
                    break;
                case "/future":
                    showFutureAction(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        request.setAttribute("debugMessage", "Listing users");
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("debugMessage", "Showing new user form");
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        request.setAttribute("user", existingUser);
        request.setAttribute("debugMessage", "Showing edit form for user with ID: " + id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String nombreCompleto = request.getParameter("nombreCompleto");
        String correoElectronico = request.getParameter("correoElectronico");
        String password = request.getParameter("password");

        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);

        String errorMessage = null;
        if (userDAO.emailExists(correoElectronico)) {
            errorMessage = "El correo electrónico ya existe.";
        } else if (!Validator.isValidBirthDate(fechaNacimiento)) {
            errorMessage = "Debe tener al menos 18 años.";
        } else if (!Validator.isValidName(nombreCompleto)) {
            errorMessage = "Nombre completo no es válido.";
        } else if (!Validator.isValidEmail(correoElectronico)) {
            errorMessage = "Correo electrónico no es válido.";
        } else if (!Validator.isValidPassword(password)) {
            errorMessage = "Contraseña no es válida.";
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("fechaNacimiento", fechaNacimientoStr);
            request.setAttribute("nombreCompleto", nombreCompleto);
            request.setAttribute("correoElectronico", correoElectronico);
            request.setAttribute("password", password);
            request.setAttribute("debugMessage", "Error inserting user: " + errorMessage);
            showNewForm(request, response);
        } else {
            String hashedPassword = HashUtil.hashPassword(password);
            User newUser = new User(fechaNacimientoStr, nombreCompleto, correoElectronico, hashedPassword);
            userDAO.insertUser(newUser);
            request.setAttribute("debugMessage", "User inserted successfully");
            response.sendRedirect("listView");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String nombreCompleto = request.getParameter("nombreCompleto");
        String correoElectronico = request.getParameter("correoElectronico");

        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);

        String errorMessage = null;
        User existingUser = userDAO.selectUser(id);
        if (!existingUser.getCorreoElectronico().equals(correoElectronico) && userDAO.emailExists(correoElectronico)) {
            errorMessage = "El correo electrónico ya existe.";
        } else if (!Validator.isValidBirthDate(fechaNacimiento)) {
            errorMessage = "Debe tener al menos 18 años.";
        } else if (!Validator.isValidName(nombreCompleto)) {
            errorMessage = "Nombre completo no es válido.";
        } else if (!Validator.isValidEmail(correoElectronico)) {
            errorMessage = "Correo electrónico no es válido.";
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("fechaNacimiento", fechaNacimientoStr);
            request.setAttribute("nombreCompleto", nombreCompleto);
            request.setAttribute("correoElectronico", correoElectronico);
            request.setAttribute("debugMessage", "Error updating user: " + errorMessage);
            showEditForm(request, response);
        } else {
            String password = existingUser.getPassword(); // Obtener la contraseña existente
            User user = new User(id, fechaNacimientoStr, nombreCompleto, correoElectronico, password);
            userDAO.updateUser(user);
            request.setAttribute("debugMessage", "User updated successfully");
            response.sendRedirect("listView");
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        request.setAttribute("debugMessage", "User deleted successfully");
        response.sendRedirect("listView");
    }

    private void showFutureAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("debugMessage", "Showing future action page");
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/future-action.jsp");
        dispatcher.forward(request, response);
    }
}