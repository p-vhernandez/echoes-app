package com.hcip.team.three.echoes.model;

public class Mood {

    private int id;

    private String moodImage;
    private String moodName;

    public Mood (int id, String moodImage, String moodName) {
        this.id = id;
        this.moodImage = moodImage;
        this.moodName = moodName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoodImage() {
        return moodImage;
    }

    public void setMoodImage(String moodImage) {
        this.moodImage = moodImage;
    }

    public String getMoodName() {
        return moodName;
    }

    public void setMoodName(String moodName) {
        this.moodName = moodName;
    }

}
