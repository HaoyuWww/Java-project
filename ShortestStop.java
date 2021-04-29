import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class ShortestStop {
//Dijkstra Algorithm
	public static List<String> ShortestPath(List<List<String>> Edge,List<List<String>> EdgeCost, String startStopId, String endStopId) {
		int EdgeSize = Edge.size();
		//System.out.println(EdgeSize);
		int EdgeCostSize =EdgeCost.size();
		List<String> ans = new ArrayList();
		//stop_times.txt
		for(int i=0;i<EdgeSize;i++) {
			//System.out.println(Edge.get(i).get(1));
			if(Edge.get(i).get(1).equals(startStopId)){
				for(int j=i;j<EdgeSize-1;j++) {
					if (Edge.get(j).get(0).equals(Edge.get(j+1).get(0))){
						String tmp = Edge.get(j).get(1);
						//System.out.println(tmp);
						ans.add(tmp);
						if(Edge.get(j).get(1).equals(endStopId)) return ans;
					}
					
				}
			}
		}
		return ans;
	}
		
		//transfers.txt
		/*
		List<List<String>> EdgeCostTrans =  new ArrayList();
		for(int i=0;i<EdgeCostSize;i++) {
			int j = i;
			List<String> tmp = new ArrayList();
			
			
		}
		*/
	public static List<String> CostShortestPath(List<List<String>> Edge,List<List<String>> EdgeCost, String startStopId, String endStopId) {
		
		//System.out.println(EdgeSize);
		int EdgeCostSize =EdgeCost.size();
		List<String> ans = new ArrayList();
		//stop_times.txt
        Double[] shortPath = new Double[EdgeCostSize];
        for(int i=0;i<EdgeCostSize;i++) {
        shortPath[i] = 0.0;//startStopId
        }
        String path[] = new String[EdgeCostSize];
        int visited[] = new int[EdgeCostSize];
        visited[0] = 1;
        int end =0;
        for(int count = 1;count<EdgeCostSize;count++){
            //int k=-1;
            Double dmin = 1000.0;
            for(int i=1;i<EdgeCostSize;i++){
                if(visited[i]==0 && (Double.parseDouble(EdgeCost.get(i).get(2))<dmin)){
                  dmin = Double.parseDouble(EdgeCost.get(i).get(2))+shortPath[i-1];
                  shortPath[i] = dmin;
                  path[count] = EdgeCost.get(i).get(1);
                  //dmin = Integer.valueOf(EdgeCost.get(i).get(2)).intValue();
                	//k=i;
                }
            }
            visited[count] = 1;
        }
            
            //shortPath[k] = dmin;
            //visited[k] = 1;
            
            for(int i=0;i<EdgeCostSize;i++){
                //if(visited[i]==0 &&EdgeCost.get(i).get(1).equals(startStopId)){
                  //  path[i] = EdgeCost.get(i).get(1);
               // }
                if (EdgeCost.get(i).get(1).equals(endStopId)){
                	//System.out.println(EdgeCost.get(i).get(1));
                	end = i+1;
                	break;
                }
            }
            \\System.out.println(end);
            for(int j=1;j<end;j++) {
                ans.add(path[j]);
                
            }
        return ans;
	}
	// get edge_cost from transfers.txt 
			public static List<List<String>> getCost(File file) {
			List<List<String>> lines = new ArrayList<>();
			StringBuilder result = new StringBuilder();
			try {
			BufferedReader br = new BufferedReader(new FileReader(file));//BufferReader
			String line;
			br.readLine();
			line= br.readLine();
			while(line!=null){
			   String[] tmp =line.split(",");
			   List<String> tmp_ = new ArrayList<>();
			   tmp_.add(tmp[0]);
			   tmp_.add(tmp[1]);
			   if(Integer.parseInt(tmp[2]) == 0) tmp_.add("0");
			   else //try{
				   tmp_.add(String.valueOf(Integer.parseInt(tmp[3])*0.01));
			   //}catch(Exception e) {
				//   e.printStackTrace();
			  // }
			   
			   
			   lines.add(tmp_);
			   //lines.add(tmp[1]);
			   line = br.readLine();
			}
			br.close(); 
			}catch(Exception e) {
				e.printStackTrace();
			}
			return lines;	
		}
//get edge cost from stop_time.txt
			public static List<List<String>> getEdge(File file) {
				List<List<String>> lines = new ArrayList<>();
				StringBuilder result = new StringBuilder();
				try {
				BufferedReader br = new BufferedReader(new FileReader(file));//BufferReader
				String line;
				br.readLine();
				line= br.readLine();
				while(line!=null){
				   String[] tmp =line.split(",");
				   List<String> tmp_ = new ArrayList<>();
				   tmp_.add(tmp[0]);
				   tmp_.add(tmp[3]);				   
				   lines.add(tmp_);
				   //lines.add(tmp[1]);
				   line = br.readLine();
				}
				br.close(); 
				}catch(Exception e) {
					e.printStackTrace();
				}
				return lines;	
			}
	
	public static void main(String ards[]) {
		File costfile = new File("C:\\Users\\Lenovo\\Desktop\\computer science\\project\\transfers.txt");
                File EdgeFile = new File("C:\\Users\\Lenovo\\Desktop\\computer science\\project\\stop_times.txt");
		
		List<List<String>> EdgeCost = getCost(costfile);
		List<List<String>> Edge = getEdge(EdgeFile);
		int costSize = EdgeCost.size();
		int edgeSize =Edge.size();
		List<String> ans= new ArrayList();
		String startStopId = "1888";//start ID
		String endStopId = "1885";//end ID
		ans = CostShortestPath(Edge,EdgeCost, startStopId, endStopId);//OUTPUT ANS；
		//Stop and COST is all in ans String.
		//print
		System.out.printf("From StartStopId %s ",startStopId);
		System.out.printf("To EndStopId %s is \n",endStopId);
		// hash remove element
		List<String> ansNew = new ArrayList<String>(new HashSet(ans));
		for (int i=0;i<ansNew.size();i++) {
		System.out.println(ansNew.get(i));
		}
	}

}
