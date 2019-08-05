package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;

public class Legal extends AppCompatActivity {
    Button legal_legalguide_back;


    PDFView pdf1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal);
        legal_legalguide_back = findViewById(R.id.legalguide_back);

        pdf1 = findViewById(R.id.pdf1);
        pdf1.fromAsset("LegalAction.pdf").load();


        legal_legalguide_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Legal.this, Fun_page.class));
                finish();
            }
        });

    }
}
