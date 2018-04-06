package com.example.qnmd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by derek on 3/14/2018.
 */

public class QuizActivity extends Activity{

    private TextView text;
    private EditText choice1;
    private EditText choice2;
    private EditText choice3;
    private EditText choice4;
    private EditText question;
    private Button button;
    private DatabaseReference database;
    private EditText answer;
    Intent intent;
    String className;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizlayout);
        intent=getIntent();
        className=intent.getStringExtra("classname");
        text=(TextView) findViewById(R.id.title);
        choice1=(EditText)findViewById(R.id.choice1);
        choice2=(EditText)findViewById(R.id.choice2);
        choice3=(EditText)findViewById(R.id.choice3);
        button=(Button) findViewById(R.id.submit);
        question=(EditText) findViewById(R.id.quizq) ;
        choice4=(EditText)findViewById(R.id.choice4);
        answer=(EditText)findViewById(R.id.ANSWER);
        FirebaseApp.initializeApp(this);
        database= FirebaseDatabase.getInstance().getReference().child(className).child("BroadcastQuestion");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.child(question.getText().toString().trim());
                database.child("Ongoing").setValue(question.getText().toString());
                database=database.child(question.getText().toString().trim());
                database.child("Question").setValue(question.getText().toString());
                database.child("Option1").setValue(choice1.getText().toString());
                database.child("Option2").setValue(choice2.getText().toString());
                database.child("Option3").setValue(choice3.getText().toString());
                database.child("Option4").setValue(choice4.getText().toString());
                database.child("answer").setValue(answer.getText().toString());
                database=FirebaseDatabase.getInstance().getReference().child(className).child("BroadcastQuestion");
                Toast.makeText(QuizActivity.this,"Quiz uploaded! ", Toast.LENGTH_LONG).show();
            }
        });
    }
}
