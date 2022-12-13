package ui.boardUI;

import utility.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

import functionality.textbox.*;

public class SquarePanel {
	/*
	 * Creates the Square board/Playing field. Contains one Main Panel and row x
	 * column numbers of squares with modified on-screen keyboard attached with each
	 * one of them.
	 */

	private String[][] field; // Contains information about type of squares
	private int row; // Number of rows for Square board
	private int column; // Number of columns for Square board
	private JPanel fieldPanel; // Panel which contains the squares/textboxes
	private char[] enabledKey; // The keys to be enabled in Keyboard
	private ArrayList<ArrayList<TextBoxInfo>> textBoxes; // Squares - TextBoxInfo object
	Util util = new Util(); // Util class contains necessary utility methods

	SquarePanel(int row, int column, String[][] field) {
		this.row = row;
		this.column = column;
		this.field = field;
		// Create the UI for SquarePanel
		createUI();

	}

	private void createUI() {
		// Main Panel
		fieldPanel = new JPanel(new GridLayout(row, column));
		fieldPanel.setBounds(260, 5, 740, 695);

		// Add row x column textfields in the fieldPanel
		textBoxes = new ArrayList<ArrayList<TextBoxInfo>>();

		for (int i = 0; i < row; i++) {

			// boxes is an array which contains the squares for every row.
			ArrayList<TextBoxInfo> boxes = new ArrayList<TextBoxInfo>();

			for (int j = 0; j < column; j++) {
				/*
				 * (1). We have 4 types of boxes. O --> Normal boxes ( All the keys in the
				 * keyboard is enabled) H --> Help Boxes ( The keyboard has only four random
				 * keys and the answer key enabled) X --> Black Boxes (No keyboard) S -->
				 * Special Boxes(All the keys in the keyboard is enabled). For any type of
				 * squares, the object of (2). TextBox class assigned for it (See
				 * functionality.textbox) is created. (3). After the text box type is identified
				 * and object of that type is created , add the textBox to the panel.
				 */
				if (field[i][j].contains("H")) {
					/* Help Boxes */
					// Generate an array with four random keys and the answer key. Only these keys
					// will be enabled.
					enabledKey = util.getRandomKeys(this.field[i][j].charAt(2));
					BlueTextbox btbObj = new BlueTextbox();
					// Add the TextBoxInfo object created for this square
					boxes.add(new TextBoxInfo(btbObj.getTextBox(), btbObj.getTextboxType(), enabledKey));
					fieldPanel.add(btbObj.getTextBox());// add the textBox to the panel
				} else if (field[i][j].contains("X")) {
					/* Black/Noir Boxes. Contains no keyboard */
					BlackTextbox ntbObj = new BlackTextbox();// add the textBox to the panel
					boxes.add(new TextBoxInfo(ntbObj.getTextBox(), ntbObj.getTextboxType()));
					fieldPanel.add(ntbObj.getTextBox());
				} else if (field[i][j].contains("S")) {
					/* Special Boxes */
					GrayTextbox gtbObj = new GrayTextbox();
					boxes.add(new TextBoxInfo(gtbObj.getTextBox(), gtbObj.getTextboxType()));
					fieldPanel.add(gtbObj.getTextBox());// add the textBox to the panel
				} else {
					/* Regular Boxes */
					PlainTextbox ptbObj = new PlainTextbox();
					boxes.add(new TextBoxInfo(ptbObj.getTextBox(), ptbObj.getTextboxType()));
					fieldPanel.add(ptbObj.getTextBox()); // add the textBox to the panel

				}

			}
			// textBoxes contains all the squares.
			textBoxes.add(boxes);

		}
	}

	public JPanel getSqPanel() {
		return fieldPanel;

	}

	public ArrayList<ArrayList<TextBoxInfo>> getBoxes() {
		return textBoxes;
	}

}
