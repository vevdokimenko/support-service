package com.itvdn;

import com.itvdn.entity.*;
import com.itvdn.helper.*;

public class Main {
    public static void main(String[] args) {
        ProfileHelper profileHelper = new ProfileHelper();
        ServiceHelper serviceHelper = new ServiceHelper();
        UserHelper userHelper = new UserHelper();
        UserRoleHelper userRoleHelper = new UserRoleHelper();
        IncidentHelper incidentHelper = new IncidentHelper();

        for (UserEntity item : serviceHelper.getServiceById(2).getUserEntities()){
            System.out.println(item);
        }
    }
}
