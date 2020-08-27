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
    private UUID id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = hashPassword(password);
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
            e.printStackTrace();
            return "";
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return "";
        }

    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
