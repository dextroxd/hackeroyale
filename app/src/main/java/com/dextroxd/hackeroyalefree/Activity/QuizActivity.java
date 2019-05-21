package com.dextroxd.hackeroyalefree.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.dextroxd.hackeroyalefree.Adapter.DemoFragmentCollectionAdapter;
import com.dextroxd.hackeroyalefree.Models.QuestionModel;
import com.dextroxd.hackeroyalefree.R;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    List<QuestionModel>questionModels;
    DemoFragmentCollectionAdapter adapter;
    public static List<String>Correct;
    public static  int correct =0;
    public static int incorrect =0;
    ViewPager viewpager;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        correct=0;
        incorrect=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawer = findViewById(R.id.drawer);
        mNavigation = findViewById(R.id.nav_view);
        mToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        mDrawer.requestLayout();
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(false);
                mDrawer.closeDrawers();
                switch (id) {
                    case R.id.aboutus:
                        Intent o = new Intent(Intent.ACTION_VIEW);
                        o.setData(Uri.parse("https://www.hackeroyale.com/about-us/"));
                        startActivity(o);
                        item.setChecked(false);
                        break;
                    case R.id.disclaimer:
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("https://www.hackeroyale.com/disclaimer/"));
                        startActivity(i);
                        item.setChecked(false);
                        break;
                    case R.id.privacy:
                        Intent j = new Intent(Intent.ACTION_VIEW);
                        j.setData(Uri.parse("https://www.hackeroyale.com/privacy/"));
                        startActivity(j);
                        item.setChecked(false);
                        break;
                    case R.id.help1:
                        Intent k = new Intent(Intent.ACTION_VIEW);
                        k.setData(Uri.parse("https://www.hackeroyale.com/contact/"));
                        startActivity(k);
                        item.setChecked(false);
                        break;
                    case R.id.rate1:
                        Uri uri = Uri.parse("market://details?id=" + getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                        }

                        item.setChecked(false);
                        break;
                    case R.id.share1:
                        ShareCompat.IntentBuilder.from(QuizActivity.this)
                                .setType("text/plain")
                                .setChooserTitle("Send via")
                                .setText("http://play.google.com/store/apps/details?id=" + getPackageName())
                                .startChooser();
                        item.setChecked(false);
                        break;



                }
                return true;
            }
        });
        questionModels=new ArrayList<>();
        viewpager = findViewById(R.id.pager);
        adapter = new DemoFragmentCollectionAdapter(getSupportFragmentManager(),questionModels);
        viewpager.setAdapter(adapter);
        viewpager.beginFakeDrag();
        getData();
        adapter.notifyDataSetChanged();
        }
    private void getData(){
        List<String>Questions = new ArrayList<>();
        List<String>Option1 = new ArrayList<>();
        List<String>Option2 = new ArrayList<>();
        List<String>Option3 = new ArrayList<>();
        List<String>Option4 = new ArrayList<>();
        Correct = new ArrayList<>();
        Questions.add("My friend has an Android phone. I just know his contact number. Is it possible for me to hack into his phone directly?");
        Questions.add("Is it possible to hack into Facebook or Google directly, as of now?");
        Questions.add("Which of the following is not a spying application?");
        Questions.add("Which if the following is not a password cracking tool?");
        Questions.add("Which is the most preferred best operating system for hackers & pentesters?");
        Questions.add("Which is the best possible way of hacking into someones’s Facebook or Twitter account?");
        Questions.add("What is the app-based operating system designed by Kali Linux team specifically for Android?");
        Questions.add("What is the way to become anonymous while using internet? ");
        Questions.add("For safety, what is the best password policy to be followed by people?");
        Questions.add("One can view & browse onion sites (xyz.onion) on simple internet connection using Chrome.");
        Questions.add("Is it totally safe to download & use torrents through TOR?");
        Questions.add("I use a top-rated premium VPN service. I use it to secretly buy products using card as a theft. I don’t need to worry as no cops can trace me!");
        Questions.add("Is Bitcoin internationally legal & acceptable?");
        Questions.add("What is the name of official authorized examination for being certified as an ethical hacker?");
        Questions.add("Which of the following is not a movie or TV series based on Hacking & Internet?");
        Questions.add("I have to do online transactions on a shopping portal. What should I check first before doing a checkout?");
        Questions.add("Which one of the following is a tool used to hack into SQL databases?");
        Questions.add("Name the renowned Kali Linux tool use for Android hacking?");
        Questions.add("What is the worst form of malware?");
        Questions.add("I want to hack into a target by intercepting the data packets & information transmitted between him & the receiver. What is this attack called?");
        Option1.add("Yes");
        Option2.add("No");
        Option3.add("nil");
        Option4.add("nil");
        Correct.add("No");
        Option1.add("No, its not.Almost not possible.");
        Option2.add("Yes,it is");
        Option3.add("nil");
        Option4.add("nil");
        Correct.add("No, its not.Almost not possible.");
        Option1.add("mSpy");
        Option2.add("Wireshark");
        Option3.add("truth spy");
        Option4.add("AndroRAT");
        Correct.add("Wireshark");
        Option1.add("THC Hydra ");
        Option2.add("John The Ripper");
        Option3.add("Cain & Abel");
        Option4.add("BurpSuite");
        Correct.add("BurpSuite");
        Option1.add("Kali Linux");
        Option2.add("Windows 10");
        Option3.add("Mac OS");
        Option4.add("Ios");
        Correct.add("Kali Linux");
        Option1.add("Hack into Facebook directly ");
        Option2.add("Man In the Middle Attack");
        Option3.add("Social Engineering");
        Option4.add("None of the above");
        Correct.add("Social Engineering");
        Option1.add("Parrot OS");
        Option2.add("Tails OS");
        Option3.add("Kali Nehunter");
        Option4.add("None of the above");
        Correct.add("Kali Nehunter");
        Option1.add("Use TOR");
        Option2.add("Use premium VPNs");
        Option3.add("Use proxy servers");
        Option4.add("All of these");
        Correct.add("All of these");
        Option1.add("Use your name as password");
        Option2.add("Use “abc123” or “admin123”");
        Option3.add("Use a combination of capital & small alphabets, numbers and special symbols with at least 8 characters in total");
        Option4.add("None of the above");
        Correct.add("Use a combination of capital & small alphabets, numbers and special symbols with at least 8 characters in total");
        Option1.add("Yes");
        Option2.add("No");
        Option3.add("nil");
        Option4.add("nil");
        Correct.add("No");
        Option1.add("Yes");
        Option2.add("No");
        Option3.add("nil");
        Option4.add("nil");
        Correct.add("No");
        Option1.add("Yes (As its completely illegal, and the VPN companies are always answerable to government agencies & cops)");
        Option2.add("No");
        Option3.add("nil");
        Option4.add("nil");
        Correct.add("Yes (As its completely illegal, and the VPN companies are always answerable to government agencies & cops)");
        Option1.add("Yes");
        Option2.add("No");
        Option3.add("nil");
        Option4.add("nil");
        Correct.add("No");
        Option1.add("CCNA");
        Option2.add("CEH");
        Option3.add("GRE");
        Option4.add("SCJP");
        Correct.add("CEH");
        Option1.add("Mr. Robot ");
        Option2.add("Suicide Squad");
        Option3.add("The Matrix");
        Option4.add("The Social Network");
        Correct.add("Suicide Squad");
        Option1.add("SSL encrypted site or not");
        Option2.add("Website has to look fancy");
        Option3.add("Passwords are not saved");
        Option4.add("Both A & C");
        Correct.add("Both A & C");
        Option1.add("Vega Scanner ");
        Option2.add("Armitage");
        Option3.add("SQLmap");
        Option4.add("None of the above");
        Correct.add("SQLmap");
        Option1.add("AndroRAT");
        Option2.add("Metasploit Framework");
        Option3.add("Droidsqli");
        Option4.add("None of the above");
        Correct.add("Metasploit Framework");
        Option1.add("Virus");
        Option2.add("Worm");
        Option3.add("Trojan");
        Option4.add("Bug");
        Correct.add("Trojan");
        Option1.add("Man in the Middle");
        Option2.add("Eavesdropping");
        Option3.add("Cracking");
        Option4.add("None of the above");
        Correct.add("Man in the Middle");
        //Following are the types of hackers:
        Questions.add("Following are the types of hackers:");
        Option1.add("White hat hackers");
        Option2.add("Black hat hackers");
        Option3.add("Grey hat hackers");
        Option4.add("All of these");
        Correct.add("All of these");
        Questions.add("Which is the most common vulnerability found in web applications?");
        Option1.add("CSRF Vulnerability");
        Option2.add("Cross-site scripting [XSS]");
        Option3.add("SQLi");
        Option4.add("None of the above");
        Correct.add("Cross-site scripting [XSS]");
        Questions.add("An attacker tries to bomb a server with too many infinite number of requests. The result is server gets down. What is this type of attack called?");
        Option1.add("SQL Injection");
        Option2.add("XSS Attack");
        Option3.add("DoS");
        Option4.add("None of the above");
        Correct.add("DoS");
        Questions.add("Which is the popular tool used in Kali Linux for scanning & penetrating into operating systems?");
        Option1.add("Nessus");
        Option2.add("Maltego");
        Option3.add("Armitage");
        Option4.add("Zenmap");
        Correct.add("Nessus");
        Questions.add("What is the initial first phase of penetration testing called?");
        Option1.add("Scanning & Enumeration");
        Option2.add("Information gathering");
        Option3.add("Remote side attack");
        Option4.add("None of the above");
        Correct.add("Information gathering");
        Questions.add("What is a keylogger?");
        Option1.add("Keylogger is a tool that captures the keystrokes of a target and sends it to the attacker.");
        Option2.add("It is a tool to make keyboard work faster.");
        Option3.add("It is a system program and is totally safe.");
        Option4.add("None of the above");
        Correct.add("Keylogger is a tool that captures the keystrokes of a target and sends it to the attacker.");
        Questions.add("What is a binder & a crypter?");
        Option1.add("Binder is a tool that binds a virus or an infectious script with a file.");
        Option2.add("Crypter encrypts the file making it undetectable to security programs.");
        Option3.add("Both A & B");
        Option4.add("None of the above");
        Correct.add("Both A & B");
        Questions.add("Name the most popular web application scanner used by testers.");
        Option1.add("Vega Scanner");
        Option2.add("Burpsuite");
        Option3.add("Acunteix WVS");
        Option4.add("None of the above");
        Correct.add("Acunteix WVS");
        Questions.add("“Bug bounty hunters” are often:");
        Option1.add("White hat hackers");
        Option2.add("Black hat hackers");
        Option3.add("Government cyber cell officers");
        Option4.add("Software developers");
        Correct.add("White hat hackers");
        Questions.add("Hacking must always be used for:");
        Option1.add("People’s good; identifying & fixing vulnerable threats to systems, thus securing the cyber space.");
        Option2.add("Harming people by attacking & stealing there information.");
        Option3.add("Earning money by hacking for someone else.");
        Option4.add("None of the above");
        Correct.add("People’s good; identifying & fixing vulnerable threats to systems, thus securing the cyber space.");
        for(int i=0;i<Questions.size();i++){
            questionModels.add(new QuestionModel(Questions.get(i),Option1.get(i),Option2.get(i),Option3.get(i),Option4.get(i),Correct.get(i)));

        }
        adapter.notifyDataSetChanged();

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
