package org.example.validator;

public class Validator {
    //TODO implements
    public static boolean validateEmail(String email) {
        String[] words = email.split("@");
        if (words.length == 2) {
            return true;
        }
        return false;
    }
}