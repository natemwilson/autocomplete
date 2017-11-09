import java.util.*;

public class Term implements Comparable<Term> {
    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            @Override
            public int compare(Term a, Term b) {
                long x = b.weight;
                long y = a.weight;
                return (int) (x - y);
            }
        };
    }

    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new Comparator<Term>() {
            @Override
            public int compare(Term a, Term b) {
                String sub_a;
                if (a.query.length() >= r) 
                    sub_a = a.query.substring(0, r);
                else
                    sub_a = a.query;
                String sub_b;
                if (b.query.length() >= r) 
                    sub_b = b.query.substring(0, r);
                else
                    sub_b = b.query;
                return sub_a.compareTo(sub_b);
            }
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return this.weight + "\t" + this.query;
    }

    // unit testing (required)
    public static void main(String[] args) {

        Term t1 = new Term("hello", 10);
        Term t2 = new Term("hey", 5);
        Term t3 = new Term("heck", 1);
        Term t4 = new Term("horses", 20);
        Term t5 = new Term("apple", 10);

        Term[] t = {t1, t2, t3, t4, t5};

        Arrays.sort(t, Term.byPrefixOrder(2));

        for (Term i : t) System.out.println(i);

    }
}