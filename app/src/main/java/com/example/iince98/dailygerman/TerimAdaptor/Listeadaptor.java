package com.example.iince98.dailygerman.TerimAdaptor;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iince98.dailygerman.R;
import com.example.iince98.dailygerman.Terim.Terim;

import java.util.List;

/**
 * Created by iince98 on 16/11/2017.
 */

public class Listeadaptor extends BaseAdapter {
    private Context mcontext;
    private List<Terim> terimList;

    @Override
    public int getCount() {

        return terimList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return terimList.get(i).getKat();

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v= View.inflate(mcontext, R.layout.item_listview, null);
        TextView tvkategori= (TextView)v.findViewById(R.id.tvkategori);
        TextView tvanlam= (TextView)v.findViewById(R.id.tvanlam);
        TextView tvterim= (TextView)v.findViewById(R.id.tvterim);

        tvkategori.setText(terimList.get(i).getKat());
        tvanlam.setText(String.valueOf(terimList.get(i).getAnlamı()));
        tvterim.setText(String.valueOf(terimList.get(i).getTerim()));


        return v;
    }
}
