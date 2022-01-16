package com.itvdn;

import com.itvdn.entity.ServiceEntity;
import com.itvdn.helper.ServiceHelper;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ServiceHelper serviceHelper = new ServiceHelper();
        List<ServiceEntity> serviceEntityList = serviceHelper.getServiceList();

        for (ServiceEntity se : serviceEntityList) {
            System.out.println(se);
        }
    }
}
