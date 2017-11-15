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
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author DGASPERI
 */
public class CrawlerGroupon extends AbstractCrawler{
    
    private static final String SHOP_NAME = "Groupon";
    private static final String LOGO_URL = "http://www.offertelowcost.net/staticfiles/img/groupon.png";
    

    public CrawlerGroupon() {
    }
    
    
    private Post creaModel (Element div, String categoria){
        Element img = div.select("img").first();
        String imageUrlStr = img.absUrl("data-original");
        URL imageUrl = null;
        try {
            imageUrl = new URL(imageUrlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, "Errore sull'url dell'immagine");
        }
        Element prezzo = div.select("s.discount-price").first();
        String prezzoStr = StringEscapeUtils.unescapeHtml(prezzo.text());
        String titleStr = StringEscapeUtils.unescapeHtml(img.attr("alt"));
        //System.err.println(titleStr);
        Element linkItem = div.select("a").first();
        String linkItemStr = linkItem.absUrl("href");
        //creo il post da template html
        PostCreator creator = new PostCreator(imageUrlStr, prezzoStr, titleStr, linkItemStr,SHOP_NAME,LOGO_URL);
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
        
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://www.groupon.it/goods?category=articoli-di-elettronica").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("figure.deal-card.deal-list-tile.deal-tile.deal-tile-standard");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Informatica.getValue()});
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.Informatica.getValue()));
//                    System.err.println("**************");
//                    System.err.println(figure.html());
//                    System.err.println("**************");
            }
            //System.err.println("SIZE "+listaItems.size());
//            for (Post item : listaItems) {
//                System.err.println(item.toString());
//            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
        
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://www.groupon.it/goods/prodotti-salute-e-bellezza").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("figure.deal-card.deal-list-tile.deal-tile.deal-tile-standard");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Bellezza.getValue()});
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.Bellezza.getValue()));
//                    System.err.println("**************");
//                    System.err.println(figure.html());
//                    System.err.println("**************");
            }
            //System.err.println("SIZE "+listaItems.size());
//            for (Post item : listaItems) {
//                System.err.println(item.toString());
//            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
        
    }

    @Override
    public List<Post> getCasa() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://www.groupon.it/goods/articoli-per-la-casa").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("figure.deal-card.deal-list-tile.deal-tile.deal-tile-standard");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Casa.getValue()});
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.Casa.getValue()));
//                    System.err.println("**************");
//                    System.err.println(figure.html());
//                    System.err.println("**************");
            }
            //System.err.println("SIZE "+listaItems.size());
//            for (Post item : listaItems) {
//                System.err.println(item.toString());
//            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
        
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://www.groupon.it/goods/auto-e-manutenzione-casa").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("figure.deal-card.deal-list-tile.deal-tile.deal-tile-standard");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.FaiDaTe.getValue()});
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.FaiDaTe.getValue()));
//                    System.err.println("**************");
//                    System.err.println(figure.html());
//                    System.err.println("**************");
            }
            //System.err.println("SIZE "+listaItems.size());
//            for (Post item : listaItems) {
//                System.err.println(item.toString());
//            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://www.groupon.it/goods/gioielli-e-orologi").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("figure.deal-card.deal-list-tile.deal-tile.deal-tile-standard");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Gioielli.getValue()});
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.Gioielli.getValue()));
//                    System.err.println("**************");
//                    System.err.println(figure.html());
//                    System.err.println("**************");
            }
            //System.err.println("SIZE "+listaItems.size());
//            for (Post item : listaItems) {
//                System.err.println(item.toString());
//            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://www.groupon.it/goods/articoli-sportivi-attivita-all-aperto").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("figure.deal-card.deal-list-tile.deal-tile.deal-tile-standard");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Sport.getValue()});
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.Sport.getValue()));
//                    System.err.println("**************");
//                    System.err.println(figure.html());
//                    System.err.println("**************");
            }
            //System.err.println("SIZE "+listaItems.size());
