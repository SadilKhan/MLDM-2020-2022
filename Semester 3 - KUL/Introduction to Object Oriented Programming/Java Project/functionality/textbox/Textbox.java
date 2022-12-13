package functionality.textbox;

import java.awt.*;
import javax.swing.*;

public abstract class Textbox {

	// Abstract class for Square Boxes in Board

	protected JTextField textBox; // Square Box

	Textbox() {
		createUI();
	}

	protected void createUI() {
		textBox = new JTextField();
		textBox.setPreferredSize(new Dimension(100, 100));
		textBox.setHorizontalAlignment(JTextField.CENTER);
		textBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	abstract void afterEffects(); // Method for customization of squares

	abstract TextboxType getTextboxType(); // Type of squares.

}
