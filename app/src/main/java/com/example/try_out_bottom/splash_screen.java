package com.example.try_out_bottom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash_screen extends AppCompatActivity {

    private static final String PREFS_NAME = "myPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                // Get the SharedPreferences object
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

                // Retrieve the saved data
                String username = settings.getString("username", "No Username Found");

                Intent intent;
                if (username.equalsIgnoreCase("No Username Found")){
                    // intent ke login
                    intent = new Intent(splash_screen.this, MainActivity.class);

                }else{
                    // intent ke home
                    intent = new Intent(splash_screen.this, akun.class);
                }
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}