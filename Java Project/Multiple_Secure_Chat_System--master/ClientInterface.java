/**
 * 
 *
 *
 * @author     
 */

import java.rmi.*;
import javax.crypto.spec.SecretKeySpec;


public interface ClientInterface extends Remote {

    public String getName() throws RemoteException;
    public String getPassword() throws RemoteException;

    public void setConnectStatus(int stat) throws RemoteException;

    public void InviteToRoom(String roomName, String[] selected, SecretKeySpec key, String admin) throws RemoteException;

    public int getConnectStatus() throws RemoteException;

    public void showMessage(String Mess) throws RemoteException;

    public void showRoomMessage(String roomName,String meesage,String meesageD) throws RemoteException;

    public String[] getSelected(String roomName) throws RemoteException;

    public void showUsers(String[] users) throws RemoteException;

    public void showUsersRoom(String roomName, String [] Selected ) throws RemoteException;

    public void showHistory(String [] message) throws RemoteException;

    public void dropped() throws RemoteException;
    public void updateLists(String roomName) throws RemoteException;

    public void closeRoom(String roomName) throws RemoteException;
    public void updateRoomKey(String roomName, SecretKeySpec key) throws RemoteException;
}
