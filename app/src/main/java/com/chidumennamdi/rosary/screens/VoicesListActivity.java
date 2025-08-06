package com.chidumennamdi.rosary.screens;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chidumennamdi.rosary.R;
import com.chidumennamdi.rosary.utils.Prefs;

import java.util.ArrayList;
import java.util.Objects;

public class VoicesListActivity extends AppCompatActivity {

    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_voices_list);

        ArrayList<String> voiceNames = getIntent().getStringArrayListExtra("voice_names");
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout container = findViewById(R.id.ll_hori);

        assert voiceNames != null;
        for (String voice : voiceNames) {
            View voiceItem = inflater.inflate(R.layout.item_voice, container, false);

            ((TextView) voiceItem.findViewById(R.id.tv_voice_name)).setText(voice);

            Switch voice_switch = voiceItem.findViewById(R.id.voice_switch);
            voice_switch.setChecked(Objects.equals(prefs.getSavedVoiceName(), voice));
            voice_switch.setOnCheckedChangeListener((item, isChecked) -> {
                applyVoice(voice);
            });

            container.addView(voiceItem);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void applyVoice(String voice) {
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("voice", voice);
        editor.apply();
    }

}