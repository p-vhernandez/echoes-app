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

    private Friend user;

    private Echo newEcho;

    public void startEchoCreation() {
        newEcho = new Echo(echoes.size(), user.getId(), new Date());
    }

    public void finishEchoCreation() {
        newEcho = null;
    }

    public void saveEchoPictures(ArrayList<String> echoPictures) {
        newEcho.deletePictures();
        newEcho.setB64Pictures(echoPictures);
    }

    public void saveEchoMood(int idMood) {
        newEcho.setMood(idMood);
    }

    public void saveEchoTitle(String title) {
        newEcho.setTitle(title);
    }

    public void saveEchoDesc(String desc) {
        newEcho.setDescription(desc);
    }

    public void saveEchoAudio(boolean hasAudio, long audioLength) {
        newEcho.setHasAudio(hasAudio);
        newEcho.setAudioLength(audioLength);
    }

    public void saveEchoDate(Date date) {
        newEcho.setDate(date);
    }

    public void saveEchoTag(Friend friend) {
        if (newEcho.getTags() == null) {
            newEcho.setTags(new ArrayList<>());
        }

        newEcho.addTag(friend);
    }

    public void removeEchoTag(Friend friend) {
        newEcho.removeTag(friend);
    }

    public void saveEchoLocation(String location) {
        newEcho.setLocation(location);
    }

    public Echo getNewEcho() {
        return newEcho;
    }

    public void setUser() {
        user = friends.get(9);
    }

    public Friend getUser() {
        return friends.get(9);
    }

    public ArrayList<Friend> getFriends() {
        ArrayList<Friend> actualFriends = new ArrayList<>();
        for (Friend friend : friends) {
            if (!friend.isUser()) {
                actualFriends.add(friend);
            }
        }

        return actualFriends;
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

    public String imageEncoder(Drawable drawable, boolean isPNG) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);

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

    public String stringFromDateCreation(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        return simpleDateFormat.format(date);
    }

}
