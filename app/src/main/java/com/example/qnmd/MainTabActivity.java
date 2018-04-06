package com.example.qnmd;

/**
 * Created by derek on 3/14/2018.
 */

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TabHost;


public class MainTabActivity extends TabActivity implements OnCheckedChangeListener{

    private TabHost mTabHost;
    private Intent mAIntent;
    private Intent mBIntent;
    private Intent mCIntent;
    private Intent mDIntent;
    private Intent mEIntent;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.framelayout);

        this.mAIntent = new Intent(this,ShowStudentDetailsActivity.class);
//        this.mBIntent = new Intent(this,BActivity.class);
        this.mCIntent = new Intent(this,QuizActivity.class);
//        this.mDIntent = new Intent(this,DActivity.class);
//        this.mEIntent = new Intent(this,EActivity.class);

        ((RadioButton) findViewById(R.id.radio_button0))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button1))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button2))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button3))
                .setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radio_button4))
                .setOnCheckedChangeListener(this);

        setupIntent();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            switch (buttonView.getId()) {
                case R.id.radio_button0:
                    this.mTabHost.setCurrentTabByTag("A_TAB");
                    break;
                case R.id.radio_button1:
                    this.mTabHost.setCurrentTabByTag("B_TAB");
                    break;
                case R.id.radio_button2:
                    this.mTabHost.setCurrentTabByTag("C_TAB");
                    break;
                case R.id.radio_button3:
                    this.mTabHost.setCurrentTabByTag("D_TAB");
                    break;
                case R.id.radio_button4:
                    this.mTabHost.setCurrentTabByTag("MORE_TAB");
                    break;
            }
        }

    }

    private void setupIntent() {
        this.mTabHost = getTabHost();
        TabHost localTabHost = this.mTabHost;

        localTabHost.addTab(buildTabSpec("A_TAB", R.string.main_home,
                R.drawable.icon_home_sel, this.mAIntent));

        localTabHost.addTab(buildTabSpec("B_TAB", R.string.main_news,
                R.drawable.icon_meassage_sel, this.mBIntent));

        localTabHost.addTab(buildTabSpec("C_TAB",
                R.string.main_quiz, R.drawable.icon_square_sel,
                this.mCIntent));

        localTabHost.addTab(buildTabSpec("D_TAB", R.string.main_ques,
                R.drawable.icon_selfinfo_sel, this.mDIntent));

        localTabHost.addTab(buildTabSpec("MORE_TAB", R.string.main_set,
                R.drawable.icon_more_sel, this.mEIntent));

    }

    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
                                         final Intent content) {
        return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel),
                getResources().getDrawable(resIcon)).setContent(content);
    }
}
