/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.util;

/**
 *
 * @author felefant
 */
public class Categories {

    public enum T_Categories {
        Abbigliamento("Abbigliamento"),
        Informatica("Informatica"),
        AppGiochi("App e giochi"),
        AutoMoto("Auto e moto"),
        VideogiochiConsole("Videogiochi e Console"),
        Bellezza("Bellezza"),
        Casa("Casa"),
        FotografiaVideo("Fotografia e Video"),
        Elettronica("Elettronica"),
        CdDvd("CD DVD"),
        FaiDaTe("Fai da te"),
        FilmETv("Film e TV"),
        GiardinoGiardinaggio("Giardino e giardinaggio"),
        GiochiGiocattoli("Giochi e giocattoli"),
        Gioielli("Gioielli"),
        Libri("Libri"),
        Musica("Musica"),
        ScarpeBorse("Scarpe e borse"),
        Software("Software"),
        Sport("Sport"),
        StrumentiMusicaliDj("Strumenti musicali e DJ"),
        Viaggi("Viaggi"),
        Blog("Blog"),
        Uncategorized("Uncategorized");

        private String value;

        T_Categories(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
}
