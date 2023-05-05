package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore fb;
    int moisture=0;
    int temperature=0;
    int humidity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fb = FirebaseFirestore.getInstance();
        Log.d("+++++++++",fb.toString());

        circulargaugeview gauge1 = findViewById(R.id.gauge1);
        circulargaugeview gauge2 = findViewById(R.id.gauge2);
        circulargaugeview gauge3 = findViewById(R.id.gauge3);

        CollectionReference usersRef = fb.collection("Record");

        fb.collection("Record").document("Sh9X4f4S7WSbicNP7hIy")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.d("++++++++",(documentSnapshot.getData()).toString());
                        moisture = documentSnapshot.getLong("Moisture").intValue();
                        Log.d("++++++C", String.valueOf(moisture));
                        temperature = (int) documentSnapshot.getLong("Temperature").intValue();
                        Log.d("++++++C",String.valueOf(temperature));
                        humidity = (int) documentSnapshot.getLong("Humidity").intValue();
                        Log.d("++++++C",String.valueOf(humidity));
                        gauge1.setProgress(moisture);
                        gauge2.setProgress(temperature);
                        gauge3.setProgress(humidity);
                    }
                });
        Log.d("++++++C", String.valueOf(moisture));
        Log.d("++++++C",String.valueOf(temperature));
        Log.d("++++++C",String.valueOf(humidity));

    }
}