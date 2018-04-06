package com.example.qnmd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by derek on 4/2/2018.
 */

public class ExamActivity extends AppCompatActivity {

    ArrayList<ExamQuestion> examList=new ArrayList<>();
    EditText quizTitle;
    Button submit;
    ExamAdapter adapter;
    DatabaseReference databaseReference;
    Intent intent;
    String className;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examlayout);
        intent=getIntent();
        examList.add(new ExamQuestion());
        quizTitle=(EditText)findViewById(R.id.quizTitle);
        submit=(Button)findViewById(R.id.SUBMIT);
        adapter=new ExamAdapter(examList,this);
        className=intent.getStringExtra("classname");

        databaseReference=FirebaseDatabase.getInstance().getReference().child(className).child("Quiz");

        ListView listView=(ListView)findViewById(R.id.questionList);
        listView.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                databaseReference.child("Ongoing").setValue(quizTitle.getText().toString());
                databaseReference=databaseReference.child("Saved");

                databaseReference=databaseReference.child(quizTitle.getText().toString());
                for(int i=0;i<examList.size();i++){
                    String question=examList.get(i).Question;
                    String answer=examList.get(i).answer;
                    String Acontent=examList.get(i).A;
                    String Bcontent=examList.get(i).B;
                    String Ccontent=examList.get(i).C;
                    String Dcontent=examList.get(i).D;
                    int type=examList.get(i).type;
                    if(type==1){
                        databaseReference.child(""+i).child("Answer").setValue(answer);
                        databaseReference.child(""+i).child("Question").setValue(question);
                        databaseReference.child(""+i).child("Option1").setValue(Acontent);
                        databaseReference.child(""+i).child("Option2").setValue(Bcontent);
                        databaseReference.child(""+i).child("Option3").setValue(Ccontent);
                        databaseReference.child(""+i).child("Option4").setValue(Dcontent);
                        databaseReference.child(""+i).child("Type").setValue("MultipleChoice");
                    }else{
                        databaseReference.child(""+i).child("Answer").setValue(answer);
                        databaseReference.child(""+i).child("Question").setValue(question);
                        databaseReference.child(""+i).child("Type").setValue("ShortAnswer");
                        databaseReference.child(""+i).child("Option1").setValue(Acontent);
                        databaseReference.child(""+i).child("Option2").setValue(Bcontent);
                        databaseReference.child(""+i).child("Option3").setValue(Ccontent);
                        databaseReference.child(""+i).child("Option4").setValue(Dcontent);
                    }
                }
                databaseReference=FirebaseDatabase.getInstance().getReference().child(className).child("Quiz");
                Toast.makeText(getApplicationContext(),
                        "Upload successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }



}
