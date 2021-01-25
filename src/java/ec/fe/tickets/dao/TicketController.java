/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.dao;

import ec.fe.tickets.model.TtTicket;
import java.util.Date;

/**
 *
 * @author Jose
 */
public class TicketController {

    public static boolean autoRegistrarTicket(TtTicket t) {
        boolean success = false;

        try {
            TicketDao ticketDao = new TicketDao();

           success = ticketDao.registrarTTAuto(t.getTtFarmacia().getFarIdFarmacia(), t.getTtEstado(), t.getTtUsuarioReporte(), t.getTtFechaReporte());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return success;
    }

    public static boolean actualizarTicket(TtTicket t) {
        boolean success = false;
        try {
            TicketDao dao = new TicketDao();
            success = dao.actualizarTicket(t.getTtIdTicket(), true, t.getTtNumeroTicket(), t.getTtObservaciones());
            if (success == true) {
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    public static boolean cerrarTicket(TtTicket t) {
        boolean success = false;
        try {
            TicketDao dao = new TicketDao();

            success = dao.cerrarTicket(t.getTtIdTicket(), false);
//            if (success) {
//                FarmaciaController.actualizarEstadoTTFarm(t.getFarmacia());
//            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

}
