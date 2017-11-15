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
public class CrawlerMondadori extends AbstractCrawler{
    
    private static final String SHOP_NAME = "Mondadori";
    private static final String LOGO_URL = "http://www.offertelowcost.net/staticfiles/img/mondadori.png";
    private List<Post> listaItems;

    public CrawlerMondadori() {
    }
    
    
    private Post creaModel (Element div, String categoria){
        Element img = div.select("img").first();
        String imageUrlStr = img.absUrl("src");
         URL imageUrl = null;
        try {
            imageUrl = new URL(imageUrlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.SEVERE, null, ex);
        }
        Element prezzo = div.select("h4.title").first();
        String prezzoStr = StringEscapeUtils.escapeHtml(img.attr("alt"));
        Element title = div.select("p.text").first();
        String titleStr = StringEscapeUtils.escapeHtml(img.attr("alt"));
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("http://www.mondadoristore.it/cinema/dvd/inMondadori-Promo-Cinema/prm401/").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("div.single-box");
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.FilmETv.getValue()});
            
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.FilmETv.getValue()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("http://www.mondadoristore.it/libri/italiani/PROMOZIONI-LIBRI/prm40/").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("div.single-box");
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Libri.getValue()});
            
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.Libri.getValue()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            doc = Jsoup.connect("http://www.mondadoristore.it/ebook/italiani/PROMOZIONI-IN-CORSO/prm382/").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("div.single-box");
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Libri.getValue()});
            
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.Libri.getValue()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaItemsTemp;
    }

    @Override
    public List<Post> getMusica() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        try {
            doc = Jsoup.connect("http://www.mondadoristore.it/musica/cd/inMondadori-Promo-Musica/prm386/").userAgent("Mozilla").get();
            //System.err.println(doc.html());
            Elements figures = doc.select("div.single-box");
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{figures.size(), Categories.T_Categories.Musica.getValue()});
            
            for (Element figure : figures) {
                    listaItemsTemp.add(creaModel(figure,Categories.T_Categories.Musica.getValue()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(CrawlerMondadori.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
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
        return new ArrayList<>();
    }

    @Override
    public List<Post> getViaggi() {
        return new ArrayList<>();
    }
}
