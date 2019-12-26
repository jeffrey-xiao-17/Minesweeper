import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.*;

import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

//Although I have warnings saying that elements are unused, it's only occurring because
//I commented out one of the tests to make it easier to run the others.
@SuppressWarnings("unused")
public class GameTest {
	
	private String testVisibilityString = "FVVVVVVVVVFVFVVF" + 
			"VVVVVFFVVVVVFVVV" + 
			"VVVVFVVVVVVVVVVV" + 
			"VVVVVVVVVVVVVVVV" + 
			"VVVVVVVFVVVVVVVV" + 
			"FVVVVVVVVFVFVVVF" + 
			"VFVVVVVVVVVVVVVV" + 
			"VVVVVFFVVFVFFVVV" + 
			"VVVVVVVVVVVVVVVV" + 
			"FVVVVVFVVVVVVVFV" + 
			"VVVVVVVVVVVVVVVV" + 
			"VVVVVVVVVVVVVVVV" + 
			"FVVVVVVVVVVVVVVV" + 
			"VVFVVVVVFVVVVVFV" + 
			"VFFVVVFVVVVVVVVV" + 
			"VVVVVVVVVVVVFVVV";

	private String testBoardString = "9100122101939219" + 
			"1101299101139211" + 
			"0001932100011100" + 
			"0001111110000000" + 
			"1100001921211011" + 
			"9210001129291019" + 
			"2910122122433111" + 
			"1110199119299100" + 
			"1100133211222211" + 
			"9100019100000191" + 
			"1100011100000111" + 
			"1100000000000000" + 
			"9211000111000111" + 
			"2492011291000191" + 
			"1992019211011211" + 
			"1221011100019100";
	
	//This testData field is used, but I commented out the test because it causes
	//pop-ups, and it was easier testing the other functions without it
	//NOTE: this file currently does not exist because it has to change name given
	//	the current time. Can explain more during game demo.
	//See the commented out test case for more details.
	private String testData = "files/Minesweeper 2019-12-03-01-38-57.txt";
	
	private String testTimerCount = "116";
	
	private String testEmptyVisibilityString = "HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHHHHHHHHHHHHHHH";
	
	private String testEmptyBoardString = "0191011111111100" + 
			"0122129219119210" + 
			"0019139311112910" + 
			"0011239311001110" + 
			"1100293292100000" + 
			"9100393129100111" + 
			"1100292011211292" + 
			"0111122111292392" + 
			"0191019119229321" + 
			"0111122233334910" + 
			"0000191199299210" + 
			"1110111122222100" + 
			"1910001110000011" + 
			"1110001910000019" + 
			"0000001110000011" + 
			"0000000000000000";
	
	private String testLostVisibilityString = "HHHHHHHVHHHHHHHV" + 
			"HHHHHVHHHHHHHVHH" + 
			"HHHHHHVHHHHHHVHH" + 
			"HHHHHHHVHHHHHHVH" + 
			"VHHHHVHHHHHHHVHH" + 
			"HHHHHHHHVHHHHHHH" + 
			"HHHHHHHHHVHHHHHV" + 
			"HHHHHHHHHHHHHHHH" + 
			"HHVHHHHHHHHHHHHH" + 
			"HHHHHHHHVHHHHHHH" + 
			"VHHVHHHHVHHHHHHH" + 
			"HHHHHHHVHHVHHHVH" + 
			"VHHHVHHHHHHHHHHH" + 
			"HHHHHHHHHVHHHHHH" + 
			"HHHHHHHVHHHHHHVH" + 
			"HHHHHVVHHHHHHHHV";
	
	private String testLostBoardString = "0000112910001129" + 
			"0000193210002931" + 
			"0000129210002931" + 
			"1100123910002391" + 
			"9100192221001921" + 
			"1100111192101121" + 
			"0000000129100019" + 
			"0111000011100011" + 
			"0191000111000000" + 
			"1222100292000000" + 
			"9119101393110111" + 
			"2212211922910191" + 
			"9101911122210111" + 
			"1101111129100111" + 
			"0000123921100192" + 
			"0000199210000129";
	
	private JLabel status = new JLabel("Running...");
	private JLabel timerLabel = new JLabel("Current Time (sec): ");
	private JLabel flagLabel = new JLabel("Flags Remaining: ");
	
	/*
	 * ---------------------------- TESTS FOR CELL.JAVA ----------------------------
	 */
	
	@Test (expected = IllegalArgumentException.class)
    public void invalidCoordinatesCellConstructor() {
		Cell c = new Cell(16, -3, 4);
    }
	
