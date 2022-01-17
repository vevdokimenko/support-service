package com.itvdn;

import com.itvdn.entity.IncidentEntity;
import com.itvdn.helper.*;

public class Main {
    public static void main(String[] args) {
        ProfileHelper profileHelper = new ProfileHelper();
        ServiceHelper serviceHelper = new ServiceHelper();
        UserHelper userHelper = new UserHelper();
        UserRoleHelper userRoleHelper = new UserRoleHelper();
        IncidentHelper incidentHelper = new IncidentHelper();

        for (IncidentEntity item : userHelper.getUserById(5).getIncidentEntityList()){
            System.out.println(item);
        }

    }
}
