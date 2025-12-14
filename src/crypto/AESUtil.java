package util; // put this in src/util/

import java.security.SecureRandom;           // Used to perform encryption/decryption
import java.util.Base64;     // Used to generate a secret key
import javax.crypto.Cipher;        // Represents the secret key
import javax.crypto.KeyGenerator; // Represents the initialization vector
import javax.crypto.SecretKey;   // To reconstruct key from bytes
import javax.crypto.spec.IvParameterSpec;    // Generates cryptographically strong random numbers

public class AESUtil{
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    public static SecretKey generateKey() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);//128 bit aes key
        return keyGen.generateKey();//returns a random key
    }
    public static IvParameterSpec generateIv(){
        byte[] iv = new byte[16];//aes block size 16 bytes
        new SecureRandom().nextBytes(iv);//random bytes fills
        return new IvParameterSpec(iv);//iv object created
    }
    public static String encrypt(String plaintext, SecretKey key, IvParameterSpec iv) throws Exception{
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,key,iv);
        byte[] encrypted=cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    public static String decrypt(String cipherText, SecretKey key, IvParameterSpec iv) throws Exception{
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,key,iv);
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted);
    }
}
