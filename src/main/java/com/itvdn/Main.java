package com.itvdn;

import com.itvdn.auth.Auth;
import com.itvdn.entity.UserEntity;
import com.itvdn.utils.Command;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Auth auth = new Auth();
        UserEntity activeUser = auth.getActiveUser();
        Command command = new Command();
        String input = "";

        while (!input.equals("exit")) {
            System.out.println("Type command below, or \"exit\" to exit program");
            input = sc.nextLine();
            String id = input.replaceAll("[^\\d+]", "");
            input = input.replaceAll("\\d+", "");

            switch (input) {
                case "exit":
                    break;
                case "fetch_all_users":
                    if (auth.isAllowed(activeUser, input)) command.fetchAllUsers();
                    break;
                case "fetch_all_incidents":
                    if (auth.isAllowed(activeUser, input)) command.fetchAllIncidents();
                    break;
                case "fetch_all_active_incidents":
                    if (auth.isAllowed(activeUser, input)) command.fetchAllActiveIncidents();
                    break;
                case "fetch_user_by_{}":
                    if (auth.isAllowed(activeUser, input)) command.fetchUserById(id);
                    break;
                case "add_user":
                    if (auth.isAllowed(activeUser, input)) command.addUser();
                    break;
                case "update_user_{}":
                    if (auth.isAllowed(activeUser, input)) command.updateUserId(id);
                    break;
                case "delete_user_{}":
                    if (auth.isAllowed(activeUser, input)) command.deleteUserId(id);
                    break;
                case "subscribe_service_{}":
                    if (auth.isAllowed(activeUser, input)) command.subscribeServiceId(activeUser, id);
                    break;
                case "unsubscribe_service_{}":
                    if (auth.isAllowed(activeUser, input)) command.unsubscribeServiceId(activeUser, id);
                    break;
                case "create_incident":
                    if (auth.isAllowed(activeUser, input)) command.createIncident(activeUser);
                    break;
                case "close_incident":
                    if (auth.isAllowed(activeUser, input)) command.closeIncident();
                    break;
                default:
                    System.err.println("Unknown command.");
                    break;
            }
        }
    }
}
