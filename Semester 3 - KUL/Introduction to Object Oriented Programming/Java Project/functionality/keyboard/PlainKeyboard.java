package functionality.keyboard;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PlainKeyboard {

	// Class for regular on-screen keyboard. This keyboard has all the letter
	// enabled.

	protected JFrame keyboardFrame; // Main Keyboard Frame.
	protected HashMap<Character, JButton> charButtonMap; // The map between alphabet characters and button.
	protected int positionX = -1; // The x coordinate of the position of keyboard in the screen.
	protected int positionY = -1; // The y coordinate of the position of keyboard in the screen.
	protected String azerty = "AZERTYUIOPQSDFGHJKLMWXCVBN"; // Azerty keyboard letter format.

	public PlainKeyboard(int x, int y) {
		setPosition(x, y); // Sets the position of keyboard frame on screen.
		initializeUI(); // Initializes the keyboard UI.
	}

	public HashMap<Character, JButton> getCharButtonMap() {
		return charButtonMap;
	}

	public void setCharButtonMap(HashMap<Character, JButton> charButtonMap) {
		this.charButtonMap = charButtonMap;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPosition(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public int getPositionY() {
		return positionY;
	}

	protected void initializeUI() {

		// Frame customization for keyboard

		keyboardFrame = new JFrame("On-Screen Keyboard");
		keyboardFrame.setSize(800, 150);
		keyboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		keyboardFrame.setLayout(new FlowLayout());
		keyboardFrame.setResizable(false);
		// Assign position to the keyboard.
		if (positionX > 0 & positionY > 0) {
			keyboardFrame.setLocation(positionX, positionY);
		} else {
			keyboardFrame.setLocationRelativeTo(null);
		}
		// Create the map between alphabet and button
		Map<Character, JButton> charButtonMap = createCBMap();
		// Add button in Azerty Style
		for (int i = 0; i < azerty.length(); i++) {
			JButton b = charButtonMap.get(azerty.charAt(i));
			keyboardFrame.add(b);
		}
		keyboardFrame.setVisible(true);
	}

	private Map<Character, JButton> createCBMap() {
		// Method to create a Map between alphabet and the button associated with it.
		
		charButtonMap = new HashMap<Character, JButton>();
		for (char i = 'A'; i <= 'Z'; i++) {
			// Add button with the alphabet as its name
			JButton button = new JButton(Character.toString(i));
			button.setName(Character.toString(i));
			button.setForeground(Color.black);
			button.setFont(new Font("Helvetica", Font.BOLD, 14));
			button.setFocusable(false);
			charButtonMap.put(i, button);
		}
		return charButtonMap;
	}

	public void dispose() {
		// Disposes the keyboard frame
		keyboardFrame.dispose();
	}

}
