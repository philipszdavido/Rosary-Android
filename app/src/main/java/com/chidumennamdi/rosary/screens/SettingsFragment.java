package com.chidumennamdi.rosary.screens;

import static androidx.core.app.ActivityCompat.recreate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.chidumennamdi.rosary.R;
import com.chidumennamdi.rosary.utils.Prefs;

import java.util.ArrayList;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    Set<Voice> voices;
    TextToSpeech tts;
    ArrayList<String> voiceNames;
    Prefs prefs;

    public SettingsFragment() {

        // Required empty public constructor

    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        tts = new TextToSpeech(context, status -> {
            voices = tts.getVoices();

            voiceNames = new ArrayList<>();
            for (Voice voice : voices) {
                voiceNames.add(voice.getName());
            }

        });

        prefs = new Prefs(context);

    }

    public static SettingsFragment newInstance(String param1, String param2) {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = requireContext();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroupTheme);
        int savedMode = prefs.prefs.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        // Set checked radio based on saved mode
        switch (savedMode) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                radioGroup.check(R.id.radioLight);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                radioGroup.check(R.id.radioDark);
                break;
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                radioGroup.check(R.id.radioSystem);
                break;
        }

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            int mode;
            if (checkedId == R.id.radioLight) {
                mode = AppCompatDelegate.MODE_NIGHT_NO;
            } else if (checkedId == R.id.radioDark) {
                mode = AppCompatDelegate.MODE_NIGHT_YES;
            } else {
                mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
            }

            prefs.prefs.edit().putInt("theme_mode", mode).apply();
            AppCompatDelegate.setDefaultNightMode(mode);
            recreate(requireActivity());
        });

        LinearLayout ll = view.findViewById(R.id.select_voices);
        ll.setOnClickListener(item -> {

            Intent intent = new Intent(requireContext(), VoicesListActivity.class);
            intent.putStringArrayListExtra("voice_names", voiceNames);
            startActivity(intent);

        });

        SeekBar speechRateSeekBar = view.findViewById(R.id.voice_rate_seekbar);
        speechRateSeekBar.setProgress(prefs.prefs.getInt("voice_rate", 100));

        speechRateSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float newRate = progress / 100f;
                prefs.prefs.edit().putInt("voice_rate", (int) newRate).apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

}