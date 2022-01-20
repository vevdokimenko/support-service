package com.itvdn.helper;

import com.itvdn.utils.HibernateUtil;
import com.itvdn.entity.UserRoleEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserRoleHelper {
    private final SessionFactory sessionFactory;

    public UserRoleHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<UserRoleEntity> getUserRoleList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();

        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery<UserRoleEntity> cq = cb.createQuery(UserRoleEntity.class);

        //этап выполнения запроса
        Query query = session.createQuery(cq);
        List<UserRoleEntity> userRoleEntityList = query.getResultList();
        session.close();
        return userRoleEntityList;
    }

    public UserRoleEntity getUserRoleById(long id) {
        Session session = sessionFactory.openSession();
        UserRoleEntity userRole = session.get(UserRoleEntity.class, id); // получение объекта по id
        session.close();
        return userRole;
    }

    public UserRoleEntity addUserRole(UserRoleEntity userRole){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(userRole); // сгенерит ID и вставит в объект
        session.getTransaction().commit();
        session.close();
        return userRole;
    }
}
