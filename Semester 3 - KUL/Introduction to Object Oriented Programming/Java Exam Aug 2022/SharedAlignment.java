import java.util.ArrayList;

public class SharedAlignment extends Alignment {
	private ArrayList<SampleInformation> sharedSampleInfo=new ArrayList<SampleInformation>();
	public SharedAlignment(String fastaFileName) {
		super(fastaFileName);
		setSharedSampleInfo(super.getSampleInfo());
	}

	@Override
	public void addGenome(String identifier,String genome,String password) {
		
	}

	@Override
	public void removeGenome(String identifier,String password) {
		
			
	}

	@Override
	public String retrieveGenome(String identifier,String password) {
		return null;
		
	}

	public ArrayList<SampleInformation> getSharedSampleInfo() {
		return sharedSampleInfo;
	}

	public void setSharedSampleInfo(ArrayList<SampleInformation> sharedSampleInfo) {
		this.sharedSampleInfo = sharedSampleInfo;
	}

}
