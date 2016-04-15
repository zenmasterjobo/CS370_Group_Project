package edu.sonoma.group.peg_master;

import android.app.Application;
/**
 * Created by jordanbergero on 4/14/16.
 */
public class GlobalApplicationClass extends Application {

    private static GlobalApplicationClass instance;
    private static UserSettings userSettings;


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



    public void customAppMethod(){

        //custom application method

    }

}
