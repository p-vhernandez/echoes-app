package com.hcip.team.three.echoes;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.hcip.team.three.echoes.model.Echo;
import com.hcip.team.three.echoes.model.Friend;
import com.hcip.team.three.echoes.model.Mood;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

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

    public String imageEncoder(int drawable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawable);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

}
