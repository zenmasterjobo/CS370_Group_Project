package edu.sonoma.group.peg_master;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by jordanbergero on 4/14/16.
 */
public class GlobalApplicationClass extends Application {

    private static GlobalApplicationClass instance;
    private static UserSettings userSettings;
    private static User currentUser;
    private static GoogleApiClient client;




    @Override
    public void onCreate(){
        super.onCreate();

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

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(User aUser){
        currentUser = aUser;
    }

    public static GoogleApiClient getClient(){
        return client;
    }
    public static void setClient(GoogleApiClient aClient){
        client = aClient;
    }



    public void customAppMethod(){

        //custom application method

    }

}
