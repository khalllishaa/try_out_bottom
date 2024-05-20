package com.example.try_out_bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.try_out_bottom.Team.arsenal;
import com.example.try_out_bottom.country.countries;
import com.example.try_out_bottom.spaiin.spains;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class welcome extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Mengambil referensi ke BottomNavigationView dari layout
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // Menetapkan listener untuk BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(navListener);

        // Memuat fragment pertama saat aplikasi pertama kali dibuka
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new arsenal())
                .commit();
    }

    // Listener untuk item yang dipilih di BottomNavigationView
    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Inisialisasi variabel untuk menampung fragment yang dipilih
                    Fragment selectedFragment = null;

                    // Cek item yang dipilih dan inisialisasi fragment yang sesuai
                    if (item.getItemId() == R.id.Arsenal) {
                        selectedFragment = new arsenal();
                    } else if (item.getItemId() == R.id.Spain) {
                        selectedFragment = new spains();
                    } else if (item.getItemId() == R.id.Countries) {
                        selectedFragment = new countries();
                    } else if (item.getItemId() == R.id.akun) {
                        selectedFragment = new akunn();
                    }

                    // Ganti fragment yang ditampilkan di container dengan fragment yang dipilih
                    if (selectedFragment != null) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, selectedFragment)
                                .commit();
                        return true; // Mengembalikan true menandakan bahwa item navigasi telah ditangani
                    }

                    return false; // Mengembalikan false jika tidak ada fragment yang dipilih
                }

            };
    }

