package com.itvdn;

import com.itvdn.entity.UserEntity;
import com.itvdn.helper.UserHelper;

import java.util.Arrays;
import java.util.List;

public class MyQuery {
    private List<Permission> permissions = Arrays.asList(
            new Permission("fetch_all_users", "SUPER_ADMIN"),
            new Permission("fetch_all_users", "ADMIN"),

            new Permission("fetch_all_incidents", "SUPER_ADMIN"),
            new Permission("fetch_all_incidents", "ADMIN"),

            new Permission("fetch_all_active_incidents", "SUPER_ADMIN"),
            new Permission("fetch_all_active_incidents", "ADMIN"),

            new Permission("fetch_user_by_id", "SUPER_ADMIN"),
            new Permission("fetch_user_by_id", "ADMIN"),

            new Permission("add_user", "SUPER_ADMIN"),
            new Permission("add_user", "ADMIN"),

            new Permission("update_user_id", "SUPER_ADMIN"),
            new Permission("update_user_id", "ADMIN"),

            new Permission("delete_user_id", "SUPER_ADMIN"),
            new Permission("delete_user_id", "ADMIN"),

            new Permission("subscribe_service_id", "SUPER_ADMIN"),
            new Permission("subscribe_service_id", "ADMIN"),
            new Permission("subscribe_service_id", "USER"),

            new Permission("unsubscribe_service_id", "SUPER_ADMIN"),
            new Permission("unsubscribe_service_id", "ADMIN"),
            new Permission("unsubscribe_service_id", "USER"),

            new Permission("create_incident", "SUPER_ADMIN"),
            new Permission("create_incident", "ADMIN"),
            new Permission("create_incident", "USER"),

            new Permission("close_incident", "SUPER_ADMIN"),
            new Permission("close_incident", "ADMIN")
    );

    public void fetchAllUsers(UserEntity user, String command) {
        if (isAllowed(user, command)) {
            UserHelper userHelper = new UserHelper();
            for (UserEntity item : userHelper.getUserList()){
                System.out.println(item);
            }
        } else {
            System.err.println("This command is not allowed for you");
        }
    }

    private boolean isAllowed(UserEntity user, String command){
        return permissions.contains(new Permission(command, user.getUserRole().getRoleName()));
    }
}
