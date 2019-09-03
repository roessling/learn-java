package de.tu_darmstadt.informatik.skorvan.student.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

public class StyleBuilder {

	private ArrayList<Short> styleArray;

	private ArrayList<String> styleValues;

	public StyleBuilder(int length) {
		styleArray = new ArrayList<>(Arrays.asList(new Short[length]));
		Collections.fill(styleArray, (short) 0);
		styleValues = new ArrayList<>(Short.SIZE);
	}

	public void addStyle(String style, int from, int to) {
		if (from > to)
			throw new IllegalArgumentException(
					"argument from (" + from + ") must not be higher than argument to (" + to + ").");

		if (from >= styleArray.size())
			return;

		if (to >= styleArray.size())
			to = styleArray.size() - 1;
		
		if (from < 0 || to < 0)
			return;

		int f = styleValues.indexOf(style);
		if (f == -1) {
			styleValues.add(style);
			f = styleValues.size() - 1;
		}

		for (int i = from; i < to; i++)
			styleArray.set(i, (short) (styleArray.get(i) | (short) 1 << f));
	}

	public StyleSpans<Collection<String>> create() {
		if (styleArray == null || styleArray.size() == 0)
			return null;
		StyleSpansBuilder<Collection<String>> sb = new StyleSpansBuilder<>();
		styleArray.forEach(s -> {
			ArrayList<String> style = new ArrayList<>(Short.SIZE);
			for (byte bit = 0; bit < styleValues.size(); bit++) {
				if ((s & (1 << bit)) != 0)
					style.add(styleValues.get(bit));
			}
			sb.add(style, 1);
		});
		return sb.create();
	}

}
