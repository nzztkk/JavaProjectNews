package com.fold21.project2;

import java.io.*;
import java.util.Scanner;

public class SaveAndReadDataInFile {
    public static void saveUser(String firstName, String lastName, String username, String password, String gender) throws IOException {
        // Открываем файл для записи
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\FrequentlyUsedFiles\\separate-work\\works\\java\\AnyData-From-Projects\\UsersData.txt", true));
        // Записываем данные пользователя в файл, разделяя их запятыми
        writer.write( "Name: " + firstName
                + ", " + "Surname: " + lastName
                + ", " + "Username: " + username
                + ", " + "Password: " + password
                + ", " + "Gender: " + gender + "  \n");
        writer.newLine();


        // Закрываем файл
        writer.close();
    }

    public boolean loginUser(String username, String password) {
        File file = new File("C:\\FrequentlyUsedFiles\\separate-work\\works\\java\\AnyData-From-Projects\\UsersData.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                for (String part : parts) {
                    String[] keyValue = part.split(": ");
                    if (keyValue[0].equals("Username") && keyValue[1].equals(username)) {
                        if (parts[3].equals("Password: " + password)) {
                            return true;
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean isUserExist(String username) {
        File file = new File("C:\\FrequentlyUsedFiles\\separate-work\\works\\java\\AnyData-From-Projects\\UsersData.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                for (String part : parts) {
                    String[] keyValue = part.split(": ");
                    if (keyValue[0].equals("Username") && keyValue[1].equals(username)) {
                        scanner.close();
                        return true;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void updatePassword(String username, String newPassword) {
        File file = new File("C:\\FrequentlyUsedFiles\\separate-work\\works\\java\\AnyData-From-Projects\\UsersData.txt");
        File tempFile = new File("C:\\FrequentlyUsedFiles\\separate-work\\works\\java\\AnyData-From-Projects\\tempUsersData.txt");
        try {
            Scanner scanner = new Scanner(file);
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            boolean userFound = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                for (String part : parts) {
                    String[] keyValue = part.split(": ");
                    if (keyValue[0].equals("Username") && keyValue[1].equals(username)) {
                        userFound = true;
                        writer.write("Name: " + parts[0].substring(6)
                                + ", Surname: " + parts[1].substring(10)
                                + ", Username: " + username
                                + ", Password: " + newPassword
                                + ", Gender: " + parts[4].substring(8) + "  \n");
                        writer.newLine();
                    }
                }
                if (!userFound) {
                    writer.write(line + "\n");
                    writer.newLine();
                }
                userFound = false;
            }
            scanner.close();
            writer.close();
            file.delete();
            tempFile.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
