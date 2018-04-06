package com.example.qnmd;

/**
 * Created by Administrator on 7/2/2018.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseActivity extends Activity {

    private DatabaseReference mdatabase;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mdatabase= FirebaseDatabase.getInstance().getReference();


    }

    /**
     * 初始化各种控件
     */



}
