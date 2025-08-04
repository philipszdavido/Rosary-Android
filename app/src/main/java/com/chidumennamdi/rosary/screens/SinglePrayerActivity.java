package com.chidumennamdi.rosary.screens;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chidumennamdi.rosary.R;
import com.chidumennamdi.rosary.models.Prayer;
import com.chidumennamdi.rosary.utils.Speech;

public class SinglePrayerActivity extends AppCompatActivity {

    private Speech speech;
    float[] speechRate = {1.0f}; // Default normal speed
    private String context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        speech = new Speech();
        speech.init(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_single_prayer);

        Intent intent = getIntent();

        LayoutInflater lf = getLayoutInflater();

        if (intent != null) {
            Prayer receivedObject = intent.getParcelableExtra("prayer");
            if (receivedObject != null) {
                String title = receivedObject.title;
                String content = receivedObject.content;

                System.out.println(content);
                System.out.println(title);

                TextView prayer_header_text = findViewById(R.id.prayer_header_text);
                prayer_header_text.setText(title);

                // set the content
                TextView prayer_body_text = findViewById(R.id.prayer_body_text);
                prayer_body_text.setText(content);

            }
        }

        Button start = findViewById(R.id.start_button);
        start.setOnClickListener(item -> {

            TextView prayer_body_text = findViewById(R.id.prayer_body_text);

            String text = prayer_body_text.getText().toString();
            speech.speak(text, prayer_body_text);

            // speechRate[0] = isChecked ? 0.5f : 1.0f;
            // speech.start(this, Locale.US, speechRate[0], prayerTextView, scrollView, prayerText);

        });

        Button stop = findViewById(R.id.stop_button);
        stop.setOnClickListener(item -> {
            speech.shutdown();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}