

import java.util.Scanner;
/***
 * @author Vinayak Tare
 * 
 */


/**
 * 
 * 		   This class controls the game play for the Max
 *         Connect-Four game. To compile the program, use the following command
 *         from the maxConnectFour directory: javac *.java
 *
 *         the usage to run the program is as follows: ( again, from the
 *         maxConnectFour directory )
 *
 *         -- for interactive mode: java MaxConnectFour interactive [ input_file
 *         ] [ computer-next / human-next ] [ search depth]
 *
 *         -- for one move mode java maxConnectFour.MaxConnectFour one-move [
 *         input_file ] [ output_file ] [ search depth]
 * 
 *         description of arguments: [ input_file ] -- the path and filename of
 *         the input file for the game
 * 
 *         [ computer-next / human-next ] -- the entity to make the next move.
 *         either computer or human. can be abbreviated to either C or H. This
 *         is only used in interactive mode
 * 
 *         [ output_file ] -- the path and filename of the output file for the
 *         game. this is only used in one-move mode
 * 
 *         [ search depth ] -- the depth of the minimax search algorithm
 * 
 * 
 */

public class maxconnect4 {
	public static AiPlayer calculon = null;
	public static int COLS = 7;
	public static String computer = "computer.txt";
	public static String human = "human.txt";

