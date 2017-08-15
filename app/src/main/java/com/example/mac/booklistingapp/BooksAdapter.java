package com.example.mac.booklistingapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 10/08/17.
 */

public class BooksAdapter extends ArrayAdapter<BookItems> {

    private TextView title;
    private TextView description;
    private TextView date;

    public BooksAdapter(Context context, int resource, List<BookItems> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.book_item_design, parent, false);
        }

        BookItems bookItemPosition = getItem(position);

        title = (TextView) v.findViewById(R.id.title);
        title.setText(bookItemPosition.getTitle());

        description = (TextView) v.findViewById(R.id.description);
        description.setText(bookItemPosition.getDescription());

        date = (TextView) v.findViewById(R.id.published_date);
        date.setText(bookItemPosition.getDate());


        return v;
    }
}

