package com.chidumennamdi.rosary;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.chidumennamdi.rosary.screens.AddPrayerFragment;
import com.chidumennamdi.rosary.screens.PrayersFragment;
import com.chidumennamdi.rosary.screens.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PrayerMainActivity extends AppCompatActivity {

    Fragment prayersFragment = new PrayersFragment();
    Fragment addPrayerFragment = new AddPrayerFragment();
    Fragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prayer_main);

        BottomNavigationView bottomNavigationItemView = findViewById(R.id.bottom_nav);

        bottomNavigationItemView.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.menu_prayers) {
                setCurrentFragment(prayersFragment);
                return true;
            } else if (id == R.id.menu_add_prayer) {
                setCurrentFragment(addPrayerFragment);
                return true;
            } else if (id == R.id.menu_settings) {
                setCurrentFragment(settingsFragment);
                return true;
            } else {
                return false;
            }

        });

        setCurrentFragment(prayersFragment);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void setCurrentFragment(Fragment frag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag, frag)
                .commit();
    }
}