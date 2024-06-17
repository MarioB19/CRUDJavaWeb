/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

/**
 *
 * @author BRUNO
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import Model.User;

public interface UserService extends Remote {
    List<User> selectAllUsers() throws RemoteException;
    void insertUser(User user) throws RemoteException;
    void updateUser(User user) throws RemoteException;
    void deleteUser(int id) throws RemoteException;
    User selectUser(int id) throws RemoteException;
    int calculateAge(User user) throws RemoteException;
    double calculateAverageAge(List<String> birthDates) throws RemoteException;
}