	@Test (expected = IllegalArgumentException.class)
    public void invalidNumericalValueCellConstructor() {
    	Cell c = new Cell(0, 12, 10);
    }
	
	@Test
    public void validInitialCellConstructor() {
		Cell c = new Cell(0, 5, 5);
		assertEquals("Getter of x coor. works", 0, c.getxCoor());
		assertEquals("Getter of y coor. works", 5, c.getyCoor());
		assertEquals("Getter of value works", 5, c.getValue());
    }
	
	@Test
    public void visibilitySetAndGet() {
		Cell c = new Cell(2, 13, 0);
		assertFalse("Getter of initial visibility", c.isVisible());
		
		c.setVisible(true);
		assertTrue("Getter of changed visibility", c.isVisible());
		
		c.setVisible(true);
		assertTrue("Getter of unchanged visibility", c.isVisible());
    }
	
	@Test
    public void flaggedSetAndGet() {
		Cell c = new Cell(6, 4, 9);
		assertFalse("Getter of initial flagged", c.isFlagged());
		
		c.setFlagged(true);
		assertTrue("Getter of changed flagged", c.isFlagged());
		
		c.setFlagged(true);
		assertTrue("Getter of unchanged flagged", c.isFlagged());
    }
	
	
	/*
	 * ---------------------------- TESTS FOR BOARD.JAVA ----------------------------
	 */
	
    @Test (expected = IllegalArgumentException.class)
    public void nonMatchingLengthStringConstructor() {
        
    	//this is fine because we are simply testing for the exception in the constructor
    	Board b = new Board("");
    }
    
    @Test
    public void testDefaultValidConstructor() {
		Board b = new Board();
		assertEquals("Test size of board (256+16 to account for newline characters)",
				272, b.toString().length());
    }
    
    @Test
    public void testOverloadedValidConstructor() {
		Board b = new Board(testBoardString);
		assertEquals("Number Surrounding Bombs", 1, b.numSurroundingBombs(0, 1));
		assertEquals("Number Surrounding Bombs", 0, b.numSurroundingBombs(0, 2));
		assertEquals("Test size of board (256+16 to account for newline characters)",
				272, b.toString().length());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testNumSurroundingBombsException() {
		Board b = new Board(testBoardString);
		b.numSurroundingBombs(16, -1);
    }
    
    @Test
    public void testBoardGettersValid() {
		Board b = new Board(testBoardString);
		assertTrue("isBomb",b.isBomb(15, 12));
		assertEquals("getNumOfCell", 2, b.numSurroundingBombs(0, 5));
		assertFalse("getVisibilityOfCell", b.getVisibilityOfCell(3, 7));
		assertFalse("getVisibilityOfCell", b.getVisibilityOfCell(7, 2));
    }
    
    @Test
    public void testSettersValid() {
		Board b = new Board(testBoardString);
		
		b.changeVisibilityOfCell(15, 12, true);
		assertTrue("changeVisibilityOfCell works",b.getVisibilityOfCell(15, 12));
		
		b.changeFlaggedOfCell(0, 5, true);
		assertTrue("changeFlaggedOfCell", b.getFlaggedOfCell(0, 5));
    }
    
    
    /*
	 * ---------------------------- TESTS FOR MOVE.JAVA ----------------------------
	 */
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorExceptionInvalidCoordinates() {
    	Move m = new Move(-1, 16, 2);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testConstructorExceptionInvalidType() {
    	Move m = new Move(0, 12, 120);
    }
    
    @Test
    public void testMoveGettersValid() {
    	Move m = new Move(13, 2, 0);
		assertEquals("x coordinate", 13, m.getxCoor());
		assertEquals("y coordinate", 2, m.getyCoor());
		assertEquals("move type", 0, m.getMoveType());
		assertEquals("toString", "Move Type: 0 on (13, 2)", m.toString());
    }
    
    /*
	 * ---------------------------- TESTS FOR GAMECOURT.JAVA ----------------------------
	 */

    @Test
    public void testGameCourtImportValid() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testBoardString, testVisibilityString, testTimerCount);
    	assertEquals("correct time", 116, gc.getCurrentTime());
    	assertEquals("correct flags remaining", 0, gc.getFlagsRemaining());
    	assertEquals("correct nonBombCellsRemaining", 0, gc.getNonBombCellsRemaining());
    	assertFalse("correct gameInPlay", gc.getGameInPlay());
    	assertArrayEquals("correct move history upon import", new Object[0], gc.getMoveHistory());
    	assertEquals("correct game status", "You win!", gc.getGameStatus());
    }
    
