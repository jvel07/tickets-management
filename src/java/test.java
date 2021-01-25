
import ec.fe.tickets.common.HibernateSessionHandler;
import ec.fe.tickets.dao.TicketController;
import ec.fe.tickets.dao.TicketDao;
import ec.fe.tickets.model.TtFarmacia;
import ec.fe.tickets.model.TtTicket;
import ec.fe.tickets.red.Ping;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.Utilities;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jose
 */
public class test {

    /**
     * @param args the command line arguments
     */
    private Boolean _estado;
    private static String _usuarioReporte;
    private static Date _fechaReporte;
    private static Date _fechaCierre;
    private static String _observaciones;
    private static TtFarmacia _farmacia;
    private String _numTicket;

    public static void main(String[] args) {
        // TODO code application logic here

        HibernateSessionHandler sesion = new HibernateSessionHandler();
    

        sesion.close();
    }

}
