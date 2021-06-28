package com.example.marketproject.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.marketproject.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        // two seconds, then enter main layout
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // run() is in main thread
                // start main page
                startActivity(new Intent(Welcome.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}