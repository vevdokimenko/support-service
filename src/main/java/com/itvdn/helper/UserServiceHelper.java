package com.itvdn.helper;

import com.itvdn.entity.ServiceEntity;
import com.itvdn.entity.UserEntity;
import com.itvdn.entity.UserServiceEntity;
import com.itvdn.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserServiceHelper {
    private final SessionFactory sessionFactory;

    public UserServiceHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<UserServiceEntity> getUserServiceList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserServiceEntity> cq = cb.createQuery(UserServiceEntity.class);

        Query query = session.createQuery(cq);
        List<UserServiceEntity> userServiceEntityList = query.getResultList();
        session.close();
        return userServiceEntityList;
    }

    public void addUserService(UserServiceEntity userServiceEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(userServiceEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUserService(UserEntity activeUser, ServiceEntity service){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserServiceEntity userService = session.get(UserServiceEntity.class, new UserServiceEntity(service, activeUser));

        session.delete(userService);

        session.getTransaction().commit();
        session.close();
    }
}
