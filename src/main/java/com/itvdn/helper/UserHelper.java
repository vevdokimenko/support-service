package com.itvdn.helper;

import com.itvdn.utils.HibernateUtil;
import com.itvdn.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

public class UserHelper {
    private final SessionFactory sessionFactory;

    public UserHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<UserEntity> getUserList() {
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();

        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);// первостепенный, корневой entity (в sql запросе - from)

        //этап выполнения запроса
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

    public UserEntity addUser(UserEntity user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user); // сгенерит ID и вставит в объект
        session.getTransaction().commit();
        session.close();
        return user;
    }
}
