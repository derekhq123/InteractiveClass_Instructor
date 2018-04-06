package com.example.qnmd;

/**
 * Created by derek on 4/3/2018.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FailListActivity extends AppCompatActivity {

    ArrayList<String> failList=new ArrayList<>();
    DatabaseReference databaseReference;
    String className;
    Intent intent;
    FailListAdapter adapter;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faillistlayout);
        intent=getIntent();
        className=intent.getStringExtra("classname");
        databaseReference = FirebaseDatabase.getInstance().getReference().child(className).child("Grades").child("FailList");
        System.out.println(className);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    System.out.println("1");
                    System.out.println(dataSnapshot.getValue().toString());
                    failList.add(dataSnapshot.getValue().toString());
                    System.out.println(failList);
                }
                adapter=new FailListAdapter(failList,FailListActivity.this);
                ListView listView=(ListView)findViewById(R.id.failList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }

        });


    }


}

class FailListAdapter extends BaseAdapter {
    private ArrayList<String> list=new ArrayList<>();
    private Context context;

    public FailListAdapter(ArrayList<String> input, Context context){
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
            view = inflater.inflate(R.layout.studentfail, null);
        }
        TextView textView=(TextView)view.findViewById(R.id.student);
        textView.setText(list.get(position));
        return view;
    }

}