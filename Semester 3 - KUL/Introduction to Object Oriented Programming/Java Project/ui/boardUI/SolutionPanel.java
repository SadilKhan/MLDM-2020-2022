package ui.boardUI;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import functionality.textbox.*;

public class SolutionPanel {
	/*
	 * Creates the bottom gray squares and check button. It has one Main Panel and
	 * two panels. One for the squares and one for the button
	 */

	private ArrayList<TextBoxInfo> boxes = new ArrayList<TextBoxInfo>(); // Gray squares for main answering field
	private int totalGraySquare; // Number of Gray Squares
	private JButton checkButton; // The check button for final evaluation
	private JPanel mainPanel; // The main panel for gray squares and the check button

	SolutionPanel(int totalGraySquare) {
		this.totalGraySquare = totalGraySquare;
		createUI(); // Creates the Solution Panel interface
	}

	private void createUI() {

		// Main Panel
		mainPanel = new JPanel(new FlowLayout());
		mainPanel.setBounds(340, 700, 660, 50);
		// Sub-Panel for gray squares
		JPanel checkPanel = new JPanel(new GridLayout(1, totalGraySquare + 1));
		// Number of Gray squares is saved in the totalGraySquare. Add that many gray
		// squares
		for (int i = 0; i < totalGraySquare; i++) {
			GrayTextbox tbObj = new GrayTextbox();
			JTextField tb = tbObj.getTextBox();
			tb.setPreferredSize(new Dimension(50, 50));
			tb.setToolTipText("Enter the character from the gray boxes in the playing field");
			checkPanel.add(tb); // Add text boxes in the sub-panel for gray boxes
			boxes.add(new TextBoxInfo(tb, tbObj.getTextboxType())); // Keep all the squares in boxes array.
		}
		// The sub panel for the button.
		JPanel checkButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		checkButton = new JButton("Check"); // The check button
		checkButton.setFocusable(false); // Gets rid of the border
		checkButton.setSize(10, 10);
		checkButton.setToolTipText("Click here to check whether your solution is right");
		checkButtonPanel.add(checkButton);
		checkPanel.add(checkButtonPanel);
		// Add the sub-panels to the main panels.
		mainPanel.add(checkPanel);
		mainPanel.add(checkButtonPanel);

	}

	public ArrayList<TextBoxInfo> getBoxes() {
		return boxes;
	}

	public JButton getCheckButton() {
		return checkButton;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

}
