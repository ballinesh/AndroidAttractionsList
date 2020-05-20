package com.example.sp2020_project3_a2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class WebViewFragment extends Fragment {
    //creating all of the data needed to display web pages
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String[] webURL = {"https://www.choosechicago.com/articles/tours-and-attractions/the-bean-chicago/",
                        "https://www.starbucksreserve.com/en-us/locations/chicago",
                        "https://www.chicago.gov/city/en/depts/dca/supp_info/millennium_park.html",
                        "https://navypier.org/",
                        "https://www.artic.edu/",
                        "https://www.lpzoo.org/",
                        "https://www.msichicago.org/",
                        "https://www.sheddaquarium.org/",
                        "https://www.adlerplanetarium.org/"};
    String[] webURLRestaurants = {"https://www.nutella.com/us/en/nutella-cafe-chicago",
                                    "https://www.nandosperiperi.com/",
                                    "https://joyyee.com/",
                                    "https://www.shakeshack.com/",
                                    "https://giordanos.com/",
                                    "https://www.portillos.com/index.html"};
    //Used to display a specific web page
    WebView webView = null;
    private int currentIndex = -1;
    private int whichActivity = -1;

    //This was initialized by default, I don't think I use these
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public WebViewFragment() {
        // Required empty public constructor
    }

    void updateWebsite(String website)
    {
        webView.loadUrl(website);
    }


    public static WebViewFragment newInstance(String param1, String param2) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    //Getters and setters for indexes
    public void setWhichActivity(int set)
    {
        whichActivity = set;
    }
    public int getShownIndex()
    {
        return currentIndex;
    }

    //Display the webpage for a given index
    void showWebAtIndex(int newIndex)
    {
        if(newIndex >= 0)
        {
            if(whichActivity == 0)
            {
                currentIndex = newIndex;
                webView.loadUrl(webURL[newIndex]);
            }
            else
            {
                currentIndex = newIndex;
                webView.loadUrl(webURLRestaurants[newIndex]);
            }

        }
    }

    //Initialized by default
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    //This helps keeping the current index when a fragment is destroyed and restored
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        webView = (WebView) view.findViewById(R.id.webview);

        if(savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt("currentIndex");
            webView.loadUrl(webURL[currentIndex]);
        }
        return view;
    }

    //Does a similar thing as onCreateView
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt("currentIndex");
            webView = (WebView) getActivity().findViewById(R.id.webview);
            showWebAtIndex(currentIndex);
        }

    }

    //This was given by default
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //Given by default
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    //Given by default
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Given by default
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //Saving the state of the program with index and current URL to display
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Toast.makeText(getActivity(), "onSaveInstanceState", Toast.LENGTH_LONG).show();

        outState.putString("currentURL", webURL[currentIndex]);
        outState.putInt("currentIndex", currentIndex);
    }
}
