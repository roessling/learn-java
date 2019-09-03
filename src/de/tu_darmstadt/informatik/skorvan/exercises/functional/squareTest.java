package de.tu_darmstadt.informatik.skorvan.exercises.functional;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

public class squareTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(1,2,3);
		List<Integer> act = nber.square(arg);
		List<Integer> exp = Arrays.asList(1,4,9);
		boolean b = act.equals(exp);
		feedLinkedlist("square(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<square(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(6, 8, -6, -8, 1);
		List<Integer> act = nber.square(arg);
		List<Integer> exp = Arrays.asList(36, 64, 36, 64, 1);
		boolean b = act.equals(exp);
		feedLinkedlist("square(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<square(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList();
		List<Integer> act = nber.square(arg);
		List<Integer> exp = Arrays.asList();
		boolean b = act.equals(exp);
		feedLinkedlist("square(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<square(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(0);
		List<Integer> act = nber.square(arg);
		List<Integer> exp = Arrays.asList(0);
		boolean b = act.equals(exp);
		feedLinkedlist("square(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<square(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(15,-15);
		List<Integer> act = nber.square(arg);
		List<Integer> exp = Arrays.asList(225,225);
		boolean b = act.equals(exp);
		feedLinkedlist("square(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<square(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(-1,0,-10);
		List<Integer> act = nber.square(arg);
		List<Integer> exp = Arrays.asList(1,0,100);
		boolean b = act.equals(exp);
		feedLinkedlist("square(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<square(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(6, -3, 12, 23, 4, 1, 19, 11, 2, 3, 2);
		List<Integer> act = nber.square(arg);
		List<Integer> exp = Arrays.asList(36, 9, 144, 529, 16, 1, 361, 121, 4, 9, 4);
		boolean b = act.equals(exp);
		feedLinkedlist("square(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<square(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test8() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(1);
		List<Integer> act = nber.square(arg);
		List<Integer> exp = Arrays.asList(1);
		boolean b = act.equals(exp);
		feedLinkedlist("square(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<square(\""+arg+"\")>", exp, act);
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
			System.out.println("PAth of the created file: "+logfile.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
