/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.dao;

import ec.fe.tickets.common.DAO;
import ec.fe.tickets.model.TtFarmacia;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Jose
 */
public class FarmaciaDao {

    public List<TtFarmacia> buscarTodasFarmacias() {

        List<TtFarmacia> listaFarmacias = null;
        String sql = "FROM TtFarmacia";
        DAO dao = new DAO() {
        };
        try {
            listaFarmacias = dao.currentSession.createQuery(sql).list();
        } catch (HibernateException e) {
            dao.currentSession.beginTransaction().rollback();
        }
        return listaFarmacias;
    }

    public List<TtFarmacia> seleccionarFarmacia() {

        List<TtFarmacia> listaFarmacias = null;
        String sql = "FROM TtFarmacia";
        DAO dao = new DAO() {
        };
        try {
            listaFarmacias = dao.currentSession.createQuery(sql).list();
        } catch (HibernateException e) {
            dao.currentSession.beginTransaction().rollback();
        }
        return listaFarmacias;
    }

    public boolean comprobarPKDuplicada(String _idFarmacia) {
        DAO dao = new DAO() {
        };

        boolean pkDuplicada = false;
        String idFarmacia;
        try {
            String sql = "select f.farIdFarmacia FROM TtFarmacia as f where f.farIdFarmacia = '" + _idFarmacia + "'";
            idFarmacia = (String) dao.currentSession.createQuery(sql).uniqueResult();
            if (idFarmacia != null) {
                pkDuplicada = _idFarmacia.toLowerCase().equals(idFarmacia.toLowerCase());
            } else {
                pkDuplicada = false;
            }
            // pkDuplicada = _idFarmacia.matches(idFarmacia);
        } catch (HibernateException e) {
            dao.currentSession.beginTransaction().rollback();
        }
        return pkDuplicada;

    }

    public boolean registrarFarmacia(TtFarmacia farmacia) {
        boolean flag;
        DAO dao = new DAO() {
        };
        try {
            dao.currentSession.save(farmacia);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

    public boolean actualizarFarmacia(TtFarmacia farmacia) {
        DAO dao = new DAO() {
        };
        boolean flag;
        try {
            dao.currentSession.update(farmacia);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }

        return flag;
    }

//    public boolean actualizarEstadoTTFarm(String _idFarmacia, Boolean _estadoTT) {
//        boolean flag;
//        try {
//            DAO dao = new DAO() {
//            };
//            Farmacia f = (Farmacia) dao.currentSession.load(Farmacia.class, _idFarmacia);
//            _estadoTT = false;
//            f.setIdFarmacia(_idFarmacia);
//            f.setEstado(_estadoTT);
//            dao.currentSession.update(f);
//            flag = true;
//        } catch (HibernateException e) {
//            DAO dao = new DAO() {
//            };
//            flag = false;
//            dao.currentSession.beginTransaction().rollback();
//        }
//        return flag;
//    }
    public boolean eliminarFarmacia(String idFarmacia) {
        boolean flag;
        DAO dao = new DAO() {
        };
        try {
            TtFarmacia farmacia = (TtFarmacia) dao.getCurrentSession().load(TtFarmacia.class, idFarmacia);
            dao.currentSession.delete(farmacia);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

}
