import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MyClass {

	static int[] S;
	static Endpoint[] Endpoints;
	static Cache[] Caches;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println(new File(".").getAbsoluteFile());
		readData("src/kittens.in");

		System.out.println(Array.getLength(Caches));
		
		for(int i = 0; i < Array.getLength(Caches); i++){
			System.out.println("yada "+i);
			for(int j = 0; j < S.length; j++){
				for(int e = 0; e < Array.getLength(Endpoints); e++){
					int a = Endpoints[e].datacenterLatency;
					
					
					
					if (!Endpoints[e].latencyPerCache.containsKey(i)){continue;}
					if (!Endpoints[e].videosRequestedNr.containsKey(j)){continue;}
					
					int b = (Integer) Endpoints[e].latencyPerCache.get(i);
					int c = (Integer) Endpoints[e].videosRequestedNr.get(j);
					Caches[i].speedup[j] += (a-b)*c;
				}
			}
		}
			
		for (int f = 0; f < Array.getLength(Caches); f++){
			Cache c = Caches[f];
			int remaining = c.size;
			System.out.println("remaining "+remaining);
			
			for(int g = 0; g < S.length; g++){
		
				
				int max = getMax(Caches[f].speedup);
				System.out.println("Smax "+ S[max]);
				
				
				if((remaining - S[max]) > 0){
					System.out.println("hello"+f);
					remaining -= S[max];
					Caches[f].addHash(max);
					System.out.println("hash added "+f);
				}
			}
			
		}
		
		generateResult("0");
		
	}
	
	public static int getMax(int[] speedup){
		
		int max = 0;
		for (int i = 0; i < speedup.length; i++){
			if (speedup[i]> speedup[max]){
				max = i;
			}
		}
		speedup[max]=0;		
		return max;
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
			
			System.out.println(C);
			
			Caches = new Cache[C];
			for (int i = 0; i < C; i++ ){
				Cache c = new Cache(X,V);
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

	public static void generateResult(String index){
		int nrOfCacheServers = Array.getLength(Caches);
		System.out.println("yad"+nrOfCacheServers);
		Writer wr;
		try {
			wr = new FileWriter("solution"+index+".txt");
			wr.write(String.valueOf(nrOfCacheServers));
			wr.write("\n");
			
			System.out.println("nr"+nrOfCacheServers);
			
			for (int i = 0; i < nrOfCacheServers; i++){
				wr.write(String.valueOf(i)+" ");
				StringBuilder stringBuilder = new StringBuilder();
				
				System.out.println("nr"+Caches[i].videosToStore.size());
				
			     for(int w = 0; w < Caches[i].videosToStore.size(); w++){
			    	 
			    	 int current = Caches[i].videosToStore.get(w);
			    	 stringBuilder.append(String.valueOf(current));
			         stringBuilder.append(" ");
			         System.out.println("toprint"+ current);
			         
			     }
			     wr.write(stringBuilder.toString());
			     stringBuilder.setLength(0);
			     wr.write("\n");
			}
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	} 
}
