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

public class lowerTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(String exp, String act, String res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	@Test
	public void test1() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("Hi alle!");
		List<String> act = nber.lower(arg);
		List<String> exp = Arrays.asList("hi alle!");
		boolean b = act.equals(exp);
		feedLinkedlist("lower(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<lower(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test2() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("GRU-DRE","WSA");
		List<String> act = nber.lower(arg);
		List<String> exp = Arrays.asList("gru-dre","wsa");
		boolean b = act.equals(exp);
		feedLinkedlist("lower(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<lower(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test3() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("");
		List<String> act = nber.lower(arg);
		List<String> exp = Arrays.asList("");
		boolean b = act.equals(exp);
		feedLinkedlist("lower(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<lower(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test4() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("ZuSaMmEn");
		List<String> act = nber.lower(arg);
		List<String> exp = Arrays.asList("zusammen");
		boolean b = act.equals(exp);
		feedLinkedlist("lower(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<lower(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test5() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList();
		List<String> act = nber.lower(arg);
		List<String> exp = Arrays.asList();
		boolean b = act.equals(exp);
		feedLinkedlist("lower(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<lower(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test6() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("IcH","BiN","gUt"," okay");
		List<String> act = nber.lower(arg);
		List<String> exp = Arrays.asList("ich","bin","gut"," okay");
		boolean b = act.equals(exp);
		feedLinkedlist("lower(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<lower(\""+arg+"\")>", exp, act);
	}
	@Test
	public void test7() {
		FunctionalClass nber = new FunctionalClass();
		List<String> arg = Arrays.asList("IcH","BiN","gUt"," okay");
		List<String> act = nber.lower(arg);
		List<String> exp = Arrays.asList("ich","bin","gut"," okay");
		boolean b = act.equals(exp);
		feedLinkedlist("lower(\""+arg.toString()+"\")->"+exp.toString(), act.toString(), b+"");
		assertEquals("<lower(\""+arg+"\")>", exp, act);
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
