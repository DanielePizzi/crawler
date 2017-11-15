/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.gui;

import com.offertelowcost.crawler.impl.CrawlerAmazon;
import com.offertelowcost.crawler.impl.CrawlerEbay;
import com.offertelowcost.crawler.impl.CrawlerFeltrinelli;
import com.offertelowcost.crawler.impl.CrawlerGroupon;
import com.offertelowcost.crawler.impl.CrawlerMediaworld;
import com.offertelowcost.crawler.impl.CrawlerMondadori;
import com.offertelowcost.crawler.impl.CrawlerZalando;
import com.offertelowcost.crawler.interfaces.AbstractCrawler;
import com.offertelowcost.main.XMLRPCClient;
import com.offertelowcost.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;
import redstone.xmlrpc.XmlRpcClient;

/**
 *
 * @author FELEFANT
 */
public class WordpressManager extends javax.swing.JFrame {

    public static final Logger LOG = Logger.getLogger(WordpressManager.class.getName());
    private XmlRpcClient client;

    /**
     * Creates new form WordpressManager
     */
    public WordpressManager() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        removeAllPostsButton = new javax.swing.JButton();
        ebayButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        feltrinelliButton = new javax.swing.JButton();
        zalandoButton = new javax.swing.JButton();
        removeAllMediaButton = new javax.swing.JButton();
        grouponButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        mediaworldButton = new javax.swing.JButton();
        mondadoriButton = new javax.swing.JButton();
        amazonButton = new javax.swing.JButton();
        allButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wordpress Manager");

