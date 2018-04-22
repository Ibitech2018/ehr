package com.divide.ibitech.divide_ibitech;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Slide_one extends AppCompatActivity {

    Button nextSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_one);

        nextSlide = findViewById(R.id.btnNext);
        nextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextSlide();
            }
        });
    }

    private void nextSlide() {
        Intent intent = new Intent(this, Slide_two.class);
        startActivity(intent);
    }


}
