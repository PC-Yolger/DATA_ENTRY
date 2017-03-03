/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entity.app.TblServicioServicio;import Model.app.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ServicioRepository {

    public List<TblServicioServicio> getAll() {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        List<TblServicioServicio> lst = new ArrayList<TblServicioServicio>();
        try {
            s.beginTransaction();
            lst = s.createCriteria(TblServicioServicio.class).list();
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    
    public TblServicioServicio search(String id) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        TblServicioServicio obj = null;
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(TblServicioServicio.class);
            criteria.add(Restrictions.eq("tesIdServicioBi", Integer.parseInt(id)));
            List<TblServicioServicio> lst = criteria.list();
            obj = (TblServicioServicio) (lst.size() > 0 ? lst.get(0) : null);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public void Create(TblServicioServicio factura) {
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

    public void Remove(TblServicioServicio factura) {
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

    public void Edit(TblServicioServicio factura) {
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
