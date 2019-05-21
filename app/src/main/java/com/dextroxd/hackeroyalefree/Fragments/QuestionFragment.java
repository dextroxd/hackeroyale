package com.dextroxd.hackeroyalefree.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dextroxd.hackeroyalefree.Activity.QuizActivity;
import com.dextroxd.hackeroyalefree.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    QuizActivity activity;
    TextView textView;
    RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    RadioGroup radioGroup;
    Button button,button1,button2;
    String question,option1,option2,option3,option4,correct;
    ViewPager viewPager;
    LinearLayout linearLayout1,linearLayout2;
    CardView cardView;
    ImageButton imageButton;
    int position=0;
    TextView textView1,textView2;
    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        question = getArguments().getString("question");
        option1 = getArguments().getString("option1");
        option2 = getArguments().getString("option2");
        option3 = getArguments().getString("option3");
        option4 = getArguments().getString("option4");
        correct = getArguments().getString("correct");
        position = getArguments().getInt("position");
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        viewPager = getActivity().findViewById(R.id.pager);
        textView = view.findViewById(R.id.question1);
        radioButton1 = view.findViewById(R.id.option1);
        radioButton2 = view.findViewById(R.id.option2);
        radioButton3 = view.findViewById(R.id.option3);
        radioButton4 = view.findViewById(R.id.option4);
        radioGroup = view.findViewById(R.id.options);
        button = view.findViewById(R.id.check1);
        button1 = view.findViewById(R.id.exit1);
        button2=view.findViewById(R.id.next1);
        linearLayout1 = view.findViewById(R.id.correct1);
        linearLayout2 = view.findViewById(R.id.incorrect1);
        textView1 = view.findViewById(R.id.unread);
        linearLayout1.setVisibility(View.INVISIBLE);
        linearLayout2.setVisibility(View.INVISIBLE);
        textView.setText(question);
        radioButton1.setText(option1);
        radioButton2.setText(option2);
        textView1.setText(position+"/30");
                if(option3.equals("nil")){
            radioButton3.setVisibility(View.GONE);
        }
        else{
      radioButton3.setText(option3);}
        if(option4.equals("nil")){
            radioButton4.setVisibility(View.GONE);
        }
        else{
            radioButton4.setText(option4);}
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    int id = radioGroup.getCheckedRadioButtonId();
                    if(id==-1){
                        Toast.makeText(getActivity(),"Please select an option",Toast.LENGTH_SHORT).show();

                    return;
                    }
                    button.setVisibility(View.GONE);
                    RadioButton radioButton = radioGroup.findViewById(id);
                    if(radioButton.getText().equals(correct)){
                        activity.correct+=1;
                        linearLayout1.setVisibility(View.VISIBLE);
                        for (int i = 0; i < radioGroup.getChildCount(); i++) {
                            radioGroup.getChildAt(i).setEnabled(false);
                        }


                    }
                    else {
                        activity.incorrect+=1;
                        linearLayout2.setVisibility(View.VISIBLE);
                        for (int i = 0; i < radioGroup.getChildCount(); i++) {
                            radioGroup.getChildAt(i).setEnabled(false);
                        }
                    }
                    Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                    animation.setDuration(500); // duration - half a second
                    animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                    animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
                    animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
                    button2.startAnimation(animation);
                    button2.setVisibility(View.VISIBLE);

//                    if(20-position==0){
//                        textView2.setText("Score");
//                        textView1.setText("");
//                    }
//                    else

                }
            });
                button1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        getActivity().finish();
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(position==30){
                            Fragment fragment = new ScoreFragment();
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_frame, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();



                        }
                        else {
                        viewPager.setCurrentItem(position);

                        }
                    }
                });
                return view;
    }

}
