/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.beans;

import ec.fe.tickets.dao.FarmaciaDao;
import ec.fe.tickets.dao.TicketDao;
import ec.fe.tickets.mail.ContenidosPredefinidos;
import ec.fe.tickets.mail.CorreoConfig;
import ec.fe.tickets.model.TtFarmacia;
import ec.fe.tickets.model.TtProveedor;
import ec.fe.tickets.red.Ping;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Jose
 */
@ManagedBean
@ViewScoped
public class FarmaciaBean {

    private List<TtFarmacia> farmacias;
    private TtFarmacia farmaciaSeleccionada;
    private String mensajePersonalizado;
    private List<SelectItem> seleccionarFarmacia;
    private List<TtFarmacia> listaFarmaciasFiltradas;
    private String idFarmacia;
    private boolean _estado;
    private String _idTicket;

    FarmaciaDao farmaciaDao = new FarmaciaDao();

    TicketDao td = new TicketDao();
    Date currentDate = new Date();
    String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(currentDate);

    /**
     * Creates a new instance of FarmaciaBean
     */
    @PostConstruct
    public void init() {
        this.farmaciaSeleccionada = new TtFarmacia();
        farmaciaSeleccionada.setTtProveedor(new TtProveedor());
    }

    public FarmaciaBean() {
        this.farmaciaSeleccionada = new TtFarmacia();
        this.farmacias = new ArrayList<TtFarmacia>();
        this.farmacias = farmaciaDao.buscarTodasFarmacias();

    }

    public void btnRegistrarFarmacia(ActionEvent actionEvent) {
        boolean farmacia = farmaciaDao.comprobarPKDuplicada(this.farmaciaSeleccionada.getFarIdFarmacia());

        if (farmaciaDao.registrarFarmacia(this.farmaciaSeleccionada) && farmacia == false) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Farmacia registrada exitosamente!");
            this.farmacias = new ArrayList<TtFarmacia>();
            this.farmacias.clear();
            this.farmacias = farmaciaDao.buscarTodasFarmacias();
            init();
        } else if (farmacia) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: se ha tratado de insertar una farmacia ya registrada!");
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido registrar la farmacia.");
        }
    }

    public void btnActualizarFarmacia(ActionEvent actionEvent) {
        if (farmaciaDao.actualizarFarmacia(this.farmaciaSeleccionada)) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "La información de la farmacia se ha actualizado correctamente!");
            farmacias.clear();
            farmacias = farmaciaDao.buscarTodasFarmacias();
            init();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido actualizar la información.");
        }
    }

    public void btnEliminarFarmacia(ActionEvent actionEvent) {
        if (farmaciaDao.eliminarFarmacia(this.farmaciaSeleccionada.getFarIdFarmacia())) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Farmacia eliminada exitosamente!");
            farmacias.clear();
            farmacias = farmaciaDao.buscarTodasFarmacias();
            init();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido eliminar la farmacia.");
        }
    }

    public void btnPing(ActionEvent actionEvent) {
        if (Ping.hacerPing(this.farmaciaSeleccionada.getFarIp())) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, this.farmaciaSeleccionada.getFarIdFarmacia() + ": EN LÍNEA.");
            farmacias.clear();
            farmacias = farmaciaDao.buscarTodasFarmacias();
            init();
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, this.farmaciaSeleccionada.getFarIdFarmacia() + ": SIN RESPUESTA.");
            farmacias.clear();
            farmacias = farmaciaDao.buscarTodasFarmacias();
            init();
        }
    }
