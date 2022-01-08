package com.itvdn.helper;

import com.itvdn.entity.User;

import javax.persistence.Query;
import java.util.List;

public class UserHelper extends SupportHelper {
    public UserHelper() {
    }

    public User getById(int id) {
        manager.getTransaction().begin();
        User user = manager.find(User.class, id);
        manager.getTransaction().commit();
        return user;
    }

    public void add(User user) {
        manager.getTransaction().begin();
        manager.merge(user);
        manager.getTransaction().commit();
    }

    public void remove(User user) {
        manager.getTransaction().begin();
        manager.remove(user);
        manager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT entity FROM user entity");
        List<User> list = query.getResultList();
        manager.getTransaction().commit();
        return list;
    }
}
