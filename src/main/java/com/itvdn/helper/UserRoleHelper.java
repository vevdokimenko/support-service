package com.itvdn.helper;

import com.itvdn.entity.UserRole;

import javax.persistence.Query;
import java.util.List;

public class UserRoleHelper extends SupportHelper {
    public UserRoleHelper() {
    }

    public UserRole getById(int id) {
        manager.getTransaction().begin();
        UserRole userRole = manager.find(UserRole.class, id);
        manager.getTransaction().commit();
        return userRole;
    }

    public void add(UserRole userRole) {
        manager.getTransaction().begin();
        manager.merge(userRole);
        manager.getTransaction().commit();
    }

    public void remove(UserRole userRole) {
        manager.getTransaction().begin();
        manager.remove(userRole);
        manager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<UserRole> getAll() {
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT entity FROM user_role entity");
        List<UserRole> list = query.getResultList();
        manager.getTransaction().commit();
        return list;
    }
}
