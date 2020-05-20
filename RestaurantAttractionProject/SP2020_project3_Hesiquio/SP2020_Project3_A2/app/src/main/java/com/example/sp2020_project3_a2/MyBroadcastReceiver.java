package com.example.sp2020_project3_a2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        String activitySelect = intent.getStringExtra("activity");
        activitySelect = activitySelect.trim();
        //Toast.makeText(context,activitySelect,Toast.LENGTH_SHORT).show();

            //Starting up the correct activity based on the intent
            if(activitySelect.equals("restaurants") == true)
            {

                Intent intent2 = new Intent();
                intent2.setClass(context, RestaurantActivity.class );
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            }
            else
            {

                Intent intent2 = new Intent();
                intent2.setClass(context, AttractionsActivity.class );
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);
            }

        }
        }



