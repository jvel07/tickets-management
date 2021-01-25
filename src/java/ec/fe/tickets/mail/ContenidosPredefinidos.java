/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.mail;

/**
 *
 * @author Jose
 */
public class ContenidosPredefinidos {

    private String version = "versión 1.5";
    
    public String obtenerTextoEnlaceCaído(String _enlace, String _identificador, String _telefonoCelular, String _telefonoConvencional,
            String _ip) {
        String mensaje = null;
        mensaje = "<style type=\"text/css\">\n"
                + "<!--\n"
                + ".estilo1 {\n"
                + "font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "font-size: 14px;\n"
                + "color: white;\n"
                + "}\n"
                + ".estilo2 {\n"
                + "font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "font-size: 13px;\n"
                + "color: black;\n"
                + "}\n"
                + ".bottom {\n"
                + "    text-align: center;\n"
                + "    position: relative;\n"
                + "    background-color: #00AFF0;\n"
                + "    font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "    font-size: 13px;\n"
                + "    color: white;\n"
                + "    padding: 5px;\n"
                + "    margin: 220px 0px 0px 0px; \n"
                + "}"
                + "</style>"
                + "\n"
                + "<h1 class=\"estilo2\">Estimados, por favor su ayuda con lo siguiente: </h1>"
                + "\n<table border=\"1\" cellspacing=\"5\" cellpadding=\"3\" rules=\"rows\">\n"
                + "<CAPTION class=\"estilo1\">Información de Enlace de Datos </CAPTION>"
                + "    <tr ALIGN=CENTER bgcolor=\"#00AFF0\" >\n"
                + "      <td class=\"estilo1\">Nombre de Farmacia</font></td>\n"
                + "      <td class=\"estilo1\">Identificador</td>\n"
                + "      <td class=\"estilo1\">IP LAN</td>\n"
                + "      <td class=\"estilo1\">Problema</td>\n"
                + "      <td class=\"estilo1\">Contacto 1</td>\n"
                + "      <td class=\"estilo1\">Contacto 2</td>\n"
                + "    </tr>\n"
                + "    <tr ALIGN=CENTER>\n"
                + "      <td class=\"estilo2\">" + _enlace + "</td>\n"
                + "      <td class=\"estilo2\">" + _identificador + "</td>\n"
                + "      <td class=\"estilo2\">" + _ip + "</td>\n"
                + "      <td class=\"estilo2\">Enlace Caído</td>\n"
                + "      <td class=\"estilo2\">" + _telefonoCelular + "</td>\n"
                + "      <td class=\"estilo2\">" + _telefonoConvencional + "</td>\n"
                + "    </tr>\n"
                + "</table> "
                + "<h1 class=\"estilo2\">Por favor, notificar avances y soluciones respondiendo a este correo; ó comunicarse a: 2993100 ext. 1630/39, 7071."
                + " Esperamos su respuesta, gracias por su gestión! </h1>"
                + "<h1 class=\"bottom\">\n"
                + "José Egas López &copy; Sistema de Enlaces Caídos, "+version+" - Farmaenlace Cía. Ltda."
                + "</h1>";
        return mensaje;
    }

    public String obtenerTextoEnlaceIntermitente(String _enlace, String _identificador, String _telefonoCelular, String _telefonoConvencional, String _ip) {
        String mensaje = null;
        mensaje = "<style type=\"text/css\">\n"
                + "<!--\n"
                + ".estilo1 {\n"
                + "font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "font-size: 14px;\n"
                + "color: white;\n"
                + "}\n"
                + ".estilo2 {\n"
                + "font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "font-size: 13px;\n"
                + "color: black;\n"
                + "}\n"
                + ".bottom {\n"
                + "    text-align: center;\n"
                + "    position: relative;\n"
                + "    background-color: #00AFF0;\n"
                + "    font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "    font-size: 13px;\n"
                + "    color: white;\n"
                + "    padding: 5px;\n"
                + "    margin: 220px 0px 0px 0px; \n"
                + "}"
                + "</style>"
                + "\n"
                + "<h1 class=\"estilo2\">Estimados, por favor su ayuda con lo siguiente: </h1>"
                + "\n<table border=\"1\" cellspacing=\"5\" cellpadding=\"3\" rules=\"rows\">\n"
                + "<CAPTION class=\"estilo1\">Información de Enlace de Datos </CAPTION>"
                + "    <tr ALIGN=CENTER bgcolor=\"#00AFF0\" >\n"
                + "      <td class=\"estilo1\">Nombre de Farmacia</font></td>\n"
                + "      <td class=\"estilo1\">Identificador</td>\n"
                + "      <td class=\"estilo1\">IP LAN</td>\n"
                + "      <td class=\"estilo1\">Problema</td>\n"
                + "      <td class=\"estilo1\">Contacto 1</td>\n"
                + "      <td class=\"estilo1\">Contacto 2</td>\n"
                + "    </tr>\n"
                + "    <tr ALIGN=CENTER>\n"
                + "      <td class=\"estilo2\">" + _enlace + "</td>\n"
                + "      <td class=\"estilo2\">" + _identificador + "</td>\n"
                + "      <td class=\"estilo2\">" + _ip + "</td>\n"
                + "      <td class=\"estilo2\">Enlace Intermitente</td>\n"
                + "      <td class=\"estilo2\">" + _telefonoCelular + "</td>\n"
                + "      <td class=\"estilo2\">" + _telefonoConvencional + "</td>\n"
                + "    </tr>\n"
                + "</table> "
                + "<h1 class=\"estilo2\">Por favor, notificar avances y soluciones respondiendo a este correo; ó comunicarse a: 2993100 ext. 1630/39, 7071."
                + " Esperamos su respuesta, gracias por su gestión! </h1>"
                + "<h1 class=\"bottom\">\n"
                + "José Egas López &copy; Sistema de Enlaces Caídos, "+version+"  - Farmaenlace Cía. Ltda."
                + "</h1>";

        return mensaje;
    }

