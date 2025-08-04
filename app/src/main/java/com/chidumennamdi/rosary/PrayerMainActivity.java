package com.chidumennamdi.rosary;

import android.os.Bundle;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prayer_main);

        Fragment prayersFragment = new PrayersFragment();
        Fragment addPrayerFragment = new AddPrayerFragment();
        Fragment settingsFragment = new SettingsFragment();

        setCurrentFragment(prayersFragment);
        BottomNavigationView bottomNavigationItemView = findViewById(R.id.bottom_nav);

        bottomNavigationItemView.setOnNavigationItemSelectedListener(item -> {
            System.out.println("Hello");
            System.out.println(item.getItemId());

            switch (item.getItemId()) {
                case 2131231237:
                    setCurrentFragment(prayersFragment);
                    break;
                case 2131231236:
                    setCurrentFragment(addPrayerFragment);
                    break;
                case 2131231238:
                    setCurrentFragment(settingsFragment);
                    break;
            }

            return true;

        });

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