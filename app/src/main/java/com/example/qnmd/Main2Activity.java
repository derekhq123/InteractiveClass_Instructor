package com.example.qnmd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {
    String className;
    Intent intent;
    Button button;
    Button button2;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                     intent = new Intent(Main2Activity.this, ShowStudentDetailsActivity.class);
                     intent.putExtra("classname",className);
                    startActivity(intent);

                    return true;
                case R.id.navigation_dashboard:
                     intent=new Intent(Main2Activity.this,ExamActivity.class);
                    intent.putExtra("classname",className);
                     startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    intent=new Intent(Main2Activity.this,QuizActivity.class);
                    intent.putExtra("classname",className);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent=getIntent();
        className=intent.getStringExtra("class name");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        button=(Button)findViewById(R.id.fail);
        button2=(Button)findViewById(R.id.allStudentList);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                intent=new Intent(Main2Activity.this,FailListActivity.class);
                intent.putExtra("classname",className);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                intent=new Intent(Main2Activity.this,AllStudentListActivity.class);
                intent.putExtra("classname",className);
                startActivity(intent);
            }
        });

    }

}
