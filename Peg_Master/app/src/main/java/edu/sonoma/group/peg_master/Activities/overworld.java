package edu.sonoma.group.peg_master.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.sonoma.group.peg_master.Database.DBHandler;
import edu.sonoma.group.peg_master.Database.User;
import edu.sonoma.group.peg_master.Database.UserTableManager;
import edu.sonoma.group.peg_master.Application_Classes.GlobalApplicationClass;
import edu.sonoma.group.peg_master.R;

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


     //ButterKnife

    @BindView(R.id.level1) Button lvl1Btn;
    @BindView(R.id.level2) Button lvl2Btn;
    @BindView(R.id.level3) Button lvl3Btn;
    @BindView(R.id.level4) Button lvl4Btn;
    @BindView(R.id.level5) Button lvl5Btn;
    @BindView(R.id.level6) Button lvl6Btn;
    @BindView(R.id.level7) Button lvl7Btn;
    @BindView(R.id.level8) Button lvl8Btn;
    @BindView(R.id.level9) Button lvl9Btn;
    @BindView(R.id.level10) Button lvl10Btn;
    @BindView(R.id.level11) Button lvl11Btn;
    @BindView(R.id.level12) Button lvl12Btn;
    @BindView(R.id.level13) Button lvl13Btn;
    @BindView(R.id.level14) Button lvl14Btn;
    @BindView(R.id.level15) Button lvl15Btn;
    @BindView(R.id.level16) Button lvl16Btn;
    @BindView(R.id.level17) Button lvl17Btn;
    @BindView(R.id.level18) Button lvl18Btn;
    @BindView(R.id.level19) Button lvl19Btn;
    @BindView(R.id.level20) Button lvl20Btn;
    @BindView(R.id.level21) Button lvl21Btn;
    @BindView(R.id.level22) Button lvl22Btn;
    @BindView(R.id.level23) Button lvl23Btn;
    @BindView(R.id.level24) Button lvl24Btn;
    @BindView(R.id.level25) Button lvl25Btn;
    @BindView(R.id.level26) Button lvl26Btn;
    @BindView(R.id.level27) Button lvl27Btn;
    @BindView(R.id.level28) Button lvl28Btn;
    @BindView(R.id.level29) Button lvl29Btn;
    @BindView(R.id.level30) Button lvl30Btn;
    @BindView(R.id.level31) Button lvl31Btn;
    @BindView(R.id.level32) Button lvl32Btn;

    
    private ImageView lvl1Stars, lvl2Stars, lvl3Stars,  lvl4Stars, lvl5Stars, lvl6Stars,
            lvl7Stars, lvl8Stars, lvl9Stars, lvl10Stars, lvl11Stars, lvl12Stars, lvl13Stars,
            lvl14Stars, lvl15Stars, lvl16Stars, lvl17Stars, lvl18Stars, lvl19Stars, lvl20Stars, lvl21Stars,
            lvl22Stars, lvl23Stars, lvl24Stars, lvl25Stars, lvl26Stars, lvl27Stars, lvl28Stars, lvl29Stars,
            lvl30Stars, lvl31Stars, lvl32Stars;

    private ScrollView scroll;



    //database stuff
    private DBHandler db;
    private UserTableManager dbManager;
    private int displayedButtons;
    private ArrayList<Button> allButtons;
    private ArrayList<ImageView> StarArray;

    private User currentUser = GlobalApplicationClass.getCurrentUser();

    @Override
    public void onStart(){
        super.onStart();
        db = new DBHandler(getApplicationContext());
        dbManager = new UserTableManager(getApplicationContext());
        scroll.fullScroll(View.FOCUS_DOWN);
        int userCLevel = GlobalApplicationClass.getCurrentUser().getCompletedLevels().size();
        for (int i = 0; i < displayedButtons; i++){
            if( userCLevel-1 < i ) {
                allButtons.get(i).setBackgroundColor(Color.parseColor("#ff0000"));
                //currentUser.getCompletedLevels().get(i-1).getNumStars();
            }
            else {
                allButtons.get(i).setBackgroundColor(Color.parseColor("#66ff66"));
            }
            if (userCLevel == i){
                allButtons.get(i).setBackgroundColor(Color.parseColor("#0000ff"));
            }

        }

        for(int i=0; i<currentUser.getCompletedLevels().size(); i++){
            if(currentUser.getCompletedLevels().get(i).getNumStars() == 3) {
                //set image for 3 stars
                StarArray.get(i).setImageResource(R.drawable.overworld3);
            }
            if(currentUser.getCompletedLevels().get(i).getNumStars() == 2){
                StarArray.get(i).setImageResource(R.drawable.overworld2);
            }
            if(currentUser.getCompletedLevels().get(i).getNumStars() == 1){
                StarArray.get(i).setImageResource(R.drawable.overworld1);
            }
            if(currentUser.getCompletedLevels().get(i).getNumStars() == 0){
                StarArray.get(i).setImageResource(R.drawable.overworld0);
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
                //Toast.makeText(getApplicationContext(),"added level",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"Completed Levels: " + currentUser.getCompletedLevels().size(),Toast.LENGTH_SHORT).show();

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

        ButterKnife.bind(this);

        scroll = (ScrollView) findViewById(R.id.fullscreen_content);
        scroll.fullScroll(View.FOCUS_DOWN);


        lvl1Stars = (ImageView)findViewById(R.id.level1stars);
        lvl2Stars = (ImageView)findViewById(R.id.level2stars);
        lvl3Stars = (ImageView)findViewById(R.id.level3stars);
        lvl4Stars = (ImageView)findViewById(R.id.level4stars);
        lvl5Stars = (ImageView)findViewById(R.id.level5stars);
        lvl6Stars = (ImageView)findViewById(R.id.level6stars);
        lvl7Stars = (ImageView)findViewById(R.id.level7stars);
        lvl8Stars = (ImageView)findViewById(R.id.level8stars);
        lvl9Stars = (ImageView)findViewById(R.id.level9stars);
        lvl10Stars = (ImageView)findViewById(R.id.level10stars);
        lvl11Stars = (ImageView)findViewById(R.id.level11stars);
        lvl12Stars = (ImageView)findViewById(R.id.level12stars);
        lvl13Stars = (ImageView)findViewById(R.id.level13stars);
        lvl14Stars = (ImageView)findViewById(R.id.level14stars);
        lvl15Stars = (ImageView)findViewById(R.id.level15stars);
        lvl16Stars = (ImageView)findViewById(R.id.level16stars);
        lvl17Stars = (ImageView)findViewById(R.id.level17stars);
        lvl18Stars = (ImageView)findViewById(R.id.level18stars);
        lvl19Stars = (ImageView)findViewById(R.id.level19stars);
        lvl20Stars = (ImageView)findViewById(R.id.level20stars);
        lvl21Stars = (ImageView)findViewById(R.id.level21stars);
        lvl22Stars = (ImageView)findViewById(R.id.level22stars);
        lvl23Stars = (ImageView)findViewById(R.id.level23stars);
        lvl24Stars = (ImageView)findViewById(R.id.level24stars);
        lvl25Stars = (ImageView)findViewById(R.id.level25stars);
        lvl26Stars = (ImageView)findViewById(R.id.level26stars);
        lvl27Stars = (ImageView)findViewById(R.id.level27stars);
        lvl28Stars = (ImageView)findViewById(R.id.level28stars);
        lvl29Stars = (ImageView)findViewById(R.id.level29stars);
        lvl30Stars = (ImageView)findViewById(R.id.level30stars);
        lvl31Stars = (ImageView)findViewById(R.id.level31stars);
        lvl32Stars = (ImageView)findViewById(R.id.level32stars);


        allButtons = new ArrayList<Button>();
        allButtons.addAll(Arrays.asList(lvl1Btn,lvl2Btn,lvl3Btn, lvl4Btn, lvl5Btn,lvl6Btn, lvl7Btn,
                                        lvl8Btn, lvl9Btn,lvl10Btn, lvl11Btn, lvl12Btn, lvl13Btn,
                lvl14Btn, lvl15Btn, lvl16Btn, lvl17Btn, lvl18Btn, lvl19Btn, lvl20Btn, lvl21Btn,
                lvl22Btn, lvl23Btn, lvl24Btn, lvl25Btn, lvl26Btn, lvl27Btn, lvl28Btn, lvl29Btn,
                lvl30Btn, lvl31Btn, lvl32Btn));
        displayedButtons = allButtons.size();

        StarArray = new ArrayList<ImageView>();
        StarArray.addAll(Arrays.asList(lvl1Stars, lvl2Stars, lvl3Stars, lvl4Stars, lvl5Stars, lvl6Stars,
                lvl7Stars, lvl8Stars, lvl9Stars, lvl10Stars, lvl11Stars, lvl12Stars, lvl13Stars, lvl14Stars,
                lvl15Stars, lvl16Stars, lvl17Stars, lvl18Stars, lvl19Stars, lvl20Stars, lvl21Stars, lvl22Stars,
                lvl23Stars, lvl24Stars, lvl25Stars, lvl26Stars, lvl27Stars, lvl28Stars, lvl29Stars, lvl30Stars,
                lvl31Stars, lvl32Stars));


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

    }
/**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     *//*
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }*/
}
