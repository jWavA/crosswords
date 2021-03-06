public class Trie { // prefix trie for storing dictionary 
	//@author James Wu
	
	Trie parent; // trie that's one higher
	Trie[] subtries;
	boolean endofWord;
	boolean used; // specific to our problem: already used words will be placed.
					// here.
	int height;// keeps track of the height of the trie;

	public Trie() {
		parent = null;
		subtries = new Trie[26]; // 26 letters
		endofWord = false;
	}

	public Trie(Trie t) {// create a new trie that has a parent
		parent = t;
		subtries = new Trie[26]; // 26 letters
		endofWord = false;
	}

	public void updateHeights() { //needs to run before all heights are updated properly in the trie
		this.height = findHeight(this, 0); //find the height of this trie
		for (Trie t : subtries) {
			if (t == null)
				continue;
			t.updateHeights(); //update the heights of all the trie's children
		}
	}

	private static int findHeight(Trie t, int currentHeight) {
		int maxHeight = currentHeight; // assume the maximum height is the
										// current height of the object
		for (int x = 0; x < 26; x++) {
			if (t.subtries[x] == null)
				continue;
			int subHeight = findHeight(t.subtries[x], currentHeight + 1); // the
																			// height
																			// of
																			// this
																			// subtrie;
			if (subHeight > maxHeight)
				maxHeight = subHeight;
		}
		// if we've reached the maximum height allowed by this trie, return this
		// value
		return maxHeight;
	}

	private int scanRow(Trie t, int height, int index) { // scans a single
															// layer for the
															// indexth trie
															// whose height is
															// height
		for (int x = 0; x < 26; x++) {
			if (t.subtries[x] == null)
				continue;
			if (t.subtries[x].height >= height) {
				if (index == 0)
					return x;
				else
					index--;
			}

		}
		return -1; // return -1 if none found

	}

	public String findWord(int length) {// finds the first word of length
										// length, returns null otherwise
		String word = "";
		int[] indexes = new int[height + 1];
		for (int x = 0; x < height; x++) {
			indexes[x] = 0; // set all indices to 0 first
		}
		int currentheight = 0;
		Trie root = this;
		while (true) { //not actually a forever loops
			int index = indexes[currentheight];
			int t = scanRow(root, length - currentheight - 1, index); // find
																		// the
																		// first
																		// trie
			// with the correct
			// height.

			//System.out.println(word);
			//System.out.println("index: " + index);

			
			if (t == -1) { //if none is found, go up a level and keep searching
				currentheight--;
				if (currentheight == 0) {
					root = this;
					word = "";
					indexes[currentheight]++; //increase the index on the height
					continue;
				}
				if (currentheight == -1) {
					return null;
					}
				word = word.substring(0, currentheight); //this isn't currentheight-1 because we just subtracted one from it

				root = root.parent;

				indexes[currentheight]++;
				continue;
			}
			root = root.subtries[t]; // move down one

			currentheight++; // increase the height.
			indexes[currentheight] = 0; // reset index
			word += (char) ((char) t + 'A') + ""; // add the letter to the word
			if (currentheight == length) { // if we've reached the correct
											// length of word
				if (root.endofWord && !root.used) { // if its actually a word and it hasn't been used
					root.used = true;
					return word;
				} else {

					word = word.substring(0, currentheight-1); // delete the
																	// last
																	// letter
																	// given
			
					root = root.parent;// move up one
					currentheight--;
					indexes[currentheight]++; // move the index one over

				}
			}

		}
	}

	public void insert(String s) { // inserts String into root trie;
		s = s.toUpperCase();
		Trie root = this;
		for (int x = 0; x < s.length(); x++) {
			if (root.subtries[s.charAt(x) - 'A'] == null) {
				root.subtries[s.charAt(x) - 'A'] = new Trie(root);

			}
			root = root.subtries[s.charAt(x) - 'A']; // go down one path
		}

		root.endofWord = true; // mark it as the end of the word

	}

	public boolean search(String s) {// returns true if the String is found
		s = s.toUpperCase();
		Trie root = this;
		for (int x = 0; x < s.length(); x++) {
			if(s.charAt(x) == '?') return false;
			if (root.subtries[s.charAt(x) - 'A'] == null) {
				return false;
			}
			root = root.subtries[s.charAt(x) - 'A'];

		}
		return root.endofWord;
	}

	public static void main(String[] args) { // testing function- will delete
												// later
		Trie t = new Trie();
		t.insert("helloa");
		t.insert("hellob");
		t.insert("orange");

		t.insert("apples");

		t.insert("melons");
		t.insert("carnegie");

		t.insert("mellon");

		t.insert("dragon");
	   
		t.updateHeights();
		
		//this should return all the words back in alphabetical order
		System.out.println("*" + t.findWord(6));
		System.out.println("*" + t.findWord(6));

		System.out.println("*" + t.findWord(6));

		System.out.println("*" + t.findWord(6));
		System.out.println("*" + t.findWord(8));

		System.out.println("*" + t.findWord(6));

		System.out.println("*" + t.findWord(6));

		System.out.println("*" + t.findWord(6));
		}

}
