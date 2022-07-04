package com.example.mywatchlist;

import java.util.Vector;

public class Content {
    private String name;
    private String dateOfRelese;
    private String type;
    private int numOfLikes;
    private String disciption;
    private Vector<User> usersThatLiked = new Vector<User>();
    //put here vector for posts


    public Content(String name, String dateOfRelese, String type, String disciption) {
        this.name = name;
        this.dateOfRelese = dateOfRelese;
        this.type = type;
        this.numOfLikes = usersThatLiked.size();
        this.disciption = disciption;
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

    public Vector<User> getUsersThatLiked() {
        return usersThatLiked;
    }

    public void setUsersThatLiked(Vector<User> usersThatLiked) {
        this.usersThatLiked = usersThatLiked;
    }

    public void addUserThatLiked(User user){
        this.usersThatLiked.add(user);
    }
}
