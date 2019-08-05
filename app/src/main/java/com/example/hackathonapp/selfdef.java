package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class selfdef extends AppCompatActivity {
    PDFView sf_selfdef;
    Button selfdef_selfdef_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfdef);
        selfdef_selfdef_back = findViewById(R.id.selfdef_back);
        sf_selfdef = (PDFView) findViewById(R.id.selfdef);
        sf_selfdef.fromAsset("pdf_self.pdf").load();

        selfdef_selfdef_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(selfdef.this, Fun_page.class));
                finish();
            }
        });


    }


}
