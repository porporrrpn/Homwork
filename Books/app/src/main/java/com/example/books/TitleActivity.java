package com.example.books;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

public class TitleActivity  extends AppCompatActivity {

    private static final String TAG = "TitleActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atitvity_title);
        Log.d(TAG, "onCreate: started.");
    }

}
