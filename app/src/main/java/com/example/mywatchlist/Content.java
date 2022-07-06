package com.example.mywatchlist;

import java.util.Vector;

public class Content {
    private String name;
    private String dateOfRelese;
    private String type;
    private int numOfLikes;
    private String disciption;
    private Vector<String> usersThatLiked;
    private Vector<String> posts;
    //put here vector for posts


    public Content(String name, String dateOfRelese, String type, String disciption) {
        this.name = name;
        this.dateOfRelese = dateOfRelese;
        this.type = type;
        this.numOfLikes = 0;
        this.disciption = disciption;
        this.usersThatLiked = new Vector<String>();
        this.posts = new Vector<String>();
    }

    public Content(){};

    public Content(Content tmpContent){
        this.name = tmpContent.getName();
        this.dateOfRelese = tmpContent.getDateOfRelese();
        this.type = tmpContent.getType();
        this.numOfLikes = tmpContent.getNumOfLikes();
        this.disciption = tmpContent.getDisciption();
        this.usersThatLiked = tmpContent.getUsersThatLiked();
        this.posts = tmpContent.getPosts();
//        for(int i = 0; i < tmpContent.getUsersThatLiked().size(); i++)
//            this.usersThatLiked.elementAt(i) = tmpContent.getUsersThatLiked().elementAt(i);
    }

    public Vector<String> getPosts() {
        return posts;
    }

    public void setPosts(Vector<String> posts) {
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfRelese() {
        return dateOfRelese;
    }

    public void setDateOfRelese(String dateOfRelese) {
        this.dateOfRelese = dateOfRelese;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumOfLikes() {
        return numOfLikes;
    }

    public void setNumOfLikes(int numOfLikes) {
        this.numOfLikes = numOfLikes;
    }

    public String getDisciption() {
        return disciption;
    }

    public void setDisciption(String disciption) {
        this.disciption = disciption;
    }

    public Vector<String> getUsersThatLiked() {
        return usersThatLiked;
    }

    public void setUsersThatLiked(Vector<String> usersThatLiked) {
        this.usersThatLiked = usersThatLiked;
    }

    public void addUserThatLiked(String tmpName){
        this.usersThatLiked.add(tmpName);
    }
}
