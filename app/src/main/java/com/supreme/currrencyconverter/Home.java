package com.supreme.currrencyconverter;

import android.app.LoaderManager;
import android.content.Context;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, LoaderCallbacks<List<Currency_Converter>> {

    private static final String CRYPTO_CURRENCY_URI = "https://api.github.com/search/users?q=language:java%20location:lagos";
    @SuppressWarnings("SpellCheckingInspection")
    private static final int CRYPTO_CURRENCY_URI_ID = 1;
    private CurrencyAdapter mAdapter;
    private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ListView currencyListView = (ListView) findViewById(R.id.list);
        //CardView cardView = (CardView) findViewById(R.id.drawer_layout);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        currencyListView.setEmptyView(mEmptyStateTextView);
        mAdapter = new CurrencyAdapter(this, new ArrayList<Currency_Converter>());
        currencyListView.setAdapter(mAdapter);


        currencyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Currency_Converter currentGitHubUsers = mAdapter.getItem(position);

//                Intent intent = new Intent(GitHub_Users_LagosActivity.this, UserDetailsActivity.class);
//
//                assert currentGitHubUsers != null;
//                intent.putExtra("user_html_uri", currentGitHubUsers.getmUrl());
//                intent.putExtra("userImage", currentGitHubUsers.getmUserImage());
//                intent.putExtra("userName", currentGitHubUsers.getmUserName());
//
//                startActivity(intent);
            }
        });

        if (!isNetworkAvailable()) {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
//            Snackbar snackbar = Snackbar
//                    .make(cardView, getString(R.string.no_internet_connection), Snackbar.LENGTH_INDEFINITE)
//                    .setAction("RETRY", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            View loadingIndicator = findViewById(R.id.loading_indicator);
//                            loadingIndicator.setVisibility(View.VISIBLE);
//                            LoaderManager loaderManager = getLoaderManager();
//                            loaderManager.initLoader(CRYPTO_CURRENCY_URI_ID, null, Home.this);
//                        }
//                    });
//
//            snackbar.show();
        } else {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(CRYPTO_CURRENCY_URI_ID, null, this);
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            // Handle the camera action
        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public Loader<List<Currency_Converter>> onCreateLoader(int id, Bundle args) {


        Uri baseUri = Uri.parse(CRYPTO_CURRENCY_URI);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        return new CurrencyLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Currency_Converter>> loader, List<Currency_Converter> data) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_currency_available);

        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Currency_Converter>> loader) {
        mAdapter.clear();
    }
}