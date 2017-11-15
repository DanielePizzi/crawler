/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.crawler.impl;

import com.offertelowcost.crawler.interfaces.AbstractCrawler;
import com.offertelowcost.model.Post;
import com.offertelowcost.util.BlogCreator;
import com.offertelowcost.util.Categories;
import com.offertelowcost.util.Categories.T_Categories;
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
public class CrawlerEbay extends AbstractCrawler{
    
    private static final String SHOP_NAME = "Ebay";
    private static final String LOGO_URL = "http://www.offertelowcost.net/staticfiles/img/ebay.png";
    private static final String PREFIX = "http://rover.ebay.com/rover/1/724-53478-19255-0/1?ff3=4&pub=5575162801&toolid=10001&campid=5337845122&customid=";
    private static final String PRELINK = "&mpre=";
    private static final Logger LOGGER = Logger.getLogger(CrawlerEbay.class.getName());

    public List<Post> creaListaPost (String link, List<Post> listaItemsOriginal, String categoria){
       
        Document doc;
        
        try {
                //primo piano
                doc = Jsoup.connect(link).maxBodySize(0).userAgent("Mozilla").get();
//                System.err.println(doc.html());
                Elements divs = doc.select("div.s-item__wrapper.clearfix");
                for (Element div : divs) {
                        Post post = creaModel(div,categoria);
                        listaItemsOriginal.add(post);
//                        Post blogPost = creaBlogPost(post);
//                        if(null != blogPost){
//                            listaItemsOriginal.add(blogPost);
//                        }
//                        System.err.println("**************");
//                        System.err.println(div.html());
//                        System.err.println("**************");
                }
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "getInformatica");
            }
            
            return listaItemsOriginal;
    }
    
    private Post creaModel (Element div, String categoria){
        //System.err.println(div.html());
        Element img = div.select("img").first();
        String imageUrlStr = img.absUrl("src");
        
        //recupero il titolo dell'item
        Elements title = div.select("h3.s-item__title");
        String titleStr = title.html();
        
        URL imageUrl = null;
        try {
            imageUrl = new URL(imageUrlStr);
        } catch (MalformedURLException ex) {
            //Logger.getLogger(CrawlerEbay.class.getName()).log(Level.SEVERE, "Sostituisco l'immagine", "Sostituisco l'immagine");
            imageUrlStr = img.absUrl("src");
            try {
                imageUrl = new URL(imageUrlStr);
            } catch (MalformedURLException ex1) {
                //Logger.getLogger(CrawlerEbay.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        Elements prezzo = div.getElementsByAttributeValue("itemprop", "price");
        
        String prezzoStr = prezzo != null ? prezzo.html() : "-";
        
        //System.err.println(titleStr);
        //recupero il link dell'item
        Element linkItem = div.select("a").first();
        String linkItemStr = linkItem.absUrl("href");
        //System.err.println(imageUrl);
        //creo il post da template html
        PostCreator creator = new PostCreator(imageUrlStr, prezzoStr, titleStr, creaLinkAffiliato(categoria, linkItemStr),SHOP_NAME,LOGO_URL);
        //System.err.println(creator.getPost());
        //creo l'oggetto post da inserire su wordpress
        Post post = new Post(titleStr, creator.getPost(), imageUrl, SHOP_NAME);
        //setto il link al prodotto
        post.setItemUrl(linkItemStr);
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
        
        //HDD
        creaListaPost ("https://www.ebay.it/b/Dispositivi-per-larchiviazione-di-dati-per-prodotti-informatici/165/bn_16546741", listaItemsTemp, T_Categories.Informatica.getValue());
		//NOTEBOOK
		creaListaPost ("https://www.ebay.it/b/Computer-portatili-e-notebook/175672/bn_16546646", listaItemsTemp, T_Categories.Informatica.getValue());
		//DESKTOP
		creaListaPost ("https://www.ebay.it/b/Computer-desktop/171957/bn_16546649", listaItemsTemp, T_Categories.Informatica.getValue());
		//COMPNENTI
		creaListaPost ("https://www.ebay.it/b/Componenti-e-parti-per-prodotti-informatici/175673/bn_16546704", listaItemsTemp, T_Categories.Informatica.getValue());
		//STAMPANTI
		creaListaPost ("https://www.ebay.it/b/Stampanti-scanner-e-forniture/171961/bn_165468271", listaItemsTemp, T_Categories.Informatica.getValue());
		//APPLE
		creaListaPost ("https://www.ebay.it/b/Laptop-Apple/111422/bn_16546647", listaItemsTemp, T_Categories.Informatica.getValue());
		//MONITOR
		creaListaPost ("https://www.ebay.it/b/Monitor-e-accessori/162497/bn_16583380", listaItemsTemp, T_Categories.Informatica.getValue());
		//TASTIER E MOUSE
		creaListaPost ("https://www.ebay.it/b/Tastiere-mouse-e-puntatori/3676/bn_16546849", listaItemsTemp, T_Categories.Informatica.getValue());
		
		 Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), T_Categories.Informatica.getValue()});

        return listaItemsTemp;
    }

    @Override
    public List<Post> getAppGiochi() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getAutoMoto() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        //AUTO
        creaListaPost ("https://www.ebay.it/b/Ricambi-per-auto/6030/bn_16548134", listaItemsTemp, T_Categories.AutoMoto.getValue());
		//MOTO
		creaListaPost ("https://www.ebay.it/b/Ricambi-per-moto/10063/bn_16548755", listaItemsTemp, T_Categories.AutoMoto.getValue());
        
		Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), T_Categories.AutoMoto.getValue()});

        return listaItemsTemp;
    }

    @Override
    public List<Post> getVideogiochiConsole() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
