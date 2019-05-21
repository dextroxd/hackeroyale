package com.dextroxd.hackeroyalefree.Activity;


import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.dextroxd.hackeroyalefree.Fragments.Anonymity;
import com.dextroxd.hackeroyalefree.Fragments.Android;
import com.dextroxd.hackeroyalefree.Fragments.Facebook;
import com.dextroxd.hackeroyalefree.Fragments.Featured;
import com.dextroxd.hackeroyalefree.Fragments.Malware;
import com.dextroxd.hackeroyalefree.Fragments.SocialEngineering;
import com.dextroxd.hackeroyalefree.Fragments.Web;
import com.dextroxd.hackeroyalefree.Fragments.WiFi;
import com.dextroxd.hackeroyalefree.Fragments.Mitm;
import com.dextroxd.hackeroyalefree.Fragments.Vpns;
import com.dextroxd.hackeroyalefree.Fragments.Windows;
import com.dextroxd.hackeroyalefree.Fragments.InformationGathering;
import com.dextroxd.hackeroyalefree.Fragments.PasswordCracking;
import com.dextroxd.hackeroyalefree.Fragments.VulnerabilityScanning;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
       if(position==0)
       {
           return new Featured();
       }
       else if (position==1)
       {
           return new Facebook();
       }
       else if (position==2)
       {
            return new Android();
       }
       else if(position==3)
       {
           return new Anonymity();
       }
       else if(position==4)
        {
            return new Web();
        }
        else if(position==5){
           return new WiFi();
       }
       else if(position==6){
           return new Malware();
       }
       else if(position==7)
       {
           return new Mitm();
       }
       else if(position==8){
           return new SocialEngineering();
       }
       else if(position==9){
           return new Windows();
       }
       else if(position==10){
           return new Vpns();
       }
       else if(position==11)
       {
           return new InformationGathering();
       }
       else if(position==12)
       {
           return new PasswordCracking();
       }
       else
       {
           return new VulnerabilityScanning();
       }
    }

    @Override
    public int getCount() {
        return 14;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0)
        {
            return "Featured";
        }
        else if (position==1)
        {
            return "Facebook";

        }
        else if(position==2)
        {
            return "Android";
        }
        else if(position==3)
        {
            return "Anonymity";
        }
        else if (position==4)
        {
            return "Web";
        }
        else if(position==5)
        {
            return "WiFi";
        }
        else if(position==6)
        {
            return "Malware, Rats & Keyloggers";
        }
        else if(position==7){
            return "Mitm & Sniffing";
        }
        else if(position==8){
            return "Social Engineering";
        }
        else if(position==9){
            return "Windows Hacking";
        }
        else if(position==10)
        {
            return "Vpns, Routers & Networks";
        }
        else if(position==11)
        {
            return "Information Gathering";
        }
        else if(position==12)
        {
            return "Password Cracking";
        }
        else
        {
            return "Vulnerability Scanning";

        }
    }
}
