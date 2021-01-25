/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.fe.tickets.beans;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Jose
 */
@ManagedBean

public class NavigationView {

    /**
     * Creates a new instance of NavigationView
     * @return 
     */
 public String irAIncidencias() {
        return "/faces/menuPrincipal/responsive2.xhtml?pm:reportarIncidencias";
    }
    
}
