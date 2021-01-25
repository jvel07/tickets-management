/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.beans;

import ec.fe.tickets.dao.FarmaciaDao;
import ec.fe.tickets.dao.TicketController;
import ec.fe.tickets.dao.TicketDao;
import ec.fe.tickets.model.TtFarmacia;
import ec.fe.tickets.model.TtTicket;

import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jose
 */
@ManagedBean
@RequestScoped
public class TicketBean {

    private List<TtTicket> tickets;
    private TtTicket ticketSeleccionado;

    TicketDao ticketDao = new TicketDao();

  //  private String _idTicket;
    private boolean _estado;
    private String _usuarioReporte;
    private Date _fechaReporte;
    private Date _fechaCierre;
    private String _observaciones;
    private String _fkIdFarmacia;
    private String _numTicket;

    /**
     * Creates a new instance of TicketBean
     */
    @PostConstruct
    public void init() {
        this.ticketSeleccionado = new TtTicket();
        ticketSeleccionado.setTtFarmacia(new TtFarmacia());
    }

    public TicketBean() {
        this.tickets = new ArrayList<TtTicket>();
        this.tickets = ticketDao.buscarTodostickets();
    }

    public void registroAutoTT(ActionEvent actionEvent) {
       
        boolean b = ticketDao.registrarTTAuto(this._fkIdFarmacia, this._estado, this._usuarioReporte, this._fechaReporte);
        if (b) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ticket registrado exitosamente!");
            tickets.clear();
            tickets = ticketDao.buscarTodostickets();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido registrar el Ticket.");
        }
    }

    public void btnRegistrarTicket(ActionEvent actionEvent) {
        FarmaciaDao farmaciaDao = new FarmaciaDao();

        Date currentDate = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(currentDate);
        this.ticketSeleccionado.setTtEstado(true);
        this.ticketSeleccionado.setTtFechaReporte(currentDate);
        if (ticketDao.registrarTicket(this.ticketSeleccionado)) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ticket registrado exitosamente!");
            tickets.clear();
            tickets = ticketDao.buscarTodostickets();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido registrar el Ticket.");
        }
    }

    public void btnActualizarTicket(ActionEvent actionEvent) {

        boolean success = TicketController.actualizarTicket(this.ticketSeleccionado);
        if (success) {
            tickets.clear();
            tickets = ticketDao.buscarTodostickets();
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "La información del Ticket se ha actualizado correctamente!");
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido cerrar el ticket..");
        }
    }

    public void btnCerrarTicket(ActionEvent actionEvent) {
//        Date currentDate = new Date();
//        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(currentDate);
//        this.ticketSeleccionado.setFechaCierre(currentDate);
//        this.ticketSeleccionado.setEstado(true);
     
        boolean estado = ticketDao.comprobarEstadoTT(this.ticketSeleccionado.getTtIdTicket());
        boolean success = TicketController.cerrarTicket(this.ticketSeleccionado);

        if (success && estado) {
            //  FarmaciaController.actualizarEstadoTTFarm(this.ticketSeleccionado.getFarmacia());
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Se ha cerrado el ticket: >" + this.ticketSeleccionado.getTtNumeroTicket() + "< con la fecha actual.");
            tickets.clear();
            tickets = ticketDao.buscarTodostickets();

        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido cerrar el ticket; es posible que ya se encuentre cerrado!");
        }

    }

    public void btnEliminarTicket(ActionEvent actionEvent) {
        if (ticketDao.eliminarTicket(this.ticketSeleccionado.getTtIdTicket())) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Ticket eliminado exitosamente!");
            tickets.clear();
            tickets = ticketDao.buscarTodostickets();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido eliminar la farmacia.");
        }
    }

    public void mostrarMensaje(FacesMessage.Severity severityMessage, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, "", mensaje));
    }

    
        
    //getters and setters
    public List<TtTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<TtTicket> tickets) {
        this.tickets = tickets;
    }

    public TtTicket getTicketSeleccionado() {
        return ticketSeleccionado;
    }

    public void setTicketSeleccionado(TtTicket ticketSeleccionado) {
        this.ticketSeleccionado = ticketSeleccionado;
    }

//    public String getIdTicket() {
//        return _idTicket;
//    }
//
//    public void setIdTicket(String _idTicket) {
//        this._idTicket = _idTicket;
//    }
    public boolean isEstado() {
        return _estado;
    }

    public void setEstado(boolean _estado) {
        this._estado = _estado;
    }

    public String getUsuarioReporte() {
        return _usuarioReporte;
    }

    public void setUsuarioReporte(String _usuarioReporte) {
        this._usuarioReporte = _usuarioReporte;
    }

    public Date getFechaReporte() {
        return _fechaReporte;
    }

    public void setFechaReporte(Date _fechaReporte) {
        this._fechaReporte = _fechaReporte;
    }

    public Date getFechaCierre() {
        return _fechaCierre;
    }

    public void setFechaCierre(Date _fechaCierre) {
        this._fechaCierre = _fechaCierre;
    }

    public String getObservaciones() {
        return _observaciones;
    }

    public void setObservaciones(String _observaciones) {
        this._observaciones = _observaciones;
    }

    public String getFkIdFarmacia() {
        return _fkIdFarmacia;
    }

    public void setFkIdFarmacia(String _fkIdFarmacia) {
        this._fkIdFarmacia = _fkIdFarmacia;
    }

    public String getNumTicket() {
        return _numTicket;
    }

    public void setNumTicket(String _numTicket) {
        this._numTicket = _numTicket;
    }

}
