package com.example.try_out_bottom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class akunn extends Fragment {
    SharedPreferences sharedPreferences;
    TextView nameTextView, emailTextView;
    Button logoutButton;
    ImageView profileImageView;

    // SharedPreferences file name
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akunn, container, false);

        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Pastikan ID yang digunakan benar dan tipe view sesuai
        nameTextView = view.findViewById(R.id.namea);
        emailTextView = view.findViewById(R.id.paw);
        logoutButton = view.findViewById(R.id.logout);
        profileImageView = view.findViewById(R.id.imagei);

        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if (name != null && email != null) {
            // Set data on TextViews
            nameTextView.setText(name);
            emailTextView.setText(email);
        }

        String imageUrl = "https://i.pinimg.com/564x/fe/fd/26/fefd2613841f0f0392666d0e6e068a46.jpg";
        Glide.with(this).load(imageUrl).into(profileImageView);

        // Button for logout session
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Clear all data from SharedPreferences
                editor.apply(); // Save the changes
                Toast.makeText(getActivity(), "Logout successfully", Toast.LENGTH_SHORT).show();

                // Move back to MainActivity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish(); // Close the current activity after logout
            }
        });

        // Handling back button to go back to welcome activity
        View backs = view.findViewById(R.id.bacck);
        backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move back to welcome activity
                Intent intent = new Intent(getActivity(), welcome.class);
                startActivity(intent);
                getActivity().finish(); // Close the current activity and return to welcome activity
            }
        });

        return view;
    }
}
