package de.tu_darmstadt.informatik.skorvan.student.gui;

import static de.tu_darmstadt.informatik.skorvan.util.Pattern.BRACE_C;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.BRACE_O;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.BRACKET_C;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.BRACKET_O;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.COMMENT;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.KEYWORD;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.PAREN_C;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.PAREN_O;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.SEMICOLON;
import static de.tu_darmstadt.informatik.skorvan.util.Pattern.STRING;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.reactfx.util.Tuple2;

public class HighlightedCodeArea extends CodeArea {

	private ArrayList<Tuple2<Integer, Integer>> errors;
	private ArrayList<Tuple2<Integer, Integer>> warnings;

	private int lastLength;

	private static final Pattern PATTERN = Pattern.compile(
			"(?<KEYWORD>" + KEYWORD + ")" + "|(?<PAREN>" + PAREN_O + "|" + PAREN_C + ")"
					+ "|(?<BRACE>" + BRACE_O + "|" + BRACE_C + ")" + "|(?<BRACKET>" + BRACKET_O + "|" + BRACKET_C + ")"
					+ "|(?<SEMICOLON>" + SEMICOLON + ")" + "|(?<STRING>" + STRING + ")" + "|(?<COMMENT>" + COMMENT + ")");

	public HighlightedCodeArea() {
		super();
		richChanges().filter(ch -> !ch.getInserted().equals(ch.getRemoved())).subscribe(change -> {
			if (!getText().equals("")) {
				setStyleSpans(0, computeHighlighting(getText()));
			}
		});

		setId("codeArea");
		setParagraphGraphicFactory(LineNumberFactory.get(this));
	}

	// errors must be of even length, each two values meaning start and end of
	// highlighting
	public void markErrorsAndWarnings(ArrayList<Tuple2<Integer, Integer>> errors, ArrayList<Tuple2<Integer, Integer>> warnings) {
		if (getText().isEmpty())
			return;
		if(errors == null)
			this.errors = null;
		else {
			this.errors = new ArrayList<>(errors);
			this.errors.sort((t1, t2) -> t1.get1() - t2.get1());
		}
		if (warnings == null)
			this.warnings = null;
		else {
			this.warnings = new ArrayList<>(warnings);
			this.warnings.sort((t1, t2) -> t1.get1() - t2.get1());
		}
		lastLength = getText().length();
		setStyleSpans(0, computeHighlighting(getText()));
	}

	private StyleSpans<Collection<String>> computeHighlighting(String text) {
		Matcher matcher = PATTERN.matcher(text);
		int lengthChanged = getText().length() - lastLength;
		StyleBuilder sb = new StyleBuilder(getText().length());

		while (matcher.find()) {
			String styleClass = matcher.group("KEYWORD") != null ? "keyword"
					: matcher.group("PAREN") != null ? "paren"
							: matcher.group("BRACE") != null ? "brace"
									: matcher.group("BRACKET") != null ? "bracket"
											: matcher.group("SEMICOLON") != null ? "semicolon"
													: matcher.group("STRING") != null ? "string"
															: matcher.group("COMMENT") != null ? "comment" : null;
			/* never happens */ assert styleClass != null;

			sb.addStyle(styleClass, matcher.start(), matcher.end());
		}

		if (errors != null && lengthChanged != 0) {
			for (int i = 0; i < errors.size(); i++) {
				Tuple2<Integer, Integer> t = errors.get(i);
				boolean remove = false;
				if (t.get1() >= getCaretPosition())
					t = t.update1(t.get1() + lengthChanged);
				if (t.get2() > getCaretPosition()) {
					if(t.get1() <= getCaretPosition()) {
						remove = true;
					} else {
						t = t.update2(t.get2() + lengthChanged);
					}
				}
				if(remove) {
					errors.remove(i);
					i--;
				} else {
					errors.set(i, t);
				}
			}
		}
		if (errors != null) {
			errors.forEach(t -> {
				if(t.get1() >= getText().length() || t.get2() >= getText().length() || t.get1() > t.get2())
					return;
				if(t.get1() == t.get2() && !String.valueOf(getText().charAt(t.get1())).matches("."))
					sb.addStyle("error", t.get1() - 1, t.get2());
				else
					sb.addStyle("error", t.get1(), t.get2() + 1);
			});
		}
		
		if (warnings != null && lengthChanged != 0) {
			for (int i = 0; i < warnings.size(); i++) {
				Tuple2<Integer, Integer> t = warnings.get(i);
				boolean remove = false;
				if (t.get1() >= getCaretPosition())
					t = t.update1(t.get1() + lengthChanged);
				if (t.get2() > getCaretPosition()) {
					if(t.get1() <= getCaretPosition()) {
						remove = true;
					} else {
						t = t.update2(t.get2() + lengthChanged);
					}
				}
				if(remove) {
					warnings.remove(i);
					i--;
				} else {
					warnings.set(i, t);
				}
			}
		}
		if (warnings != null) {
			warnings.forEach(t -> {
				if(t.get1() >= getText().length() || t.get2() >= getText().length() || t.get1() > t.get2())
					return;
				if(t.get1() == t.get2() && !String.valueOf(getText().charAt(t.get1())).matches("."))
					sb.addStyle("warning", t.get1() - 1, t.get2());
				else
					sb.addStyle("warning", t.get1(), t.get2() + 1);
			});
		}
		
		lastLength = getText().length();

		StyleSpans<Collection<String>> s = sb.create();
		return s;
	}

	public int getLineForPosition(int position) {
		if(position < 0 || position >= getText().length())
			return -1;
		System.out.println("position: " + position + ". result: " + (int) (getText().chars().limit(position).filter(c -> c == ((int) '\n')).count() + 1));
		return (int) getText().chars().limit(position).filter(c -> c == ((int) '\n')).count() + 1;
	}
	
	public int getLengthUntilLine(int line) {
		if(line <= 1)
			return 0;
		String[] lines = getText().split("\n");
		if (line >= lines.length)
			return Integer.MAX_VALUE;
		int result = 0;
		for(int i = 0; i < line - 1 && i < lines.length; i++) {
			result += lines[i].length() + 1;
		}
		return result;
	}
}
