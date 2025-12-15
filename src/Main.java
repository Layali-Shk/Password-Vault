import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import crypto.HashUtil;
import vault.VaultService;
import model.Entry;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        File file = new File("masterpass.txt");

        byte[] salt;
        String storedHash;

        // --- Authentication ---
        if (!file.exists()) {
            System.out.print("Create a master password: ");
            String masterPassword = scanner.nextLine();

            salt = HashUtil.generateSalt();
            storedHash = HashUtil.hashPassword(masterPassword, salt);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(salt);
                fos.write(storedHash.getBytes());
            }

            System.out.println("Master password created and saved!");
        } else {
            byte[] fileBytes = new byte[(int) file.length()];

            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(fileBytes, 0, fileBytes.length);
            }

            salt = new byte[16];
            System.arraycopy(fileBytes, 0, salt, 0, 16);
            storedHash = new String(fileBytes, 16, fileBytes.length - 16);

            System.out.print("Enter master password: ");
            String enteredPassword = scanner.nextLine();

            if (!HashUtil.verifyPassword(enteredPassword, salt, storedHash)) {
                System.out.println("Access denied!");
                return; // stop program
            }

            System.out.println("Access granted!");
        }

        // --- Vault Service ---
        VaultService vaultService = new VaultService();

        // --- Menu loop ---
        boolean running = true;

        while (running) {
            System.out.println("\n--- Password Vault ---");
            System.out.println("1. Add entry");
            System.out.println("2. List entries");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Service: ");
                    String service = scanner.nextLine();

                    System.out.print("Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    System.out.print("Notes: ");
                    String notes = scanner.nextLine();

                    Entry entry = new Entry(service, username, password, notes);
                    vaultService.addEntry(entry);

                    System.out.println("Entry added.");
                    break;

                case "2":
                    for (Entry e : vaultService.getEntries()) {
                        System.out.println("\n" + e);
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        // --- Close scanner last ---
        scanner.close();
    }
}
