package de.tu_darmstadt.informatik.skorvan.exercises.map;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class firstSwapTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	public static MapClass nber;
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() {
		nber = new MapClass();
	}
	
	@Test
	public void test1() {
		String [] arg = new String[]{"x", "y", "x"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"x", "y", "x"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test2() {
		String [] arg = new String[]{};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test3() {
		String [] arg = new String[]{"www"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"www"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test4() {
		String [] arg = new String[]{"x", "y", "z", "aa", "dd", "oo"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"x", "y", "z", "aa", "dd", "oo"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test5() {
		String [] arg = new String[]{"5", "4", "21", "22", "23", "54"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"54", "4", "22", "21", "23", "5"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test6() {
		String [] arg = new String[]{"ab", "ac"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"ac", "ab"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test7() {
		String [] arg = new String[]{"egal", "dose", "gdi", "echo", "gdp", "engel"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"echo", "dose", "gdp", "egal", "gdi", "engel"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test8() {
		String [] arg = new String[]{"ax", "bx", "ay", "by", "ai", "aj", "bx", "by"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"ay", "by", "ax", "bx", "ai", "aj", "bx", "by"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test9() {
		String [] arg = new String[]{"ax", "bx", "cx", "cy", "by", "ay", "aaa", "azz"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"ay", "by", "cy", "cx", "bx", "ax", "aaa", "azz"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test10() {
		String [] arg = new String[]{"list", "of", "words", "swims", "over", "lily", "water", "wait"};
		String [] act = nber.firstSwap(arg);
		String [] exp = new String[]{"lily", "over", "water", "swims", "of", "list", "words", "wait"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("firstSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		Assert.assertArrayEquals("<firstSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
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
