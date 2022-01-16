package com.itvdn.helper;

import com.itvdn.HibernateUtil;
import com.itvdn.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserHelper {
    private final SessionFactory sessionFactory;

    public UserHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<UserEntity> getUserList(){
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

    public UserEntity addUser(UserEntity user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user); // сгенерит ID и вставит в объект
        session.getTransaction().commit();
        session.close();
        return user;
    }
}
