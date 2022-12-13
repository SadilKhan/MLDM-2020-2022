import java.io.*;
import java.util.*;

public abstract class Alignment {
	private String identifier;
	private String genome;
	private ArrayList<SampleInformation> sampleInfo=new ArrayList<SampleInformation>();

	public ArrayList<SampleInformation> getSampleInfo() {
		return sampleInfo;
	}

	public void setSampleInfo(ArrayList<SampleInformation> sampleInfo) {
		this.sampleInfo = sampleInfo;
	}

	public Alignment(String fastaFileName) {
		File fastaFile=new File(fastaFileName);

		try {
			Scanner readScanner=new Scanner(fastaFile);
			String identifier,genome;
			while(readScanner.hasNext()) {
				identifier=readScanner.next();
				genome=readScanner.next();
				sampleInfo.add(new SampleInformation(identifier,genome));
			}
			
			readScanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("An Error Occurred "+e);
		}


	}



	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getGenome() {
		return genome;
	}

	public void setGenome(String genome) {
		this.genome = genome;
	}
	protected void addGenome(String identifier,String genome, String password) {
		sampleInfo.add(new SampleInformation(identifier,genome));

	}


	protected void removeGenome(String identifier,String password) {
		for(int i=0;i<sampleInfo.size();i++) {
			if (sampleInfo.get(i).getIdentifier().equals(identifier)) {
				sampleInfo.remove(i);
			}
		}	
	}

	protected String retrieveGenome(String identifier,String password) {
		for(int i=0;i<sampleInfo.size();i++) {
			if (sampleInfo.get(i).getIdentifier().equals(identifier)) {
				return sampleInfo.get(i).getGenome();
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return "Identifier" + this.identifier+ "Genome "+ this.genome;
		
	}
}