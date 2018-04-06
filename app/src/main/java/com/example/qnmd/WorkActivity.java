package com.example.qnmd;

/**
 * Created by Administrator on 5/2/2018.
 */


        import android.content.Intent;
        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.firebase.client.Firebase;
        import com.google.firebase.FirebaseApp;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class WorkActivity extends Activity {

    Button SubmitButton ;

    Button DisplayButton;

    EditText  ReplyEditText;

    // Declaring String variable ( In which we are storing firebase server URL ).
    //public static final String Firebase_Server_URL = "https://insertdata-android-examples.firebaseio.com/";

    // Declaring String variables to store name & phone number get from EditText.




    DatabaseReference databaseReference;

    // Root Database Name for Firebase Database.
    //public static final String Database_Path = "Student_Details_Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputlayout);

        //Firebase.setAndroidContext(WorkActivity.this);

        //firebase = new Firebase(Firebase_Server_URL);
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Questions");

        SubmitButton = (Button)findViewById(R.id.submit);

        ReplyEditText = (EditText)findViewById(R.id.reply);

        DisplayButton = (Button)findViewById(R.id.DisplayButton);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Adding name into class function object.


                // Getting the ID from firebase database.
                //String StudentRecordIDFromServer = databaseReference.push().getKey();

                // Adding the both name and number values using student details class object using ID.
                //databaseReference.child(StudentRecordIDFromServer).setValue(studentDetails);
                databaseReference.child(ReplyEditText.getText().toString().trim()).setValue("0");
                // Showing Toast message after successfully data submit.
                Toast.makeText(WorkActivity.this,"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

            }
        });

        DisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WorkActivity.this, ShowStudentDetailsActivity.class);
                startActivity(intent);

            }
        });

    }



}
