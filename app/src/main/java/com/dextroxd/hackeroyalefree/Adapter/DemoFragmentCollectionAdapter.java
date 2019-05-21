package com.dextroxd.hackeroyalefree.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.dextroxd.hackeroyalefree.Models.QuestionModel;
import com.dextroxd.hackeroyalefree.Fragments.QuestionFragment;

import java.util.List;

public class DemoFragmentCollectionAdapter extends FragmentStatePagerAdapter {
    private List<QuestionModel>list;
    ViewPager viewPager;
    public DemoFragmentCollectionAdapter(FragmentManager fm, List<QuestionModel>list) {
        super(fm);
        this.list = list;

    }

    @Override
    public Fragment getItem(int position) {
        QuestionFragment demoFragment = new QuestionFragment();
        Bundle bundle = new Bundle();
        QuestionModel model = list.get(position);
        bundle.putString("question",model.getQuestion());
        bundle.putString("option1",model.getOption1());
        bundle.putString("option2",model.getOption2());
        bundle.putString("option3",model.getOption3());
        bundle.putString("option4",model.getOption4());
        bundle.putString("correct",model.getCorrect());
        bundle.putInt("position",position+1);
        demoFragment.setArguments(bundle);
        return demoFragment;
    }

    @Override
    public int getCount() {
        return 30;
    }

}
