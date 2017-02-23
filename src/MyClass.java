import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyClass {

	static int[] S;
	static Endpoint[] Endpoints;
	static Cache[] Caches;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println(new File(".").getAbsoluteFile());
		readData("src/test.in");
		
		//System.out.println(Endpoints[0].size);
		

		for(int i = 0; i < Array.getLength(Caches); i++){
			for(int j = 0; j < S.length; j++){
				for(int e = 0; e < Array.getLength(Endpoints); e++){
					int a = Endpoints[e].datacenterLatency;
					int b = (Integer) Endpoints[e].latencyPerCache.get(i);
					int c = (Integer) Endpoints[e].videosRequestedNr.get(j);
					Caches[i].speedup[j] += (a-b)*c;
				}
			}
		}
	
	}
	
	public static void readData(String url){
		try {
			Scanner file = new Scanner(new File(url));
			//load initial var
			int V = file.nextInt();
			int E = file.nextInt();
			int R = file.nextInt();
			int C = file.nextInt();
			int X = file.nextInt();
			
			Caches = new Cache[X];
			for (int i = 0; i < X; i++ ){
				Cache c = new Cache(C,V);
				Caches[i] = c;
			}
			//load Videos sizes
			S = new int[V];
			for(int i = 0; i < V; i++){
				S[i] = file.nextInt();
			}	
			
			Map<Integer,Integer> videosRequestedNr = new HashMap<Integer, Integer>();
			Endpoints = new Endpoint[E];
			for (int i = 0 ; i < E ; i++){
				int L = file.nextInt();
				int K = file.nextInt();
				Map<Integer,Integer> latencyPerCache = new HashMap<Integer, Integer>();
				
				for(int j = 0; j < K; j++){
					latencyPerCache.put(file.nextInt(),file.nextInt());
				}
				 //videosRequestedNr not yet initializeed
				Endpoints[i] = new Endpoint( latencyPerCache,L);
			}
			
			for(int j = 0; j < R; j++){
				Integer vidID = file.nextInt();
				Integer EndpointID = file.nextInt();
				Integer numberOfRequests = file.nextInt();
				videosRequestedNr.put(vidID,numberOfRequests);
				
				Endpoint currentEndpoint = Endpoints[EndpointID];
				currentEndpoint.setVideosRequestedNr(videosRequestedNr);
			}
			
					
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
