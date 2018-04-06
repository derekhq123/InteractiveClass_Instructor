package com.example.qnmd;

/**
 * Created by derek on 4/2/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReplyActivity extends AppCompatActivity {

    //private static final String TAG = "MainActivity";
    Intent intent;
    String question;
    TextView questionText;
    DatabaseReference databaseReference;
    Button SubmitButton;
    EditText ReplyEditText;
    Button DisplayButton;
    String answer;
    Button back;
    Context context;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputlayout);
        intent=getIntent();
        question=intent.getStringExtra("Question");
        questionText=(TextView)findViewById(R.id.Question);
        questionText.setText(question);
        String className=ShowStudentDetailsActivity.className;
        databaseReference = FirebaseDatabase.getInstance().getReference().child(className).child("Forum");
        SubmitButton = (Button)findViewById(R.id.submit);
        back=(Button) findViewById(R.id.Back);
        ReplyEditText = (EditText)findViewById(R.id.reply);
        context=this;
        DisplayButton = (Button)findViewById(R.id.DisplayButton);
        answer=ReplyEditText.getText().toString();
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer=ReplyEditText.getText().toString();


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String nowQuestion=dataSnapshot.child("question").getValue().toString();

                            if(nowQuestion.equals(question)){
                                databaseReference.child(dataSnapshot.getKey().toString()).child("answer").setValue(answer);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Toast.makeText(ReplyActivity.this,"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent=new Intent(context,ShowStudentDetailsActivity.class);
                intent.putExtra("classname",ShowStudentDetailsActivity.className);
                startActivity(intent);
            }
        });

    }



}