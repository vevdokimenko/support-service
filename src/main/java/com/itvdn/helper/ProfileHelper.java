package com.itvdn.helper;

import com.itvdn.utils.HibernateUtil;
import com.itvdn.entity.ProfileEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProfileHelper {
    private final SessionFactory sessionFactory;

    public ProfileHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<ProfileEntity> getProfileList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();

        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery<ProfileEntity> cq = cb.createQuery(ProfileEntity.class);
        Root<ProfileEntity> root = cq.from(ProfileEntity.class);// первостепенный, корневой entity (в sql запросе - from)

        //этап выполнения запроса
        Query query = session.createQuery(cq);
        List<ProfileEntity> profileEntityList = query.getResultList();
        session.close();
        return profileEntityList;
    }

    public ProfileEntity getProfileById(long id) {
        Session session = sessionFactory.openSession();
        ProfileEntity profile = session.get(ProfileEntity.class, id); // получение объекта по id
        session.close();
        return profile;
    }

    public ProfileEntity addProfile(ProfileEntity profile){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(profile); // сгенерит ID и вставит в объект
        session.getTransaction().commit();
        session.close();
        return profile;
    }
}
