package edu.sonoma.group.peg_master;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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


public class MainMenuFragment extends Fragment{


    //private Button infoButton, optionsButton;
    private ImageButton startButton, infoButton, optionsButton;
    private Button changeUsersButton;
    private DBHandler db;
    private UserTableManager dbManager;
    //used to set currentUser
    private String lastUser;
    //use currentUser preferences and level progress
    private User currentUser;
    private int currentLevel;
    private List<User> allUsers;


    void updateMusic(){
        Toast.makeText(getActivity().getApplicationContext(),"BEFORE: " + Boolean.toString(currentUser.getMusic()),Toast.LENGTH_SHORT).show();

        dbManager.updateUserMusic(currentUser);
        //update current user w/ music preference
        currentUser = dbManager.getUser(currentUser.getName());
        //Log.v("myApp", "AFTER UPDATE: " + Boolean.toString(dbManager.getUser(currentUser.getName()).getMusic()));
        Toast.makeText(getActivity().getApplicationContext(),"AFTER" + Boolean.toString(currentUser.getMusic()),Toast.LENGTH_SHORT).show();
        ((MainMenu)getActivity()).Sound(currentUser.getMusic());


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHandler(getActivity().getApplicationContext());
        dbManager = new UserTableManager(getActivity().getApplicationContext());




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
                lastUser = userName;
                editor.putString("lastUser",lastUser);
                editor.apply();



            }
        }
        //if user has gotten out of overworld screen, update levels table
        else if(requestCode ==2){

            int completedLevels = GlobalApplicationClass.getCurrentUser().getCompletedLevels().size() - currentLevel;
            Toast.makeText(getActivity().getApplicationContext(),"COMPLETED LEVELS: " + Integer.toString(completedLevels),Toast.LENGTH_SHORT).show();

            dbManager.addCompletedLevel(GlobalApplicationClass.getCurrentUser(), completedLevels);
            currentLevel = GlobalApplicationClass.getCurrentUser().getCompletedLevels().size();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        startButton = (ImageButton) view.findViewById(R.id.Startbutton);
        infoButton = (ImageButton) view.findViewById(R.id.Infobutton);
        optionsButton = (ImageButton) view.findViewById(R.id.Optionsbutton);
        //createUserButton = (Button)view.findViewById(R.id.CreateUser);
        //changeUsersButton = (Button)view.findViewById(R.id.ChangeUsers);










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
                Toast.makeText(getActivity().getApplicationContext(),Boolean.toString(currentUser.getMusic()),Toast.LENGTH_SHORT).show();

                newFrag.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFrag);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });




        /*
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User testUser = new User();
                testUser.setName("nick");
                testUser.setPlayed(100);
                testUser.setScore(69);
                testUser.setPlayed(100);
                testUser.setChestsOpened(63);
                dbManager.addUserData(testUser);

            }
        });
        */

        /*
        changeUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //List<User>myUsers = new ArrayList<User>();
                //update list of users, then create array of user.getName()
                allUsers = dbManager.getAllUsers();
                String[] userStrings = new String[allUsers.size()];
                int idx =0;
                for(User aUser: allUsers){
                    userStrings[idx] = aUser.getName();
                    idx++;
                }
                Intent userIntent = new Intent(getActivity(),UserList.class);
                userIntent.putExtra("allUsers", userStrings);
                startActivityForResult(userIntent, 1);
                Toast.makeText(getActivity().getApplicationContext(), Integer.toString(allUsers.size()), Toast.LENGTH_LONG).show();



            }
        });
    */
        return view;
    }






}
