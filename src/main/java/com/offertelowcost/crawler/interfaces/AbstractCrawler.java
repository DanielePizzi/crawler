/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.crawler.interfaces;

import com.offertelowcost.model.Post;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author DGASPERI
 */
public abstract class AbstractCrawler {

    private List<Post> listaItems;
    
    public List<Post> inizializza(){
        
        listaItems = new ArrayList<>();
        
        listaItems.addAll(getAppGiochi());
        listaItems.addAll(getAutoMoto());
        listaItems.addAll(getVideogiochiConsole());
        listaItems.addAll(getBellezza());
        listaItems.addAll(getCasa());
        listaItems.addAll(getFotografiaVideo());
        listaItems.addAll(getCdDvd());
        listaItems.addAll(getFaiDaTe());
        listaItems.addAll(getFilmETv());
        listaItems.addAll(getGiardinoGiardinaggio());
        listaItems.addAll(getGiochiGiocattoli());
        listaItems.addAll(getGioielli());
        listaItems.addAll(getLibri());
        listaItems.addAll(getMusica());
        listaItems.addAll(getScarpeBorse());
        listaItems.addAll(getSoftware());
        listaItems.addAll(getSport());
        listaItems.addAll(getStrumentiMusicaliDj());
        listaItems.addAll(getAbbigliamento());
        listaItems.addAll(getViaggi());
        listaItems.addAll(getInformatica());
        listaItems.addAll(getElettronica());
        
        // randomize list
        Collections.shuffle(listaItems);
        
        return listaItems;
    }

    public abstract List<Post> getInformatica();

    public abstract List<Post> getAppGiochi();

    public abstract List<Post> getAutoMoto();

    public abstract List<Post> getVideogiochiConsole();

    public abstract List<Post> getBellezza();

    public abstract List<Post> getCasa();

    public abstract List<Post> getFotografiaVideo();

    public abstract List<Post> getElettronica();

    public abstract List<Post> getCdDvd();

    public abstract List<Post> getFaiDaTe();

    public abstract List<Post> getFilmETv();

    public abstract List<Post> getGiardinoGiardinaggio();

    public abstract List<Post> getGiochiGiocattoli();

    public abstract List<Post> getGioielli();

    public abstract List<Post> getLibri();

    public abstract List<Post> getMusica();

    public abstract List<Post> getScarpeBorse();

    public abstract List<Post> getSoftware();

    public abstract List<Post> getSport();

    public abstract List<Post> getStrumentiMusicaliDj();

    public abstract List<Post> getAbbigliamento();
    
    public abstract List<Post> getViaggi();

}
