package ui.filechooserUI;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class Config {
	private Properties file=new Properties();
	
	Config(){
		load();
	}
	
	private void load() {
		// Find the path where default txt is stored
		Path currentRelativePath = Paths.get("");
		String pathString = currentRelativePath.toAbsolutePath().toString();
		if (!pathString.contains("src")) {
			pathString = pathString + "/src/Files/puzzle-1-adjusted.txt";
		} else {
			pathString = pathString + "/Files/puzzle-1-adjusted.txt";
		}
		// Set the file name
		file.setProperty("filename", pathString);
		// Set the solution
		file.setProperty("solution", "southpark");
	}
	
	public String getProperty(String property) {
		return file.getProperty(property);

	}
		

	

}
