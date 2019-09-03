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

public class noXTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}

	@Test
	public void test1() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("x");
		List<String> act = nber.noX(arg);
		List<String> exp = Arrays.asList("");
		boolean b = act.equals(exp);
		feedLinkedlist("noX(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<noX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("");
		List<String> act = nber.noX(arg);
		List<String> exp = Arrays.asList("");
		boolean b = act.equals(exp);
		feedLinkedlist("noX(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<noX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList();
		List<String> act = nber.noX(arg);
		List<String> exp = Arrays.asList();
		boolean b = act.equals(exp);
		feedLinkedlist("noX(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<noX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("Der", "The");
		List<String> act = nber.noX(arg);
		List<String> exp = Arrays.asList("Der", "The");
		boolean b = act.equals(exp);
		feedLinkedlist("noX(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<noX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("citxy", "flixtzer", "xtuxx");
		List<String> act = nber.noX(arg);
		List<String> exp = Arrays.asList("city", "flitzer","tu");
		boolean b = act.equals(exp);
		feedLinkedlist("noX(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<noX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("CMR", "CIV", "DE", "FRA");
		List<String> act = nber.noX(arg);
		List<String> exp = Arrays.asList("CMR", "CIV", "DE", "FRA");
		boolean b = act.equals(exp);
		feedLinkedlist("noX(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<noX(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("zx", "yx", "xx", "tx");
		List<String> act = nber.noX(arg);
		List<String> exp = Arrays.asList("z", "y", "", "t");
		boolean b = act.equals(exp);
		feedLinkedlist("noX(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<noX(\""+arg+"\")>", exp, act);
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
