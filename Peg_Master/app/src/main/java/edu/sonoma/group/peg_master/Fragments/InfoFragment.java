package edu.sonoma.group.peg_master.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.sonoma.group.peg_master.R;


public class InfoFragment extends Fragment {

    private Button back;
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
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        rootView.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return rootView;
    }
}
