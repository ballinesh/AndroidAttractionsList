package com.example.sp2020_project3_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button restaurantButton;
Button attractionButton;
 int requestID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating the buttons
        restaurantButton = findViewById(R.id.restaurantButton);
        attractionButton = findViewById(R.id.attractionButton);

        //Setting on click listener for the restaurant button, will broadcast an intent
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getApplicationContext().checkSelfPermission( "edu.uic.cs478.sp.project3") != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(), "No permission", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{"edu.uic.cs478.sp.project3"}, requestID);
                }
                else
                {


                    Toast.makeText(getApplicationContext(), "Broadcasting Restaurant Intent", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setPackage("com.example.sp2020_project3_a2");
                    intent.putExtra("activity", "restaurants");
                    intent.setAction("com.example.sp2020_project3_a2.startReceiver");
                    intent.setComponent(
                            new ComponentName("com.example.sp2020_project3_a2", "com.example.sp2020_project3_a2.MyBroadcastReceiver"));
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendBroadcast(intent);
                }
            }
        });

        //The same as a restaurant intent except it will open the attractionsActivity
        attractionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {



                //Broadcast for Attractions
                //Also checks for permissions
                if (getApplicationContext().checkSelfPermission( "edu.uic.cs478.sp.project3") != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(), "No permission", Toast.LENGTH_SHORT).show();
                    requestPermissions(new String[]{"edu.uic.cs478.sp.project3"}, requestID);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Broadcasting Attraction Intent", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setPackage("com.example.sp2020_project3_a2");
                    intent.putExtra("activity", "attractions");
                    intent.setAction("com.example.sp2020_project3_a2.startReceiver");
                    intent.setComponent(
                            new ComponentName("com.example.sp2020_project3_a2","com.example.sp2020_project3_a2.MyBroadcastReceiver"));
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendBroadcast(intent);
                }







            }
        });
    }
}
