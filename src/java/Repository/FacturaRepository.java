/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import Entity.app.TblServicioFactura;
import Model.app.HibernateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
public class FacturaRepository {

    public List<TblServicioFactura> getAll() {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        List<TblServicioFactura> lst = new ArrayList<TblServicioFactura>();
        try {
            s.beginTransaction();
            lst = s.createCriteria(TblServicioFactura.class).list();
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(lst, new Comparator<TblServicioFactura>() {
            @Override
            public int compare(TblServicioFactura o1, TblServicioFactura o2) {
                return o1.getTesIdFacturaBi() - o2.getTesIdFacturaBi();
            }
        });
        return lst;
    }

    public TblServicioFactura Search(String id) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        TblServicioFactura obj = null;
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(TblServicioFactura.class);
            criteria.add(Restrictions.eq("tesIdFacturaBi", Integer.parseInt(id)));
            List<TblServicioFactura> lst = criteria.list();
            obj = (TblServicioFactura) (lst.size() > 0 ? lst.get(0) : null);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public List<TblServicioFactura> Search(String sucursal, String servicio) {
        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        List<TblServicioFactura> lst = new ArrayList<TblServicioFactura>();
        try {
            s.beginTransaction();
            Criteria criteria = s.createCriteria(TblServicioFactura.class);
            if (!"".equals(sucursal)) {
                criteria.add(Restrictions.eq("testIdDireccionBi", Integer.parseInt(sucursal)));
            }
            if (!"".equals(servicio)) {
                criteria.add(Restrictions.eq("tesCodigoSintesisBi", Integer.parseInt(servicio)));
            }
            lst = criteria.list();
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public void Create(TblServicioFactura factura) {
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

    public void Remove(TblServicioFactura factura) {
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

    public void Edit(TblServicioFactura factura) {
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
