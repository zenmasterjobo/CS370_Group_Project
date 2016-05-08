package edu.sonoma.group.peg_master;
import android.content.IntentSender;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

public class MainMenu extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    //creating a media player instance for the sound file
    private MediaPlayer mainMenuSound;
    Boolean mainSound = true;
    private Bundle savedState;

    private Button startButton, infoButton, optionsButton;

    // GOOGLE STUFF
    private static int RC_SIGN_IN = 9001;

    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public MediaPlayer getMainMenuSound(){return mainMenuSound;}

    public GoogleApiClient getClient(){
        return client;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Create the Google Api Client with access to the Play Games services
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                        // add other APIs and scopes here as needed
                .build();
        setContentView(R.layout.activity_main_menu);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MainMenuFragment MMF = new MainMenuFragment();
        ft.replace(R.id.fragment_container, MMF, null);
        ft.addToBackStack("MMF");
        ft.commit();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(Games.API)
                .build();
        mainMenuSound = MediaPlayer.create(this, R.raw.windwaker);


    }

    @Override
    public void onStart() {
        super.onStart();
        //start the music
        //mainMenuSound.start();
        //boolean enabled = GlobalApplicationClass.getUserSettings().isMusicEnabled();
        //Sound(enabled);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Log.d("MainMenu", "client: CONNECTING");
        client.connect();

        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainMenu Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://edu.sonoma.group.peg_master/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainMenu Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://edu.sonoma.group.peg_master/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        Log.d("MainMenu", "onStop(): disconnecting");
        if (client.isConnected()) {
            Log.d("MainMenu", "We have somehow connected");
            client.disconnect();
        }
        client.disconnect();
    }

    public void Sound(Boolean Status){
        if(Status == true) {
            //play the sound when the app opens
            Toast.makeText(this,"SOUND TRUE", Toast.LENGTH_SHORT).show();

            mainMenuSound.start();
        }
        if (Status == false){
            ////pause the sound when the switch is flipped
            mainMenuSound.pause();
        }

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("mainMenuAc", "We have connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("mainMenu", "connection suspended");
        client.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("conncetionFailed", "failed to connect");
        Log.d("f", connectionResult.toString());
        if (connectionResult.hasResolution()) {
            try {
                // !!!
                connectionResult.startResolutionForResult(this, 1);
            } catch (IntentSender.SendIntentException e) {
                client.connect();
            }
        }
    }
}