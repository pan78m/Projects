/**
 * 
 *
 *
 * @author     
 */

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Client extends UnicastRemoteObject implements ClientInterface {

    private String clientName;
    private String password;
    private int status = 0;
    public Vector<newRoom> room;
    public historyMessage hMessage = null;
    SecretKeySpec key;

    /**
     *
     * @param Name
     * @throws RemoteException
     */
    public Client(String Name) throws RemoteException {
        room = new Vector<newRoom>();
        clientName = Name;
    }

    /**
     *
     * @throws RemoteException
     */
    @Override
    public void dropped() throws RemoteException{
        ClientUI.dropped();
    }

    /**
     *
     * @param clientname
     * @param roomname
     * @param usersSelected
     * @param key1
     * @param host
     * @param admin
     * @throws MalformedURLException
     * @throws RemoteException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public void clientCreateRoom(String clientname, String roomname, String[] usersSelected, SecretKeySpec key1, String host, String admin) throws MalformedURLException, RemoteException, NoSuchAlgorithmException, NoSuchPaddingException {
        /*
        Decrypt the key before storing
        */
        System.out.println("username: " + this.getName() + "  pass: " + this.getPassword());
        newRoom tempRoom = new newRoom(clientname, roomname, usersSelected, key1, host, admin, this);
        room.addElement(tempRoom);
    }

    /**
     *
     * @param userName
     * @param host
     * @throws MalformedURLException
     * @throws RemoteException
     */
    public void createHistory(String userName, String host) throws MalformedURLException, RemoteException {
        hMessage = new historyMessage(userName, host);
    }

    /**
     *
     * @param roomName
     * @param selected
     * @param key1
     * @param admin
     * @throws RemoteException
     */
    @Override
    public void InviteToRoom(String roomName, String[] selected, SecretKeySpec key1, String admin) throws RemoteException {
        try {
            try {
                try {
                    ClientUI.showInvite(roomName, selected, key1, admin);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     *
     * @param roomName
     * @return
     */
    @Override
    public String[] getSelected(String roomName) {
        newRoom tempRoom;
        Iterator itr = room.iterator();
        while (itr.hasNext()) {
            tempRoom = (newRoom) itr.next();
            if (roomName.equals(tempRoom.getRoomName())) {
                return tempRoom.geSelected();
            }
        }
        return null;
    }

    /**
     *
     * @param roomname
     */
    public void removeRoom(String roomname) {
        room.removeElement(roomname);
    }

    /**
     *
     * @param state
     * @throws RemoteException
     */
    @Override
    public void setConnectStatus(int state) throws RemoteException {
        status = state;
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public int getConnectStatus() throws RemoteException {
        return status;
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public String getName() throws RemoteException {
        return clientName;
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public String getPassword() throws RemoteException {
        return this.password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param Mess
     * @throws RemoteException
     */
    @Override
    public void showMessage(String Mess) throws RemoteException {
        ClientUI.showMessage(Mess);
    }
    
    /**
     *
     * @param roomName
     * @param Mess
     * @param messageD
     * @throws RemoteException
     */
    @Override
    public void showRoomMessage(String roomName, String Mess, String messageD) throws RemoteException {
        ClientUI.showRoomMessage(roomName, Mess, messageD);
    }

    /**
     *
     * @param roomName
     * @param key
     * @throws RemoteException
     */
    @Override
    public void updateRoomKey(String roomName, SecretKeySpec key) throws RemoteException {
        ClientUI.updateRoomKey(roomName, key);
    }

    /**
     *
     * @param users
     * @throws RemoteException
     */
    @Override
    public void showUsers(String[] users) throws RemoteException {
        ClientUI.showUsers(users);

    }

    /**
     *
     * @param roomName
     * @throws RemoteException
     */
    @Override
    public void updateLists(String roomName) throws RemoteException {
        ClientUI.updateLists(roomName);
    }

    /**
     *
     * @param roomName
     * @param Selected
     * @throws RemoteException
     */
    @Override
    public void showUsersRoom(String roomName, String[] Selected) throws RemoteException {
        ClientUI.showUsersRoom(roomName, Selected);
    }

    /**
     *
     * @param message
     * @throws RemoteException
     */
    @Override
    public void showHistory(String[] message) throws RemoteException {
        try {
            ClientUI.showHistory(message);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param arg
     * @throws RemoteException
     */
    public static void main(String arg[]) throws RemoteException {
    }

    /**
     *
     * @param roomName
     * @throws RemoteException
     */
    @Override
    public void closeRoom(String roomName) throws RemoteException {
        ClientUI.closeRoom(roomName);
    }
}
