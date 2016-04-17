package edu.sonoma.group.peg_master;

import java.util.ArrayList;

/**
 * Created by admin on 4/13/16.
 */
public class GameFactory {

    private ArrayList<Chest> chests;

    // This will set the private variable of chests
    // to the amount of chests needed for a given level
    // Every 5 levels a new chest will be added
    // FIXME i dont think this math is correct but it might be B)
    public GameFactory(int level){
        Integer numChests = 3;
        for (int i = 1; i <= level; i++){
            if (level-i*5 > 0){
                numChests += 1;
                level -= i*5;
            }
        }
    }

    public ArrayList<Chest> getBoard(){
        return chests;
    }

}
