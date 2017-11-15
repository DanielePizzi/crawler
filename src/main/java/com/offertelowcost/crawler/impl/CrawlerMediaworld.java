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
 * @author FELEFANT
 */
public class CrawlerMediaworld extends AbstractCrawler {

    private static final String SHOP_NAME = "Mediaworld";
    private static final String LOGO_URL = "http://www.offertelowcost.net/staticfiles/img/mediaworld.png";
    private static final Logger LOGGER = Logger.getLogger(CrawlerMediaworld.class.getName());

    private Post creaModel(Element div, String categoria) {

        // recupero immagine
        Element img = div.select("div.product_image").select("img").last();
        String imageUrlStr = img.absUrl("src");

        URL imageUrl = null;
        try {
            imageUrl = new URL(imageUrlStr);
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        // recupero prezzo
        String prezzoStr = "";

        List<Element> prezzoList = div.select("div.offerprice").select("div.price").first().children();
        for (Element element : prezzoList) {

            if ("".equals(element.html())) {
                prezzoStr += ",";
            } else {
                prezzoStr += element.html();
            }
        }

        prezzoStr += " €";

        // recupero titolo
        Element productElement = div.select("div.product_name").select("a").first();
        String titleStr = productElement.html();

        // recupero link al prodotto
        Element linkItem = productElement;
        String linkItemStr = linkItem.absUrl("href");

        // creo il post da template html
        PostCreator creator = new PostCreator(imageUrlStr, prezzoStr, titleStr, linkItemStr, SHOP_NAME, LOGO_URL);

        // creo l'oggetto post da inserire su wordpress
        Post post = new Post(titleStr, creator.getPost(), imageUrl, SHOP_NAME);

        // setto la categoria del post appena creato
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        String categoriaPrincipale = Categories.T_Categories.VideogiochiConsole.getValue();
        try {

            doc = Jsoup.connect("https://www.mediaworld.it/mw/giochi-pc-e-mac").maxBodySize(0).get();

            Elements divs = doc.select("div.product");
            LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), categoriaPrincipale});

            for (int i = 0; i < divs.size(); i++) {
                Element div = divs.get(i);
                // il primo che trova non e' valido
                if (i != 0) {
                    listaItemsTemp.add(creaModel(div, categoriaPrincipale));
                }
            }

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return listaItemsTemp;
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

        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        String categoriaPrincipale = Categories.T_Categories.Musica.getValue();
        try {

            doc = Jsoup.connect("https://www.mediaworld.it/mw/musica").maxBodySize(0).get();

            Elements divs = doc.select("div.product");
            LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{divs.size(), categoriaPrincipale});

            for (int i = 0; i < divs.size(); i++) {
                Element div = divs.get(i);
                // il primo che trova non e' valido
                if (i != 0) {
                    listaItemsTemp.add(creaModel(div, categoriaPrincipale));
                }
            }

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
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
