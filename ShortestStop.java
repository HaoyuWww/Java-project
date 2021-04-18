package com.p1;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ShortestStop {
//Dijkstra Algorithm
	public int[] DijkstraPaths(int[][] adjMatrix) {
		int[] result = new int[adjMatrix.length];   //用于存放顶点0到其它顶点的最短距离
	    boolean[] used = new boolean[adjMatrix.length];  //用于判断顶点是否被遍历
	    used[0] = true;  //表示顶点0已被遍历
	    for(int i = 1;i < adjMatrix.length;i++) {
	        result[i] = adjMatrix[0][i];
	        used[i] = false;
	    }

	    for(int i = 1;i < adjMatrix.length;i++) {
	        int min = Integer.MAX_VALUE;    //用于暂时存放顶点0到i的最短距离，初始化为Integer型最大值
	        int k = 0;
	        for(int j = 1;j < adjMatrix.length;j++) {  //找到顶点0到其它顶点中距离最小的一个顶点
	            if(!used[j] && result[j] != -1 && min > result[j]) {
	                min = result[j];
	                k = j;
	            }
	        }
	        used[k] = true;    //将距离最小的顶点，记为已遍历
	        for(int j = 1;j < adjMatrix.length;j++) {  //然后，将顶点0到其它顶点的距离与加入中间顶点k之后的距离进行比较，更新最短距离
	            if(!used[j]) {  //当顶点j未被遍历时
	                //首先，顶点k到顶点j要能通行；这时，当顶点0到顶点j的距离大于顶点0到k再到j的距离或者顶点0无法直接到达顶点j时，更新顶点0到顶点j的最短距离
	                if(adjMatrix[k][j] != -1 && (result[j] > min + adjMatrix[k][j] || result[j] == -1))
	                    result[j] = min + adjMatrix[k][j];
	            }
	        }
	    }
	    return result;
	}
	// get edge_cost from transfers.txt 
			public static List<List> getCost(File file) {
			List<List> lines = new ArrayList<>();
			StringBuilder result = new StringBuilder();
			try {
			BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
			String line;
			br.readLine();
			line= br.readLine();
			while(line!=null){
			   String[] tmp =line.split(",");
			   List tmp_ = new ArrayList<>();
			   tmp_.add(tmp[0]);
			   tmp_.add(tmp[1]);
			   if(Integer.parseInt(tmp[2]) == 0) tmp_.add("0");
			   else //try{
				   tmp_.add((Integer.parseInt(tmp[3]))*0.01);
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
	
	public static void main(String ards[]) {
		File file = new File("D:/project/inputs/transfers.txt");
		
		//read .txt data
		
		List<List> EdgeCost = getCost(file);
		int size = EdgeCost.size();
		for(int i =0; i<size;i++) {
			List ans = EdgeCost.get(i);
			for(int j =0; j<3;j++) {
			
		    System.out.println(ans.get(j));
			}
		}
	}


}
