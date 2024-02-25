import java.util.*;
import static java.lang.System.out;
import java.io.*;

public class WordLadder {
	private Set<String> dictionary;
	private Set<String> used;
	public ArrayList<String> dict = new ArrayList<String>();
	public int size;

	public static void main(String args[]) throws IOException {
		WordLadder ladder = new WordLadder();
		@SuppressWarnings("resource")
		Scanner console = new Scanner(new File("/Users/saimanasa/eclipse-workspace/Lab06-WordLadder/src/input.txt"));
		while (console.hasNext()) {
			String str = ladder.setWords(console.next(), console.next());
			out.println(str);
		}
	}

	public  WordLadder() {
		dictionary = new HashSet<>();
		used = new HashSet<>();
		size = dict.size();
		Scanner console = null;
		try {
			console = new Scanner(new File("/Users/saimanasa/eclipse-workspace/Lab06-WordLadder/src/dictionary.txt"));
		}
		catch (IOException e) {
			System.out.println("Can't find the file!");
		}

		while (console.hasNextLine()) {
			String next = console.nextLine().toLowerCase();
			dictionary.add(next);
			dict.add(next);
		}
	}

	public String setWords(String input, String output) {
		used = new HashSet<>();
		Queue<Stack<String>> run = new LinkedList<>();
		Stack<String> words = new Stack<>();
		words.push(input);
		run.offer(words);

		while (run.isEmpty() == false) {
			if (input.length() == output.length()) {
				Stack<String>  fin = run.poll();
				String check = fin.peek();
				if (check.equals(output)) {
					return "Found a ladder! >>> " + fin.toString();
				}
				if (dictionary.contains(input) == false || dictionary.contains(output) == false) {
					return "No ladder between " + input + " and " + output;
				}  
				String wordNew;
				for (int i = 0; i < check.length(); i++) {
					String beg = check.substring(0, i);
					String o = check.substring(i+1); 
					for (char ch = 'a'; ch <= 'z'; ch++) {
						wordNew = beg + ch + o;
						if (dictionary.contains(wordNew) && (used.contains(wordNew) == false)) {
							used.add(wordNew);
							@SuppressWarnings("unchecked")
							Stack<String> stackCopy = (Stack<String>) fin.clone();
							stackCopy.push(wordNew);                         
							run.offer(stackCopy);
						} 
					}
				} 

				/*
				for (int i = 0; i < dict.size(); i++) {
					String newWord = dict.get(i);
					if (isNeighbor(check, newWord) == true && used.contains(newWord) == false) {
						used.add(newWord);
						@SuppressWarnings("unchecked")
						Stack<String> stackCopy = (Stack<String>) fin.clone();
						stackCopy.push(newWord);                         
						run.offer(stackCopy);
					}
				}
				 */

			} else {
				return "No ladder between " + input + " and " + output;
			}
		}
		return "No ladder between " + input + " and " + output;
	}

	/*
	public static boolean isNeighbor(String one, String two) {
		int dif = 0;
		if (one.length() != two.length() ) {
			return false;
		}
		for (int i = 0; i < one.length(); i++) {
			if (one.charAt(i) != two.charAt(i)) {
				dif++;
			}
		}
		if (dif > 1) {
			return false;
		} else {
			return true;
		}
	}
	 */
}