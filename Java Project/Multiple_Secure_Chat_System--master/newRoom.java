/**
 * 
 *
 *
 * @author     
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author macos
 */
public class newRoom extends javax.swing.JFrame {
    public String messD;
    public String Dmessage;
    public String Emessage;
    JList list;
    ServerInterface server;
    String[] selected;
    String host;
    String roomName;
    String clientName;
    String admin;
    private SecretKeySpec key;
    private Cipher cipher;
    public static final String AES = "AES";
    Client client;
    java.util.List<newRoom> rooms = new ArrayList<>();

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
        cipher.init(Cipher.DECRYPT_MODE, key);
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
     * @param clientname
     * @param roomname
     * @param usersSelected
     * @param key1
     * @param host1
     * @param admin
     * @param client
     * @throws MalformedURLException
     * @throws RemoteException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public newRoom(String clientname, String roomname, String[] usersSelected, 
            SecretKeySpec key1, String host1, String admin, Client client) 
            throws MalformedURLException, RemoteException, NoSuchAlgorithmException, 
            NoSuchPaddingException {
        super("Private Chat");
        clientName = clientname;
        selected = usersSelected;
        roomName = roomname;
        key = key1;
        host = host1;
        cipher = Cipher.getInstance(AES);
        this.admin = admin;
        this.client = client;
        try {
            server = (ServerInterface) Naming.lookup("rmi://" + host + "/Server");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        
        getRootPane().setDefaultButton(jButton1);
        
        if(this.admin.equals(clientName)) {
            list2.setVisible(true);
            jLabel3.setVisible(true);
            jButton2.setVisible(true);
            jButton3.setVisible(true);
            fillOtherUsers();
        } else {
            list2.setVisible(false);
            jLabel3.setVisible(false);
            jButton2.setVisible(false);
            jButton3.setVisible(false);
        }
    }

    /**
     *
     * @throws RemoteException
     */
    public void fillOtherUsers() throws RemoteException {
        list2.removeAll();
        String[] users = server.getConnect();
        java.util.List<String> roomUsers = new ArrayList<String>();
        Collections.addAll(roomUsers, selected);
        for (int i = 0; i < users.length; i++) {
            if(!roomUsers.contains(users[i]))
                list2.add(users[i]);
        }
    }

    /**
     *
     * @return
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     *
     * @return
     */
    public String getAdmin() {
        return admin;
    }

    /**
     *
     * @return
     */
    public String[] geSelected() {
        return selected;
    }
    
    
    /**
     *
     * @param algorithm
     * @param sKey
     * @return
     * @throws NoSuchAlgorithmException
     */
    public SecretKeySpec generateKey(String algorithm, byte[] sKey) throws NoSuchAlgorithmException {
    try{
        return new SecretKeySpec(sKey, algorithm);
        } catch(Exception e) {}
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
     * @param plainText
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     */
    public String encrypt(String plainText, SecretKeySpec key)
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
	} catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
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
	} catch(NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println("Something went wrong in decryption");
            System.out.println(e.toString());
	}
	return null;
    }
    
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
    public void showMessage(String message, String messageD) {
        try {
            Dmessage = decrypt(message);
            System.out.println(" ****    FROM THE CLIENT 2     *****");
            System.out.println("Key Used            : " + key.toString());
            System.out.println("Encrpyption Message : " + message);
            System.out.println("Decrpyption Message : " + Dmessage);

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            messD = Digest(Dmessage);
            System.out.println("Message Digest client: " + messD);
            System.out.println("Message Digest server: " + messageD);
        } catch (Exception ex) {
            Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (messageD.equals(messD)) {
            this.jTextArea1.append(Dmessage + "\n");
            this.jTextArea1.setCaretPosition(this.jTextArea1.getDocument().getLength());
        }
    }

    public void showUsers(String[] users) {
        list1.removeAll();
        if(users != null) {
            for (int i = 0; i < users.length; i++) {
                if(users[i] != null)
                    if (!users[i].equals(""))
                        list1.add(users[i]);
            }
        }
        selected = users;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        list1 = new java.awt.List();
        list2 = new java.awt.List();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setTitle("Private Chat -  "+ roomName);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Private Chat - " + clientName);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("users Conneted");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Send Message");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("users Online");

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jButton1)
                            .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton3)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(515, 406));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String message = jTextField1.getText().trim();
        if (!message.equals("")) {
            message = (clientName + ": " + jTextField1.getText().trim()).trim();
            try {
                try {
                    Emessage = encrypt(message);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
                } catch (GeneralSecurityException ex) {
                    Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    messD = Digest(message);
                } catch (Exception ex) {
                    Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    System.out.println(" ****    FROM THE CLIENT 1    *****");
                    System.out.println("Key Used            : " + key.toString());
                    System.out.println("Encrpyption Message : " + Emessage);
                    System.out.println("Decrpyption Message : " + decrypt(Emessage));
                    System.out.println("Message Digest 1    : " + messD);

                } catch (Exception ex) {
                    Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Field to Decrption...");
                }
                server.multicastMessage(clientName, roomName, selected, Emessage, messD);
            } catch (RemoteException ex) {
                Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jTextField1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        for (int i = 0; i < selected.length; i++) {
            if(selected[i] != null) {
                if (clientName.equals(selected[i])) {
                    selected[i] = "";
                    try {
                        server.showUsersRoom(roomName, selected);
                        server.updateRoomKey(roomName, selected);
                    } catch (RemoteException ex) {
                        Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(client.room.remove(this)) {
                        System.out.println("removed secuessfully");
                    }
                }
            }
        }
        try {
            System.out.println("c: " + clientName + "   admin: " + admin);
                for(int i=0; i<selected.length; i++) {
                        System.out.print(selected[i] + "   ");
                    }
                    System.out.println();
                server.showUsersRoom(roomName, selected);
        } catch (RemoteException ex) {
            Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //int usersConnected = list1.getItemCount();

        String[] usersSelected = list2.getSelectedItems();
        String users = "";
        if(usersSelected.length != 0) {
            try {
                String[] newList = new String[usersSelected.length + selected.length];
                int count=0;
                for(int i=0; i<selected.length; i++) {
                    newList[count++] = selected[i];
                }
                for(int i=0; i<usersSelected.length; i++) {
                    newList[count++] = usersSelected[i];
                    users = users + usersSelected[i] + "-";
                }
                SecretKeySpec eKey = encryptKey(key, (client.getName()+client.getPassword()));
                selected = newList;
                long currentTime = System.currentTimeMillis();
                SecretKeySpec keyToUse = generateKeyFromText(client.getName()+client.getPassword());
                String request = "AddUsersToARoom-" + users + currentTime;
                request = encrypt(request, keyToUse);
                server.addUsersToAroom(roomName, request, selected);
                server.showUsersRoom(roomName, selected);
            } catch (RemoteException ex) {
                Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GeneralSecurityException ex) {
                Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String[] usersSelected = list1.getSelectedItems();
        boolean adminSelected = false;
        for(int i=0; i<usersSelected.length; i++) {
            if(usersSelected[i].equals(admin)) {
                adminSelected = true;
            }
        }
        if (adminSelected) {
            JOptionPane.showMessageDialog(this, "You can not choose yourself", "Warring Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(usersSelected.length != 0) {
            try {
                server.closeRoom(roomName, usersSelected);
                for(int i=0; i<usersSelected.length; i++) {
                    list1.remove(usersSelected[i]);
                }
                String[] users = list1.getItems(); 
                server.showUsersRoom(roomName, users);
                server.updateRoomKey(roomName, users);
            } catch (RemoteException ex) {
                Logger.getLogger(newRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private java.awt.List list1;
    private java.awt.List list2;
    // End of variables declaration//GEN-END:variables

    void updateKey(SecretKeySpec key) {
        this.key = key;
    }
}
