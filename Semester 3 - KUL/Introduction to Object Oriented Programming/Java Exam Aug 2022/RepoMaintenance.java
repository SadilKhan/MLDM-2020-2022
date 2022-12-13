import java.io.*;
import java.util.*;

public class RepoMaintenance implements RepoMaintenanceInferface {
	private String teamName;
	private String fastaFile;
	private ArrayList<UserAlignment> userAlFiles=new ArrayList<UserAlignment>();
	private Alignment sharedAl =null;
	
	public RepoMaintenance(String teamName,String fastaFile) {
		this.teamName=teamName;
		this.fastaFile=fastaFile;
		
	}
	
	public void initializeRepo() {
		File teamFile= new File(this.teamName);
		
		try {
			Scanner readScanner= new Scanner(teamFile);
			String line;
			String[] tokens;
			line=readScanner.nextLine();
			while(readScanner.hasNext()) {
				line=readScanner.nextLine();
				tokens=line.split(" ");
				userAlFiles.add(new UserAlignment(tokens[1],tokens[0],fastaFile));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("An Error Occurred "+ e);
		}
		
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getFastaFile() {
		return fastaFile;
	}

	public void setFastaFile(String fastaFile) {
		this.fastaFile = fastaFile;
	}

	public ArrayList<UserAlignment> getUserAlFiles() {
		return userAlFiles;
	}

	public void setUserAlFiles(ArrayList<UserAlignment> userAlFiles) {
		this.userAlFiles = userAlFiles;
	}

	@Override
	public void addUserAlignment(String userName, String userPosition,String fastaFile) {
		userAlFiles.add(new UserAlignment(userName, userPosition, fastaFile));
		
	}

	@Override
	public void removeUserAlignment(String userName) {
		for(int i=0;i<userAlFiles.size();i++) {
			if (userAlFiles.get(i).getUserName().equals(userName)) {
				userAlFiles.remove(i);
			}
		}
		
	}

	@Override
	public void addSharedAlignment() {
		setSharedAl(new SharedAlignment(fastaFile));
		
	}

	@Override
	public void removeSharedAlignment() {
		setSharedAl(null);
		
	}

	@Override
	public void promoteToSharedAlignment(String password, String userName) {
		if (password.equals("TeamLead")) {
			setSharedAl(retrieveUserAlignment(userName));
		}
		
	}

	@Override
	public UserAlignment retrieveUserAlignment(String userName) {
		for(int i=0;i<userAlFiles.size();i++) {
			if (userAlFiles.get(i).getUserName().equals(userName)) {
				return userAlFiles.get(i);
			}
		}
		return null;
	}

	public Alignment getSharedAl(String password) {
		return sharedAl;
	}

	public void setSharedAl(Alignment sharedAl) {
		this.sharedAl = sharedAl;
	}

	@Override
	public Alignment retrieveSharedAlignment(String userName) {
		return sharedAl;
	}
	

}
