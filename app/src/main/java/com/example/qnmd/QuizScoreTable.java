package com.example.qnmd;

/**
 * Created by derek on 4/3/2018.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizScoreTable extends Activity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();

        final View contentView = findViewById(R.id.my_recycler_view);
        String myDataset[] = new String[5];
        myDataset[0] = "1002100";
        myDataset[1] = "1002101";
        myDataset[2] = "1002102";
        myDataset[3] = "1002103";
        myDataset[4] = "1002104";



        setContentView(R.layout.main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        Button myFL = (Button) findViewById(R.id.failbtn);
        myFL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(QuizScoreTable.this,FailListActivity.class));
//                Intent intent = new Intent(mContext, RecyclerActivity.class);
//                startActivity(intent);

            }
        });


    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataset;
        private ViewHolder mViewHolder;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextViewID;
            public TextView mTextViewQuiz1;
            public TextView mTextViewQuiz2;
            public TextView mTextViewQuiz3;
            public ViewHolder(View v) {
                super(v);

                mTextViewID = (TextView) v.findViewById(R.id.studentid);
                mTextViewQuiz1 = (TextView) v.findViewById(R.id.quiz1);
                mTextViewQuiz2 = (TextView) v.findViewById(R.id.quiz2);
                mTextViewQuiz3 = (TextView) v.findViewById(R.id.quiz3);
            }
            public ViewHolder(TextView sid, TextView q1, TextView q2, TextView q3) {
                super(sid);


                mTextViewID = sid;
                mTextViewQuiz1 = q1;
                mTextViewQuiz2 = q2;
                mTextViewQuiz3 = q3;

            }

            public ViewHolder(TextView sid) {
                super(sid);


                mTextViewID = sid;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(String[] myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {

            View todo_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scoreitem, parent, false);

            mViewHolder = new ViewHolder(todo_view);

            return mViewHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            holder.mTextViewID.setText(mDataset[position]);

            holder.mTextViewQuiz1.setText("60");
            holder.mTextViewQuiz2.setText("70");
            holder.mTextViewQuiz3.setText("70");


        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }


    }
}





