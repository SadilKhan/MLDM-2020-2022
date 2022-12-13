package ui.boardUI;

import javax.swing.*;

import utility.*;
import functionality.listener.*;
import functionality.textbox.TextBoxInfo;

import java.util.*;

public class GameUI implements GameUIInterface {
	private int row; // Number of Rows in the Playing Field.
	private int column; // Number of Columns in the Playing Field.
	private String[][] field; // The Information about the different types of squares.
	private ArrayList<String> acrossClues; // The clues for horizontal box fields.
	private ArrayList<String> downClues; // The clues for vertical box fields.
	private JFrame gameFrame; // Main Game JFrame
	// All the textboxes in the playing field
	private ArrayList<ArrayList<TextBoxInfo>> textBoxes = new ArrayList<ArrayList<TextBoxInfo>>();
	private int totalGraySquare = 0; // Total number of Gray Squares.
	private JButton checkButton; // Button for checking.
	private String solution; // The solution word/phrase which will be checked finally
	private Util utility = new Util(); // Util object are used for various utility tasks

	public GameUI(int row, int column, String[][] field, ArrayList<String> acrossClues, ArrayList<String> downClues,
			String solution) {
		this.row = row;
		this.column = column;
		this.field = field;
		this.acrossClues = acrossClues;
		this.downClues = downClues;
		this.solution = solution;

		createUI();
	}

	private void createUI() {
		// Creates the Inferface of the puzzle game.

		// Create frame for UI
		createFrame();
		// Create Clues Panel
		createCluesPanel();
		// Create Square Field
		createSquarePanel();
		// Create Solution Panel
		createSolutionPanel();
		// Bind every square with keyboards.
		new TextBoxListener(textBoxes);
		gameFrame.setVisible(true);
	}

	private void createFrame() {
		/* Creates a JFrame upon which we will add component for UI */
		gameFrame = new JFrame("Swedish-Style Crossword Puzzle");
		gameFrame.setSize(1000, 800); // Set the size of the frame
		gameFrame.setResizable(false); // gameFrame isn't resizable.
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(null);
		gameFrame.setLocationRelativeTo(null);// Makes the frame appear in the center of the screen.
	}

	@Override
	public void createSquarePanel() {
		/*
		 * Creates the main square board. It creates an object of SquarePanel class
		 * which will form the squareboard for playing. Every square is of TextBoxInfo
		 * objects which contains the metadata JTextField object, What type of square
		 * it's(See textbox.TextBoxType) and the keys that will be enabled on the
		 * keyboard which will appear upon clicking the square.
		 */
		SquarePanel sqPanelObj = new SquarePanel(row, column, field);
		JPanel sqPanel = sqPanelObj.getSqPanel(); // Get the panel from SquarePanel object
		gameFrame.add(sqPanel); // Add the panel.
		ArrayList<ArrayList<TextBoxInfo>> boxes = sqPanelObj.getBoxes(); // Get the Squares which are of TextBoxInfo
																			// object.
		textBoxes = boxes; // Save the squares in textBoxes which contain all the square.
	}

	@Override
	public void createCluesPanel() {
		/*
		 * Creates the Clues Panel which contains the Across and Down Clues. It creates
		 * an object of CluesPanel class which creates a panel with both type of
		 * classes.
		 */
		CluesPanel cluesPanelObj = new CluesPanel(acrossClues, downClues);
		JPanel cluesPanel = cluesPanelObj.getStatementPanel(); // Get the panel with clues
		gameFrame.add(cluesPanel);

	}

	@Override
	public void createSolutionPanel() {
		/*
		 * Creates the bottom panel which contains gray squares for solution and check
		 * button for evaluating the solution
		 */
		totalGraySquare = utility.findTotalGraySquare(field); // Find Number of gray Squares
		SolutionPanel solPanelObj = new SolutionPanel(totalGraySquare); // Panel with n number of gray squares and check
																		// button
		JPanel solPanel = solPanelObj.getMainPanel(); // The solution panel
		gameFrame.add(solPanel); // Add the panel in the gameFrame
		ArrayList<TextBoxInfo> boxes = solPanelObj.getBoxes(); // Get the gray boxes in the square panel
		checkButton = solPanelObj.getCheckButton(); // Get the check button
		// Add boxes in textboxes which contains all the text boxes
		textBoxes.add(boxes); // Add the gray squares in textBoxes which contains all the squares.
		// Activate SolutionListener. This listener checks the solution when check button is pressed.
		new SolutionListener(checkButton, solution, textBoxes);

	}

}
