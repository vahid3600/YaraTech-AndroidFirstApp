package com.vahid.yaratech_androidfirsttest.Retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vahid.yaratech_androidfirsttest.R;

import java.util.List;

/**
 * Created by Vah on 8/4/2018.
 */

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private List<User> users;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    UserRecyclerViewAdapter(Context context, ItemClickListener itemClickListener, List<User> users) {
        this.mInflater = LayoutInflater.from(context);
        this.mClickListener = itemClickListener;
        this.users = users;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = users.get(position).getName();
        String phone = users.get(position).getPhone();
        holder.name_textview.setText(name);
        holder.phone_textview.setText(phone);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return users.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name_textview;
        TextView phone_textview;

        ViewHolder(View itemView) {
            super(itemView);
            name_textview = itemView.findViewById(R.id.name);
            phone_textview = itemView.findViewById(R.id.phone_number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

