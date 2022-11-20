
package minesweepergame;

import java.util.Scanner;

// Recreate a simplified version of the game Minesweeper to be played in the java console

// The game should be able to 
// randomly generate 10 mines -> 
	// 2D array (for loop within a for loop)
	// 
// in a 10x10 grid ✅

// The user will be able to 
// enter a command -> use scanner object
// that represents 
// a coordinate (x,y)
// to check a location for a mine


// The application will 
// display a number 
// from 0-8 -> e.g. 2
// depending on 
// how many mines surround that location (x, y)

// If the user selects a mine, 
// the game will respond "boom!" -> display message to console
// and the game will be lost

// If every non-mine square 
// has been revealed,
// the game is won

// Render the grid 
// to the console 
// after every user command e.g. empty box


public class App {

	public static void main(String[] args) {
		int size = 10;
		int totalMines = 10;
		
		Board board = new Board(size, totalMines);
		Scanner scanner = new Scanner(System.in);
		
		board.draw();
		
		while (!board.gameOver) {
			Coordinate input = getCoordinates(size, scanner);
			board.input(input);
			board.draw();
		}
	}
	
	public static Coordinate getCoordinates(int maxSize, Scanner scanner)
	{
		Coordinate input = new Coordinate();
		
		System.out.println("Enter X Coordinate:");
		while (input.x == 0 || input.y == 0)
		{
			try {
				int output = scanner.nextInt();
				if (output < 1 || output > maxSize) {
					System.out.printf("Invalid Position. Please specify a number between 1 and %d", maxSize);					
				} else {
					if (input.x == 0) {
						input.x = output;	
						System.out.println("Enter Y Coordinate:");									
					} else {
						input.y = output;
					}
				}
			}
			catch(Exception e) {
				scanner.next();
				System.out.println("you must enter a whole number");
			}
		}
		
		return input;
	}

}
