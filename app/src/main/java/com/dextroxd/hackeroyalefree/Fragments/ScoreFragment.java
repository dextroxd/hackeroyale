package com.dextroxd.hackeroyalefree.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dextroxd.hackeroyalefree.Activity.QuizActivity;
import com.dextroxd.hackeroyalefree.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {
    QuizActivity activity;
    Button button1;
    public ScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score, container, false);
//        correct = getArguments().getInt("Correct");
//        incorrect = getArguments().getInt("Incorrect");
        button1 = view.findViewById(R.id.exit1);
        TextView textView = view.findViewById(R.id.correct);
        TextView textView1 = view.findViewById(R.id.incorrect);
        TextView textView2 = view.findViewById(R.id.finalscore);
        textView.setText(activity.correct+"");
        textView1.setText(activity.incorrect+"");
        textView2.setText(activity.correct+"/30");
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();
            }
        });

        return view;
    }

}
