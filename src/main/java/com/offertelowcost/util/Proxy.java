/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 *
 * @author DGASPERI
 */
public class Proxy {

    public Proxy() {
    }

    public static void settaProxy() {
        final String user = "dgasperi";
        final String pass = "Daemond03";
        //proxy
        System.setProperty("http.proxyHost", "10.68.64.37");
        System.setProperty("http.proxyPort", "8081");
        System.setProperty("http.proxyUser", user);
        System.setProperty("http.proxyPassword", pass);
        System.setProperty("https.proxyHost", "10.68.64.37");
        System.setProperty("https.proxyPort", "8081");
        System.setProperty("https.proxyUser", user);
        System.setProperty("https.proxyPassword", pass);

        Authenticator.setDefault(
                new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass.toCharArray());
            }
        }
        );
    }
    
        public static void settaProxyBNL() {
        
        //proxy
        System.setProperty("http.proxyHost", "10.237.248.199");
        System.setProperty("http.proxyPort", "8080");
        System.setProperty("https.proxyHost", "10.237.248.199");
        System.setProperty("https.proxyPort", "8080");
    }
}
