import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main2 {

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
				if(c == ' ' || c == '\n') {//if its a space
					wordlist[words] = currentword;
					words++;
					//System.out.println(currentword);
					currentword = "";

				}
				else {
					currentword += (char) c + "";
				}
			}
		}

		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		System.out.println("Loaded " + words + " words into the dictionary.");
		//System.out.println("Updating heights...");
		int length = 6;
		int lengthwords = 0;
		System.out.println("Finding "  + length + " letter words");
		
		for(int x = 0; x< wordlist.length; x++) {
			if(wordlist[x].length() == length) {
				lengthwords++;
			}
		}
		
		System.out.println("Total " +  length  + " letter words in dictionary: "  + lengthwords);
			
	}
	
	private Grid solve(String[] wordlist, Grid grid) {
		return null;
	}

}