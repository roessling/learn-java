package de.tu_darmstadt.informatik.skorvan.student;

import static de.tu_darmstadt.informatik.skorvan.student.TestRunner.ERROR;
import static de.tu_darmstadt.informatik.skorvan.student.TestRunner.EXCEPTION;
import static de.tu_darmstadt.informatik.skorvan.student.TestRunner.FAILURE;
import static de.tu_darmstadt.informatik.skorvan.student.TestRunner.RESULT;
import static de.tu_darmstadt.informatik.skorvan.student.TestRunner.SEP;
import static de.tu_darmstadt.informatik.skorvan.student.TestRunner.SUCCESS;
import static de.tu_darmstadt.informatik.skorvan.student.TestRunner.TEST;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import de.tu_darmstadt.informatik.skorvan.exercises.UnitResultItem;
import de.tu_darmstadt.informatik.skorvan.student.gui.StudentGui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestResultWriter implements Consumer<String> {

	private Consumer<String> out;
	
	public TestResultWriter(Consumer<String> out) {
		this.out = out;
	}

	@Override
	public void accept(String msg) {
		assert (msg.startsWith(TEST));
		String[] message = msg.split(SEP);
		System.out.println(msg);

		// eryeuge eine liste von richtig falsch paaren
		for (String string : message) {

		}

		if (message[1].equals(SUCCESS)) {
			out.accept("Alle Tests waren erfolgreich!");
			out.accept("Die Laufzeit betrug " + message[2] + " ms.");

		} else if (message[1].equals(RESULT)) {
			int fails = Integer.valueOf(message[2]);
			int runs = Integer.valueOf(message[3]);
			int time = Integer.valueOf(message[4]);
			out.accept("Es " + (fails > 1 ? "sind" : "ist") + " " + fails + " von " + runs + " Tests fehlgeschlagen.");
			out.accept("Die Laufzeit betrug " + time + " ms.");
		} else if (message[1].equals(FAILURE)) {
			String[] split = message[2].split("<");
			if (split[0].startsWith("private")) {
				out.accept("Die privaten Tests waren nicht erfolgreich.");
			} else {
				String call = split[1].substring(0, split[1].indexOf('>'));
				String expected = split[2].substring(0, split[2].indexOf('>'));
				String actual = split[3].substring(0, split[3].indexOf('>'));  //TODO so dass es fehlt
				out.accept("Der Test mit dem Aufruf " + call + " lieferte das Ergebnis <" + actual
						+ ">. Erwartet wurde <" + expected + ">.");
//				StudentGui.data.add(new UnitResultItem(call + " -> " + expected, actual, "FALSCH"));

			}
		} else if (message[1].equals(EXCEPTION)) {
			out.accept("In Zeile " + message[2] + " gab es eine(n) " + message[3] + ".");
		} else if (message[1].equals(ERROR)) {
			if (message[2].startsWith("cannot find symbol")) {
				String symbol = message[2].split(Pattern.quote("" + (char) 10))[1].substring(12);
				String type = "";
				String name = "";
				if (symbol.startsWith("variable")) {
					type = "Variable";
					name = symbol.substring(9);
				} else if (symbol.startsWith("method")) {
					type = "Methode";
					name = symbol.substring(7);
				} else {
					System.err.println("unhandled symbol type: " + symbol);
				}
				out.accept("Die " + type + " \"" + name
						+ "\" wurde in Ihrem Code nicht gefunden. Stellen Sie sicher, dass sie korrekt definiert wurde. \u00dcberpr\u00fcfen Sie ggf. die Schreibweise.");
			} else {
				out.accept("Fehler: " + msg);
			}
		} else {
			out.accept("unhandled message: " + msg);
		}
	}

}