//            for (Post item : listaItems) {
//                System.err.println(item.toString());
//            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
        
    }

    @Override
    public List<Post> getStrumentiMusicaliDj() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getAbbigliamento() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getViaggi() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            //spa
            doc = Jsoup.connect("https://www.groupon.it/occasion/spa_wellness").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("figure.deal-card");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Viaggi.getValue()});
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModelViaggi(figure,Categories.T_Categories.Viaggi.getValue()));
            }
            //viaggi con volo
            doc = Jsoup.connect("https://www.groupon.it/occasion/travel_deals_flights").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figuresEst = doc.select("figure.deal-card");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figuresEst.size(), Categories.T_Categories.Viaggi.getValue()});
            for (Element figure : figuresEst) {
                    listaItemsTemp.add(creaModelViaggi(figure,Categories.T_Categories.Viaggi.getValue()));
            }
            //lowcost
            doc = Jsoup.connect("https://www.groupon.it/occasion/low_cost_travel").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figuresLow = doc.select("figure.deal-card");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figuresLow.size(), Categories.T_Categories.Viaggi.getValue()});
            for (Element figure : figuresLow) {
                    listaItemsTemp.add(creaModelViaggi(figure,Categories.T_Categories.Viaggi.getValue()));
            }
            //luxury
            doc = Jsoup.connect("https://www.groupon.it/occasion/travel-premium-deals").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figuresLux = doc.select("figure.deal-card");
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figuresLux.size(), Categories.T_Categories.Viaggi.getValue()});
            for (Element figure : figuresLux) {
                    listaItemsTemp.add(creaModelViaggi(figure,Categories.T_Categories.Viaggi.getValue()));
            }
            //tempo libero
//            doc = Jsoup.connect("https://www.groupon.it/browse/roma?lat=41.87194&lng=12.567379999999957&address=Italia&locale=it_IT").userAgent("Mozilla").get();
//            System.err.println(doc.html());
//            Elements divTempLib = doc.select("cui-image-lazy-container");
//            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divTempLib.size(), Categories.T_Categories.Viaggi.getValue()});
//            for (Element figure : divTempLib) {
//                    listaItemsTemp.add(creaModelViaggi(figure,Categories.T_Categories.Viaggi.getValue()));
//            }
            
        
        } catch (IOException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaItemsTemp;
    }
    
    private Post creaModelViaggi (Element div, String categoria){
        Element img = div.select("img").first();
        String style = img.attr("style");
        String imageUrlStr = "";
        try{
            imageUrlStr = style.substring(style.indexOf("(")+1,style.lastIndexOf(")"));
        } catch (StringIndexOutOfBoundsException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, "url non presente nello style dell'immagine", "");
        }
        
        URL imageUrl = null;
        try {
            if (null != imageUrlStr && !imageUrlStr.equals("")){
                imageUrl = new URL(imageUrlStr);
            }else{
                imageUrl = new URL(LOGO_URL);

            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlerGroupon.class.getName()).log(Level.SEVERE, null, ex);
        }
        Element prezzo = div.select("span.current-price").first();
        String prezzoStr = StringEscapeUtils.unescapeHtml(prezzo.text());
        String titleStr = StringEscapeUtils.unescapeHtml(img.attr("alt"));
        //System.err.println(titleStr);
        Element linkItem = div.select("a").first();
        String linkItemStr = linkItem.absUrl("href");
        //creo il post da template html
        PostCreator creator = new PostCreator(imageUrlStr, prezzoStr, titleStr, linkItemStr,SHOP_NAME,LOGO_URL);
        //creo l'oggetto post da inserire su wordpress
        Post post = new Post(titleStr, creator.getPost(), imageUrl, SHOP_NAME);
        //setto la categoria del post appena creato
        List<String> categories = new ArrayList<>();
        categories.add(categoria);
        post.setCategories(categories);
        
        return post;
    }
}
