package com.th3snehasish.gdgassignmentone;

import android.app.ListActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;


public class MainActivity extends ListActivity {


    String[] names ={
            "Snehasish",
            "Rajashree",
            "Pradeep",
            "Madhuchhanda",
            "Rajkishor",
            "Sailabala",
            "Sruti",
            "Chidananda",
            "Mitanjali",
    };

    String[] relation ={
            "My Self",
            "Someone special",
            "Dad",
            "Mom",
            "Grandpa",
            "Grandma",
            "Sister",
            "Uncle",
            "Aunt",
    };

    Integer[] imageIDs = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher

    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        FamilyAdapter adapter = new FamilyAdapter(this, names, imageIDs, relation);
        setListAdapter(adapter);
    }

}