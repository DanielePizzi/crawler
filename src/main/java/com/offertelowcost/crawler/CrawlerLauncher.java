/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.crawler;

import com.offertelowcost.crawler.interfaces.AbstractCrawler;
import com.offertelowcost.crawler.impl.CrawlerEbay;
import com.offertelowcost.crawler.impl.CrawlerFeltrinelli;
import com.offertelowcost.crawler.impl.CrawlerGroupon;
import com.offertelowcost.crawler.impl.CrawlerZalando;
import com.offertelowcost.model.Post;
import com.offertelowcost.util.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DGASPERI
 */
public class CrawlerLauncher {
    
    private static List<Post> postList = new ArrayList();
    
//    public static void main(String[] args) {
//
////        String user = "felefant";
////        String pass = "Capg3mini9";
////
////        //proxy
////        System.setProperty("http.proxyHost", "10.68.64.37");
////        System.setProperty("http.proxyPort", "8081");
////        System.setProperty("http.proxyUser", user);
////        System.setProperty("http.proxyPassword", pass);
//        AbstractCrawler crawlerEbay = new CrawlerEbay();
//        AbstractCrawler crawlerZalando = new CrawlerZalando();
//        AbstractCrawler crawlerFeltrinelli = new CrawlerFeltrinelli();
//        AbstractCrawler crawlerGroupon = new CrawlerGroupon();
//        
//        postList.addAll(crawlerEbay.inizializza());
////        postList.addAll(crawlerZalando.inizializza());
////        postList.addAll(crawlerFeltrinelli.inizializza());
//        //postList.addAll(crawlerGroupon.inizializza());
////        System.err.println(postList.size());
//        for (Post post : postList) {
//            System.err.println(post.toString());
//        }
//        
//    }
    
    public static List<Post> getPostList() {
        return postList;
    }
}
