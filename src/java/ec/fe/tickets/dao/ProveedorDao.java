/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.dao;

import ec.fe.tickets.common.DAO;
import ec.fe.tickets.model.TtProveedor;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Jose
 */
public class ProveedorDao {

    public List<TtProveedor> buscarTodosProveedores() {

        List<TtProveedor> listaProveedores = null;
        String sql = "FROM TtProveedor";
        try {
            DAO dao = new DAO() {
            };
            listaProveedores = dao.currentSession.createQuery(sql).list();
        } catch (HibernateException e) {
            DAO dao = new DAO() {
            };
            dao.currentSession.beginTransaction().rollback();
        }
        return listaProveedores;
    }

    public boolean comprobarProveedorDup(String _idProveedor) {
        DAO dao = new DAO() {
        };

        boolean pkDuplicada = false;
        String idProveedor;
        try {
            String sql = "select p.proIdProveedor FROM TtProvedor as p where p.proIdProveedor = '" + _idProveedor + "'";
            idProveedor = (String) dao.currentSession.createQuery(sql).uniqueResult();
            pkDuplicada = _idProveedor.toLowerCase().equals(idProveedor.toLowerCase());
            // pkDuplicada = _idFarmacia.matches(idFarmacia);
        } catch (HibernateException e) {
            dao.currentSession.beginTransaction().rollback();
        }
        return pkDuplicada;

    }

    public List<TtProveedor> seleccionarProveedores() {

        List<TtProveedor> listaProveedores = null;
        String sql = "FROM TtProveedor";
        try {
            DAO dao = new DAO() {
            };
            listaProveedores = dao.currentSession.createQuery(sql).list();
        } catch (HibernateException e) {
            DAO dao = new DAO() {
            };
            dao.currentSession.beginTransaction().rollback();
        }
        return listaProveedores;
    }

    public boolean registrarProveedor(TtProveedor proveedor) {
        boolean flag;
        DAO dao = new DAO() {
        };
        try {
            dao.currentSession.save(proveedor);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

    public boolean actualizarProveedor(TtProveedor proveedor) {
        boolean flag;
        try {
            DAO dao = new DAO() {
            };
            dao.currentSession.update(proveedor);
            flag = true;
        } catch (HibernateException e) {
            DAO dao = new DAO() {
            };
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

    public boolean eliminarProveedor(String idProveedor) {

        DAO dao = new DAO() {
        };
        boolean flag;
        try {

            TtProveedor proveedor = (TtProveedor) dao.getCurrentSession().load(TtProveedor.class, idProveedor);
            dao.currentSession.delete(proveedor);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

}
