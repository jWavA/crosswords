import java.util.ArrayList;

public class Grid { // defines a 2 dimensional grid as the area in which we will
					// work. Assume square grids for simplicity's sake
	Character[][] grid; // two dimensional grid we will be using.
	int length; // lengthxlength grid
	ArrayList<Word> puzzle; //the words in the puzzle

	public Grid(int length) { // creates an empty grid.
		this.length = length;
		grid = new Character[length][length];
		puzzle = new ArrayList<Word>();

	}

	private boolean checkValid(int x, int y) { // checks if a certain coordinate
												// pair is valid.
		if (x < length && y < length && 0 > x && 0 > y)
			return true;
		return false;
	}

	public void makeBlack(int x, int y) {
		if (!checkValid(x, y)) { // if it isn't a valid coordinate system
			System.out.println("invalid coordinates");
			return;
		}
		// otherwise fill the space with 0.
		grid[x][y] = 0;

	}

	public void insertChar(int x, int y, char c) { // inserts a character c into
													// location x, y on the grid
		if (!checkValid(x, y)) {
			System.out.println("invalid coordinates");
			return;

		}
		// otherwise fill it
		grid[x][y] = c;
	}

	public void insertStringDown(int row, int col, String str) { 
		// inserts String vertically
		if (!checkValid(row, col)) {
			System.out.println("invalid coordinates");
			return;

		}
		
		if((length - row) < str.length() ) {
			//if the String is too big to fit in the grid
			System.out.println("Invalid String: too large to fit in specified area");
			return;
		}
		
		//otherwise we can begin filling
		for(int i = 0; i < str.length(); i++) {
			insertChar(row+i,col, str.charAt(i));
		}

	}
	
	public void insertStringAcross(int row, int col, String str) { 
		// inserts String horizontally
		if (!checkValid(row, col)) {
			System.out.println("invalid coordinates");
			return;

		}
		
		if((length - col) < str.length() ) {
			//if the String is too big to fit in the grid
			System.out.println("Invalid String: too large to fit in specified area");
			return;
		}
		
		//otherwise we can begin filling
		for(int i = 0; i < str.length(); i++) {
			insertChar(row,col+i, str.charAt(i));
		}

	}

}
