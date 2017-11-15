/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.crawler.impl;

import com.offertelowcost.crawler.interfaces.AbstractCrawler;
import com.offertelowcost.model.Post;
import com.offertelowcost.util.Categories;
import com.offertelowcost.util.PostCreator;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author DGASPERI
 */
public class CrawlerZalando extends AbstractCrawler{
    
    private static final String SHOP_NAME = "Zalando";
    private static final String LOGO_URL = "http://www.offertelowcost.net/staticfiles/img/zalando.png";
    List<Post> listaItems;
    
    public CrawlerZalando(){
    }
    
    private Post creaModel (Element li, String categoria){
        //da ricreare il link
        String zalandoAbsUrl = "https://www.zalando.it";
        Element img = li.select("img").first();
        String imageUrlStr = img.absUrl("src");
        URL imageUrl = null;
        try {
             imageUrl = new URL(imageUrlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlerZalando.class.getName()).log(Level.SEVERE, null, ex);
        }
        String titleStr = img.attr("alt");
        Element prezzo = li.select("div.catalogArticlesList_price").last();
        String prezzoStr = prezzo.html();
        //System.err.println(titleStr);
        Element itemUrl = li.select("a").first();
        String itemUrlStr = itemUrl.absUrl("href");
        //creo il post da template html
        PostCreator creator = new PostCreator(imageUrlStr, prezzoStr, titleStr, itemUrlStr,SHOP_NAME,LOGO_URL);
        //System.err.println(itemUrlStr);
        //creo l'oggetto post da inserire su wordpress
        Post post = new Post(titleStr, creator.getPost(), imageUrl, SHOP_NAME);
        //setto la categoria del post appena creato
        List<String> categories = new ArrayList<>();
        categories.add(categoria);
        post.setCategories(categories);
        
        return post;
    }

    @Override
    public List<Post> getInformatica() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getAppGiochi() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getAutoMoto() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getVideogiochiConsole() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getBellezza() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getCasa() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getFotografiaVideo() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getElettronica() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getCdDvd() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getFaiDaTe() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getFilmETv() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getGiardinoGiardinaggio() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getGiochiGiocattoli() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getGioielli() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getLibri() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getMusica() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getScarpeBorse() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getSoftware() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getSport() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getStrumentiMusicaliDj() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getAbbigliamento() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {

            doc = Jsoup.connect("https://www.zalando.it/promo/").get();
            //System.err.println(doc.html());
            Elements lis = doc.select("li.catalogArticlesList_item");
//            
            listaItemsTemp = new ArrayList<>();
            for (Element li : lis) {
                    listaItemsTemp.add(creaModel(li,Categories.T_Categories.Abbigliamento.getValue()));
//                    System.err.println("**************");
//                    System.err.println(li.html());
//                    System.err.println("**************");
            }
            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{lis.size(), Categories.T_Categories.Abbigliamento.getValue()});
        } catch (IOException ex) {
            Logger.getLogger(CrawlerZalando.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
    }

    @Override
    public List<Post> getViaggi() {
        return new ArrayList<>();
    }
}
