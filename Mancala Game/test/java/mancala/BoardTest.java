package mancala;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class BoardTest {
    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach public void setUp() {
        //game = new MancalaGame();
        board = new Board();
        player1 = new Player("Player One");
        player2 = new Player("Player Two");
    } 

    private int getPitValue(int pit){
        return board.getPits().get(pit-1).getStoneCount();
    }

    private int getStoreValue(int store){
        return board.getStores().get(store-1).getTotalStones();
    }

    @Test
    public void testSetUpPitsAndGetPits() {
        for (Pit pit : board.getPits()) {
            assertEquals(4, pit.getStoneCount()); //asserts that the are equal
        }
    }


    @Test
    public void testMoveStonesValidMove() throws InvalidMoveException,PitNotFoundException {
        int startPit = 3; // assuming valid move
        board.registerPlayers(player1, player2);
        int stonesAddedToStore = board.moveStones(startPit, player1);
        assertEquals(5,getPitValue(4));
        assertEquals(1,player1.getStoreCount());

    }

    @Test
    public void testMoveStonesInvalidMove() {
        assertThrows(InvalidMoveException.class, () -> board.moveStones(20, player1)); // should throw an invalid move since startPit out of bounds
    }

    @Test
    public void testCaptureStonesWhenNoCapture() throws PitNotFoundException{
        int endPit = 3; // this is the pit that we end on (since it is a fresh board amount of stones is not zero)
        int test = board.captureStones(endPit); // since first move across should have 4 
        assertEquals(0,test);
    }

    @Test
    public void testCaptureStonesInvalidEndPit() throws PitNotFoundException{
        int endPit = -1; // invalid endPoint
        assertThrows(PitNotFoundException.class, () -> board.captureStones(endPit));
    }

    @Test
    public void testMoveStonesCaptureStones() throws InvalidMoveException ,Exception{
        board.registerPlayers(player1, player2);
        int test = 0;
        test = board.moveStones(1,player1);
        test = board.moveStones(9,player2);
        test = board.moveStones(10,player2);
        test = board.moveStones(6,player1);
        test = board.moveStones(9,player2);
        assertEquals(5,test); // I am expecting the store count to go up by 5 since that is what is being captured

    }

    @Test
    public void testRegisterPlayerOne() throws PitNotFoundException{
        board.registerPlayers(player1, player2);
        assertEquals(player1.getStore(),board.getStores().get(0)); // player has a close that returns the store
                                                                  // so board getStores at index 0 should match, because I registered it to index 0
    }

    @Test
    public void testRegisterPlayerTwo() throws PitNotFoundException{
        board.registerPlayers(player1, player2);
        assertEquals(player2.getStore(),board.getStores().get(1)); // player has a close that returns the store
                                                                  // so board getStores at index 1 should match, because I registered it to index 1
    }

    @Test 
    public void testIsSideEmptyValidPit() throws PitNotFoundException{
        boolean test = board.isSideEmpty(1);
        assertEquals(false,test); // should be false because each pit has 4 in the beggining
    }

    @Test 
    public void testIsSideEmptyInvalidPit() throws PitNotFoundException{
        assertThrows(PitNotFoundException.class, () -> board.isSideEmpty(-9000));
    }

    @Test 
    public void testIsSideEmptyEmpty() throws PitNotFoundException,InvalidMoveException {
         board.registerPlayers(player1, player2);
        int test = 0;
        
        test = board.moveStones(1,player1);
        test = board.moveStones(9,player2);
        test = board.moveStones(10,player2);
        test = board.moveStones(6,player1);
        test = board.moveStones(9,player2);

        test = board.moveStones(5,player1);
        test = board.moveStones(9,player2);
        test = board.moveStones(6,player1);
        test = board.moveStones(4,player1);
        test = board.moveStones(8,player2);

        test = board.moveStones(6,player1);
        test = board.moveStones(5,player1);
        test = board.moveStones(12,player2);
        test = board.moveStones(6,player1);
        test = board.moveStones(11,player2);

        test = board.moveStones(5,player1);
        test = board.moveStones(6,player1);
        test = board.moveStones(4,player1);
        test = board.moveStones(12,player2);
        test = board.moveStones(9,player2);

        test = board.moveStones(6,player1);
        test = board.moveStones(5,player1);
        test = board.moveStones(10,player2);
        test = board.moveStones(6,player1);
        test = board.moveStones(3,player1);

        test = board.moveStones(12,player2);
        test = board.moveStones(11,player2);
        test = board.moveStones(5,player1);
        test = board.moveStones(12,player2);

        boolean tester = board.isSideEmpty(7);
        assertEquals(true,tester);
    }

    @Test
    public void testResetBoardPlayer1() throws InvalidMoveException, PitNotFoundException{
        board.registerPlayers(player1, player2);
        int move = board.moveStones(1,player1); // move a pits stone
        board.resetBoard();
        int test = board.getNumStones(1);
        assertEquals(4,test);
    }

    @Test
    public void testRestBoardPlayer2() throws InvalidMoveException, PitNotFoundException{
        board.registerPlayers(player1, player2);
        int move = board.moveStones(8,player2); // move a pits stone
        board.resetBoard();
        int test = board.getNumStones(8);
        assertEquals(4,test);
    }

    @Test
    public void testMoveStonesInvalidMovePlayer1() throws InvalidMoveException {
        int startPit = 2;
        assertThrows(InvalidMoveException.class, () -> board.moveStones(startPit, player1)); 
        // when i change to 2 it still passess
        // should throw an invalid move since startPit out of bounds
    }

    @Test
    public void testMoveStonesInvalidMovePlayer2() throws InvalidMoveException {
        assertThrows(InvalidMoveException.class, () -> board.moveStones(20, player2)); 
        // should throw an invalid move since startPit out of bounds
    }

// come up with more tomorrow

} 