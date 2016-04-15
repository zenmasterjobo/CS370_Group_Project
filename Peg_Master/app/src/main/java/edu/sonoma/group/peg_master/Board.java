package edu.sonoma.group.peg_master;

/**
 * Created by admin on 4/9/16.
 */
public class Board {
    private int [][]board;

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
}
