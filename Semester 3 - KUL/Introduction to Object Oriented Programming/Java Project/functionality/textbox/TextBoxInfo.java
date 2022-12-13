package functionality.textbox;

import javax.swing.*;

public class TextBoxInfo {
	// Class for Squares. It contains metadata for any square.

	private JTextField textbox;
	private TextboxType textboxtype; // Type of the textbox ( Regular,Black,Blue,Gray)
	private char[] enabledKey; // Keys to be enabled in a keyboard assigned for the any TextBoxInfo object.

	public JTextField getTextbox() {
		return textbox;
	}

	public void setTextbox(JTextField textbox) {
		this.textbox = textbox;
	}

	public TextboxType getTextboxtype() {
		return textboxtype;
	}

	public void setTextboxtype(TextboxType textboxtype) {
		this.textboxtype = textboxtype;
	}

	public TextBoxInfo(JTextField textbox, TextboxType textboxtype, char[] enabledKey) {
		this.textbox = textbox;
		this.textboxtype = textboxtype;
		this.enabledKey = enabledKey; // Only for blue squares
	}

	public TextBoxInfo(JTextField textbox, TextboxType textboxtype) {
		this.textbox = textbox;
		this.textboxtype = textboxtype;
		this.enabledKey = null; // Except for blue squares, every keyboard has all the keys enabled.
	}

	public char[] getEnabledKey() {
		return enabledKey;
	}

	public void setEnabledKey(char[] enabledKey) {
		this.enabledKey = enabledKey;
	}

}
