package com.example.quiz;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InstructorQuiz extends AppCompatActivity implements View.OnClickListener{
    InstructorQuizAdapter myAdapter;
    ArrayList<InstructorQuizAdapter.question> ql=new ArrayList<>();
    String url="http://10.12.176.11/upload_quiz.php";
    RequestQueue requestQueue;
    EditText quiz_name;
    EditText week;
    String instructor_id="1002191";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_quiz);
        ql.add(new InstructorQuizAdapter.question());

        quiz_name=(EditText)findViewById(R.id.quiz_name);
        week=(EditText)findViewById(R.id.week);
        Button save=(Button)findViewById(R.id.save);
        save.setOnClickListener(this);

        myAdapter=new InstructorQuizAdapter(ql,this);
        ListView listView=(ListView)findViewById(R.id.questionList);
        listView.setAdapter(myAdapter);
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue=Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }


    @Override
    public void onClick(View view) {
        ql=myAdapter.getQl();
        //upload the question list to database
        for(final InstructorQuizAdapter.question q:ql){
            StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(InstructorQuiz.this, response, Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(InstructorQuiz.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params=new HashMap<>();
                    params.put("quiz_name",quiz_name.getText().toString());
                    params.put("week",week.getText().toString());
                    params.put("instructor_id",instructor_id);
                    params.put("description",q.description);
                    if(q.type==0){
                        params.put("type","QNA");
                    }else if(q.type==1){
                        params.put("type","MCQ");
                    }
                    params.put("mark",q.point);
                    params.put("a_choice",q.a);
                    params.put("b_choice",q.b);
                    params.put("c_choice",q.c);
                    params.put("d_choice",q.d);
                    params.put("answer",q.answer);
                    Log.i("shunqi",q.answer);
                    return params;
                }
            };

            getRequestQueue().add(stringRequest);
        }
    }
}

class InstructorQuizAdapter extends BaseAdapter {
    private ArrayList<question> ql=new ArrayList<>();
    private Context context;

    public InstructorQuizAdapter(ArrayList<question> input, Context context){
        ql=input;
        this.context=context;
    }

    @Override
    public int getCount() {
        return ql.size();
    }

    @Override
    public Object getItem(int i) {
        return ql.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.vlist, null);
        }

        TextView title=(TextView)view.findViewById(R.id.title);
        title.setText("Question "+(position+1));


        final TextView a=(TextView)view.findViewById(R.id.a);
        final TextView b=(TextView)view.findViewById(R.id.b);
        final TextView c=(TextView)view.findViewById(R.id.c);
        final TextView d=(TextView)view.findViewById(R.id.d);

        final EditText a_description=(EditText)view.findViewById(R.id.a_description);
        a_description.setText(ql.get(position).a);
        a_description.setTag(position);

        final EditText b_description=(EditText)view.findViewById(R.id.b_description);
        b_description.setText(ql.get(position).b);
        b_description.setTag(position);

        final EditText c_description=(EditText)view.findViewById(R.id.c_description);
        c_description.setText(ql.get(position).c);
        c_description.setTag(position);

        final EditText d_description=(EditText)view.findViewById(R.id.d_description);
        d_description.setText(ql.get(position).d);
        d_description.setTag(position);

        final EditText point=(EditText)view.findViewById(R.id.point);
        point.setText(ql.get(position).point);
        point.setTag(position);

        final EditText answer=(EditText)view.findViewById(R.id.answer);
        answer.setText(ql.get(position).answer);
        answer.setTag(position);

        final Spinner spinner=(Spinner)view.findViewById(R.id.type);
        spinner.setSelection(ql.get(position).type);
        spinner.setTag(position);

        final EditText editText=(EditText)view.findViewById(R.id.description);
        editText.setText(ql.get(position).description);
        editText.setTag(position);

        Button add_next=(Button)view.findViewById(R.id.addNext);
        Button delete=(Button)view.findViewById(R.id.delete);

        add_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //il.add(position+1,position+2);
                question newQuestion=new question();
                ql.add(position+1,newQuestion);
                notifyDataSetChanged();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //il.remove(position);
                ql.remove(position);
                notifyDataSetChanged();
            }
        });

        a_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ql.get((Integer)a_description.getTag()).a=a_description.getText().toString();
            }
        });

        b_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ql.get((Integer)b_description.getTag()).b=b_description.getText().toString();
            }
        });

        c_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ql.get((Integer)c_description.getTag()).c=c_description.getText().toString();
            }
        });

        d_description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ql.get((Integer)d_description.getTag()).d=d_description.getText().toString();
            }
        });

        point.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ql.get((Integer)point.getTag()).point=point.getText().toString();
            }
        });

        answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ql.get((Integer)answer.getTag()).answer=answer.getText().toString();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ql.get((Integer)editText.getTag()).description=editText.getText().toString();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ql.get((Integer)spinner.getTag()).type=spinner.getSelectedItemPosition();
                if(spinner.getSelectedItemPosition()==1){
                    a.setVisibility(View.VISIBLE);
                    b.setVisibility(View.VISIBLE);
                    c.setVisibility(View.VISIBLE);
                    d.setVisibility(View.VISIBLE);
                    a_description.setVisibility(View.VISIBLE);
                    b_description.setVisibility(View.VISIBLE);
                    c_description.setVisibility(View.VISIBLE);
                    d_description.setVisibility(View.VISIBLE);
                    answer.setHint("Answer");
                }else if(spinner.getSelectedItemPosition()==0){
                    a.setVisibility(View.GONE);
                    b.setVisibility(View.GONE);
                    c.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                    a_description.setVisibility(View.GONE);
                    b_description.setVisibility(View.GONE);
                    c_description.setVisibility(View.GONE);
                    d_description.setVisibility(View.GONE);
                    answer.setHint("Key Words (Format: kw1,kw2...)");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        return view;
    }
    public void add(){
        ql.add(new question());
    }

    static class question{
        String description="";
        int type=0;
        String point="";
        String a="";
        String b="";
        String c="";
        String d="";
        String answer="";
    }
    public ArrayList<question> getQl(){
        return ql;
    }
}
