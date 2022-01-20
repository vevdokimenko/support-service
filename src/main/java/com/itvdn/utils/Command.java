package com.itvdn.utils;

import com.itvdn.auth.Permission;
import com.itvdn.entity.IncidentEntity;
import com.itvdn.entity.UserEntity;
import com.itvdn.helper.IncidentHelper;
import com.itvdn.helper.UserHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Command {
    private final List<Permission> permissions = Arrays.asList(
            new Permission("fetch_all_users", "SUPER_ADMIN"),
            new Permission("fetch_all_users", "ADMIN"),

            new Permission("fetch_all_incidents", "SUPER_ADMIN"),
            new Permission("fetch_all_incidents", "ADMIN"),

            new Permission("fetch_all_active_incidents", "SUPER_ADMIN"),
            new Permission("fetch_all_active_incidents", "ADMIN"),

            new Permission("fetch_user_by_{}", "SUPER_ADMIN"),
            new Permission("fetch_user_by_{}", "ADMIN"),

            new Permission("add_user", "SUPER_ADMIN"),
            new Permission("add_user", "ADMIN"),

            new Permission("update_user_{}", "SUPER_ADMIN"),
            new Permission("update_user_{}", "ADMIN"),

            new Permission("delete_user_{}", "SUPER_ADMIN"),
            new Permission("delete_user_{}", "ADMIN"),

            new Permission("subscribe_service_{}", "SUPER_ADMIN"),
            new Permission("subscribe_service_{}", "ADMIN"),
            new Permission("subscribe_service_{}", "USER"),

            new Permission("unsubscribe_service_{}", "SUPER_ADMIN"),
            new Permission("unsubscribe_service_{}", "ADMIN"),
            new Permission("unsubscribe_service_{}", "USER"),

            new Permission("create_incident", "SUPER_ADMIN"),
            new Permission("create_incident", "ADMIN"),
            new Permission("create_incident", "USER"),

            new Permission("close_incident", "SUPER_ADMIN"),
            new Permission("close_incident", "ADMIN")
    );

    public boolean isAllowed(UserEntity user, String command) {
        boolean result = permissions.contains(new Permission(command, user.getUserRole().getRoleName()));
        if (!result) System.err.println("This command is not allowed for you");
        return result;
    }

    public void fetchAllUsers() {
        UserHelper userHelper = new UserHelper();
        for (UserEntity item : userHelper.getUserList()) {
            System.out.println(item);
        }
    }

    public void fetchAllIncidents() {
        for (IncidentEntity item : new IncidentHelper().getIncidentList()) {
            System.out.println(item);
        }
    }

    public void fetchAllActiveIncidents() {
        for (IncidentEntity item : new IncidentHelper().getActiveIncidentList()) {
            System.out.println(item);
        }
    }

    public void fetchUserById(UserEntity activeUser, String command, String id) {
        if (isAllowed(activeUser, command)) {
            System.out.println(new UserHelper().getUserById(Long.parseLong(id)));
        } else {
            System.err.println("This command is not allowed for you");
        }
    }

    public void addUser(UserEntity activeUser, String command) {
        //TODO
    }

    public void updateUserId(UserEntity activeUser, String command, String id) {
        //TODO
    }

    public void deleteUserId(UserEntity activeUser, String command, String id) {
        //TODO
    }

    public void subscribeServiceId(UserEntity activeUser, String command, String id) {
        //TODO
    }

    public void unsubscribeServiceId(UserEntity activeUser, String command, String id) {
        //TODO
    }

    public void createIncident(UserEntity activeUser, String command) {
        if (isAllowed(activeUser, command)) {
            IncidentHelper helper = new IncidentHelper();

            System.out.println("Type service name:");
            String serviceName = new Scanner(System.in).nextLine();
            System.out.println("Describe your problem with " + serviceName + ":");
            String problemDescription = new Scanner(System.in).nextLine();

            IncidentEntity incident = new IncidentEntity(serviceName, true, problemDescription, activeUser);
            helper.addIncident(incident);

            System.out.println("Your ticket was added: " + incident);
        } else {
            System.err.println("This command is not allowed for you");
        }
    }

    public void closeIncident(UserEntity activeUser, String command) {
        if (isAllowed(activeUser, command)) {
            IncidentHelper helper = new IncidentHelper();

            System.out.println("Type id of incident you want to close:");
            for (IncidentEntity item : helper.getActiveIncidentList()) {
                System.out.println(item.getId() + " " + item.getServiceName() + " " + item.getProblemDescription());
            }
            long incidentId = Long.parseLong(new Scanner(System.in).nextLine());

            helper.closeIncident(incidentId);
            System.out.println("Incident " + incidentId + " closed.");
        } else {
            System.err.println("This command is not allowed for you");
        }
    }
}