//
//    public Map<String, Object> onFilter(AjaxBehaviorEvent event) {
//        DataTable table = (DataTable) event.getSource();
//        List<Farmacia> obj = table.getFilteredValue();
//        listaFarmaciasFiltradas = obj;
//        if (obj != null) {
//            System.out.println("filtered = " + obj.size());
//        } else {
//            System.out.println("No records found");
//        }
//        Map<String, Object> filters = table.getFilters();
//        return filters;
//    }

    public List<TtFarmacia> autoCompletarFarmacias(String query) {
        List<TtFarmacia> todasFarmacias = farmaciaDao.buscarTodasFarmacias();
        List<TtFarmacia> farmaciasFiltered = new ArrayList<TtFarmacia>();

        for (int i = 0; i < todasFarmacias.size(); i++) {
            TtFarmacia farmacia = todasFarmacias.get(i);
            if (farmacia.getFarIdFarmacia().toLowerCase().startsWith(query)) {
                farmaciasFiltered.add(farmacia);
            }
        }

        return farmaciasFiltered;
    }

    public void mostrarMensaje(FacesMessage.Severity severityMessage, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, "", mensaje));
    }

    //getters and setters
    public List<TtFarmacia> getFarmacias() {
        return farmacias;
    }

    public void setFarmacias(List<TtFarmacia> farmacias) {
        this.farmacias = farmacias;
    }

    public TtFarmacia getFarmaciaSeleccionada() {
        return farmaciaSeleccionada;
    }

    public void setFarmaciaSeleccionada(TtFarmacia farmaciaSeleccionada) {
        this.farmaciaSeleccionada = farmaciaSeleccionada;
    }

    public FarmaciaDao getFarmaciaDao() {
        return farmaciaDao;
    }

    public void setFarmaciaDao(FarmaciaDao farmaciaDao) {
        this.farmaciaDao = farmaciaDao;
    }

    public String getMensajePersonalizado() {
        return mensajePersonalizado;
    }

    public void setMensajePersonalizado(String mensajePersonalizado) {
        this.mensajePersonalizado = mensajePersonalizado;
    }

    public List<SelectItem> getSeleccionarFarmacia() {
        this.seleccionarFarmacia = new ArrayList<SelectItem>();
        List<TtFarmacia> listaFarmacias = farmaciaDao.seleccionarFarmacia();
        for (TtFarmacia farmacia : listaFarmacias) {
            SelectItem selectItem = new SelectItem(farmacia.getFarIdFarmacia());
            this.seleccionarFarmacia.add(selectItem);
        }
        return seleccionarFarmacia;
    }

    public boolean isEstado() {
        return _estado;
    }

    public void setEstado(boolean _estado) {
        this._estado = _estado;
    }

    public String getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(String idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public List<TtFarmacia> getListaFarmaciasFiltradas() {
        return listaFarmaciasFiltradas;
    }

    public void setListaFarmaciasFiltradas(List<TtFarmacia> listaFarmaciasFiltradas) {
        this.listaFarmaciasFiltradas = listaFarmaciasFiltradas;
    }

    public String prepararFarmacia() {
        TtFarmacia farmacia = new TtFarmacia();

        return "pm:edit?transition=flip";
    }

    // envío de correos
    public void sendMailCaidos(ActionEvent event) {

        switch (this.farmaciaSeleccionada.getTtProveedor().getProIdProveedor()) {

            case "CNT":
                if (sendMailCaidoCNT()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Caída de enlace reportada al Proveedor CNT, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre CNT.");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            case "Telconet":
                if (sendMailCaidoTelco()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Caída de enlace reportada al Proveedor Telconet, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre Telconet.");

                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            case "Level 3":
                if (sendMailCaidoL3()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Caída de enlace reportada al Proveedor Level 3, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre Level 3.");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            default:
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: No se ha encontrado el proveedor.");
                farmacias.clear();
                farmacias = farmaciaDao.buscarTodasFarmacias();
                init();
                break;
        }

    }

    public void sendMailIntermitentes(ActionEvent event) {
        switch (this.farmaciaSeleccionada.getTtProveedor().getProIdProveedor()) {
            case "CNT":
                if (sendMailIntermitenteCNT()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Intermitencia de enlace reportada al Proveedor CNT, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre CNT.");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            case "Telconet":
                if (sendMailIntermitenteTelco()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Intermitencia de enlace reportada al Proveedor Telconet, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre Telconet.");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            case "Level 3":
                if (sendMailIntermitenteL3()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Intermitencia de enlace reportada al Proveedor Level 3, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre Level 3.");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            default:
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: No se ha encontrado proveedor.");
                farmacias.clear();
                farmacias = farmaciaDao.buscarTodasFarmacias();
                init();
                break;
        }

    }

    public void sendMailPersonalizado(ActionEvent event) {
        switch (this.farmaciaSeleccionada.getTtProveedor().getProIdProveedor()) {
            case "CNT":
                if (sendMailPersonalizadoCNT()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Problema de enlace reportado al Proveedor CNT, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre CNT.");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            case "Telconet":
                if (sendMailPersonalizadoTelco()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Problema de enlace reportado al Proveedor Telconet, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre Telconet.");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            case "Level 3":
                if (sendMailPersonalizadoLevel3()) {
                    td.registrarTTAuto(this.farmaciaSeleccionada.getFarIdFarmacia(), true, null, currentDate);
                    mostrarMensaje(FacesMessage.SEVERITY_INFO, "Problema de enlace reportado al Proveedor Level 3, se ha registrado un TT correspondiente a esta"
                            + " incidencia, por favor mantenerlo actualizado con información que suministre Level 3.");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                } else {
                    mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error al enviar el correo!");
                    farmacias.clear();
                    farmacias = farmaciaDao.buscarTodasFarmacias();
                    init();
                }
                break;
            default:
                mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha encontrado el proveedor.");
                farmacias.clear();
                farmacias = farmaciaDao.buscarTodasFarmacias();
                init();
                break;
        }

    }

    //Enviar correo personalizado Telco
    public boolean sendMailPersonalizadoTelco() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();

        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioTelco(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE CON PROBLEMAS - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoPersonalizado(this.mensajePersonalizado, this.farmaciaSeleccionada.getFarIdFarmacia(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo personalizado Telco");
        }
        return flag;
    }

    //Enviar correo personalizado CNT
    public boolean sendMailPersonalizadoCNT() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();

        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioCNT(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE CON PROBLEMAS - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoPersonalizado(this.mensajePersonalizado, this.farmaciaSeleccionada.getFarIdFarmacia(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo personalizado CNT");
        }
        return flag;
    }

    //Enviar correo personalizado Level3
    public boolean sendMailPersonalizadoLevel3() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();

        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioLevel3(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE CON PROBLEMAS - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoPersonalizado(this.mensajePersonalizado, this.farmaciaSeleccionada.getFarIdFarmacia(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo personalizado Level");
        }
        return flag;
    }

//Enviar correo de enlace caído a CNT
    public boolean sendMailCaidoCNT() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();
        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioCNT(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE CAÍDO - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoEnlaceCaído(this.farmaciaSeleccionada.getFarIdFarmacia().toString(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo de enlace caído a CNT");
        }
        return flag;
    }

//Enviar correo de enlace intermitente a CNT
    public boolean sendMailIntermitenteCNT() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();

        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioCNT(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE INTERMITENTE - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoEnlaceIntermitente(this.farmaciaSeleccionada.getFarIdFarmacia(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo de enlace intermitente a CNT");
        }
        return flag;
    }

//Enviar correo de enlace caído a Telco
    public boolean sendMailCaidoTelco() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();

        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioTelco(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE CAÍDO - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoEnlaceCaído(this.farmaciaSeleccionada.getFarIdFarmacia(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo de enlace caído a Telco");
        }
        return flag;
    }

    //Enviar correo de intermitente caído a Telco
    public boolean sendMailIntermitenteTelco() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();

        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioTelco(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE INTERMITENTE - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoEnlaceIntermitente(this.farmaciaSeleccionada.getFarIdFarmacia(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo de enlace intermitente a CNT");
        }
        return flag;
    }

    //Enviar correo de enlace caído a level 3
    public boolean sendMailCaidoL3() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();

        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioLevel3(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE CAÍDO - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoEnlaceCaído(this.farmaciaSeleccionada.getFarIdFarmacia(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo de enlace intermitente a CNT");
        }
        return flag;
    }

    //Enviar correo de enlace intermitente a level 3
    public boolean sendMailIntermitenteL3() {
        boolean flag = false;
        CorreoConfig correoConfig = new CorreoConfig();
        ContenidosPredefinidos contenidosPredefinidos = new ContenidosPredefinidos();

        if (correoConfig.enviarCorreo(contenidosPredefinidos.obtenerDestinatarioLevel3(this.farmaciaSeleccionada.getTtProveedor().getProCorreo().split(",")), "ENLACE INTERMITENTE - " + this.farmaciaSeleccionada.getFarIdFarmacia() + " - FARMAENLACE",
                contenidosPredefinidos.obtenerTextoEnlaceIntermitente(this.farmaciaSeleccionada.getFarIdFarmacia(),
                        this.farmaciaSeleccionada.getFarPiloto(), this.farmaciaSeleccionada.getFarTelfCelular(),
                        this.farmaciaSeleccionada.getFarTelfConvencional(), this.farmaciaSeleccionada.getFarIp()))) {
            flag = true;
        } else {
            System.out.println("error al enviar correo de enlace intermitente a CNT");
        }
        return flag;
    }

}
