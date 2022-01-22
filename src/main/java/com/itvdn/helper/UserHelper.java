package com.itvdn.helper;

import com.itvdn.entity.IncidentEntity;
import com.itvdn.entity.ProfileEntity;
import com.itvdn.utils.HibernateUtil;
import com.itvdn.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class UserHelper {
    private final SessionFactory sessionFactory;

    public UserHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<UserEntity> getUserList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Query query = session.createQuery(cq);
        List<UserEntity> userEntityList = query.getResultList();
        session.close();
        return userEntityList;
    }

    public UserEntity getUserById(long id) {
        Session session = sessionFactory.openSession();
        UserEntity user = session.get(UserEntity.class, id); // получение объекта по id
        session.close();
        return user;
    }

    public UserEntity getUserByUserName(String userName) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        cq.select(root).where(cb.equal(root.get("userName"), userName));

        Query query = session.createQuery(cq);
        List<UserEntity> list = query.getResultList();

        session.close();

        if (list.size() == 0) return null;

        UserEntity user = list.get(0);
        return (userName.equals(user.getUserName())) ? user : null;
    }

    public void addUser(UserEntity user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUserById(String id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        UserEntity user = session.get(UserEntity.class, Long.parseLong(id));

        CriteriaDelete<UserEntity> cd = cb.createCriteriaDelete(UserEntity.class);
        Root<UserEntity> userEntityRoot = cd.from(UserEntity.class);
        cd.where(cb.equal(userEntityRoot, user));
        Query query = session.createQuery(cd);
        int deletedValues = query.executeUpdate();
        System.out.println("Deleted users: " + deletedValues);

        session.getTransaction().commit();
        session.close();
    }

    public void updateUser(UserEntity user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        UserEntity userDB = session.get(UserEntity.class, user.getId());
        ProfileEntity profileDB = userDB.getProfile();

        profileDB.setFirstName(user.getProfile().getFirstName());
        profileDB.setLastName(user.getProfile().getLastName());
        profileDB.setEmail(user.getProfile().getEmail());
        profileDB.setPhoneNumber(user.getProfile().getPhoneNumber());
        profileDB.setPostalCode(user.getProfile().getPostalCode());
        userDB.setUserName(user.getUserName());
        userDB.setPassword(user.getPassword());
        userDB.setUserRole(user.getUserRole());

        session.save(profileDB);
        session.save(userDB);

        session.getTransaction().commit();
        session.close();
    }
}
