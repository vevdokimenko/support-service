package com.itvdn.helper;

import com.itvdn.utils.HibernateUtil;
import com.itvdn.entity.IncidentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

public class IncidentHelper {
    private final SessionFactory sessionFactory;

    public IncidentHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<IncidentEntity> getIncidentList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();

        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery<IncidentEntity> cq = cb.createQuery(IncidentEntity.class);
        Root<IncidentEntity> root = cq.from(IncidentEntity.class);// первостепенный, корневой entity (в sql запросе - from)

        //этап выполнения запроса
        Query query = session.createQuery(cq);
        List<IncidentEntity> incidentEntityList = query.getResultList();
        session.close();
        return incidentEntityList;
    }

    public List<IncidentEntity> getActiveIncidentList(){
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<IncidentEntity> cq = cb.createQuery(IncidentEntity.class);
        Root<IncidentEntity> root = cq.from(IncidentEntity.class);

        Selection[] selections = {
                root.get("serviceName"),
                root.get("isActive"),
                root.get("problemDescription"),
                root.get("user")
        };

        cq.select(cb.construct(IncidentEntity.class, selections))
                .where(cb.equal(root.get("isActive"), 1));
        Query query = session.createQuery(cq);
        List<IncidentEntity> list = query.getResultList();

        session.close();
        return list;
    }

    public IncidentEntity getIncidentById(long id) {
        Session session = sessionFactory.openSession();
        IncidentEntity incident = session.get(IncidentEntity.class, id); // получение объекта по id
        session.close();
        return incident;
    }

    public IncidentEntity addIncident(IncidentEntity incident){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(incident); // сгенерит ID и вставит в объект
        session.getTransaction().commit();
        session.close();
        return incident;
    }
}
