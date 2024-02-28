package mancala;

public class Player{

    private String playerName;
    private Store playerStore;
    
    public Player(){
        playerName = null;
       // playerStore = new Store();
    }

    public Player(String name){
        playerName = name;
    }

    public String getName(){
        return playerName;
    }

    public Store getStore(){
        return playerStore;
    }

    public int getStoreCount(){
        return playerStore.getTotalStones();
    }

    public void setName(String name){
        playerName = name;
    }

    public void setStore(Store store){
        playerStore = store;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name ='" + playerName + '\'' +
                ", store=" + playerStore +
                '}';
    }

}