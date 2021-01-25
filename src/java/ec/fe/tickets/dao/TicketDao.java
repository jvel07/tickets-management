/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.dao;

import ec.fe.tickets.common.DAO;
import ec.fe.tickets.model.TtFarmacia;
import ec.fe.tickets.model.TtTicket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Jose
 */
public class TicketDao {

    public List<TtTicket> buscarTodostickets() {
        DAO dao = new DAO() {
        };
        List<TtTicket> listaTickets = null;
        String sql = "FROM TtTicket";
        try {
            listaTickets = dao.currentSession.createQuery(sql).list();
        } catch (HibernateException e) {
            dao.currentSession.beginTransaction().rollback();
        }
        return listaTickets;
    }



    public boolean registrarTicket(TtTicket ticket) {
        DAO dao = new DAO() {
        };
        boolean flag;
        try {
            dao.currentSession.save(ticket);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

    public boolean registrarTTAuto(String idFarmacia, Boolean estado, String usuarioReporte, Date fechaReporte) {
        DAO dao = new DAO() {
        };

        boolean flag;
        try {
            TtFarmacia farmacia = (TtFarmacia) dao.currentSession.load(TtFarmacia.class, idFarmacia);

            TtTicket tt = new TtTicket(farmacia, estado, usuarioReporte, fechaReporte);
            dao.currentSession.save(tt);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

    public boolean actualizarTicket(Integer _idTicket, Boolean _estado, String _numTicket, String _obs) {
        DAO dao = new DAO() {
        };
        boolean flag;
        try {

            TtTicket t = (TtTicket) dao.currentSession.load(TtTicket.class, _idTicket);
            t.setTtEstado(_estado);
            t.setTtNumeroTicket(_numTicket);
            t.setTtObservaciones(_obs);
            dao.currentSession.merge(t);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

    public boolean cerrarTicket(Integer _idTicket, Boolean _estado) {
        DAO dao = new DAO() {
        };
        boolean flag;
        try {

            Date currentDate = new Date();
            String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(currentDate);

            TtTicket t = (TtTicket) dao.currentSession.load(TtTicket.class, _idTicket);

            t.setTtFechaCierre(currentDate);
            t.setTtEstado(_estado);

            dao.currentSession.merge(t);
            flag = true;
        } catch (HibernateException e) {
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }

    public boolean comprobarEstadoTT(Integer _idTicket) {
        DAO dao = new DAO() {
        };
        boolean estadoTT = false;
        try {

            String sql = "select t.ttEstado FROM TtTicket as t where t.ttIdTicket = '" + _idTicket + "'";
            estadoTT = (boolean) dao.currentSession.createQuery(sql).uniqueResult();

        } catch (HibernateException e) {

            dao.currentSession.beginTransaction().rollback();
        }
        return estadoTT;

    }

//    public boolean actualizarTicket(Ticket ticket) {
//        boolean flag;
//        try {
//            DAO dao = new DAO() {
//            };
//            dao.currentSession.merge(ticket);
//            flag = true;
//        } catch (HibernateException e) {
//            DAO dao = new DAO() {
//            };
//            flag = false;
//            dao.currentSession.beginTransaction().rollback();
//        }
//        return flag;
    //   }
    public boolean eliminarTicket(Integer idTicket) {
        boolean flag;
        try {
            DAO dao = new DAO() {
            };
            TtTicket ticket = (TtTicket) dao.currentSession.load(TtTicket.class, idTicket);
            dao.currentSession.delete(ticket);
            flag = true;
        } catch (HibernateException e) {
            DAO dao = new DAO() {
            };
            flag = false;
            dao.currentSession.beginTransaction().rollback();
        }
        return flag;
    }
}
