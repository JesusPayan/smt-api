package utils;

import org.hibernate.query.sqm.tree.expression.Conversion;

import java.util.Base64;
import java.util.Random;



public class PasswordGenerator {
    public static String generatePassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
    public static String encodePassword(String pass){
       return Base64.getEncoder().withoutPadding().encodeToString(pass.getBytes());
    }


}
