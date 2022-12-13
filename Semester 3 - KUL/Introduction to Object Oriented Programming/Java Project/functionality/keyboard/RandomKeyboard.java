package functionality.keyboard;

import utility.Util;

public class RandomKeyboard extends PlainKeyboard {

	/*
	 * Extension of plain keyboard. Class for Keyboard with 4 random + one user
	 * provided clickable buttons.
	 */

	private char[] enabledKey; // The letters to have clickable buttons.

	public RandomKeyboard(int x, int y, char[] enabledKey) {
		super(x, y); // Position of the keyboard.
		this.enabledKey = enabledKey;
		afterEffects(); // Modify the keyboard
	}

	private void afterEffects() {

		// Further modification in PlainKeyboard to create suitable keyboard

		Util util = new Util();
		for (char c : super.azerty.toCharArray()) {
			// If a character is not in enabledKey, disable the button associated with the
			// character
			if (!util.containsValue(enabledKey, c)) {
				super.charButtonMap.get(c).setEnabled(false);

			}
		}
	}
}
