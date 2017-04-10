package edu.sonoma.group.peg_master.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import java.util.List;

import edu.sonoma.group.peg_master.Activities.MainMenu;
import edu.sonoma.group.peg_master.Database.DBHandler;
import edu.sonoma.group.peg_master.Database.User;
import edu.sonoma.group.peg_master.Database.UserList;
import edu.sonoma.group.peg_master.Database.UserTableManager;
import edu.sonoma.group.peg_master.GlobalApplicationClass;
import edu.sonoma.group.peg_master.R;
import edu.sonoma.group.peg_master.Activities.overworld;


public class MainMenuFragment extends Fragment{


    //private Button infoButton, optionsButton;
    private Button startButton, infoButton, optionsButton, statsButton, googlePlay;

    private DBHandler db;
    private UserTableManager dbManager;
    //used to set currentUser
    private String lastUser;
    //use currentUser preferences and level progress
    private User currentUser;
    private int currentLevel;
    private List<User> allUsers;

    void updateDB(User aUser){
        int completedLevels = aUser.getCompletedLevels().size() - currentLevel;

        dbManager.addCompletedLevel(aUser, completedLevels);
        currentLevel = aUser.getCompletedLevels().size();
    }
    void updateMusic(){

        dbManager.updateUserMusic(currentUser);
        //update current user w/ music preference
        currentUser = dbManager.getUser(currentUser.getName());
        //Log.v("myApp", "AFTER UPDATE: " + Boolean.toString(dbManager.getUser(currentUser.getName()).getMusic()));
        ((MainMenu)getActivity()).Sound(currentUser.getMusic());


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHandler(getActivity().getApplicationContext());
        dbManager = new UserTableManager(getActivity().getApplicationContext());

/*
        if(GlobalApplicationClass.getClient()!=null){
            Toast.makeText(getActivity().getApplicationContext(),"got client",Toast.LENGTH_SHORT).show();
        }
*/
        //get shared prefs
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity().getApplicationContext());
        SharedPreferences.Editor editor = mPrefs.edit();
        //populate allUsers from db
        allUsers = dbManager.getAllUsers();
        //check if database is empty. if it is, prompt for new user. Set last user and current user
        if(allUsers.size() < 1){
            Intent userIntent = new Intent(getActivity(),UserList.class);
            startActivityForResult(userIntent, 1);

        }

        //check for lastUser in SharedPreferences (should be there)
        lastUser = mPrefs.getString("lastUser","null");

        if(lastUser !="null"){
            currentUser = dbManager.getUser(lastUser);
            currentUser.setCompletedLevels(dbManager.getLevels(currentUser));
            currentLevel = currentUser.getCompletedLevels().size();
            GlobalApplicationClass.setCurrentUser(currentUser);


            //start music if enabled for currentUser
            if(currentUser.getMusic())
                ((MainMenu)getActivity()).Sound(currentUser.getMusic());
            else{
                //why does this work?????
                ((MainMenu)getActivity()).Sound(true);
                ((MainMenu)getActivity()).Sound(false);
            }


            //debug
            //Toast.makeText(this.getActivity().getApplicationContext(),Boolean.toString(currentUser.getMusic()),Toast.LENGTH_SHORT).show();
            //Toast.makeText(this.getActivity().getApplicationContext(),currentUser.getName(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode ==1){
            if(resultCode == Activity.RESULT_OK){

                SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity().getApplicationContext());
                SharedPreferences.Editor editor = mPrefs.edit();

                String userName = data.getStringExtra("name");
                //Toast.makeText(getActivity().getApplicationContext(), userName, Toast.LENGTH_SHORT).show();
                User newUser = new User(userName);
                dbManager.addUserData(newUser);
                currentUser = newUser;
                GlobalApplicationClass.setCurrentUser(currentUser);
                lastUser = userName;
                editor.putString("lastUser",lastUser);
                editor.apply();



            }
        }
        //if user has gotten out of overworld screen, update levels table
        else if(requestCode ==2){

            /*
            int completedLevels = GlobalApplicationClass.getCurrentUser().getCompletedLevels().size() - currentLevel;
            Toast.makeText(getActivity().getApplicationContext(),"COMPLETED LEVELS: " + Integer.toString(completedLevels),Toast.LENGTH_SHORT).show();

            dbManager.addCompletedLevel(GlobalApplicationClass.getCurrentUser(), completedLevels);
            currentLevel = GlobalApplicationClass.getCurrentUser().getCompletedLevels().size();
            */
//            updateDB(GlobalApplicationClass.getCurrentUser());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        startButton = (Button) view.findViewById(R.id.Startbutton);
        infoButton = (Button) view.findViewById(R.id.Infobutton);
        optionsButton = (Button) view.findViewById(R.id.Optionsbutton);
        statsButton = (Button)view.findViewById(R.id.Statsbutton);
        googlePlay = (Button)view.findViewById(R.id.googlePlay);
        //createUserButton = (Button)view.findViewById(R.id.CreateUser);
        //changeUsersButton = (Button)view.findViewById(R.id.ChangeUsers);

        googlePlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                GoogleApiClient c = GlobalApplicationClass.getClient();
                c.connect();
                startActivityForResult(Games.Leaderboards.getLeaderboardIntent(c,
                        "CgkI65D-vuwKEAIQBg"), 1);
                Log.d("GooglePlayButton", "has been clicked, now calling connect");
            }
        });








        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), overworld.class);
                Bundle bundle = new Bundle();
                //bundle.putString("currentUser",currentUser.getName());
                //intent.putExtras(bundle);
                startActivityForResult(intent,2);
                //startActivity(intent);
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
                //http://stackoverflow.com/questions/13445594/data-sharing-between-fragments-and-activity-in-android/20521851#20521851
                //http://developer.android.com/guide/components/fragments.html#CommunicatingWithActivity
                OptionsFragment newFrag = new OptionsFragment();
                //bundle music setting
                Bundle bundle = new Bundle();
                bundle.putBoolean("bmusic",currentUser.getMusic());
                //Toast.makeText(getActivity().getApplicationContext(),Boolean.toString(currentUser.getMusic()),Toast.LENGTH_SHORT).show();

                newFrag.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFrag);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatisticsFragment newFrag = new StatisticsFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,newFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        
        return view;
    }






}
