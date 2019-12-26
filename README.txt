=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: -----
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

	 NOTE: My original game was "Tower Defense." I later switched to Minesweeper, and
	 so I submitted a new proposal directly to TA's. The feedback I got from them
	 was all positive. None of them had any particular comments aside from ensuring
	 functionality and thoroughness.

  1. 2D Arrays
  	 Features implemented: (1) 2D array of type JButton as well as (2) a 2D array of 
  	 	type Cell. Both 2D arrays had dimensions of 16 x 16. The first 2D array houses
  	 	all of the buttons, each of which appear on the main frame and act as the visual
  	 	"cells" for the user. The second 2D array is located in the Board class. This
  	 	array houses the actual Cells, each of which contain individual specific fields
  	 	(i.e. visibility and flagged status).
  	 Reason for 2D Arrays: 2D arrays are perfect for Minesweeper because the board is 
  	 	of a fixed size and dimension. There isn't any need to move buttons or cells
  	 	around, which is another advantage of using 2D arrays. Additionally, using arrays
  	 	allows for me to easily access individual buttons and cells (I believe it is also
  		quicker and more efficient, which is always great!).
  	 

  2. Collections
  	 Features implemented: (1) a LinkedList of type Move and (2) an ArrayList of type
  	 	Integer. The LinkedList contains all of the previous moves done by the user (since
  	 	the beginning of the game or import). Note that the move class contains the coordinates
  	 	of the affected cell and the type of move done. The ArrayList contains the "id's" of
  	 	all of the flagged cells (to simplify indices, used the formula (16 * row + column)).
  	 Reason for Collections: (1) I chose to use a LinkedList since the order of the moves
  	 	is very important. Additionally, I add each move to the front of the LinkedList.
  	 	This allows for easy undo's since all the program needs to do is pop off the most 
  	 	recent move from the LinkedList and undo it (doesn't need to traverse through the 
  	 	whole list).
  	 	(2) I chose to use an ArrayList to store the positions of all the flagged cells
  	 	since I was less worried about order. Flagging cells could come in almost any
  	 	order, so I didn't see the purpose of a LinkedList. I simply needed to know the 
  	 	indices of all the flagged cells, so I found the ArrayList a better choice (not to
  	 	mention the added efficiency and speed). Any time a cell was flagged, I'd add it to
  	 	the ArrayList, and vice versa.

  3. File I/O
  	 Features implemented: I implemented a File I/O that would import and export .txt files
  	 	containing the specific game states. To import a file, the user would click on a 
  	 	button, which would bring up the file import screen. At this point, the user
  	 	would click on a specific file. If the fits the proper format (visibility board,
  	 	numbers board, timer count), then the correct state will be updated on the GUI and
  	 	the game will resume. If the incorrect file is imported, a message dialog will be 
  	 	shown. Nothing will be updated. To export a file, the user would click on the export
  	 	button, and the file will be saved in the project folder. All files are labeled with
  	 	a unique time-stamp (YYYY-MM-DD-HH-MM-SS.txt).
  	 Reason for File I/O: I found it important to have players be able to save, resume, and 
  	 	pick up on previous games. A File I/O would help to accomplish this. My implementation
  	 	is an appropriate use of the concept - it configures the board correctly depending on 
  	 	the input file and accounts for invalid input files. 

  4. Testable Component
  	 Features implemented: I first tested each of my individual classes, ensuring that the 
  	 	getters, setters, and any small game logic/setup worked correctly. Proper testing of
  	 	these classes is pivotal, as the rest of the game relies on flawless implementations of 
  	 	these classes. Then, I tested the GameCourt class. This is where the bulk of the game
  	 	logic occurs, so proper testing here is key. I used JUnit for all of these tests. In
  	 	general, I tried to test for all exceptions and edge cases, as well as a few normal 
  	 	cases.
  	 Reason for Testable: As I stated above, proper testing is fundamental to the success of
  	 	my game. Although I can test a lot of the features through the GUI, I still want to 
  	 	ensure that the back-end products are functioning as I want.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  - Cell.java -> This class focuses on each individual cell of the Minesweeper game.
 		Cell itself does not contain much aside from a few getters and setters.
		Each cell also stores a boolean variable indicating its visibility and 
		flagged status. Each cell will later be housed in a 2D array (see Board.java).
		Cells will be accessed to assess the current state of the game as well as to 
		update moves.
  - Board.java -> This class focuses on consolidating the board and containing the proper
 		numerical values/set conditions. Aside from the standard setters and getters, 
 		the three sections that contain algorithms are the constructor (2nd), addBombs, 
 		and numSurroundingBombs.
		The algorithm in determining the position of items is as follows.
			• The x coordinate will be: VALUE / 16
			• The y coordinate will be: VALUE % 16
 			• For example, cell number 23 will be in row 1 (23/16 = 1) and column 7 (23%16 = 7)
 		Board.java will act as a key class referenced in GameCourt.java. This file will hold
 		the actual cells and will work in conjunction with the 2D array of JButtons
 		(see GameCourt.java)
  - Move.java -> This class focuses on storing any move. To do so, it must know the coordinates
		of the affected cell and the type of move.
		Legend:
			• 0 = User recently revealed a cell
			• 1 = User recently flagged a cell
 			• 2 = User recently unflagged a cell
 		This class is pivotal to keeping track of recently made moves (see moveHistory field in
 		GameCourt.java).
  - GameCourt.java -> This class holds the primary game logic for how the different buttons, cells, and 
		bombs interact with one another. As you will see, most of the functions depend and
		rely closely on each other.
		As you can easily tell, most of the substance regarding the game is housed in this file.
		This file is called from Game.java and works in conjunction to ensure secondary labels
		and game logic functions correctly.
  - Game.java -> This classes focuses on specifying the frame and widgets of the GUI. When the
  		user wants to run the actual game, this is the file that should be run. The 
  		implementation of this file is very similar to that in MushroomOfDoom, the only difference
  		being that mine has more buttons and a more sophisticated control panel.   
  		
  * GameTest.java -> The file which holds all of my tests


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  - Swing is still new to me, so getting used to the frame and interaction between
 	Game.java and GameCourt.java was a difficult obstacle to get across. Additionally,
  	I had originally implemented all of my game logic and main class in one file. This made
  	it easier to run and test. However, I realize that this is not proper style, so I 
  	decided to separate the two files. 
  - Moreover, I tried to ensure separation of tasks by creating many separate methods
  	rather than one long, bulky solution.
  - EDIT: After writing more tests, I created more smaller methods to make testing easier.
  - EDIT 2: I realized that I wanted the files to be located in its own folder. I had to change
  	the paths of the export files such that they all wen to the "files" folder.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  - As stated above, I had originally created all of my methods and logic in a few
  functions and files. Looking back, I should have started by separating chunks of code.
  This made debugging my code a lot easier.
  - Now, I feel that my project is well designed. I have a few separate classes dedicated
  to the individual functionalities, as well as many smaller functions in my GameCourt.java.
  In general, I believe my private state is properly encapsulated. Most of my getters
  are simply returning primitive types. However, when I do return a list, for example, I 
  make sure to return a copy of it.
  - If given the chance to refactor, I would try to separate my files even further. In general,
  I do not like having files over 500 lines long. This makes it difficult and annoying to 
  scroll up, down, and back up the page. Having separate files can also help to distribute tasks
  evenly.
  - Additionally, I would work on accounting for when making my "undo" function more accurate.
  Right now, it'll undo the last move and adjust neighbors accordingly. However, in special cases,
  as described in the comments above the undo() method, it'll cover the previously non-zero cells.
  I could account for this by having the moveHistory be a LinkedList<LinkedList<Move>> where each
  smaller List represents all of the cells changed on the most recent move. 
  - If given a chance to add more things, I would try to add a feature where users can rename their
  export files. Currently, they are all set to "Minesweeper "+current time.
  - Additionally, if given more time (and with perhaps more knowledge of Swing), I would try to
  color the background of the buttons a certain color. Right now, button.setBackground(Color) is
  the only way I know to change the color of a button. However, this only changes the color
  behind the actual button (not the button itself).


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  	- http://minesweeperonline.com/ <-- Experimented with various moves (ex. what would occur
  	if all but one cell was flagged on board and remaining cell was a 0)
  	- https://unichar.app/web/ <-- Used to get the flag and bomb-like unichar
