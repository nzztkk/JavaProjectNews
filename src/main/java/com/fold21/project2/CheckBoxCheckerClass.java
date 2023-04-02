package com.fold21.project2;

import java.io.*;

public class CheckBoxCheckerClass {

    private static final String FILENAME = "C:\\FrequentlyUsedFiles\\separate-work\\works\\java\\AnyData-From-Projects\\UsersDataForCheckBox.txt";

    public static void saveUserData(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            bw.write("Username: " + username + "; " + "Password: " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getUserData() {
        String[] userData = new String[2];
        File file = new File(FILENAME);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                if (line != null) {
                    String[] tokens = line.split(":");
                    userData[0] = tokens[0];
                    userData[1] = tokens[1];
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userData;
    }

    public static void deleteUserData() {
        File file = new File(FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

}
