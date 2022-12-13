
public class SampleInformation {
	private String identifier;
	private String genome;

	public SampleInformation(String identifier,String genome) {
		this.identifier=identifier;
		this.genome=genome;
		
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

}
