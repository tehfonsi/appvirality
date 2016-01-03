package com.github.tehfonsi.appvirality;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by stephanschober on 03.01.16.
 */
public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleViewHolder> {

    private List<String> mItems;

    public SimpleRecyclerViewAdapter(List<String> items) {
        this.mItems = items;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.text.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
