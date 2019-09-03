package de.tu_darmstadt.informatik.skorvan.student.gui;

import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

class LinkStyleDef {
	
	private Runnable action;
	
	LinkStyleDef() {}
	
	LinkStyleDef(Runnable action) {
		this.action = action;
	}
	
	void applyToText(Text text) {
		if(action != null) {
			text.setCursor(Cursor.HAND);
			text.setFill(Color.BLUE);
			text.setUnderline(true);
			text.setOnMouseClicked(click -> action.run());
		}
	}
	
}



class wasd{}