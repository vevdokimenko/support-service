package com.itvdn.helper;

import com.itvdn.utils.HibernateUtil;
import com.itvdn.entity.ProfileEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProfileHelper {
    private final SessionFactory sessionFactory;

    public ProfileHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<ProfileEntity> getProfileList(){
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ProfileEntity> cq = cb.createQuery(ProfileEntity.class);

        Query query = session.createQuery(cq);
        List<ProfileEntity> profileEntityList = query.getResultList();
        session.close();
        return profileEntityList;
    }

    public ProfileEntity getProfileById(long id) {
        Session session = sessionFactory.openSession();
        ProfileEntity profile = session.get(ProfileEntity.class, id);
        session.close();
        return profile;
    }

    public long addProfile(ProfileEntity profile){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        long id = (long) session.save(profile);
        session.getTransaction().commit();
        session.close();
        return id;
    }
}
