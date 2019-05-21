package com.dextroxd.hackeroyalefree.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.ParcelableSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dextroxd.hackeroyalefree.Activity.MainActivity;
import com.dextroxd.hackeroyalefree.Activity.ReadActivity;
import com.dextroxd.hackeroyalefree.Models.ListModel;
import com.dextroxd.hackeroyalefree.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

     List<ListModel> modelList;
    Activity activity;
    SharedPreferences shref;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    SharedPreferences.Editor editor;
    String key = "Key";
    Gson gson = new Gson();
    List<ListModel> starArrayList = new ArrayList<>();
    public ListAdapter(List<ListModel> modelList, Activity activity) {
        this.modelList = modelList;
        this.activity = activity;
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView,textView1,textView2;
        ImageButton imageButton,imageButton1,imageButton2;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView)

        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageinlist);
            textView = itemView.findViewById(R.id.texttitle);
//            textView1 = itemView.findViewById(R.id.textdesc);
            textView2 = itemView.findViewById(R.id.textread);
            imageButton = itemView.findViewById(R.id.sharelink);
            imageButton1 = itemView.findViewById(R.id.starred1);
            imageButton2 = itemView.findViewById(R.id.read1);
            cardView = itemView.findViewById(R.id.cardclick);


        }
    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(viewGroup.getContext(), R.layout.list_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.MyViewHolder myViewHolder, final int i) {
        final ListModel model = modelList.get(i);
        ReadActivity.listModels = modelList;
        shref=activity.getSharedPreferences("Mypref", Context.MODE_PRIVATE);
//        editor = shref.edit();
        String response=shref.getString(key , "null");
        if (!response.equals("null"))
            starArrayList = gson.fromJson(response,new TypeToken<List<ListModel>>(){}.getType());
        RequestQueue queue = Volley.newRequestQueue(activity);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, model.getmImage(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Picasso.get().load(Html.fromHtml(Html.fromHtml(response.getJSONObject("guid").getString("rendered")).toString()).toString()).into(myViewHolder.imageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        queue.add(jsonObjectRequest);

        myViewHolder.imageButton2.setColorFilter(Color.argb(255,128,0,0));
        myViewHolder.imageButton1.setColorFilter(Color.argb(255,0,0,0));
        myViewHolder.imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shref = activity.getSharedPreferences("Mypref",Context.MODE_PRIVATE);
                editor=shref.edit();
                if(shref.getBoolean(model.getDesc1(),true))
                {   myViewHolder.imageButton1.setColorFilter(Color.argb(255,225,223,0));

                    editor.putBoolean(model.getDesc1(),false);
                    starArrayList.add(model);
                    HashSet<ListModel> hashSet = new HashSet<>();
                    hashSet.addAll(starArrayList);
                    starArrayList.clear();
                    starArrayList.addAll(hashSet);
                    String json = gson.toJson(starArrayList);
                    Log.i(starArrayList.size()+"",starArrayList.size()+"");
                    editor.putString(key, json);
                    editor.apply();
                    Toast.makeText(activity,"You have starred this article.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    myViewHolder.imageButton1.setColorFilter(Color.argb(255,0,0,0));
                    editor.putBoolean(model.getDesc1(),true);
                    int i=starArrayList.indexOf(model);
                    if(i != 0)
                    {  starArrayList.remove(i+1);
                        notifyDataSetChanged();
//                        notifyItemRemoved(i+1);
//                        notifyItemRangeChanged(i+1,starArrayList.size());
                        HashSet<ListModel> hashSet = new HashSet<>();
                        hashSet.addAll(starArrayList);
                        starArrayList.clear();
                        starArrayList.addAll(hashSet);
                        }

                    else
                    {
                    starArrayList.remove(i);
                        notifyDataSetChanged();
//                                            notifyItemRemoved(i);
//                        notifyItemRangeChanged(i,starArrayList.size());

                        HashSet<ListModel> hashSet = new HashSet<>();
                        hashSet.addAll(starArrayList);
                        starArrayList.clear();
                        starArrayList.addAll(hashSet);
                    ;
//                    notifyItemRemoved(i);
//                    notifyItemRangeChanged(i,getItemCount());
                    }
//                    starArrayList.remove(model);
//                    notifyItemRemoved(i);
//                    notifyItemRangeChanged(i,starArrayList.size());

//                    starArrayList.remove(i);
                    HashSet<ListModel> hashSet = new HashSet<>();
                    hashSet.addAll(starArrayList);
                    starArrayList.clear();
                    starArrayList.addAll(hashSet);
                    String json = gson.toJson(starArrayList);
                    Log.i(starArrayList.size()+"",starArrayList.size()+"");
                    editor.putString(key, json);
                    editor.apply();
                    Toast.makeText(activity,"You have un-starred article.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(!shref.getBoolean(model.getmTitle(),true))
        {
            myViewHolder.imageButton2.setColorFilter(Color.argb(255,0,128,0));

        }
        if(!shref.getBoolean(model.getDesc1(),true))
        {
            myViewHolder.imageButton1.setColorFilter(Color.argb(255,225,223,0));
        }
        myViewHolder.textView.setText(model.getmTitle());
//        myViewHolder.textView1.setText(model.getDesc1());
        myViewHolder.textView2.setText(model.getReadTime());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//              ListModel listModel = model;
                Intent in = new Intent(activity, ReadActivity.class);
//              in.putExtra("title",listModel.getmTitle());
//              in.putExtra("text",listModel.getText1());
                in.putExtra("position",i);
                activity.startActivity(in);
            }
        });
        myViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder.from(activity)
                        .setType("text/plain")
                        .setChooserTitle("Send Via")
                        .setText(model.getDesc1())
                        .startChooser();
            }
        });
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
