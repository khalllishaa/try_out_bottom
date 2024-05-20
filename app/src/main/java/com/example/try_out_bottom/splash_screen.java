package com.example.try_out_bottom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    private static final String PREFS_NAME = "myPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Dapatkan SharedPreferences object
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

                // Ambil data yang disimpan
                String username = settings.getString("username", "No Username Found");

                Intent intent;
                if (username.equalsIgnoreCase("No Username Found")) {
                    // Intent ke login
                    intent = new Intent(splash_screen.this, MainActivity.class);
                } else {
                    // Intent ke home
                    intent = new Intent(splash_screen.this, welcome.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2000); // Delay 2 detik
    }
}
