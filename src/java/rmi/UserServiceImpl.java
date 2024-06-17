/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

/**
 *
 * @author BRUNO
 */

import DAO.UserDAO;
import Model.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    private UserDAO userDAO;

    protected UserServiceImpl() throws RemoteException {
        userDAO = new UserDAO();
    }

    @Override
    public List<User> selectAllUsers() throws RemoteException {
        return userDAO.selectAllUsers();
    }

    @Override
    public void insertUser(User user) throws RemoteException {
        try {
            userDAO.insertUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateUser(User user) throws RemoteException {
        try {
            userDAO.updateUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(int id) throws RemoteException {
        try {
            userDAO.deleteUser(id);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User selectUser(int id) throws RemoteException {
        return userDAO.selectUser(id);
    }

    @Override
    public int calculateAge(User user) throws RemoteException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(user.getFechaNacimiento(), formatter);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Override
    public double calculateAverageAge(List<String> birthDates) throws RemoteException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int totalAge = 0;
        int count = birthDates.size();

        for (String birthDateString : birthDates) {
            LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
            int age = Period.between(birthDate, LocalDate.now()).getYears();
            totalAge += age;
        }

        return count == 0 ? 0 : (double) totalAge / count;
    }
}
