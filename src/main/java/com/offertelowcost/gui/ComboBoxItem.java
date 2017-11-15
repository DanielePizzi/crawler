/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.gui;

import net.bican.wordpress.Term;

/**
 *
 * @author felefant
 */
public class ComboBoxItem {

    private Term term;

    public ComboBoxItem(Term term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return term.getName();
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

}
