package edu.usal.util;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ValidableTextField extends JTextField {

	private static final long serialVersionUID = 1L;

	private Pattern pattern;

	private Border wrongBorder = BorderFactory.createLineBorder(Color.RED);
	private Border defaultBorder = BorderFactory.createLineBorder(Color.BLACK);

	public ValidableTextField(String regEx) {
		super();
//		this.defaultBorder = this.getBorder();
		this.setColumns(15);
		this.pattern = Pattern.compile(regEx);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				validateText();
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	private void validateText() {
		Matcher matcher = pattern.matcher(this.getText());
		if (!matcher.matches()) {
			this.setBorder(wrongBorder);
		} else {
			this.setBorder(defaultBorder);
		}
	}

}
