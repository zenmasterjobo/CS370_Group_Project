package edu.sonoma.group.peg_master;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class overworld extends AppCompatActivity {

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     *//*
    private static final boolean AUTO_HIDE = true;

    *//**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     *//*
    private static final int AUTO_HIDE_DELAY_MILLIS = 300;

    *//**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     *//*
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    *//**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     *//*
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };*/

    //level buttons
    private Button lvl1Btn,lvl2Btn,lvl3Btn, lvl4Btn, lvl5Btn,lvl6Btn, lvl7Btn, lvl8Btn, lvl9Btn,lvl10Btn, lvl11Btn;

    //database stuff
    private DBHandler db;
    private UserTableManager dbManager;
    private int displayedButtons;
    private ArrayList<Button> allButtons;


    private User currentUser = GlobalApplicationClass.getCurrentUser();

    @Override
    public void onStart(){
        super.onStart();
        db = new DBHandler(getApplicationContext());
        dbManager = new UserTableManager(getApplicationContext());
        int userCLevel = GlobalApplicationClass.getCurrentUser().getCompletedLevels().size();
        for (int i = 0; i < displayedButtons; i++){
            if( userCLevel-1 < i ) {
                Log.d("User has not completed", Integer.toString(i));
                allButtons.get(i).setBackgroundColor(Color.parseColor("#ff0000"));
            }
            else {
                allButtons.get(i).setBackgroundColor(Color.parseColor("#66ff66"));
            }
            if (userCLevel == i){
                allButtons.get(i).setBackgroundColor(Color.parseColor("#0000ff"));
            }

        }
    }


    protected Integer numberOfChests(Integer level){
        Integer chests = 3;
        while (level - 5 >= 0){
            level -= 5;
            chests += 1;
        }

        return chests;
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        //if user finishes a level
        if(requestCode ==1){
            if(resultCode == Activity.RESULT_OK){
                dbManager.addCompletedLevel(GlobalApplicationClass.getCurrentUser(), 1);
                Toast.makeText(getApplicationContext(),"added level",Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onDestroy(){
        //Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK);
        //Toast.makeText(getApplicationContext(),"ONDESTROY",Toast.LENGTH_SHORT).show();
        //finishActivity(2);
        super.onDestroy();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_overworld);

        ScrollView scroll = (ScrollView) findViewById(R.id.fullscreen_content);
        scroll.scrollTo(0, scroll.getBottom());

        //set them level buttons to the ones in the layout
        lvl1Btn = (Button)findViewById(R.id.level1);
        lvl2Btn = (Button)findViewById(R.id.level2);
        lvl3Btn = (Button)findViewById(R.id.level3);
        lvl4Btn = (Button)findViewById(R.id.level4);
        lvl5Btn = (Button)findViewById(R.id.level5);
        lvl6Btn = (Button)findViewById(R.id.level6);
        lvl7Btn = (Button)findViewById(R.id.level7);
        lvl8Btn = (Button)findViewById(R.id.level8);
        lvl9Btn = (Button)findViewById(R.id.level9);
        lvl10Btn = (Button)findViewById(R.id.level10);
        lvl11Btn = (Button)findViewById(R.id.level11);

        allButtons = new ArrayList<Button>();

        allButtons.addAll(Arrays.asList(lvl1Btn,lvl2Btn,lvl3Btn, lvl4Btn, lvl5Btn,lvl6Btn, lvl7Btn,
                                        lvl8Btn, lvl9Btn,lvl10Btn, lvl11Btn));
        displayedButtons = allButtons.size();

        //debug GAC works
        for (int i = 0; i < displayedButtons; i ++){
            Button b = allButtons.get(i);
            final int finalI = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Create intent to switch to levelActivity
                    Intent intent = new Intent(overworld.this, levelActivity.class);
                    int requestCode = 1;

                    //make a bundle to transfer chest# for each level
                    int level = finalI +1;
                    int userCLevel = GlobalApplicationClass.getCurrentUser().getCompletedLevels().size();

                    //only let them play the level if they have completed previous levels.
                    if(userCLevel >= level-1 || level ==1) {
                        Bundle numChests = new Bundle();
                        numChests.putInt("numChests", numberOfChests(level));
                        numChests.putInt("levelnum", level);
                        //put bundle in the intent for transfer. Use getIntent().getExtras().getString/int/...(key)
                        //inside activity to access this data.
                        intent.putExtras(numChests);
                        //switch activity
                        //startActivity(intent);
                        startActivityForResult(intent, requestCode);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"PLAY PREVIOUS LEVEL TO UNLOCK",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

        /* MIGHT BE REMOVABLE SOON IF ABOVE WORKS
        //btn onclicklisteners
        lvl1Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Create intent to switch to levelActivity
                Intent intent = new Intent(overworld.this, levelActivity.class);
                int requestCode = 1;

                //make a bundle to transfer chest# for each level
                int level = 1;
                Bundle numChests = new Bundle();
                numChests.putInt("numChests", numberOfChests(level));

                //put bundle in the intent for transfer. Use getIntent().getExtras().getString/int/...(key)
                //inside activity to access this data.
                intent.putExtras(numChests);

                //switch activity
                //startActivity(intent);
                startActivityForResult(intent, requestCode);

            }
        });

        lvl2Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Create intent to switch to levelActivity
                Intent intent = new Intent(overworld.this, levelActivity.class);

                //make a bundle to transfer chest# for each level
                int level = 2;
                Bundle numChests = new Bundle();
                numChests.putInt("numChests", numberOfChests(level));

                //put bundle in the intent for transfer. Use getIntent().getExtras().getString/int/...(key)
                //inside activity to access this data.
                intent.putExtras(numChests);

                //switch activity
                startActivity(intent);
            }
        });

        lvl3Btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Create intent to switch to levelActivity
                Intent intent = new Intent(overworld.this, levelActivity.class);

                //make a bundle to transfer chest# for each level
                int level = 3;
                Bundle numChests = new Bundle();
                numChests.putInt("numChests",numberOfChests(level));

                //put bundle in the intent for transfer. Use getIntent().getExtras().getString/int/...(key)
                //inside activity to access this data.
                intent.putExtras(numChests);

                //switch activity
                startActivity(intent);
            }
        });

        */
        //auto generated
       // mVisible = true;
       // mControlsView = findViewById(R.id.fullscreen_content_controls);
       // mContentView = findViewById(R.id.fullscreen_content);
    }

    /*@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    *//**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     *//*
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }*/
}
