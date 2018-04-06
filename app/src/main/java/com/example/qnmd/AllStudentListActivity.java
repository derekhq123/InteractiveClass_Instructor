package com.example.qnmd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by derek on 4/3/2018.
 */
class StudentGrades{
    public ArrayList<String> gradesList=new ArrayList<>();
    public String overallGrade="";
    public String score="";
    public String studentName;
}
public class AllStudentListActivity extends AppCompatActivity {
    Intent intent;
    String className;
    DatabaseReference databaseReference;
    ArrayList<StudentGrades> allstudentList=new ArrayList<>();

    AllstudentsAdapter adapter;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faillistlayout);
        intent=getIntent();
        className=intent.getStringExtra("classname");


        databaseReference=FirebaseDatabase.getInstance().getReference().child(className).child("Grades").child("PerStudent");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    StudentGrades student=new StudentGrades();
                    student.studentName=dataSnapshot.getKey().toString();
                    for(DataSnapshot shot:dataSnapshot.getChildren()){
                        if(shot.getKey().toString().equals("Overall Grade")){
                            student.overallGrade=shot.getValue().toString();
                        }else if(shot.getKey().toString().equals("score")){
                            student.score=shot.getValue().toString();
                        }else{
                            student.gradesList.add(shot.getValue().toString());
                        }
                    }
                    allstudentList.add(student);
                }
                adapter=new AllstudentsAdapter(allstudentList,AllStudentListActivity.this);
                ListView listView=(ListView)findViewById(R.id.failList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }

        });


    }
}


class AllstudentsAdapter extends BaseAdapter{
    private ArrayList<StudentGrades> list=new ArrayList<>();
    private Context context;

    public AllstudentsAdapter(ArrayList<StudentGrades> input, Context context){
        list=input;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        View view=convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.allstudentlistview, null);
        }
        TextView textView=(TextView)view.findViewById(R.id.studentName);
        textView.setText(list.get(position).studentName);
        String quizScore="";
        ArrayList<String> scorelist=list.get(position).gradesList;
        for(int i=0;i<scorelist.size();i++){
            quizScore+="Quiz "+(i+1)+": "+scorelist.get(i)+"\n";
        }
        TextView textView1=(TextView)view.findViewById(R.id.Allquiz);
        textView1.setText(quizScore);

        TextView textView2=(TextView)view.findViewById(R.id.overall);
        textView2.setText("Overall grade: "+list.get(position).overallGrade);
        TextView textView3=(TextView)view.findViewById(R.id.score);
        textView3.setText("Score: "+list.get(position).score);
        return view;
    }
}