    public String obtenerTextoPersonalizado(String _mensajePersonalizado, String _enlace, String _identificador, String _telefonoCelular, String _telefonoConvencional, String _ip) {
        String mensaje = null;
        mensaje = "<style type=\"text/css\">\n"
                + "<!--\n"
                + ".estilo1 {\n"
                + "font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "font-size: 14px;\n"
                + "color: white;\n"
                + "}\n"
                + ".estilo2 {\n"
                + "font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "font-size: 13px;\n"
                + "color: black;\n"
                + "}\n"
                + ".bottom {\n"
                + "    text-align: center;\n"
                + "    position: relative;\n"
                + "    background-color: #00AFF0;\n"
                + "    font-family: Segoe UI Light, Helvetica, sans-serif;\n"
                + "    font-size: 13px;\n"
                + "    color: white;\n"
                + "    padding: 5px;\n"
                + "    margin: 220px 0px 0px 0px; \n"
                + "}"
                + "</style>"
                + "\n"
                + "<h1 class=\"estilo2\">Estimados, por favor su ayuda con lo siguiente: </h1>"
                + "\n<table border=\"1\" cellspacing=\"5\" cellpadding=\"3\" rules=\"rows\">\n"
                + "<CAPTION class=\"estilo1\">Información de Enlace de Datos </CAPTION>"
                + "    <tr ALIGN=CENTER bgcolor=\"#00AFF0\" >\n"
                + "      <td class=\"estilo1\">Nombre de Farmacia</font></td>\n"
                + "      <td class=\"estilo1\">Identificador</td>\n"
                + "      <td class=\"estilo1\">IP LAN</td>\n"
                + "      <td class=\"estilo1\">Problema</td>\n"
                + "      <td class=\"estilo1\">Contacto 1</td>\n"
                + "      <td class=\"estilo1\">Contacto 2</td>\n"
                + "    </tr>\n"
                + "    <tr ALIGN=CENTER>\n"
                + "      <td class=\"estilo2\">" + _enlace + "</td>\n"
                + "      <td class=\"estilo2\">" + _identificador + "</td>\n"
                + "      <td class=\"estilo2\">" + _ip + "</td>\n"
                + "      <td class=\"estilo2\">" + _mensajePersonalizado+"</td>\n"
                + "      <td class=\"estilo2\">" + _telefonoCelular + "</td>\n"
                + "      <td class=\"estilo2\">" + _telefonoConvencional + "</td>\n"
                + "    </tr>\n"
                + "</table> "
                + "<h1 class=\"estilo2\">Por favor, notificar avances y soluciones respondiendo a este correo; ó comunicarse a: 2993100 ext. 1630/39, 7071."
                + " Esperamos su respuesta, gracias por su gestión! </h1>"
                + "<h1 class=\"bottom\">\n"
                + "José Egas López &copy; Sistema de Enlaces Caídos, "+version+" "
                + ""
                + "- Farmaenlace Cía. Ltda."
                + "</h1>";

        return mensaje;
    }

    public String[] obtenerDestinatarioCNT(String[] _destCNT) {
            // String[] destinatarios = {"sistemaenlaces@farmaenlace.com", "cntcorp@cnt.gob.ec"};
        String[] destinatarios = _destCNT;
        return destinatarios;
    }

    public String[] obtenerDestinatarioTelco(String[] _destTelco) {
        String[] destinatarios = _destTelco;
        //String[] destinatarios = {"redes@farmaenlace.com"};
        return destinatarios;
    }

    public String[] obtenerDestinatarioLevel3(String[] _destLevel) {
       // String[] destinatarios = {"sistemaenlaces@farmaenlace.com", "network-management@globalcrossing.com", "servicedesknmes@GlobalCrossing.com"};
        String[] destinatarios = _destLevel;
        return destinatarios;
    }

}
