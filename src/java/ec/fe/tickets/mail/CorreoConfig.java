/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Jose
 */
public class CorreoConfig {

    public boolean enviarCorreo(String[] _destinatarios, String _asunto, String _cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.farmaenlace.com");
        props.put("mail.stmp.user", "sistemaenlaces@farmaenlace.com");
        //If you want you use TLS 
        //  props.put("mail.smtp.auth", "true");

        //  props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.password", "password");
        // If you want to use SSL
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "25");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String username = "sistemaenlaces@farmaenlace.com";
                String password = "1234567";
                return new PasswordAuthentication(username, password);
            }
        });
        //String[] to = {"joseegas@farmaenlace.com"};
        String[] to = _destinatarios;
        String from = "sistemaenlaces@farmaenlace.com";
        String subject = _asunto;
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] addressTo = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                addressTo[i] = new InternetAddress(to[i]);
            }
            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);            
            // msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);

            // Create the message part 
           // BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            //messageBodyPart.setText(_cuerpo);
            
            msg.setContent(_cuerpo, "text/html; charset=ISO-8859-1");

//            BodyPart text = new MimeBodyPart();
//            text.setContent("\n"+
//                    + "<h2><span style="color: red;">Comunicándose desde Java</span></h2>\n"+
//                    + "Este email ha sido enviado desde java\n"
//                    + "", "text/html");
            // Create a multipar message
            //Multipart multipart = new MimeMultipart();
            // Set text message part
            // multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
//            messageBodyPart = new MimeBodyPart();
//            String filename = "cmdcmd.txt";
//            DataSource source = new FileDataSource("C:/Users/Jose/Documents/Farmaenlace/cmdcmd.txt");
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filename);
//            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            //  msg.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            transport.send(msg);
            System.out.println("Correo enviado.");
            return true;
        } catch (Exception exc) {
            System.out.println(exc);
            System.out.println("No se ha podido enviar el correo!");
            return false;
        }
    }
}
