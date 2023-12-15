package com.example.uasproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    TextView toolbar_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_tv = findViewById(R.id.toolbar_title);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.buttomHome:
                        toolbar_tv.setText("Home");
                        openFragment(new HomeFragment());
                        return true;
                    case R.id.catatan:
                        toolbar_tv.setText("Catatan");
                        openFragment(new CatatanFragment());
                        return true;
                    case R.id.rekap:
                        toolbar_tv.setText("Rekap");
                        openFragment(new RekapFragment());
                        return true;
                    case R.id.laporan:
                        toolbar_tv.setText("Laporan");
                        openFragment(new LaporanFragment());
                        return true;
                }
                return false;
            }
        });



        openFragment(new HomeFragment());


    }




    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}