package edu.sonoma.group.peg_master;

import android.app.Application;
/**
 * Created by jordanbergero on 4/14/16.
 */
public class GlobalApplicationClass extends Application {

    private static GlobalApplicationClass instance;
    private static UserSettings userSettings;
    private DBHandler db;
    private UserTableManager dbManager;
    //private String lastUser;
    private User currentUser;



    @Override
    public void onCreate(){
        super.onCreate();
        db = new DBHandler(getApplicationContext());
        //dbManager = new UserTableManager(getApplicationContext());

        // get settings from DB and set UserSettings values
        getUserSettings().setIsMusicEnabled(true);
    }

    public static GlobalApplicationClass getInstance() {
        return instance;
    }

    public static UserSettings getUserSettings() {
        if (userSettings == null) {
            userSettings = new UserSettings();
        }

        return userSettings;
    }

    public DBHandler getDBHandler(){
        return db;
    }

    public UserTableManager getDBManager(){
        if(dbManager == null){
            dbManager = new UserTableManager(getApplicationContext());
        }
        return dbManager;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void setCurrentUser(User aUser){
        currentUser = aUser;
    }

    public void customAppMethod(){

        //custom application method

    }

}
