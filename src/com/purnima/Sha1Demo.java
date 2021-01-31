package com.purnima;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1Demo {

    public static String createFileChecksum(MessageDigest md, File infile) throws IOException {
        FileInputStream file = new FileInputStream(infile);
        byte [] bytes = new byte[1024];
        int count;

        while ((count = file.read(bytes)) != -1) {
            md.update(bytes, 0, count);
        }
        file.close();

        byte[] bytes1 = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes1) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        File file = new File("/Users/purnima/Documents/checkSumDemo.txt");

        String line;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("Current text : " + line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MessageDigest shaDigest = MessageDigest.getInstance("SHA-1");
        String shaChecksum = createFileChecksum(shaDigest, file);
        System.out.println("FileCheckSum : " + shaChecksum);

        try {
            System.out.println("Random String : "+ Main.getRandomString(60));
            FileOutputStream stream = new FileOutputStream(file);
            stream.write((Main.getRandomString(60)).getBytes());
            stream.close();
            System.out.println("Added...");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*String verify = Main.StringIntoHashcode(line);
        System.out.println("verifyCheckSum : " + verify);

         */
    }
}
