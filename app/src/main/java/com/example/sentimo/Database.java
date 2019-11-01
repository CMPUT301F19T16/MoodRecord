package com.example.sentimo;

import android.widget.BaseAdapter;
import androidx.annotation.Nullable;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference users = db.collection("users");
    private ArrayList<Mood> moodHistory;
    private String username;
    private BaseAdapter mAdapter;

    Database() {
        moodHistory = new ArrayList<>();
    }

    Database(String username) {
        moodHistory = new ArrayList<>();
        this.username = username;
        getAllMoods();
    }

    /**
     * Read data from firestore and save it to local array list
     */
    private void getAllMoods() {
        CollectionReference userMoods = getUserMoods();
        userMoods.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                moodHistory.clear();
                List<DocumentSnapshot> data;
                if (queryDocumentSnapshots != null) {
                    data = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : data) {
                        Mood mood = d.toObject(Mood.class);
                        moodHistory.add(mood);
                    }
                    if (mAdapter != null) mAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     * Return an dynamic array of moods
     *
     * @return Array of moods
     */
    public ArrayList<Mood> getMoodHistory() {
        return moodHistory;
    }

    /**
     * Add a mood to the history
     *
     * @param mood mood to be added
     */
    public void addMood(Mood mood) {
        getUserMoods().document(Integer.toString(mood.hashCode())).set(mood);
    }

    /**
     * Delete a mood from history
     *
     * @param mood mood to be deleted
     */
    public void deleteMood(Mood mood) {
        CollectionReference userMoods = getUserMoods();
        String hashcode = Integer.toString(mood.hashCode());
        userMoods.document(hashcode).delete();
    }

    /**
     * Set a adapter to be notified when the data changed
     *
     * @param mAdapter adapter to be notified
     */
    public void setAdapter(BaseAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    private CollectionReference getUserMoods() {
        return users.document(this.username).collection("moods");
    }

}