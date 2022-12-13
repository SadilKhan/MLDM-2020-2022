
public class Main {

	public static void main(String[] args) {
		String teamName=args[0];
		String fastaFile=args[1];
		
		RepoMaintenance repo= new RepoMaintenance(teamName,fastaFile);
		repo.initializeRepo();
		

	}

}
