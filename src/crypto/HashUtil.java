import java.security.SecureRandom;      // for generating random salt
import java.security.spec.KeySpec;      // used for PBKDF2
import javax.crypto.SecretKeyFactory;   // PBKDF2 hashing
import javax.crypto.spec.PBEKeySpec;    // PBKDF2 specification
import java.util.Base64;                // store hash as string

public class HashUtil{
public static byte[] g
generateSalt() throws Exception{
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
}

public static String hashPassword(String password, byte[] salt) throws Exception{
    int iterations = 65536;
    int keyLength = 256;
    KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    byte[] hash = factory.generateSecret(spec).getEncoded();
    return Base64.getEncoder().encodeToString(hash);


}

public static boolean verifyPassword(String enteredPassword, byte[] salt, String storedHash)throws Exception{
    String newHash = hashPassword(enteredPassword, salt);
    return newHash.equals(storedHash);
}
}