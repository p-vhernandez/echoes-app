package com.hcip.team.three.echoes.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hcip.team.three.echoes.R;
import com.hcip.team.three.echoes.model.Echo;

import java.util.ArrayList;

public class EchoesAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Echo> allEchoes;

    public EchoesAdapter(Context context, ArrayList<Echo> allEchoes) {
        this.context = context;
        this.allEchoes = allEchoes;
    }


    @Override
    public int getCount() {
        return allEchoes.size();
    }

    @Override
    public Object getItem(int i) {
        return allEchoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return allEchoes.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.custom_layout_echo, parent, false);

        try {
            Echo echo = allEchoes.get(position);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
