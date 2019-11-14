package com.example.sprint2.Model;

import android.graphics.drawable.Drawable;

public class Character {
    int id;
    private String bahasa;
    private String sunda;
    private String aksara;
    private String vocal;

    private String contoh;
    private int image;
    private int sound;

    // constructors
    public Character() {
    }

    public Character(String sunda) {
        this.sunda = sunda;
    }

    public Character(int id, String sunda) {
        this.id = id;
        this.sunda = sunda;
    }

    public Character(String sunda, String aksara, String vocal) {
        this.sunda = sunda;
        this.aksara = aksara;
        this.vocal = vocal;
    }

    public Character(int id, String sunda, String aksara, String vocal) {
        this.id = id;
        this.sunda = sunda;
        this.aksara = aksara;
        this.vocal = vocal;
    }

    public Character(String sunda, String contoh, int image, int sound) {
        this.sunda = sunda;
        this.contoh = contoh;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSunda(String sunda) {
        this.sunda = sunda;
    }

    public void setAksara(String aksara) {
        this.aksara = aksara;
    }

    public void setVocal(String vocal) {
        this.vocal = vocal;
    }

    public void setContoh(String contoh) {
        this.contoh = contoh;
    }

    public void setImage(int image) {
        this.image= image;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getSunda() {
        return this.sunda;
    }

    public String getAksara() {
        return this.aksara;
    }

    public String getVocal() {
        return this.vocal;
    }

    public String getContoh() {
        return this.contoh;
    }

    public int getImage() {
        return this.image;
    }

    public int getSound() {
        return this.sound;
    }

}
