package com.example.sentimo.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.sentimo.Mood;

// The AddMoodFragment class is a fragment that creates a Mood object that is then displayed on
// the main screen.

//// This class is a modified version of the ListyCity demo in Lab 3 and uses a lot of resources
//// from the AddCityFragment class.
//// I don't know who wrote the ListyCity demo, but the Android Studio .zip file is on eclass at this URL
//// Url == https://eclass.srv.ualberta.ca/course/view.php?id=54165
public class AddMoodFragment extends ChangeMoodFragment {
    private AddMoodListener listener;

    // Subclass UI initialization

    @Override
    protected void subclassInitialization() {
        this.emotion = null;
        this.situation = null;

    }

    // Subclass listener interfaces and methods
    public interface AddMoodListener{
        void onDonePressed(Mood newMood);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        // Attaching AddMoodListener from parent Activity
        if (context instanceof AddMoodListener){
            this.listener = (AddMoodFragment.AddMoodListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement AddMoodListener");
        }
    }

    @Override
    public void callListener(Mood mood) {
        listener.onDonePressed(mood);
    }

    // Alert dialog builder method

    @Override
    protected AlertDialog.Builder returnBuilder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Add Mood")
                .setNegativeButton("Back", null)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String date = dateTextView.getText().toString();
                        String time = timeTextView.getText().toString();
//                        String emotionText = emojiImageButton.getText().toString();
                        if (AddMoodFragment.this.emotion == null) {
                            throw new RuntimeException("IMPLEMENT WARNING FOR NO EMOTION");
                        }
                        String reason = reasonEditText.getText().toString();
                        Boolean location = locationCheckBox.isChecked();
                        // Need to add if statements for null date, time, or emotion
                        Mood myMood = new Mood(date, time, AddMoodFragment.this.emotion, reason, AddMoodFragment.this.situation, location);
                        callListener(myMood);
                    }
                });
    }

}
