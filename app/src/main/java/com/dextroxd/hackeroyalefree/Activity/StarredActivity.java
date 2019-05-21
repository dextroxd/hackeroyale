package com.dextroxd.hackeroyalefree.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.dextroxd.hackeroyalefree.Models.ListModel;
import com.dextroxd.hackeroyalefree.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class StarredActivity extends AppCompatActivity {
    String key="Key";
    SharedPreferences shref;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;
    com.dextroxd.hackeroyalefree.Adapter.ListAdapter listAdapter;
    RecyclerView recyclerView;
    List<ListModel> starArrayList;
    ImageButton imageButton;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        if (AppCompatDelegate.getDefaultNightMode()
//                == AppCompatDelegate.MODE_NIGHT_YES) {
//            setTheme(R.style.darkTheme);
//        } else {
//            setTheme(R.style.AppTheme);
//        }
        setContentView(R.layout.activity_starred);
        Gson gson = new Gson();
        shref = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        String response=shref.getString(key , "null");
        starArrayList = new ArrayList<>();
        if (!response.equals("null"))
        starArrayList = gson.fromJson(response,new TypeToken<List<ListModel>>(){}.getType());
        listAdapter = new com.dextroxd.hackeroyalefree.Adapter.ListAdapter(starArrayList, this);
        recyclerView = findViewById(R.id.recycler1);
        recyclerView.setAdapter(listAdapter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter.notifyDataSetChanged();
        progressBar = findViewById(R.id.progress1);
        imageButton = findViewById(R.id.refreshbutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                progressBar.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.VISIBLE);
            }
        });
    }
}
