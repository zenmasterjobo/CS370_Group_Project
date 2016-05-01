package edu.sonoma.group.peg_master;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class levelActivity extends AppCompatActivity {

    private int numChests;
    private int levelNum;
    private GameFactory gf;
    private Board board = new Board();
    private BoardFragment bf;
    private Key kih;
    private Integer num_moves = 0;
    private ArrayList<ArrayList<TextView>> keysOnChest = new ArrayList<ArrayList<TextView>>();

    private TextView keyInHand;
    private TextView moveCounter;

    @Override
    protected void onStart(){
        super.onStart();
        board.generate(numChests);
        kih = new Key(-1);
        setup();
        updateGraphics();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //set numChests from the bundle
        numChests = getIntent().getExtras().getInt("numChests");
        levelNum = getIntent().getExtras().getInt("levelnum");
        String debugmsg = "numChests: " + Integer.toString(numChests);
        Log.d("NumChests", Integer.toString(numChests));
        //Toast.makeText(this.getApplicationContext(),debugmsg,Toast.LENGTH_LONG).show();

        //use numChests here on a class to represent result from algorithm? might have to do
        //on background thread

        //auto generated
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        bf = new BoardFragment();
        ft.replace(R.id.fragment_container, bf, null);
        ft.commit();
    }

    private void setup(){

        this.keyInHand = bf.getKih();
        this.moveCounter = bf.getMoveCounter();

        ArrayList<ImageButton> buttons = bf.getBoardButtonsByNumberOfChests(numChests);
        for (int i = 0; i < buttons.size(); i++){
            ImageButton b = buttons.get(i);
            final int finalI = i;
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Key temp = board.getChestAt(finalI +1).makeMove(kih);
                    kih = new Key(Integer.parseInt(temp.getNumber()));
                    if (board.done()){
                        Toast.makeText(getApplicationContext(), "OMFG UR SO COOL", Toast.LENGTH_LONG).show();
                        //add this completed level to GAC currentUser and update.
                        CompletedLevel cLevel = new CompletedLevel(levelNum,3,numChests);
                        User cUser = GlobalApplicationClass.getCurrentUser();
                        cUser.addLevel(cLevel);
                        GlobalApplicationClass.setCurrentUser(cUser);
                    }
                    num_moves += 1;
                    updateGraphics();
                    Toast.makeText(getApplicationContext(), num_moves.toString(), Toast.LENGTH_LONG).show();

                    moveCounter.setText("Moves: " + Integer.toString(num_moves));
                }
            });
        }
        Log.d("Setup(1)","Finished creating on click listeners");
        ArrayList<TextView> chestNumbers = bf.getChestNumbersByNumberOfChests(numChests);
        Log.d("Setup(1.5)", "The size of the chest number array list is:" + chestNumbers.size());

        ArrayList<TextView> allKeys = bf.getBoardTextViews();
        for (int i = 0; i < allKeys.size(); i++){
            allKeys.get(i).setVisibility(View.INVISIBLE);
        }

        // Go though all the chest numbers and make them invisible
        ArrayList<TextView> allChestNumbers = bf.getChestNumbers();
        for (int i = 0; i < allChestNumbers.size(); i++){
            allChestNumbers.get(i).setVisibility(View.INVISIBLE);
        }

        // Go through the needed chest numbers and make their value corespond to the chest it is
        for (int i = 0; i < chestNumbers.size(); i++){
            TextView t = chestNumbers.get(i);
            t.setText(Integer.toString(i+1));
            t.setVisibility(View.VISIBLE);
        }
        Log.d("Setup(2)", "Finished setting chest numbers");
        this.keysOnChest = bf.getChestKeysByNumberOfChests(numChests);

        // Go through all chest buttons and make them invisible
        ArrayList<ImageButton> allChestButtons = bf.getBoardButtons();
        for (int i = 0; i < allChestButtons.size(); i++){
            allChestButtons.get(i).setVisibility(View.INVISIBLE);
        }
        // Now go through the chest buttons we need for this level and make them visible
        ArrayList<ImageButton> neededChestButtons = bf.getBoardButtonsByNumberOfChests(numChests);
        for (int i = 0; i < neededChestButtons.size(); i++){
            neededChestButtons.get(i).setVisibility(View.VISIBLE);
        }


    }

    private void updateGraphics() {

        TextView keyInHand = bf.getKih();

        if (kih.getNumber().equals("-1")){
            keyInHand.setText(" ");
            keyInHand.setBackgroundColor(Color.parseColor("#10d313"));
        } else {
            keyInHand.setText(kih.getNumber());
            keyInHand.setBackgroundColor(Color.parseColor("#e40e0e"));
        }

        for (int i = 0; i < keysOnChest.size(); i ++){
            if (board.getChestAt(i+1).getLeftKey() != null){
                if (!(board.getChestAt(i+1).getLeftKey().getNumber().equals("-1"))){
                    keysOnChest.get(i).get(0).setText(board.getChestAt(i+1).getLeftKey().getNumber());
                    keysOnChest.get(i).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
                } else {
                    keysOnChest.get(i).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                    keysOnChest.get(i).get(0).setText(" ");
                }
                keysOnChest.get(i).get(0).setVisibility(View.VISIBLE);
                keysOnChest.get(i).get(1).setVisibility(View.INVISIBLE);
            } else if (board.getChestAt(i+1).getRightKey() != null){
                if (!(board.getChestAt(i+1).getRightKey().getNumber().equals("-1"))){
                    keysOnChest.get(i).get(1).setText(board.getChestAt(i+1).getRightKey().getNumber());
                    keysOnChest.get(i).get(1).setBackgroundColor(Color.parseColor("#e40e0e"));
                } else {
                    keysOnChest.get(i).get(1).setBackgroundColor(Color.parseColor("#10d313"));
                    keysOnChest.get(i).get(1).setText(" ");
                }
                keysOnChest.get(i).get(1).setVisibility(View.VISIBLE);
                keysOnChest.get(i).get(0).setVisibility(View.INVISIBLE);
            }
        }
        /* IF THE ABOVE WORKS THE BELOW CAN BE REMOVED
           BE WARY OF A RANDOM CURLY BRACE THAT NEEDS TO REMAIN
        ArrayList<TextView> tVs = bf.getBoardTextViews();

        Map<String, ArrayList<ArrayList<TextView>>> chestKeys = bf.getChestKeysMap();
        ArrayList<ArrayList<TextView>> temp = new ArrayList<ArrayList<TextView>>();
        temp = chestKeys.get("Row2");
        // Chest 1 drawing logic
        if (board.getChestAt(1).getLeftKey() != null) {
            if (!(board.getChestAt(1).getLeftKey().getNumber().equals("-1"))) {
                temp.get(0).get(0).setText(board.getChestAt(1).getLeftKey().getNumber());
                temp.get(0).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(0).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(0).get(0).setText(" ");
            }
            temp.get(0).get(0).setVisibility(View.VISIBLE);
            temp.get(0).get(1).setVisibility(View.INVISIBLE);
        } else if (board.getChestAt(1).getRightKey() != null) {
            if (!(board.getChestAt(1).getRightKey().getNumber().equals("-1"))) {
                temp.get(0).get(1).setText(board.getChestAt(1).getRightKey().getNumber());
                temp.get(0).get(1).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(0).get(1).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(0).get(1).setText(" ");
            }
            temp.get(0).get(1).setVisibility(View.VISIBLE);
            temp.get(0).get(0).setVisibility(View.INVISIBLE);
        }
        // Chest 2 Drawing logic
        if (board.getChestAt(2).getLeftKey() != null) {
            if (!(board.getChestAt(2).getLeftKey().getNumber().equals("-1"))) {
                temp.get(1).get(0).setText(board.getChestAt(2).getLeftKey().getNumber());
                temp.get(1).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(1).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(1).get(0).setText(" ");
            }
            temp.get(1).get(0).setVisibility(View.VISIBLE);
            temp.get(1).get(1).setVisibility(View.INVISIBLE);
        } else if (board.getChestAt(2).getRightKey() != null) {
            if (!(board.getChestAt(2).getRightKey().getNumber().equals("-1"))) {
                temp.get(1).get(1).setText(board.getChestAt(2).getRightKey().getNumber());
                temp.get(1).get(1).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(1).get(1).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(1).get(1).setText(" ");
            }
            temp.get(1).get(1).setVisibility(View.VISIBLE);
            temp.get(1).get(0).setVisibility(View.INVISIBLE);
        }
        // Chest 3 drawing logic
        if (board.getChestAt(3).getLeftKey() != null) {
            if (!(board.getChestAt(3).getLeftKey().getNumber().equals("-1"))) {
                temp.get(2).get(0).setText(board.getChestAt(3).getLeftKey().getNumber());
                temp.get(2).get(0).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(2).get(0).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(2).get(0).setText(" ");
            }
            temp.get(2).get(0).setVisibility(View.VISIBLE);
            temp.get(2).get(1).setVisibility(View.INVISIBLE);
        } else if (board.getChestAt(3).getRightKey() != null) {
            if (!(board.getChestAt(3).getRightKey().getNumber().equals("-1"))) {
                temp.get(2).get(1).setText(board.getChestAt(3).getRightKey().getNumber());
                temp.get(2).get(1).setBackgroundColor(Color.parseColor("#e40e0e"));
            } else {
                temp.get(2).get(1).setBackgroundColor(Color.parseColor("#10d313"));
                temp.get(2).get(1).setText(" ");
            }
            temp.get(2).get(1).setVisibility(View.VISIBLE);
            temp.get(2).get(0).setVisibility(View.INVISIBLE);
        }
        */
    }

        // Index 18 is left slot of chest 2-1

        // e40e0e is red
        // 10d313 is green
        /*
        if (board.getChestAt(1).getLeftKey() != null){
            if (board.getChestAt(1).getLeftKey().getNumber() != "-1") {
                tVs.get(18).setBackgroundColor(Color.parseColor("#e40e0e"));
                tVs.get(18).setText(board.getChestAt(1).getLeftKey().getNumber());
            } else {
                tVs.get(18).setBackgroundColor(Color.parseColor("10d313"));
                tVs.get(18).setText("");
            }
            tVs.get(19).setVisibility(View.INVISIBLE);
        }
        else if (board.getChestAt(1).getRightKey() != null){
            if (board.getChestAt(1).getRightKey().getNumber() != "-1") {
                tVs.get(19).setBackgroundColor(Color.parseColor("#e40e0e"));
                tVs.get(19).setText(board.getChestAt(1).getLeftKey().getNumber());
            } else {
                tVs.get(19).setBackgroundColor(Color.parseColor("10d313"));
                tVs.get(19).setText("");
            }
            tVs.get(18).setVisibility(View.INVISIBLE);
        }

        //tVs.get(19).setText(board.getChestAt(1).getRightKey().getNumber());
        tVs.get(20).setText(board.getChestAt(2).getLeftKey().getNumber());
        tVs.get(20).setBackgroundColor(Color.parseColor("#e40e0e"));
        tVs.get(21).setVisibility(View.INVISIBLE);
        //tVs.get(21).setText(board.getChestAt(2).getRightKey().getNumber());
        tVs.get(22).setText(board.getChestAt(3).getLeftKey().getNumber());
        tVs.get(22).setBackgroundColor(Color.parseColor("#e40e0e"));
        tVs.get(23).setVisibility(View.INVISIBLE);
        //tVs.get(23).setText(board.getChestAt(3).getRightKey().getNumber());

        TextView chest1L = (TextView) bf.getView().findViewById(R.id.chest2_1left);
        TextView chest1R = (TextView) bf.getView().findViewById(R.id.chest2_1right);
        TextView chest2L = (TextView) bf.getView().findViewById(R.id.chest2_2left);
        TextView chest2R = (TextView) bf.getView().findViewById(R.id.chest2_2right);
        TextView chest3L = (TextView) bf.getView().findViewById(R.id.chest2_3left);
        TextView chest3R = (TextView) bf.getView().findViewById(R.id.chest2_3right);
        chest1L.setText(board.getChestAt(1).getLeftKey().getNumber());
        chest1R.setText(board.getChestAt(1).getRightKey().getNumber());
        chest2L.setText(board.getChestAt(2).getLeftKey().getNumber());
        chest2R.setText(board.getChestAt(2).getRightKey().getNumber());
        chest3L.setText(board.getChestAt(3).getLeftKey().getNumber());
        chest3R.setText(board.getChestAt(3).getRightKey().getNumber());
        */
}
