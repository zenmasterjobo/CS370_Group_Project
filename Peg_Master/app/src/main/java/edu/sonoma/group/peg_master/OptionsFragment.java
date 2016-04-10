package edu.sonoma.group.peg_master;

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
    MediaPlayer mainMenuSound;
        @Override
        public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_options, container, false);

            switchStatus = (TextView) rootView.findViewById(R.id.switchStatus);
            switch1 = (Switch) rootView.findViewById(R.id.switch1);


            //set the Switch to On
            switch1.setChecked(true);
            //attach a listener to the state of the switch
            switch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        switchStatus.setText("Music is On");
                        ((MainMenu) getActivity()).Sound(true);
                    }
                    else {
                        switchStatus.setText("Music is off");
                        ((MainMenu) getActivity()).Sound(false);
                    }
                }

            });
            if (switch1.isChecked()){
                switchStatus.setText("Switch is currently on");
                ((MainMenu) getActivity()).Sound(true);
            }
            else {
                switchStatus.setText("Switch is currently off");
                ((MainMenu) getActivity()).Sound(false);
            }

            return rootView;
        }
    }