package com.example.iince98.dailygerman;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by iince98 on 02/12/2017.
 */

public class Adapter extends BaseAdapter {
    private Context mcontext;
    private List<Terim_class> mTerimlist;

    public Adapter(Context mcontext, List<Terim_class> mTerimlist) {
        this.mcontext = mcontext;
        this.mTerimlist = mTerimlist;
    }

    @Override
    public int getCount() {
        return mTerimlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mTerimlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mTerimlist.get(position).getKategori();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= View.inflate(mcontext, R.layout.item_listview, null);
        TextView tvkategori= (TextView)v.findViewById(R.id.tvkategori);
        TextView tvterim= (TextView)v.findViewById(R.id.tvterim);
        TextView tvanlam= (TextView)v.findViewById(R.id.tvanlam);
        tvkategori.setText(String.valueOf(mTerimlist.get(position).getKategori()));
        tvterim.setText(mTerimlist.get(position).getTerim());
        tvanlam.setText(mTerimlist.get(position).getAnlamı());
        return v;
    }
}

