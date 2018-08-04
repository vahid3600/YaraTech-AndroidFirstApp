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

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder> {

    private List<Comment> comments;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    CommentRecyclerViewAdapter(Context context, List<Comment> comments) {
        this.mInflater = LayoutInflater.from(context);
        this.comments = comments;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.comment_item_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = comments.get(position).getName();
        String email = comments.get(position).getEmail();
        String body = comments.get(position).getBody();
        holder.name_textview.setText(name);
        holder.email_textview.setText(email);
        holder.body_textview.setText(body);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return comments.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_textview;
        TextView email_textview;
        TextView body_textview;

        ViewHolder(View itemView) {
            super(itemView);
            name_textview = itemView.findViewById(R.id.name);
            email_textview = itemView.findViewById(R.id.email);
            body_textview = itemView.findViewById(R.id.body);
        }
    }
}
