package com.dextroxd.hackeroyalefree.Activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dextroxd.hackeroyalefree.Models.ListModel;
import com.dextroxd.hackeroyalefree.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReadActivity extends AppCompatActivity{
    public static List<ListModel> listModels;
    ImageButton imageButton1,imageButton2,imagebutton3,imageButton4,imageButton5;
    TextView textView;
    WebView textView1;
    ListModel model;
    String key = "Key";
    List<ListModel> starArrayList = new ArrayList<>();
    Gson gson = new Gson();
    SharedPreferences shref,sharedPreferences;
    SharedPreferences.Editor editor;
    ScrollView scrollView;
    boolean abc;
    int i=0;
    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        abc=sharedPreferences.getBoolean("isChecked",false);
//        if (AppCompatDelegate.getDefaultNightMode()
//                ==AppCompatDelegate.MODE_NIGHT_YES||abc)
//        {
//            setTheme(R.style.darkTheme);
//            }
//        else
//        {
//            setTheme(R.style.AppTheme);
//            }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Intent in = getIntent();
//        String title = in.getStringExtra("title");
//        String text = in.getStringExtra("text");
        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        shref=getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        String response=shref.getString(key , "null");
        if (!response.equals("null"))
        starArrayList = gson.fromJson(response,new TypeToken<List<ListModel>>(){}.getType());
        i =in.getIntExtra("position",0);
        model = listModels.get(i);
        size = listModels.size();
        textView = findViewById(R.id.textc1);
        textView1 = findViewById(R.id.desc1);
        textView.setText(model.getmTitle());
        textView1.getSettings().setJavaScriptEnabled(true);
        textView1.getSettings().setDisplayZoomControls(true);
        textView1.getSettings().setAllowFileAccess(true);
        textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        textView1.loadDataWithBaseURL("file:///android_asset/",getHtmlData(model.getText1().replace("\r\n", "<br/>")),"text/html","UTF-8",null);
        Timer repeatTask = new Timer();
        repeatTask.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int height =  (int)Math.floor(textView1.getContentHeight()*textView1.getResources().getDisplayMetrics().density);
                        int webViewHeight = textView1.getMeasuredHeight();
//        if (textView1.getScrollY() + webViewHeight >= height)
//        {
//            textView1.scrollBy(0, 0);
//            textView1.scrollTo(0, 0);
//        }
//        else
//        {
            textView1.scrollBy(textView1.getTop(), 3);
//        }
    }
});

        }
        }, 0, 50);
        imageButton1 = findViewById(R.id.LeftClick);
        imageButton2 = findViewById(R.id.RightClick);
        imagebutton3 = findViewById(R.id.starred);
        imageButton4 = findViewById(R.id.starre1);
        imageButton5 = findViewById(R.id.sharethis);
        if(!shref.getBoolean(model.getmTitle(),true))
        {
            imagebutton3.setColorFilter(Color.argb(255,0,128,0));

        }
        if(!shref.getBoolean(model.getDesc1(),true))
        {
            imageButton4.setColorFilter(Color.argb(255,225,223,0));
        }
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shref = getSharedPreferences("Mypref",Context.MODE_PRIVATE);
                editor=shref.edit();
                if(shref.getBoolean(model.getDesc1(),true))
                {   imageButton4.setColorFilter(Color.argb(255,225,223,0));
                    editor.putBoolean(model.getDesc1(),false);
                    starArrayList.add(model);
                    String json = gson.toJson(starArrayList);
                    Log.i(starArrayList.size()+"",starArrayList.size()+"");
                    editor.putString(key, json);
                    editor.apply();
                    Toast.makeText(ReadActivity.this,"You have starred this article.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    imageButton4.setColorFilter(Color.argb(255,0,0,0));
                    editor.putBoolean(model.getDesc1(),true);
                    int i=starArrayList.indexOf(model);
                    if(i != 0)
                        starArrayList.remove(i+1);
                    else
                        starArrayList.remove(i);
                    String json = gson.toJson(starArrayList);
                    Log.i(starArrayList.size()+"",starArrayList.size()+"");
                    editor.putString(key, json);
                    editor.apply();
                    Toast.makeText(ReadActivity.this,"You have un-starred this article.",Toast.LENGTH_SHORT).show();
                }


            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(i>0)
                {   imageButton1.setEnabled(true);
                    imageButton2.setEnabled(true);
                    i--;
                    model = listModels.get(i);
                    textView.setText(model.getmTitle());
                    if(!shref.getBoolean(model.getmTitle(),true))
                    {
                        imagebutton3.setColorFilter(Color.argb(255,0,128,0));

                    }
                    else{
                        imagebutton3.setColorFilter(Color.argb(255,128,0,0));
                    }
                    if(!shref.getBoolean(model.getDesc1(),true))
                    {
                        imageButton4.setColorFilter(Color.argb(255,225,223,0));
                    }else {
                        imageButton4.setColorFilter(Color.argb(255,0,0,0));
                    }
                    textView1.getSettings().setJavaScriptEnabled(true);
                    textView1.getSettings().setDisplayZoomControls(true);
                    textView1.getSettings().setAllowFileAccess(true);
                    textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                    textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
                    textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                    textView1.loadDataWithBaseURL("file:///android_asset/",getHtmlData(model.getText1().replace("\r\n", "<br/>")),"text/html","UTF-8",null);
                }
                else {
                    imageButton1.setEnabled(false);
                    imageButton2.setEnabled(true);
                }
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i<size-1)
                {   imageButton2.setEnabled(true);
                    imageButton1.setEnabled(true);
                    i++;
                    model = listModels.get(i);
                    textView.setText(model.getmTitle());
                    if(!shref.getBoolean(model.getmTitle(),true))
                    {
                        imagebutton3.setColorFilter(Color.argb(255,0,128,0));

                    }
                    else{
                        imagebutton3.setColorFilter(Color.argb(255,128,0,0));
                    }
                    if(!shref.getBoolean(model.getDesc1(),true))
                    {
                        imageButton4.setColorFilter(Color.argb(255,225,223,0));
                    }else {
                        imageButton4.setColorFilter(Color.argb(255,0,0,0));
                    }
                    textView1.getSettings().setJavaScriptEnabled(true);
                    textView1.getSettings().setDisplayZoomControls(true);
                    textView1.getSettings().setAllowFileAccess(true);
                    textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                    textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
                    textView1.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                    textView1.loadDataWithBaseURL("file:///android_asset/",getHtmlData(model.getText1().replace("\r\n", "<br/>")),"text/html","UTF-8",null);
                }
                else {
                    imageButton1.setEnabled(true);
                    imageButton2.setEnabled(false);
                }
            }
        });
        imagebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shref = getSharedPreferences("Mypref",Context.MODE_PRIVATE);
                editor=shref.edit();
                if(shref.getBoolean(model.getmTitle(),true))
                {
                    int i = shref.getInt("Progress",0);
                    i = i+1;
                    Log.e(i+"",i+"");
                    editor.putBoolean(model.getmTitle(),false);
                    editor.putInt("Progress",i);
                    editor.apply();
                    imagebutton3.setColorFilter(Color.argb(255,0,128,0));
                    Toast.makeText(ReadActivity.this,"You have completed this article.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ReadActivity.this,"You have already read article.",Toast.LENGTH_SHORT).show();
                }


            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder.from(ReadActivity.this)
                        .setType("text/plain")
                        .setChooserTitle("Send Via")
                        .setText(model.getDesc1())
                        .startChooser();
            }
        });
    }
    private String getHtmlData(String bodyHTML) {
//        String head = "<head><style type=\"text/css\"> *{box-sizing: border-box;} " +
//                "@font-face {\n" +
//                "    font-family: MyFont;\n" +
//                "    src: url(\"file:///android_asset/font/thsdr.ttf\")\n" +
//                "} img{max-width: 100%; width:auto; height: auto;} " +
//                "body {\n" +
//                "    font-family: MyFont;\n" +
//                "    font-size: medium;\n" +
//                "    text-align: justify;\n" +
//                "} pre{ white-space:pre-wrap; word-wrap:break-word; text-align:justify;} .td-post-content p{word-wrap: break-word;} </style></head>";
        String head = "<head><style type=\"text/css\">" +
                "@import url('https://fonts.googleapis.com/css?family=Lato:300,400,700,900');\n" +
                "\n"+
//                "*,\n"+
//                "*::after,\n"+
//                "*::before {\n"+
//                "  margin: 0;\n"+
//                "  padding: 0;\n"+
//                "  box-sizing: inherit;\n"+
//                "}\n"+
//                "\n"+
                "body {\n"
                +
//                "    box-sizing: border-box;\n" +
                "    font-family: \"Lato\", sans-serif;\n" +
                "    text-align: justify;\n" +
                "  }\n" +
                "\n" +
                "a,\n" +
                "a:visited,\n" +
                "a:active{\n" +
                "    display: inline-block;\n" +
                "    text-decoration: none;\n" +
                "    color: red;\n" +
                "}\n" +
                "\n"
                +"img{max-width: 100%; width:100%; height: auto;}"
                +"p iframe{max-width: 100%; width:100%; height: auto;}"+
                "pre{\n" +
                "  white-space: pre-wrap;\n" +
                "  word-wrap: break-word;\n" +
                "  text-align: justify;\n" +
                "}\n" +
                "\n" +
                "p a{ \n" +
                    "word-break: break-all;\n" +
                "}"+"p{ \n" +
                "word-wrap: break-word;\n" +
                "}"+
                " </style></head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
