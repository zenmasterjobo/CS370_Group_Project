package edu.sonoma.group.peg_master;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

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

    public static void updateAcheivements(int chests_completed, int moves_taken, int stars_earned){
        int total_chests = 0;
        int total_stars = 0;
        for (int i = 0; i < currentUser.getCompletedLevels().size(); i++){
            total_chests += currentUser.getCompletedLevels().get(i).getNumChests();
            total_stars += currentUser.getCompletedLevels().get(i).getNumStars();
        }
        if (chests_completed > 0){
            Log.d("Global", "You should get the acheivement now");
            Games.Achievements.unlock(client, "CgkI65D-vuwKEAIQAQ");
        }
        if (currentUser.getCompletedLevels().size() >= 5){
            Games.Achievements.unlock(client, "CgkI65D-vuwKEAIQAg");
        }
        if (currentUser.getCompletedLevels().size() >= 29){
            Games.Achievements.unlock(client, "CgkI65D-vuwKEAIQAw");
        }
        if (moves_taken > 10){
            Games.Achievements.unlock(client, "CgkI65D-vuwKEAIQBA");
        }
        if (stars_earned == 3){
            Games.Achievements.unlock(client, "CgkI65D-vuwKEAIQBQ");
        }
        long t = (long) total_stars;
        Games.Leaderboards.submitScore(client, "CgkI65D-vuwKEAIQBg", t);
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
        client.connect();
    }



    public void customAppMethod(){

        //custom application method

    }

}
