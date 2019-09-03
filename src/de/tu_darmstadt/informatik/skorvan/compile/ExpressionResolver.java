package de.tu_darmstadt.informatik.skorvan.compile;

import static com.github.javaparser.ast.expr.BinaryExpr.Operator.AND;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.EQUALS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.GREATER;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.GREATER_EQUALS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.LESS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.LESS_EQUALS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.NOT_EQUALS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.OR;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.PLUS;
import static com.github.javaparser.ast.expr.BinaryExpr.Operator.XOR;
import static com.github.javaparser.ast.expr.UnaryExpr.Operator.LOGICAL_COMPLEMENT;

import java.io.File;

import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.BinaryExpr.Operator;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.model.declarations.MethodDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.SymbolReference;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

/**
 * This class contains methods used by the Precompiler to determine if an
 * expression is of a certain type
 * 
 * this class uses
 * JavaParser			https://github.com/javaparser
 * JavaSymbolSolver		https://github.com/javaparser/javasymbolsolver
 * licensed under the Apache License Version 2.0
 * refer to http://www.apache.org/licenses/LICENSE-2.0 for a full copy
 * 
 * @author Jan Skorvan <jan@skorvan.de>
 */
public class ExpressionResolver {

	private static JavaParserFacade javaParserFacade;

	/**
	 * initialize the ExpressionResolver with a String representing a source
	 * file
	 * 
	 * @param sourceFile
	 *            a String determining the source file that should be analyzed
	 */
	public static void init(String sourceFile) {
		javaParserFacade = JavaParserFacade.get(new CombinedTypeSolver(new ReflectionTypeSolver(),
				new JavaParserTypeSolver(new File(sourceFile).getParentFile())));
	}

	/**
	 * this method determines if the given expression is of type or resolves to
	 * boolean/java.lang.Boolean
	 * 
	 * @param e
	 *            the Expression to be analyzed
	 * @return true if the given expression is of type or resolves to
	 *         boolean/java.lang.Boolean
	 */
	public static boolean isBoolean(Expression e) {
		// literal boolean expression (true, false) -> return true
		if (e instanceof BooleanLiteralExpr)
			return true;
		// identifier of a variable -> determine type of referenced variable
		if (e instanceof NameExpr) {
			String descr = javaParserFacade.getType(e).describe();
			return descr.equals("boolean") || descr.equals("java.lang.Boolean");
		}
		// binary expression -> determine operator and its return type
		if (e instanceof BinaryExpr) {
			Operator op = ((BinaryExpr) e).getOperator();
			if (op == EQUALS || op == NOT_EQUALS || op == GREATER || op == GREATER_EQUALS || op == LESS
					|| op == LESS_EQUALS || op == AND || op == OR)
				return true;
			if (op == XOR)
				return isBoolean(((BinaryExpr) e).getLeft()) && isBoolean(((BinaryExpr) e).getRight());
		}
		// unary expression -> return true if it is "!"
		if (e instanceof UnaryExpr && ((UnaryExpr) e).getOperator() == LOGICAL_COMPLEMENT) {
			return true;
		}
		// instanceof -> return true
		if (e instanceof InstanceOfExpr)
			return true;
		// method call -> determine return type of method
		if (e instanceof MethodCallExpr) {
			SymbolReference<MethodDeclaration> a = javaParserFacade.solve((MethodCallExpr) e);
			if (a.isSolved()) {
				String descr = a.getCorrespondingDeclaration().getReturnType().describe();
				return descr.equals("boolean") || descr.equals("java.lang.Boolean");
			}
		}
		// expression in brackets "()" -> resolve inner expression
		if (e instanceof EnclosedExpr) {
			return isBoolean(((EnclosedExpr) e).getInner());
		}
		return false;
	}

	/**
	 * this method determines if the given expression is of type or resolves to
	 * java.lang.String
	 * 
	 * @param e
	 *            the Expression to be analyzed
	 * @return true if the given expression if of type or resolves to
	 *         java.lang.String
	 */
	public static boolean isString(Expression e) {
		// String literal ("something") -> return true
		if (e instanceof StringLiteralExpr)
			return true;
		// identifier of a variable -> determine variable type
		if (e instanceof NameExpr) {
			return javaParserFacade.getType(e).describe().equals("java.lang.String");
		}
		// binary expression -> true if the operator is "+" and one of the
		// operands is a String
		if (e instanceof BinaryExpr) {
			if (((BinaryExpr) e).getOperator() == PLUS)
				return isString(((BinaryExpr) e).getLeft()) || isString(((BinaryExpr) e).getRight());
			else
				return false;
		}
		// method call -> determine return type of method
		if (e instanceof MethodCallExpr) {
			SymbolReference<MethodDeclaration> a = javaParserFacade.solve((MethodCallExpr) e);
			if (a.isSolved()) {
				return a.getCorrespondingDeclaration().getReturnType().describe().equals("java.lang.String");
			}
		}
		// enclosed expression in brackets "()" -> resolve inner expression
		if (e instanceof EnclosedExpr) {
			return isString(((EnclosedExpr) e).getInner());
		}
		return false;
	}

}
