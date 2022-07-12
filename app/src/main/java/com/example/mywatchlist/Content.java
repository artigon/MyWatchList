package com.example.mywatchlist;

import java.util.Vector;

public class Content {
    private String name;
    private String dateOfRelese;
    private int numOfLikes;
    private String disciption;
    private Vector<String> usersThatLiked;
    //put here vector for posts


    public Content(String name, String dateOfRelese, String disciption) {
        this.name = name;
        this.dateOfRelese = dateOfRelese;
        this.numOfLikes = 0;
        this.disciption = disciption;
        this.usersThatLiked = new Vector<String>();
    }

    public Content() {
        this.usersThatLiked = new Vector<String>();
    }



    public Content(Content tmpContent) {
        this.name = tmpContent.getName();
        this.dateOfRelese = tmpContent.getDateOfRelese();
        this.numOfLikes = tmpContent.getNumOfLikes();
        this.disciption = tmpContent.getDisciption();
        this.usersThatLiked = new Vector<String>();
    }

    public void copyUserList(Vector<String> tmp){
        if(tmp.size() != 0) {
            for (int i = 0; i < tmp.size(); i++)
                this.usersThatLiked.add(tmp.elementAt(i));
        }
    }

    public void addLike() {
        this.numOfLikes++;
    }

    public void takeLike() {
        this.numOfLikes--;
    }

    public boolean userThatLikesCheckList(String name) {
        if (this.usersThatLiked.size() != 0) {
            for (int i = 0; i < this.usersThatLiked.size(); i++) {
                if (this.usersThatLiked.elementAt(i).equals(name))
                    return true;
            }
        }
        return false;
    }

    public void addUserToList(String name) {
        this.usersThatLiked.add(name);
    }

    public void deleteUserFromList(String name) {
        for (int i = 0; i < this.usersThatLiked.size(); i++) {
            if (this.usersThatLiked.elementAt(i).equals(name)) {
                this.usersThatLiked.remove(i);
                break;
            }
        }
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

    public void addUserThatLiked(String tmpName) {
        this.usersThatLiked.add(tmpName);
    }
}
