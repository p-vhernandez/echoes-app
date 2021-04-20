package com.hcip.team.three.echoes.model;

import java.util.ArrayList;
import java.util.Date;

public class Echo {

    private int id;

    private String title;
    private String description;
    private String location;

    private ArrayList<Friend> tags;
    private ArrayList<String> b64Pictures;

    private Date date;

    // TODO: add audio to the Echo

    public Echo() {

    }

}
