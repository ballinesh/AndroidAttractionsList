package com.example.sp2020_project3_a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver myReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating the broadcast receiver
        myReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("com.example.sp2020_project3_a2.startReceiver");
        setTitle("Project 3 App2");
        registerReceiver(myReceiver,intentFilter);

    }

    //Infating the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.a2menu, menu);
        return true;
    }

    //Options menu that gets the correct activity if the user finds themselves in the
    //main activity of app2
    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.attractionsItem:
                Intent intentAttraction = new Intent(MainActivity.this, AttractionsActivity.class);
                startActivity(intentAttraction);
                return true;
            case R.id.restaurantsItem:
                Intent intentRestaurant = new Intent(MainActivity.this, RestaurantActivity.class);
                startActivity(intentRestaurant);
                return true;
            default:
                return true;
        }
    }

}
