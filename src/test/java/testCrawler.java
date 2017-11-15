
import com.offertelowcost.crawler.impl.CrawlerAmazon;
import com.offertelowcost.crawler.impl.CrawlerEbay;
import com.offertelowcost.crawler.impl.CrawlerFeltrinelli;
import com.offertelowcost.crawler.impl.CrawlerGroupon;
import com.offertelowcost.crawler.impl.CrawlerMediaworld;
import com.offertelowcost.crawler.impl.CrawlerMondadori;
import com.offertelowcost.crawler.impl.CrawlerZalando;
import com.offertelowcost.crawler.interfaces.AbstractCrawler;
import com.offertelowcost.model.Post;
import com.offertelowcost.util.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DGASPERI
 */
public class testCrawler {
    public static void main(String[] args) {
        //Proxy.settaProxy();
//        Proxy.settaProxyBNL();
        AbstractCrawler crawlerEbay = new CrawlerEbay();
        AbstractCrawler crawlerFeltrinelli = new CrawlerFeltrinelli();
        AbstractCrawler crawlerGroupon = new CrawlerGroupon();
        AbstractCrawler crawlerZalando = new CrawlerZalando();
        AbstractCrawler crawlerMediaworld = new CrawlerMediaworld();
        AbstractCrawler crawlerMondadori = new CrawlerMondadori();
        AbstractCrawler crawlerAmazon = new CrawlerAmazon();
        
        List<Post> posts = new ArrayList<>();
        
//        posts.addAll(crawlerEbay.inizializza());
//        posts.addAll(crawlerFeltrinelli.inizializza());
//        posts.addAll(crawlerGroupon.inizializza());
//        posts.addAll(crawlerZalando.inizializza());
        posts.addAll(crawlerMediaworld.inizializza());
//        posts.addAll(crawlerMondadori.inizializza());
//        posts.addAll(crawlerAmazon.inizializza());
       
        int count = 0;
        for (Post post : posts) {
            if(post.getCategories().contains("Blog")){
                count++;
            }
//            System.err.println(post.getPostContent());
        }
        Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Trovate {0} offerte dai crawlers", new Object[]{posts.size()-count });
        Logger.getLogger(CrawlerEbay.class.getName()).log(Level.INFO, "Creati {0} post per il blog", new Object[]{count });
    }
}
