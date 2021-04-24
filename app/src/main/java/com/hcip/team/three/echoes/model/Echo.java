package com.hcip.team.three.echoes.model;

import java.util.ArrayList;
import java.util.Date;

public class Echo implements Comparable<Echo>{

    private int id;
    private int creator;
    private int mood;

    private String title;
    private String description;
    private String location;

    private ArrayList<Friend> tags;
    private ArrayList<String> b64Pictures;

    private Date date;

    private boolean hasMood;

    // TODO: add audio to the Echo

    public Echo(int id, int creator, Date date) {
        this.id = id;
        this.creator = creator;
        this.date = date;

        this.hasMood = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Friend> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Friend> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getB64Pictures() {
        return b64Pictures;
    }

    public void setB64Pictures(ArrayList<String> b64Pictures) {
        this.b64Pictures = b64Pictures;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void addImage(String b64Image) {
        if (b64Pictures == null) {
            b64Pictures = new ArrayList<>();
        }

        this.b64Pictures.add(b64Image);
    }

    public void deleteImages() {
        this.b64Pictures.clear();
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
        this.hasMood = true;
    }

    @Override
    public int compareTo(Echo echo) {
        return this.getDate().compareTo(echo.date);
    }

    public boolean isHasMood() {
        return hasMood;
    }

    public void setHasMood(boolean hasMood) {
        this.hasMood = hasMood;
    }
}
