package edu.sonoma.group.peg_master;

import java.util.ArrayList;

/**
 * Created by admin on 4/9/16.
 */
public class Board {
    private int [][]board;

    private ArrayList<Chest> b = new ArrayList<Chest>();

    public void generate(int numChests){
        int [] possible=new int[numChests];
        for (int i=0;i <100;++i){
            possible[i]=i;
        }
    }
    public boolean isSolvable(int [][]board){
        int count = 0;

        if (count % 2 == 0)
            return true;
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
