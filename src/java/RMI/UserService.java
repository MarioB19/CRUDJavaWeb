
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserService extends Remote {
    double calculateAverageAge(List<String> birthdates) throws RemoteException;
}
