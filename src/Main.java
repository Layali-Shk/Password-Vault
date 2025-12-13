
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Main{
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        File file = new File("masterpass.txt");
        byte[] salt;
        String storedHash;
        if (!file.exists()){
            System.out.print("Create a master password: ");
            String masterPassword = scanner.nextLine();
            salt = HashUtil.generateSalt();
            storedHash = HashUtil.hashPassword(masterPassword, salt);
            try (FileOutputStream fos = new FileOutputStream(file)){
                fos.write(salt);
                fos.write(storedHash.getBytes());

            }
            System.out.println("Master password created and saved!");

        }else{
            byte[] fileBytes = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)){
                fis.read(fileBytes);
            }
            salt = new byte[16];
            System.arraycopy(fileBytes,0,salt,0,16);
            storedHash=new String(fileBytes,16,fileBytes.length-16);
            System.out.print("Enter master password: ");
            String enteredPassword = scanner.nextLin();
            if (HashUtil.verifyPassword(enteredPassword,salt,storedHash)){
                System.out.println("Access granted!");

            }else{
                System.out.println("Access denied!");
                scanner.close();
                return;

            }
            scanner.close();
        }
    }
}