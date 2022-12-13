package ui.filechooserUI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

interface FileChooserUIInterface {
	// Interface for FileChooserUI class
		final JButton gameStartButton = new JButton("Start Game"); // Button for opening a file dialog box
		JFrame gameOpenerFrame = new JFrame("Crossword Puzzle"); // Frame for the interface
		JPanel gameStartPanel=new JPanel();
}
