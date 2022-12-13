package ui.boardUI;

import javax.swing.*;

import utility.Util;

import java.awt.*;
import java.util.ArrayList;

public class CluesPanel {
	/*
	 * Creates the leftside panel for clues . It contains one Main Panel and three
	 * sub Panels added to the main Panel. The three sub-panels are firstPanel(which
	 * contains only one label) and acrossPanel for across clues and downPanel for
	 * down clues
	 */

	private JPanel statementPanel; // Main Panel
	private ArrayList<String> acrossClues; // The clues for horizontal box fields.
	private ArrayList<String> downClues; // The clues for vertical box fields.
	private Util utility=new Util();

	CluesPanel(ArrayList<String> acrossClues, ArrayList<String> downClues) {
		this.acrossClues = acrossClues;
		this.downClues = downClues;
		createUI();
	}

	private void createUI() {
		// Customizes the Main Statement Panel

		// Main Panel
		statementPanel = new JPanel();
		statementPanel.setLayout(new BoxLayout(statementPanel, BoxLayout.Y_AXIS));
		statementPanel.setBounds(10, 10, 250, 700);
		statementPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// The First Panel contains only one label.
		JPanel firstPanel = new JPanel();
		JLabel firstLabel = new JLabel("Crossword Puzzle Instructions:");
		firstLabel.setForeground(new Color(76, 0, 153));// Change the color of the label
		firstLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Change the font style of the label
		firstPanel.add(firstLabel);
		// Across Panel for across statements
		JPanel acrossPanel = utility.createPanel(this.acrossClues);
		// Down Panel for down statements
		JPanel downPanel = utility.createPanel(this.downClues);
		// Add the sub-panels in the Main Panel statementPanel
		statementPanel.add(firstPanel);
		statementPanel.add(acrossPanel);
		statementPanel.add(downPanel);
	}

	public JPanel getStatementPanel() {
		return statementPanel;
	}

	public void setStatementPanel(JPanel statementPanel) {
		this.statementPanel = statementPanel;
	}

}
