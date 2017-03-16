Name: Vinayak Ravindra Tare
UTA ID: 1001453869
Programming language: Java

This program uses alpha-beta pruning to find the optimal solution.

structure:
maxconnect4.java
{
methods:
HumanPlay()                : takes input from user and makes the next
                             move for human player.
ComputerPlay()		   : calls AI player to make a move
PrintScore()		   : Prints current game score  
PrintFinalScore()	   : Prints final game score and declares the winner and exits the program.
exit_function()		   : exits from the program.                           
}

Aiplayer.java{
findBestPlay() 		   : returns an integer indicating which column the AiPlayer would like to play in.
MaxValue()		   : computes the max value
MinValue()		   : computes the min value
}
GameBoard.java{
getscore()       : returns the score for the player given as an argument.
getCurrentTurn() : returns the current turn.
getGameBoard()   : a dual indexed array representing the gameboard
playpiece()	 : This method plays a piece on the game board.
isValidPlay()    : determines if a play is valid or not. 
}

How to run the Code :
Compile
javac maxconnect4.java GameBoard.java AiPlayer.java

Run:
Interactive Mode
java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]
java maxconnect4 interactive input1.txt computer-next 7

One-Move Mode:
java maxconnect4 one-move [input_file] [output_file] [depth]
java maxconnect4 one-move input1.txt output1.txt 5


TOURNAMENT
I would like my program to go for the Tournament.
