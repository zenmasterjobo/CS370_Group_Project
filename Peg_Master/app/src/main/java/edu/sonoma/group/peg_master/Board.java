package edu.sonoma.group.peg_master;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by admin on 4/9/16.
 */
public class Board {
    private int [][]board;

    private ArrayList<Chest> b = new ArrayList<Chest>();

    public void generate(int numChests){
        ArrayList<Integer> possible = new ArrayList<Integer>();
        for (int i = 0; i < numChests; i++){
            possible.add(i + 1);
        }
        Log.d("Possible array", possible.toString());

        long seed = System.nanoTime();
        Collections.shuffle(possible, new Random(seed));
        Log.d("Possible shuffled array", possible.toString());

        ArrayList<Chest> temp_board = new ArrayList<Chest>();
        for (int i = 0; i < numChests; i++){
            Chest c = new Chest();
            Key k = new Key(possible.get(i));
            long l = System.nanoTime();
            boolean left = new Random(l).nextBoolean();
            if (left){
                c.setLeftKey(k);
            }
            else{
                c.setRightKey(k);
            }
            temp_board.add(c);
        }
        Log.d("A starting board", temp_board.toString());
        setBoard(temp_board);

    }
    public boolean isSolvable(int [][]board){
        int count = 0;

        if (count % 2 == 0)
            return true;
        else
            return true;
    }

    public void setBoard(ArrayList<Chest> _b){
        this.b = _b;
    }

    public Chest getChestAt(int chest){
        return b.get(chest-1);
    }

    // Checks if the game has been completed
    // Returns true if it is, false if it is not
    public Boolean done(){
        for (int i = 0; i < b.size(); i++){
            if ( (b.get(i).getLeftKey() != null) && !(b.get(i).getLeftKey().getNumber().equals(Integer.toString(i+1))) ){
                return false;
            }
        }
        return true;
    }

}
