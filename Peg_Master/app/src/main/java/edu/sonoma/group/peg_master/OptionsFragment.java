package edu.sonoma.group.peg_master;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * Created by jordanbergero on 3/26/16.
 */


public class OptionsFragment extends Fragment {
    private Switch switch1;
    private TextView switchStatus;
    private Bundle savedState;
    private Bundle savedState1;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_options, null);
        switchStatus = (TextView) rootView.findViewById(R.id.switchStatus);
        switch1 = (Switch) rootView.findViewById(R.id.switch1);


        boolean enabled = GlobalApplicationClass.getUserSettings().isMusicEnabled();
        switch1.setChecked(enabled);


        if(savedInstanceState != null && savedState == null)
        {
            //savedState = savedInstanceState.getBundle(BundleTagUtility.SWITCHSTATUS);
            //savedState = savedInstanceState.getBundle(BundleTagUtility.SWITCH1);
           // savedState = savedInstanceState.getBundle("MyBoolean");

        }

            //switchStatus.setText(savedState.getCharSequence(BundleTagUtility.SWITCHSTATUS));
            //switch1.setChecked(savedState1.getBoolean(BundleTagUtility.SWITCH1));


        //savedState = null;

        //set the Switch to On
        //switch1.setChecked(true);
        //attach a listener to the state of the switch
        switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            //only runs the status of the switch is changed
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switch1.isChecked()) {
                    switchStatus.setText("Music is On");
                    ((MainMenu) getActivity()).Sound(true);
                    GlobalApplicationClass.getUserSettings().setIsMusicEnabled(true);

                }
                else {
                    switchStatus.setText("Music is off");
                    ((MainMenu) getActivity()).Sound(false);
                    GlobalApplicationClass.getUserSettings().setIsMusicEnabled(false);
                    //savedState = saveState();
                    //onSaveInstanceState(savedState);

                }
                //call update currentUser music setting

            }

        });

        //This is needed to set the text when the fragment loads.
        if (switch1.isChecked()){
            switchStatus.setText("Music is On");
            ((MainMenu) getActivity()).Sound(true);
            GlobalApplicationClass.getUserSettings().setIsMusicEnabled(true);
            //savedState = saveState();
            //onSaveInstanceState(savedState);

        }
        else {
            switchStatus.setText("Music is Off");
            ((MainMenu) getActivity()).Sound(false);
            GlobalApplicationClass.getUserSettings().setIsMusicEnabled(false);
            //savedState = saveState();
            //onSaveInstanceState(savedState);

        }



        return rootView;
    }



    @Override
    public void onDestroyView(){
        super.onDestroyView();
        switchStatus = null;
    }

    private Bundle saveState() {
        Bundle state = new Bundle();
       // state.putCharSequence(BundleTagUtility.SWITCHSTATUS, switchStatus.getText());
        //state.putBoolean(BundleTagUtility.SWITCH1, switch1.isChecked());
        state.putBoolean("MyBoolean", false);
        return state;
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        //outState.putBundle(BundleTagUtility.SWITCH1, (savedState != null) ? savedState : saveState());
        //outState.putBoolean("MyBoolean", false);
    }



}