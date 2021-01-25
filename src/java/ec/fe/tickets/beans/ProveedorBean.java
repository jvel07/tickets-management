/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.beans;

import ec.fe.tickets.dao.ProveedorDao;
import ec.fe.tickets.model.TtProveedor;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
public class ProveedorBean {

    private List<TtProveedor> proveedores;
    private TtProveedor proveedorSeleccionado;
    private List<SelectItem> seleccionarProveedor;

    ProveedorDao proveedorDao = new ProveedorDao();

    /**
     * Creates a new instance of FarmaciaBean
     */
    @PostConstruct
    public void init() {
        this.proveedorSeleccionado = new TtProveedor();
    }

    public ProveedorBean() {
        this.proveedores = new ArrayList<TtProveedor>();
        this.proveedores = proveedorDao.buscarTodosProveedores();
    }

    public void btnRegistrarProveedor(ActionEvent actionEvent) {
        boolean proveedor = proveedorDao.comprobarProveedorDup(this.proveedorSeleccionado.getProIdProveedor());

        if (proveedorDao.registrarProveedor(this.proveedorSeleccionado) && proveedor != true) {
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Proveedor registrado exitosamente!");
//           farmacias = new ArrayList<>();
            proveedores.clear();
            proveedores = proveedorDao.buscarTodosProveedores();
            init();
        } else if (proveedor) {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: se ha tratado de insertar un proveedor ya registrado!");
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido registrar el proveedor.");
        }
    }

    public void btnActualizarProveedor(ActionEvent actionEvent) {
        if (proveedorDao.actualizarProveedor(this.proveedorSeleccionado)) {
            proveedores.clear();
            proveedores = proveedorDao.buscarTodosProveedores();
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "La información del proveedor se ha actualizado correctamente!");
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido actualizar la información.");
        }
    }

    public void btnEliminarProveedor(ActionEvent actionEvent) {
        if (proveedorDao.eliminarProveedor(this.proveedorSeleccionado.getProIdProveedor())) {
            proveedores.clear();
            proveedores = proveedorDao.buscarTodosProveedores();
            mostrarMensaje(FacesMessage.SEVERITY_INFO, "Proveedor eliminado exitosamente!");
        } else {
            mostrarMensaje(FacesMessage.SEVERITY_ERROR, "Error: no se ha podido eliminar el proveedor.");
        }
    }

    public void mostrarMensaje(FacesMessage.Severity severityMessage, String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(severityMessage, "", mensaje));
    }

    //getters and setters
    public List<TtProveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<TtProveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public TtProveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(TtProveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public ProveedorDao getProveedorDao() {
        return proveedorDao;
    }

    public void setProveedorDao(ProveedorDao proveedorDao) {
        this.proveedorDao = proveedorDao;
    }

    public List<SelectItem> getSeleccionarProveedor() {
        this.seleccionarProveedor = new ArrayList<SelectItem>();
        List<TtProveedor> listaPerfiles = proveedorDao.seleccionarProveedores();
        for (TtProveedor proveedor : listaPerfiles) {
            SelectItem selectItem = new SelectItem(proveedor.getProIdProveedor());
            this.seleccionarProveedor.add(selectItem);
        }

        return seleccionarProveedor;
    }

}
