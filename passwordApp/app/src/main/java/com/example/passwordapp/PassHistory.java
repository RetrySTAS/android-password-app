package com.example.passwordapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PassHistory extends AppCompatActivity {

    private Button buttonGenHistoryBack;

    private TextView PassListArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_history);

        buttonGenHistoryBack = findViewById(R.id.buttonGenHistoryBack);
        PassListArr = findViewById(R.id.PassListArr);

        Intent intent = getIntent();
        String[] arr = (intent.getStringArrayExtra("pl"));

        for(String p: arr){
            PassListArr.append(p + "\n");
        }


        buttonGenHistoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassHistory.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}