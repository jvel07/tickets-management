/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.common;


import ec.fe.tickets.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Jose07
 */
public abstract class DAO {

    public Session currentSession;

    public DAO() {
        currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public DAO(Session session) {
        currentSession = session;
    }

    public DAO(DAO dao) {
        currentSession = dao.getCurrentSession();
    }

    public Session getCurrentSession() {
        return currentSession;
    }
    
    public void setCurrentSession(Session currentSession){
     this.currentSession = currentSession;
    }

}
