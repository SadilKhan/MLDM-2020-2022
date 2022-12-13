package functionality.textbox;

import java.awt.Color;

import javax.swing.JTextField;

public class BlueTextbox extends Textbox {

	// Class for Blue helper Square Boxes

	public BlueTextbox() {
		super();
		// Add effects in the box
		afterEffects();
	}

	@Override
	void afterEffects() {
		super.textBox.setBackground(new Color(37, 150, 190));
	}

	public JTextField getTextBox() {
		return super.textBox;
	}

	@Override
	public TextboxType getTextboxType() {
		return TextboxType.BLUE;
	}

}
