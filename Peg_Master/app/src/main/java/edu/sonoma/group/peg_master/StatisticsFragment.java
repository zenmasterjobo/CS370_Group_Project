package edu.sonoma.group.peg_master;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    private int numCompletedLevels,numChestsOpened,numStars;
    private User currentUser;
    private ArrayList<String> stats;
    ListView userStats;
    private View fragView;

    public StatisticsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.fragment_statistics, container, false);
        currentUser = GlobalApplicationClass.getCurrentUser();
        numCompletedLevels = currentUser.getCompletedLevels().size();
        userStats = (ListView)fragView.findViewById(R.id.listView);
        stats = new ArrayList<>();
        int stars=0;
        int chests =0;
        for(int i =0;i <currentUser.getCompletedLevels().size();i++){
            stars+=currentUser.getCompletedLevels().get(i).getNumStars();
            chests+=currentUser.getCompletedLevels().get(i).getNumChests();
        }
        numStars = stars;
        numChestsOpened= chests;
        Toast.makeText(getActivity().getApplicationContext(),Integer.toString(numStars),Toast.LENGTH_SHORT).show();


        //populate stats list
        stats.add("Completed Levels: " + Integer.toString(numCompletedLevels));
        stats.add("Total Stars: " + Integer.toString(numStars));
        stats.add("Total Chests Opened: " + Integer.toString(numChestsOpened));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1,stats);
        userStats.setAdapter(arrayAdapter);





        return fragView;

    }



}
