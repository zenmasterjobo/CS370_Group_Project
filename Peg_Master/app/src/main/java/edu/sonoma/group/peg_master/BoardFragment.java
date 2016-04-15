package edu.sonoma.group.peg_master;

import android.app.Activity;
import android.media.Image;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 3/24/16.
 */
public class BoardFragment extends Fragment {

    private ImageButton chest1_1, chest1_2, chest1_3, chest2_1, chest2_2, chest2_3, chest3_1, chest3_2, chest3_3, chest4_1, chest4_2, chest4_3;
    private TextView chest1_1number, chest1_2number, chest1_3number, chest2_1number, chest2_2number, chest2_3number, chest3_1number, chest3_2number, chest3_3number, chest4_1number, chest4_2number, chest4_3number,
            chest1_1left, chest1_1right, chest1_2left, chest1_2right, chest1_3left, chest1_3right,
            chest2_1left, chest2_1right, chest2_2left, chest2_2right, chest2_3left, chest2_3right,
            chest3_1left, chest3_1right, chest3_2left, chest3_2right, chest3_3left, chest3_3right,
            chest4_1left, chest4_1right, chest4_2left, chest4_2right, chest4_3left, chest4_3right;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        //back = (Button) getView().findViewById(R.id.back);
        //back.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        MainMenuFragment newFrag = new MainMenuFragment();
        //        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //        transaction.replace(R.id.fragment_container, newFrag);
        //        transaction.addToBackStack(null);
        //        transaction.commit();
        //    }
        //});
        View rootView = inflater.inflate(R.layout.fragment_board, container, false);

        chest1_1 = (ImageButton) rootView.findViewById(R.id.chest1_1);
        chest1_2 = (ImageButton) rootView.findViewById(R.id.chest1_2);
        chest1_3 = (ImageButton) rootView.findViewById(R.id.chest1_3);
        chest2_1 = (ImageButton) rootView.findViewById(R.id.chest2_1);
        chest2_2 = (ImageButton) rootView.findViewById(R.id.chest2_2);
        chest2_3 = (ImageButton) rootView.findViewById(R.id.chest2_3);
        chest3_1 = (ImageButton) rootView.findViewById(R.id.chest3_1);
        chest3_2 = (ImageButton) rootView.findViewById(R.id.chest3_2);
        chest3_3 = (ImageButton) rootView.findViewById(R.id.chest3_3);
        chest4_1 = (ImageButton) rootView.findViewById(R.id.chest4_1);
        chest4_2 = (ImageButton) rootView.findViewById(R.id.chest4_2);
        chest4_3 = (ImageButton) rootView.findViewById(R.id.chest4_3);

        chest1_1number = (TextView) rootView.findViewById(R.id.chest1_1number);
        chest1_2number = (TextView) rootView.findViewById(R.id.chest1_2number);
        chest1_3number = (TextView) rootView.findViewById(R.id.chest1_3number);
        chest2_1number = (TextView) rootView.findViewById(R.id.chest2_1number);
        chest2_2number = (TextView) rootView.findViewById(R.id.chest2_2number);
        chest2_3number = (TextView) rootView.findViewById(R.id.chest2_3number);
        chest3_1number = (TextView) rootView.findViewById(R.id.chest3_1number);
        chest3_2number = (TextView) rootView.findViewById(R.id.chest3_2number);
        chest3_3number = (TextView) rootView.findViewById(R.id.chest3_3number);
        chest4_1number = (TextView) rootView.findViewById(R.id.chest4_1number);
        chest4_2number = (TextView) rootView.findViewById(R.id.chest4_2number);
        chest4_3number = (TextView) rootView.findViewById(R.id.chest4_3number);

        chest1_1left = (TextView) rootView.findViewById(R.id.chest1_1left);
        chest1_2left = (TextView) rootView.findViewById(R.id.chest1_2left);
        chest1_3left = (TextView) rootView.findViewById(R.id.chest1_3left);
        chest2_1left = (TextView) rootView.findViewById(R.id.chest2_1left);
        chest2_2left = (TextView) rootView.findViewById(R.id.chest2_2left);
        chest2_3left = (TextView) rootView.findViewById(R.id.chest2_3left);
        chest3_1left = (TextView) rootView.findViewById(R.id.chest3_1left);
        chest3_2left = (TextView) rootView.findViewById(R.id.chest3_2left);
        chest3_3left = (TextView) rootView.findViewById(R.id.chest3_3left);
        chest4_1left = (TextView) rootView.findViewById(R.id.chest4_1left);
        chest4_2left = (TextView) rootView.findViewById(R.id.chest4_2left);
        chest4_3left = (TextView) rootView.findViewById(R.id.chest4_3left);

        chest1_1right = (TextView) rootView.findViewById(R.id.chest1_1right);
        chest1_2right = (TextView) rootView.findViewById(R.id.chest1_2right);
        chest1_3right = (TextView) rootView.findViewById(R.id.chest1_3right);
        chest2_1right = (TextView) rootView.findViewById(R.id.chest2_1right);
        chest2_2right = (TextView) rootView.findViewById(R.id.chest2_2right);
        chest2_3right = (TextView) rootView.findViewById(R.id.chest2_3right);
        chest3_1right = (TextView) rootView.findViewById(R.id.chest3_1right);
        chest3_2right = (TextView) rootView.findViewById(R.id.chest3_2right);
        chest3_3right = (TextView) rootView.findViewById(R.id.chest3_3right);
        chest4_1right = (TextView) rootView.findViewById(R.id.chest4_1right);
        chest4_2right = (TextView) rootView.findViewById(R.id.chest4_2right);
        chest4_3right = (TextView) rootView.findViewById(R.id.chest4_3right);

        return rootView;
    }
}
