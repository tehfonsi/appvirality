package com.github.tehfonsi.appvirality;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by stephanschober on 03.01.16.
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {

    public TextView text;

    public SimpleViewHolder(View itemView) {
        super(itemView);

        this.text = (TextView) itemView.findViewById(R.id.text);
    }
}
