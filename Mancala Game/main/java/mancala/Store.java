package mancala;

public class Store{
    private Player theOwner;
    private int storeTotal;


    public Store(){
        storeTotal = 0;
        theOwner = null;
    }

    public void addStones (int amount){
        storeTotal += amount;
    }

    public int emptyStore(){
        int stonesInStore = storeTotal; 
        storeTotal = 0;
        return stonesInStore; 
    }

    public Player getOwner(){
        return theOwner;
    }

    public int getTotalStones(){
        return storeTotal;
    }

    public void setOwner(Player player){
        theOwner = player;
    }

    @Override
    public String toString(){
        return Integer.toString(storeTotal); 
    }

}
