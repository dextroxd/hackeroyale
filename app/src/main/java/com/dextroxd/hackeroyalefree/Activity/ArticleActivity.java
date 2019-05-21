package com.dextroxd.hackeroyalefree.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dextroxd.hackeroyalefree.R;
import com.dextroxd.hackeroyalefree.Fragments.SearchFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ArticleActivity extends AppCompatActivity {
    boolean abc;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;
    ImageButton imageButton,imageButton1;
    TabLayout tabLayout;
    ViewPager viewPager;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        abc = sharedPreferences.getBoolean("isChecked", false);
        super.onCreate(savedInstanceState);
        int defaultValue = 0;
        int page = getIntent().getIntExtra("One", defaultValue);
        setContentView(R.layout.activity_article);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        mDrawer = findViewById(R.id.drawer);
        mNavigation = findViewById(R.id.nav_view);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        mDrawer.requestLayout();
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(false);
                mDrawer.closeDrawers();
                switch (id) {
                    case R.id.aboutus:
                        Intent o = new Intent(Intent.ACTION_VIEW);
                        o.setData(Uri.parse("https://www.hackeroyale.com/about-us/"));
                        startActivity(o);
                        item.setChecked(false);
                        break;
                    case R.id.disclaimer:
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://www.hackeroyale.com/disclaimer/"));
                        startActivity(i);
                        item.setChecked(false);
                        break;
                    case R.id.privacy:
                        Intent j = new Intent(Intent.ACTION_VIEW);
                        j.setData(Uri.parse("https://www.hackeroyale.com/privacy/"));
                        startActivity(j);
                        item.setChecked(false);
                        break;
                    case R.id.help1:
                        Intent k = new Intent(Intent.ACTION_VIEW);
                        k.setData(Uri.parse("https://www.hackeroyale.com/contact/"));
                        startActivity(k);
                        item.setChecked(false);
                        break;
                    case R.id.rate1:
                        Uri uri = Uri.parse("market://details?id=" + getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                        }

                        item.setChecked(false);
                        break;
                    case R.id.share1:
                        ShareCompat.IntentBuilder.from(ArticleActivity.this)
                                .setType("text/plain")
                                .setChooserTitle("Send via")
                                .setText("http://play.google.com/store/apps/details?id=" + getPackageName())
                                .startChooser();
                        item.setChecked(false);
                        break;



                }
                return true;
            }
        });
        progressBar = findViewById(R.id.progress1);
        imageButton = findViewById(R.id.refreshbutton);
        imageButton1 = findViewById(R.id.search_bar);
        viewPager = findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(simpleFragmentPagerAdapter);
        tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setTabGravity(1);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(page);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                finish();
                int position = viewPager.getCurrentItem();
                overridePendingTransition(0, 0);
                Intent intent = new Intent(getBaseContext(),ArticleActivity.class);
                intent.putExtra("One",position);
                startActivity(intent);
                overridePendingTransition(0, 0);
                progressBar.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.VISIBLE);
            }
        });
        if(!isNetworkConnected())
        {
//            Toast.makeText(this,"Please check your internet connection and try again. ",Toast.LENGTH_SHORT).show();
            Snackbar.make(findViewById(android.R.id.content),"Please check your internet connection and try again.", Snackbar.LENGTH_LONG).show();
            return;
        }
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SearchFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        super.onResume();

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
