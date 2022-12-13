import javax.swing.SwingUtilities;

import ui.filechooserUI.FileChooserUI;

public class Main {
	// The Main class to initiate the game interface

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				FileChooserUI ui = new FileChooserUI();
				ui.initilizeUI();
			}
		});
	}
}
