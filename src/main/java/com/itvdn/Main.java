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
            String id = command.replaceAll("[^\\d+]","");
            command = command.replaceAll("\\d+", "");

            switch (command) {
                case "exit":
                    break;
                case "fetch_all_users":
                    System.out.println(id);
                    query.fetchAllUsers(auth.getActiveUser(), command);
                    break;
                case "fetch_all_incidents":
                    query.fetchAllIncidents(auth.getActiveUser(), command);
                    break;
                case "fetch_all_active_incidents":
                    query.fetchAllActiveIncidents(auth.getActiveUser(), command);
                    break;
                case "fetch_user_by_{}":
                    query.fetchUserById(auth.getActiveUser(), command, id);
                    break;
                default:
                    System.err.println("Unknown command.");
                    break;
            }
        }
    }
}
