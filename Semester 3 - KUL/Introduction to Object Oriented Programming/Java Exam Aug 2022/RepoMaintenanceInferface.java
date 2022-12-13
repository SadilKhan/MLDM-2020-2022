
public interface RepoMaintenanceInferface {
	
	void addUserAlignment(String userName,String userPosition,String fastaFile);
	void removeUserAlignment(String userName);
	void addSharedAlignment();
	void removeSharedAlignment();
	void promoteToSharedAlignment(String password,String userName); // password is team position
	UserAlignment retrieveUserAlignment(String userName);
	Alignment retrieveSharedAlignment(String userName);
}
