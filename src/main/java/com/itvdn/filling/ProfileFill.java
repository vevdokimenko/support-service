package com.itvdn.filling;

import com.itvdn.entity.Profile;
import com.itvdn.helper.ProfileHelper;
import java.util.List;

public class ProfileFill {
    public static void main(String[] args) {
        ProfileHelper helper = new ProfileHelper();

        Profile p1 = new Profile(
                "Ivan",
                "Ivanov",
                "ivanov@email.com",
                "+380501234567",
                "71100"
        );

        Profile p2 = new Profile(
                "Petro",
                "Petrov",
                "petrov@email.com",
                "+380661234567",
                "71100"
        );

        Profile p3 = new Profile(
                "Vasya",
                "Vasilyev",
                "vasilyev@email.com",
                "+380951234567",
                "71100"
        );

        helper.add(p1);
        helper.add(p2);
        helper.add(p3);

        List<Profile> list = helper.getAll();

        for (Profile item : list) {
            System.out.println(item);
        }
    }
}
