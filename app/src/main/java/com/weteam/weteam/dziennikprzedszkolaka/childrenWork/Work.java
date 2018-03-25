package com.weteam.weteam.dziennikprzedszkolaka.childrenWork;

/**
 * Created by User on 2018-01-14.
 */

public class Work {
    private int id;
    private String name;
    private byte [] image;


    public Work(int id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
