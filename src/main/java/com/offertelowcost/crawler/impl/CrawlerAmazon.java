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
public class CrawlerAmazon extends AbstractCrawler{

    private static final String SHOP_NAME = "Amazon";
    private static final String LOGO_URL = "http://www.offertelowcost.net/staticfiles/img/amazon.png";
    private static final String TRACKING_ID = "&tag=offlowcos-21";
    private static final Logger LOGGER = Logger.getLogger(CrawlerAmazon.class.getName());
    
    public List<Post> creaListaPost (String link, List<Post> listaItemsOriginal, String categoria){
       
        Document doc;
        
        try {
                //primo piano
                doc = Jsoup.connect(link).maxBodySize(0).userAgent("Mozilla").get();
//                System.err.println(doc.html());
                Elements divs = doc.select("div.s-item-container");
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
        Element img = div.select("img").first();
        String imageUrlStr = img.absUrl("src");
        URL imageUrl = null;
        try {
            imageUrl = new URL(imageUrlStr);
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, "ERRORE URL IMMAGINE creaModel");
        }
        Element prezzo = div.select("span.a-size-base.a-color-price.s-price.a-text-bold").first();
        String prezzoStr = "SCOPRILO SU AMAZON";
        if(null != prezzo){
            prezzoStr = StringEscapeUtils.unescapeHtml(prezzo.text());
        }
        
        Element linkItem = div.select("a").first();
        String linkItemStr = linkItem.absUrl("href")+TRACKING_ID;
        String titleStr = div.select("a.a-link-normal.s-access-detail-page.s-color-twister-title-link.a-text-normal").attr("title");
        //System.err.println("********** "+titleStr);
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
    
//    

    @Override
    public List<Post> getInformatica() {
        
        List<Post> listaItemsTemp = new ArrayList<>();
        //portatili
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=amb_link_JwgL4szZNmuN4KHmwBnzkA_1?ie=UTF8&node=460158031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-left-4&pf_rd_r=BF1GY6SQF9ZZHZE7APF6&pf_rd_r=BF1GY6SQF9ZZHZE7APF6&pf_rd_t=101&pf_rd_p=32ff094c-a9b5-454a-9a86-4d8e1dde5f3a&pf_rd_p=32ff094c-a9b5-454a-9a86-4d8e1dde5f3a&pf_rd_i=425916031", listaItemsTemp, Categories.T_Categories.Informatica.getValue());
        //Tablet
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=amb_link_CRVWgJYbNNG9pcm_dXhbEQ_2?ie=UTF8&node=460188031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-leftnav&pf_rd_r=R0RJFAVFCQTRA7BBVCCZ&pf_rd_r=R0RJFAVFCQTRA7BBVCCZ&pf_rd_t=101&pf_rd_p=ffcad56e-f8b5-4b18-9a42-dddf9e2a7e1f&pf_rd_p=ffcad56e-f8b5-4b18-9a42-dddf9e2a7e1f&pf_rd_i=460128031", listaItemsTemp, Categories.T_Categories.Informatica.getValue());
        //HDD
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=amb_link_CRVWgJYbNNG9pcm_dXhbEQ_5?ie=UTF8&node=460128031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-leftnav&pf_rd_r=R0RJFAVFCQTRA7BBVCCZ&pf_rd_r=R0RJFAVFCQTRA7BBVCCZ&pf_rd_t=101&pf_rd_p=ffcad56e-f8b5-4b18-9a42-dddf9e2a7e1f&pf_rd_p=ffcad56e-f8b5-4b18-9a42-dddf9e2a7e1f&pf_rd_i=460128031", listaItemsTemp, Categories.T_Categories.Informatica.getValue());
        //MONITOR
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=amb_link_CRVWgJYbNNG9pcm_dXhbEQ_6?ie=UTF8&node=460159031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-leftnav&pf_rd_r=R0RJFAVFCQTRA7BBVCCZ&pf_rd_r=R0RJFAVFCQTRA7BBVCCZ&pf_rd_t=101&pf_rd_p=ffcad56e-f8b5-4b18-9a42-dddf9e2a7e1f&pf_rd_p=ffcad56e-f8b5-4b18-9a42-dddf9e2a7e1f&pf_rd_i=460128031", listaItemsTemp, Categories.T_Categories.Informatica.getValue());

        LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), Categories.T_Categories.Informatica.getValue()});
        return listaItemsTemp;
    }

    @Override
    public List<Post> getAppGiochi() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getAutoMoto() {
        
       List<Post> listaItemsTemp = new ArrayList<>();
       //ACCESSORI AUTO
       listaItemsTemp = creaListaPost("https://www.amazon.it/Accessori/b/ref=amb_link_gFjGYkQwNTa4kk8WtyYDfA_1?ie=UTF8&node=2420687031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-left-2&pf_rd_r=6KGNM1E7GBM2A46XC7CA&pf_rd_r=6KGNM1E7GBM2A46XC7CA&pf_rd_t=101&pf_rd_p=a8b44f1f-097f-4430-bff8-e54ee686a89e&pf_rd_p=a8b44f1f-097f-4430-bff8-e54ee686a89e&pf_rd_i=1571280031", listaItemsTemp, Categories.T_Categories.AutoMoto.getValue());
       //ACCESSORI MOTO
       listaItemsTemp = creaListaPost("https://www.amazon.it/Accessorimoto/b/ref=amb_link_2?ie=UTF8&node=2420953031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-left-2&pf_rd_r=JK4P7S8XN80HTKBXQ0CW&pf_rd_r=JK4P7S8XN80HTKBXQ0CW&pf_rd_t=101&pf_rd_p=ff93de88-f1fa-4bc0-9765-e64a8249d276&pf_rd_p=ff93de88-f1fa-4bc0-9765-e64a8249d276&pf_rd_i=2420930031", listaItemsTemp, Categories.T_Categories.AutoMoto.getValue());
       
       LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), Categories.T_Categories.AutoMoto.getValue()}); 
       return listaItemsTemp;
    }

    @Override
    public List<Post> getVideogiochiConsole() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getBellezza() {
        List<Post> listaItemsTemp = new ArrayList<>();
       //PROFUMI
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_Beautyct_ct_1_h_w?_encoding=UTF8&node=6306898031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=NE1RY79MJNVKBWK7NJ4F&pf_rd_t=101&pf_rd_p=cfc94415-41b4-5d4c-b1d2-3143ce174e6e&pf_rd_i=6198082031", listaItemsTemp, Categories.T_Categories.Bellezza.getValue());
       //TRUCCO
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_Beautyct_ct_2_h_w?_encoding=UTF8&node=6306900031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=NE1RY79MJNVKBWK7NJ4F&pf_rd_t=101&pf_rd_p=cfc94415-41b4-5d4c-b1d2-3143ce174e6e&pf_rd_i=6198082031", listaItemsTemp, Categories.T_Categories.Bellezza.getValue());
       //CAPELLI
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_Beautyct_ct_3_h_w?_encoding=UTF8&node=4327902031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=NE1RY79MJNVKBWK7NJ4F&pf_rd_t=101&pf_rd_p=cfc94415-41b4-5d4c-b1d2-3143ce174e6e&pf_rd_i=6198082031", listaItemsTemp, Categories.T_Categories.Bellezza.getValue());
       //PELLE
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_Beautyct_ct_4_h_w?_encoding=UTF8&node=6306897031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=NE1RY79MJNVKBWK7NJ4F&pf_rd_t=101&pf_rd_p=cfc94415-41b4-5d4c-b1d2-3143ce174e6e&pf_rd_i=6198082031", listaItemsTemp, Categories.T_Categories.Bellezza.getValue());
       //MANICURE
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_Beautyct_ct_5_h_w?_encoding=UTF8&node=6306899031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=NE1RY79MJNVKBWK7NJ4F&pf_rd_t=101&pf_rd_p=cfc94415-41b4-5d4c-b1d2-3143ce174e6e&pf_rd_i=6198082031", listaItemsTemp, Categories.T_Categories.Bellezza.getValue());
       //CORPO
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_Beautyct_ct_6_h_w?_encoding=UTF8&node=4327880031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=NE1RY79MJNVKBWK7NJ4F&pf_rd_t=101&pf_rd_p=cfc94415-41b4-5d4c-b1d2-3143ce174e6e&pf_rd_i=6198082031", listaItemsTemp, Categories.T_Categories.Bellezza.getValue());
        
       LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), Categories.T_Categories.Bellezza.getValue()});
       return listaItemsTemp;
    }

    @Override
    public List<Post> getCasa() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
       //ELETTRODOMESTICI
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acsd_hfnv_hd_bw_belukx_ct_x_ct01_w?_encoding=UTF8&node=3543494031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=RX7V078VN1K83JEW79CA&pf_rd_t=101&pf_rd_p=758e6ea5-2975-5963-92a1-0d67e2b561f2&pf_rd_i=602473031", listaItemsTemp, Categories.T_Categories.Casa.getValue());
       //TESSILE
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acsd_hfnv_hd_bw_bZSiFL_ct_x_ct01_w?_encoding=UTF8&node=731677031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=Y7D1ACPSY7NRK7DP6PDK&pf_rd_t=101&pf_rd_p=d22c39e0-1b8c-545f-9c8c-df488495761b&pf_rd_i=524015031", listaItemsTemp, Categories.T_Categories.Casa.getValue());
       //ILLUMINAZIONE
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acsd_hfnv_hd_bw_bZSiFL_ct_x_ct02_w?_encoding=UTF8&node=1849538031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=EAKSSEQQAZ5D9T0A1A06&pf_rd_t=101&pf_rd_p=d22c39e0-1b8c-545f-9c8c-df488495761b&pf_rd_i=524015031", listaItemsTemp, Categories.T_Categories.Casa.getValue());
       //ARREDAMENTO
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acsd_hfnv_hd_bw_bZSiFL_ct_x_ct03_w?_encoding=UTF8&node=2808571031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=R86QJZKNBT9ATTBGM1SJ&pf_rd_t=101&pf_rd_p=d22c39e0-1b8c-545f-9c8c-df488495761b&pf_rd_i=524015031", listaItemsTemp, Categories.T_Categories.Casa.getValue());
       //HOBBY
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acsd_hfnv_hd_bw_bZSiFL_ct_x_ct05_w?_encoding=UTF8&node=4340026031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=KAYVBEBPT7W1C0AFE8AF&pf_rd_t=101&pf_rd_p=d22c39e0-1b8c-545f-9c8c-df488495761b&pf_rd_i=524015031", listaItemsTemp, Categories.T_Categories.Casa.getValue());
       //DECORAZIONE
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acsd_hfnv_hd_bw_bZSiFL_ct_x_ct06_w?_encoding=UTF8&node=731676031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=JS0WGK5RZFCWP9DQMVW5&pf_rd_t=101&pf_rd_p=d22c39e0-1b8c-545f-9c8c-df488495761b&pf_rd_i=524015031", listaItemsTemp, Categories.T_Categories.Casa.getValue());
       //ARREDAMENTO
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acsd_hfnv_hd_bw_bZSiFL_ct_x_ct09_w?_encoding=UTF8&node=3225815031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=JCNW1W1E47H2T4GWWHFS&pf_rd_t=101&pf_rd_p=d22c39e0-1b8c-545f-9c8c-df488495761b&pf_rd_i=524015031", listaItemsTemp, Categories.T_Categories.Casa.getValue());
       //CLIMATIZZAZIONE
       listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acsd_hfnv_hd_bw_bZSiFL_ct_x_ct10_w?_encoding=UTF8&node=3692884031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=8E6QB9N2K58RE7FXX3K4&pf_rd_t=101&pf_rd_p=d22c39e0-1b8c-545f-9c8c-df488495761b&pf_rd_i=524015031", listaItemsTemp, Categories.T_Categories.Casa.getValue());
        
       LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), Categories.T_Categories.Casa.getValue()});
       return listaItemsTemp;
        
    }

    @Override
    public List<Post> getFotografiaVideo() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getElettronica() {

        List<Post> listaItemsTemp = new ArrayList<>();
        //CELLULARI
        listaItemsTemp = creaListaPost("https://www.amazon.it/s/ref=s9_acss_bw_cg_itwinew_3a1_w?__mk_it_IT=%C5M%C5Z%D5%D1&rh=i%3Aelectronics%2Cn%3A412609031%2Cn%3A%21412610031%2Cn%3A1497228031%2Cn%3A473246031&bbn=473246031&sort=relevancerank&hidden-keywords=smartphone&ie=UTF8&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-12&pf_rd_r=AMMNG0P5CZ40S3TR3HES&pf_rd_t=101&pf_rd_p=ad978249-efe0-54b6-9810-fb080f91dafb&pf_rd_i=435507031", listaItemsTemp, Categories.T_Categories.Elettronica.getValue());
        //FOTOCAMERE
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_ab9pack_ct_2_h_w?_encoding=UTF8&ie=UTF8&node=473536031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=R8XD4MKY63H66YWYBKPR&pf_rd_t=101&pf_rd_p=719ba2ae-4fe6-5b54-b931-589981460b7a&pf_rd_i=435505031", listaItemsTemp, Categories.T_Categories.Elettronica.getValue());
        //TV
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_ab9pack_ct_1_h_w?_encoding=UTF8&ie=UTF8&node=473352031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=9BAHSH68X5BYC2KBKQPB&pf_rd_t=101&pf_rd_p=e008e27f-4c10-5bbf-8e58-ec4009792376&pf_rd_i=435504031", listaItemsTemp, Categories.T_Categories.Elettronica.getValue());
        
        LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), Categories.T_Categories.Elettronica.getValue()});
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
        
        //UTENSILI ELETTRICI
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_cattile2_ct_1_h_w?_encoding=UTF8&node=3119726031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=781G1X9ZT50R1NY11914&pf_rd_t=101&pf_rd_p=44aee138-345e-5dd2-8570-4baa58a675c4&pf_rd_i=2454160031", listaItemsTemp, Categories.T_Categories.FaiDaTe.getValue());
        //GIARDINAGGIO
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_cattile2_ct_2_h_w?_encoding=UTF8&node=731505031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=781G1X9ZT50R1NY11914&pf_rd_t=101&pf_rd_p=44aee138-345e-5dd2-8570-4baa58a675c4&pf_rd_i=2454160031", listaItemsTemp, Categories.T_Categories.FaiDaTe.getValue());
        //UTENSILI A MANO
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_cattile2_ct_3_h_w?_encoding=UTF8&node=3119725031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=781G1X9ZT50R1NY11914&pf_rd_t=101&pf_rd_p=44aee138-345e-5dd2-8570-4baa58a675c4&pf_rd_i=2454160031", listaItemsTemp, Categories.T_Categories.FaiDaTe.getValue());
        //BAGNO E CUCINA
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_cattile2_ct_5_h_w?_encoding=UTF8&node=3119607031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=781G1X9ZT50R1NY11914&pf_rd_t=101&pf_rd_p=44aee138-345e-5dd2-8570-4baa58a675c4&pf_rd_i=2454160031", listaItemsTemp, Categories.T_Categories.FaiDaTe.getValue());
        //MATERIALE ELETTRICO
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_cattile2_ct_6_h_w?_encoding=UTF8&node=3119611031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=781G1X9ZT50R1NY11914&pf_rd_t=101&pf_rd_p=44aee138-345e-5dd2-8570-4baa58a675c4&pf_rd_i=2454160031", listaItemsTemp, Categories.T_Categories.FaiDaTe.getValue());
        //FERRAMENTA
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_cattile2_ct_7_h_w?_encoding=UTF8&node=3119610031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=781G1X9ZT50R1NY11914&pf_rd_t=101&pf_rd_p=44aee138-345e-5dd2-8570-4baa58a675c4&pf_rd_i=2454160031", listaItemsTemp, Categories.T_Categories.FaiDaTe.getValue());
        //DECORAZIONE
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_cattile2_ct_8_h_w?_encoding=UTF8&node=3119613031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=781G1X9ZT50R1NY11914&pf_rd_t=101&pf_rd_p=44aee138-345e-5dd2-8570-4baa58a675c4&pf_rd_i=2454160031", listaItemsTemp, Categories.T_Categories.FaiDaTe.getValue());
        //DOMOTICA
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_ct_cattile2_ct_9_h_w?_encoding=UTF8&node=13979705031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-4&pf_rd_r=781G1X9ZT50R1NY11914&pf_rd_t=101&pf_rd_p=44aee138-345e-5dd2-8570-4baa58a675c4&pf_rd_i=2454160031", listaItemsTemp, Categories.T_Categories.FaiDaTe.getValue());
        
        LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), Categories.T_Categories.FaiDaTe.getValue()});
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
        
        //DONNA
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_cg_DGSS17_2a1_w?node=10435296031&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-4&pf_rd_r=NGPW5YZ958XRWRT3YHCS&pf_rd_t=101&pf_rd_p=2780a4b9-ad9d-4a2b-81ef-77a67478fd49&pf_rd_i=2454163031", listaItemsTemp, Categories.T_Categories.Gioielli.getValue());
        //UOMO
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_cg_DGSS17_2b1_w?node=10435325031&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-4&pf_rd_r=G462G7YWW5ZAQ5XJQSXX&pf_rd_t=101&pf_rd_p=2780a4b9-ad9d-4a2b-81ef-77a67478fd49&pf_rd_i=2454163031", listaItemsTemp, Categories.T_Categories.Gioielli.getValue());
        //BAMBINA
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_cg_DGSS17_2c1_w?node=10435257031&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-4&pf_rd_r=G462G7YWW5ZAQ5XJQSXX&pf_rd_t=101&pf_rd_p=2780a4b9-ad9d-4a2b-81ef-77a67478fd49&pf_rd_i=2454163031", listaItemsTemp, Categories.T_Categories.Gioielli.getValue());
        //BAMBINO
        listaItemsTemp = creaListaPost("https://www.amazon.it/b/ref=s9_acss_bw_cg_DGSS17_2d1_w?node=10435284031&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-4&pf_rd_r=G462G7YWW5ZAQ5XJQSXX&pf_rd_t=101&pf_rd_p=2780a4b9-ad9d-4a2b-81ef-77a67478fd49&pf_rd_i=2454163031", listaItemsTemp, Categories.T_Categories.Gioielli.getValue());
       
        LOGGER.log(Level.INFO, "Trovati {0} elementi in: {1}", new Object[]{listaItemsTemp.size(), Categories.T_Categories.Gioielli.getValue()});
        return listaItemsTemp;
        
    }

    @Override
    public List<Post> getLibri() {
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
//        try {
//            //primo piano
//            doc = Jsoup.connect("http://www.amazon.it/b?_encoding=UTF8&camp=3370&creative=23322&linkCode=ur2&node=411663031&site-redirect=&tag=offlowcos-21").maxBodySize(0).userAgent("Mozilla").get();
//            //System.err.println(doc.html());
//            Elements divs = doc.select("div.fluid.asin");
//            for (Element div : divs) {
//                    listaItemsTemp.add(creaModelLibri(div,Categories.T_Categories.Libri.getValue()));
////                    System.err.println("**************");
////                    System.err.println(div.html());
////                    System.err.println("**************");
//            }
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE, "getLibri");
//        }
        return listaItemsTemp;
        
    }

    @Override
    public List<Post> getMusica() {
        return new ArrayList<>();
    }

    @Override
    public List<Post> getScarpeBorse() {
        
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
//        try {
//            //donna
//            doc = Jsoup.connect("http://www.amazon.it/b/ref=ln_donna_shoe_stiv?ie=UTF8&lo=shoes&node=700815031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-left-2&pf_rd_r=0XNK1QRYKBPFF677X5TT&pf_rd_t=101&pf_rd_p=707631667&pf_rd_i=700766031").maxBodySize(0).userAgent("Mozilla").get();
//            //System.err.println(doc.html());
//            Elements divs = doc.select("div.s-item-container");
//            for (Element div : divs) {
//                    Post post = creaModelElett(div,Categories.T_Categories.ScarpeBorse.getValue());
//                    listaItemsTemp.add(post);
////                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
////                    }catch (NullPointerException ex) {
////                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
////                    }
//            }
//            //uomo
//            doc = Jsoup.connect("http://www.amazon.it/s/ref=sv_ap_arrow_2_2_1_6?rh=i%3Ashoes%2Cn%3A700704031&lo=shoes&rw_html_to_wsrp=1").maxBodySize(0).userAgent("Mozilla").get();
////            System.err.println(doc.html());
//            Elements divsUomo = doc.select("div.s-item-container");
//            for (Element div : divsUomo) {
//                    Post post = creaModelElett(div,Categories.T_Categories.ScarpeBorse.getValue());
//                    listaItemsTemp.add(post);
////                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
////                    }catch (NullPointerException ex) {
////                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
////                    }
//            }
//            //borse
//            doc = Jsoup.connect("http://www.amazon.it/s/ref=lp_524006031_nr_n_0?fst=as%3Aoff&rh=n%3A524006031%2Cn%3A!524007031%2Cn%3A700516031&bbn=524007031&ie=UTF8&qid=1461362250&rnid=524007031").maxBodySize(0).userAgent("Mozilla").get();
////            System.err.println(doc.html());
//            Elements divsBor = doc.select("div.s-item-container");
//            for (Element div : divsBor) {
//                    Post post = creaModelElett(div,Categories.T_Categories.ScarpeBorse.getValue());
//                    listaItemsTemp.add(post);
////                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
////                    }catch (NullPointerException ex) {
////                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
////                    }
//            }
//            //zaini
//            doc = Jsoup.connect("http://www.amazon.it/b?ie=UTF8&node=3102034031").maxBodySize(0).userAgent("Mozilla").get();
////            System.err.println(doc.html());
//            Elements divsZain = doc.select("div.s-item-container");
//            for (Element div : divsZain) {
//                    Post post = creaModelElett(div,Categories.T_Categories.ScarpeBorse.getValue());
//                    listaItemsTemp.add(post);
////                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
////                    }catch (NullPointerException ex) {
////                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
////                    }
//            }
//            
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE, "getScarpeBorse");
//        }
        return listaItemsTemp;
        
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
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
//        try {
//            //chitarre elettriche
//            doc = Jsoup.connect("https://www.amazon.it/s/ref=s9_acss_bw_cg_MICG_2b1?__mk_it_IT=%C5M%C5Z%D5%D1&rh=i%3Ami%2Cn%3A5021861031&ie=UTF8&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-3&pf_rd_r=JA3QKC9CRJBPYBP0B23K&pf_rd_t=101&pf_rd_p=1040348947&pf_rd_i=5021798031").maxBodySize(0).userAgent("Mozilla").get();
//             //System.err.println(doc.html());
//            Elements divs = doc.select("div.s-item-container");
//            for (Element div : divs) {
//                    Post post = creaModelElett(div,Categories.T_Categories.StrumentiMusicaliDj.getValue());
//                    listaItemsTemp.add(post);
//                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
//                    }catch (NullPointerException ex) {
//                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
//                    }
//            }
//            //elettriche acustiche
//            doc = Jsoup.connect("https://www.amazon.it/s/ref=s9_acss_bw_cg_MICG_2d1?__mk_it_IT=%C5M%C5Z%D5%D1&rh=i%3Ami%2Cn%3A5021862031&ie=UTF8&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-3&pf_rd_r=JA3QKC9CRJBPYBP0B23K&pf_rd_t=101&pf_rd_p=1040348947&pf_rd_i=5021798031").maxBodySize(0).userAgent("Mozilla").get();
//             //System.err.println(doc.html());
//            Elements divsEleAcu = doc.select("div.s-item-container");
//            for (Element div : divsEleAcu) {
//                    Post post = creaModelElett(div,Categories.T_Categories.StrumentiMusicaliDj.getValue());
//                    listaItemsTemp.add(post);
//                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
//                    }catch (NullPointerException ex) {
//                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
//                    }
//            }
//            //acustiche
//            doc = Jsoup.connect("https://www.amazon.it/s/ref=s9_acss_bw_cg_MICG_2c1?__mk_it_IT=%C5M%C5Z%D5%D1&rh=i%3Ami%2Cn%3A5021859031&ie=UTF8&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-3&pf_rd_r=JA3QKC9CRJBPYBP0B23K&pf_rd_t=101&pf_rd_p=1040348947&pf_rd_i=5021798031").maxBodySize(0).userAgent("Mozilla").get();
//             //System.err.println(doc.html());
//            Elements divsAcu = doc.select("div.s-item-container");
//            for (Element div : divsAcu) {
//                    Post post = creaModelElett(div,Categories.T_Categories.StrumentiMusicaliDj.getValue());
//                    listaItemsTemp.add(post);
//                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
//                    }catch (NullPointerException ex) {
//                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
//                    }
//            }
//            //effetti
//            doc = Jsoup.connect("https://www.amazon.it/s/ref=s9_acss_bw_cg_MICG_3a1?__mk_it_IT=%C5M%C5Z%D5%D1&rh=i%3Ami%2Cn%3A5021863031&ie=UTF8&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-3&pf_rd_r=JA3QKC9CRJBPYBP0B23K&pf_rd_t=101&pf_rd_p=1040348947&pf_rd_i=5021798031").maxBodySize(0).userAgent("Mozilla").get();
//             //System.err.println(doc.html());
//            Elements divsEffe = doc.select("div.s-item-container");
//            for (Element div : divsEffe) {
//                    Post post = creaModelElett(div,Categories.T_Categories.StrumentiMusicaliDj.getValue());
//                    listaItemsTemp.add(post);
//                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
//                    }catch (NullPointerException ex) {
//                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
//                    }
//            }
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE, "getStrumentiMusicaliDj");
//        }
        return listaItemsTemp;
    }

    @Override
    public List<Post> getAbbigliamento() {
        
        Document doc;
        List<Post> listaItemsTemp = new ArrayList<>();
        
//        try {
//            //vestiti donna
//            doc = Jsoup.connect("http://www.amazon.it/b/ref=ln_donna_abbi_spor?ie=UTF8&lo=apparel&node=1347370031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-left-2&pf_rd_r=11RA1TCS148N8GVGYVJC&pf_rd_t=101&pf_rd_p=707581647&pf_rd_i=2892859031").maxBodySize(0).userAgent("Mozilla").get();
//            //System.err.println(doc.html());
//            Elements divs = doc.select("div.s-item-container");
//            for (Element div : divs) {
//                    Post post = creaModelElett(div,Categories.T_Categories.Abbigliamento.getValue());
//                    listaItemsTemp.add(post);
////                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
////                    }catch (NullPointerException ex) {
////                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
////                    }
//            }
//            //vestiti uomo
//            doc = Jsoup.connect("http://www.amazon.it/b/ref=ln_uomo_abbi_spor?ie=UTF8&lo=apparel&node=1347371031&pf_rd_m=A11IL2PNWYJU7H&pf_rd_s=merchandised-search-left-2&pf_rd_r=1RW4VZFYEZE6W5BW4X7Y&pf_rd_t=101&pf_rd_p=709114307&pf_rd_i=2892862031").maxBodySize(0).userAgent("Mozilla").get();
////            System.err.println(doc.html());
//            Elements divsUomo = doc.select("div.s-item-container");
//            for (Element div : divsUomo) {
//                    Post post = creaModelElett(div,Categories.T_Categories.Abbigliamento.getValue());
//                    listaItemsTemp.add(post);
////                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
////                    }catch (NullPointerException ex) {
////                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
////                    }
//            }
//            //bambino
//            doc = Jsoup.connect("http://www.amazon.it/b/ref=s9_acss_bw_cg_itclo_1d1?ie=UTF8&node=2892858031&lo=clothing&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-3&pf_rd_r=1GQB62MWD0N5RH8Z0W0C&pf_rd_t=101&pf_rd_p=817253747&pf_rd_i=2844433031").maxBodySize(0).userAgent("Mozilla").get();
////            System.err.println(doc.html());
//            Elements divsBambm = doc.select("div.s-item-container");
//            for (Element div : divsBambm) {
//                    Post post = creaModelElett(div,Categories.T_Categories.Abbigliamento.getValue());
//                    listaItemsTemp.add(post);
////                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
////                    }catch (NullPointerException ex) {
////                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
////                    }
//            }
//            //bambina
//            doc = Jsoup.connect("http://www.amazon.it/b/ref=s9_acss_bw_cg_itclo_1d1?ie=UTF8&node=2892858031&lo=clothing&pf_rd_m=A2VX19DFO3KCLO&pf_rd_s=merchandised-search-3&pf_rd_r=1GQB62MWD0N5RH8Z0W0C&pf_rd_t=101&pf_rd_p=817253747&pf_rd_i=2844433031").maxBodySize(0).userAgent("Mozilla").get();
////            System.err.println(doc.html());
//            Elements divsBambf = doc.select("div.s-item-container");
//            for (Element div : divsBambf) {
//                    Post post = creaModelElett(div,Categories.T_Categories.Abbigliamento.getValue());
//                    listaItemsTemp.add(post);
////                    try{
////                        Post blogPost = creaBlogPost(post);
////                        listaItemsTemp.add(blogPost);
////                    }catch (NullPointerException ex) {
////                        LOGGER.log(Level.SEVERE, "Manca description salto il post...", "Manca description salto il post...");
////                    }
//            }
//            
//        } catch (IOException ex) {
//            LOGGER.log(Level.SEVERE, "getAbbigliamento");
//        }
        return listaItemsTemp;
    }

    @Override
    public List<Post> getViaggi() {
        return new ArrayList<>();
    }
    
    public Post creaBlogPost (Post post){
        Document doc;
        String longDescr="";
        Post blogPost = null;
        
        if(null != post.getPostTitle() && null != post.getItemUrl()){
//            try {
//                doc = Jsoup.connect(post.getItemUrl()).maxBodySize(0).userAgent("Mozilla").get();
//                longDescr = doc.getElementById("productDescription").html();
                //creo il post da template html
                BlogCreator blogCreator = new BlogCreator(post.getImageUrl().toString(),post.getPostTitle(), post.getItemUrl(),SHOP_NAME,LOGO_URL,post.getPostTitle());
                blogPost = new Post(post.getPostTitle(), blogCreator.getPost(), post.getImageUrl(), post.getPostShop());
                //categorizzazione
                List<String> categories = new ArrayList<>();
                categories.add(Categories.T_Categories.Blog.getValue());
                blogPost.setCategories(categories);

//            } catch (IOException  ex) {
//                LOGGER.log(Level.SEVERE, "creaBlogPost");
//            }
        }
        return blogPost;
    }
   
}
