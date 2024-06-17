/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

/**
 *
 * @author brunosanchezpadilla
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    public static void main(String[] args) {
        try {
            UserServiceImpl userService = new UserServiceImpl();
            
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("UserService", userService);
            
            System.out.println("UserService bound in registry");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}







