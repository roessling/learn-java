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

import junit.framework.Assert;

public class allSwapTest {

	public static LinkedList<String> stdoutHistory = new LinkedList<>();
	public final String SEP = "&";
	private void feedLinkedlist(Object exp, Object act, Object res){
		stdoutHistory.add(exp+SEP+act+SEP+res);
	}
	
	@Test
	public void test1() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"x", "y", "x"};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{"x", "y", "x"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test2() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"ab", "ac"};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{"ac", "ab"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
		
	}
	@Test
	public void test3() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test4() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"xxx"};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{"xxx"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test5() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"4", "8", "15", "16", "23", "42"};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{"42", "8", "16", "15", "23", "4"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test6() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"list", "of", "words", "swims", "over", "lily", "water", "wait"};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{"lily", "over", "water", "swims", "of", "list", "words", "wait"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test7() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"a", "b", "c", "xx", "yy", "zz"};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{"a", "b", "c", "xx", "yy", "zz"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	@Test
	public void test8() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"easy", "does", "it", "every", "ice", "eaten"};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{"every", "does", "ice", "easy", "it", "eaten"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
	}
	
	@Test
	public void test9() {
		MapClass nber = new MapClass();
		String [] arg = new String[]{"ax", "bx", "ay", "by", "ai", "aj", "bx", "by"};
		String [] act = nber.allSwap(arg);
		String [] exp = new String[]{"ay", "by", "ax", "bx", "aj", "ai", "by", "bx"};
		boolean b = Arrays.equals(act, exp);
		feedLinkedlist("allSwap (\""+Arrays.toString(arg)+"\")->"+Arrays.toString(exp), Arrays.toString(act), b+"");
		assertEquals("<allSwap (\""+ Arrays.toString(arg)+"\")>", exp, act);
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
