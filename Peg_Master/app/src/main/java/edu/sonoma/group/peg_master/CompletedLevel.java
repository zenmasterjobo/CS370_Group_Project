package edu.sonoma.group.peg_master;

/**
 * Created by student on 5/1/16.
 */
public class CompletedLevel {

    private int LID;
    private int numStars;
    private int numChests;


    public CompletedLevel(int id,int stars,int chests){
        LID = id;
        numStars =stars;
        numChests =chests;
    }

    public void setLID(int ID){
        LID = ID;
    }
    public int getLID(){
        return LID;
    }

    public void setNumStars(int stars){
        numStars = stars;
    }
    public int getNumStars(){
        return numStars;
    }

    public void setNumChests(int chests){
        numChests = chests;

    }

    public int getNumChests(){
        return numChests;
    }

}
