package com.hcip.team.three.echoes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.hcip.team.three.echoes.model.Echo;
import com.hcip.team.three.echoes.model.Friend;
import com.hcip.team.three.echoes.model.Mood;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class EchoesApplication extends Application {

    private ArrayList<Friend> friends;
    private ArrayList<Echo> echoes;
    private ArrayList<Mood> moods;

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    public ArrayList<Echo> getEchoes() {
        Collections.sort(echoes);
        Collections.reverse(echoes);
        return echoes;
    }

    public void setEchoes(ArrayList<Echo> echoes) {
        this.echoes = echoes;
    }

    public ArrayList<Mood> getMoods() {
        return moods;
    }

    public void setMoods(ArrayList<Mood> moods) {
        this.moods = moods;
    }

    public String imageEncoder(int drawable, boolean isPNG) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);

        if (isPNG) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        } else {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        }

        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public Drawable imageDecoder(String b64Image) {
        byte[] decodedString = Base64.decode(b64Image, Base64.DEFAULT);
        Bitmap bitmap =  BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return new BitmapDrawable(getResources(), bitmap);
    }

    @SuppressLint("SimpleDateFormat")
    public Date dateFromString(String dateString) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String stringFromDate(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        return simpleDateFormat.format(date);
    }

}
