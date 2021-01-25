/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.beans;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jose
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

      public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
              
        String ruta = "";

        if (username != null && username.equals("tickets") && password != null && password.equals("**Tc174**")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", username);
            ruta = "/tickets/faces/menuTemplate.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Credenciales no válidas...");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }
    
       public void logOut() {
        String rute = "/tickets/faces/login.xhtml";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();

        context.addCallbackParam("loggedOut", true);
        context.addCallbackParam("ruta", rute);
    }


}
