import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * An anagram solver that will return all of the anagrams of 
 * a given word. It does this by assigning each letter to a prime so that every
 * anagram has a very specific numerical value given by the product of its prime numbers.
 */
public class Anagram {

	HashMap<Integer, ArrayList<String>> anagramDict = new HashMap<Integer, ArrayList<String>>();
	HashSet<String> dictionary = new HashSet<String>();
	int[] primes = new int[26];
	
	public Anagram() {
		Arrays.fill(primes, 0);
		primes = fillPrimes();
		
		try {
			importDict();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// fill an array with 26 prime numbers
	private int[] fillPrimes() {
		
		int tester = 1;
		
		for(int i = 0; i < 26; i++) {
			while(!isPrime(tester)){
				tester++;
			}
			primes[i] = tester;
			tester++;
		}
		
		return primes;
	}
	
	// Check whether a number is prime
	private boolean isPrime(long n) {
	    if(n < 2) {
	    	return false;
	    }
	    else if(n == 2 || n == 3) {
	    	return true;
	    }
	    else if(n%2 == 0 || n%3 == 0) {
	    	return false;
	    }
	    long sqrtN = (long)Math.sqrt(n)+1;
	    for(long i = 6L; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) {
	        	return false;
	        }
	    }
	    return true;
	}

	// Import the dictionary and store it in a hashmap
	private void importDict() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\EtaiS\\workspace\\Anagram\\src\\dictionary.txt"));		
		try {
		    
			String line;
		    while ((line = br.readLine()) != null) {
		    	dictionary.add(line);
		    	int value = 1;
		    	
		        for(int i = 0; i < line.length(); i++){
		        	value *= primes[line.charAt(i) - 'a'];
		        }
		        
		        if(anagramDict.get(value) == null) {
		        	ArrayList<String> stringList = new ArrayList<String>();
		        	anagramDict.put(value, stringList);
		        }
		        anagramDict.get(value).add(line);
		    }
		    br.close();
		} 
		
		catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		}
		
		finally {
		    br.close();
		}
	}
	
	// Print all of the anagrams
	public void getAnagrams(String word) {
		if (isWord(word)) {
			int value = 1;
			for(int i = 0; i < word.length(); i++) {
				value *= primes[word.charAt(i) - 'a'];
			}
			try {
				ArrayList<String> wordList = anagramDict.get(value);
				for(int i = 0; i < wordList.size(); i++) {
					if(!(wordList.get(i).equals(word))) {
						System.out.print(wordList.get(i) + " ");
					}
				}
			}
			catch(Exception e) {
				System.out.println("Something went wrong!");
			}
		}
		else {
			System.out.println("That is not a word.");
		}
	}

	// Check to make sure the word is valid
	public boolean isWord(String word) {
		if(dictionary.contains(word)) {
			return true;
		}
		return false;
	}

	// Check which word has the most anagrams
	public String mostAnagrams() {
		Set<Integer> keys = anagramDict.keySet();
		int highest = 0;
		String highestString = ""; 
		int highestKey = 0;
		for(int k : keys) {
			int current = anagramDict.get(k).size();
			if(current > highest) {
				highest = current;
				highestString = anagramDict.get(k).get(0);
				highestKey = k;
			}
		}
		
		ArrayList<String> currentList = anagramDict.get(highestKey);
		for(String word : currentList) {
			System.out.println(word);
		}
		return highestString;
	}
}
