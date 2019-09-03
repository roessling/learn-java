package de.tu_darmstadt.informatik.skorvan.util;

public class Pattern {

	private static final String[] KEYWORDS = new String[] { "abstract", "assert", "boolean", "break", "byte", "case",
			"catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends",
			"final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface",
			"long", "native", "new", "package", "private", "protected", "public", "return", "short", "static",
			"strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
			"volatile", "while", "true", "false" };
	
	public static final String KEYWORD = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
	public static final String PAREN_O = "\\(";
	public static final String PAREN_C = "\\)";
	public static final String BRACE_O = "\\{";
	public static final String BRACE_C = "\\}";
	public static final String BRACKET_O = "\\[";
	public static final String BRACKET_C = "\\]";
	public static final String SEMICOLON = ";";
	public static final String STRING = "\"([^\"\\\\\\n]|\\\\.)*\"?";
	public static final String COMMENT = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
	public static final String CHAR = "'([^'\\\\]|\\\\[^u\\d]|\\\\u(\\d|a|b|c|d|e|f){4}|\\\\[0-3]?[0-7]{1,2})'";
	public static final String LONG_CHAR = "'([^'\\\\]|\\\\[^u\\d]|\\\\u(\\d|a|b|c|d|e|f){4}|\\\\[0-3]?[0-7]{1,2}){2,}'";
	public static final String IWF_NOPAREN = "((if|for)\\s*+[^\\(])|(while\\s*+[^\\(;])";
	public static final String QQ = "\"";
	public static final String Q = "'";

}
