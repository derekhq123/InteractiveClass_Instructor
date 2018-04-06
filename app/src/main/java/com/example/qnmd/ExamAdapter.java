package com.example.qnmd;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by derek on 4/2/2018.
 */

public class ExamAdapter extends BaseAdapter {
    private ArrayList<ExamQuestion> questionList=new ArrayList<>();
    private Context context;

    public ExamAdapter(ArrayList<ExamQuestion> input, Context context){
        questionList=input;
        this.context=context;
    }
    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int i) {
        return questionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public ArrayList<ExamQuestion> getQuestionList(){
        return questionList;
    }
    public View getView(final int position, View convertView, ViewGroup parent){
        View view=convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.questionlist, null);
        }
        final TextView questionTitle=(TextView)view.findViewById(R.id.questionTitle);
        questionTitle.setText("Question: "+(position+1));
        final TextView A=(TextView)view.findViewById(R.id.A);
        final EditText Acontent=(EditText)view.findViewById(R.id.Acontent);
        Acontent.setText(questionList.get(position).A);
        Acontent.setTag(position);
        final TextView B=(TextView)view.findViewById(R.id.B);
        final EditText Bcontent=(EditText)view.findViewById(R.id.Bcontent);
        Bcontent.setText(questionList.get(position).B);
        Bcontent.setTag(position);
        final TextView C=(TextView)view.findViewById(R.id.C);
        final EditText Ccontent=(EditText)view.findViewById(R.id.Ccontent);
        Ccontent.setText(questionList.get(position).C);
        Ccontent.setTag(position);
        final TextView D=(TextView)view.findViewById(R.id.D);
        final EditText Dcontent=(EditText)view.findViewById(R.id.Dcontent);
        Dcontent.setText(questionList.get(position).D);
        Dcontent.setTag(position);

        final EditText point=(EditText)view.findViewById(R.id.point);
        point.setText(questionList.get(position).point);
        point.setTag(position);

        final Spinner questionType=(Spinner) view.findViewById(R.id.questiontype);
        questionType.setSelection(questionList.get(position).type);
        questionType.setTag(position);

        final EditText questionDes=(EditText)view.findViewById(R.id.questionContent);
        questionDes.setText(questionList.get(position).Question);
        questionDes.setTag(position);

        final EditText answer=(EditText) view.findViewById(R.id.answer);
        answer.setText(questionList.get(position).answer);
        answer.setTag(position);

        final Button add=(Button)view.findViewById(R.id.add);
        final Button delete=(Button) view.findViewById(R.id.delete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionList.add(position+1,new ExamQuestion());
                notifyDataSetChanged();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               questionList.remove(position);
               notifyDataSetChanged();
            }
        });

        Acontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                questionList.get((Integer)Acontent.getTag()).A=Acontent.getText().toString();
            }
        });
        Bcontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                questionList.get((Integer)Bcontent.getTag()).B=Bcontent.getText().toString();
            }
        });
        Ccontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                questionList.get((Integer)Ccontent.getTag()).C=Ccontent.getText().toString();
            }
        });

        Dcontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                questionList.get((Integer)Dcontent.getTag()).D=Dcontent.getText().toString();
            }
        });

        point.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                questionList.get((Integer)point.getTag()).point=point.getText().toString();
            }
        });

        questionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                questionList.get((Integer)questionType.getTag()).type=questionType.getSelectedItemPosition();
                if(questionType.getSelectedItemPosition()==1){
                    A.setVisibility(View.VISIBLE);
                    B.setVisibility(View.VISIBLE);
                    C.setVisibility(View.VISIBLE);
                    D.setVisibility(View.VISIBLE);
                    Acontent.setVisibility(View.VISIBLE);
                    Bcontent.setVisibility(View.VISIBLE);
                    Ccontent.setVisibility(View.VISIBLE);
                    Dcontent.setVisibility(View.VISIBLE);
                    answer.setHint("Write your choice");
                }else if(questionType.getSelectedItemPosition()==0){
                    A.setVisibility(View.GONE);
                    B.setVisibility(View.GONE);
                    C.setVisibility(View.GONE);
                    D.setVisibility(View.GONE);
                    Acontent.setVisibility(View.GONE);
                    Bcontent.setVisibility(View.GONE);
                    Ccontent.setVisibility(View.GONE);
                    Dcontent.setVisibility(View.GONE);
                    answer.setHint("You should write your answer in this format(...)");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        questionDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                questionList.get((Integer)questionDes.getTag()).Question=questionDes.getText().toString();
            }
        });

        answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                questionList.get((Integer)answer.getTag()).answer=answer.getText().toString();
            }
        });

        return view;
    }

}
