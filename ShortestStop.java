package com.p1;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class ShortestStop {
//Dijkstra Algorithm
	public int[] DijkstraPaths(int[][] adjMatrix) {
		int[] result = new int[adjMatrix.length];   //���ڴ�Ŷ���0�������������̾���
	    boolean[] used = new boolean[adjMatrix.length];  //�����ж϶����Ƿ񱻱���
	    used[0] = true;  //��ʾ����0�ѱ�����
	    for(int i = 1;i < adjMatrix.length;i++) {
	        result[i] = adjMatrix[0][i];
	        used[i] = false;
	    }

	    for(int i = 1;i < adjMatrix.length;i++) {
	        int min = Integer.MAX_VALUE;    //������ʱ��Ŷ���0��i����̾��룬��ʼ��ΪInteger�����ֵ
	        int k = 0;
	        for(int j = 1;j < adjMatrix.length;j++) {  //�ҵ�����0�����������о�����С��һ������
	            if(!used[j] && result[j] != -1 && min > result[j]) {
	                min = result[j];
	                k = j;
	            }
	        }
	        used[k] = true;    //��������С�Ķ��㣬��Ϊ�ѱ���
	        for(int j = 1;j < adjMatrix.length;j++) {  //Ȼ�󣬽�����0����������ľ���������м䶥��k֮��ľ�����бȽϣ�������̾���
	            if(!used[j]) {  //������jδ������ʱ
	                //���ȣ�����k������jҪ��ͨ�У���ʱ��������0������j�ľ�����ڶ���0��k�ٵ�j�ľ�����߶���0�޷�ֱ�ӵ��ﶥ��jʱ�����¶���0������j����̾���
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
			BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
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
