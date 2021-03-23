import java.util.Scanner;

public class QueensPuzzle {
	private static int size;
	private int board[][];
	private static Scanner input = new Scanner(System.in);

	public QueensPuzzle() {
		board = new int[size][size];
		printBoard();
	}

	/*
	 * Metod som placerar damerna i brädet från vänster till höger kolumnvis. 
	 * Avslutas när brädet är fullt.
	 */
	public boolean placeQueen(int board[][], int column) {
		if (column == size)
			return true;

		for (int i = 0; i < size; i++) {
			if (isPositionSafe(board, column, i) == true) {
				board[i][column] = 1;

				if (placeQueen(board, column + 1) == true) {
					return true;
				} else {
					board[i][column] = 0;
				}
			}
		}
		return false;
	}

	/*
	 * Kollar om damen kan placeras på en viss position utan att kunna bli
	 * attackerad av en annan dam. Vi behöver bara kolla damerna till vänster
	 * eftersom de placeras från vänster till höger kolumnvis.
	 */
	private boolean isPositionSafe(int board[][], int column, int row) {
		// Kollar samma rad
		for (int i = 0; i < column; i++)
			if (board[row][i] == 1)
				return false;

		// Kollar diagonalt neråt
		for (int i = row, j = column; j >= 0 && i < size; i++, j--)
			if (board[i][j] == 1)
				return false;

		// Kollar diagonalt uppåt
		for (int i = row, j = column; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;

		return true;
	}
	
	/*
	 * Skriver ut brädet när PlaceQueen() är klar, alltså när
	 * brädet är fullt.
	 */
	public void printBoard() {
		if (placeQueen(board, 0) == true) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					System.out.print("[" + board[i][j] + "]");
				}
				System.out.println();
			}
		}
	}

	public static void main(String args[]) {
		do {
			System.out.print("Enter the size: ");
			size = input.nextInt();
			if (size < 4) {
				System.out.println("The size must be atleast 4"); // Omöjligt att lösa om storleken är mindre än 4
			}
		} while (size < 4);

		new QueensPuzzle();
	}
}
