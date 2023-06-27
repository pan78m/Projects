/**
 * 
 *
 *
 * @author     
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.rmi.*;
import java.rmi.server.*;
import java.security.InvalidKeyException;
import java.util.*;
import java.util.ArrayList.*;
import java.util.Vector.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.binary.Base64;

public class Server extends UnicastRemoteObject implements ServerInterface {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String messD;
    public static String Emessage;
    public static String Dmessage;
    public static String Dpassword;
    public static String passDS;
    public static String publicMess;
    public static String privateMess;
    public static Scanner sc = new Scanner(System.in);
    public static String userid = "root", password = "bhavik", url = "jdbc:mysql://localhost:3306/chat";
    public static PreparedStatement pstmt;
    public static ResultSet rs;
    public static Connection con;
    private static Vector<ClientInterface> Clients;
    public int roomId = 1;
    private Vector<String> roomNames = new Vector<String>();
    private List<String[]> roomUsers = new ArrayList<String[]>();	
    private List<ClientInterface> roomAdmins = new ArrayList<>();
    private Vector<SecretKeySpec> roomKeys = new Vector<SecretKeySpec>();
    public static SecretKeySpec loginKey;
    public static SecretKeySpec key;
    public static SecretKeySpec dKey;
    private Cipher cipher;
    public static final String AES = "AES";

    /**
     *
     * @param Clear
     * @return
     * @throws Exception
     */
    public String Digest(String Clear) throws Exception {
        String CLEAR = Clear;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(CLEAR.getBytes(), 0, CLEAR.length());
        String Dig = new BigInteger(1, m.digest()).toString(16);
        return Dig;
    }

    /**
     *
     * @param message
     * @return
     * @throws Exception
     */
    public static String TripleDES_Encrypt(String message) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey Lkey = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher ecipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        ecipher.init(Cipher.ENCRYPT_MODE, Lkey, iv);
        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = ecipher.doFinal(plainTextBytes);
        return Base64.encodeBase64String(cipherText);
    }

    /**
     *
     * @param text
     * @return
     * @throws Exception
     */
    public static String TripleDES_Decrypt(String text) throws Exception {
    	byte[] message = Base64.decodeBase64(text);
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey Lkey = new SecretKeySpec(keyBytes, "DESede");
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, Lkey, iv);
        final byte[] plainText = decipher.doFinal(message);
        return new String(plainText, "UTF-8");
    }

    /**
     *
     * @param plainText
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     */
    public String encrypt(String plainText)
            throws NoSuchAlgorithmException, GeneralSecurityException {
        try{
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] input = plainText.getBytes("UTF8");
            byte[] cipherText;
            cipherText = cipher.doFinal(input);
            cipherText = Base64.encodeBase64(cipherText);
            return new String(cipherText);
        } catch(InvalidKeyException | UnsupportedEncodingException 
                | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println("Something went wrong in encryption");
            System.out.println(e.toString());
        }
	return null;
    }

    /**
     *
     * @param text
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     */
    public String decrypt(String text)
            throws NoSuchAlgorithmException, GeneralSecurityException {
            
        try{
            cipher.init(Cipher.DECRYPT_MODE, dKey);
            byte[] cipherText = Base64.decodeBase64(text);
            byte[] plainText;
            plainText = cipher.doFinal(cipherText);
            String d = Base64.encodeBase64String(plainText);
            return new String(plainText, "UTF8");
	} catch(Exception e) {
            System.out.println("Something went wrong in decryption");
            System.out.println(e.toString());
	}
	return null;
    }
    
    /**
     *
     * @param text
     * @param keyToUse
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     */
    public String decrypt(String text, SecretKeySpec keyToUse)
            throws NoSuchAlgorithmException, GeneralSecurityException {
            
        try{
            cipher.init(Cipher.DECRYPT_MODE, keyToUse);
            byte[] cipherText = Base64.decodeBase64(text);
            byte[] plainText;
            plainText = cipher.doFinal(cipherText);
            String d = Base64.encodeBase64String(plainText);
            return new String(plainText, "UTF8");
	} catch(InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException | UnsupportedEncodingException e) {
            System.out.println("Something went wrong in decryption");
            System.out.println(e.toString());
	}
	return null;
    }

    /**
     *
     * @param algorithm
     * @param sKey
     * @throws NoSuchAlgorithmException
     */
    private SecretKeySpec generateKey(String algorithm, byte[] sKey) 
            throws NoSuchAlgorithmException {
    try{
        return new SecretKeySpec(sKey, algorithm);
        } catch(Exception e) {}
        return null;
    }

    /**
     *
     * @param key
     * @param text
     * @return
     */
    public SecretKeySpec decryptKey(SecretKeySpec key, String text) {
        try {
            BigInteger b = new java.math.BigInteger(String.format("%040x", new BigInteger(1, (text).getBytes("UTF-8"))), 16);
            byte [] keyBytes = new byte[16];
            byte [] bigBytes = b.toByteArray();
            System.arraycopy(bigBytes, 0, keyBytes, 0, Math.min(keyBytes.length, bigBytes.length));
            SecretKeySpec keyTodecrypt = generateKey("AES", keyBytes);
            SecretKeySpec keyToUse;
            byte[] db = decrypt(Base64.decodeBase64(key.getEncoded()), keyTodecrypt);
            keyToUse = new SecretKeySpec(db, "AES");
            return keyToUse;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException | GeneralSecurityException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param text
     * @return
     */
    public SecretKeySpec generateKeyFromText(String text) {
        try {
            BigInteger b = new java.math.BigInteger(String.format("%040x", new BigInteger(1, (text).getBytes("UTF-8"))), 16);
            byte [] keyBytes = new byte[16];
            byte [] bigBytes = b.toByteArray();
            System.arraycopy(bigBytes, 0, keyBytes, 0, Math.min(keyBytes.length, bigBytes.length));
            SecretKeySpec k = generateKey("AES", keyBytes);
            return k;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param key
     * @param text
     * @return
     */
    public SecretKeySpec encryptKey(SecretKeySpec key, String text) {
        try {
            BigInteger b = new java.math.BigInteger(String.format("%040x", new BigInteger(1, (text).getBytes("UTF-8"))), 16);
            byte [] keyBytes = new byte[16];
            byte [] bigBytes = b.toByteArray();
            System.arraycopy(bigBytes, 0, keyBytes, 0, Math.min(keyBytes.length, bigBytes.length));
            SecretKeySpec k = generateKey("AES", keyBytes);
            byte[] eb = encrypt(key.getEncoded(), k);
            SecretKeySpec keyToSend = new SecretKeySpec(eb, "AES");
            return keyToSend;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException | GeneralSecurityException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param input
     * @param ekey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     */
    public byte[] encrypt(byte[] input, SecretKeySpec ekey)
            throws NoSuchAlgorithmException, GeneralSecurityException {
        try{
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, ekey);
            byte[] cipherText;
            cipherText = cipher.doFinal(input);
            cipherText = Base64.encodeBase64(cipherText);
            return cipherText;
	} catch(Exception e) {
            System.out.println("Something went wrong in encryption");
            System.out.println(e.toString());
	}
	return null;
    }

    /**
     *
     * @param cipherText
     * @param ekey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     */
    public byte[] decrypt(byte[] cipherText, SecretKeySpec ekey)
            throws NoSuchAlgorithmException, GeneralSecurityException {
        try{
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, ekey);
    	    byte[] plainText;
            plainText = cipher.doFinal(cipherText);
            return plainText;
	} catch(NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException e) {
            System.out.println("Something went wrong in decryption");
            System.out.println(e.toString());
	}
	return null;
    }
    
    /**
     *
     * @param algorithm
     * @throws NoSuchAlgorithmException
     */
    private void generateKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
        SecretKey skey = kg.generateKey();
        key = new SecretKeySpec(skey.getEncoded(), algorithm);
        System.out.println("SecretKeySpec is for New Room: " + key.toString());
    }

    /**
     *
     * @param algorithm
     * @throws NoSuchAlgorithmException
     */
    private SecretKeySpec generateKey(String algorithm, String sKey) throws NoSuchAlgorithmException {
        return key = new SecretKeySpec(sKey.getBytes(), algorithm);
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public SecretKeySpec getLoginKey() throws RemoteException {
        return loginKey;
    }

    /**
     *
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, userid, password);
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return con;
    }

    /**
     *
     * @param name
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isUserName(String name) throws RemoteException {
        try {
			System.out.print("Checking for name ::"+name);
            con = getConnection();
            pstmt = con.prepareStatement("select * from users where name = ?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * @param name
     * @param pass
     * @param passDC
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isPassCorrect(String name, String pass, String passDC) throws RemoteException {

        try {

            Dpassword = TripleDES_Decrypt(pass);
            passDS = Digest(Dpassword);
            System.out.println("Digest Client  : " + passDC);
            System.out.println("Digest Server  : " + passDS);
        } catch (Exception ee) {
            System.out.println("Failed to Digest : " + ee.toString());
        }
        try {
            if (passDC.equals(passDS)) {

                try {
                    con = getConnection();
                    pstmt = con.prepareStatement("select pass from users where name = ?");
                    pstmt.setString(1, name);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        System.out.println("Befor check in DataBase.");
                        System.out.println("from DB: " + rs.getString(1));
                        System.out.println("from CL: " + passDS);
                        if (passDS.equals(rs.getString(1))) {
                            System.out.println("Successfully password");
                            return true;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ee) {
            System.out.println("Failed To Digest...");
        }
        return false;
    }

    /**
     *
     * @param name
     * @param pass
     */
    public void addUser(String name, String pass) {
        try {
            con = getConnection();
            pstmt = con.prepareStatement("insert into users (name,pass) values(?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, pass);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param name
     */
    public void removeUser(String name) {
        try {
            con = getConnection();
            pstmt = con.prepareStatement("delete from users where name=?");
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param name
     * @param message
     * @param room
     */
    public void updateMessage(String name, String message ,String room) {
        try {
            String fullMessage = new java.util.Date().toString() + "\n" + room + "\n"+ message;
            con = getConnection();
            pstmt = con.prepareStatement("select * from users where name=?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            rs.next();
            int clientId = rs.getInt(1);
            pstmt = con.prepareStatement("insert into message values(?,?)");
            pstmt.setInt(1, clientId);
            pstmt.setString(2, fullMessage);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param userName
     */
    public void deleteHistory(String userName) {
        try {
            con = getConnection();
            pstmt = con.prepareStatement("select * from users where name = ?");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();
            rs.next();
            int id = rs.getInt(1);
            pstmt = con.prepareStatement("delete from message where cid=?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public int getCountRegisted() {
        try {
            con = getConnection();
            pstmt = con.prepareStatement("select count(*) from users");
            rs = pstmt.executeQuery();
            rs.next();
            int length = rs.getInt(1);
            return length;
        } catch (Exception ee) {
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public String[] getRegisted() {
        try {
			System.out.println("in get reg **********************");
            con = getConnection();
			if(con!=null)
				System.out.println("Connection succrss");
			else
				System.out.println("Connection failed");
            pstmt = con.prepareStatement("select count(*) from users");
            rs = pstmt.executeQuery();
            int length;
            rs.next();
            length = rs.getInt(1);
            System.out.println("length : " + length);
            String[] users = new String[length];
            con = getConnection();
            pstmt = con.prepareStatement("select name from users");
            rs = pstmt.executeQuery();
            int index = 0;
            while (rs.next()) {
                users[index] = rs.getString(1);
                index++;
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public String[] getHistoyMessage(String name) {
        try {
            con = getConnection();
            pstmt = con.prepareStatement("select count(*) from message");
            rs = pstmt.executeQuery();
            int length;
            if (rs.next()) {
                length = rs.getInt(1);
            } else {
                return null;
            }
            System.out.println("length : " + length);
            String[] message = new String[length];

            con = getConnection();
            pstmt = con.prepareStatement("select * from users where name=?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            rs.next();
            int clientId = rs.getInt(1);
            pstmt = con.prepareStatement("select * from message where cid=?");
            pstmt.setInt(1, clientId);
            rs = pstmt.executeQuery();
            int index = 0;
            while (rs.next()) {
                message[index] = rs.getString(2) + "\n";
                index++;
            }
            return message;
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     *      End DataBase Methods
     *
     * @throws RemoteException
     * @throws Exception
     */
    public Server() throws RemoteException, Exception {
        super();

        Clients = new Vector<ClientInterface>();
        cipher = Cipher.getInstance(AES);
    }

    /**
     *
     * @param name
     * @param pass
     * @param passDC
     * @throws RemoteException
     */
    @Override
    public void register(String name, String pass, String passDC) 
            throws RemoteException {
				System.out.println("*** register "+name+" "+pass+" "+passDC);
        try {
            try {
                Dpassword = TripleDES_Decrypt(pass);
                System.out.println("d password: " + Dpassword);
                passDS = Digest(Dpassword);
                System.out.println("Digest Client  : " + passDC);
                System.out.println("Digest Server  : " + passDS);
                if (passDC.equals(passDS)) {
                    addUser(name, passDS);
                    ServerUI.registedList(getRegisted(), getCountRegisted());
                	System.out.println("match");
                } else {
                	System.out.println("not match");
                }
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param name
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean isUserLogin(String name) throws RemoteException {

        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            try {
                if (tempClient.getName().equals(name)) {
                    return true;
                }
            } catch (Exception ee) {
                System.out.println("isUserLogin: " + ee.toString());
            }
        }
        return false;
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public int getRoomId() throws RemoteException {
        return roomId++;
    }

    /**
     *
     * @param roomName
     * @param request
     * @param admin
     * @throws RemoteException
     */
    @Override
    public void createRoom(String roomName, String request, String admin) 
            throws RemoteException {
        try {
            generateKey(AES);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClientInterface roomAdmin = getAdminInfo(admin);
        SecretKeySpec keyTouse = generateKeyFromText(roomAdmin.getName()+roomAdmin.getPassword());
        try {
            request = decrypt(request, keyTouse);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] requestArray = request.split("-");
        if(requestArray.length<=2) {
            System.err.println("Something went wrong in reading the reauest");
            return;
        }
        String[] Selected = new String[requestArray.length-2];
        String req = requestArray[0];
        if(!req.equals("CreateRoom")) {
            System.err.println("Something went wrong in reading the reauest");
            return;
        }
        long time = Long.parseLong(requestArray[requestArray.length-1]);
        long currentTime = System.currentTimeMillis();
        if((currentTime-time) > 30000) {
            System.err.println("Something went wrong in reading the reauest");
            return;
        }
        for(int i=0; i<Selected.length;i++) {
            Selected[i] = requestArray[i+1];
        }
        roomKeys.addElement(key);
        roomNames.addElement(roomName);
        roomUsers.add(Selected);
        roomAdmins.add(roomAdmin);
        SecretKeySpec roomKey = getRoomKey(roomName);
        String[] selected = Selected;
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        System.out.println("Before Send Order To All.....");

        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            for (int i = 0; i < selected.length; i++) {
                if (tempClient.getName().equals(selected[i])) {
                    /*Here encrypt before sending*/
                    SecretKeySpec keyToSend = encryptKey(roomKey, (tempClient.getName()+tempClient.getPassword()));
                    tempClient.InviteToRoom(roomName, selected, keyToSend, roomAdmin.getName());
                }
            }
            try {
            } catch (Exception ee) {
                System.out.println("getConnect: " + ee.toString());
            }
        }
        System.out.println("Send All Order to All needed");
    }

    /**
     *
     * @param roomName
     * @param users
     */
    public void updateRoomUsers(String roomName, String[] users) {
        for (int i = 0; i < roomNames.size(); i++) {
            if (roomName.equals(roomNames.get(i))) {
                roomUsers.set(i, users);
            }
        }
    }

    /**
     *
     * @param roomName
     * @param request
     * @param users
     * @throws RemoteException
     */
    @Override
    public void addUsersToAroom(String roomName, String request, String[] users) 
            throws RemoteException {
        System.out.println("Before Send Order To All.....");
        ClientInterface roomAdmin = getAdmin(roomName);
        SecretKeySpec keyTouse = generateKeyFromText(roomAdmin.getName()+roomAdmin.getPassword());
        try {
            request = decrypt(request, keyTouse);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] requestArray = request.split("-");
        if(requestArray.length<=2) {
            System.err.println("Something went wrong in reading the reauest");
            return;
        }
        String[] selected = new String[requestArray.length-2];
        String req = requestArray[0];
        if(!req.equals("AddUsersToARoom")) {
            System.err.println("Something went wrong in reading the reauest");
            System.out.println("Something went wrong when adding new clients to "
                    + "the room: " + roomName);
            System.out.println("The user who is trying to add: " 
                    + roomAdmin.getName());
            return;
        }
        long time = Long.parseLong(requestArray[requestArray.length-1]);
        long currentTime = System.currentTimeMillis();
        if((currentTime-time) > 30000) {
            System.err.println("Something went wrong in reading the reauest");
            System.out.println("Something went wrong when adding new clients to "
                    + "the room: " + roomName);
            System.out.println("The user who is trying to add: " 
                    + roomAdmin.getName());
            return;
        }
        for(int i=0; i<selected.length;i++) {
            selected[i] = requestArray[i+1];
        }
        
        updateRoomUsers(roomName, users);
        for(int i=0; i<users.length; i++) {
            System.out.print(users[i] + "   ");
        }
        System.out.println();
        SecretKeySpec roomKey = getRoomKey(roomName);
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            for (int i = 0; i < selected.length; i++) {
                if (tempClient.getName().equals(selected[i])) {
                    System.out.println();
                    System.out.println("c: " + tempClient.getName() + "   " + selected[i]);
                    SecretKeySpec keyToSend = encryptKey(roomKey, (tempClient.getName()+tempClient.getPassword()));
                    tempClient.InviteToRoom(roomName, selected, keyToSend, roomAdmin.getName());
                }
            }
            try {
            } catch (Exception ee) {
                System.out.println("getConnect: " + ee.toString());
            }
        }
    }

    /**
     *
     * @param roomName
     * @param users
     * @throws RemoteException
     */
    @Override
    public void closeRoom(String roomName, String[] users) throws RemoteException {
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        System.out.println("Before Send close Order To All.....");

        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            for (int i = 0; i
                    < users.length; i++) {
                if (tempClient.getName().equals(users[i])) {
                    tempClient.closeRoom(roomName);
                }
            }
            try {
            } catch (Exception ee) {
                System.out.println("getConnect: " + ee.toString());
            }
        }
    }

    /**
     *
     * @param client
     * @throws RemoteException
     */
    @Override
    public void showHistory(ClientInterface client) throws RemoteException {

        String userName = client.getName();
        String[] message = getHistoyMessage(userName);
        ClientInterface tempClient = (ClientInterface) client;
        try {
            tempClient.showHistory(message);
        } catch (Exception ee) {
            System.out.println(ee.toString());
        }
    }

    /**
     *
     * @param userName
     * @throws RemoteException
     */
    @Override
    public void deleteMessage(String userName) throws RemoteException {
        deleteHistory(userName);
    }

    /**
     *
     * @return
     * @throws RemoteException
     */
    @Override
    public String[] getConnect() throws RemoteException {
        int size = Clients.size();
        String[] users = new String[size];

        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        int i = 0;
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            try {
                users[i] = tempClient.getName();
                i++;
            } catch (Exception ee) {
                System.out.println("getConnect: " + ee.toString());
            }
        }
        return users;
    }

    public int getCountOnline() throws RemoteException {
        return Clients.size();
    }

    /**
     *
     * @param client
     * @throws RemoteException
     */
    @Override
    public void login(ClientInterface client) throws RemoteException {
        Clients.addElement(client);
        System.out.print("==>" + client.getName() + " Joined id  \n");

        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            try {
                tempClient.showUsers(getConnect());
                for (int i = 0; i < roomNames.size(); i++) {
                    tempClient.updateLists(roomNames.get(i));
                }
            } catch (Exception ee) {
                System.out.println("login: " + ee.getMessage());
            }
        }
        ServerUI.onlineList(getConnect(), getCountOnline());
    }
    
    /**
     *
     * @param client
     * @throws RemoteException
     */
    @Override
    public void logout(ClientInterface client) throws RemoteException {
        String clientName = client.getName();
        for(int i=0; i<roomUsers.size(); i++) {
            for(int j=0; j<roomUsers.get(i).length; j++) {
                if(roomUsers.get(i)[j] != null)
                    if(roomUsers.get(i)[j].equals(clientName)) {
                        roomUsers.get(i)[j] = "";
                }
            }
        }
        System.out.print("-->" + client.getName() + " Leaving  \n");
        Clients.removeElement(client);

        for(int i=0; i<roomNames.size(); i++) {
            showUsersRoom(roomNames.get(i), getRoomUsers(roomNames.get(i)));
        }
        
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            try {
                tempClient.showUsers(getConnect());
                for (int i = 0; i < roomNames.size(); i++) {
                    tempClient.updateLists(roomNames.get(i));
                }
            } catch (Exception ee) {
                System.out.println("logout: " + ee.toString());
            }
        }
        ServerUI.onlineList(getConnect(), getCountOnline());
    }

    /**
     *
     * @param client
     * @param Mess
     * @throws RemoteException
     */
    @Override
    public void brodcastMessage(ClientInterface client, String Mess) throws RemoteException {
        System.out.println("applied..");
        String clientName = client.getName();
        publicMess = clientName + ": " + Mess;
        updateMessage(clientName, publicMess,"public chat:");
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            try {
                tempClient.showMessage(publicMess);
            } catch (Exception ee) {
                System.out.println("brodcastMessage to" + tempClient.getName() + " Faield : " + ee.toString());
                logout(tempClient);
            }
        }
        ServerUI.showMessage(publicMess);
    }

    /**
     *
     * @param Mess
     * @throws RemoteException
     */
    public void brodcastMessageByServer(String Mess) throws RemoteException {
        publicMess = "Server" + ": " + Mess;
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            try {
                tempClient.showMessage(publicMess);
            } catch (Exception ee) {
                System.out.println("brodcastMessage to " + tempClient.getName() + " Faield : " + ee.toString());
                logout(tempClient);
            }
        }
        ServerUI.showMessage(publicMess);
    }

    /**
     *
     * @param name
     */
    public void dropClient(String name) {
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            try {
                if (tempClient.getName().equals(name))
                tempClient.dropped();
            } catch (Exception ee) {                
            }
        }
    }

    /**
     *
     * @param roomname
     * @return
     */
    public SecretKeySpec getRoomKey(String roomname) {

        for (int i = 0; i < roomNames.size(); i++) {
            if (roomname.equals(roomNames.get(i))) {
                return roomKeys.get(i);
            }
        }
        System.out.println("Faild To Find RoomKey");
        return null;
    }

    /**
     *
     * @param roomname
     * @return
     */
    public ClientInterface getAdmin(String roomname) {

        for (int i = 0; i < roomNames.size(); i++) {
            if (roomname.equals(roomNames.get(i))) {
                return roomAdmins.get(i);
            }
        }
        System.out.println("Faild To Find the admin");
        return null;
    }

    /**
     *
     * @param clientname
     * @param roomname
     * @param Selected
     * @param message
     * @param messageD
     * @throws RemoteException
     */
    @Override
    public void multicastMessage(String clientname, String roomname, String[] Selected, String message, String messageD) throws RemoteException {

        dKey = getRoomKey(roomname);
        try {
            Dmessage = decrypt(message);
            System.out.println(" **** FROM THE SERVER  1 *****");
            System.out.println("Key Used            : " + dKey.toString());
            System.out.println("Encrpyption Message : " + message);
            System.out.println("Decrpyption Message : " + Dmessage);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Field to Decrption...");
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Field to Decrption...");
        }
        try {
            messD = Digest(Dmessage);
            System.out.println("Message Digest client: " + messageD);
            System.out.println("Message Digest server: " + messD);
        } catch (Exception ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (messageD.equals(messD)) {
            privateMess = (clientname + ":" + Dmessage).trim();
            try {
                Emessage = encrypt(privateMess);
                try {
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            //updateMessage(clientname, Dmessage, roomname);
            ClientInterface tempClient;
            Iterator itr = Clients.iterator();
            String[] selected = getRoomUsers(roomname);
            while (itr.hasNext()) {
                tempClient = (ClientInterface) itr.next();
                for (int i = 0; i < selected.length; i++) {
                    if (tempClient.getName().equals(selected[i])) {
                        tempClient.showRoomMessage(roomname, message, messageD);
                    }
                }
                try {
                } catch (Exception ee) {
                    System.out.println("multicastMessage to " + tempClient.getName() + " Faield : " + ee.toString());
                    logout(tempClient);
                }
            }
        }
    }

    /**
     *
     * @param roomName
     * @param users
     * @throws java.rmi.RemoteException
     */
    @Override
    public void updateRoomKey(String roomName, String[] users) throws RemoteException{
        try {
            generateKey(AES);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        int roomIndex = roomNames.indexOf(roomName);
        roomKeys.setElementAt(key, roomIndex);
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
            while (itr.hasNext()) {
                tempClient = (ClientInterface) itr.next();
                for (int i = 0; i < users.length; i++) {
                    try {
                        if (tempClient.getName().equals(users[i]) && !(users[i].equals(""))) {
                            tempClient.updateRoomKey(roomName, key);
                        }
                    } catch (RemoteException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                } catch (Exception ee) {
                    try {
                        System.out.println("update key to " + tempClient.getName() + " Faield : " + ee.toString());
                    } catch (RemoteException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        logout(tempClient);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }

    /**
     *
     * @param roomName
     * @return
     */
    public String [] getRoomUsers(String roomName) {
        for (int i = 0; i < roomNames.size(); i++) {
            if (roomName.equals(roomNames.get(i))) {
                return roomUsers.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param roomName
     * @param Selected
     * @throws RemoteException
     */
    @Override
    public void showUsersRoom(String roomName, String[] Selected) throws RemoteException {
        ClientInterface tempClient;
        Iterator itr = Clients.iterator();
        System.out.println("Before Send ListUsers To All.....");
        updateRoomUsers(roomName, Selected);
        //String[] selected = getRoomUsers(roomName);
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            for (int i = 0; i < Selected.length; i++) {
                if (tempClient.getName().equals(Selected[i]) && !(Selected[i].equals(""))) {
                    tempClient.showUsersRoom(roomName, Selected);
                }
            }
            try {
            } catch (Exception ee) {
                System.out.println("getConnect: " + ee.toString());
            }
        }
        System.out.println("Send Message to All needed");
    }

    /**
     *
     * @param Arg
     * @throws Exception
     */
    public static void main(String[] Arg) throws Exception {
    }

    /**
     *
     * @param admin
     * @return   the admin info of the given admin name
     * @throws RemoteException
     */
    private ClientInterface getAdminInfo(String admin) throws RemoteException {
        Iterator itr = Clients.iterator();
        ClientInterface tempClient;
        while (itr.hasNext()) {
            tempClient = (ClientInterface) itr.next();
            if(tempClient.getName().equals(admin)) {
                return tempClient;
            }
            try {
            } catch (Exception ee) {
                System.out.println("getConnect: " + ee.toString());
            }
        }
        System.out.println("Faild in finding the client name: " + admin);
        return null;
    }
}

