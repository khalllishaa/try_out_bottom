package com.example.try_out_bottom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // SharedPreferences file name
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    SharedPreferences sharedPreferences;
    Button login, signup;
    EditText editTextName, editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get SharedPreferences object
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Check if data is available in SharedPreferences
        String savedName = sharedPreferences.getString(KEY_NAME, null);
        if (savedName != null) {
            // If data is available, directly open HomeAct
            Intent intent = new Intent(MainActivity.this, welcome.class);
            startActivity(intent);
            finish(); // Finish MainActivity
        }

        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.pw);
        login = findViewById(R.id.masuk);
        signup = findViewById(R.id.signup);

        // Register Button onClick Listener
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Put data into SharedPreferences when the button is clicked
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, editTextName.getText().toString());
                editor.putString(KEY_EMAIL, editTextEmail.getText().toString());
                editor.apply();

                // Move to HomeAct
                Intent intent = new Intent(MainActivity.this, welcome.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Sign Up Success!", Toast.LENGTH_SHORT).show();
                finish(); // Finish MainActivity
            }
        });

        // Login Button onClick Listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();

                // Check email and password condition
                if (name.equals("khalisha") && email.equals("2412")) {
                    // Save login details in SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME, name);
                    editor.putString(KEY_EMAIL, email);
                    editor.apply();

                    // If correct, move to welcome activity
                    Intent intent = new Intent(MainActivity.this, welcome.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    finish(); // Finish MainActivity
                } else {
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
