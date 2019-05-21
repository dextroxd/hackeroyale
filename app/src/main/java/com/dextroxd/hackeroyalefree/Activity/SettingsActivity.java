package com.dextroxd.hackeroyalefree.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dextroxd.hackeroyalefree.R;

public class SettingsActivity extends AppCompatActivity {
    private SwitchCompat switchCompat;
    boolean abc;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        abc=sharedPreferences.getBoolean("isChecked",false);
        if (AppCompatDelegate.getDefaultNightMode()
                ==AppCompatDelegate.MODE_NIGHT_YES||abc) {
            setTheme(R.style.darkTheme);
        }
        else
        {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switchCompat = findViewById(R.id.myswitch);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES||abc)
        {
            switchCompat.setChecked(true);
        }
        LinearLayout linearLayout = findViewById(R.id.rate);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        LinearLayout linearLayout1 = findViewById(R.id.share);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder.from(SettingsActivity.this)
                        .setType("text/plain")
                        .setChooserTitle("Send via")
                        .setText("http://play.google.com/store/apps/details?id=" + SettingsActivity.this.getPackageName())
                        .startChooser();
            }
        });

        final SharedPreferences.Editor editor = sharedPreferences.edit();

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    editor.putBoolean("isChecked",true);
                    editor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    overridePendingTransition(0,0);
                    startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                    overridePendingTransition(0,0);
                    finish();


                }
                else
                {   editor.putBoolean("isChecked",false);
                    editor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    overridePendingTransition(0,0);
                    startActivity(new Intent(SettingsActivity.this,MainActivity.class));
                    overridePendingTransition(0,0);
                    finish();

                }
            }
        });
//        String tmpHtml = "<b><img src=\"http://innovativeprofessionaloffices.com/wp-content/uploads/2014/07/seo-for-small-business.jpg\" alt=\"testing\"></b>";
//        String htmlTextStr = Html.fromHtml(tmpHtml).toString();
//        WebView Description = findViewById(R.id.hellotest);
//        Description.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        WebSettings webSettings = Description.getSettings();
//        Description.loadDataWithBaseURL(null,tmpHtml,"text/html", "UTF-8",null);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingsActivity.this,MainActivity.class));
        finish();
    }
}
