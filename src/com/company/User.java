package com.company;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class User {
    private String firstname;
    private String lastname;
    private UUID id;
    private String username;
    private String department;
    private String adress;
    private String password;

    public User(String firstname, String lastname, String username, String password, String adress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = hashPassword(password);
        this.adress = adress;
    }

    private String hashPassword(String password) {
        try{
            byte[] salt = new byte[16];
            Random random = new Random();
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();
            Base64.Encoder enc = Base64.getEncoder();
            String saltString = enc.encodeToString(salt);
            String hashString = enc.encodeToString(hash);
            System.out.println(enc.encodeToString(salt));
            return saltString + hashString;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No such algoithm!");
            return "";
        } catch (InvalidKeySpecException e) {
            System.out.print("Invalid key");
            return "";
        }

    }

    public void changePassword(String newPassword){
        this.password = hashPassword(newPassword);
    }

    public String getLastname() {
        return lastname;
    }

    public String getDepartment() {
        return department;
    }

    public String getAdress() {
        return adress;
    }

    public String getFirstname() {
        return firstname;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
