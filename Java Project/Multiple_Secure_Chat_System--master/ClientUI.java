/**
 * 
 *
 *
 * @author     
 */

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.*;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

public class ClientUI extends javax.swing.JFrame {

    static ServerInterface server;
    static Iterator<newRoom> itr;
    static Client client;
    String userName;
    static String host;
    static int enterRoom;
    private static Cipher cipher;

    /**
     *
     * @param newClient
     * @param host1
     */
    public ClientUI(Client newClient, String host1) {
        super();      
        try {
            host = host1;
            server = (ServerInterface)Naming.lookup("rmi://" + host + "/Server");
            client = newClient;
            client.setConnectStatus(1);
            userName = client.getName();


        } catch (NotBoundException | MalformedURLException 
                | RemoteException ee) {
            System.out.println(ee.toString());
        }
        initComponents();
        getRootPane().setDefaultButton(jButton1);
    }
    
    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     *
     * @param algorithm
     * @param sKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static SecretKeySpec generateKey(String algorithm, byte[] sKey) 
            throws NoSuchAlgorithmException {
    try{
        return new SecretKeySpec(sKey, algorithm);
        } catch(Exception e) {}
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
    public static byte[] encrypt(byte[] input, SecretKeySpec ekey)
            throws NoSuchAlgorithmException, GeneralSecurityException {
        try{
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, ekey);
            byte[] cipherText;
            cipherText = cipher.doFinal(input);
            cipherText = Base64.encodeBase64(cipherText);
            return cipherText;
	} catch(NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IllegalBlockSizeException 
                | BadPaddingException e) {
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
    public static byte[] decrypt(byte[] cipherText, SecretKeySpec ekey)
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
     * @param plainText
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     */
    public static String encrypt(String plainText, SecretKeySpec key)
            throws NoSuchAlgorithmException, GeneralSecurityException {
        try{
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] input = plainText.getBytes("UTF8");
            byte[] cipherText;
            cipherText = cipher.doFinal(input);
            cipherText = Base64.encodeBase64(cipherText);
            return new String(cipherText);
        } catch(InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println("Something went wrong in encryption");
            System.out.println(e.toString());
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
     */
    public static void dropped() {
        try {
            server.logout(client);
            System.exit(0);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param roomName
     * @throws RemoteException
     */
    public static void updateLists(String roomName) throws RemoteException {
        newRoom tempRoom;
        itr = client.room.iterator();
        while (itr.hasNext()) {
            tempRoom = (newRoom) itr.next();
            if (roomName.equals(tempRoom.getRoomName())) {
                tempRoom.fillOtherUsers();
            }
        }
    }

    /**
     *
     * @param roomName
     * @param Mess
     * @param messageD
     */
    public static void showRoomMessage(String roomName, String Mess, String messageD) throws RemoteException {
        System.out.println("client: " + client.getName());
        newRoom tempRoom;
        itr = client.room.iterator();
        while (itr.hasNext()) {
            tempRoom = (newRoom) itr.next();
            if (roomName.equals(tempRoom.getRoomName())) {
                tempRoom.showMessage(Mess, messageD);
                System.out.println("r: " + roomName);
            }
        }
        System.out.println("Show Client Private Chat ...");
    }

    /**
     *
     * @param roomName
     * @param key
     */
    public static void updateRoomKey(String roomName, SecretKeySpec key) {
        newRoom tempRoom;
        itr = client.room.iterator();
        while (itr.hasNext()) {
            tempRoom = (newRoom) itr.next();
            if (roomName.equals(tempRoom.getRoomName())) {
                tempRoom.updateKey(key);
            }
        }
        System.out.println("Show Client Private Chat ...");
    }

    /**
     *
     * @param roomName
     */
    public static void closeRoom(String roomName) {
        newRoom tempRoom;
        itr = client.room.iterator();
        System.out.println("closing");
        while (itr.hasNext()) {
            tempRoom = (newRoom) itr.next();
            System.out.println("r: " + tempRoom.getRoomName());
            if (roomName.equals(tempRoom.getRoomName())) {
                tempRoom.dispose();
                itr.remove();
            }
        }
        System.out.println("end closing");
    }

    /**
     *
     * @param key
     * @param text
     * @return
     */
    public static SecretKeySpec decryptKey(SecretKeySpec key, String text) {
        try {
            BigInteger b = new java.math.BigInteger(String.format("%040x", 
                    new BigInteger(1, (text).getBytes("UTF-8"))), 16);
            byte [] keyBytes = new byte[16];
            byte [] bigBytes = b.toByteArray();
            System.arraycopy(bigBytes, 0, keyBytes, 0, Math.min(keyBytes.length, 
                    bigBytes.length));
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
     * @param key
     * @param text
     * @return
     */
    public static SecretKeySpec encryptKey(SecretKeySpec key, String text) {
        try {
            BigInteger b = new java.math.BigInteger(String.format("%040x", 
                    new BigInteger(1, (text).getBytes("UTF-8"))), 16);
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
     * @param roomName
     * @param selected
     * @param key
     * @param admin
     * @throws RemoteException
     * @throws MalformedURLException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     */
    public static void showInvite(String roomName, String[] selected, SecretKeySpec key, String admin) throws RemoteException, MalformedURLException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        SecretKeySpec keyToUse;
        keyToUse = decryptKey(key, (client.getName()+client.getPassword()));
        client.clientCreateRoom(client.getName(), roomName, selected, keyToUse, host, admin);
        System.out.println("Inivited Accepted");
        newRoom tempRoom;
        itr = client.room.iterator();

        while (itr.hasNext()) {
            tempRoom = (newRoom) itr.next();
            System.out.println("c: " + client.getName() + "  r: " + tempRoom.getRoomName());
            if (roomName.equals(tempRoom.getRoomName())) {
                tempRoom.setVisible(true);
            }
        }
        System.out.println("Show Client Private Chat ...");
    }

    /**
     *
     * @param message
     * @throws RemoteException
     * @throws MalformedURLException
     */
    public static void showHistory(String[] message) throws RemoteException, MalformedURLException {
        client.createHistory(client.getName(), host);
        client.hMessage.setVisible(true);
        client.hMessage.showHistory(message);
        client.hMessage.setDisplay(1);
    }

    /**
     *
     * @param message
     */
    public static void showMessage(String message) {
        jTextArea1.append(message + "\n");
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
    }

    /**
     *
     * @param users
     */
    public static void showUsers(String[] users) {
        list1.removeAll();
        for (int i = 0; i < users.length; i++) {
            list1.add(users[i]);
        }
    }

    /**
     *
     * @param roomName
     * @param Selected
     * @throws RemoteException
     */
    public static void showUsersRoom(String roomName, String[] Selected) 
            throws RemoteException {
        newRoom tempRoom;
        itr = client.room.iterator();
        while (itr.hasNext()) {
            tempRoom = (newRoom) itr.next();
            if (roomName.equals(tempRoom.getRoomName())) {
                tempRoom.showUsers(Selected);
                tempRoom.selected = Selected;
                tempRoom.fillOtherUsers();
            }
        }
        System.out.println("Show Client Private Chat ...");
    }

    public static void closeAllrooms() throws RemoteException {
        System.out.println(" --------- client name: " + client.getName());
        newRoom tempRoom;
        itr = client.room.iterator();
        System.out.println("closing");
        while (itr.hasNext()) {
            tempRoom = (newRoom) itr.next();
            System.out.println("r: " + tempRoom.getRoomName());
                tempRoom.dispose();
                itr.remove();
            }
        System.out.println("rooms closed");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jOptionPane1 = new javax.swing.JOptionPane();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        list1 = new java.awt.List();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel4.setForeground(new java.awt.Color(255, 51, 0));
        jLabel4.setText("Chat - Application");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Password    :");

        jButton4.setText("Login");

        jButton5.setText("Register");

        jLabel6.setText("User Name  :");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButton4)
                        .addGap(42, 42, 42)
                        .addComponent(jButton5))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel4))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField4)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat App - " + userName + " window");
        setBackground(new java.awt.Color(255, 0, 0));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTextField1.setText(" ");
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Send Message");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        list1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        list1.setMultipleMode(true);

        jButton2.setText("New Room");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Public Chat Room");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Users Connected");

        jButton6.setText("history Message");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton6)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2)))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1)
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jButton3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleParent(jButton1);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents
    // Send Buton
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            	String message = jTextField1.getText().trim();
             	System.out.println("Before Send to server");
            	if (!message.equals("")) {
                	server.brodcastMessage(client, message);
            	}

        } catch (RemoteException ex) {
            System.out.println("Failed Send to server");
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextField1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed
// Closing Window Action
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            // TODO add your handling code here:
            int d = JOptionPane.showConfirmDialog(this, "Are You Sure ?", "Exit Window ", 1, JOptionPane.OK_CANCEL_OPTION);
            if (d == 0) {
                closeAllrooms();
                server.logout(client);
                System.exit(0);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_jTextField4ActionPerformed
    // New Room Button
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int usersConnected = list1.getItemCount();

        String[] usersSelected = new String[usersConnected];
        int index = 0;
        int owner = 0;
        String users = "";
        for (int i = 0; i < usersConnected; i++) {
            if (list1.isIndexSelected(i)) {
                users = users + list1.getItem(i) + "-";
                if (userName.equals(list1.getItem(i))) {
                    owner++;
                }
                usersSelected[index] = list1.getItem(i);
                index++;
            }
        }
        if (index <= 1 || owner == 0) {
            JOptionPane.showMessageDialog(this, "Ruls to Create Private Chat:\n1. Select your self.\n2. Select two users at least.", "Warring Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        //String roomPass = JOptionPane.showInputDialog("Enter RooM Password: ");
        try {
            int roomid = server.getRoomId();
            String roomName = " Room ID: " + roomid + " Created By " + client.getName()  ;
            System.out.println("Room Name: " + roomName);
            System.out.println("before create Room & show list");
            long currentTime = System.currentTimeMillis();
            String request = "CreateRoom-" + users + currentTime;
            SecretKeySpec keyToUse = generateKeyFromText(client.getName()+client.getPassword());
            request = encrypt(request, keyToUse);
            server.createRoom(roomName, request, client.getName());
            server.showUsersRoom(roomName, usersSelected);
            System.out.println("after create Room & show lsit ");

        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    // Exit Button
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            int d = JOptionPane.showConfirmDialog(this, "Are You Sure ?", "Exit Window ", 1, JOptionPane.OK_CANCEL_OPTION);
            if (d == 0) {
                server.logout(client);
                System.exit(0);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    // History Button
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            // TODO add your handling code here:
            server.showHistory(client);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private static javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private static java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
