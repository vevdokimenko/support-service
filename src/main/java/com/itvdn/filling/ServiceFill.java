package com.itvdn.filling;

import com.itvdn.entity.Profile;
import com.itvdn.entity.Service;
import com.itvdn.helper.ServiceHelper;

import java.util.List;

public class ServiceFill {
    public static void main(String[] args) {
        ServiceHelper helper = new ServiceHelper();

        Service row1 = new Service(
                "Online payments",
                true,
                1.99
        );

        Service row2 = new Service(
                "Nova Poshta delivery",
                true,
                2.99
        );

        Service row3 = new Service(
                "Detailed analytics",
                true,
                0.99
        );

        helper.add(row1);
        helper.add(row2);
        helper.add(row3);

        List<Service> list = helper.getAll();

        for (Service item : list) {
            System.out.println(item);
        }
    }
}
