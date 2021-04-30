import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Using some code from a textbook of Algorithms and Data Structures I
 * Changes a little bit
 */
public class TST {
	private static int n; // size
	private static Node<String> root; // root of TST
	// All the loading stop data
	private static HashMap<String, String> map = new HashMap<String, String>();
	// All the stop names
	private static List<String> list = new ArrayList<String>();
	private static boolean source = false;

	public static TST buildTST() {
		TST t = new TST();

		DataResolver dataResolver = new DataResolver();
		try {
			list = dataResolver.resolveDataFrom("F:\\JavaData\\stops.txt");
			for (String lineData : list) {
				String partLine[] = lineData.split(",");
				String code[] = partLine[0].split("= ");
				String name[] = partLine[2].split("= ");
				put(name[1], code[1]);
				map.put(code[1], lineData);
			}

		} catch (Exception e) {
			System.out.println("Something went wrong.");
			source = true;
		}
		return t;
	}

	private static class Node<Value> {
		private char c; // character
		private Node<Value> left, mid, right; // left, middle, and right subtries
		private Value val; // value associated with string
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public static int size() {
		return n;
	}

	/**
	 * Does this symbol table contain the given key?
	 * 
	 * @param key the key
	 * @return {@code true} if this symbol table contains {@code key} and
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public static boolean contains(String key) {
		if (key == null) {
			throw new IllegalArgumentException("argument to contains() is null");
		}
		return get(key) != null;
	}

	/**
	 * Returns the value associated with the given key.
	 * 
	 * @param key the key
	 * @return the value associated with the given key if the key is in the symbol
	 *         table and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public static String get(String key) {
		if (key == null) {
			throw new IllegalArgumentException("calls get() with null argument");
		}
		if (key.length() == 0)
			throw new IllegalArgumentException("key must have length >= 1");
		Node<String> x = get(root, key, 0);
		if (x == null)
			return null;
		return x.val;
	}

	// return subtrie corresponding to given key
	private static Node<String> get(Node<String> x, String key, int d) {
		if (x == null)
			return null;
		if (key.length() == 0)
			throw new IllegalArgumentException("key must have length >= 1");
		char c = key.charAt(d);
		if (c < x.c)
			return get(x.left, key, d);
		else if (c > x.c)
			return get(x.right, key, d);
		else if (d < key.length() - 1)
			return get(x.mid, key, d + 1);
		else
			return x;
	}

	/**
	 * Inserts the key-value pair into the symbol table, overwriting the old value
	 * with the new value if the key is already in the symbol table. If the value is
	 * {@code null}, this effectively deletes the key from the symbol table.
	 * 
	 * @param key the key
	 * @param val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public static void put(String key, String val) {
		if (key == null) {
			throw new IllegalArgumentException("calls put() with null key");
		}
		if (!contains(key))
			n++;
		else if (val == null)
			n--; // delete existing key
		root = put(root, key, val, 0);
	}

	private static Node<String> put(Node<String> x, String key, String val, int d) {
		char c = key.charAt(d);
		if (x == null) {
			x = new Node<String>();
			x.c = c;
		}
		if (c < x.c)
			x.left = put(x.left, key, val, d);
		else if (c > x.c)
			x.right = put(x.right, key, val, d);
		else if (d < key.length() - 1)
			x.mid = put(x.mid, key, val, d + 1);
		else
			x.val = val;
		return x;
	}

	/**
	 * Returns all of the keys in the set that start with {@code prefix}.
	 * 
	 * @param prefix the prefix
	 * @return all of the keys in the set that start with {@code prefix}, as an
	 *         iterable
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 */
	public Iterable<String> keysWithPrefix(String prefix) {
		if (prefix == null) {
			throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
		}
		Queue<String> queue = new LinkedList<String>();
		Node<String> x = get(root, prefix, 0);
		if (x == null)
			return queue;
		if (x.val != null)
			queue.add(prefix);
		collect(x.mid, new StringBuilder(prefix), queue);
		return queue;
	}

	// all keys in subtrie rooted at x with given prefix
	private void collect(Node<String> x, StringBuilder prefix, Queue<String> queue) {
		if (x == null)
			return;
		collect(x.left, prefix, queue);
		if (x.val != null)
			queue.add(prefix.toString() + x.c);
		collect(x.mid, prefix.append(x.c), queue);
		prefix.deleteCharAt(prefix.length() - 1);
		collect(x.right, prefix, queue);
	}

	/**
	 * Returns all of the keys in the symbol table that match {@code pattern}, where
	 * . symbol is treated as a wildcard character.
	 * 
	 * @param pattern the pattern
	 * @return all of the keys in the symbol table that match {@code pattern}, as an
	 *         iterable, where . is treated as a wildcard character.
	 */
	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> queue = new LinkedList<String>();
		collect(root, new StringBuilder(), 0, pattern, queue);
		return queue;
	}

	private void collect(Node<String> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
		if (x == null)
			return;
		char c = pattern.charAt(i);
		if (c == '.' || c < x.c)
			collect(x.left, prefix, i, pattern, queue);
		if (c == '.' || c == x.c) {
			if (i == pattern.length() - 1 && x.val != null)
				queue.add(prefix.toString() + x.c);
			if (i < pattern.length() - 1) {
				collect(x.mid, prefix.append(x.c), i + 1, pattern, queue);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}
		if (c == '.' || c > x.c)
			collect(x.right, prefix, i, pattern, queue);
	}

	public List<String> find(String keywords) {
		List<String> list = new ArrayList<>();
		Iterable<String> allNames = keysWithPrefix(keywords);
		for (String names : allNames) {
			list.add(map.get(get(names)));
		}
		return list;
	}

	public static void main(String[] args) {
		TST t = TST.buildTST();
		if (!source) {
			String stopName = "EAGLECLIFF RD AT BAKER RD";
			List<String> stops = t.find(stopName);
			if (stops.size() == 0) {
				System.out.println("Wrong input!");
			} else {
				for (String allData : stops) {
					System.out.println(allData);
				}
				System.out.println("Total stop(s)= " + stops.size());
			}
		}
	}
}