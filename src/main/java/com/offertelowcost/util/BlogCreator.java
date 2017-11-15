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
public class BlogCreator {
    
    private static final Logger LOGGER = Logger.getLogger(BlogCreator.class.getName());
    private String post;
    private String imageUrl;
    private String title;
    private String itemUrl;
    private String itemShop;
    private String itemImgUrl;
    private String blogDescr;
    
    public BlogCreator() {
    }

    public BlogCreator(String imageUrl, String title, String itemUrl,String itemShop, String itemImgUrl, String blogDescr) {
        creaPostDaTemplate(imageUrl,title,itemUrl,itemShop,itemImgUrl,blogDescr);
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

    public String getBlogDescr() {
        return blogDescr;
    }

    public void setBlogDescr(String blogDescr) {
        this.blogDescr = blogDescr;
    }
    
    
    
    
    
    private void creaPostDaTemplate(String imageUrl, String title, String itemUrl,String itemShop, String itemImgUrl, String blogDescr){
        
        Date data= new Date();
        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
        String dtStr = sf.format(data);
        ClassLoader classLoader = getClass().getClassLoader();

        File input;
        //LOGGER.info("Leggo il file dal path /mnt/library/Crawler");
        input = new File("/mnt/library/Crawler/templates/templateBlog.html");
        if(!input.canRead()){
            //LOGGER.info("Leggo il file dal path locale");
            input = new File(classLoader.getResource("templateBlog.html").getFile());

        }
        try {
            Document doc = Jsoup.parse(input, "UTF-8");
            Element img = doc.getElementById("item_img");
            img.attr("src", imageUrl);
            img.attr("alt",StringEscapeUtils.escapeHtml(title));
            

            Element itemLink = doc.getElementById("item_link");
            itemLink.attr("href", itemUrl);
            Element shopImg = doc.getElementById("shop_img");
            shopImg.attr("alt", itemShop);
            shopImg.attr("src", itemImgUrl);
//            Element descrItemShop1 = doc.getElementById("desc_item_shop1");
//            descrItemShop1.html(itemShop);
            Element descrItemShop2 = doc.getElementById("desc_item_shop2");
            descrItemShop2.html(itemShop);
            Element descrItemShop3 = doc.getElementById("desc_item_shop3");
            descrItemShop3.html(itemShop);
//            Element descrItemName = doc.getElementById("desc_item_name");
//            descrItemName.html(StringEscapeUtils.escapeHtml(title));
//            Element descrDate1 = doc.getElementById("date1");
//            descrDate1.html(dtStr);
//            Element descrDate2 = doc.getElementById("date2");
//            descrDate2.html(dtStr);
//            Element descrItemprice = doc.getElementById("item_price2");
//            descrItemprice.html(prezzo);
            Element descrItemLink = doc.getElementById("item_link_desc");
            descrItemLink.attr("href", itemUrl);
            Element descrItemUrl = doc.getElementById("desc_item_url");
            descrItemUrl.html(itemUrl);
            Element descrItemLong = doc.getElementById("desc_item_long");
            descrItemLong.html(blogDescr);
            Element descr1 = doc.getElementById("desc_item1");
            descr1.html(StringEscapeUtils.escapeHtml(title));
            Element descr2 = doc.getElementById("desc_item2");
            descr2.html(StringEscapeUtils.escapeHtml(title));
            Element descr3 = doc.getElementById("desc_item3");
            descr3.html(StringEscapeUtils.escapeHtml(title));
//            Element descr4 = doc.getElementById("desc_item4");
//            descr4.html(StringEscapeUtils.escapeHtml(title));
            
            setPost(doc.html());
            
        } catch (IOException ex) {
            Logger.getLogger(BlogCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