//        try {
//            doc = Jsoup.connect("http://www.ebay.it/rpp/deals/elettronica/videogiochi-e-console").maxBodySize(0).userAgent("Mozilla").get();
//        
////            System.err.println(doc.html());
////            System.err.println("**************************************");
//            Elements divs = doc.getElementsByAttributeValue("itemtype", "http://schema.org/Product");
//            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), T_Categories.VideogiochiConsole.getValue()});
//            for (Element div : divs) {
////                    System.err.println(div.html());
//                    listaItemsTemp.add(creaModel(div,T_Categories.VideogiochiConsole.getValue()));
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return listaItemsTemp;
    }

    @Override
    public List<Post> getBellezza() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getCasa() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
        //BRICOLAGE
        creaListaPost ("https://www.ebay.it/b/Articoli-per-il-bricolage-e-fai-da-te/631/bn_16582490", listaItemsTemp, T_Categories.Casa.getValue());
		//DECORAZIONE
		creaListaPost ("https://www.ebay.it/b/Articoli-per-la-decorazione-della-casa/10033/bn_16545012", listaItemsTemp, T_Categories.Casa.getValue());
        
		Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), T_Categories.Casa.getValue()});

       
        
        return listaItemsTemp;
    }

    @Override
    public List<Post> getFotografiaVideo() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getElettronica() {
    	
    	Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
        //SMARTPHONE
        creaListaPost ("https://www.ebay.it/b/Cellulari-e-smartphone/9355/bn_16547931", listaItemsTemp, T_Categories.Elettronica.getValue());
		//SMNARTWATCH
		creaListaPost ("https://www.ebay.it/b/Smartwatch/178893/bn_16547938", listaItemsTemp, T_Categories.Elettronica.getValue());
        
		Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), T_Categories.Elettronica.getValue()});
    	
        return listaItemsTemp;
    }

    @Override
    public List<Post> getCdDvd() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getFaiDaTe() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
