package com.itvdn;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Auth auth = new Auth();
        Command command = new Command();
        String input = "";

        while (!input.equals("exit")) {
            System.out.println("Type command below, or \"exit\" to exit program");
            input = sc.nextLine();
            String id = input.replaceAll("[^\\d+]","");
            input = input.replaceAll("\\d+", "");

            switch (input) {
                case "exit":
                    break;
                case "fetch_all_users":
                    System.out.println(id);
                    command.fetchAllUsers(auth.getActiveUser(), input);
                    break;
                case "fetch_all_incidents":
                    command.fetchAllIncidents(auth.getActiveUser(), input);
                    break;
                case "fetch_all_active_incidents":
                    command.fetchAllActiveIncidents(auth.getActiveUser(), input);
                    break;
                case "fetch_user_by_{}":
                    command.fetchUserById(auth.getActiveUser(), input, id);
                    break;
                default:
                    System.err.println("Unknown command.");
                    break;
            }
        }
    }
}
