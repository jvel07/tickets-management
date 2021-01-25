/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.fe.tickets.red;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class Ping {

//    public static boolean ping(String ipAddress) {
//        HttpURLConnection connection = null;
//        try {
//
//            final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
//
//            Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
//
//            Matcher matcher = pattern.matcher(ipAddress);
//
//            long inicio = System.currentTimeMillis();
//            URL u = new URL("192.168.238.1");
//            connection = (HttpURLConnection) u.openConnection();
//            connection.setRequestMethod("HEAD");
//
//            int code = connection.getResponseCode();
//            long fin = System.currentTimeMillis();
//            System.out.println("Codigo: " + code + " t:" + (fin - inicio));
//
//        } catch (Exception ex) {
//            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//
//        }
//        return false;
//
//    }
    public static boolean hacerPing(String ip) {

        boolean conStatus = false;

        try {

            int timeOut = 2000;
            long inicio = System.currentTimeMillis();

//            final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
//
//            Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
//
//            Matcher matcher = pattern.matcher(ip);
            InetAddress address = InetAddress.getByName(ip);

            if (address.isReachable(timeOut)) {
                long fin = System.currentTimeMillis();
                conStatus = true;
                System.out.println("t:" + (fin - inicio) / 1000);
            } else {
                conStatus = false;
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conStatus;
    }

}
