/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

/**
 *
 * @author brand
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIClient {
    private UserService userService;

    public RMIClient() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            userService = (UserService) registry.lookup("UserService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double calculateAverageAge(List<String> birthdates) {
        double averageAge = 0;
        try {
            averageAge = userService.calculateAverageAge(birthdates);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return averageAge;
    }
}
