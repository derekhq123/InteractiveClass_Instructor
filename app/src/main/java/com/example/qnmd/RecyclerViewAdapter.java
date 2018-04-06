package com.example.qnmd;

/**
 * Created by Administrator on 22/2/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context mContext;
    List<StudentDetails> StudentInfo;

    public RecyclerViewAdapter(Context context, List<StudentDetails> TempList) {

        StudentInfo = TempList;

        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_class, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.StudentNameTextView.setText(StudentInfo.get(position).getQuestion());
        holder.StudentNumberTextView.setText(StudentInfo.get(position).getStudentName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                //Toast.makeText(mContext,StudentInfo.get(position).getQuestion(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, ReplyActivity.class);
                //intent.putExtra("image_url", mImages.get(position));
                intent.putExtra("Question", StudentInfo.get(position).getQuestion());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return StudentInfo.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView StudentNameTextView;
        public TextView StudentNumberTextView;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {

            super(itemView);

            StudentNameTextView = (TextView) itemView.findViewById(R.id.ShowQuestionTextView);

            StudentNumberTextView = (TextView) itemView.findViewById(R.id.ShowNamesTextView);

            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
