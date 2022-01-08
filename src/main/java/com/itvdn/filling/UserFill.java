package com.itvdn.filling;

import com.itvdn.entity.User;
import com.itvdn.helper.ProfileHelper;
import com.itvdn.helper.UserHelper;
import com.itvdn.helper.UserRoleHelper;

import java.util.List;

public class UserFill {
    public static void main(String[] args) {
        UserHelper helper = new UserHelper();
        ProfileHelper ph = new ProfileHelper();
        UserRoleHelper urh = new UserRoleHelper();

        User row1 = new User(
                "ivanov",
                "123456",
                urh.getById(2),
                ph.getById(1)
        );

        User row2 = new User(
                "petrov",
                "123456",
                urh.getById(1),
                ph.getById(2)
        );

        User row3 = new User(
                "vasilyev",
                "123456",
                urh.getById(3),
                ph.getById(3)
        );

        helper.add(row1);
        helper.add(row2);
        helper.add(row3);

        List<User> list = helper.getAll();

        for (User item : list) {
            System.out.println(item);
        }
    }
}
