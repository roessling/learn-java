package de.tu_darmstadt.informatik.skorvan.student;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import de.tu_darmstadt.informatik.skorvan.compile.Compiler;
import de.tu_darmstadt.informatik.skorvan.compile.Precompiler;
import de.tu_darmstadt.informatik.skorvan.student.gui.ErrorMarker;

public class ErrorMessageWriter implements Consumer<String> {

	private Consumer<String> compOut;

	private Consumer<String> precompOut;

	private ErrorMarker err;

	private int lineEnd;

	private int lineOffset;

	private int lastCol;

	private boolean ignoreParseError;

	public ErrorMessageWriter(Consumer<String> compOut, Consumer<String> precompOut, ErrorMarker err, int lineOffset,
			int lineEnd, int lastCol) {
		this.compOut = compOut;
		this.precompOut = precompOut;
		this.err = err;
		this.lineEnd = lineEnd;
		this.lastCol = lastCol;
		this.lineOffset = lineOffset;
	}

	private String writeAndMarkWarning(String[] pos, String msg) {
		int startLine = Integer.parseInt(pos[0]);
		int startCol = Integer.parseInt(pos[1]);
		int endLine = Integer.parseInt(pos[2]);
		int endCol = Integer.parseInt(pos[3]);
		StringBuilder sb = new StringBuilder();
		if (startLine == endLine) {
			sb.append("Zeile ").append(startLine - lineOffset).append(" Zeichen ").append(startCol);
			if (startCol != endCol) {
				sb.append("-").append(endCol);
			}
			sb.append(": ");
		} else {
			sb.append("Zeile ").append(startLine - lineOffset).append(" Zeichen ").append(startCol).append(" - Zeile ")
					.append(endLine - lineOffset).append(" Zeichen ").append(endCol).append(": ");
		}
		sb.append(msg);
		err.markWarning(startLine - lineOffset, startCol, endLine - lineOffset, endCol);
		return sb.toString();
	}

