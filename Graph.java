import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Graph {
	
	double p;
	int[][] adjmatrix;
	int k;
	
	public Graph(double p, int k) {
		this.p = p;
		this.k = k;
	}
	
	public void initialize() {
		
		adjmatrix = new int[5][5];
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(i != j)
					adjmatrix[i][j] = 1;
			}
		}
	}
	
	public void dfs(int s, boolean[] visited) {
		
		visited[s] = true;
		
		for(int i = 0; i < 5; i++) {
			if(adjmatrix[s][i] == 1)
				if(!visited[i])
					dfs(i, visited);
		}
		
	}
	
	//Use dfs to find if there have a path in the graph
	public boolean path() {
		
		boolean haspath = true;
		boolean[] visited = new boolean[5];
		
		for(int i = 0; i < 5; i++)
			visited[i] = false;
		
		dfs(0, visited);
		
		for(int i = 0; i < 5; i++) {
			if(!visited[i])
				haspath = false;
		}
		
		return haspath;
	}
	
	public double calculate() {
		
		double sum = 1.0;
		
		if(path()) {
			for(int i = 0; i < 5; i++) {
				for(int j = i+1; j < 5; j++) {

					if(adjmatrix[i][j] == 1)
						sum = sum * p;
					else
						sum = sum * (1.0-p);
				}
			}
		}
		else
			return 0;

		return sum;
	}
	
	public double ExhaustiveEnumeration() {
		
		double r = 0;
		//Pick k number between 0 to 1024 then put them into TreeSet
		Set<Integer> Klist = new TreeSet<Integer>();
		
		//add until k unique number inside TreeSet
		while(Klist.size() < k) {
			Random ran = new Random();
			int number = ran.nextInt(1024);
			Klist.add(number);
		}

		for(int i = 0; i < 1024; i++) {
			
			initialize();
			
			//Convert i to 10 bits binary
			String number = String.format("%10s", Integer.toBinaryString(i)).replace(' ', '0');
			char[] nlist = number.toCharArray();
			int digit = 0;
			
			//Flip the system status correspond to the number that have been picked 
			if(Klist.contains(i)) {
				for(int temp = 0; temp < nlist.length; temp++) {
					if(nlist[temp] == '1')
						nlist[temp] = '0';
					else
						nlist[temp] = '1';
				}
			}
			
			//Change the adjacency matrix based on the correspond binary value
			for(int node1 = 0; node1 < 5; node1++) {
				for(int node2 = node1+1; node2 < 5; node2++) {
					if(nlist[digit] == '0') {
						adjmatrix[node1][node2] = 0;
						adjmatrix[node2][node1] = 0;
					}
				    else {
						adjmatrix[node1][node2] = 1;
						adjmatrix[node2][node1] = 1;
					}
					digit++;
				}
			}
			
			//Sum the network reliability R if exist path inside the topology
			r += calculate();
		}
		
		return r;
	}
	
	
	public static void main(String args[]) {
		
		for(int i = 0; i <= 25; i++) {
			double p = i*0.04;
			Graph g = new Graph(p, 0);
			System.out.println(g.ExhaustiveEnumeration());
		}
		System.out.println();
		//Fix p to 0.85, range k = 0,...,25:
		
		for(int k = 0; k <= 25; k++) {
			
			Graph g = new Graph(0.85, k);
			double sum = 0;
			for(int i = 0; i < 10; i++) {
				sum += g.ExhaustiveEnumeration();
			}
			System.out.println(sum/10);
		}
		

		

	}
}
