package de.tu_darmstadt.informatik.skorvan.compile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;

import de.tu_darmstadt.informatik.skorvan.student.gui.ConsoleWriter;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 * This class can compile .java sources using the javax.tools.JavaCompiler,
 * providing error reports.
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class Compiler {

	public static final String SEP = "§";
	public static final String COMPILER = "compiler";
	public static final String CONFIG = "config";

	private static String classpath;

	/**
	 * set the classpath to be used for compiling
	 * 
	 * @param classpath
	 *            the classpath to be used for compiling
	 */
	public static void setClasspath(String classpath) {
		Compiler.classpath = classpath;
	}

	/**
	 * compile a test file, providing only the diagnostic error messages
	 * 
	 * @param file
	 *            the test file to be compiled
	 * @param output
	 *            the Consumer that receives diagnostic error messages
	 * @return true if the compilation was successful, false otherwise
	 */
	public static boolean compileTests(File file, Consumer<String> output) {
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(file);
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		List<String> options = new ArrayList<String>();
		if (classpath != null)
			options.addAll(Arrays.asList("-classpath", classpath, "-Xdiags:verbose"));
		CompilationTask task = jc.getTask(null, null, diagnostics, options, null, units);
		boolean res = task.call();
		for (Diagnostic<? extends JavaFileObject> dia : diagnostics.getDiagnostics()) {
			output.accept(dia.getMessage(Locale.ENGLISH));
		}

		return res;
	}

	/**
	 * compile a java source file, providing error messages along with line and
	 * column information that has been corrected to fit only the code written
	 * by users
	 * 
	 * Error reports are built as follows:
	 * 
	 * COMPILER + SEP + CONFIG: if there was no java compiler found in the used
	 * environment
	 * 
	 * COMPILER + SEP + (line) + SEP + (colstart) + SEP + (colend) + SEP +
	 * (message): a standard error report, containing the line number, start and
	 * end character of the error and the error message itself
	 * 
	 * @param file
	 *            the file to be compiled
	 * @param output
	 *            the consumer that receives constructed error messages
	 * @param lineOffset
	 *            the number of lines contained in the prefix of the framework,
	 *            that gets subtracted to calculate line numbers correct for the
	 *            written user code
	 * @param charOffset
	 *            the number of characters contained in the prefix of the
	 *            framework, used for calculating the correct character start
	 *            and end position
	 * @return true if the compilation was successful, false otherwise
	 */
	public static boolean compile(File file, Consumer<String> output, int lineOffset, int charOffset) {
		JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
		if (jc == null) {
			System.err.println("could not find java compiler. Version " + System.getProperty("java.version"));
			output.accept(COMPILER + SEP + CONFIG);
			return false;
		}
		StandardJavaFileManager fileManager = jc.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(file);
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		List<String> options = new ArrayList<String>();
		if (classpath != null)
			options.addAll(Arrays.asList("-classpath", classpath, "-Xdiags:verbose"));
		CompilationTask task = jc.getTask(new ConsoleWriter(output), null, diagnostics, options, null, units);
		boolean res = task.call();

		for (Diagnostic<? extends JavaFileObject> dia : diagnostics.getDiagnostics()) {
			long start = dia.getColumnNumber() - dia.getPosition() + dia.getStartPosition();
			if (start < 1)
				start = 1;
			long end = dia.getColumnNumber() - dia.getPosition() + dia.getEndPosition();
			output.accept(COMPILER + SEP + (dia.getLineNumber() - lineOffset) + SEP + start + SEP + end + SEP
					+ dia.getMessage(Locale.ENGLISH));
		}
		try {
			fileManager.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		output.accept("DONE");
		return res;
	}

}