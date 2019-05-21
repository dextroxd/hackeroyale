package com.dextroxd.hackeroyalefree.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.dextroxd.hackeroyalefree.Activity.ReadActivity;
import com.dextroxd.hackeroyalefree.Activity.RecyclerItemClickListener;
import com.dextroxd.hackeroyalefree.Models.ListModel;
import com.dextroxd.hackeroyalefree.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Web extends Fragment {
    private LinearLayoutManager linearLayoutManager;
    String mTitle, mExcerpt, mText, mImage, mReadtime;
    ProgressBar progressBar;
    ArrayList<ListModel> listModels;
    com.dextroxd.hackeroyalefree.Adapter.ListAdapter listAdapter;
    RecyclerView recyclerView;
    JsonArrayRequest jsonArrayRequest;
    RequestQueue queue;
    String url;
    int p=1;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public Web() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listModels = new ArrayList<>();
//        if (AppCompatDelegate.getDefaultNightMode()
//                == AppCompatDelegate.MODE_NIGHT_YES) {
//            getActivity().setTheme(R.style.darkTheme);
//        } else {
//            getActivity().setTheme(R.style.AppTheme);
//        }
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        progressBar = view.findViewById(R.id.progressbarc);
        listAdapter = new com.dextroxd.hackeroyalefree.Adapter.ListAdapter(listModels, getActivity());
        recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setAdapter(listAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter.notifyDataSetChanged();

        url = "https://www.hackeroyale.com/wp-json/wp/v2/posts?categories=142&page=1";
        getJson(url);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(linearLayoutManager.findLastCompletelyVisibleItemPosition()==listModels.size()-1){
                    p++;

                    getJson("https://www.hackeroyale.com/wp-json/wp/v2/posts?categories=142&page="+p);

                }
            }
        });




        return view;
    }
    public void getJson(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                try {

                    progressBar.setVisibility(View.VISIBLE);

                    for (int i = 0; i < response.length(); i++) {

                        mTitle=Html.fromHtml(response.getJSONObject(i).getJSONObject("title").getString("rendered")).toString()+ '\n';
                        mExcerpt=(Html.fromHtml(Html.fromHtml(response.getJSONObject(i).getString("link")).toString()).toString() + '\n');
                        mText=response.getJSONObject(i).getJSONObject("content").getString("rendered") + '\n';
                        mImage=("https://www.hackeroyale.com/wp-json/wp/v2/media/"+Html.fromHtml(String.valueOf(response.getJSONObject(i).getInt("featured_media"))));
                        mReadtime=("2 min");
//                        sharedPreferences = getContext().getSharedPreferences("Mypref", Context.MODE_PRIVATE);
//                        editor = sharedPreferences.edit();
//                        editor.putBoolean(mTitle,false);
//                        editor.putBoolean(mExcerpt,false);
//                        editor.commit();
//                        editor.apply();
                        listModels.add(new ListModel(mTitle, mImage, mReadtime, mText, mExcerpt));
                    }

                    progressBar.setVisibility(View.INVISIBLE);
                    listAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(jsonArrayRequest);
    }

}
