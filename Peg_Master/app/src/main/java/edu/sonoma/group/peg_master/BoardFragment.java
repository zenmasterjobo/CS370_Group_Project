package edu.sonoma.group.peg_master;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 3/24/16.
 */
public class BoardFragment extends Fragment {

    private ImageButton chest1_1, chest1_2, chest1_3, chest2_1, chest2_2, chest2_3, chest3_1, chest3_2, chest3_3, chest4_1, chest4_2, chest4_3;
    private TextView chest1_1number, chest1_2number, chest1_3number, chest2_1number, chest2_2number, chest2_3number, chest3_1number, chest3_2number, chest3_3number, chest4_1number, chest4_2number, chest4_3number,
            chest1_1left, chest1_1right, chest1_2left, chest1_2right, chest1_3left, chest1_3right,
            chest2_1left, chest2_1right, chest2_2left, chest2_2right, chest2_3left, chest2_3right,
            chest3_1left, chest3_1right, chest3_2left, chest3_2right, chest3_3left, chest3_3right,
            chest4_1left, chest4_1right, chest4_2left, chest4_2right, chest4_3left, chest4_3right,
            moveCounter, kih, levelCounter;

    private ArrayList<TextView> boardTextViews = new ArrayList<TextView>();
    private ArrayList<ImageButton> boardButtons = new ArrayList<ImageButton>();
    private ArrayList<TextView> chestNumbers = new ArrayList<TextView>();

    private Map _boardButtons = new HashMap();
    private Map _chestNumbers = new HashMap();
    private Map _chestKeys = new HashMap();

