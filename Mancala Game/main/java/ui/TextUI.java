package ui;

import mancala.MancalaGame;
import mancala.Player;
import mancala.Board;
import mancala.InvalidMoveException; // Import the custom exceptions
import mancala.GameNotOverException;
import mancala.NoSuchPlayerException;
import mancala.PitNotFoundException;
import java.util.Scanner;

public class TextUI {
    private MancalaGame game;
    private Scanner scanner; 
    private Player playerOne = new Player();
    private Player playerTwo = new Player ();
    private String p1Name = "";
    private String p2Name = "";
    private int pitNum = 0;

    public void initilizer(){ // sets everythin up
        game = new MancalaGame();
        scanner = new Scanner(System.in);
    }
    public void print(){
       // System.out.println("hi");
       // game.testPrint();
        //System.out.println(game.toString());
    }

    public void startGame(){
        System.out.println("Welcome to Mancala!");
        System.out.println(" "); // for style
        game.setPlayers(playerOne,playerTwo);
        System.out.println("Enter the name of the first player: ");
        p1Name = scanner.nextLine();
        System.out.println(" ");
        System.out.println("Enter the name of the second player: ");
        p2Name = scanner.nextLine();
        System.out.println(" ");

        game.getPlayers().get(0).setName(p1Name);
        game.getPlayers().get(1).setName(p2Name);
    
        game.setCurrentPlayer(playerOne); // setting the current player to player 1 
   }

   public void playGame(){
        Player theWinner;
        while(!game.isGameOver()){
        System.out.println("");
        System.out.println(game.getCurrentPlayer().getName() + "'s turn.");
        System.out.println(game.toString());
        if(game.getCurrentPlayer()== playerOne){
            System.out.println("Please enter the pit number you would like to move (pits 1 - 6) : ");
            pitNum = scanner.nextInt();
        }else{
        System.out.println("Please enter the pit number you would like to move (pits 7 - 12) : ");
        pitNum = scanner.nextInt();
        }
            try{
                game.move(pitNum);
                //System.out.println(game.toString());

                if(game.getCurrentPlayer()== playerOne && !game.getfreeTurnM()){
                    game.setCurrentPlayer(playerTwo);// switch players 
                }else if(game.getCurrentPlayer()== playerTwo && !game.getfreeTurnM()){
                    game.setCurrentPlayer(playerOne);// switch players 
                } 
            }catch (Exception ex){
                if(game.getCurrentPlayer() == playerOne){
                    game.setCurrentPlayer(playerOne); // makes the player that made a mistake go again
                }else{
                    game.setCurrentPlayer(playerTwo);
                }
                System.out.println(ex.getMessage());
            }
        } 
        //System.out.println("STOP GAME OVER");
        //System.out.println(game.toString());
        try{
        if(game.isGameOver()){
            theWinner = game.getWinner();
            if(theWinner != null){
                System.out.println( theWinner.getName() + " wins with " + theWinner.getStoreCount()+  " stones in their store. Congrats!");
            }
        }
        }catch(GameNotOverException ex){
            System.out.println(ex.getMessage());
        }
    scanner.close();
   }

    public static void main(String[] args) {
        TextUI text = new TextUI();
        text.initilizer(); 
        text.startGame();
        text.playGame();
        //text.print();
        //text.startGame();
    }

}