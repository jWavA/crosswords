import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main2 { // for testing
	static Trie dictionary = new Trie();
	static Wordlist list;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("Reading file...");

		BufferedReader inputStream = null;
		int words = 0;
		String[] wordlist = new String[172804];

		String currentword = "";
		try {

			inputStream = new BufferedReader(new FileReader("scrabble.txt"));
			int c;
			System.out.println("File read. Loading words into dictionary...");

			while ((c = inputStream.read()) != -1) {
				if (c == ' ' || c == '\n') {// if its a space
					wordlist[words] = currentword;
					dictionary.insert(currentword);
					words++;
					// System.out.println(currentword);
					currentword = "";

				} else {
					currentword += (char) c + "";
				}
			}
		}

		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		list = new Wordlist(wordlist);
		Grid grid = new Grid(3);

		grid.startGrid();

		solve(grid, 0, 1);

		System.out.println(isSolved(grid));

	}

	private static Grid solve(Grid grid, int index, int direction) { // 1 for
																		// across,
																		// 2
		// for down. Alternates between the two directions so that it does 1
		// across, 1 down, 2 across, 2 down, etc.
		System.out.println("---------------");
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				System.out.print(grid.grid[x][y] + " ");
			}
			System.out.println("\n");
		}
		if (direction == 1) {

			while (!isSolved(grid)) {
				for (Word word : grid.across) {

					if (dictionary.search(word.word)) {
						continue;
					} else {

						String findword = list.findWord(word.word, index);
						index++;
						if(index > 10000) {
							index = 0;
						}
						if (findword == null) {
							return null;

						} else {

							System.out.println(findword);
							System.out.println(word.row);
							System.out.println(word.column);
							grid.insertStringAcross(word.row, word.column,
									findword);
							try {

								Grid solution = solve(grid, index, 2);
								if (solution == null) {
									word.word = "???";
									for(int x = word.column; x < word.length; x++) {
										grid.grid[word.row][x] = '?';
									}
									return solve(grid, index, 1);

								}
								return solution;
							} catch (Exception e) {
								System.out
										.println("Maximum limit reached; stopping");
								System.exit(0);
							}

						}

					}
				}

			}
		} else if (direction == 2) {
			while (!isSolved(grid)) {
				for (Word word : grid.down) {

					if (dictionary.search(word.word)) {
						continue;
					} else {
						String findword = list.findWord(word.word, index);
						index++;
						if(index > 10000) {
							index = 0;
						}
						if (findword == null) {
							return null;
						} else {
							grid.insertStringDown(word.row, word.column,
									findword);
							try {
								Grid solution = solve(grid, index, 1);
								if (solution == null) {
									word.word = "???";
									for(int x = word.row; x < word.length; x++) {
										grid.grid[x][word.column] = '?';
									}
									return solve(grid, index, 2);
								}
								return solution;
							} catch (Exception e) {
								System.out
										.println("Maximum limit reached; stopping");
								System.exit(0);
							}

						}

					}
				}
			}
		}

		return grid;
	}

	public static boolean isSolved(Grid grid) { // checks if every word is
												// solved
		for (Word word : grid.across) {
			if (!dictionary.search(word.word)) {
				return false;
			}
		}
		for (Word word : grid.down) {
			if (!dictionary.search(word.word)) {
				return false;
			}
		}

		return true;

	}

}
