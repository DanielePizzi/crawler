/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.main;

import com.offertelowcost.gui.WordpressManager;
import com.offertelowcost.model.Post;
import com.offertelowcost.util.Proxy;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.io.IOUtils;
import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcClient;
import redstone.xmlrpc.XmlRpcException;
import redstone.xmlrpc.XmlRpcFault;
import redstone.xmlrpc.XmlRpcStruct;

/**
 *
 * @author FELEFANT
 */
public class XMLRPCClient {

    public static final Logger LOG = Logger.getLogger(XMLRPCClient.class.getName());
    private static final String USERNAME = "offertelowcost";
    private static final String PASSWORD = "[kas8JSK-as@s#]k";
    private static final int BLOG_ID = 1;
    private static final String XMLRPC_URL = "http://www.offertelowcost.net/xmlrpc.php";
    private static List<Post> postList = new ArrayList();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                //Proxy.settaProxy();
//                Proxy.settaProxyBNL();
                try {
                    //First step, init an client
                    XmlRpcClient client = new XmlRpcClient(XMLRPC_URL, true);

                    /* avvio la gui */
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                    WordpressManager wm = new WordpressManager();
                    wm.setClient(client);
                    wm.setVisible(true);

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | MalformedURLException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static int caricaFile(XmlRpcClient client, URL imageURL, String fileNamePrefix) {
        int imageId = -1;
        try {
            String url = imageURL.toString();
            String fileName = url.substring(url.lastIndexOf('/') + 1);
            String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

            byte[] bytes = IOUtils.toByteArray(imageURL.openStream());
            HashMap data = new HashMap();

            data.put("name", fileNamePrefix + fileName);

            // set mime type
            if ("png".equalsIgnoreCase(ext)) {
                data.put("type", "image/png");
            } else if ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "jpe".equalsIgnoreCase(ext)) {
                data.put("type", "image/jpeg");
            } else if ("tif".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext)) {
                data.put("type", "image/tiff");
            } else if ("gif".equalsIgnoreCase(ext)) {
                data.put("type", "image/gif");
            }

            data.put("bits", bytes);
            data.put("overwrite", false);

            XmlRpcStruct attachment = (XmlRpcStruct) client.invoke("wp.uploadFile", new Object[]{BLOG_ID, USERNAME, PASSWORD, data});

            imageId = Integer.parseInt((String) attachment.get("id"));

        } catch (XmlRpcException | XmlRpcFault | IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return imageId;
    }

    public static int inserisciArticolo(XmlRpcClient client, Post post) {
        int postId = -1;
        try {
            //Now, put data into the client. The client will encode all data into XML and send it to wordpress XML-RPC API
            HashMap hmContent = new HashMap();
            hmContent.put("post_title", post.getPostTitle());
            hmContent.put("post_content", post.getPostContent());
            hmContent.put("post_status", post.getPostStatus()); // draft or publish

            String prefix = retrievePrefixFileName(post);

            hmContent.put("post_thumbnail", caricaFile(client, post.getImageUrl(), prefix));

            HashMap taxonomies = new HashMap();
            taxonomies.put("category", post.getCategories());

            hmContent.put("terms_names", taxonomies);

            String postIdStr = (String) client.invoke("wp.newPost", new Object[]{BLOG_ID, USERNAME, PASSWORD, hmContent, true});

            post.setPostId(Integer.parseInt(postIdStr));
        } catch (XmlRpcException | XmlRpcFault ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return postId;
    }

    /**
     * ritorna tutte le categorie del blog
     *
     * @param client
     * @return
     */
    public static List<String> recuperaCategorie(XmlRpcClient client) {
        List<String> list = new ArrayList<>();
        try {
            Object categorie = client.invoke("wp.getCategories", new Object[]{BLOG_ID, USERNAME, PASSWORD});

            List<XmlRpcStruct> categories = (List<XmlRpcStruct>) categorie;

            for (XmlRpcStruct category : categories) {
                String description = (String) category.get("description");
                list.add(description);
            }

        } catch (XmlRpcException | XmlRpcFault ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void removeAllPosts(XmlRpcClient client) {
        try {
            // recupero tutti gli id
            HashMap hmContent = new HashMap();
            hmContent.put("number", 800);

            List<XmlRpcStruct> allPosts = (List<XmlRpcStruct>) client.invoke("wp.getPosts", new Object[]{BLOG_ID, USERNAME, PASSWORD, hmContent});

            // ciclo e rimuovo
            for (XmlRpcStruct post : allPosts) {

                // check Blog posts
                boolean deletePost = true;

                XmlRpcArray terms = (XmlRpcArray) post.get("terms");

                for (Object term : terms) {
                    XmlRpcStruct s = (XmlRpcStruct) term;
                    if ("Blog".equals(s.get("name"))) {
                        deletePost = false;
                    }
                }

                if (deletePost) {
                    String postId = (String) post.get("post_id");

                    boolean result = (boolean) client.invoke("wp.deletePost", new Object[]{BLOG_ID, USERNAME, PASSWORD, postId});
                    if (result) {
                        LOG.log(Level.INFO, "Rimosso con successo il post con id: {0}", postId);
                    } else {
                        LOG.log(Level.SEVERE, "Impossibile rimuovere il post con id: {0}", postId);
                    }
                }
            }

            LOG.info("All posts removed!");
        } catch (XmlRpcException | XmlRpcFault ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

    }

    public static void removeAllMedia(XmlRpcClient client) {

        // non si può fare
    }

    private static String retrievePrefixFileName(Post post) {
        String prefix = "";
        List<String> list = post.getCategories();
        for (String cat : list) {
            if ("Blog".equals(cat)) {
                prefix = "ZZZZZ-";
                break;
            }
        }
        return prefix;
    }
}
