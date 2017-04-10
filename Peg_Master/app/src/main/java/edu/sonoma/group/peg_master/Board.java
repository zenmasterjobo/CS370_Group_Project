package edu.sonoma.group.peg_master;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by admin on 4/9/16.
 */
public class Board {
    private int minMoves;
    private int medMoves;
    private int maxMoves;
    private ArrayList<Chest> b = new ArrayList<Chest>();
    private Integer numChests;
    private int boardScore;

    public Board(){
        this.b = new ArrayList<Chest>();
        this.numChests = 0;
    }

    public ArrayList<Integer> getScoreRanking(){
        ArrayList <Integer> ret = new ArrayList<Integer>();
        ret.addAll(Arrays.asList(this.minMoves, this.medMoves, this.maxMoves));
        Log.d("Array of score ranks", ret.toString());
        return ret;
    }

    public Integer getBoardScore(){
        return this.boardScore;
    }

    public void generate(int numChests){
        this.numChests = numChests;
        this.minMoves = numChests * 2;
        this.medMoves = numChests * 3;
        this.maxMoves = numChests * 4;
        ArrayList<Integer> possible = new ArrayList<Integer>();
        for (int i = 0; i < numChests; i++){
            possible.add(i + 1);
        }
        Log.d("Possible array", possible.toString());

        ArrayList<Chest> temp_board = new ArrayList<Chest>();
        for (int i = 0; i < numChests; i++){
            Chest c = new Chest();
            Key k = new Key(possible.get(i));
            c.setLeftKey(k);
            temp_board.add(c);
        }
        setBoard(temp_board);

        Log.d("Board before generation", b.toString());

        Key keyInHand = new Key(-1);
        for (int j = 0; j < 10; j++){
            long l = System.nanoTime();
            int chest = new Random(l).nextInt(numChests);
            Key k = getChestAt(chest+1).makeMove(keyInHand);
            //keyInHand = null;
            keyInHand = new Key(Integer.parseInt(k.getNumber()));
            //k = null;
        }
        // Hack way around never having a green key on the field
        if (! (keyInHand.getNumber().equals("-1"))){
            for (int h = 0; h < numChests; h++){
                if ( getChestAt(h+1).getLeftKey() != null && getChestAt(h+1).getLeftKey().getNumber().equals("-1")){
                    Key k = getChestAt(h+1).makeMove(keyInHand);
                    break;
                }
                else if (getChestAt(h+1).getRightKey() != null && getChestAt(h+1).getRightKey().getNumber().equals("-1")){
                    Key k = getChestAt(h+1).makeMove(keyInHand);
                    break;
                }
            }
        }
        Log.d("Board after generation", b.toString());

        boardScore = 0;

        for (int j = 0; j < numChests; j++){
            if ( getChestAt(j+1).getLeftKey() == null){
                boardScore += 1;
            }
            else if ( !( getChestAt(j+1).getLeftKey().getNumber().equals(Integer.toString(j+1)) ) ) {
                    boardScore += 1;
            }
        }
        Log.d("Board Value is:", Integer.toString(boardScore));

    }
    public boolean isSolvable(int [][]board){
        int count = 0;
        for (int i = 0; i < numChests; i++){
        }

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
            if ( (b.get(i).getLeftKey() == null) || !(b.get(i).getLeftKey().getNumber().equals(Integer.toString(i+1))) ){
                return false;
            }
        }
        return true;
    }

}
