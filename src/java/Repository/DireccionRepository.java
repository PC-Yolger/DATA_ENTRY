/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entity.app.TblServicioDireccion;
import Model.app.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
public class DireccionRepository {

    public List<TblServicioDireccion> getAll() {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        List<TblServicioDireccion> lst = new ArrayList<TblServicioDireccion>();
        try {
            s.beginTransaction();
            lst = s.createCriteria(TblServicioDireccion.class).list();
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public TblServicioDireccion search(String id) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        TblServicioDireccion obj = null;
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(TblServicioDireccion.class);
            criteria.add(Restrictions.eq("tesIdDireccionBi", Integer.parseInt(id)));
            List<TblServicioDireccion> lst = criteria.list();
            obj = (TblServicioDireccion) (lst.size() > 0 ? lst.get(0) : null);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void Create(TblServicioDireccion factura) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.save(factura);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public void Remove(TblServicioDireccion factura) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.delete(factura);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public void Edit(TblServicioDireccion factura) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
            s.update(factura);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }
}
