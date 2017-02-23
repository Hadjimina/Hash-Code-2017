import java.util.HashSet;

public class Cache {
	int[] speedup;
	int size;
	HashSet<Integer> videosToStore;

	public void Cache(int size, int nrOfVideos) {
		this.size = size;
		speedup = new int[nrOfVideos];
		videosToStore = new HashSet<Integer>();
	}

}
