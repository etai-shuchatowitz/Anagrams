import java.util.Scanner;

public class AnagramTest {

	public static void main(String[] args) {
		Anagram anagram = new Anagram();
		boolean quit = false;
		System.out.println("Press q to quit.");
		while (!quit) {

			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.println("");
			System.out.println("Enter a word: ");
			String n = reader.next(); // Scans the next token of the input
			if(n.equals("q")){
				break;
			}
			else if(n.equals("mostanagrams")) {
				System.out.println(anagram.mostAnagrams());
			}
			n = n.trim().toLowerCase();
			
			anagram.getAnagrams(n);
		}
	}
}
