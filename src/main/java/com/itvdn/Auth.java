package com.itvdn;

import com.itvdn.entity.UserEntity;
import com.itvdn.helper.UserHelper;

import java.util.Scanner;

public class Auth {
    private UserEntity activeUser;

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
                                    activeUser.getUserRole().getRoleName() + "]\n"
                    );
                    isAccessGranted = true;
                }
            }
        }
    }

    public UserEntity getActiveUser() {
        return activeUser;
    }
}
