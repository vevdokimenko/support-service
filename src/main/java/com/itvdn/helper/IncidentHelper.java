package com.itvdn.helper;

import com.itvdn.utils.HibernateUtil;
import com.itvdn.entity.IncidentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class IncidentHelper {
    private final SessionFactory sessionFactory;

    public IncidentHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<IncidentEntity> getIncidentList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IncidentEntity> cq = cb.createQuery(IncidentEntity.class);
        Root<IncidentEntity> root = cq.from(IncidentEntity.class);

        Query query = session.createQuery(cq);
        List<IncidentEntity> incidentEntityList = query.getResultList();
        session.close();
        return incidentEntityList;
    }

    public List<IncidentEntity> getActiveIncidentList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IncidentEntity> cq = cb.createQuery(IncidentEntity.class);
        Root<IncidentEntity> root = cq.from(IncidentEntity.class);

        cq.select(root).where(cb.equal(root.get("isActive"), 1));
        Query query = session.createQuery(cq);
        List<IncidentEntity> list = query.getResultList();

        session.close();
        return list;
    }

    public IncidentEntity getIncidentById(long id) {
        Session session = sessionFactory.openSession();
        IncidentEntity incident = session.get(IncidentEntity.class, id);
        session.close();
        return incident;
    }

    public void addIncident(IncidentEntity incident) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(incident);
        session.getTransaction().commit();
        session.close();
    }

    public void closeIncident(long id) {
        Session session = sessionFactory.openSession();
        try (session) {
            session.beginTransaction();
            IncidentEntity incident = session.get(IncidentEntity.class, id);
            incident.setIsActive(false);
            session.save(incident);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("No such incident.");
        }
    }
}
