package ui.filechooserUI;

import javax.swing.*;

import utility.InfoExtractor;

import java.awt.*;
import java.io.IOException;
import java.awt.event.*;

public class FileChooserUI implements FileChooserUIInterface, ActionListener {
	// The welcome UI for starting the game.
	private int width = 400;
	private int height = 400;

	public void initilizeUI() {
		// Initialize Frame
		addFrame();
		// Customize Frame
		customiseFrame();
		// Add the panels
		gameOpenerFrame.setVisible(true);
	}

	private void customiseFrame() {
		// Method for customizing Frame

		// Customize Panel
		gameStartPanel.setLayout(null);
		// Custmoze start button to position in the middle
		int buttonWidth = 80;
		int buttonHeight = 20;
		gameStartButton.setBounds(
				new Rectangle((width - buttonWidth) / 2, (height - buttonHeight) / 2, buttonWidth, buttonHeight));
		gameStartButton.setSize(new Dimension(buttonWidth, buttonHeight));
		gameStartButton.setFocusable(false);
		gameStartButton.addActionListener(this);
		gameStartPanel.add(gameStartButton); // Add the button in the panel
		gameOpenerFrame.add(gameStartPanel); // Add the panel in the frame
	}

	private void addFrame() {
		// Add Frame
		gameOpenerFrame.setTitle("Welcome to the Crossword Puzzle Game");
		gameOpenerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOpenerFrame.setSize(width, height);
		gameOpenerFrame.setLayout(new BorderLayout());
		gameOpenerFrame.setResizable(false);
		gameOpenerFrame.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		// The config file stores the filename for constructing the game and 

		Config configFile = new Config();
		String fileName = configFile.getProperty("filename");
		String solution = configFile.getProperty("solution");
		System.out.println(fileName);
		InfoExtractor extractor = new InfoExtractor();
		try {
			extractor.TxtExtractor(fileName, solution);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
