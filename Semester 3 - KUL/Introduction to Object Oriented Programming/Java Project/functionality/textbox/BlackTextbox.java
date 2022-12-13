package functionality.textbox;

import java.awt.*;

import javax.swing.JTextField;

public class BlackTextbox extends Textbox {

	// Class for Black square boxes

	public BlackTextbox() {
		super();
		// Add effects in the box
		afterEffects();

	}

	@Override
	void afterEffects() {
		super.textBox.setToolTipText("Black Boxes: You can't enter any letter here");
		super.textBox.setBackground(Color.BLACK);
		super.textBox.setEnabled(false);// Disable the text field
	}

	public JTextField getTextBox() {
		return super.textBox;
	}

	@Override
	public TextboxType getTextboxType() {
		return TextboxType.BLACK;
	}

}
