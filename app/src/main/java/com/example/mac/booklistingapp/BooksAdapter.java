package com.example.mac.booklistingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 10/08/17.
 */

public class BooksAdapter extends ArrayAdapter<BookItems> {

    private TextView title;
    private TextView description;
    private TextView date;

    public BooksAdapter(Context context, List<BookItems> objects) {
        super(context,0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.book_item_design, parent, false);
        }

        BookItems bookItemPosition = getItem(position);

        ImageView bookImage=(ImageView)v.findViewById(R.id.book_image);

        title = (TextView) v.findViewById(R.id.title);
        title.setText("Title:"+bookItemPosition.getTitle());

        description = (TextView) v.findViewById(R.id.description);
        description.setText(bookItemPosition.getDescription());

        date = (TextView) v.findViewById(R.id.published_date);
        date.setText("Release Date:"+bookItemPosition.getDate());



        Picasso.with(getContext()).load(bookItemPosition.getLink()).into(bookImage);

       // new DownloadImagesTask(bookImage).execute(bookItemPosition.getLink());


        return v;
    }
//    public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {
//
//        ImageView imageView;
//
//        public DownloadImagesTask(ImageView imageView){
//            imageView = imageView;
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//            imageView = imageView;
//            return download_Image(urls[0]);
//        }
//
//
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            imageView.setImageBitmap(result);              // how do I pass a reference to mChart here ?
//        }
//
//        private Bitmap download_Image(String url) {
//            Bitmap bm = null;
//            try {
//                URL aURL = new URL(url);
//                URLConnection conn = aURL.openConnection();
//                conn.connect();
//                InputStream is = conn.getInputStream();
//                BufferedInputStream bis = new BufferedInputStream(is);
//                bm = BitmapFactory.decodeStream(bis);
//                bis.close();
//                is.close();
//            } catch (IOException e) {
//                Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
//            }
//            return bm;
//        }
    }
