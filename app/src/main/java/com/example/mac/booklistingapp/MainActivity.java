package com.example.mac.booklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.app.LoaderManager;

import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookItems>> {

    private BooksAdapter adapter;

    public static final String LOG_TAG = MainActivity.class.getName();

    private static final String JSON_URL =
            "https://www.googleapis.com/books/v1/volumes?maxResults=40&q=";

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;

    private ListView listBooks;
    private EditText editText;
    private Button search;

    private TextView mEmptyStateTextView;

    private NetworkInfo networkInfo;

    private ConnectivityManager cm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listBooks = (ListView) findViewById(R.id.ListView);
        editText = (EditText) findViewById(R.id.Edit);
        search = (Button) findViewById(R.id.Search);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listBooks.setEmptyView(mEmptyStateTextView);


        adapter = new BooksAdapter(getBaseContext(), new ArrayList<BookItems>());

        //Get details on the currently active default data network
        networkInfo = cm.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, MainActivity.this);
        } else {
            mEmptyStateTextView.setText(R.string.no_internet);
        }

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, MainActivity.this);

        listBooks.setAdapter(adapter);



        //Missing new AsyncTask

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Missing new AsyncTask
                LoaderManager loaderManager = getSupportLoaderManager();
                loaderManager.restartLoader(EARTHQUAKE_LOADER_ID, null, MainActivity.this);
            }
        });
    }

    @Override
    public Loader<List<BookItems>> onCreateLoader(int i, Bundle bundle) {
        String query = editText.getText().toString();

        return new BookItemsAsyncTaskLoader(this, JSON_URL + query);
    }

    @Override
    public void onLoadFinished(Loader<List<BookItems>> loader, List<BookItems> data) {
        adapter.clear();
        if (data != null) {
            adapter.addAll(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<BookItems>> loader) {
        adapter.clear();

    }
}






