package com.itvdn.helper;

import com.itvdn.entity.Profile;

import javax.persistence.Query;
import java.util.List;

public class ProfileHelper extends SupportHelper {
    public ProfileHelper() {
    }

    public Profile getById(int id) {
        manager.getTransaction().begin();
        Profile profile = manager.find(Profile.class, id);
        manager.getTransaction().commit();
        return profile;
    }

    public void add(Profile profile) {
        manager.getTransaction().begin();
        manager.merge(profile);
        manager.getTransaction().commit();
    }

    public void remove(Profile profile) {
        manager.getTransaction().begin();
        manager.remove(profile);
        manager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Profile> getAll() {
        manager.getTransaction().begin();
        Query query = manager.createQuery("SELECT entity FROM profile entity");
        List<Profile> list = query.getResultList();
        manager.getTransaction().commit();
        return list;
    }
}
