# Password Vault

A simple **Java-based Password Vault** application that securely stores and manages your passwords and credentials. This project demonstrates concepts like password hashing, encryption, and object-oriented programming in Java.

---

## Features

- **Master Password Authentication**  
  Users must create or enter a master password to access the vault. The password is securely hashed using PBKDF2 with a random salt.

- **Add Entries**  
  Each entry contains:
  - Service name
  - Username
  - Password
  - Notes

- **List Entries**  
  View all stored entries in a readable format.

- **Secure Data Handling**  
  Uses `HashUtil` for password hashing and `AESUtil` (for future expansion) for encryption. Entries are managed in memory via the `VaultService` class.

---

## Project Structure
```bash
src/
├─ Main.java # Entry point of the application
├─ crypto/
│ └─ HashUtil.java # Password hashing utility using PBKDF2
├─ model/
│ └─ Entry.java # Data model for vault entries
└─ vault/
└─ VaultService.java # Manages all entries in the vault
```

- `masterpass.txt` stores the hashed master password and salt.  
- `vault.data` can be used in future versions for persistent storage of encrypted entries.

---

## Technologies Used

- Java 17+  
- Java Security Libraries (PBKDF2, AES)  
- Object-Oriented Programming (OOP) principles  
- File I/O for storing the master password  

---

## How to Run

1. **Clone the repository**

```bash
git clone https://github.com/Layali-Shk/Password-Vault.git
cd Password-Vault
```

2. **Compile the project**
```bash
mkdir -p bin
javac -d bin src/Main.java src/crypto/HashUtil.java src/model/Entry.java src/vault/VaultService.java
```
3. **Run the program**
```bash
java -cp bim Main
```
4. **Follow the prompts**
- Create a master password if running for the first time.
- Use the menu to add or list entries.

---

## Future Improvements
- Encrypt stored entries using AESUtil for enhanced security.
- Save entries to a file (vault.data) to persist between sessions.
- Add the ability to edit or delete entries.
- Implement a GUI interface for better usability.

---
## License 
This project is licensed under the MIT License.





