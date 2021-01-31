package com.purnima;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {
        String s2 = "RITWIK";
        System.out.println("\n" + s2 + " : " + StringIntoHashcode(s2));

        int n = 4;

        System.out.println(getRandomString(n));
        System.out.println("\n" + getRandomString(n) + " : " + StringIntoHashcode(getRandomString(n)));
    }
    /*
    With the help of Sha1 we can convert any string into hash code of 40 char. We are doing that below
     */
    public static String StringIntoHashcode(String input) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = msgDigest.digest(input.getBytes());

            BigInteger bigInteger = new BigInteger(1, messageDigest);

            String hashText = bigInteger.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getRandomString(int n) {
        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(n);
        for(int i = 0; i < n ; i++){
            int index = (int)(randomString.length() * Math.random());
            sb.append(randomString.charAt(index));
        }
        return sb.toString();
    }
}