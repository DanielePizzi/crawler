/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.offertelowcost.model;

import java.net.URL;
import java.util.List;

/**
 *
 * @author DGASPERI
 */
public class Post {

    private String postTitle;
    private String postContent;
    private String postStatus;
    private int postId;
    private List<String> categories;
    private URL imageUrl;
    private String postShop;
    private String itemUrl;

    public Post() {
        postStatus = "publish";
    }

    public Post(String postTitle, String postContent, URL imageUrl, String postShop) {
        postStatus = "publish";
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.imageUrl = imageUrl;
        this.postShop = postShop;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPostShop() {
        return postShop;
    }

    public void setPostShop(String postShop) {
        this.postShop = postShop;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }
    
    

    @Override
    public String toString() {
        return "Post{" + "postTitle=" + postTitle + ", postStatus=" + postStatus + ", postId=" + postId + ", categories=" + categories + ", imageUrl=" + imageUrl + ", postShop=" + postShop +  ", itemUrl=" + itemUrl +'}';
    }

}
