package de.tu_darmstadt.informatik.skorvan.exercises.map;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.AfterClass;
import org.junit.Test;

public class wordAppendTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"x", "y", "x"};
		String act = nber.wordAppend(arg);
		String exp = "x";
		boolean b = act==(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test2() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"x", "b", "x", "c", "x", "d", "x"};
		String act = nber.wordAppend(arg);
		String exp = "xx";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test3() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"x", "y", "x"};
		String act = nber.wordAppend(arg);
		String exp = "x";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test4() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"w", "", "w"};
		String act = nber.wordAppend(arg);
		String exp = "w";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test5() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{};
		String act = nber.wordAppend(arg);
		String exp = "";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test6() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a", "b", "b", "a", "a"};
		String act = nber.wordAppend(arg);
		String exp = "ba";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test7() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a", "b", "b", "b", "a", "c", "a", "a"};
		String act = nber.wordAppend(arg);
		String exp = "baa";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test8() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a", "b", "b", "b", "a", "c", "a", "a", "a", "b", "a"};
		String act = nber.wordAppend(arg);
		String exp = "baaba";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test9() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"x", "y", "z"};
		String act = nber.wordAppend(arg);
		String exp = "";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test10() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"xx", "xx", "yy", "xx", "zz", "yy", "zz", "xx"};
		String act = nber.wordAppend(arg);
		String exp = "xxyyzzxx";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test11() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"this", "or", "that", "and", "this", "and", "that"};
		String act = nber.wordAppend(arg);
		String exp = "thisandthat";
		boolean b = act.equals(exp);
		feedLinkedlist("wordAppend(\""+Arrays.toString(arg)+"\")->"+exp, act, b+"");
		assertEquals("<wordAppend(\""+ Arrays.toString(arg)+"\")>", exp, act);
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
