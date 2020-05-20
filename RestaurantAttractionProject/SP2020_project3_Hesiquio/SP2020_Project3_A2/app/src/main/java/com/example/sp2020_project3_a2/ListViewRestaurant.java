package com.example.sp2020_project3_a2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sp2020_project3_a2.dummy.DummyContent;
import com.example.sp2020_project3_a2.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;


public class ListViewRestaurant extends ListFragment {

    //Contents that will be used to populate the list of restaurants
    ListView list;
    String[] attractions = {"Nutella Cafe" , "Nando's Peri Peri Chicken", "Joy Yee", "Shake Shack", "Giordano's", "Portillo's"};
    private ListSelectionListener listener = null;


    // Callback interface that allows this Fragment to notify the QuoteViewerActivity when
    // user clicks on a List Item
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }


    //Good to go
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        setRetainInstance(true);
        Log.i("ListFragment", getClass().getSimpleName() + ":entered onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        Log.i("ListFragment", getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedState);

        // Set the list adapter for the ListView
        // Discussed in more detail in the user interface classes lesson
        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_item, attractions));

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    // Called when the user selects an item from the List
    //Good to go
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        // Indicates the selected item has been checked
        getListView().setItemChecked(pos, true);

        // Inform the QuoteViewerActivity that the item in position pos has been selected
        listener.onListSelection(pos);
    }

    //Good to go
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        try {

            // Set the ListSelectionListener for communicating with the QuoteViewerActivity
            // Try casting the containing activity to a ListSelectionListener
            listener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {
            // Cast failed: This is not going to work because containing activity may not
            // have implemented onListSelection() method
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }


    //Given by default, had to add retain instance to not destroy the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("ListFragment", getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }






}