	public static void main(String[] args) {
		// check for the correct number of arguments
		if (args.length != 4) {
			System.out.println("Four command-line arguments are needed:\n"
					+ "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
					+ " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

			exit_function(0);
		}

		// parse the input arguments
		String game_mode = args[0].toString(); // the game mode
		String input = args[1].toString(); // the
																				// input
																				// game
																				// file
		int depthLevel = Integer.parseInt(args[3]); // the depth level of the ai
													// search

		// create and initialize the game board
		GameBoard currentGame = new GameBoard(input);

		// create the Ai Player
		calculon = new AiPlayer(depthLevel);

		// variables to keep up with the game
		int playColumn = 99; // the players choice of column to play
		@SuppressWarnings("unused")
		boolean playMade = false; // set to true once a play has been made

		if (game_mode.equalsIgnoreCase("interactive")) {
			//Interactive mode
			currentGame.printGameBoard();
			if (args[2].toString().equalsIgnoreCase("computer-next")) {
				int computerPiece = currentGame.getCurrentTurn();
				ComputerPlay(currentGame, computerPiece);
			} else if (args[2].toString().equalsIgnoreCase("human-next")) {
				int humanPiece = currentGame.getCurrentTurn();
				HumanPlay(currentGame,humanPiece);

			}
			else{
				System.out.println("Please specify command line arguments in this form. 'java [program name] interactive [input_file] [computer-next / human-next] [depth]'");
			}

			
		}

		else if (!game_mode.equalsIgnoreCase("one-move")) {
			System.out.println("\n" + game_mode + " is an unrecognized game mode \n try again. \n");
			return;
		}
		

		/************************ one-move mode ******************************/
		// get the output file name
		String output =  args[2].toString(); //output file

		System.out.print("\n*****MaxConnect-4 game*****\n");
		System.out.print("Game state before move:\n");

		// print the current game board
		currentGame.printGameBoard();
		// print the current scores
		System.out.println(
				"Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = " + currentGame.getScore(2) + "\n ");

		// ****************** this chunk of code makes the computer play
		if (currentGame.getPieceCount() < 42) {
			int current_player = currentGame.getCurrentTurn();
			// AI play
			playColumn = calculon.findBestPlay(currentGame);

			// play the piece
			currentGame.playPiece(playColumn);

			// display the current game board
			System.out.println(
					"move " + currentGame.getPieceCount() + ": Player " + current_player + ", column " + playColumn);
			System.out.print("game state after move:\n");
			currentGame.printGameBoard();

			// print the current scores
			System.out.println(
					"Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = " + currentGame.getScore(2) + "\n ");
			if(currentGame.getPieceCount()==42){
				printFinalScore(currentGame);
			}
			currentGame.printGameBoardToFile(output);

		} else{
			
			System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
		}

		// ************************** end computer play

		return;

	} // end of main()

	/**
	 * Name: Prints score of current Game
	 * @param currentGame current Game state
	 */
	public static void printScore(GameBoard currentGame){
		System.out.println("Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = " + currentGame.getScore(2) + "\n ");

	}
	
	/****
	 * Name:printFinalScore prints final score of player 1 & 2 and declares the winner
	 * @param currentGame current Game state
	 */
	public static void printFinalScore(GameBoard currentGame){
		//System.out.println("Score: Player 1 = " + currentGame.getScore(1) + ", Player2 = " + currentGame.getScore(2) + "\n ");
		currentGame.printGameBoard();
		System.out.println("\n*************************Final Score*******************************");
		System.out.println("****                                                             ");
		System.out.println("****      Player 1 : " + currentGame.getScore(1));
		System.out.println("****      Player 2 : " + currentGame.getScore(2));
		System.out.println("****                                                             ");
		System.out.println("*******************************************************************\n");
		
		if(currentGame.getScore(1)==currentGame.getScore(2)){
			System.out.println("Game is Tie!!! ");
		}
		else if(currentGame.getScore(1)>currentGame.getScore(2)){
			System.out.println("!!!Player 1 Won!!!");
		}
		else{
			System.out.println("!!!Player 2 Won!!!");
		}
		exit_function(0);
	}
	
	
	/******
	 * Name: ComputerPlay This method make computer player make its move
	 * @param currentGame current Game state
	 * @param computerPiece computerPiece value
	 * 
	 * ****/
	public static void ComputerPlay(GameBoard currentGame, int computerPiece) {
		if(currentGame.getPieceCount()>=42){
			printFinalScore(currentGame);
			
		}
		int play = calculon.findBestPlay(currentGame);
		System.out.println("move : " + currentGame.getPieceCount() + "  Computer Player,  column " + play);
		System.out.print("game state after move:\n");
		currentGame.playPiece(play);
		currentGame.printGameBoard();
		
		int humanPiece;
		if (computerPiece == 1) {humanPiece = 2;} else {humanPiece = 1;}
		printScore(currentGame);
		currentGame.printGameBoardToFile(computer);
		HumanPlay(currentGame, humanPiece);
	}

	/******
	 * Name: HumanPlay This method takes input from user to make his/her move
	 * @param currentGame current Game state
	 * @param humanPiece humanPiece value
	 * 
	 * ****/
	public static void HumanPlay(GameBoard currentGame, int humanPiece) {
		if(currentGame.getPieceCount()>=42){
			printFinalScore(currentGame);
			
		}
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter column number you wanna play (1 to 7)");
		int playChoice = sc.nextInt();
		playChoice = playChoice-1;
		if (currentGame.isValidPlay(playChoice)) {
			System.out.println("move : " + currentGame.getPieceCount() + "  Human Player,  column " + playChoice);
			System.out.print("game state after move:\n");
			currentGame.playPiece(playChoice);
			
		} else {
			System.out.println("Invalid move");
			HumanPlay(currentGame, humanPiece);
		}
		int computerPiece;
		if (humanPiece == 1) {computerPiece = 2;} else {computerPiece = 1;}
		currentGame.printGameBoard();
		printScore(currentGame);
		currentGame.printGameBoardToFile(human);
		ComputerPlay(currentGame, computerPiece);

	}

	/**
	 * This method is used when to exit the program prematurely.
	 * 
	 * @param value
	 *            an integer that is returned to the system when the program
	 *            exits.
	 */
	private static void exit_function(int value) {
		System.out.println("exiting from MaxConnectFour.java!\n\n");
		System.exit(value);
	}
} // end of class connectFour
