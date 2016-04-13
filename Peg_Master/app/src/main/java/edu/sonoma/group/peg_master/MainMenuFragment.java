package edu.sonoma.group.peg_master;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainMenuFragment extends Fragment {


    //private Button infoButton, optionsButton;
    private ImageButton startButton, infoButton, optionsButton;
    private Button createUserButton,printUsersButton;
    private DBHandler db;
    private UserTableManager dbManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHandler(getActivity().getApplicationContext());
        dbManager = new UserTableManager(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        startButton = (ImageButton) view.findViewById(R.id.Startbutton);
        infoButton = (ImageButton) view.findViewById(R.id.Infobutton);
        optionsButton = (ImageButton) view.findViewById(R.id.Optionsbutton);
        createUserButton = (Button)view.findViewById(R.id.CreateUser);
        printUsersButton = (Button)view.findViewById(R.id.PrintUsers);










        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), overworld.class);
                startActivity(intent);
                //BoardFragment newFrag = new BoardFragment();
                //FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.fragment_container, newFrag);
                //transaction.addToBackStack(null);
                //transaction.commit();
            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoFragment newFrag = new InfoFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsFragment newFrag = new OptionsFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFrag);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User testUser = new User();
                testUser.setName("nick");
                testUser.setPlayed(100);
                testUser.setScore(69);
                dbManager.addUserData(testUser);

            }
        });

        printUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User>myUsers = new ArrayList<User>();
                myUsers = dbManager.getAllUsers();
                Toast.makeText(getActivity().getApplicationContext(), Integer.toString(myUsers.size()), Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }

}
