package com.example.sentimo.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.sentimo.Emotions.Confident;
import com.example.sentimo.Emotions.Embarrassed;
import com.example.sentimo.Emotions.Emotion;
import com.example.sentimo.Emotions.Happy;
import com.example.sentimo.Emotions.Loved;
import com.example.sentimo.Emotions.Mad;
import com.example.sentimo.Emotions.Sad;
import com.example.sentimo.Emotions.Tired;
import com.example.sentimo.Emotions.Worried;
import com.example.sentimo.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SelectMoodFragment extends DialogFragment {
    SelectMoodFragmentInteractionListener listener;
    Emotion emotion;
    Button happyButton;
    Button sadButton;
    Button madButton;
    Button lovedButton;
    Button embarassedButton;
    Button tiredButton;
    Button confidentButton;
    Button worriedButton;
    View.OnClickListener buttonListener;
    View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.select_emoji, null);

        happyButton = view.findViewById(R.id.happyButton);
        sadButton = view.findViewById(R.id.sadButton);
        madButton = view.findViewById(R.id.madButton);
        lovedButton = view.findViewById(R.id.lovedButton);
        embarassedButton = view.findViewById(R.id.embarassedButton);
        tiredButton = view.findViewById(R.id.tiredButton);
        confidentButton = view.findViewById(R.id.confidentButton);
        worriedButton = view.findViewById(R.id.worriedButton);

        buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.happyButton:
                        emotion = new Happy();
                        break;
                    case R.id.sadButton:
                        emotion = new Sad();
                        break;
                    case R.id.madButton:
                        emotion = new Mad();
                        break;
                    case R.id.lovedButton:
                        emotion = new Loved();
                        break;
                    case R.id.embarassedButton:
                        emotion = new Embarrassed();
                        break;
                    case R.id.tiredButton:
                        emotion = new Tired();
                        break;
                    case R.id.confidentButton:
                        emotion = new Confident();
                        break;
                    case R.id.worriedButton:
                        emotion = new Worried();
                        break;
                    default:
                        throw new RuntimeException("Unknown emotion case");
                }
                listener.MoodReturned(SelectMoodFragment.this.emotion);
                SelectMoodFragment.this.dismiss();
            }
        };

        happyButton.setOnClickListener(buttonListener);
        sadButton.setOnClickListener(buttonListener);
        madButton.setOnClickListener(buttonListener);
        lovedButton.setOnClickListener(buttonListener);
        embarassedButton.setOnClickListener(buttonListener);
        tiredButton.setOnClickListener(buttonListener);
        confidentButton.setOnClickListener(buttonListener);
        worriedButton.setOnClickListener(buttonListener);


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Mood")
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.MoodReturned(null);
                    }
                }).create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (SelectMoodFragmentInteractionListener)getParentFragment();
    }

    public interface SelectMoodFragmentInteractionListener {
        void MoodReturned(Emotion emotion);
    }

}