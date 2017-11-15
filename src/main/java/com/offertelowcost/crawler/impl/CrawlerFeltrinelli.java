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
public class CrawlerFeltrinelli extends AbstractCrawler {

    private static final String SHOP_NAME = "Feltrinelli";
    private static final String LOGO_URL = "http://www.offertelowcost.net/staticfiles/img/feltrinelli.png";

    

    private static Post creaModelDvd(Element div, String categoryName) {
        Element img = div.select("img").first();
        String imageUrlStr = img.absUrl("src");
        URL imageUrl = null;
        try {
            imageUrl = new URL(imageUrlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.SEVERE, null, ex);
        }

        String titleStr = img.attr("alt");
        Element prezzo = div.select("div.add-to-cart").first().getElementsByTag("strong").get(0);
        String prezzoStr;
        if(null == prezzo){
            prezzoStr = titleStr;
        }else{
            prezzoStr = prezzo.html();
        }
        //System.err.println(titleStr);
        Element linkItem = div.select("a").first();
        String linkItemStr = linkItem.absUrl("href");
        //System.err.println(linkItemStr);

        PostCreator creator = new PostCreator(imageUrlStr.trim(), prezzoStr.trim(), titleStr.trim(), linkItemStr.trim(), SHOP_NAME,LOGO_URL);
//        System.err.println(creator.getPost());
        Post post = new Post(titleStr, creator.getPost(), imageUrl, SHOP_NAME);
        List<String> categories = new ArrayList<>();
        categories.add(categoryName);
        post.setCategories(categories);
        //System.err.println(post.toString());
        return post;
    }
    private static Post creaModel(Element div, String categoryName) {
        Element img = div.select("img").first();
        String imageUrlStr = img.absUrl("src");
        URL imageUrl = null;
        try {
            imageUrl = new URL(imageUrlStr);
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.SEVERE, null, ex);
        }

        String titleStr = img.attr("alt");
        Element prezzo = div.select("div.add-to-cart").first().getElementsByTag("strong").get(0);
        String prezzoStr;
        if(null == prezzo){
            prezzoStr = titleStr;
        }else{
            prezzoStr = prezzo.html();
        }
        //System.err.println(titleStr);
        Element linkItem = div.select("a").first();
        String linkItemStr = linkItem.absUrl("href");
        //System.err.println(linkItemStr);

        PostCreator creator = new PostCreator(imageUrlStr.trim(), prezzoStr.trim(), titleStr.trim(), linkItemStr.trim(), SHOP_NAME,LOGO_URL);
        //setto il link al prodotto
        Post post = new Post(titleStr, creator.getPost(), imageUrl, SHOP_NAME);
        post.setItemUrl(linkItemStr);
        List<String> categories = new ArrayList<>();
        categories.add(categoryName);
        post.setCategories(categories);
        //System.err.println(post.toString());
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
        
        Document doc;
        List<Post> listaItems = new ArrayList<>();
        try {

            doc = Jsoup.connect("http://www.lafeltrinelli.it/cinema-film/c-416/0/1/").get();
            //System.err.println(doc.html());
            Elements divs = doc.select("div.block-list-item.list-with-btns.block.video.home.video");
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), Categories.T_Categories.CdDvd.getValue()});

            for (Element div : divs) {
                listaItems.add(creaModelDvd(div, Categories.T_Categories.CdDvd.getValue()));
            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.SEVERE, "Errore connessione CD DVD");
        }
        return listaItems;
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
        Document doc;
        List<Post> listaItems = new ArrayList<>();
        try {

            doc = Jsoup.connect("http://www.lafeltrinelli.it/libri/c-1/0/1/").get();
//            System.err.println(doc.html());
            Elements divs = doc.select("div.block.libri");
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), Categories.T_Categories.Libri.getValue()});
            //utilizzata per creare un numero massimo di blog post per i libri
            int count = 0;
            for (Element div : divs) {
                Post post = creaModel(div, Categories.T_Categories.Libri.getValue());
                listaItems.add(post);
                if(count <= 10){
                    try{
                        Post blogPost = creaBlogPost(post);
                        listaItems.add(blogPost);
                    }catch (NullPointerException ex) {
                       Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.SEVERE, null, "Manca description salto il post...");
                    }
                }
                count++;
            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.SEVERE, "Errore connessione libri");
        }
        return listaItems;
    }

    @Override
    public List<Post> getMusica() {
        
        Document doc;
        List<Post> listaItems = new ArrayList<>();
        try {

            doc = Jsoup.connect("http://www.lafeltrinelli.it/musica/c-3/0/1/").get();
//            System.err.println(doc.html());
            Elements divs = doc.select("div.block-list-item.list-with-btns.block.music.musica");
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), Categories.T_Categories.Musica.getValue()});

            for (Element div : divs) {
                listaItems.add(creaModel(div, Categories.T_Categories.Musica.getValue()));
            }
        } catch (IOException ex) {
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.SEVERE, "Errore connessione");
        }
        return listaItems;
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
    
    public Post creaBlogPost (Post post){
        Document doc;
        String longDescr="";
        
        try {
           doc = Jsoup.connect(post.getItemUrl()).maxBodySize(0).get();
           longDescr = doc.select("div.block-content").select("p").first().html();
           //System.err.println(longDescr);

        } catch (IOException ex) {
            Logger.getLogger(CrawlerFeltrinelli.class.getName()).log(Level.SEVERE, null, ex);
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