//        try {
//
//            doc = Jsoup.connect("http://www.ebay.it/rpp/Campagne/Bricolage").maxBodySize(0).userAgent("Mozilla").get();
////            System.err.println(doc.html());
////            System.err.println("**************************************");
//            Elements divs = doc.getElementsByAttributeValue("itemtype", "http://schema.org/Product");
//            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), T_Categories.FaiDaTe.getValue()});
//            for (Element div : divs) {
//                    listaItemsTemp.add(creaModel(div,T_Categories.FaiDaTe.getValue()));
//                    //System.err.println(div.html());
//            }
//            //System.err.println("****************** "+divs.size());
//        } catch (IOException ex) {
//            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.SEVERE, null, ex);
//        }
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

        //OROLOGI
		creaListaPost ("https://www.ebay.it/b/Orologi-da-polso/31387/bn_16547256", listaItemsTemp, T_Categories.Gioielli.getValue());
		//BIGIOTTERIA
		creaListaPost ("https://www.ebay.it/b/Bigiotteria/10968/bn_16547048", listaItemsTemp, T_Categories.Gioielli.getValue());
		//GIOIELLI DI LUSSO
		creaListaPost ("https://www.ebay.it/b/Gioielli-di-lusso/4196/bn_16547085", listaItemsTemp, T_Categories.Gioielli.getValue());
		//ANELLI
		creaListaPost ("https://www.ebay.it/b/Anelli-di-bigiotteria/67681/bn_16547049", listaItemsTemp, T_Categories.Gioielli.getValue());
		
		Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), T_Categories.Gioielli.getValue()});
        
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
        //UOMO
  		creaListaPost ("https://www.ebay.it/b/Scarpe-da-uomo/93427/bn_16544296", listaItemsTemp, T_Categories.ScarpeBorse.getValue());
  		//DONNA
  		creaListaPost ("https://www.ebay.it/b/Scarpe-da-donna/3034/bn_16544254", listaItemsTemp, T_Categories.ScarpeBorse.getValue());
  		
  		Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), T_Categories.ScarpeBorse.getValue()});
        return listaItemsTemp;
    }

    @Override
    public List<Post> getSoftware() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getSport() {
        
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        //PESI
        creaListaPost ("https://www.ebay.it/b/Articoli-per-pesi-e-massa-muscolare/28066/bn_16547495", listaItemsTemp, T_Categories.Sport.getValue());
        //ABBIGLIAMENTO
        creaListaPost ("https://www.ebay.it/b/Abbigliamento-e-accessori-per-palestra-fitness-corsa-e-yoga/158913/bn_16547414", listaItemsTemp, T_Categories.Sport.getValue());
        return listaItemsTemp;
    }

    @Override
    public List<Post> getStrumentiMusicaliDj() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getAbbigliamento() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
//        try {
//            //uomo
//            doc = Jsoup.connect("http://www.ebay.it/rpp/deals/moda/accessori-uomo").maxBodySize(0).userAgent("Mozilla").get();
//        
////            System.err.println(doc.html());
////            System.err.println("**************************************");
//            Elements divs = doc.getElementsByAttributeValue("itemtype", "http://schema.org/Product");
//            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), T_Categories.Abbigliamento.getValue()});
//            for (Element div : divs) {
////                    System.err.println(div.html());
//                    listaItemsTemp.add(creaModel(div,T_Categories.Abbigliamento.getValue()));
//            }
//            //donna
//            doc = Jsoup.connect("http://www.ebay.it/rpp/deals/moda/borse-e-accessori-donna").maxBodySize(0).userAgent("Mozilla").get();
//        
////            System.err.println(doc.html());
////            System.err.println("**************************************");
//            divs = doc.getElementsByAttributeValue("itemtype", "http://schema.org/Product");
//            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), T_Categories.Abbigliamento.getValue()});
//            for (Element div : divs) {
////                    System.err.println(div.html());
//                    listaItemsTemp.add(creaModel(div,T_Categories.Abbigliamento.getValue()));
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return listaItemsTemp;
    }

    @Override
    public List<Post> getViaggi() {
        return new ArrayList<>();
    }
    
    private String creaLinkAffiliato (String categoria, String itemUrl){
        String catFormatted = categoria.replaceAll(" ", "_").toLowerCase(); 
        //System.err.println("LINK "+PREFIX+catFormatted+PRELINK+itemUrl);
        return PREFIX+catFormatted+PRELINK+itemUrl;
    }
    
    public Post creaBlogPost (Post post){
        Document doc;
        String longDescr="";
        
        try {
           doc = Jsoup.connect(post.getItemUrl()).maxBodySize(0).userAgent("Mozilla").get();
           longDescr = doc.getElementById("productDescription").html();

        } catch (IOException ex) {
            Logger.getLogger(CrawlerEbay.class.getName()).log(Level.SEVERE, "creaBlogPost");
        }
        //creo il post da template html
        BlogCreator blogCreator = new BlogCreator(post.getImageUrl().toString(),post.getPostTitle(), post.getItemUrl(),SHOP_NAME,LOGO_URL,StringEscapeUtils.unescapeHtml(longDescr));
        Post blogPost = new Post(post.getPostTitle(), blogCreator.getPost(), post.getImageUrl(), post.getPostShop());
        //categorizzazione
        List<String> categories = new ArrayList<>();
        categories.add(Categories.T_Categories.Blog.getValue());
        blogPost.setCategories(categories);
        
        return blogPost;
    }
}
