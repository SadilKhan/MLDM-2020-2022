package functionality.listener;

import java.awt.event.*;
import java.nio.file.*;
import java.util.ArrayList;

import javax.swing.*;

import functionality.textbox.TextBoxInfo;

public class SolutionListener implements ActionListener {

	/*
	 * This class gets activated when 'check' Button is clicked. This checks whether
	 * the solution present in the bottom gray squares are correct.
	 */

	private JButton checkButton;
	private String solution;
	private ArrayList<ArrayList<TextBoxInfo>> textBoxes;

	public SolutionListener(JButton checkButton, String solution, ArrayList<ArrayList<TextBoxInfo>> textBoxes) {
		this.checkButton = checkButton;
		this.solution = solution;
		this.textBoxes = textBoxes;
		this.checkButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Action for the check button. Returns a small Option Pane if the answer is correct or incorrect.
		 */
		// Get the directory where images are saved
		Path currentRelativePath = Paths.get("");
		String pathString = currentRelativePath.toAbsolutePath().toString();
		// Find the path where image is stored
		if (!pathString.contains("src")) {
			pathString = pathString + "/src/Files/";
		} else {
			pathString = pathString + "/Files/";
		}
		int totalGraySquare = textBoxes.get(textBoxes.size() - 1).size();
		// Get the solution from the letters in the bottom gray squares.
		String userSolution = "";
		for (int i = 0; i < totalGraySquare; i++) {
			JTextField tempTextField = textBoxes.get(textBoxes.size() - 1).get(i).getTextbox();
			String text = tempTextField.getText().toLowerCase();
			if (text != null) {
				userSolution += text;
			} else {
				// If there are no letters in squares, warning message is showed
				ImageIcon winnerImage = new ImageIcon(pathString + "wrong_answer.png");
				JOptionPane.showMessageDialog(null, "Sorry!! Wrong Answer", "Wrong Answer", JOptionPane.WARNING_MESSAGE,
						winnerImage);
			}
		}
		// Check the solution
		if (solution.matches(userSolution)) {
			ImageIcon winnerImage = new ImageIcon(pathString + "winner_emoji.png");
			JOptionPane.showMessageDialog(null, "Yayy!! Correct Answer", "Congratulations", JOptionPane.PLAIN_MESSAGE,
					winnerImage);
		} else {
			ImageIcon winnerImage = new ImageIcon(pathString + "wrong_answer.png");
			JOptionPane.showMessageDialog(null, "Sorry!! Wrong Answer", "Wrong Answer", JOptionPane.WARNING_MESSAGE,
					winnerImage);
		}

	}

}
