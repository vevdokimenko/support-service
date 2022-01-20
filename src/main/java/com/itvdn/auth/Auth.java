package com.itvdn.auth;

import com.itvdn.entity.PermissionEntity;
import com.itvdn.entity.ServiceEntity;
import com.itvdn.entity.UserEntity;
import com.itvdn.helper.PermissionHelper;
import com.itvdn.helper.UserHelper;

import java.util.List;
import java.util.Scanner;

public class Auth {
    private UserEntity activeUser;

    private final List<PermissionEntity> permissions = new PermissionHelper().getPermissionsList();

    public Auth() {
        boolean isAccessGranted = false;

        while (!isAccessGranted) {
            System.out.println("Enter username:");
            String userName = new Scanner(System.in).nextLine();
            System.out.println("Enter password:");
            String password = new Scanner(System.in).nextLine();

            UserHelper userHelper = new UserHelper();
            activeUser = userHelper.getUserByUserName(userName);
            if (activeUser == null) {
                System.err.println("User not found");
            } else {
                if (!password.equals(activeUser.getPassword())) {
                    System.err.println("Wrong password");
                } else {
                    System.out.println(
                            "Welcome, " +
                                    activeUser.getUserName() + " [" +
                                    activeUser.getUserRole().getRoleName() + "]"
                    );
                    System.out.print("Your services:");
                    for (ServiceEntity item : activeUser.getServiceEntityList()) {
                        System.out.print(" [" + item.getId() + " " + item.getServiceName() + "]");
                    }
                    System.out.println("\n");
                    isAccessGranted = true;
                }
            }
        }
    }

    public UserEntity getActiveUser() {
        return activeUser;
    }

    public boolean isAllowed(UserEntity user, String command) {
        boolean result = permissions.contains(
                new PermissionEntity(command, user.getUserRole().getRoleName())
        );
        if (!result) System.err.println("This command is not allowed for you");
        return result;
    }
}
