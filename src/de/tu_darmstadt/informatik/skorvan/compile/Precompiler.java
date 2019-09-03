package de.tu_darmstadt.informatik.skorvan.compile;

import static de.tu_darmstadt.informatik.skorvan.util.Pattern.BRACE_C;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.BRACE_O;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.BRACKET_C;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.BRACKET_O;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.CHAR;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.COMMENT;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.LONG_CHAR;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.PAREN_C;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.PAREN_O;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.Q;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.QQ;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.STRING;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.IWF_NOPAREN;

import java.io.FileInputStream;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.Range;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.stmt.EmptyStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * This class analyzes source code using the JavaParser and pattern matching to
 * find typical programming mistakes
 * 
 * this class uses
 * JavaParser			https://github.com/javaparser
 * licensed under the Apache License Version 2.0
 * refer to http://www.apache.org/licenses/LICENSE-2.0 for a full copy
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class Precompiler {

	// separator to split parts of the message
	public static final String SEP = "$";
	// first part of the message to state its origin
	public static final String PRECOMPILER = "precomp";
	// JavaParser gave an Exception
	public static final String PARSING_EXCEPTION = "exception";
	// a char with more than one symbol in it was found
	public static final String CHAR_TOO_LONG = "longchar";
	// the braces {} are unbalanced
	public static final String BRACES = "braces";
	// the parentheses () are unbalanced
	public static final String PARENS = "parens";
	// the brackets [] are unbalanced
	public static final String BRACKETS = "brackets";
	// the number of double quotes " is uneven
	public static final String DOUBLE_QUOTE = "doublequote";
	// the number of single quotes ' is uneven
	public static final String SINGLE_QUOTE = "singlequote";
	// == might have been confused with =
	public static final String A_EQ_SYN = "a-eq";
	// Strings are compared using == instead of .equals
	public static final String B_STR_EQ = "b-str-eq";
	// bit-wise operators & | are used instead of && ||
	public static final String D_AND_OR = "d-and-or";
	// semicolon ; directly after if/for/while forming an empty body
	public static final String E_SMI_CON = "e-smi-con";
	// condition of if/for/while is not written in parentheses
	public static final String G_CUR_IF = "g-cur-if";

	// The constructed pattern to find all types of errors in one pass. The
	// sequence is crucial as some patterns stop others from being found if
	// placed before them.
	private static final Pattern PATTERN = Pattern
			.compile("(?<STRING>" + STRING + ")|(?<COMMENT>" + COMMENT + ")" + "|(?<CHAR>" + CHAR + ")|(?<LONGCHAR>"
					+ LONG_CHAR + ")" + "|(?<IWFNOPAREN>" + IWF_NOPAREN + ")" + "|(?<BRACEO>" + BRACE_O + ")|(?<BRACEC>"
					+ BRACE_C + ")" + "|(?<PARENO>" + PAREN_O + ")|(?<PARENC>" + PAREN_C + ")" + "|(?<BRACKETO>"
					+ BRACKET_O + ")|(?<BRACKETC>" + BRACKET_C + ")" + "|(?<QQ>" + QQ + ")|(?<Q>" + Q + ")");

	/**
	 * check given code for errors, reporting them to the given String consumer
	 * 
	 * @param code
	 *            the code written by the user, analyzed by using pattern
	 *            matching
	 * @param sourceFile
	 *            the source file for the complete code, analyzed using
	 *            JavaParser
	 * @param output
	 *            the consumer that receives error messages
	 */
	public static void checkForErrors(String code, String sourceFile, Consumer<String> output) {
		Matcher matcher = PATTERN.matcher(code);

		int braceo = 0, bracec = 0, pareno = 0, parenc = 0, bracko = 0, brackc = 0, dquo = 0, squo = 0;

		int matcherOverride = 0;

		for (int lastMatchEnd = 0; matcher
				.find(lastMatchEnd); lastMatchEnd = matcherOverride != 0 ? matcherOverride : matcher.end()) {
			matcherOverride = 0;
			if (matcher.group("STRING") != null)
				;
			else if (matcher.group("COMMENT") != null)
				;
			else if (matcher.group("CHAR") != null)
				;
			else if (matcher.group("LONGCHAR") != null)
				output.accept(PRECOMPILER + SEP + CHAR_TOO_LONG + SEP + matcher.start() + SEP + matcher.end());
			else if (matcher.group("BRACEO") != null)
				braceo++;
			else if (matcher.group("IWFNOPAREN") != null) {
				matcherOverride = matcher.end() - 1;
				output.accept(PRECOMPILER + SEP + G_CUR_IF + SEP + matcher.start() + SEP + matcher.end());
			} else if (matcher.group("BRACEC") != null)
				bracec++;
			else if (matcher.group("PARENO") != null)
				pareno++;
			else if (matcher.group("PARENC") != null)
				parenc++;
			else if (matcher.group("BRACKETO") != null)
				bracko++;
			else if (matcher.group("BRACKETC") != null)
				brackc++;
			else if (matcher.group("QQ") != null)
				dquo++;
			else if (matcher.group("Q") != null)
				squo++;
			else
				System.out.println(PRECOMPILER + ": unknown group: " + matcher.group());
		}

		try {
			FileInputStream in = new FileInputStream(sourceFile);
			CompilationUnit cu = JavaParser.parse(in);
			ExpressionResolver.init(sourceFile);
			cu.accept(new CustomVisitor(), output);
		} catch (ParseProblemException e) {
			output.accept(PRECOMPILER + SEP + PARSING_EXCEPTION + SEP + e.getMessage().split("\n")[0]);
		} catch (Exception e) {
			System.err.println("Precompiler exception: " + e.getMessage());
		}

		// count curly brackets, parentheses, square brackets, double and single
		// quotation marks
		if (braceo != bracec)
			output.accept(PRECOMPILER + SEP + BRACES + SEP + (braceo - bracec));
		if (pareno != parenc)
			output.accept(PRECOMPILER + SEP + PARENS + SEP + (pareno - parenc));
		if (bracko != brackc)
			output.accept(PRECOMPILER + SEP + BRACKETS + SEP + (bracko - brackc));
		if (dquo % 2 != 0)
			output.accept(PRECOMPILER + SEP + DOUBLE_QUOTE);
		if (squo % 2 != 0)
			output.accept(PRECOMPILER + SEP + SINGLE_QUOTE);
	}

	/**
	 * this class has methods for visiting nodes in the AST constructed by
	 * JavaParser
	 * 
	 * @author Jan Skorvan <jan@skorvan.de>
	 */
	private static class CustomVisitor extends VoidVisitorAdapter<Consumer<String>> {

		@Override
		public void visit(BinaryExpr n, Consumer<String> out) {
			// DETECT TYPE 3 ERROR (comparing Strings with == instead of equals)
			if (n.getOperator() == Operator.EQUALS) {
				if (ExpressionResolver.isString(n.getLeft()) && ExpressionResolver.isString(n.getRight())) {
					StringBuilder msg = new StringBuilder();
					msg.append(PRECOMPILER + SEP + B_STR_EQ);
					Optional<Range> range = n.getRange();
					if (range.isPresent()) {
						Range r = range.get();
						msg.append(SEP + r.begin.line + SEP + r.begin.column + SEP + r.end.line + SEP + r.end.column);
					} else {
						System.err.println("no range found for " + n.toString());
					}
					out.accept(msg.toString());
				}
			}
			// DETECT TYPE 9 ERROR (confusing bit-wise with short-circuit
			// operators
			else if ((n.getOperator() == Operator.BINARY_AND || n.getOperator() == Operator.BINARY_OR)
					&& ExpressionResolver.isBoolean(n.getRight()) && ExpressionResolver.isBoolean(n.getLeft())) {
				out.accept(PRECOMPILER + SEP + D_AND_OR + SEP + convRange(n.getRange().get()));
			}

			super.visit(n, out);
		}

		@Override
		public void visit(IfStmt n, Consumer<String> out) {
			// DETECT TYPE 5 ERROR (confusing assignment with comparison)
			if (n.getCondition() instanceof AssignExpr) {
				AssignExpr a = (AssignExpr) n.getCondition();
				// if (a.getOperator() == AssignExpr.Operator.ASSIGN) {
				out.accept(PRECOMPILER + SEP + A_EQ_SYN + SEP + convRange(a.getRange().get()));
				// }
			}
			// DETECT TYPE 8 ERROR (; after if condition)
			if (n.getThenStmt() instanceof EmptyStmt) {
				out.accept(
						PRECOMPILER + SEP + E_SMI_CON + SEP + "if" + SEP + convRange(n.getThenStmt().getRange().get()));
			}
			// DETECT TYPE 8 ERROR (; after else)
			if (n.getElseStmt().isPresent() && n.getElseStmt().get() instanceof EmptyStmt) {
				out.accept(PRECOMPILER + SEP + E_SMI_CON + SEP + "else" + SEP
						+ convRange(n.getElseStmt().get().getRange().get()));
			}
			super.visit(n, out);
		}

		@Override
		public void visit(WhileStmt n, Consumer<String> out) {
			// DETECT TYPE 5 ERROR (confusing assignment with comparison)
			if (n.getCondition() instanceof AssignExpr) {
				out.accept(PRECOMPILER + SEP + A_EQ_SYN + SEP + convRange(n.getCondition().getRange().get()));
			}
			// DETECT TYPE 8 ERROR (; after while condition)
			if (n.getBody() instanceof EmptyStmt) {
				out.accept(
						PRECOMPILER + SEP + E_SMI_CON + SEP + "while" + SEP + convRange(n.getBody().getRange().get()));
			}
			super.visit(n, out);
		}

		@Override
		public void visit(ForStmt n, Consumer<String> out) {
			// DETECT TYPE 8 ERROR (; after for statement)
			if (n.getBody() instanceof EmptyStmt) {
				out.accept(PRECOMPILER + SEP + E_SMI_CON + SEP + "for" + SEP + convRange(n.getBody().getRange().get()));
			}
			super.visit(n, out);
		}

		/**
		 * convert a Range to a String with nice formatting
		 * 
		 * @param r
		 *            the Range to be converted
		 * @return a String displaying the Range
		 */
		private String convRange(Range r) {
			int startLine = r.begin.line;
			int startCol = r.begin.column;
			int endLine = r.end.line;
			int endCol = r.end.column;
			return startLine + SEP + startCol + SEP + endLine + SEP + endCol;
		}

	}
}