	@Override
	public void accept(String in) {
		/************************************** PRECOMPILER *************************************/
		if (in.startsWith(Precompiler.PRECOMPILER)) {
			String[] message = in.split(Pattern.quote(Precompiler.SEP));
			assert (message[0].equals(Precompiler.PRECOMPILER));

			/************************************* A_EQ_SYN *************************************/
			if (message[1].equals(Precompiler.A_EQ_SYN)) {
				precompOut.accept(writeAndMarkWarning(Arrays.copyOfRange(message, 2, 6),
						"Eventuell haben Sie den Zuweisungsoperator \"=\" mit dem Vergleichsoperator \"==\" verwechselt."));
			}
			/************************************* B_STR_EQ *************************************/
			else if (message[1].equals(Precompiler.B_STR_EQ)) {
				precompOut.accept(writeAndMarkWarning(Arrays.copyOfRange(message, 2, 6),
						"Sie scheinen zwei Strings mit dem Operator \"==\" zu vergleichen.\n"
								+ "Oft liefert jedoch nur die Methode a.equals(b) das erwartete Ergebnis beim Vergleichen von zwei Strings a und b."));
			}
			/************************************* E_SMI_CON ************************************/
			else if (message[1].equals(Precompiler.E_SMI_CON)) {
				precompOut.accept(writeAndMarkWarning(Arrays.copyOfRange(message, 3, 7),
						"Ein Semikolon \";\" direkt nach \"" + message[2]
								+ "\" stellt einen leeren Ausdruck dar, sodass nichts ausgeführt wird."
								+ (message[2].equals("while") ? " Eine Ausnahme bildet die do-while-Schleife." : "")));
			}
			/************************************* D_AND_OR *************************************/
			else if (message[1].equals(Precompiler.D_AND_OR)) {
				precompOut.accept(writeAndMarkWarning(Arrays.copyOfRange(message, 2, 6),
						"Die Bit-Operatoren \"&\" und \"|\" haben eine andere Funktionsweise als die Booleschen Operatoren \"&&\" und \"||\".\n"
								+ "Verwenden Sie erstere nur, wenn Sie sich \u00fcber die genaue Funktionsweise im Klaren sind."));
			}
			/************************************* G_CUR_IF *************************************/
			else if (message[1].equals(Precompiler.G_CUR_IF)) {
				int row = err.mark(Integer.parseInt(message[2]), Integer.parseInt(message[3]));
				precompOut.accept("Zeile " + row
						+ ": Die Bedingung einer if-/while-/for-Anweisung wird in runden Klammern \"()\" angegeben.");
				ignoreParseError = true;
			}
			/************************************** BRACES **************************************/
			else if (message[1].equals(Precompiler.BRACES)) {
				int dif = Integer.parseInt(message[2]);
				if (dif > 0)
					precompOut.accept("Es wurden " + dif
							+ " geschweifte Klammern \"{}\" mehr ge\u00f6ffnet als geschlossen wurden. Vermutlich liegt hier ein Fehler vor.");
				else
					precompOut.accept("Es wurden " + -dif
							+ " geschweifte Klammern \"{}\" mehr geschlossen als ge\u00f6ffnet wurden. Vermutlich liegt hier ein Fehler vor.");
			}
			/************************************* BRACKETS *************************************/
			else if (message[1].equals(Precompiler.BRACKETS)) {
				int dif = Integer.parseInt(message[2]);
				if (dif > 0)
					precompOut.accept("Es wurden " + dif
							+ " eckige Klammern \"[]\" mehr ge\u00f6ffnet als geschlossen wurden. Vermutlich liegt hier ein Fehler vor.");
				else
					precompOut.accept("Es wurden " + -dif
							+ " eckige Klammern \"[]\" mehr geschlossen als ge\u00f6ffnet wurden. Vermutlich liegt hier ein Fehler vor.");
			}
			/************************************** PARENS **************************************/
			else if (message[1].equals(Precompiler.PARENS)) {
				int dif = Integer.parseInt(message[2]);
				if (dif > 0)
					precompOut.accept("Es wurden " + dif
							+ " runde Klammern \"()\" mehr ge\u00f6ffnet als geschlossen wurden. Vermutlich liegt hier ein Fehler vor.");
				else
					precompOut.accept("Es wurden " + -dif
							+ " runde Klammern \"()\" mehr geschlossen als ge\u00f6ffnet wurden. Vermutlich liegt hier ein Fehler vor.");
			}
			/************************************** DQUOTES *************************************/
			else if (message[1].equals(Precompiler.DOUBLE_QUOTE)) {
				precompOut.accept(
						"Es wurde eine ungerade Anzahl von Anf\u00f4hrungszeichen \" gefunden. Vermutlich liegt hier ein Fehler vor.");
			}
			/************************************** SQUOTES *************************************/
			else if (message[1].equals(Precompiler.SINGLE_QUOTE)) {
				precompOut.accept(
						"Es wurde eine ungerade Anzahl von Apostrophen ' gefunden. Vermutlich liegt hier ein Fehler vor.");
			}
			/************************************* WRONGCHAR ************************************/
			else if (message[1].equals(Precompiler.CHAR_TOO_LONG)) {
				precompOut.accept(
						"Ein char speichert genau ein Zeichen. Innerhalb der Apostrophe '' darf daher nur genau ein Zeichen stehen.");
			}
			/********************************* PARSINGEXCEPTION *********************************/
			else if (message[1].equals(Precompiler.PARSING_EXCEPTION)) {
				if (!ignoreParseError && message[2].contains("Parse error. Found ")) {
					String line = message[2].substring(6, message[2].indexOf(','));
					String startCol = String.valueOf(Integer.parseInt(
							message[2].substring(message[2].indexOf("col") + 4, message[2].indexOf(')'))) + 1);
					String token = message[2].substring(message[2].indexOf('"') + 1,
							message[2].indexOf('"', message[2].indexOf('"') + 1));
					System.out.println("ParsingException line " + line + " lineEnd " + lineEnd + " startCol " + startCol
							+ " lastCol " + lastCol);
					if (Integer.parseInt(line) - lineOffset > lineEnd || Integer.parseInt(line) - lineOffset == lineEnd
							&& Integer.parseInt(startCol) >= lastCol) {
						precompOut
								.accept("Fehler beim Parsen im Framework. Eventuell fehlt eine geschweifte Klammer \"}\""
										+ "oder in der letzten Zeile ein Semikolon \";\".");
					} else {
						precompOut.accept("Zeile " + (line + 1) + ": Fehler beim Parsen: Der Ausdruck \"" + token
								+ "\" scheint an dieser Stelle falsch zu sein. Eventuell fehlt in der vorherigen Zeile ein Semikolon \";\" oder eine geschweifte Klammer \"}\".");
					}
				} else if (message[2].contains("Lexical error at line ")) {
					String line = message[2].substring(22, message[2].indexOf(','));
					String col = message[2].substring(message[2].indexOf(", column ") + 9, message[2].indexOf('.'));
					if (Integer.parseInt(line) - lineOffset > lineEnd
							|| Integer.parseInt(line) - lineOffset == lineEnd && Integer.parseInt(col) >= lastCol) {
						precompOut
								.accept("Unerwartetes Zeichen im Framework. \u00dcberpr\u00fcfen Sie zuerst alle anderen Fehler in Ihrem Code."
										+ "Bleibt dieser Fehler bestehen, wenden Sie sich bitte an den Aufgabensteller.");
					} else {
						precompOut.accept(writeAndMarkWarning(new String[] { line, col, line, col },
								"Unerwartetes Zeichen an dieser Stelle"));
					}
				} else if (!ignoreParseError)
					precompOut.accept("no parse/lexical error: " + message[2]);
				else
					return;
			} else {
				precompOut.accept(message[1] + ": unhandled");
			}
			precompOut.accept("");
		}
		/*************************************** COMPILER ***************************************/
		else if (in.startsWith(Compiler.COMPILER)) {
			if (in.endsWith(Compiler.CONFIG)) {
				if (System.getProperty("java.version").startsWith("1.8"))
					compOut.accept(
							"Es wurde kein Compiler gefunden. Bitte \u00fcberpr\u00fcfen Sie den JDK-Pfad in der Konfiguration.");
				else
					compOut.accept(
							"Das Programm wurde von einer Laufzeitumgebung ohne Compiler ausgef\u00fchrt. Bitte starten Sie das Programm unter Verwendung eines JDKs oder verwenden Sie Java-Version 1.8.");
				return;
			}
			String[] message = in.split(Pattern.quote(Compiler.SEP));
			assert (message[0].equals(Compiler.COMPILER));
			int line = Integer.parseInt(message[1]);
			int start = Integer.parseInt(message[2]);
			int end = Integer.parseInt(message[3]);
			String msg = message[4];
			if (line > lineEnd || line < 0) {
				compOut.accept("Compilerfehler im Framework");
			} else {
				compOut.accept(
						"Compilerfehler in Zeile " + line + " Zeichen " + start + (end == start ? "" : "-" + end));
				err.mark(line, start, line, end);
			}
			if (msg.startsWith("cannot find symbol")) {
				String symbol = msg.split(Pattern.quote("" + (char) 10))[1].substring(12);
				System.out.println(symbol);
				String type = "";
				String name = "";
				if (symbol.startsWith("variable")) {
					type = "Variable";
					name = symbol.substring(9);
				} else if (symbol.startsWith("method")) {
					type = "Methode";
					name = symbol.substring(7);
				} else if (symbol.startsWith("class")) {
					type = "Klasse";
					name = symbol.substring(6);
				} else {
					System.err.println("unhandled symbol type: " + symbol);
				}
				compOut.accept("Die " + type + " \"" + name
						+ "\" wurde nicht gefunden. Stellen Sie sicher, dass sie korrekt definiert wurde. \u00dcberpr\u00fcfen Sie ggf. die Schreibweise.");
			} else if (msg.endsWith("might not have been initialized")) {
				String symbol = msg.substring(0, msg.length() - 32);
				System.out.println(symbol);
				String type = "";
				String name = "";
				if (symbol.startsWith("variable")) {
					type = "Variable";
					name = symbol.substring(9);
				} else if (symbol.startsWith("method")) {
					type = "Methode";
					name = symbol.substring(8);
				} else {
					System.err.println("unhandled symbol type: " + symbol);
				}
				compOut.accept("Die " + type + " \"" + name + "\" wird verwendet, bevor sie initialisiert wurde.");
			} else if (msg.endsWith("expected")) {
				String expected = msg.substring(0, msg.indexOf("expected") - 1);
				compOut.accept("Es wurde " + expected
						+ " erwartet. \u00dcberpr\u00fcfen Sie im Zweifelsfall auch umliegende Zeilen auf Fehler.");
			} else if (msg.contains("cannot be applied to given types")) {
				String method = msg.substring(7, msg.indexOf(" in class "));
				String[] lines = msg.split("\n");
				String required = lines[1].substring(12);
				if (required.equals("no arguments"))
					required = "keine Argumente";
				else if (required.contains(","))
					required = "Argumente der Typen " + required.replace(",", ", ");
				else
					required = "ein Argument vom Typ " + required;
				String found = lines[2].substring(9);
				if (found.equals("no arguments"))
					found = "n keine Argumente";
				else if (found.contains(","))
					found = "n Argumente vom Typ " + found.replace(",", ", ");
				else
					found = " ein Argument vom Typ " + found;
				System.out.println(method);
				compOut.accept("Die Methode " + method + " erwartet laut Definition " + required + ". Gefunden wurde"
						+ found + ".");
				compOut.accept("\u00dcberpr\u00fcfen Sie, ob der Aufruf korrekt ist. Falls Sie die Methode " + method
						+ " selbst definiert haben, ist m\u00f6glicherweise die Definition fehlerhaft.");
				if (found.split(",").length == required.split(",").length)
					compOut.accept(
							"Es besteht ebenfalls die M\u00f6glichkeit, dass beim Aufruf eine Konvertierung fehlgeschlagen ist.");
			} else if (msg.startsWith("no suitable method found for")) {
				String[] lines = msg.split("\n");
				String call = lines[0].substring(29).replace(",", ", ");
				compOut.accept("F\u00fcr den Aufruf " + call
						+ " wurde keine passende Methodendefinition gefunden. Folgende Definitionen konnten nicht angewendet werden:");
				for (int i = 1; i < lines.length; i += 2) {
					String def = lines[i]
							.substring(lines[i].lastIndexOf('.') + 1, lines[i].indexOf(" is not applicable"))
							.replace(",", ", ");
					String reason = "";
					if (lines[i + 1].contains("possible lossy conversion from ")) {
						String fromType = lines[i + 1].substring(57, lines[i + 1].indexOf(" to "));
						String toType = lines[i + 1].substring(
								lines[i + 1].indexOf(fromType + " to ") + fromType.length() + 4,
								lines[i + 1].length() - 1);
						reason = "M\u00f6glicher Pr\u00e4zisionsverlust bei der Konvertierung von " + fromType + " zu "
								+ toType;
					} else if (lines[i + 1].contains("actual and formal argument lists differ in length"))
						reason = "Anzahl von Parametern in Definition und Aufruf unterscheidet sich";
					else if (lines[i + 1].contains(" cannot be converted to ")) {
						String fromType = lines[i + 1].substring(26, lines[i + 1].indexOf(" cannot be converted to "));
						String toType = lines[i + 1].substring(lines[i + 1].indexOf(" cannot be converted to ") + 24,
								lines[i + 1].length() - 1);
						reason = fromType + " kann nicht zu " + toType + " konvertiert werden";
					}
					compOut.accept(def + "; " + reason);
				}
				compOut.accept("\u00dcberpr\u00fcfen Sie, ob der Aufruf korrekt ist. Falls Sie die Methode "
						+ call.substring(0, call.indexOf('('))
						+ " selbst definiert haben, ist m\u00f6glicherweise die Definition fehlerhaft.");
			} else if (msg.equals("missing return statement")) {
				compOut.accept(
						"Es fehlt ein \"return\"-Ausdruck, der den R\u00fcckgabewert der Methode enth\u00e4lt. Stellen Sie sicher, dass f\u00fcr alle Auswertungspfade ein return-Ausdruck erreicht wird. Falls die Methode keinen Wert zur\u00fcckgeben soll, k\u00f6nnen Sie den Typ der Methode zu \"void\" \u00e4ndern.");
			} else if (msg.startsWith("non-static ") && msg.endsWith(" cannot be referenced from a static context")) {
				String type = msg.substring(11, msg.indexOf(' ', 11));
				String name = msg.substring(12 + type.length(), msg.indexOf(' ', 12 + type.length()));
				if (type.equals("variable"))
					type = "Variable";
				else if (type.equals("method"))
					type = "Methode";
				else
					type = "unkown type: " + type;
				compOut.accept("Die nicht-statische " + type + " \"" + name.replace(",", ", ")
						+ "\" kann nicht aus einem statischen Kontext aufgerufen werden.");
			} else if (msg.equals("missing method body, or declare abstract")) {
				compOut.accept(
						"Ein Semikolon nach der Methodendeklaration ist nur bei einer abstrakten Methode erlaubt. Bei allen anderen Methoden muss stattdessen ein Methodenk\u00f6rper folgen, der in geschweiften Klammern {} steht.");
			} else if (msg.equals("reached end of file while parsing")) {
				compOut.accept(
						"Das Ende der Datei wurde vorzeitig erreicht. \u00dcberpr\u00fcfen Sie, ob alle geschweiften Klammern wieder geschlossen werden.");
			} else if (msg.equals("illegal start of expression")) {
				compOut.accept(
						"Ung\u00fcltiger Ausdruck. Der \"Precompiler\"-Reiter enth\u00e4lt m\u00f6glicherweise weitere Informationen.");
			} else if (msg.equals("variable declaration not allowed here")) {
				compOut.accept("Es ist an dieser Stelle nicht erlaubt, eine Variable zu deklarieren.");
			} else if (msg.equals("illegal start of type")) {
				compOut.accept(
						"Ung\u00fcltiger Beginn einer Deklaration. M\u00f6glicherweise wurde dieser Fehler durch einen anderen Fehler hervorgerufen. Beachten Sie auch den \"Precompiler\"-Reiter.");
			} else if (msg.startsWith("incompatible types: ")) {
				if (msg.contains(" cannot be converted to ")) {
					String first = msg.substring(20, msg.indexOf(" cannot be converted to "));
					String second = msg.substring(msg.indexOf(" cannot be converted to ") + 24);
					compOut.accept("Inkompatible Typen: " + first + " kann nicht automatisch zu " + second
							+ " konvertiert werden. Sie k\u00f6nnen einen Cast verwenden, wenn Sie sicher sind, dass die Typen explizit konvertierbar sind.");
				} else if (msg.startsWith("incompatible types: possible lossy conversion from ")) {
					String from = msg.substring(msg.indexOf("incompatible types: possible lossy conversion from ") + 51,
							msg.indexOf(" to "));
					String to = msg.substring(msg.indexOf(" to ") + 4);
					compOut.accept("Die Konvertierung von " + from + " zu " + to
							+ " verursacht m\u00f6glicherweise einen Pr\u00e4zisionsverlust. Stellen Sie sicher, dass Sie diese M\u00f6glichkeit betrachtet haben. Eine Konvertierung l\u00e4sst sich mit einem Cast explizit anweisen.");
				} else {
					compOut.accept("unbehandelte Nachricht: " + msg);
				}
			} else if (msg.contains(" is already defined in ")) {
				String whatType = msg.substring(0, msg.indexOf(' '));
				String what = msg.substring(msg.indexOf(' ') + 1, msg.indexOf(" is already defined in ")).replace(",",
						", ");
				String whereType = msg.substring(msg.indexOf(" is already defined in ") + 23, msg.lastIndexOf(' '));
				String where = msg.substring(msg.lastIndexOf(' ') + 1).replace(",", ", ");
				if (whatType.equals("variable"))
					whatType = "Variable";
				else if (whatType.equals("method"))
					whatType = "Methode";
				if (whereType.equals("method"))
					whereType = "Methode";
				else if (whereType.equals("class"))
					whereType = "Klasse";
				if (where.indexOf('.') > -1)
					where = where.substring(where.lastIndexOf('.') + 1);
				compOut.accept("Die " + whatType + " " + what + " wurde bereits in der " + whereType + " " + where
						+ " definiert.");
			} else if (msg.equals("unreachable statement")) {
				compOut.accept(
						"Dieser Code wird niemals ausgef\u00fchrt. Grund daf\u00fcr kann sein, dass im Code davor eine Endlosschleife oder ein return-Ausdruck vorhanden sind.");
			} else if (msg.endsWith(" cannot be dereferenced")) {
				String type = msg.substring(0, msg.indexOf(" cannot be dereferenced"));
				compOut.accept("Der Typ " + type
						+ " kann nicht dereferenziert werden. Es ist also nicht möglich, Methoden auf primitiven Datentypen aufzurufen.");
			} else if (msg.equals("malformed floating point literal")) {
				compOut.accept("Eine Gleitkommezahl wurde an dieser Stelle falsch geformt.");
			} else {
				System.err.println("unhandled message: " + msg);
				compOut.accept("unbehandelte Nachricht: " + msg);
			}
			compOut.accept("");
		} else if (in.equals("DONE")) {
			precompOut.accept("DONE");
			compOut.accept("DONE");
			err.show();
			ignoreParseError = false;
		} else
			System.err.println("unhandled error type: " + in);
	}
}