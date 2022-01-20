package com.itvdn.helper;

import com.itvdn.entity.PermissionEntity;
import com.itvdn.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;

public class PermissionHelper {
    private final SessionFactory sessionFactory;

    public PermissionHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<PermissionEntity> getPermissionsList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PermissionEntity> cq = cb.createQuery(PermissionEntity.class);
        Root<PermissionEntity> root = cq.from(PermissionEntity.class);

        Selection[] selections = {root.get("command"), root.get("role")};
        cq.select(cb.construct(PermissionEntity.class, selections));

        Query query = session.createQuery(cq);
        List<PermissionEntity> permissions = query.getResultList();
        session.close();
        return permissions;
    }

    public void addPermission(PermissionEntity permissions) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(permissions);
        session.getTransaction().commit();
        session.close();
    }
}
