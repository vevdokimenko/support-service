package com.itvdn;

import com.itvdn.entity.*;
import com.itvdn.helper.*;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Auth auth = new Auth();

        String command = "";
        while (!command.equals("exit")) {
            System.out.println(auth.getActiveUserName() + ", welcome!");
            System.out.println("Type command below, or \"exit\" to exit program");
            command = sc.nextLine();

            switch (command) {
                case "exit":
                    break;
                case "fetch_all_users":
                    fetchAllUsers();
                    break;
                default:
                    System.err.println("Unknown command.");
                    break;
            }
        }
    }

    private static void fetchAllUsers() {
        System.out.println("fetch_all_users");
    }
}
