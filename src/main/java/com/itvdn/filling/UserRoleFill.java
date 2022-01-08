package com.itvdn.filling;

import com.itvdn.entity.UserRole;
import com.itvdn.helper.UserRoleHelper;

import java.util.List;

public class UserRoleFill {
    public static void main(String[] args) {
        UserRoleHelper helper = new UserRoleHelper();

        UserRole user = new UserRole("USER","Role for user.");
        UserRole admin = new UserRole("ADMIN", "Role for administrator.");
        UserRole superAdmin = new UserRole("SUPER_ADMIN", "Role for super administrator.");

        helper.add(user);
        helper.add(admin);
        helper.add(superAdmin);

        List<UserRole> list = helper.getAll();

        for (UserRole item : list) {
            System.out.println(item);
        }
    }
}
