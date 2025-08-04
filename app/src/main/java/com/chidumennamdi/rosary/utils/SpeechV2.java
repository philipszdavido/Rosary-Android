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

public class SpeechV2 {
    private TextToSpeech tts;
    private float normalSpeed = 1.0f;
    private float slowSpeed = 0.5f;

    public void start(Context context, Locale locale, float speed, TextView textView, ScrollView scrollView, String text) {
        SpannableString spannable = new SpannableString(text);
        tts = new TextToSpeech(context, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(locale);
                tts.setSpeechRate(speed);
                tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                    @Override
                    public void onStart(String utteranceId) { }

                    @Override
                    public void onDone(String utteranceId) {
                        textView.post(() -> textView.setText(text)); // Clear highlights
                    }

                    @Override
                    public void onError(String utteranceId) { }

                    @Override
                    public void onRangeStart(String utteranceId, int start, int end, int frame) {
                        textView.post(() -> {
                            spannable.removeSpan(BackgroundColorSpan.class);
                            spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            textView.setText(spannable);

                            // Auto scroll
                            Layout layout = textView.getLayout();
                            if (layout != null) {
                                int line = layout.getLineForOffset(start);
                                int y = layout.getLineTop(line);
                                scrollView.smoothScrollTo(0, y);
                            }
                        });
                    }
                });

                Bundle params = new Bundle();
                params.putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "PRAYER");
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, params, "PRAYER");
            }
        });
    }

    public void pause() {
        if (tts != null) tts.stop();
    }

    public void resume(String text, Context context, Locale locale, float speed, TextView textView, ScrollView scrollView) {
        start(context, locale, speed, textView, scrollView, text);
    }

    public void stop() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
