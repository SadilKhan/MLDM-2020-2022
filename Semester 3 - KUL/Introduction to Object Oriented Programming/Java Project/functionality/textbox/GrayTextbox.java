package functionality.textbox;

import java.awt.*;

import javax.swing.JTextField;

public class GrayTextbox extends Textbox {

	// Class for Gray Square Boxes

	public GrayTextbox() {
		// Superclass Constructer
		super();
		// Add effects
		afterEffects();
	}

	@Override
	void afterEffects() {
		super.textBox.setToolTipText("You need to collect the characters at these squares to form a special word "
				+ "(or solution) which you can enter below the playing field");
		super.textBox.setBackground(Color.GRAY);

	}

	public JTextField getTextBox() {
		return super.textBox;
	}

	@Override
	public TextboxType getTextboxType() {
		return TextboxType.GRAY;
	}

}
