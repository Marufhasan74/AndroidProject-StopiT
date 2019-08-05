package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportIncident extends AppCompatActivity {
    private EditText suspectName;
    private EditText susaddress;
    private EditText report;
    Button submit;
    Button back;


    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_incindent);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Incident Report");
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        suspectName = findViewById(R.id.reportIncident_suspectName);
        susaddress = findViewById(R.id.reportIncident_susaddress);
        report = findViewById(R.id.reportIncident_report);
        submit = findViewById(R.id.reportIncident_submit);
        back = findViewById(R.id.reportIncident_back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReportIncident.this, Fun_page.class));
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startReportPosting();

            }
        });

    }

    private void startReportPosting() {

        String Name = suspectName.getText().toString().trim();
        String Susaddress = susaddress.getText().toString().trim();
        String Report = report.getText().toString().trim();

        if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Susaddress) && !TextUtils.isEmpty(Report)) {
            DatabaseReference newReport = mDatabase.push();
            newReport.child("Suspect Name").setValue(Name);
            newReport.child("Suspect\'s Address").setValue(Susaddress);
            newReport.child("Report").setValue(Report);
            /*newReport.child("UserID").setValue(mCurrentUser.getUid());*/

            Toast.makeText(getApplicationContext(), "Thank you.Our Legal Team is looking into your case", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

    }


}
