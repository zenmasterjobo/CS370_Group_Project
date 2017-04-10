package edu.sonoma.group.peg_master.Database;

import java.util.ArrayList;

import edu.sonoma.group.peg_master.Application_Classes.CompletedLevel;

/**
 * Created by nick on 4/12/16.
 */
public class User {
    private String userName;
    private int userID;
    private int hiScore;
    private int timePlayed;
    private int chestsOpened;
    private int numStars;
    private ArrayList<CompletedLevel> completedLevels;
    private boolean bMusic;

    // constructor for user
    public User(String name){
        userName = name;
        hiScore = 0;
        timePlayed = 0;
        chestsOpened = 0;
        numStars = 0;
        bMusic = true;
        completedLevels = new ArrayList<>();
    }

    //getters and setters!
    public void setName(String name){
        userName = name;
    }
    public String getName(){
        return userName;
    }

    public void setID(int ID){
        userID = ID;
    }
    public int getID(){
        return userID;
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

    public void setMusic(boolean music){bMusic = music;};
    public boolean getMusic(){return bMusic;};

    public void addLevel(CompletedLevel level){
        completedLevels.add(level);
    }
    public ArrayList<CompletedLevel> getCompletedLevels(){
        return completedLevels;
    }
    public void setCompletedLevels(ArrayList<CompletedLevel>cLevels){completedLevels = cLevels;}


}