    @Test
    public void testGameCourtImportLoss() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testLostBoardString, testLostVisibilityString, "100");
    	assertEquals("correct time", 100, gc.getCurrentTime());
    	assertEquals("correct flags remaining", 30, gc.getFlagsRemaining());
    	assertEquals("correct nonBombCellsRemaining", 226, gc.getNonBombCellsRemaining());
    	assertFalse("correct gameInPlay", gc.getGameInPlay());
    	assertArrayEquals("correct move history upon import", new Object[0], gc.getMoveHistory());
    	assertEquals("correct game status", "You lose!", gc.getGameStatus());
    }
    
    @Test
    public void testGameCourtInitialGameValid() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	assertEquals("correct time", 0, gc.getCurrentTime());
    	assertEquals("correct flags remaining", 30, gc.getFlagsRemaining());
    	assertEquals("correct nonBombCellsRemaining", 226, gc.getNonBombCellsRemaining());
    	assertTrue("correct gameInPlay", gc.getGameInPlay());
    	assertArrayEquals("correct move history upon import", new Object[0], gc.getMoveHistory());
    	assertEquals("correct game status", "Running...", gc.getGameStatus());
    }
    
    
    /*
     * NOTE: This case was hard to test: because my files are names specific to the export 
     * time-stamp, I had to keep running my test cases, hoping to get it on the dot.
     * 
     * Please consider this test. I only commented it out because it makes testing the others
     * easier (also because this test passed when I ran it, but will be hard to replicate
     * due to the nature of my export file name - need to get it exactly on the dot timing wise)
     *//*
    @Test
    public void testGameCourtExport() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testBoardString, testVisibilityString, testTimerCount);
    	
    	gc.exportGameState();
		File file = Paths.get(testData).toFile();

    	BufferedReader br;
		
    	try {
			br = new BufferedReader(new FileReader(file));
			String l = br.readLine();
			
			for (int i = 0; i < 16; i++ ) {
				assertEquals(testVisibilityString.substring(i*16, i*16+16), l);
				l = br.readLine();
			}
			l = br.readLine();
			
			for (int i = 0; i < 16; i++ ) {
				assertEquals(testBoardString.substring(i*16, i*16+16), l);
				l = br.readLine();
			}
			l = br.readLine();
			assertEquals(testTimerCount, l);
		}
    	catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    */
    
    @Test
    public void testImportException() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	assertFalse(gc.importGameState("files/nonexistent_file.txt"));
		
    } 
    
    @Test
    public void testImportValidInGame() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	assertTrue(gc.importGameState("files/Minesweeper 2019-12-06-14-40-49.txt"));
    	assertEquals("correct time", 6, gc.getCurrentTime());
    	assertEquals("correct flags remaining", 30, gc.getFlagsRemaining());
    	assertEquals("correct nonBombCellsRemaining", 140, gc.getNonBombCellsRemaining());
    	assertTrue("correct gameInPlay", gc.getGameInPlay());
    	assertArrayEquals("correct move history upon import", new Object[0], gc.getMoveHistory());
    	assertEquals("correct game status", "Running...", gc.getGameStatus());
    } 
    
    @Test
    public void testImportValidWin() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	assertTrue(gc.importGameState("files/Minesweeper 2019-12-06-15-35-37.txt"));
    	assertEquals("correct time", 188, gc.getCurrentTime());
    	assertEquals("correct flags remaining", 30, gc.getFlagsRemaining());
    	assertEquals("correct nonBombCellsRemaining", 0, gc.getNonBombCellsRemaining());
    	assertFalse("correct gameInPlay", gc.getGameInPlay());
    	assertArrayEquals("correct move history upon import", new Object[0], gc.getMoveHistory());
    	assertEquals("correct game status", "You win!", gc.getGameStatus());
    } 
    
    @Test
    public void testShowOneClickedCell() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.showClickedCell(0, 0);
    	String s = gc.getVisibilityBoard();
    	
    	assertEquals("Cell at (0,0) is visible", "V", s.substring(0,1));
		
    }
    
    @Test
    public void testHideOneClickedCell() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testEmptyBoardString, testEmptyVisibilityString, "0");
    	gc.showClickedCell(0, 0);
    	gc.hideClickedCell(0, 0);
    	String s = gc.getVisibilityBoard();

    	//test to see that all cells are "hidden"
    	for (int row = 0; row < 16; row++) {
    		for (int column = 0; column < 16; column++) {
    			assertEquals("Cell at ("+row+","+column+") is not visible", 
    					"H", s.substring(row*17+column,row*17+column+1));
    		}
    	}
		
    }
    
    @Test
    public void testFlagOneCell() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testEmptyBoardString, testEmptyVisibilityString, "0");
    	gc.flagCell(1, 6);
    	
    	String s = gc.getVisibilityBoard();
    	
    	assertEquals("Cell at (1,6) is flagged", "F", s.substring(23, 24));

    	//test to see that all other cells are "hidden"
    	for (int row = 0; row < 16; row++) {
    		for (int column = 0; column < 16; column++) {
    			if (row != 1 || column != 6) {
    			assertEquals("Cell at ("+row+","+column+") is not flagged", 
    					"H", s.substring(row*17+column,row*17+column+1));
    			}
    		}
    	}
    } 
    
    @Test
    public void testRevealAllBombs() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testEmptyBoardString, testEmptyVisibilityString, "0");
    	gc.revealAllBombs();
    	
    	String s = gc.getVisibilityBoard();

    	int countBombsVisible = 0;
    	
    	for (int row = 0; row < 16; row++) {
    		for (int column = 0; column < 16; column++) {
    			if (s.substring(row*17+column,row*17+column+1).equals("V")) {
    				countBombsVisible++;
    			}
    		}
    	}
    	assertEquals("16 bombs visible", 30, countBombsVisible);
    } 
    
    @Test
    public void testHideAllBombs() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testEmptyBoardString, testEmptyVisibilityString, "0");
    	gc.revealAllBombs();
    	gc.hideAllBombs();
    	
    	String s = gc.getVisibilityBoard();

    	int countBombsVisible = 0;
    	
    	for (int row = 0; row < 16; row++) {
    		for (int column = 0; column < 16; column++) {
    			if (s.substring(row*17+column,row*17+column+1).equals("V")) {
    				countBombsVisible++;
    			}
    		}
    	}
    	assertEquals("0 bombs visible", 0, countBombsVisible);
    } 
    
    @Test
    public void testOneMoveHistory() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testEmptyBoardString, testEmptyVisibilityString, "0");
    	gc.showClickedCell(0, 5);
    	
    	Object[] mh = gc.getMoveHistory();
    	assertEquals("Move history only contains 1 move", 1, mh.length);
    	assertEquals("Recent move was show cell at (0,5)", "Move Type: 0 on (0, 5)", 
    			mh[0].toString());
    }
    
    @Test
    public void testUndoSingleMoveHistory() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testEmptyBoardString, testEmptyVisibilityString, "0");
    	gc.showClickedCell(0, 5);
    	gc.undo();
    	
    	String s = gc.getVisibilityBoard();
    	Object[] mh = gc.getMoveHistory();
    	
    	assertEquals("Move history contains 0 moves", 0, mh.length);
    	assertEquals("Cell at (0, 5) is not visible", "H", s.substring(5,6));
    }
    
    @Test
    public void testUndoMultipleMoveHistory() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testEmptyBoardString, testEmptyVisibilityString, "0");
    	gc.showClickedCell(0, 5);
    	gc.flagCell(15, 15);
    	gc.flagCell(15, 15); //cell (15,15) is now hidden (no flag)
    	gc.flagCell(15, 15); //cell (15,15) is now flagged
    	
    	gc.undo();
    	gc.undo();
    	
    	String s = gc.getVisibilityBoard();
    	Object[] mh = gc.getMoveHistory();
    	
    	assertEquals("Move history contains 2 moves", 2, mh.length);
    	assertEquals("Cell at (0, 5) is visible", "V", s.substring(5,6));
    	assertEquals("Cell at (15, 15) is flagged", "F", s.substring(270,271));
    	assertEquals("Flags remaining is 29", 29, gc.getFlagsRemaining());
    } 
    
    @Test
    public void testResetDefault() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.showClickedCell(0, 5);
    	gc.flagCell(15, 15);
    	gc.flagCell(15, 15); //cell (15,15) is now only hidden (no flag)
    	gc.flagCell(15, 15); //cell (15,15) is now flagged
    	gc.reset();
    	
    	assertEquals("correct time", 0, gc.getCurrentTime());
    	assertEquals("correct flags remaining", 30, gc.getFlagsRemaining());
    	assertEquals("corrent nonBombCellsRemaining", 226, gc.getNonBombCellsRemaining());
    	assertTrue("correct gameInPlay", gc.getGameInPlay());
    	assertArrayEquals("correct move history upon import", new Object[0], gc.getMoveHistory());
    } 
    
    @Test
    public void testWinStatus() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.winGame();
    	
    	assertEquals("correct status", "You win!", gc.getGameStatus());
    } 
    
    @Test
    public void testLoseStatus() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.loseGame();
    	
    	assertEquals("correct status", "You lose!", gc.getGameStatus());
    } 
    
    @Test
    public void testWinLoseGameStatusEncapsulation() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.winGame();
    	String s = gc.getGameStatus();
    	s = "should not break";
    	assertEquals("correct status", "You win!", gc.getGameStatus());
    	
    	gc.loseGame();
    	
    	assertEquals("correct status", "You lose!", gc.getGameStatus());	
    } 
    
    @Test
    public void testGameStatusAtStart() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	
    	assertEquals("correct status", "Running...", gc.getGameStatus());
    	assertFalse("Win at start", gc.checkWinGame());
    } 
    
    @Test
    public void testFlaggedCellNotRevealedNextToZero() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.resetWithGivenState(testEmptyBoardString, testEmptyVisibilityString, "0");
    	
    	//Both cells below are actually 0's, but we flag them anyway
    	//These two cells should not be revealed upon clicking on a neighboring 0
    	gc.flagCell(15, 0);
    	gc.flagCell(15, 12);
    	
    	gc.showClickedCell(15, 7);
    	Object[] flagged = gc.getFlaggedCells();
    	assertTrue("2 flagged cells total", flagged.length == 2);
    	assertEquals("Cell (15,0) should be flagged", 240, flagged[0]);
    	assertEquals("Cell (15,0) should be flagged", 252, flagged[1]);
    } 
    
    @Test (expected = IllegalArgumentException.class)
    public void testShowCellOutOfBounds() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.importGameState("files/Minesweeper 2019-12-06-14-40-49.txt");
    	
    	gc.showClickedCell(-12, 42);
    } 
    
    @Test (expected = IllegalArgumentException.class)
    public void testHideCellOutOfBounds() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.importGameState("files/Minesweeper 2019-12-06-14-40-49.txt");
    	
    	gc.hideClickedCell(-12, 42);
    } 
    
    @Test 
    public void testImportWinGame() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.importGameState("files/Minesweeper 2019-12-08-23-46-06.txt");
    	
    	assertFalse("Game is not in play", gc.getGameInPlay());
    	assertEquals("Game is won", "You win!", gc.getGameStatus());
    }
    
    @Test 
    public void testShowMultipleCells() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.importGameState("files/Minesweeper 2019-12-08-23-43-38.txt");
    	gc.showClickedCell(15, 15);
    	
    	String vb = gc.getVisibilityBoard();
    	
    	assertTrue("Game is in play", gc.getGameInPlay());
    	assertEquals("Game is running", "Running...", gc.getGameStatus());
    	assertEquals("Cell (15,15) is visible", "V", vb.substring(270, 271));
    	assertEquals("Cell (15,14) is visible", "V", vb.substring(269, 270));
    	assertEquals("Cell (15,13) is visible", "V", vb.substring(268, 269));
    	assertEquals("Cell (15,12) is visible", "V", vb.substring(267, 268));
    	assertEquals("Cell (14,12) is visible", "V", vb.substring(250, 251));
    	assertEquals("Cell (14,13) is visible", "V", vb.substring(251, 252));
    	assertEquals("Cell (14,14) is visible", "V", vb.substring(252, 253));
    	assertEquals("Cell (14,15) is visible", "V", vb.substring(253, 254));
    } 
    
    @Test 
    public void testImportAllFlagged() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.importGameState("files/Minesweeper 2019-12-09-00-08-39.txt");
    	gc.showClickedCell(15, 15);
    	
    	String vb = gc.getVisibilityBoard();
    	
    	assertTrue("Game is in play", gc.getGameInPlay());
    	assertEquals("Game is running", "Running...", gc.getGameStatus());
    	
    	
    	for (int row = 0; row < 16; row++) {
    		for (int column = 0; column < 16; column++) {
    			assertEquals("Cell (" + row + "," + column + ") is flagged", "F", 
    					vb.substring(row * 17 + column, row * 17 + column + 1));
    		}
    	}
    } 
    
    @Test 
    public void testClickOnFlagCell() {
    	GameCourt gc = new GameCourt(status, timerLabel, flagLabel);
    	gc.makeAndAddBoard();
    	gc.importGameState("files/Minesweeper 2019-12-09-00-08-39.txt");
    	gc.showClickedCell(15, 15);
    	
    	gc.showClickedCell(0, 0);
    	
    	String vb = gc.getVisibilityBoard();
    	
    	assertTrue("Game is in play", gc.getGameInPlay());
    	assertEquals("Game is running", "Running...", gc.getGameStatus());
    	assertEquals("Cell (0,0) is still flagged", "F", vb.substring(0, 1));
    	
    }
}
