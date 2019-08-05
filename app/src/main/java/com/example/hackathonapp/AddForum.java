package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddForum extends AppCompatActivity {
    Button addforum_addforum_back;
    private EditText addforum_title;
    private EditText addforum_author;
    private EditText addforum_description;
    Button addforum_submit;


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_forum);

        addforum_submit = findViewById(R.id.addforum_submit);
        addforum_title = findViewById(R.id.addforum_title);
        addforum_author = findViewById(R.id.addforum_author);
        addforum_description = findViewById(R.id.addforum_description);
        addforum_addforum_back = findViewById(R.id.addforum_back);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Forum");


        addforum_addforum_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddForum.this, ForumPage.class));
                finish();
            }
        });

        addforum_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();

            }
        });

    }

    private void startPosting() {

        String title = addforum_title.getText().toString().trim();
        String author = addforum_author.getText().toString().trim();
        String description = addforum_description.getText().toString().trim();

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(author) && !TextUtils.isEmpty(description)) {
            DatabaseReference newforum = mDatabase.push();
            newforum.child("title").setValue(title);
            newforum.child("author").setValue(author);
            newforum.child("description").setValue(description);

            Toast.makeText(getApplicationContext(), "Posted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddForum.this, ForumPage.class));
        } else {
            Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddForum.this, ForumPage.class));
        finish();
    }
}
