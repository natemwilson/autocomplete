import java.util.*;

public class Autocomplete {
	Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
    	this.terms = terms;

    	Arrays.sort(this.terms);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
    	Term t = new Term(prefix, 0);
    	int front =
    	BinarySearchDeluxe.firstIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
    	int end =
    	BinarySearchDeluxe.lastIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));

    	if (front < 0) return new Term[0];

    	Term[] matches = Arrays.copyOfRange(terms, front, end);

    	Arrays.sort(matches, Term.byReverseWeightOrder());

    	return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
    	Term t = new Term(prefix, 0);
    	int front =
    	BinarySearchDeluxe.firstIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
    	int end =
    	BinarySearchDeluxe.lastIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));

    	return end - front;
    }

    // unit testing (required)
	public static void main(String[] args) {

    // read in the terms from a file
    String filename = args[0];
    In in = new In(filename);
    int n = in.readInt();
    Term[] terms = new Term[n];
    for (int i = 0; i < n; i++) {
        long weight = in.readLong();           // read the next weight
        in.readChar();                         // scan past the tab
        String query = in.readLine();          // read the next query
        terms[i] = new Term(query, weight);    // construct the term
    }

    // read in queries from standard input and print out the top k matching terms
    int k = Integer.parseInt(args[1]);
    Autocomplete autocomplete = new Autocomplete(terms);
    while (StdIn.hasNextLine()) {
        String prefix = StdIn.readLine();
        Term[] results = autocomplete.allMatches(prefix);
        StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
        for (int i = 0; i < Math.min(k, results.length); i++)
            StdOut.println(results[i]);
    }
}


}