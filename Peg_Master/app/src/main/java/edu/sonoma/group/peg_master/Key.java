package edu.sonoma.group.peg_master;

/**
 * Created by student on 3/6/16.
 */
public class Key {
    private String Color;
    private Integer Key_Number;

    public void setNumber(int n){
        Key_Number = n;
    }

    public Key(int number){
        Key_Number = number;
    }

    public String getNumber(){
        return Key_Number.toString();
    }
    public Integer getNumberAsInt(){
        return Key_Number;
    }
}
