package com.itvdn;

import com.itvdn.entity.IncidentEntity;
import com.itvdn.entity.UserEntity;
import com.itvdn.helper.IncidentHelper;
import com.itvdn.helper.UserHelper;

import java.util.Arrays;
import java.util.List;

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

    private boolean isAllowed(UserEntity user, String command) {
        return permissions.contains(new Permission(command, user.getUserRole().getRoleName()));
    }

    public void fetchAllUsers(UserEntity activeUser, String command) {
        if (isAllowed(activeUser, command)) {
            UserHelper userHelper = new UserHelper();
            for (UserEntity item : userHelper.getUserList()) {
                System.out.println(item);
            }
        } else {
            System.err.println("This command is not allowed for you");
        }
    }

    public void fetchAllIncidents(UserEntity activeUser, String command) {
        if (isAllowed(activeUser, command)) {
            for (IncidentEntity item : new IncidentHelper().getIncidentList()) {
                System.out.println(item);
            }
        } else {
            System.err.println("This command is not allowed for you");
        }
    }

    public void fetchAllActiveIncidents(UserEntity activeUser, String command) {
        if (isAllowed(activeUser, command)) {
            for (IncidentEntity item: new IncidentHelper().getActiveIncidentList()){
                System.out.println(item);
            }
        } else {
            System.err.println("This command is not allowed for you");
        }
    }

    public void fetchUserById(UserEntity activeUser, String command, String id) {
        if (isAllowed(activeUser, command)) {
            System.out.println(new UserHelper().getUserById(Long.parseLong(id)));
        } else {
            System.err.println("This command is not allowed for you");
        }
    }
}
