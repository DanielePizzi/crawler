/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.main;

import com.offertelowcost.crawler.impl.CrawlerAmazon;
import com.offertelowcost.crawler.impl.CrawlerEbay;
import com.offertelowcost.crawler.impl.CrawlerFeltrinelli;
import com.offertelowcost.crawler.impl.CrawlerMondadori;
import com.offertelowcost.crawler.interfaces.AbstractCrawler;
import com.offertelowcost.model.Post;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import redstone.xmlrpc.XmlRpcClient;

/**
 *
 * @author DGASPERI
 */
public class Crawler {
    
    private static final Logger LOG = Logger.getLogger(Crawler.class.getName());
    private static final Scanner READER = new Scanner(System.in);
    private static final String XMLRPC_URL = "http://www.offertelowcost.net/xmlrpc.php";
    private static XmlRpcClient client;
    
    
    public static void main(String[] args) {
        
        int selezione = 0;
        try {
            client = new XmlRpcClient(XMLRPC_URL, true);
        
            while(selezione != 5){
            	 
            	selezione = menu();
            
	            if(selezione == 1){
	                System.out.println("HAI SELEZIONATO L'OPZIONE: "+selezione);
	                List<Post> postList = new ArrayList();
	
	                AbstractCrawler crawlerAmazon = new CrawlerAmazon();
	                postList.addAll(crawlerAmazon.inizializza());
	                int count = 1;
	                int size = postList.size();
	                for (Post post : postList) {
	                    XMLRPCClient.inserisciArticolo(client, post);
	                    int conta = count++;
	                    LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
	                 }
	
	            }else if(selezione == 2){
	                System.out.println("HAI SELEZIONATO L'OPZIONE: "+selezione);
	                List<Post> postList = new ArrayList();
	                AbstractCrawler crawlerEbay = new CrawlerEbay();
	                postList.addAll(crawlerEbay.inizializza());
	                int count = 1;
	                int size = postList.size();
	                for (Post post : postList) {
	                    XMLRPCClient.inserisciArticolo(client, post);
	                    int conta = count++;
	                    LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
	                }
	            }else if(selezione == 3){
	                System.out.println("HAI SELEZIONATO L'OPZIONE: "+selezione);
	                List<Post> postList = new ArrayList();
	                AbstractCrawler crawlerFeltrinelli = new CrawlerFeltrinelli();
	                postList.addAll(crawlerFeltrinelli.inizializza());
	                int count = 1;
	                int size = postList.size();
	                for (Post post : postList) {
	                    XMLRPCClient.inserisciArticolo(client, post);
	                    int conta = count++;
	                    LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
	                }
	            }else if(selezione == 4){
	                System.out.println("HAI SELEZIONATO L'OPZIONE: "+selezione);
	                List<Post> postList = new ArrayList();
	                AbstractCrawler crawlerMondadori = new CrawlerMondadori();
	                postList.addAll(crawlerMondadori.inizializza());
	                int count = 1;
	                int size = postList.size();
	                for (Post post : postList) {
	                    XMLRPCClient.inserisciArticolo(client, post);
	                    int conta = count++;
	                    LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
	                }
	            }else if (selezione == 5) {
	                System.out.println("HAI SELEZIONATO L'OPZIONE: "+selezione);
	                READER.close();
	                System.out.println("CHIUSURA DELL' APPLICAZIONE...");
	                System.exit(0);
	            }
        	}
        } catch (MalformedURLException ex) {
           LOG.log(Level.SEVERE, "ERRORE XmlRpcClient", ex);
        }
     }
    
    
    public static int menu(){
        System.out.println();
        System.out.println();
        int selezione;
        
        System.out.println("********************************************************");
        System.out.println("*        INTERFACCIA DI INSERIMENTO OFFERTE            *");
        System.out.println("********************************************************");
        System.out.println("*                                                      *");
        System.out.println("*        1 - INSERISCI OFFERTE AMAZON                  *");
        System.out.println("*        2 - INSERISCI OFFERTE E-BAY                   *");
        System.out.println("*        3 - INSERISCI OFFERTE FELTRINELLI             *");
        System.out.println("*        4 - INSERISCI OFFERTE MONDADORI               *");
        System.out.println("*        5 - ESCI                                      *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************");
        System.out.println();
        System.out.println();
        System.out.print("INSERISCI IL NUMERO DELLA TUA SCELTA E PREMI INVIO: ");

        selezione = READER.nextInt();
        
        return selezione;
    }
    
    public static int menuIntermedio(){
        
        int selezione;
        System.out.print("INSERISCI IL NUMERO DELLA TUA SCELTA E PREMI INVIO:");

        selezione = READER.nextInt();
        
        return selezione;
    }
}
