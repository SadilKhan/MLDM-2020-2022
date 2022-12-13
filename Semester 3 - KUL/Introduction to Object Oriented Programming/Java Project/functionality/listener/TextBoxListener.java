package functionality.listener;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import functionality.keyboard.*;
import functionality.textbox.TextBoxInfo;
import functionality.textbox.TextboxType;
import utility.*;

public class TextBoxListener implements MouseListener {

	/*
	 * Class for binding a textbox with a keyboard. Whenever a textbox is clicked, a
	 * keyboard will be generated.
	 * 
	 */

	private ArrayList<ArrayList<TextBoxInfo>> textBoxes; // Square tiles
	private char[] enabledKey = null; // The active keyboard keys
	private PlainKeyboard onScreenKeyboard; // On-Screen Keyboard
	private Map<TextboxType, KeyboardType> tbToKeyboardMap; // The map between square type and keyboard type.
	Util util = new Util();

	public TextBoxListener(ArrayList<ArrayList<TextBoxInfo>> textBoxes) {
		this.textBoxes = textBoxes;
		// Add mouse listener to every Textboxes
		for (int i = 0; i < this.textBoxes.size(); i++) {
			for (int j = 0; j < this.textBoxes.get(i).size(); j++) {
				this.textBoxes.get(i).get(j).getTextbox().addMouseListener(this);
			}
		}
		// Get the map between Text Box Type and Keyboard Type
		tbToKeyboardMap = util.tbtypeTokeyboard();
	}

	private void chooseKeyboard(JTextField textbox, TextboxType type, char[] enabledKey) {

		// Adds a keyboard for a square tile
		Point location = textbox.getLocationOnScreen();
		int x = location.x % 600 + 100;
		int y = location.y % 600 + 100;
		KeyboardType ktype = tbToKeyboardMap.get(type);
		switch (ktype) {

		case PLAIN:
			// Dispose the previous keyboard
			if (this.onScreenKeyboard != null) {
				this.onScreenKeyboard.dispose();
			}
			// Create a new keyboard object and display it on screen.
			this.onScreenKeyboard = new PlainKeyboard(x, y);
			break;

		case RANDOM:
			// Dispose the previous keyboard
			if (this.onScreenKeyboard != null) {
				this.onScreenKeyboard.dispose();
			}
			this.onScreenKeyboard = new RandomKeyboard(x, y, enabledKey);
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		 * When a square is clicked, the previous keyboard is disposed. So pressing a
		 * square multiple times won't create multiple keyboards. 2. If no keyboard
		 * object is generated before i.e when the square is clicked for the first time,
		 * a keyboard object is generated. Aldo when a square is clicked, the keyboard
		 * shouldn't cover the square. So a position relative to the field is
		 * calculated.
		 * 
		 */

		for (int i = 0; i < this.textBoxes.size(); i++) {
			for (int j = 0; j < this.textBoxes.get(i).size(); j++) {
				JTextField tb = this.textBoxes.get(i).get(j).getTextbox();
				TextboxType tbt = this.textBoxes.get(i).get(j).getTextboxtype();
				enabledKey = this.textBoxes.get(i).get(j).getEnabledKey();
				if (e.getSource() == tb) {
					// Add a new Keyboard
					chooseKeyboard(tb, tbt, enabledKey);
					// Get the alphabet to button map
					HashMap<Character, JButton> charButtonMap = onScreenKeyboard.getCharButtonMap();
					for (char c = 'A'; c <= 'Z'; c++) {
						// Add ActionListener to every key in the keyboard
						charButtonMap.get(c).addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// Get the string associated with the button that is pressed in the on-screen
								// keyboard
								String s = ((Component) e.getSource()).getName();
								tb.setText(s);
								// After a button is pressed, the on-screen keyboard disappears
								onScreenKeyboard.dispose();
							}
						});
					}
				}
			}
		}
	}

	public PlainKeyboard getOnScreenKeyboard() {
		return this.onScreenKeyboard;
	}

	public void setOnScreenKeyboard(PlainKeyboard onScreenKeyboard) {
		this.onScreenKeyboard = onScreenKeyboard;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
