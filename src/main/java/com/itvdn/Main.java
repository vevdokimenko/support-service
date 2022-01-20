package com.itvdn;

import com.itvdn.auth.Auth;
import com.itvdn.utils.Command;

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
                    if (command.isAllowed(auth.getActiveUser(), input)){
                        command.fetchAllUsers();
                    }
                    break;
                case "fetch_all_incidents":
                    if (command.isAllowed(auth.getActiveUser(), input)){
                        command.fetchAllIncidents();
                    }
                    break;
                case "fetch_all_active_incidents":
                    if (command.isAllowed(auth.getActiveUser(), input)){
                        command.fetchAllActiveIncidents();
                    }
                    break;
                case "fetch_user_by_{}":
                    command.fetchUserById(auth.getActiveUser(), input, id);
                    break;
                case "add_user":
                    command.addUser(auth.getActiveUser(), input);
                    break;
                case "update_user_{}":
                    command.updateUserId(auth.getActiveUser(), input, id);
                    break;
                case "delete_user_{}":
                    command.deleteUserId(auth.getActiveUser(), input, id);
                    break;
                case "subscribe_service_{}":
                    command.subscribeServiceId(auth.getActiveUser(), input, id);
                    break;
                case "unsubscribe_service_{}":
                    command.unsubscribeServiceId(auth.getActiveUser(), input, id);
                    break;
                case "create_incident":
                    command.createIncident(auth.getActiveUser(), input);
                    break;
                case "close_incident":
                    command.closeIncident(auth.getActiveUser(), input);
                    break;
                default:
                    System.err.println("Unknown command.");
                    break;
            }
        }
    }
}
