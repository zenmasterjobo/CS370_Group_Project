package edu.sonoma.group.peg_master.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

import edu.sonoma.group.peg_master.R;

/**
 * Created by jordanbergero on 5/1/16.
 */
public class StarScreenFragment extends Fragment {

    private Button returnToWorld;
    private ImageView s1,s2,s3;
    private ArrayList<ImageView> starsList = new ArrayList<ImageView>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_starscreen, container, false);
        rootView.findViewById(R.id.backToLevelSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        s1 = (ImageView) rootView.findViewById(R.id.star1);
        s2 = (ImageView) rootView.findViewById(R.id.star2);
        s3 = (ImageView) rootView.findViewById(R.id.star3);

        starsList = new ArrayList<ImageView>();
        starsList.add(s1);
        starsList.add(s2);
        starsList.add(s3);
        Log.d("Stars in onCreate",starsList.toString());

        return rootView;

    }

    public ArrayList<ImageView> getStarsImageViews(){
        Log.d("Stars in return",starsList.toString());
        return this.starsList;
    }

}
