package edu.sonoma.group.peg_master;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by admin on 3/24/16.
 */
public class BoardFragment extends Fragment {

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
        return rootView;
    }
}
