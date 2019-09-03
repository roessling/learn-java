package de.tu_darmstadt.informatik.skorvan.exercises.map;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class wordCountTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a", "b", "a", "b"};
		Map<String, Integer> act = nber.wordCount(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("a", 2);exp.put("b", 2);
		boolean b = act.equals(exp);
		feedLinkedlist("wordCount(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordCount(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test2() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{};
		Map<String, Integer> act = nber.wordCount(arg);
		Map<String, Integer> exp = new HashMap<>();

		boolean b = act.equals(exp);
		feedLinkedlist("wordCount(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordCount(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test3() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"x", "y", "z"};
		Map<String, Integer> act = nber.wordCount(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("x", 1);exp.put("y", 1);exp.put("z", 1);
		boolean b = act.equals(exp);
		feedLinkedlist("wordCount(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordCount(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test4() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"100", "305", "100"};
		Map<String, Integer> act = nber.wordCount(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("100", 2);exp.put("305", 1);
		boolean b = act.equals(exp);
		feedLinkedlist("wordCount(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordCount(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test5() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"W","W","W","W","W"};
		Map<String, Integer> act = nber.wordCount(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("W", 5);
		boolean b = act.equals(exp);
		feedLinkedlist("wordCount(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordCount(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test6() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"aber", "", "bob", "aber"};
		Map<String, Integer> act = nber.wordCount(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("aber", 2);exp.put("bob", 1);exp.put("", 1);
		boolean b = act.equals(exp);
		feedLinkedlist("wordCount(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordCount(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test7() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a", "b", "a", "z", "a"};
		Map<String, Integer> act = nber.wordCount(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("b", 1);exp.put("a", 3);exp.put("z", 1);
		boolean b = act.equals(exp);
		feedLinkedlist("wordCount(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordCount(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	
	
	@AfterClass
	public static void ShowResult(){
		saveTestValues();
		stdoutHistory.stream().forEach(System.out::println);
	}

    private static void saveTestValues(){
    	try {
    		String tempDir = System.getProperty("java.io.tmpdir");
            File logfile = new File(tempDir + "/data.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(logfile));
			for(String str: stdoutHistory){
				bw.write(str);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
