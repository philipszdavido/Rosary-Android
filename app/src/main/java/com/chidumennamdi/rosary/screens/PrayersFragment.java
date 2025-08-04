package com.chidumennamdi.rosary.screens;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chidumennamdi.rosary.R;
import com.chidumennamdi.rosary.models.Prayer;
import com.chidumennamdi.rosary.models.PrayerData;
import com.chidumennamdi.rosary.utils.Utils;

import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrayersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrayersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrayersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrayersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrayersFragment newInstance(String param1, String param2) {
        PrayersFragment fragment = new PrayersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context = requireContext();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prayers, container, false);
        TextView date = view.findViewById(R.id.today_date);

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMMM d", Locale.ENGLISH);
        String formattedDate = formatter.format(today);

        date.setText(formattedDate);

        HorizontalScrollView horiScrollView = view.findViewById(R.id.horiScrollView);
        Button short_prayer = view.findViewById(R.id.short_prayer);
        Button angelus = view.findViewById(R.id.angelus);

        LinearLayout hori_button_container = view.findViewById(R.id.hori_button_container);

        for(Prayer prayer : PrayerData.allPrayers) {

            View newButton = constructQuickPrayerView(context, prayer);
            hori_button_container.addView(newButton);

        }

        ListView prayers_list_view = view.findViewById(R.id.prayers_list_view);
        prayers_list_view.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return PrayerData.allPrayers.length;
            }

            @Override
            public Object getItem(int position) {
                return PrayerData.allPrayers[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = convertView;

                if (view == null) {
                    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                    view = inflater.inflate(R.layout.prayer_list_item, parent, false);
                }
                return view;
            }
        });

        return view;

    }

    View constructQuickPrayerView(Context context, Prayer prayer) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        params.setMarginEnd(5);

        GradientDrawable roundedDrawable = new GradientDrawable();
        roundedDrawable.setShape(GradientDrawable.RECTANGLE);
        roundedDrawable.setCornerRadius(24f);
        roundedDrawable.setColor((Utils.randomColor()));

        Button newButton = new Button(context);
        newButton.setLayoutParams(params);
        newButton.setText(prayer.title);
        newButton.setBackground(roundedDrawable);
        //newButton.setBackgroundColor(Utils.randomColor());
        newButton.setTextColor(Color.WHITE);
        int dp = Utils.dp(20, context);
        newButton.setPadding(dp, dp, dp, dp);
        newButton.setOnClickListener(item -> {
            System.out.println(item);
        });

        return newButton;

    }
}

