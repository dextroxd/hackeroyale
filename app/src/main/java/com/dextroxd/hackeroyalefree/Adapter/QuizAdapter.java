
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dextroxd.hackeroyalefree.Activity.MainActivity;
import com.dextroxd.hackeroyalefree.Activity.QuizActivity;
import com.dextroxd.hackeroyalefree.Activity.ReadActivity;
import com.dextroxd.hackeroyalefree.Models.ListModel;
import com.dextroxd.hackeroyalefree.Models.QuestionModel;
import com.dextroxd.hackeroyalefree.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {

    List<QuestionModel>modelList;
    Activity activity;
    QuestionModel model;
    String Correct;
    RadioButton radioButton;
    QuizActivity yoyo;
    public QuizAdapter(List<QuestionModel> modelList, Activity activity) {
        this.modelList = modelList;
        this.activity = activity;
    }





    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
        RadioGroup radioGroup;
        Button button;

        public MyViewHolder(@NonNull View itemView)

        {
            super(itemView);
            textView = itemView.findViewById(R.id.question1);
            radioButton1 = itemView.findViewById(R.id.option1);
            radioButton2 = itemView.findViewById(R.id.option2);
            radioButton3 = itemView.findViewById(R.id.option3);
            radioButton4 = itemView.findViewById(R.id.option4);
            radioGroup = itemView.findViewById(R.id.options);
            button = itemView.findViewById(R.id.check1);

        }
    }

    @NonNull
    @Override
    public QuizAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(viewGroup.getContext(), R.layout.cardquiz, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuizAdapter.MyViewHolder myViewHolder, final int i) {
        model = modelList.get(myViewHolder.getAdapterPosition());
       myViewHolder.textView.setText(model.getQuestion());
       myViewHolder.radioButton1.setText(model.getOption1());
       myViewHolder.radioButton2.setText(model.getOption2());
        Correct = model.getCorrect();
                if(model.getOption3().equals("nil")){
            myViewHolder.radioButton3.setVisibility(View.GONE);
        }
        else{
        myViewHolder.radioButton3.setText(model.getOption3());}
        if(model.getOption4().equals("nil")){
            myViewHolder.radioButton4.setVisibility(View.GONE);
        }
        else{
        myViewHolder.radioButton4.setText(model.getOption4());}

       myViewHolder.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(myViewHolder.radioGroup.getCheckedRadioButtonId()==-1){
                   Toast.makeText(activity,"Please select an option",Toast.LENGTH_SHORT).show();
                   return;
               }
               int id = myViewHolder.radioGroup.getCheckedRadioButtonId();
               radioButton = myViewHolder.radioGroup.findViewById(id);
              if(radioButton.getText().equals(yoyo.Correct.get(i))){
                  yoyo.correct+=1;
                  Toast.makeText(activity,"Correct Option",Toast.LENGTH_SHORT).show();
              }
              else {
                  yoyo.incorrect+=1;
                  Toast.makeText(activity,"Incorrect Option",Toast.LENGTH_SHORT).show();
              }


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

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
