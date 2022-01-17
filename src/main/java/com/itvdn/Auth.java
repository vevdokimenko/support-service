package com.itvdn;

import com.itvdn.entity.UserEntity;
import com.itvdn.entity.UserRoleEntity;

import java.util.Scanner;

public class Auth {
    private UserEntity activeUser;
    private UserRoleEntity activeUserRole;

    public Auth() {
        System.out.println("Enter username:");
        String userName = new Scanner(System.in).nextLine();
        System.out.println("Enter password:");
        String password = new Scanner(System.in).nextLine();
    }

    public String getActiveUserName(){
        return activeUser.getUserName();
    }
}
