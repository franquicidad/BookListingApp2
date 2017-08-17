package com.example.mac.booklistingapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookItems>{

    private BooksAdapter adapter;

    public static final String LOG_TAG= MainActivity.class.getName();

    private static final String JSON_URL =
            "https://www.googleapis.com/books/v1/volumes?maxResults=40&q=";

    private ListView listBooks;
    private EditText editText;
    private Button search;

    private String mUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listBooks=(ListView)findViewById(R.id.ListView);
        editText=(EditText) findViewById(R.id.Edit);
        search=(Button)findViewById(R.id.Search);

        adapter=new BooksAdapter(getBaseContext(),R.layout.activity_main,new ArrayList<BookItems>());

        listBooks.setAdapter(adapter);

        //Missing new AsyncTask
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=editText.getText().toString();
                //Missing new AsyncTask
                new BookItemsAsyncTask().execute(JSON_URL+query);

            }
        });
    }

    @Override
    public Loader<List<BookItems>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<BookItems>> loader, List<BookItems> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<BookItems>> loader) {

    }


    private class BookItemsLoader extends AsyncTaskLoader<List<BookItems>> {

        public BookItemsLoader(Context context,String url) {
            super(context);
            mUrl=url;
        }

//        @Override
//        protected List<BookItems> doInBackground(String... urls) {
//            //Create the Url object
//            URL url=QueryUtils.createUrl(urls[0]);
//            try{
//                String jsonResponse=QueryUtils.makeHttpRequest(url);
//                ArrayList<BookItems> books=QueryUtils.extractBookItems(jsonResponse);
//                return books;
//            } catch (IOException e) {
//                Log.e(LOG_TAG, "No answer",e);
//            }
//            return null;
//        }

//        @Override
//        protected void onPostExecute(List<BookItems> data) {
//
//        }

        @Override
        public List<BookItems> loadInBackground() {
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
        protected void onStartLoading() {
            forceLoad();
        }
    }
}