        removeAllPostsButton.setText("Remove all posts");
        removeAllPostsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllPostsButtonActionPerformed(evt);
            }
        });

        ebayButton.setText("Insert eBay offers");
        ebayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ebayButtonActionPerformed(evt);
            }
        });

        feltrinelliButton.setText("Insert Feltrinelli offers");
        feltrinelliButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feltrinelliButtonActionPerformed(evt);
            }
        });

        zalandoButton.setText("Insert Zalando offers");
        zalandoButton.setEnabled(true);
        zalandoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zalandoButtonActionPerformed(evt);
            }
        });

        removeAllMediaButton.setText("Remove all media");
        removeAllMediaButton.setEnabled(false);
        removeAllMediaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllMediaButtonActionPerformed(evt);
            }
        });

        grouponButton.setText("Insert Groupon offers");
        grouponButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grouponButtonActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        mediaworldButton.setText("Insert MediaWorld offers");
        mediaworldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediaworldButtonActionPerformed(evt);
            }
        });

        mondadoriButton.setText("Insert Mondadori offers");
        mondadoriButton.setEnabled(false);
        mondadoriButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mondadoriButtonActionPerformed(evt);
            }
        });

        amazonButton.setText("Insert Amazon offers");
        amazonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amazonButtonActionPerformed(evt);
            }
        });

        allButton.setText("Insert All offers");
        allButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(removeAllPostsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(feltrinelliButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(zalandoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(removeAllMediaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(grouponButton, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(mediaworldButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(mondadoriButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(amazonButton, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                    .addComponent(ebayButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allButton, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mondadoriButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mediaworldButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(feltrinelliButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zalandoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(grouponButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ebayButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amazonButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeAllMediaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeAllPostsButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeAllPostsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllPostsButtonActionPerformed

        XMLRPCClient.removeAllPosts(client);
    }//GEN-LAST:event_removeAllPostsButtonActionPerformed

    private void ebayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ebayButtonActionPerformed

        List<Post> postList = new ArrayList();
        AbstractCrawler crawlerEbay = new CrawlerEbay();
        postList.addAll(crawlerEbay.inizializza());
        int count = 1;
        int size = postList.size();
        for (Post post : postList) {
            XMLRPCClient.inserisciArticolo(client, post);
            int conta = count++;
            writeConsole(post.getPostId(), conta, size);
            LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
        }


    }//GEN-LAST:event_ebayButtonActionPerformed

    private void zalandoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zalandoButtonActionPerformed

        List<Post> postList = new ArrayList();
        AbstractCrawler crawlerZalando = new CrawlerZalando();
        postList.addAll(crawlerZalando.inizializza());
        int count = 1;
        int size = postList.size();
        for (Post post : postList) {

            XMLRPCClient.inserisciArticolo(client, post);
            int conta = count++;
            writeConsole(post.getPostId(), conta, size);
            LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
        }
    }//GEN-LAST:event_zalandoButtonActionPerformed

    private void feltrinelliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feltrinelliButtonActionPerformed
        List<Post> postList = new ArrayList();
        AbstractCrawler crawlerFeltrinelli = new CrawlerFeltrinelli();
        postList.addAll(crawlerFeltrinelli.inizializza());
        int count = 1;
        int size = postList.size();
        for (Post post : postList) {
            XMLRPCClient.inserisciArticolo(client, post);
            int conta = count++;
            writeConsole(post.getPostId(), conta, size);
            LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
        }
    }//GEN-LAST:event_feltrinelliButtonActionPerformed

    private void removeAllMediaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllMediaButtonActionPerformed
//        XMLRPCClient.removeAllMedia(client);

        for (int i = 0; i < 20; i++) {
            try {
                writeConsole(i, 5, i);
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(WordpressManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_removeAllMediaButtonActionPerformed

    private void grouponButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grouponButtonActionPerformed
        List<Post> postList = new ArrayList();
        AbstractCrawler crawlerGroupon = new CrawlerGroupon();
        postList.addAll(crawlerGroupon.inizializza());
        int count = 1;
        int size = postList.size();
        for (Post post : postList) {
            XMLRPCClient.inserisciArticolo(client, post);
            int conta = count++;
            writeConsole(post.getPostId(), conta, size);
            LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
        }
    }//GEN-LAST:event_grouponButtonActionPerformed

    private void mediaworldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediaworldButtonActionPerformed
        List<Post> postList = new ArrayList();
        AbstractCrawler crawlerMediaworld = new CrawlerMediaworld();
        postList.addAll(crawlerMediaworld.inizializza());
        int count = 1;
        int size = postList.size();
        for (Post post : postList) {
//               System.err.println(post.toString());
            XMLRPCClient.inserisciArticolo(client, post);
            int conta = count++;
            writeConsole(post.getPostId(), conta, size);
            LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
        }
    }//GEN-LAST:event_mediaworldButtonActionPerformed

    private void mondadoriButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mondadoriButtonActionPerformed
        List<Post> postList = new ArrayList();
        AbstractCrawler crawlerMondadori = new CrawlerMondadori();
        postList.addAll(crawlerMondadori.inizializza());
        int count = 1;
        int size = postList.size();
        for (Post post : postList) {
            XMLRPCClient.inserisciArticolo(client, post);
            int conta = count++;
            writeConsole(post.getPostId(), conta, size);
            LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
        }
    }//GEN-LAST:event_mondadoriButtonActionPerformed

    private void amazonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amazonButtonActionPerformed
        List<Post> postList = new ArrayList();
        AbstractCrawler crawlerAmazon = new CrawlerAmazon();
        postList.addAll(crawlerAmazon.inizializza());
        int count = 1;
        int size = postList.size();
        for (Post post : postList) {
            XMLRPCClient.inserisciArticolo(client, post);
            int conta = count++;
            writeConsole(post.getPostId(), conta, size);
            LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
        }
    }//GEN-LAST:event_amazonButtonActionPerformed

    private void allButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allButtonActionPerformed
        List<Post> postList = new ArrayList();
        AbstractCrawler crawlerMediaworld = new CrawlerMediaworld();
        AbstractCrawler crawlerGroupon = new CrawlerGroupon();
        AbstractCrawler crawlerZalando = new CrawlerZalando();
        AbstractCrawler crawlerMondadori = new CrawlerMondadori();
        AbstractCrawler crawlerFeltrinelli = new CrawlerFeltrinelli();
        AbstractCrawler crawlerEbay = new CrawlerEbay();
        AbstractCrawler crawlerAmazon = new CrawlerAmazon();
        postList.addAll(crawlerGroupon.inizializza());
        postList.addAll(crawlerZalando.inizializza());
        postList.addAll(crawlerMediaworld.inizializza());
//        postList.addAll(crawlerMondadori.inizializza());
        postList.addAll(crawlerFeltrinelli.inizializza());
        postList.addAll(crawlerEbay.inizializza());
        postList.addAll(crawlerAmazon.inizializza());
        int count = 1;
        int size = postList.size();
        for (Post post : postList) {
            XMLRPCClient.inserisciArticolo(client, post);
            int conta = count++;
            writeConsole(post.getPostId(), conta, size);
            LOG.log(Level.INFO, ">>>> inserito post id = {0} | inserimento {1} di {2}", new Object[]{post.getPostId(), conta, size});
        }
    }//GEN-LAST:event_allButtonActionPerformed

    public XmlRpcClient getClient() {
        return client;
    }

    public void setClient(XmlRpcClient client) {
        this.client = client;
    }

    private void writeConsole(final int postId, final int count, final int size) {
        String textToAppend = ">>>> inserito post id = " + postId + " | inserimento " + count + " di " + size + "\n";
        jTextArea1.append(textToAppend);
        jScrollPane1.update(jScrollPane1.getGraphics());
        jTextArea1.setCaretPosition(jTextArea1.getText().length() - 1);
        
        DefaultCaret caret = (DefaultCaret)jTextArea1.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        this.validate();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allButton;
    private javax.swing.JButton amazonButton;
    private javax.swing.JButton ebayButton;
    private javax.swing.JButton feltrinelliButton;
    private javax.swing.JButton grouponButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton mediaworldButton;
    private javax.swing.JButton mondadoriButton;
    private javax.swing.JButton removeAllMediaButton;
    private javax.swing.JButton removeAllPostsButton;
    private javax.swing.JButton zalandoButton;
    // End of variables declaration//GEN-END:variables
}