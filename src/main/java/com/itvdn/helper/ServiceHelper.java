package com.itvdn.helper;

import com.itvdn.utils.HibernateUtil;
import com.itvdn.entity.ServiceEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ServiceHelper {
    private final SessionFactory sessionFactory;

    public ServiceHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<ServiceEntity> getServiceList(){
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ServiceEntity> cq = cb.createQuery(ServiceEntity.class);

        Query query = session.createQuery(cq);
        List<ServiceEntity> serviceEntityList = query.getResultList();
        session.close();
        return serviceEntityList;
    }

    public ServiceEntity getServiceById(long id) {
        Session session = sessionFactory.openSession();
        ServiceEntity service = session.get(ServiceEntity.class, id);
        session.close();
        return service;
    }

    public ServiceEntity addService(ServiceEntity service){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(service);
        session.getTransaction().commit();
        session.close();
        return service;
    }
}
