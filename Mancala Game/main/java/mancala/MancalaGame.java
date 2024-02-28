package mancala;
import java.util.ArrayList;

public class MancalaGame{

        private Board board = new Board();
        private Player currentPlayer = new Player();
        private ArrayList<Player> players = new ArrayList<Player>();
        private Player playerOne; 
        private Player playerTwo; 
        
    public MancalaGame(){ 
        board = new Board(); // Initialize the game board
        players = new ArrayList<>(); // Create a list for player
    }
/** 
    public void testPrint(){

      int value =0;
      System.out.println("Move 1----------------------------------------------------");
        value = board.moveStones(1,players.get(0)); // was oringall 1 
        System.out.println(board.toString());

      System.out.println("Move 2----------------------------------------------------");

        value = board.moveStones(12,players.get(1));
        System.out.println(board.toString());

      System.out.println("Move 3----------------------------------------------------");

        value = board.moveStones(2,players.get(0));
        System.out.println(board.toString());

      System.out.println("Move 4----------------------------------------------------");
        value = board.moveStones(11,players.get(1));
        System.out.println(board.toString());

      System.out.println("Move 5----------------------------------------------------");
        value = board.moveStones(12,players.get(1));
        System.out.println(board.toString());
    
     System.out.println("Move 6----------------------------------------------------");
        value = board.moveStones(7,players.get(1));
        System.out.println(board.toString()); // player 2 capture
/** 
    System.out.println("Move 7----------------------------------------------------");
        value = board.moveStones(11,players.get(1));
        System.out.println(board.toString()); // player 2 capture



    
     /**    value = board.moveStones(1,players.get(0));
        System.out.println(board.toString());
    
        value = board.moveStones(12,players.get(1));
        System.out.println(board.toString());

        value = board.moveStones(2,players.get(0));
        System.out.println(board.toString());

        value = board.moveStones(9,players.get(1)); // player 1 capture
        System.out.println(board.toString());

        value = board.moveStones(1,players.get(0));
        System.out.println(board.toString());
      

    /** 
        try{
            boolean isIt;
            isIt = board.isSideEmpty(19);
            System.out.println(" The value of isIt is : " + isIt);
        }catch(PitNotFoundException e){
           System.out.println("Caught exception in isSideEmpty: " + e.getMessage());
        }

        try{
            board.distributeStones(200); // this check only works if move stones in board class is commented out
        }catch (PitNotFoundException ex){
             System.out.println("Caught exception in distrubute stones: " + ex.getMessage());

        }
    **/
      // System.out.println(" this value of stones put in stores "+ value );
       //newBoard.distributeStones(0);
       //newBoard.distributeStones(5);
   // }

    public Board getBoard(){
        return board;
    }

    public Player getCurrentPlayer(){ 
        return currentPlayer;
    }

    public int getNumStones(int pitNum) throws PitNotFoundException{
        if(pitNum > 12 || pitNum < 1){
            throw new PitNotFoundException("Invalid Pit Number");
        }
        return board.getNumStones(pitNum);
    }

    public ArrayList<Player> getPlayers(){
        return players; // list of players
    }

    public int getStoreCount(Player player) throws NoSuchPlayerException{
        if(!player.getName().equals(player)){
            throw new NoSuchPlayerException("The player does not exist");
        }
       return player.getStoreCount();
       //return player.getStore().getTotalStones();
    }

    public Player getWinner() throws GameNotOverException{
        //System.out.println( "This is player 1 store:" + players.get(0).getStoreCount());
       // System.out.println( "This is player 2 store:" + players.get(1).getStoreCount());
      if (isGameOver()) { // Check if the game is over   //ChatGPT Start
            int player1Count = players.get(0).getStoreCount();
            int player2Count = players.get(1).getStoreCount();
            if (player1Count > player2Count) { // Check for the winner based on store counts
                return players.get(0);
            } else if (player2Count > player1Count) {
                return players.get(1);
            }
        }
        else{
            throw new GameNotOverException("The game is not over!");
        }
        return null; // Return null if there's no winner yet //ChatGPT End  
    }

    public boolean isGameOver(){
        try{
        if (board.isSideEmpty(2) || board.isSideEmpty(7)) {
            return true; // The game is over
        } 
        return false; // The game is not over
        }catch(PitNotFoundException ex){
            ex.getMessage();
            return false;
        }
    }

    public int move(int startPit) throws InvalidMoveException{  
        int added = 0;
        if(currentPlayer == playerOne && startPit<7){
            try{
                board.moveStones(startPit,players.get(0));
                for(int i= 0; i<6; i++){
                    try{
                    added += board.getNumStones(i);
                    //System.out.println("Added for each interatio: " +added);
                    }catch (PitNotFoundException ex){
                        throw new InvalidMoveException(ex.getMessage());
                    }   
                }
            }catch(InvalidMoveException ex){
                throw new InvalidMoveException(ex.getMessage());
            }
        }
        if(currentPlayer == playerTwo && startPit>6){
            try{
                board.moveStones(startPit,players.get(1));
                    for(int i= 6; i<12; i++){
                        try{
                            added += board.getNumStones(i);
                        }catch (PitNotFoundException ex){
                            throw new InvalidMoveException(ex.getMessage());
                        }
                    }
            }catch(InvalidMoveException ex){
                throw new InvalidMoveException(ex.getMessage());
            }
        }
        if(currentPlayer == playerOne && startPit>6 || currentPlayer == playerTwo && startPit<7){
           throw new InvalidMoveException("Invalid Move. Try a pit on your side");
        } // would i still need this?? 
        //System.out.println("This is how many stones are left on your side " + added);
        return added;
    }

    public void setFreeTurnM(boolean flag){
        board.setFreeTurn(flag); // setting the free turn flag
    }

    public boolean getfreeTurnM(){
        return board.getfreeTurn();
    }

    public void setBoard(Board theBoard){
        board.initializeBoard();
    }

    public void setCurrentPlayer(Player player){ // i 
        currentPlayer = player; // sets currentPlayer
    }

    public void setPlayers(Player onePlayer, Player twoPlayer){ // i
        playerOne = onePlayer;
        playerTwo = twoPlayer;
        players.add(playerOne);
        players.add(playerTwo);
        currentPlayer = players.get(0); 
        board.registerPlayers(playerOne,playerTwo);
    }

    public void startNewGame(){
         board.resetBoard();
    }
    
    @Override
    public String toString(){
        return board.toString();
    }

}