package com.chidumennamdi.rosary.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Locale;

public class Speech {

    private TextToSpeech tts;
    private boolean isReady = false;

    public void init(Context context) {
        tts = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(Locale.US);
                if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                    isReady = true;
                }
            }
        });
    }

    public void speak(String text, TextView textView) {
        if (!isReady) return;

        SpannableString spannableText = new SpannableString(text);

        Bundle params = new Bundle();
        params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "PRAYER_UTTERANCE");

        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {

            @Override
            public void onStart(String utteranceId) {
                // Reset all text
                textView.post(() -> textView.setText(spannableText));
            }

            @Override
            public void onRangeStart(String utteranceId, int start, int end, int frame) {
                textView.post(() -> {
                    SpannableString highlightSpan = new SpannableString(text);
                    highlightSpan.setSpan(new BackgroundColorSpan(0xFFFFFF00), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textView.setText(highlightSpan);
                });
            }

            @Override
            public void onDone(String utteranceId) {
                textView.post(() -> textView.setText(spannableText));
            }

            @Override
            public void onError(String utteranceId) { }
        });

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, params, "PRAYER_UTTERANCE");
    }

    public void shutdown() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}


