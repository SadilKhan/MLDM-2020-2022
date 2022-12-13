import java.util.*;

public class UserAlignment extends Alignment {
	private String userName;
	private String userPosition;
	private ArrayList<SampleInformation> userSampleInfo=new ArrayList<SampleInformation>();
	public UserAlignment(String userName, String userPosition,String fastaFileName) {
		super(fastaFileName);
		userSampleInfo=super.getSampleInfo();
		this.userName=userName;
		this.setUserPosition(userPosition);
		
	}

	@Override
	public void addGenome(String identifier,String genome,String password) {
		if (password.equals(this.userName)) {
		userSampleInfo.add(new SampleInformation(identifier,genome));
		
	}}

	@Override
	public void removeGenome(String identifier,String password) {
		if (password.equals(this.userName)) {		
		for(int i=0;i<userSampleInfo.size();i++) {
			if (userSampleInfo.get(i).getIdentifier().equals(identifier)) {
				userSampleInfo.remove(i);
			}
		}		
	}}

	@Override
	public String retrieveGenome(String identifier,String password) {
		if (password.equals(this.userName)) {
		for(int i=0;i<userSampleInfo.size();i++) {
			if (userSampleInfo.get(i).getIdentifier().equals(identifier)) {
				return userSampleInfo.get(i).getGenome();
			}
		}
	}
		return null;}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<SampleInformation> getUserSampleInfo() {
		return userSampleInfo;
	}

	public void setUserSampleInfo(ArrayList<SampleInformation> userSampleInfo) {
		this.userSampleInfo = userSampleInfo;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	@Override
	public String toString() {
		return "User Name "+this.userName + "Identifier " +super.getIdentifier()+ "Genome "+ super.getGenome();
		
	}

}
