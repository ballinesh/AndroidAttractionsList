package com.example.sp2020_project3_a2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sp2020_project3_a2.MyListFragment.ListSelectionListener;

public class AttractionsActivity extends AppCompatActivity implements ListSelectionListener {

    //Creating fragment instances
    private Fragment MyListFragment;
    private final WebViewFragment webFragment = new WebViewFragment();
    //Creating the layouts for each view
    private FrameLayout listFrameLayout, webViewFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);


        listFrameLayout = (FrameLayout) findViewById(R.id.ListFragment);
        webViewFrameLayout = (FrameLayout) findViewById(R.id.WebFragment);
        webFragment.setWhichActivity(0);
        //Reference to support Fragment Manager
        fm = getSupportFragmentManager();


        //Start a new transaction
        FragmentTransaction ft = fm.beginTransaction();

        //Replace instead of add
        ft.replace(R.id.ListFragment, new MyListFragment());

        //Commit the Fragment transaction
        ft.commit();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        fm.addOnBackStackChangedListener(
                // UB 2/24/2019 -- Use support version of Listener
                new FragmentManager.OnBackStackChangedListener()
                {
                    public void onBackStackChanged()
                    {
                        setLayout();
                    }
                });

        //Setting the title to display attractions
        setTitle("Attractions");
    }

    public void setLayout()
    {
        if(!webFragment.isAdded())
        {
            //Make the list occupy the entire layout
            //Toast.makeText(this,"Bruh", Toast.LENGTH_SHORT).show();
            listFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            webViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
        }
        else
        {
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                //Make the list take 1/3 of the screen
                listFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT,1f));
                webViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT,2f));
            }
            else
            {
                //Make the Webview occupy whole screen
                listFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
                webViewFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            }

        }

    }

    @Override
    public void onListSelection(int index) {

        // If the QuoteFragment has not been added, add it now
        if (!webFragment.isAdded()) {

            // Start a new FragmentTransaction
            // UB 2/24/2019 -- Now must use compatible version of FragmentTransaction
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            // Add the QuoteFragment to the layout
            fragmentTransaction.add(R.id.WebFragment, webFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            fm.executePendingTransactions();
        }
        setLayout();
        if (webFragment.getShownIndex() != index) {

            // Tell the QuoteFragment to show the quote string at position index
            webFragment.showWebAtIndex(index);

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        //Toast.makeText(this,"Config Change", Toast.LENGTH_SHORT).show();
        // If the QuoteFragment has not been added, add it now
        if (!webFragment.isAdded()) {


            //Code I had originally in here however it would create a web view
            //When we didn't need one
            //So if there is no webview created then do nothing

            /*// Start a new FragmentTransaction
            // UB 2/24/2019 -- Now must use compatible version of FragmentTransaction
            FragmentTransaction fragmentTransaction = fm.beginTransaction();

            // Add the QuoteFragment to the layout
            fragmentTransaction.add(R.id.WebFragment, webFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            fm.executePendingTransactions();*/


        }
        else
        {
            setLayout();
            int index = webFragment.getShownIndex();
            webFragment.showWebAtIndex(index);
        }

    }

    //Menu inflator for the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.a2menu, menu);
        return true;
    }

    //This is the menu that will switch between the restaurant and attractions activity
    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.attractionsItem:
                return true;
            case R.id.restaurantsItem:
                Intent intentRestaurant = new Intent(AttractionsActivity.this, RestaurantActivity.class);
                startActivity(intentRestaurant);
                return true;
            default:
                return true;
        }
    }
}
