//test change

package model;
import java.io.Serializable;

public class Entry implements Serializable{
    private String service;
    private String username;
    private String password;
    private String notes;
    public Entry(String service, String username, String password, String notes){
        this.service = service;
        this.username = username;
        this.password = password;
        this.notes = notes;
    }
    public String getService(){
        return service;
    }
        public String getUsername(){
            return username;
        }
        public String getPassword(){
            return password;
        }
        public String getNotes(){
            return notes;
        }
    public String toString(){
        System.out.println(entry);
        return "Service: "+ service+
        "\nUsername: "+username+
        "\nPassword: "+ password+
        "\nNotes: "+ notes;
    }
    }