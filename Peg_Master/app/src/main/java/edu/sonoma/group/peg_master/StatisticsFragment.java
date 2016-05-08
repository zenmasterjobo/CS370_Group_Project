package edu.sonoma.group.peg_master;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

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
    private TextView completedLevels, totalStars, chestsOpened;
    private Button back, achievemnts;

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

        completedLevels = (TextView) fragView.findViewById(R.id.compLevels);
        totalStars = (TextView) fragView.findViewById(R.id.starsEarned);
        chestsOpened = (TextView) fragView.findViewById(R.id.chestsOpened);

        completedLevels.setText("Completed Levels: " + Integer.toString(numCompletedLevels));
        totalStars.setText("Total Stars: " + Integer.toString(numStars));
        chestsOpened.setText("Total Chests Opened: " + Integer.toString(numChestsOpened));

        back = (Button) fragView.findViewById(R.id.back);
        achievemnts = (Button) fragView.findViewById(R.id.ach);

        achievemnts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleApiClient client = GlobalApplicationClass.getClient();
                startActivityForResult(Games.Achievements.getAchievementsIntent(client),
                        0);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        return fragView;

    }



}
