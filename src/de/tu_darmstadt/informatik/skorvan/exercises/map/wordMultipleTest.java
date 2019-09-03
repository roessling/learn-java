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

public class wordMultipleTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"s","t","t","s"};
		Map<String, Boolean> act = nber.wordMultiple(arg);
		Map<String, Boolean> exp = new HashMap<>();
		exp.put("s", true);exp.put("t", true);
		boolean b = act.equals(exp);
		feedLinkedlist("wordMultiple(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordMultiple(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test2() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{};
		Map<String, Boolean> act = nber.wordMultiple(arg);
		Map<String, Boolean> exp = new HashMap<>();

		boolean b = act.equals(exp);
		feedLinkedlist("wordMultiple(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordMultiple(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test3() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"fop","fop","fop","fop","fop"};
		Map<String, Boolean> act = nber.wordMultiple(arg);
		Map<String, Boolean> exp = new HashMap<>();
		exp.put("fop", true);
		boolean b = act.equals(exp);
		feedLinkedlist("wordMultiple(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordMultiple(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test4() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a","b","c","d"};
		Map<String, Boolean> act = nber.wordMultiple(arg);
		Map<String, Boolean> exp = new HashMap<>();
		exp.put("a", false);exp.put("b", false);exp.put("c", false);exp.put("d", false);
		boolean b = act.equals(exp);
		feedLinkedlist("wordMultiple(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordMultiple(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test5() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"wo","bist","du","?","?"};
		Map<String, Boolean> act = nber.wordMultiple(arg);
		Map<String, Boolean> exp = new HashMap<>();
		exp.put("wo", false);exp.put("bist", false);exp.put("du", false);exp.put("?", true);
		boolean b = act.equals(exp);
		feedLinkedlist("wordMultiple(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<wordMultiple(\""+ Arrays.toString(arg)+"\")>", exp, act);
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
