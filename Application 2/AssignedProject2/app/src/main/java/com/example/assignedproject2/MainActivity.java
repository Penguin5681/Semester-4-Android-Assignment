package com.example.assignedproject2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int CONSTRAINT_LAYOUT_MAIN = R.id.constraint_layout_main_activity;

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setSelectedItemId(R.id.home_icon);
        getSupportFragmentManager().beginTransaction().replace(CONSTRAINT_LAYOUT_MAIN, new HomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_icon) {
                getSupportFragmentManager().beginTransaction().replace(CONSTRAINT_LAYOUT_MAIN, new HomeFragment()).commit();
                return true;
            }
            else if (item.getItemId() == R.id.add_profile_icon) {
                getSupportFragmentManager().beginTransaction().replace(CONSTRAINT_LAYOUT_MAIN, new AddProfileFragment()).commit();
                return true;
            }
            else if (item.getItemId() == R.id.profile_list_icon) {
                getSupportFragmentManager().beginTransaction().replace(CONSTRAINT_LAYOUT_MAIN, new AllProfilesFragment()).commit();
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

}