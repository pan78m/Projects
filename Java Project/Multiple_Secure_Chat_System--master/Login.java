/**
 * 
 *
 *
 * @author     
 */

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author macos
 */
public class Login extends javax.swing.JFrame {

    ServerInterface server;
    String Epassword;
    String Dpassword;
    String host;
    String userName;
    public String passD;
    public String Epass;
    public static final String AES = "AES";

    public String Digest(String Clear) throws Exception {
        String CLEAR = Clear;
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(CLEAR.getBytes(), 0, CLEAR.length());
        String Dig = new BigInteger(1, m.digest()).toString(16);
        return Dig;
    }

/*
 byte[] raw;
            String encryptedString;
            SecretKeySpec skeySpec;
            byte[] encryptText = text.getBytes();
            Cipher cipher;
            try {
                raw = Base64.decodeBase64(secretKey);
                skeySpec = new SecretKeySpec(raw, "AES");
                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
            } 
*/

   public static String TripleDES_Encrypt(String message) throws Exception {
	   System.out.println("*** In TripleDES_Encrypt "+message);
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey Lkey = new SecretKeySpec(keyBytes, "DESede");
        //System.out.println("key used E : " + Lkey.toString());
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher ecipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        ecipher.init(Cipher.ENCRYPT_MODE, Lkey, iv);
        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = ecipher.doFinal(plainTextBytes);
        // final String encodedCipherText = new sun.misc.BASE64Encoder()
        // .encode(cipherText);
        return Base64.encodeBase64String(cipherText);
    }

    public static String TripleDES_Decrypt(String text) throws Exception {
    	byte[] message = Base64.decodeBase64(text);
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey Lkey = new SecretKeySpec(keyBytes, "DESede");
        //System.out.println("key used D : " + Lkey.toString());
        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        decipher.init(Cipher.DECRYPT_MODE, Lkey, iv);
        // final byte[] encData = new
        // sun.misc.BASE64Decoder().decodeBuffer(message);
        final byte[] plainText = decipher.doFinal(message);
        return new String(plainText, "UTF-8");
    }

    /** Creates new form Login */
    public Login() throws NoSuchAlgorithmException, NoSuchPaddingException, MalformedURLException {
        super("Login window");
        host = JOptionPane.showInputDialog("Enter Location:", "localhost");
        try {
            server = (ServerInterface) Naming.lookup("rmi://" + host + "/Server");
			if(server!=null)
				System.out.println("server called");
            //Registry registry = LocateRegistry.getRegistry(host, 1099);
            //server = (ServerInterface) registry.lookup(ServerInterface.class.getSimpleName());
        } catch (NotBoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Failed To Connect Srver ip:" + host, "Error Message", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        initComponents();
        getRootPane().setDefaultButton(jButton2);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Chat - Application");

        jButton1.setText("Register");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Password :");

        jLabel3.setText("User Name :");

        jLabel4.setText("Re-Password :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPasswordField2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-269)/2, (screenSize.height-214)/2, 269, 214);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
		System.out.println("*** jButton1ActionPerformed");
        if (!jPasswordField1.isVisible()) {
            jPasswordField1.setVisible(true);
            jLabel4.setVisible(true);
        } else {
            String name = jTextField2.getText().trim();
            if (name.matches("^[a-zA-Z0-9_-]{3,15}$")) {
                try {
                    if (server.isUserName(name)) {
                        JOptionPane.showMessageDialog(this, "ERROR, this user name exsit", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {
						System.out.println("*** else username check");
                        char[] pass1 = jPasswordField1.getPassword();
                        char[] pass2 = jPasswordField2.getPassword();
                        System.out.println("*** Original Password: " + pass1);
                        if (new String(pass1).length() >= 6) {
                            if (Arrays.equals(pass2, pass1)) {
                                try {
                                    Epassword = TripleDES_Encrypt(new String(pass2));
                                    System.out.println("Encryption Password: " + Epassword);
                                    System.out.println("Decryption Password: " + TripleDES_Decrypt(Epassword));

                                    passD = Digest(new String(pass2));
                                } catch (Exception ex) {
                                    System.out.println("Failed to Digest.");
                                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                System.out.println("Digest Password: " + passD);
                                server.register(name, Epassword, passD);
                                JOptionPane.showMessageDialog(this, "you successflly registr", "Sucess Register", JOptionPane.INFORMATION_MESSAGE);
                                jPasswordField1.setVisible(false);
                                jLabel4.setVisible(false);
                            } else {
                                JOptionPane.showMessageDialog(this, "ERROR, Password Miss Match", "Error Message", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "ERROR, Password Must More than 6 charachter", "Error Message", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Enter Correct Name", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
        jTextField2.setText("");
        jPasswordField1.setText("");
        jPasswordField2.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
		System.out.println("in action --2");
        String name = jTextField2.getText().trim();
        char[] pass2 = jPasswordField2.getPassword();
        try {
            if (server.isUserName(name)) {
                try {
                    Epassword = TripleDES_Encrypt(new String (pass2));
                    System.out.println("Encryption Password: " + Epassword);
                    System.out.println("Decryption Password: " + TripleDES_Decrypt(Epassword));

                    passD = Digest(new String (pass2));
                    System.out.println("Original Password: " + new String (pass2));
                    System.out.println("Digest   Password: " + passD);
                } catch (Exception ee) {
                    System.out.println("Field to Digest :" + ee.toString());
                }
                if (server.isPassCorrect(name, Epassword, passD)) {
                    if (server.isUserLogin(name)) {
                        JOptionPane.showMessageDialog(this, "ERROR,you already Logged in", "Error Message", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "you successflly logged in", "Sucess Login", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("password is:" + pass2);
                        this.setVisible(false);
                        Client newClient = new Client(name);
                        newClient.setPassword(passD);
                        new ClientUI(newClient, host).setVisible(true);
                        server.login(newClient);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "ERROR, Wrong Password ", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "ERROR, this name not registed", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    Login log = new Login();
                    log.setVisible(true);
                    log.jPasswordField1.setVisible(false);
                    log.jLabel4.setVisible(false);
                } catch (Exception ee) {
                    System.out.println(ee.toString());
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
