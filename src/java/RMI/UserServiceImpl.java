/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

/**
 *
 * @author brand
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    protected UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public double calculateAverageAge(List<String> birthdates) throws RemoteException {
        List<Integer> ages = birthdates.stream()
                .map(date -> Period.between(LocalDate.parse(date), LocalDate.now()).getYears())
                .collect(Collectors.toList());

        return ages.stream().mapToInt(Integer::intValue).average().orElse(0);
    }
}