package edu.sonoma.group.peg_master;

/**
 * Created by nick on 4/12/16.
 */
public class User {
    private String userName;
    private int hiScore;
    private int timePlayed;
    private int chestsOpened;
    private int numStars;

    // constructor for user
    public User(){
        userName = "";
        hiScore = 0;
        timePlayed = 0;
    }

    //getters and setters!
    public void setName(String name){
        userName = name;
    }
    public String getName(){
        return userName;
    }

    public void setScore(int hiscore){
        hiScore= hiscore;
    }
    public int getScore(){
        return hiScore;
    }

    public void setPlayed(int time){
        timePlayed = time;
    }
    public int getPlayed(){
        return timePlayed;
    }

    public void setChestsOpened(int chests){chestsOpened = chests;}
    public int getChestsOpened(){return chestsOpened;}

    public void setNumStars(int stars){numStars = stars;}
    public int getNumStars(){return numStars;}
}