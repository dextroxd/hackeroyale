package com.dextroxd.hackeroyalefree.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.dextroxd.hackeroyalefree.R;

public class SliderIntro extends AppCompatActivity {
    private TypeWriter tw;
    boolean abc;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        abc=sharedPreferences.getBoolean("isChecked",false);
//        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES||abc)
//        {
//            setTheme(R.style.darkTheme);
//        }
//        else
//        {
//            setTheme(R.style.AppTheme);
//        }
        super.onCreate(savedInstanceState);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_slider_intro);
        tw = findViewById(R.id.tv);
        tw.setText("");
        tw.setCharacterDelay(150);
        tw.animateText("Welcome to HackeRoyale.");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SliderIntro.this,MainActivity.class));
                finish();
            }
        },4000);


    }
}
