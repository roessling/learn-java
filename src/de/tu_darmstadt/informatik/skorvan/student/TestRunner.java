package de.tu_darmstadt.informatik.skorvan.student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import de.tu_darmstadt.informatik.skorvan.compile.Compiler;
import de.tu_darmstadt.informatik.skorvan.exercises.UnitResultItem;
import de.tu_darmstadt.informatik.skorvan.student.gui.ShowTestResultGui;
import de.tu_darmstadt.informatik.skorvan.student.gui.StudentGui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestRunner {

	public static final String SEP = "§";

	public static final String TEST = "test";

	public static final String ERROR = "error";

	public static final String FAILURE = "failure";

	public static final String EXCEPTION = "exception";

	public static final String RESULT = "result";

	public static final String CLASSNOTFOUND = "classnotfound";

	public static final String SUCCESS = "success";

	public static boolean runTests(Consumer<String> output, Path testFile, String testedName, int lineOffset) {
		String packageName = testFile.getParent().getFileName().toString();
		String testFileName = testFile.getFileName().toString();

		// move test file over to not overwrite it
		try {
			Files.move(testFile, testFile.resolveSibling("temp"), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		// add package info and rest of original file to new file
		try (BufferedReader br = new BufferedReader(new FileReader(testFile.resolveSibling("temp").toFile()));
				PrintWriter pw = new PrintWriter(testFile.toFile())) {
			pw.println("package " + packageName + ";");
			while (br.ready()) {
				String line = br.readLine();
				if (line.endsWith("@Test"))
					line += "(timeout=1000)";
				if(line.contains("package"))
					continue;
				pw.println(line);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}

		Result result = null;

		// execute new file
		try {
			if (Compiler.compileTests(testFile.toFile(), comp -> output.accept(TEST + SEP + ERROR + SEP + comp))) {
				URLClassLoader classLoader = new URLClassLoader(
						new URL[] { testFile.getParent().getParent().toUri().toURL() });
				Class<?> c = Class.forName(
						packageName + "." + testFileName.replaceAll("\\.java", "").replace(File.separatorChar, '.'),
						true, classLoader);
				result = JUnitCore.runClasses(c);
			}
		} catch (MalformedURLException | ClassNotFoundException e) {
			System.err.println("TestClass not found: " + testFile.toString());
			System.err.println(e.getMessage());
			output.accept(TEST + SEP + ERROR + SEP + CLASSNOTFOUND);
		}

		if (result == null)
			return false;
		Print();
		return display(result, output, testedName, lineOffset);
	}
	/**
	 * the result from the unit test is edited here and added the oberservablelist so it can be displayed in the gui.
	 */
	public static void Print(){
		String tempDir = System.getProperty("java.io.tmpdir");
	      String fileName = tempDir+"data.txt";
		System.out.println("Gettting ready Read file");
		List<String> list = new ArrayList<>();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
			//br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String str: list){
			String[] parts = str.split("&");
			StudentGui.dataTestsOutput.add(new UnitResultItem(parts[0], parts[1], parts[2]));
			ShowTestResultGui.dataTestsOutput.add(new UnitResultItem(parts[0], parts[1], parts[2]));
		}
	}
	private static boolean display(Result result, Consumer<String> out, String testedName, int lineOffset) {
		if (result.wasSuccessful()) {
			out.accept(TEST + SEP + SUCCESS + SEP + result.getRunTime());
			return true;
		} else {
			out.accept(TEST + SEP + RESULT + SEP + result.getFailureCount() + SEP + result.getRunCount() + SEP
					+ result.getRunTime());
			result.getFailures().forEach(f -> {
				if (f.getException() instanceof AssertionError) {
					out.accept(TEST + SEP + FAILURE + SEP + f.getMessage());
				} else {
					String[] trace = f.getTrace().split("\\R");
					int line = -1;
					for (int i = 1; i < trace.length - 3; i++) {
						if (!(f.getException() instanceof StackOverflowError) || trace[i + 1].equals(trace[i])
								|| trace[i + 2].equals(trace[i]) || trace[i + 3].equals(trace[i])) { // in
																										// case
																										// of
																										// SOE,
																										// make
																										// sure
																										// the
																										// correct
																										// line
																										// is
																										// chosen
							if (trace[i].contains(testedName) && trace[i].matches(".*\\:\\d.*")) {
								line = Integer.parseInt(trace[i].substring(trace[i].indexOf(':'), trace[i].length())
										.replaceAll("\\D", "")) - lineOffset;
								break;
							}
						}
					}
					out.accept(TEST + SEP + EXCEPTION + SEP + line + SEP + f.getException().getClass().getSimpleName());
				}
			});
			return false;
		}
	}

}