package SecureTextAndVoiceChat.SharedClasses;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


public class SecurityTasks {
    
    public static SecretKey createAES() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException, InvalidKeyException, BadPaddingException {
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(128);
    SecretKey AESKey = keyGen.generateKey(); // My AES key

     byte[] raw = AESKey.getEncoded();
     System.out.println("AES KEY is " +DatatypeConverter.printHexBinary( raw));
     System.out.println("length of key in bytes "+raw.length); // Prints 16
        return AESKey;
    }
public static byte[] EncryptAESwithPublicKey(SecretKey AESKey,RSAPublicKey pub) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException
     { 
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.ENCRYPT_MODE, pub);       
    byte[] cipherData;
    cipherData = cipher.doFinal(AESKey.getEncoded());
    System.out.println("cipherdata(Encrypted with public key): " +DatatypeConverter.printHexBinary(cipherData));
    System.out.println("cipherdata length in bytes: " +cipherData.length);
    return cipherData;
}
public static byte[] DecryptAESwithPrivateKey(byte[] encryptedKey,RSAPrivateKey priv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException
     { 
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, priv);    
    byte[] decryptedKey;
    decryptedKey = cipher.doFinal(encryptedKey);
    System.out.println("decryptedKey: "+ DatatypeConverter.printHexBinary(decryptedKey));
    System.out.println("length of decryptedKey: "+decryptedKey.length);
    return decryptedKey;
}
public static SealedObject EncryptRecordPacketwithTheSessionKey(SecretKey AESKey,RecordPacket recordPacket) throws IOException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
{
    
  Cipher c = Cipher.getInstance("AES");
  c.init(Cipher.ENCRYPT_MODE,AESKey);
  SealedObject encryptedRecordPacket = new SealedObject(recordPacket,c);
  return encryptedRecordPacket;
 } 
public static RecordPacket DecryptRecordPacketwithTheSessionKey(SecretKey AESKey,SealedObject secretObject) throws IOException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, ClassNotFoundException
{
  
  Cipher c = Cipher.getInstance("AES");
  c.init(Cipher.ENCRYPT_MODE,AESKey);
  RecordPacket recordPacket = (RecordPacket) secretObject.getObject(AESKey);  
  return recordPacket;
 } 
public static SealedObject EncryptObjectwithTheSessionKey(SecretKey AESKey,Object object) throws IOException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
{
    
  Cipher c = Cipher.getInstance("AES");
  c.init(Cipher.ENCRYPT_MODE,AESKey);
  SealedObject encryptedObject = new SealedObject((Serializable) object,c);
  return encryptedObject;
 } 
public static Object DecryptObjectwithTheSessionKey(SecretKey AESKey,SealedObject secretObject) throws IOException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, ClassNotFoundException
{
  
  Cipher c = Cipher.getInstance("AES");
  c.init(Cipher.ENCRYPT_MODE,AESKey);
  Object object =  (Object) secretObject.getObject(AESKey);  
  return object;
 } 
    
}

