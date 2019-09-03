package de.tu_darmstadt.informatik.skorvan.exercises.map;

import java.util.HashMap;
import java.util.Map;

public class MapClass {

	public Map<String, Integer> word0(String[] strings) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String s : strings) {
			map.put(s, 0);
		}
		return map;
	}

	public Map<String, Integer> wordLen(String[] strings) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String s : strings) {
			map.put(s, s.length());
		}
		return map;
	}

	public Map<String, String> pairs(String[] strings) {
		Map<String, String> map = new HashMap<String, String>();
		for (String s : strings) {
			map.put((s.substring(0, 1)), s.substring(s.length() - 1));
		}
		return map;
	}

	public Map<String, Integer> wordCount(String[] strings) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String s : strings) {

			if (!map.containsKey(s)) {
				map.put(s, 1);
			} else {
				int count = map.get(s);
				map.put(s, count + 1);
			}
		}
		return map;
	}

	public Map<String, String> firstChar(String[] strings) {
		Map<String, String> map = new HashMap<String, String>();
		for (String s : strings) {
			if (!map.containsKey(s.substring(0, 1))) {
				map.put(s.substring(0, 1), s);

			} else {
				String existing = map.get(s.substring(0, 1));
				map.put(s.substring(0, 1), existing + s);
			}

		}
		return map;
	}

	public String wordAppend(String[] strings) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String result = "";
		for (int i = 0; i < strings.length; i++) {
			String s = strings[i];
			if (!map.containsKey(s)) {
				map.put(s, 1);
			} else {
				int count = map.get(s);
				map.put(s, count + 1);
				if (map.get(s) != 1 && map.get(s) % 2 == 0) {
					result += s;
				}
			}

		}
		return result;
	}

	public Map<String, Boolean> wordMultiple(String[] strings) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (String s : strings) {

			if (!map.containsKey(s)) {
				map.put(s, false);
			} else {
				map.put(s, true);
			}
		}
		return map;
	}

	public String[] allSwap(String[] strings) {
		String[] result = new String[strings.length];
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < strings.length; i++) {
			char c = strings[i].charAt(0);
			if (map.containsKey(c)) {
				int p = map.get(c);
				map.remove(c);
				;
				result[i] = result[p];
				result[p] = strings[i];
			} else {
				result[i] = strings[i];
				map.put(c, i);
			}
		}
		return result;
	}

	public String[] firstSwap(String[] strings) {
		String[] result = new String[strings.length];
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < strings.length; i++) {
			char c = strings[i].charAt(0);
			if (map.containsKey(c) && map.get(c) >= 0) {
				int p = map.get(c);
				map.put(c, -1);

				result[i] = result[p];
				result[p] = strings[i];
			} else {
				result[i] = strings[i];
				if (!map.containsKey(c))
					map.put(c, i);
			}
		}
		return result;
	}

}
