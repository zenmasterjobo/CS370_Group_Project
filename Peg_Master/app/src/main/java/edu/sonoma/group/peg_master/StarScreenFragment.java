package edu.sonoma.group.peg_master;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Button;

/**
 * Created by jordanbergero on 5/1/16.
 */
public class StarScreenFragment extends Fragment {

    private Button returnToWorld;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_starscreen, null);
        rootView.findViewById(R.id.backToLevelSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return rootView;

    }
}
