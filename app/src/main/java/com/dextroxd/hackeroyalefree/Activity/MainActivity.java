package com.dextroxd.hackeroyalefree.Activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ShareCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.dextroxd.hackeroyalefree.R;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    boolean abc;
    SharedPreferences sharedPreferences;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigation;
    private InterstitialAd interstitialAd;
    Intent PostAdIntent;
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.nav_menu, menu);
//
//        return true;
//    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public void onBackPressed() {
//        finishAndRemoveTask();
//    }


    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        abc=sharedPreferences.getBoolean("isChecked",false);
//        if (AppCompatDelegate.getDefaultNightMode()
//                ==AppCompatDelegate.MODE_NIGHT_YES||abc)
//        {
//            setTheme(R.style.darkTheme);
//
//        }
//        else
//        {
//            setTheme(R.style.AppTheme);
//
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial));
        AdRequest request = new AdRequest.Builder().build();
        interstitialAd.loadAd(request);

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
                        ShareCompat.IntentBuilder.from(MainActivity.this)
                                .setType("text/plain")
                                .setChooserTitle("Send via")
                                .setText("http://play.google.com/store/apps/details?id=" + getPackageName())
                                .startChooser();
                        item.setChecked(false);
                        break;
                    case R.id.premium:
                        Uri uri1 = Uri.parse("market://details?id=" + "com.dextroxd.hackeroyale");
                        Intent goToMarket1 = new Intent(Intent.ACTION_VIEW, uri1);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket1.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket1);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + "com.dextroxd.hackeroyale")));
                        }

                        item.setChecked(false);


                        break;



                }
                return true;
            }
        });
//        ArcProgress arcProgress = findViewById(R.id.progressbar1);
//        arcProgress.setProgress(sharedPreferences.getInt("Progress",0)/3);
        DonutProgress donutProgress = findViewById(R.id.progressbar1);
        float res = sharedPreferences.getInt("Progress",0)*100/174f;
        DecimalFormat df = new DecimalFormat("#.#");

        donutProgress.setProgress(Float.parseFloat(df.format(res)));
        CardView cardView = findViewById(R.id.quiz);
        CardView cardView1 = findViewById(R.id.read);
        CardView cardView2 = findViewById(R.id.settings);
        CardView cardView3 = findViewById(R.id.contactus);
        CardView cardView4 = findViewById(R.id.bookmarked);
        ImageView imageView,imageView1,imageView2,imageView3;
        imageView = findViewById(R.id.facebook);
//        imageView1 = findViewById(R.id.youtube);
        imageView2 = findViewById(R.id.amazon);
        imageView3 = findViewById(R.id.website1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(Intent.ACTION_VIEW);
                o.setData(Uri.parse("https://www.facebook.com/hackeroyale"));
                startActivity(o);


            }
        });
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent o = new Intent(Intent.ACTION_VIEW);
//                o.setData(Uri.parse("https://www.youtube.com/channel/UCyrPTioloQHbmOdCICH85tw"));
//                startActivity(o);
//
//            }
//        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(Intent.ACTION_VIEW);
                o.setData(Uri.parse("https://www.amazon.com/Pro-hackers-Guide-Hacking-hacking-right/dp/1721871594/ref=mp_s_a_1_4?ie=UTF8&qid=1546927864&sr=8-4&pi=AC_SX236_SY340_FMwebp_QL65&keywords=pro+hackers+guide&dpPl=1&dpID=41r3aQxt3CL&ref=plSrch"));
                startActivity(o);

            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(Intent.ACTION_VIEW);
                o.setData(Uri.parse("http://www.hackeroyale.com"));
                startActivity(o);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostAdIntent = new Intent(MainActivity.this,QuizActivity.class);
                ShowAd();

            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostAdIntent = new Intent(MainActivity.this,TutorialActivity.class);
                ShowAd();
//                startActivity(new Intent(MainActivity.this, TutorialActivity.class));
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
//                finish();
                ShareCompat.IntentBuilder.from(MainActivity.this)
                        .setType("text/plain")
                        .setChooserTitle("Send via")
                        .setText("http://play.google.com/store/apps/details?id=" + getPackageName())
                        .startChooser();
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String[] arr = {"admin@hackeroyale.com"};
//                Intent intent = new Intent(Intent.ACTION_SENDTO);
//                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//                intent.putExtra(Intent.EXTRA_EMAIL, arr);
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Help");
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);

//                }
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

            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostAdIntent = new Intent(MainActivity.this, StarredActivity.class);
                ShowAd();

            }
        });

    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        item.setChecked(false);
//        switch (id) {
//            case R.id.aboutus:
//                Intent o = new Intent(Intent.ACTION_VIEW);
//                o.setData(Uri.parse("https://www.hackeroyale.com/about-us/"));
//                startActivity(o);
//                break;
//            case R.id.disclaimer:
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("https://www.hackeroyale.com/disclaimer/"));
//                startActivity(i);
//                break;
//            case R.id.privacy:
//                Intent j = new Intent(Intent.ACTION_VIEW);
//                j.setData(Uri.parse("https://www.hackeroyale.com/privacy/"));
//                startActivity(j);
//                break;
//            case R.id.help1:
//                Intent k = new Intent(Intent.ACTION_VIEW);
//                k.setData(Uri.parse("https://www.hackeroyale.com/contact/"));
//                startActivity(k);
//                break;
//            case R.id.settings1:
//                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
//                break;
//
//
//        }
//        return true;
//    }

    @Override
    protected void onResume() {
//        ArcProgress arcProgress = findViewById(R.id.progressbar1);
//        arcProgress.setProgress(sharedPreferences.getInt("Progress",0)/3);
        DonutProgress donutProgress = findViewById(R.id.progressbar1);
        float res = sharedPreferences.getInt("Progress",0)*100/174f;
        DecimalFormat df = new DecimalFormat("#.#");

        donutProgress.setProgress(Float.parseFloat(df.format(res)));
        super.onResume();
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
    public void ShowAd(){
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    if (PostAdIntent != null)
                    {
                        startActivity(PostAdIntent);
                        PostAdIntent = null;

                    }
                }
            });

        } else
        {
            if (PostAdIntent != null)
            {
                startActivity(PostAdIntent);
                PostAdIntent = null;

            }
        }
    }
}
