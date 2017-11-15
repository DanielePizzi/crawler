/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author dgasperi
 */
public class PostCreator {
    
    private static final Logger LOGGER = Logger.getLogger(PostCreator.class.getName());
    private String post;
    private String imageUrl;
    private String prezzo;
    private String title;
    private String itemUrl;
    private String itemShop;
    private String itemImgUrl;
    
    public PostCreator() {
    }

    public PostCreator(String imageUrl, String prezzo, String title, String itemUrl,String itemShop, String itemImgUrl) {
        creaPostDaTemplate(imageUrl,prezzo,title,itemUrl,itemShop,itemImgUrl);
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(String prezzo) {
        this.prezzo = prezzo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemShop() {
        return itemShop;
    }

    public void setItemShop(String itemShop) {
        this.itemShop = itemShop;
    }

    public String getItemImgUrl() {
        return itemImgUrl;
    }

    public void setItemImgUrl(String itemImgUrl) {
        this.itemImgUrl = itemImgUrl;
    }
    
    
    
    
    
    private void creaPostDaTemplate(String imageUrl, String prezzo, String title, String itemUrl,String itemShop, String itemImgUrl){
        
        Date data= new Date();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        String dtStr = sf.format(data);
        ClassLoader classLoader = getClass().getClassLoader();

        File input;
        //LOGGER.info("Leggo il file dal path /mnt/library/Crawler");
        input = new File("/mnt/library/Crawler/templates/template.html");
        if(!input.canRead()){
            //LOGGER.info("Leggo il file dal path locale");
            input = new File(classLoader.getResource("template.html").getFile());

        }
        try {
            Document doc = Jsoup.parse(input, "UTF-8");
            Element img = doc.getElementById("item_img");
            
            Element subTitle = doc.getElementById("sub_title");
            subTitle.html(title);
            
            img.attr("src", imageUrl);
            img.attr("alt",StringEscapeUtils.escapeHtml(title));
            Element descr = doc.getElementById("desc_item");
            descr.html(StringEscapeUtils.escapeHtml(title));
            Element price = doc.getElementById("item_price");
            price.html(prezzo);
            Element itemLink = doc.getElementById("item_link");
            itemLink.attr("href", itemUrl);
            Element shopImg = doc.getElementById("shop_img");
            shopImg.attr("alt", itemShop);
            shopImg.attr("src", itemImgUrl);
            Element descrItemShop1 = doc.getElementById("desc_item_shop1");
            descrItemShop1.html(itemShop);
            Element descrItemShop2 = doc.getElementById("desc_item_shop2");
            descrItemShop2.html(itemShop);
            Element descrItemShop3 = doc.getElementById("desc_item_shop3");
            descrItemShop3.html(itemShop);
            Element descrItemName = doc.getElementById("desc_item_name");
            descrItemName.html(StringEscapeUtils.escapeHtml(title));
            Element descrDate1 = doc.getElementById("date1");
            descrDate1.html(dtStr);
            Element descrDate2 = doc.getElementById("date2");
            descrDate2.html(dtStr);
            Element descrItemprice = doc.getElementById("item_price2");
            descrItemprice.html(prezzo);
            Element descrItemLink = doc.getElementById("item_link_desc");
            descrItemLink.attr("href", itemUrl);
            Element descrItemUrl = doc.getElementById("desc_item_url");
            descrItemUrl.html( itemUrl);
            
            setPost(doc.html());
            
        } catch (IOException ex) {
            Logger.getLogger(PostCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
