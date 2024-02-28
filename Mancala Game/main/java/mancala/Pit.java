package mancala;

public class Pit{
    private int stoneNum;

    public Pit(){
        stoneNum = 0; // would I made this 4?? I want each pit to have 4 stones in the start 
    }

    public void addStone(){
       stoneNum++;
    }

    public int getStoneCount(){
        return stoneNum;
    }

    public int removeStones(){ 
        int removedStones = stoneNum;
        stoneNum = 0;
        return removedStones; 
    }

    @Override
    public String toString() { 
        return Integer.toString(stoneNum);
    }

}