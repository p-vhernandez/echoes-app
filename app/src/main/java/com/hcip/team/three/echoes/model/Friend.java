package com.hcip.team.three.echoes.model;

public class Friend {

    private int id;

    private String name;
    private String b64ProfilePicture;

    public Friend(int id, String name, String b64ProfilePicture) {
        this.id = id;
        this.name = name;
        this.b64ProfilePicture = b64ProfilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getB64ProfilePicture() {
        return b64ProfilePicture;
    }

    public void setB64ProfilePicture(String b64ProfilePicture) {
        this.b64ProfilePicture = b64ProfilePicture;
    }
}

