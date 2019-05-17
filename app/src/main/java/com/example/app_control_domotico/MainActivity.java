package com.example.app_control_domotico;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.*;

public class MainActivity extends AppCompatActivity {
    protected FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();

    }

    public void OnClic(View  view){
        if(view.getId() == R.id.btnLed1){
            FirebaseApp app = db.getApp();
            Toast.makeText(getApplicationContext(),document.getId() + " => " + document.getData(),Toast.LENGTH_SHORT).show();
        }
    }
}
