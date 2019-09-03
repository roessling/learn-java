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
import org.junit.Test;

public class wordLenTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a", "b", "a", "b"};
		Map<String, Integer> act = nber.wordLen(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("a", 1);exp.put("b", 1);
		boolean b = act.equals(exp);
		feedLinkedlist("wordLen(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordLen(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test2() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{};
		Map<String, Integer> act = nber.wordLen(arg);
		Map<String, Integer> exp = new HashMap<>();

		boolean b = act.equals(exp);
		feedLinkedlist("wordLen(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordLen(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test3() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"K"};
		Map<String, Integer> act = nber.wordLen(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("K", 1);
		boolean b = act.equals(exp);
		feedLinkedlist("wordLen(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordLen(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test4() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"aber", "bob", "alice", "aber"};
		Map<String, Integer> act = nber.wordLen(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("aber", 4);exp.put("bob", 3);exp.put("alice", 5);
		boolean b = act.equals(exp);
		feedLinkedlist("wordLen(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordLen(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test5() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"java", "b", "java", "java"};
		Map<String, Integer> act = nber.wordLen(arg);
		Map<String, Integer> exp = new HashMap<>();
		exp.put("java", 4);exp.put("b", 1);
		boolean b = act.equals(exp);
		feedLinkedlist("wordLen(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordLen(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}

	
	@AfterClass
	public static void ShowResult(){
		saveTestValues();
//		stdoutHistory.stream().forEach(System.out::println);
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
