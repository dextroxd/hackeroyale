package com.dextroxd.hackeroyalefree.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
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

import com.dextroxd.hackeroyalefree.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class TutorialActivity extends AppCompatActivity {
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9,cardView10,cardView11,cardView12,cardView13,cardView14;
    int page = 0;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigation;
    Intent PostAdIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawer = findViewById(R.id.drawer);
        mNavigation = findViewById(R.id.nav_view);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
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
                        ShareCompat.IntentBuilder.from(TutorialActivity.this)
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
        cardView1 = findViewById(R.id.first);
        cardView2 = findViewById(R.id.second);
        cardView3 = findViewById(R.id.third);
        cardView4 = findViewById(R.id.fourth);
        cardView5 = findViewById(R.id.fifth);
        cardView6 = findViewById(R.id.sixth);
        cardView7 = findViewById(R.id.seventh);
        cardView8 = findViewById(R.id.eighth);
        cardView9 = findViewById(R.id.ninth);
        cardView10 = findViewById(R.id.tenth);
        cardView11 = findViewById(R.id.eleventh);
        cardView12 = findViewById(R.id.tweleveth);
        cardView13 = findViewById(R.id.thirteenth);
        cardView14 = findViewById(R.id.founteenth);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=0;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=1;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=2;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=3;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=4;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=5;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });

        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=6;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=7;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=8;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=9;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=10;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=11;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });
        cardView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=12;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
            }
        });
        cardView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=13;
                Intent intent = new Intent(TutorialActivity.this,ArticleActivity.class);
                intent.putExtra("One", page);// One is your argument
                startActivity(intent);
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

}
