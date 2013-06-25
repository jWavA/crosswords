
public class Trie { //prefix trie for storing dictionary
	Trie parent; //trie that's one higher
	Trie[] subtries;
	boolean endofWord;
	int height;//keeps track of the height of the trie;
	
	public Trie() {
		parent = null;
		subtries = new Trie[26]; //26 letters
		endofWord = false;
		height = 0;
	}
	public Trie(Trie t) {//create a new trie that has a parent
		parent = t;
		subtries = new Trie[26]; //26 letters
		endofWord = false;
		height = findHeight(t, 0);
	}
	
	private static int findHeight(Trie t, int currentHeight) {
		int maxHeight = currentHeight; //assume the maximum height is the current height of the object
		for(int x = 0; x < 26; x++) {
			if(t.subtries[x] == null) continue; 
			int subHeight = findHeight(t.subtries[x], currentHeight+1); //the height of this subtrie;
			if(subHeight > maxHeight) maxHeight = subHeight;
		}
		//if we've reached the maximum height allowed by this trie, return this value
		return maxHeight;
	}
	
	public String findWord(int length) {//finds the first word of length length, returns null otherwise
		//TODO implement method
		return "";
	}
	
	
	
	
	public void insert(String s) { //inserts String into root trie;
		s = s.toUpperCase();
		Trie root = this;
		for(int x = 0; x < s.length(); x++) {
			if(root.subtries[s.charAt(x)-'A'] == null) {
				root.subtries[s.charAt(x)-'A'] = new Trie(root);
				
			}
			root = root.subtries[s.charAt(x)- 'A']; //go down one path
		}
		
		root.endofWord = true; //mark it as the end of the word	
			
		
		
	}
	
	public boolean search(String s) {// returns true if the String is found
		s = s.toUpperCase();
		Trie root = this;
		for(int x = 0; x < s.length(); x++) {
			if(root.subtries[s.charAt(x)-'A'] == null) {
				return false;
			}
			root = root.subtries[s.charAt(x)- 'A'];
			
		}
		return root.endofWord;
	}
	
	
	
	
	public static void main(String[] args) { //testing function- will delete later
		Trie t = new Trie();
		t.insert("HELLO");
		t.insert("a");
		t.insert("goodbye");
		System.out.println(t.search("hello"));
		System.out.println(t.search("goodbye"));
		System.out.println(findHeight(t, 0));
		System.out.println(findHeight(t.subtries[7], 1));
		
	}
	
}