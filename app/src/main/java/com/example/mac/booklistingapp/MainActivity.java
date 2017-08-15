package com.example.mac.booklistingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BooksAdapter adapter;

    public static final String LOG_TAG= MainActivity.class.getName();

    private static final String JSON_URL =
            "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=40";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listBooks=(ListView)findViewById(R.id.ListView);

        adapter=new BooksAdapter(getBaseContext(),R.layout.activity_main,new ArrayList<BookItems>());

        listBooks.setAdapter(adapter);

        //Missing new AsyncTask
        new BookItemsAsyncTask().execute(JSON_URL);
    }
    private class BookItemsAsyncTask extends AsyncTask<String,Void,List<BookItems>> {

        @Override
        protected List<BookItems> doInBackground(String... urls) {
            //Create the Url object
            URL url=QueryUtils.createUrl(urls[0]);
            try{
                String jsonResponse=QueryUtils.makeHttpRequest(url);
                ArrayList<BookItems> books=QueryUtils.extractBookItems(jsonResponse);
                return books;
            } catch (IOException e) {
                Log.e(LOG_TAG, "No answer",e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<BookItems> data) {
            adapter.clear();
            adapter.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }
}




