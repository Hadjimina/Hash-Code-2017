import java.util.ArrayList;
import java.util.HashSet;

public class Cache {
	
	int size;
	int[] speedup;
	ArrayList<Integer> videosToStore;

	public Cache(int size, int nrOfVideos) {
		this.size = size;
		speedup = new int[nrOfVideos];
		videosToStore = new ArrayList<>();
	}
	
	public void addHash(int x ){
		this.videosToStore.add(x);
	}

}
