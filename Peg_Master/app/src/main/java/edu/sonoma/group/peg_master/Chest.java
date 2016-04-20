package edu.sonoma.group.peg_master;



/**
 * Created by admin on 4/13/16.
 */
public class Chest {
    private Key leftKey = null;
    private Key rightKey = null;

    // Returns the left key
    public Key getLeftKey(){
        return leftKey;
    }

    // Returns the right key
    public Key getRightKey(){
        return rightKey;
    }

    // Sets the left key
    public void setLeftKey(Key k){
        leftKey = k;
    }

    // Sets the right key
    public void setRightKey(Key k){
        rightKey = k;
    }

    // Returns the location of the chest that does not have a key
    // 0 is left location
    // 1 is right location
    public Integer availableLocation(){
        if (leftKey == null){
            return 0;
        }
        else {
            return 1;
        }
    }

    // This function will make a move into a given chest
    // It will check which location is currently available in the chest
    // and put the key that is in your hand into the available location
    // it will then return the new key in your hand from the chest.
    public Key makeMove(Key keyInHand){
        Key newKeyInHand = null;
        if (leftKey == null){
            newKeyInHand = rightKey;
            leftKey = keyInHand;
            rightKey = null;
        }
        else if (rightKey == null){
            newKeyInHand = leftKey;
            rightKey = keyInHand;
            leftKey = null;
        }
        return newKeyInHand;
    }

    public void createEmptyChest(){
        Key lKey = new Key(0);
        Key rKey = new Key(0);
        this.leftKey = lKey;
        this.rightKey = rKey;
    }
}
