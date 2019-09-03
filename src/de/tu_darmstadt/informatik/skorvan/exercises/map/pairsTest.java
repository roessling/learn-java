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

public class pairsTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a", "b"};
		Map<String, String> act = nber.pairs(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("a", "a");exp.put("b", "b");
		boolean b = act.equals(exp);
		feedLinkedlist("pairs(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<pairs(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test2() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"aber", "bitte", "darmstadt", "boom"};
		Map<String, String> act = nber.pairs(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("a", "r");exp.put("b", "m");exp.put("d", "t");
		boolean b = act.equals(exp);
		feedLinkedlist("pairs(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<pairs(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test3() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"sule", "soule", "suele"};
		Map<String, String> act = nber.pairs(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("s", "e");
		boolean b = act.equals(exp);
		feedLinkedlist("pairs(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<pairs(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test4() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{};
		Map<String, String> act = nber.pairs(arg);
		Map<String, String> exp = new HashMap<>();

		boolean b = act.equals(exp);
		feedLinkedlist("pairs(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<pairs(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test5() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"bis", "king", "beratung", "kohle"};
		Map<String, String> act = nber.pairs(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("b", "g");exp.put("k", "e");
		boolean b = act.equals(exp);
		feedLinkedlist("pairs(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<pairs(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test6() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"chris", "matze", "jeff", "baongla"};
		Map<String, String> act = nber.pairs(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("c", "s");exp.put("m", "e");exp.put("j", "f");exp.put("b", "a");
		boolean b = act.equals(exp);
		feedLinkedlist("pairs(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<pairs(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test7() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"gabun", "douala"};
		Map<String, String> act = nber.pairs(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("g", "n");exp.put("d", "a");
		boolean b = act.equals(exp);
		feedLinkedlist("pairs(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<pairs(\""+ Arrays.toString(arg)+"\")>", exp, act);
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