    public BoardFragment(){
        //boardTextViews = null;
        //boardButtons = null;
        //chestNumbers = null;
        //_boardButtons = null;
        //_chestKeys = null;
        //_chestNumbers = null;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Will return the board buttons as a dictionary in the format of
    // "Row#" : <Arraylist of the 3 buttons>
    public Map<String,ArrayList<ImageButton>> getBoardButtonMap(){
        Log.d("Board Button Map", _boardButtons.toString());
        return _boardButtons;
    }

    public TextView getLevelCounter(){
        return levelCounter;
    }

    public TextView getMoveCounter(){
        return moveCounter;
    }

    public TextView getKih(){
        return kih;
    }

    public Map<String, ArrayList<TextView>> getChestNumberMap(){
        Log.d("_chestNumbers Map", _boardButtons.toString());
        return _chestNumbers;
    }

    public ArrayList<TextView> getChestNumbers(){
        return chestNumbers;
    }

    public Map<String, ArrayList<ArrayList<TextView>>> getChestKeysMap(){
        Log.d("Chest Keys Map", _chestKeys.toString());
        return _chestKeys;
    }

    public ArrayList<ImageButton> getBoardButtons(){
        Log.d("boardButtons in func", "The length is: " + boardButtons.size());
        return boardButtons;
    }

    public ArrayList<TextView> getBoardTextViews(){
        Log.d("boardTextViews in func", "The length is: " + boardTextViews.size());
        return boardTextViews;
    }

    public ArrayList<ImageButton> getBoardButtonsByNumberOfChests(int num_chests){
        ArrayList<ImageButton> temp = new ArrayList<ImageButton>();
        Map <String, ArrayList<ImageButton>> buttonMap = getBoardButtonMap();
        if (num_chests == 3){
            temp = buttonMap.get("Row2");
        }
        else if (num_chests == 4){
            temp.add(buttonMap.get("Row1").get(1));
            temp.add(buttonMap.get("Row2").get(0));
            temp.add(buttonMap.get("Row2").get(2));
            temp.add(buttonMap.get("Row3").get(1));
        }
        else if (num_chests == 5){
            temp.add(buttonMap.get("Row1").get(1));
            temp.add(buttonMap.get("Row2").get(0));
            temp.add(buttonMap.get("Row2").get(2));
            temp.add(buttonMap.get("Row3").get(1));

            temp.add(buttonMap.get("Row2").get(1));
        }
        return temp;
    }

    public ArrayList<TextView> getChestNumbersByNumberOfChests(int num_chests){
        ArrayList<TextView> ret = new ArrayList<TextView>();
        Map <String, ArrayList<TextView>> chestNumberMap = getChestNumberMap();
        if (num_chests == 3){
            ret = chestNumberMap.get("Row2");
        }
        else if (num_chests == 4){
            ret.add(chestNumberMap.get("Row1").get(1));
            ret.add(chestNumberMap.get("Row2").get(0));
            ret.add(chestNumberMap.get("Row2").get(2));
            ret.add(chestNumberMap.get("Row3").get(1));
        }
        else if (num_chests == 5){
            ret.add(chestNumberMap.get("Row1").get(1));
            ret.add(chestNumberMap.get("Row2").get(0));
            ret.add(chestNumberMap.get("Row2").get(2));
            ret.add(chestNumberMap.get("Row3").get(1));
            // Adding middle chest for 5
            ret.add(chestNumberMap.get("Row2").get(1));
        }
        return ret;
    }

    public ArrayList<ArrayList<TextView>> getChestKeysByNumberOfChests(int num_chests){
        ArrayList<ArrayList<TextView>> ret = new ArrayList<ArrayList<TextView>>();
        Map<String, ArrayList<ArrayList<TextView>>> keyMap = getChestKeysMap();
        if (num_chests == 3){
            ret = keyMap.get("Row2");
        }
        else if (num_chests == 4){
            // Make a new chest
            ArrayList<TextView> chest = new ArrayList<TextView>();
            // Add the left and right key holders from row 1 chest 1 (middle)
            chest.add(keyMap.get("Row1").get(1).get(0));
            chest.add(keyMap.get("Row1").get(1).get(1));
            // Add chest to what we are going to return
            ret.add(chest);

            chest = new ArrayList<TextView>();
            chest.add(keyMap.get("Row2").get(0).get(0));
            chest.add(keyMap.get("Row2").get(0).get(1));
            ret.add(chest);

            chest = new ArrayList<TextView>();
            chest.add(keyMap.get("Row2").get(2).get(0));
            chest.add(keyMap.get("Row2").get(2).get(1));
            ret.add(chest);

            chest = new ArrayList<TextView>();
            chest.add(keyMap.get("Row3").get(1).get(0));
            chest.add(keyMap.get("Row3").get(1).get(1));
            ret.add(chest);

        }
        else if (num_chests == 5){
            // Make a new chest
            ArrayList<TextView> chest = new ArrayList<TextView>();
            // Add the left and right key holders from row 1 chest 1 (middle)
            chest.add(keyMap.get("Row1").get(1).get(0));
            chest.add(keyMap.get("Row1").get(1).get(1));
            // Add chest to what we are going to return
            ret.add(chest);

            chest = new ArrayList<TextView>();
            chest.add(keyMap.get("Row2").get(0).get(0));
            chest.add(keyMap.get("Row2").get(0).get(1));
            ret.add(chest);

            chest = new ArrayList<TextView>();
            chest.add(keyMap.get("Row2").get(2).get(0));
            chest.add(keyMap.get("Row2").get(2).get(1));
            ret.add(chest);

            chest = new ArrayList<TextView>();
            chest.add(keyMap.get("Row3").get(1).get(0));
            chest.add(keyMap.get("Row3").get(1).get(1));
            ret.add(chest);

            chest = new ArrayList<TextView>();
            chest.add(keyMap.get("Row2").get(1).get(0));
            chest.add(keyMap.get("Row2").get(1).get(1));
            ret.add(chest);

        }
        return ret;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        //back = (Button) getView().findViewById(R.id.back);
        //back.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        MainMenuFragment newFrag = new MainMenuFragment();
        //        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //        transaction.replace(R.id.fragment_container, newFrag);
        //        transaction.addToBackStack(null);
        //        transaction.commit();
        //    }
        //});
        View rootView = inflater.inflate(R.layout.fragment_board, container, false);

        chest1_1 = (ImageButton) rootView.findViewById(R.id.chest1_1);
        chest1_2 = (ImageButton) rootView.findViewById(R.id.chest1_2);
        chest1_3 = (ImageButton) rootView.findViewById(R.id.chest1_3);
        chest2_1 = (ImageButton) rootView.findViewById(R.id.chest2_1);
        chest2_2 = (ImageButton) rootView.findViewById(R.id.chest2_2);
        chest2_3 = (ImageButton) rootView.findViewById(R.id.chest2_3);
        chest3_1 = (ImageButton) rootView.findViewById(R.id.chest3_1);
        chest3_2 = (ImageButton) rootView.findViewById(R.id.chest3_2);
        chest3_3 = (ImageButton) rootView.findViewById(R.id.chest3_3);
        chest4_1 = (ImageButton) rootView.findViewById(R.id.chest4_1);
        chest4_2 = (ImageButton) rootView.findViewById(R.id.chest4_2);
        chest4_3 = (ImageButton) rootView.findViewById(R.id.chest4_3);

        chest1_1number = (TextView) rootView.findViewById(R.id.chest1_1number);
        chest1_2number = (TextView) rootView.findViewById(R.id.chest1_2number);
        chest1_3number = (TextView) rootView.findViewById(R.id.chest1_3number);
        chest2_1number = (TextView) rootView.findViewById(R.id.chest2_1number);
        chest2_2number = (TextView) rootView.findViewById(R.id.chest2_2number);
        chest2_3number = (TextView) rootView.findViewById(R.id.chest2_3number);
        chest3_1number = (TextView) rootView.findViewById(R.id.chest3_1number);
        chest3_2number = (TextView) rootView.findViewById(R.id.chest3_2number);
        chest3_3number = (TextView) rootView.findViewById(R.id.chest3_3number);
        chest4_1number = (TextView) rootView.findViewById(R.id.chest4_1number);
        chest4_2number = (TextView) rootView.findViewById(R.id.chest4_2number);
        chest4_3number = (TextView) rootView.findViewById(R.id.chest4_3number);

        chest1_1left = (TextView) rootView.findViewById(R.id.chest1_1left);
        chest1_2left = (TextView) rootView.findViewById(R.id.chest1_2left);
        chest1_3left = (TextView) rootView.findViewById(R.id.chest1_3left);
        chest2_1left = (TextView) rootView.findViewById(R.id.chest2_1left);
        chest2_2left = (TextView) rootView.findViewById(R.id.chest2_2left);
        chest2_3left = (TextView) rootView.findViewById(R.id.chest2_3left);
        chest3_1left = (TextView) rootView.findViewById(R.id.chest3_1left);
        chest3_2left = (TextView) rootView.findViewById(R.id.chest3_2left);
        chest3_3left = (TextView) rootView.findViewById(R.id.chest3_3left);
        chest4_1left = (TextView) rootView.findViewById(R.id.chest4_1left);
        chest4_2left = (TextView) rootView.findViewById(R.id.chest4_2left);
        chest4_3left = (TextView) rootView.findViewById(R.id.chest4_3left);

        chest1_1right = (TextView) rootView.findViewById(R.id.chest1_1right);
        chest1_2right = (TextView) rootView.findViewById(R.id.chest1_2right);
        chest1_3right = (TextView) rootView.findViewById(R.id.chest1_3right);
        chest2_1right = (TextView) rootView.findViewById(R.id.chest2_1right);
        chest2_2right = (TextView) rootView.findViewById(R.id.chest2_2right);
        chest2_3right = (TextView) rootView.findViewById(R.id.chest2_3right);
        chest3_1right = (TextView) rootView.findViewById(R.id.chest3_1right);
        chest3_2right = (TextView) rootView.findViewById(R.id.chest3_2right);
        chest3_3right = (TextView) rootView.findViewById(R.id.chest3_3right);
        chest4_1right = (TextView) rootView.findViewById(R.id.chest4_1right);
        chest4_2right = (TextView) rootView.findViewById(R.id.chest4_2right);
        chest4_3right = (TextView) rootView.findViewById(R.id.chest4_3right);

        kih = (TextView) rootView.findViewById(R.id.keyInHand);
        moveCounter = (TextView) rootView.findViewById(R.id.moveCounter);
        levelCounter = (TextView) rootView.findViewById(R.id.levelCounter);

        chest1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        chest1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest2_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest3_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest4_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        chest4_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        boardTextViews.addAll(Arrays.asList(chest1_1number, chest1_2number, chest1_3number, chest2_1number, chest2_2number, chest2_3number, chest3_1number, chest3_2number, chest3_3number, chest4_1number, chest4_2number, chest4_3number,
                chest1_1left, chest1_1right, chest1_2left, chest1_2right, chest1_3left, chest1_3right,
                chest2_1left, chest2_1right, chest2_2left, chest2_2right, chest2_3left, chest2_3right,
                chest3_1left, chest3_1right, chest3_2left, chest3_2right, chest3_3left, chest3_3right,
                chest4_1left, chest4_1right, chest4_2left, chest4_2right, chest4_3left, chest4_3right));
        boardButtons.addAll(Arrays.asList(chest1_1, chest1_2, chest1_3, chest2_1, chest2_2, chest2_3, chest3_1,
                chest3_2, chest3_3, chest4_1, chest4_2, chest4_3));
        Log.d("boardTextViews", "The length is: " + boardTextViews.size());
        Log.d("boardButtons", "The length is: " + boardButtons.size());

         // Setting up data structures to store the row buttons
        ArrayList<ImageButton> _row1Buttons = new ArrayList<ImageButton>();
        ArrayList<ImageButton> _row2Buttons = new ArrayList<ImageButton>();
        ArrayList<ImageButton> _row3Buttons = new ArrayList<ImageButton>();

        _row1Buttons.addAll(Arrays.asList(chest1_1, chest1_2, chest1_3));
        _row2Buttons.addAll(Arrays.asList(chest2_1, chest2_2, chest2_3));
        _row3Buttons.addAll(Arrays.asList(chest3_1, chest3_2, chest3_3));

        _boardButtons.put("Row1", _row1Buttons);
        _boardButtons.put("Row2", _row2Buttons);
        _boardButtons.put("Row3", _row3Buttons );
        // The board buttons are now stored in a map with rows 1-3 having a array list
        // of 3 elements one button for each chest


        // Setting up data structures to store the chest numbers
        ArrayList<TextView> _row1ChestNumbers = new ArrayList<TextView>();
        ArrayList<TextView> _row2ChestNumbers = new ArrayList<TextView>();
        ArrayList<TextView> _row3ChestNumbers = new ArrayList<TextView>();

        _row1ChestNumbers.addAll(Arrays.asList(chest1_1number, chest1_2number, chest1_3number));
        _row2ChestNumbers.addAll(Arrays.asList(chest2_1number, chest2_2number, chest2_3number));
        _row3ChestNumbers.addAll(Arrays.asList(chest3_1number, chest3_2number, chest3_3number));

        _chestNumbers.put("Row1", _row1ChestNumbers);
        _chestNumbers.put("Row2", _row2ChestNumbers);
        _chestNumbers.put("Row3", _row3ChestNumbers);
        // The chest numbers are now stored in a map with rows 1-3 having a array list
        // of 3 elements one button for each chest

        // Setting up data structures to store the chest key holders
        ArrayList<ArrayList<TextView>> _row1ChestKeys = new ArrayList<ArrayList<TextView>>();
        ArrayList<ArrayList<TextView>> _row2ChestKeys = new ArrayList<ArrayList<TextView>>();
        ArrayList<ArrayList<TextView>> _row3ChestKeys = new ArrayList<ArrayList<TextView>>();
        // 2d array, this way I can access a specific chest in a row which will contain
        // a left keyholder and a right keyholder

        ArrayList<TextView> _chest1Keys = new ArrayList<TextView>();
        ArrayList<TextView> _chest2Keys = new ArrayList<TextView>();
        ArrayList<TextView> _chest3Keys = new ArrayList<TextView>();

        _chest1Keys.addAll(Arrays.asList(chest2_1left, chest2_1right));
        _chest2Keys.addAll(Arrays.asList(chest2_2left, chest2_2right));
        _chest3Keys.addAll(Arrays.asList(chest2_3left, chest2_3right));

        _row2ChestKeys.addAll(Arrays.asList(_chest1Keys, _chest2Keys, _chest3Keys));

        _chestKeys.put("Row2", _row2ChestKeys);

        _chest1Keys = new ArrayList<TextView>();
        _chest2Keys = new ArrayList<TextView>();
        _chest3Keys = new ArrayList<TextView>();

        _chest1Keys.addAll(Arrays.asList(chest1_1left, chest1_1right));
        _chest2Keys.addAll(Arrays.asList(chest1_2left, chest1_2right));
        _chest3Keys.addAll(Arrays.asList(chest1_3left, chest1_3right));

        _row1ChestKeys.addAll(Arrays.asList(_chest1Keys, _chest2Keys, _chest3Keys));

        _chestKeys.put("Row1", _row1ChestKeys);

        _chest1Keys = new ArrayList<TextView>();
        _chest2Keys = new ArrayList<TextView>();
        _chest3Keys = new ArrayList<TextView>();

        _chest1Keys.addAll(Arrays.asList(chest3_1left, chest3_1right));
        _chest2Keys.addAll(Arrays.asList(chest3_2left, chest3_2right));
        _chest3Keys.addAll(Arrays.asList(chest3_3left, chest3_3right));

        _row3ChestKeys.addAll(Arrays.asList(_chest1Keys, _chest2Keys, _chest3Keys));

        _chestKeys.put("Row3", _row3ChestKeys);

        ArrayList<TextView> allChestKeyHolders = new ArrayList<TextView>();
        allChestKeyHolders.addAll(Arrays.asList(chest1_1left, chest1_1right, chest1_2left, chest1_2right, chest1_3left, chest1_3right,
                chest2_1left, chest2_1right, chest2_2left, chest2_2right, chest2_3left, chest2_3right,
                chest3_1left, chest3_1right, chest3_2left, chest3_2right, chest3_3left, chest3_3right,
                chest4_1left, chest4_1right, chest4_2left, chest4_2right, chest4_3left, chest4_3right));
        // Now can loop through all key holders and add every pair into a temp

        chestNumbers.addAll(Arrays.asList(chest1_1number, chest1_2number, chest1_3number,
                chest2_1number, chest2_2number, chest2_3number, chest3_1number, chest3_2number,
                chest3_3number, chest4_1number, chest4_2number, chest4_3number));

        /*
        // Counters to find when to fill a chest, and a row
        int keyHolderCounter = 1;
        int chestCounter = 1;
        ArrayList<TextView> temp_chest = new ArrayList<TextView>();
        ArrayList<ArrayList<TextView>> temp_row = new ArrayList<ArrayList<TextView>>();
        // Iterate through the the key holders and every 2 add one to a chest
        // When we have 3 chests we add them into a row
        Iterator<TextView> keyHolder = allChestKeyHolders.iterator();
        while (keyHolder.hasNext()){
            // we add the current key to our temp chest
            temp_chest.add(keyHolder.next());
            // if we have 2 keys in a chest we want to add it to a row
            if (keyHolderCounter % 2 == 0){
                temp_row.add(temp_chest);
                // reset the temp chest
                temp_chest = new ArrayList<TextView>();
                // if we have 3 chests in a row we want to add these chests to a row
                // in our map
                if (chestCounter % 3 == 0) {
                    _chestKeys.put("Row"+Integer.toString(chestCounter), temp_row);
                    temp_row = new ArrayList<ArrayList<TextView>>();
                }
                chestCounter++;
            }
            keyHolderCounter++;
        }
        // _chestKeys should now contain a map which contains 3 keys row 1-3
        // Each row has a 2d array, the first index is for which chest
        // the second is the left or the right key
        */
        return rootView;
    }
}
