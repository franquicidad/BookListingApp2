package com.example.mac.booklistingapp;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by mac on 21/08/17.
 */

public class BookItemsAsyncTaskLoader extends AsyncTaskLoader<List<BookItems>> {

    private String mUrl;

    public BookItemsAsyncTaskLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public List<BookItems> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<BookItems> bookItems = null;
        // Perform the network request, parse the response, and extract a list of earthquakes.
        bookItems = QueryUtils.fetchBookList(mUrl);
        return bookItems;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
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
//            adapter.clear();
//            adapter.addAll(data);
//            adapter.notifyDataSetChanged();
//        }
