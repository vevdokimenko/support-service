package com.itvdn;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Auth auth = new Auth();
        MyQuery query = new MyQuery();

        String command = "";
        while (!command.equals("exit")) {
            System.out.println("Type command below, or \"exit\" to exit program");
            command = sc.nextLine();

            switch (command) {
                case "exit":
                    break;
                case "fetch_all_users":
                    query.fetchAllUsers(auth.getActiveUser(), command);
                    break;
                default:
                    System.err.println("Unknown command.");
                    break;
            }
        }
    }
}
