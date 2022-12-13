package utility;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

import ui.boardUI.*;
import ui.filechooserUI.FileChooserUI;

public class InfoExtractor {

	// Class to extract relevant information from supported txt file

	private BufferedReader txtFile;

	public void TxtExtractor(String path, String solution) throws IOException {
		// The method to extract information from txt file

		BufferedReader file = new BufferedReader(new FileReader(new File(path)));// Read the TXT file

		String[] shapeInfo = file.readLine().split(" ", -1); // The shape information of the playing field

		try {
			int row = Integer.parseInt(shapeInfo[0]); // Get the number of rows of the puzzle
			int column = Integer.parseInt(shapeInfo[1]); // Get the number of columns of the puzzle
			// Extract the box types
			String[][] field = new String[row][column];
			// The clues in the Across Column
			ArrayList<String> acrossClues = new ArrayList<String>();
			// The clues in the Down Column
			ArrayList<String> downClues = new ArrayList<String>();
			// Current sentence from file.readline()
			String curSentence = "";
			// Extract the field information - it contains the text box types
			for (int i = 0; i < row; i++) {
				field[i] = file.readLine().split(" ", -1);
			}
			// Down clues
			while (!curSentence.equals("DOWN")) {
				curSentence = file.readLine();
				acrossClues.add(curSentence);
			}
			// Across Clues
			acrossClues.remove(acrossClues.size() - 1);// Remove the DOWN keyword
			while (curSentence != null) {
				downClues.add(curSentence);
				curSentence = file.readLine();
			}
			// Initialize the GameUI
			new GameUI(row, column, field, acrossClues, downClues, solution);

		} catch (Exception e) {
			// If wrong txt is provided then open the Main File Opener again.
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Wrong Txt file!", "Warning", JOptionPane.WARNING_MESSAGE);
			new FileChooserUI();
		}
		file.close(); // close the file.

	}

	public BufferedReader getTxtFile() {
		return txtFile;
	}

	public void setTxtFile(BufferedReader txtFile) {
		this.txtFile = txtFile;
	}
}
