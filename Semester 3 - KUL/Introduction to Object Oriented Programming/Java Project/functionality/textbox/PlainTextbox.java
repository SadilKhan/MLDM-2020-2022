package functionality.textbox;

import javax.swing.JTextField;

public class PlainTextbox extends Textbox {

	// Class for Regular plain keyboard
	public PlainTextbox() {
		// Superclass Constructer
		super();
		// Add effects
		afterEffects();
	}

	@Override
	void afterEffects() {
		super.textBox.setToolTipText("Regular Boxes: On-Screen Keyboard will appear when you click on the box");
	}

	public JTextField getTextBox() {
		return super.textBox;
	}

	@Override
	public TextboxType getTextboxType() {

		return TextboxType.REGULAR;
	}

}
