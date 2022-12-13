package utility;

import java.util.*;
import javax.swing.*;
import functionality.keyboard.KeyboardType;
import functionality.textbox.TextboxType;
import java.awt.*;

public class Util {
	// Class for various utility function

	public boolean containsValue(char[] list, char value) {
		// Checks if a value is in a list

		for (char a : list) {
			if (a == value) {
				return true;
			}
		}
		if (list.length == 0) {
			return true;
		}
		return false;
	}

	public JPanel createPanel(ArrayList<String> clues) {

		// Panel for Across clues and Down Clues
		JPanel aPanel = new JPanel();
		aPanel.setLayout(new BoxLayout(aPanel, BoxLayout.Y_AXIS)); // Box Layout is used for vertical positioning of
																	// labels
		// Add the clues in the aPanel
		for (int i = 0; i < clues.size(); i++) {
			/*
			 * Every item in the clues except the first item is of the form n.clue where n
			 * is a number which is colored differently than clue. The First item is either
			 * Across or Down. A panel is created for every item. So, the item is split and
			 * positioned separately in a panel. To efficiently position the two strings,
			 * borderlayout is used.
			 * 
			 */
			String[] splitClues = clues.get(i).split("\\.", -1);
			// Add a dot to the number. n--> n.
			if (splitClues.length > 1) {
				splitClues[0] = splitClues[0] + ".";
			}
			;
			// For every item in the Across/Down lists, a panel is created
			JPanel tempPanel = new JPanel(new BorderLayout());
			for (int j = 0; j < splitClues.length; j++) {
				JLabel tempLabel = new JLabel(splitClues[j]);

				if (j == 0) {
					// Change the color for Across word and the bullets
					tempLabel.setForeground(new Color(76, 0, 153));
					tempLabel.setFont(new Font("Arial", Font.BOLD, 12));
					tempPanel.add(tempLabel, BorderLayout.WEST);
				} else {
					tempLabel.setFont(new Font("Arial", Font.PLAIN, 12));
					tempPanel.add(tempLabel, BorderLayout.CENTER);
				}
			}
			// Add each clue statements in the main panels
			aPanel.add(tempPanel);
		}
		return aPanel;

	}

	public int findTotalGraySquare(String[][] field) {

		// Finds number of Gray/Special Squares in a board. Required for creating the
		// solution panel.

		int totalGraySquare = 0;
		int row = field.length;
		int column = field[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (field[i][j].contains("S")) {

					totalGraySquare += 1;
				}
			}
		}
		return totalGraySquare;
	}

	public char[] getRandomKeys(char c) {
		/*
		 * Generates an array of characted which contains user-provided character and
		 * four random characters.
		 * 
		 */
		ArrayList<Character> hintKeys = new ArrayList<Character>();
		hintKeys.add(c);
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		while (hintKeys.size() < 5) {
			Random rd = new Random();
			char letter = abc.charAt(rd.nextInt(abc.length()));
			if (!hintKeys.contains(letter)) {
				hintKeys.add(letter);
			}
		}
		// Transform the ArrayList to Array
		char[] hintKeyArrays = new char[hintKeys.size()];
		for (int i = 0; i < hintKeys.size(); i++) {
			hintKeyArrays[i] = hintKeys.get(i);
		}
		return hintKeyArrays;
	}

	public Map<TextboxType, KeyboardType> tbtypeTokeyboard() {
		/*
		 * Method for mapping the type of squares to type of keyboard.(Check
		 * textbox.TextBoxType, keyboard.KeyboardType)
		 */

		Map<TextboxType, KeyboardType> map = new HashMap<TextboxType, KeyboardType>();
		map.put(TextboxType.REGULAR, KeyboardType.PLAIN); // Regular white squares have plain keyboard
		map.put(TextboxType.BLUE, KeyboardType.RANDOM); // Blue help squares have keyboard with 5 buttons enabled
		map.put(TextboxType.GRAY, KeyboardType.PLAIN); // Gray Squares also have plain keyboard
		return map;

	}

}
