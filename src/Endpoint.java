import java.util.Map;

public class Endpoint {

	Map latencyPerCache;
	Map videosRequestedNr;
	int datacenterLatency;
	
	public Endpoint( Map latencyPerCache, int datacenterLatency){
		this.latencyPerCache = latencyPerCache;
		//this.videosRequestedNr = videosRequestedNr;
		this.datacenterLatency = datacenterLatency;
		
		
	}
	
	public void setVideosRequestedNr(Map videosRequestedNr){
		this.videosRequestedNr = videosRequestedNr;
	}
	
}
