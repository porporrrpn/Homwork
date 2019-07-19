package com.example.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private  List<Book> mBooks;


    public  ImageAdapter(Context context,List<Book> books){
        this.mContext = context;
        this.mBooks = books;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent, false);
        return  new ImageViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        Book uploadCurrent = mBooks.get(position);
        holder.textViewTitle.setText(uploadCurrent.getTitle());
        holder.textViewMessge.setText(uploadCurrent.getMessage());
        Picasso.with(mContext)
                .load(uploadCurrent.getPicture())
                .fit()
                .centerCrop()
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public  class ImageViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewTitle;
        public ImageView imageView;
        public  TextView textViewMessge;



        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.title_textView);
            textViewMessge = itemView.findViewById(R.id.message_textView);
            imageView = itemView.findViewById(R.id.imageView);



        }


    }


}
