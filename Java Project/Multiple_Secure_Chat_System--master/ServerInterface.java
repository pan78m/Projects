/**
 * 
 *
 *
 * @author 
 */

import java.rmi.*;
import javax.crypto.spec.SecretKeySpec;


public interface ServerInterface extends Remote {

    public SecretKeySpec getLoginKey() throws RemoteException;
    
    public boolean isUserName(String name) throws RemoteException;

    public boolean isPassCorrect(String name, String  pass,String passD) throws RemoteException;

    public boolean isUserLogin(String name) throws RemoteException;

    public int getRoomId() throws RemoteException;

    public void createRoom(String roomName, String request, String admin) throws RemoteException;

    public void register(String name, String  pass,String passD) throws RemoteException;

    public void login(ClientInterface client) throws RemoteException;

    public void logout(ClientInterface client) throws RemoteException;

    public void brodcastMessage(ClientInterface client, String message) throws RemoteException;

    public void multicastMessage(String clientName, String roomName,String []Selected, String message,String messageD) throws RemoteException;

    public void showUsersRoom(String roomName, String [] seleceted) throws RemoteException;

    public void showHistory(ClientInterface client) throws RemoteException;
    public void addUsersToAroom(String roomName, String request, String [] users) throws RemoteException;
    public void deleteMessage(String userName) throws RemoteException;
    public String[] getConnect() throws RemoteException;

    public void closeRoom(String roomName, String[] selected) throws RemoteException;
    public void updateRoomKey(String roomName, String[] users) throws RemoteException;
}