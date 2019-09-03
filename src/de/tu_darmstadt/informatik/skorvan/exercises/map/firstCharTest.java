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

public class firstCharTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{};
		Map<String, String> act = nber.firstChar(arg);
		Map<String, String> exp = new HashMap<>();
//		exp.put("s", "saltsoda");exp.put("t", "teatoast");
		boolean b = act.equals(exp);
		feedLinkedlist("firstChar(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<firstChar(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test2() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"gdi", "fop", "eise", "it"};
		Map<String, String> act = nber.firstChar(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("g", "gdi");exp.put("f", "fop");exp.put("e", "eise");exp.put("i", "it");
		boolean b = act.equals(exp);
		feedLinkedlist("firstChar(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<firstChar(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test3() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"salt", "tea", "soda", "toast"};
		Map<String, String> act = nber.firstChar(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("s", "saltsoda");exp.put("t", "teatoast");
		boolean b = act.equals(exp);
		feedLinkedlist("firstChar(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<firstChar(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test4() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"bing", "bang", "Yang", "Ying", "qwertz"};
		Map<String, String> act = nber.firstChar(arg);
		Map<String, String> exp = new HashMap<>();
		exp.put("b", "bingbang");exp.put("Y", "YangYing");exp.put("q", "qwertz");
		boolean b = act.equals(exp);
		feedLinkedlist("firstChar(\""+Arrays.toString(arg)+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<firstChar(\""+ Arrays.toString(arg)+"\")>", exp, act);
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
