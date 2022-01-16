package com.itvdn.helper;

import com.itvdn.HibernateUtil;
import com.itvdn.entity.ServiceEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ServiceHelper {
    private final SessionFactory sessionFactory;

    public ServiceHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<ServiceEntity> getServiceList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();

        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery<ServiceEntity> cq = cb.createQuery(ServiceEntity.class);
        Root<ServiceEntity> root = cq.from(ServiceEntity.class);// первостепенный, корневой entity (в sql запросе - from)

        //этап выполнения запроса
        Query query = session.createQuery(cq);
        List<ServiceEntity> serviceEntityList = query.getResultList();
        session.close();
        return serviceEntityList;
    }

    public ServiceEntity getServiceById(long id) {
        Session session = sessionFactory.openSession();
        ServiceEntity service = session.get(ServiceEntity.class, id); // получение объекта по id
        session.close();
        return service;
    }

    public ServiceEntity addService(ServiceEntity service){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(service); // сгенерит ID и вставит в объект
        session.getTransaction().commit();
        session.close();
        return service;
    }
}
