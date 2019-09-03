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

public class rightDigitTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(1,2,3);
		List<Integer> act = nber.rightDigit(arg);
		List<Integer> exp = Arrays.asList(1,2,3);
		boolean b = act.equals(exp);
		feedLinkedlist("rightDigit(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<rightDigit(\""+arg+"\")>", exp, act);
	}
	
	@Test
	public void test2() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(34,3,0);
		List<Integer> act = nber.rightDigit(arg);
		List<Integer> exp = Arrays.asList(4,3,0);
		boolean b = act.equals(exp);
		feedLinkedlist("rightDigit(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<rightDigit(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList();
		List<Integer> act = nber.rightDigit(arg);
		List<Integer> exp = Arrays.asList();
		boolean b = act.equals(exp);
		feedLinkedlist("rightDigit(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<rightDigit(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(0);
		List<Integer> act = nber.rightDigit(arg);
		List<Integer> exp = Arrays.asList(0);
		boolean b = act.equals(exp);
		feedLinkedlist("rightDigit(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<rightDigit(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(10,20,51,4,10);
		List<Integer> act = nber.rightDigit(arg);
		List<Integer> exp = Arrays.asList(0,0,1,4,0);
		boolean b = act.equals(exp);
		feedLinkedlist("rightDigit(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<rightDigit(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(9,8,7,6,5,4,3,2,1,0);
		List<Integer> act = nber.rightDigit(arg);
		List<Integer> exp = Arrays.asList(9,8,7,6,5,4,3,2,1,0);
		boolean b = act.equals(exp);
		feedLinkedlist("rightDigit(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<rightDigit(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		FunctionalClass nber = new FunctionalClass();
		List<Integer> arg = Arrays.asList(6, 3334, 12, 23, 4, 1, 19, 1123, 23, 31, 244,1000000);
		List<Integer> act = nber.rightDigit(arg);
		List<Integer> exp = Arrays.asList(6,4,2,3,4,1,9,3,3,1,4,0);
		boolean b = act.equals(exp);
		feedLinkedlist("rightDigit(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<rightDigit(\""+arg+"\")>", exp, act);
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
