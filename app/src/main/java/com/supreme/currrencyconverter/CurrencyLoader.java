package com.supreme.currrencyconverter;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class CurrencyLoader extends AsyncTaskLoader<List<Currency_Converter>> {
    /**
     * Query URL
     */
    private String mUrl;


    public CurrencyLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Currency_Converter> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of gitHub users.
        return QueryUtils.fetchCurrencyDta(mUrl);
    }
}