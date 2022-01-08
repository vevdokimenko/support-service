package com.itvdn.helper;

import com.itvdn.entity.Service;

import javax.persistence.Query;
import java.util.List;

public class ServiceHelper extends SupportHelper {
    public ServiceHelper() {
    }

    public Service getById(int id) {
        manager.getTransaction().begin();
        Service service = manager.find(Service.class, id);
        manager.getTransaction().commit();
        return service;
    }

    public void add(Service service) {
        manager.getTransaction().begin();
        manager.merge(service);
        manager.getTransaction().commit();
    }

    public void remove(Service service) {
        manager.getTransaction().begin();
        manager.remove(service);
        manager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Service> getAll() {
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT entity FROM service entity");
        List<Service> list = query.getResultList();
        manager.getTransaction().commit();
        return list;
    }
}
