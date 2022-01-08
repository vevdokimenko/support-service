package com.itvdn.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SupportHelper {
    EntityManagerFactory managerFactory;
    EntityManager manager;

    public SupportHelper() {
        managerFactory = Persistence.createEntityManagerFactory("sup-serv");
        manager = managerFactory.createEntityManager();
    }
}
