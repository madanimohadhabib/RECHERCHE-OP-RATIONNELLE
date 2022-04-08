// Java program for implementation of Ford Fulkerson
// algorithm
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.LinkedList;

class Exo03_opl {
	static final int V = 12; // Nombre de noeuds

	
	boolean bfs(int rGraph[][], int s, int t, int parent[])
	{
		
		 //crée un tableau des noeus visités avec le marquage des non visités
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; ++i)
			visited[i] = false;

		// Création de la qeue
		LinkedList<Integer> queue
			= new LinkedList<Integer>();
		queue.add(s);
		visited[s] = true;
		parent[s] = -1;

		
		while (queue.size() != 0) {
			int u = queue.poll();

			for (int v = 0; v < V; v++) {
				if (visited[v] == false
					&& rGraph[u][v] > 0) {
					if (v == t) {
						parent[v] = u;
						return true;
					}
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}

		
		return false;
	}

	
	int fordFulkerson(int graph[][], int s, int t)
	{
		int u, v;
		//crée un graph qui contient les noeud et les valeur associé au exercice
		
		int rGraph[][] = new int[V][V];

		for (u = 0; u < V; u++)
			for (v = 0; v < V; v++)
				rGraph[u][v] = graph[u][v];

		
		int parent[] = new int[V];

		int flot_max = 0; 

		
		while (bfs(rGraph, s, t, parent)) {
			// trouvé le min capacite tout au long le pattern
			
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				path_flow
					= Math.min(path_flow, rGraph[u][v]);
			}

			
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= path_flow;
				rGraph[v][u] += path_flow;
			}

			// ajouter le flot qui convient au chemain
			flot_max += path_flow;
		}

		// return la valeur fu flow >=0
                return flot_max;
        }

	// test de la fonction
	public static void main(String[] args)
		throws java.lang.Exception
	{
		// creation du graph de notre exercice
		int graph[][] = new int[][] {
	/* les noeud du notre graphe:
                    
                    */
                    /* 11 */{ 0, 35, 25, 0, 0, 0,0,0,0,0,0,0 }, // position 0
                 /* 1 */    { 0, 0, 0, 20, 0,15,12,0,0,0,0,0 },  // position 1
                  /* 2 */   { 0, 0, 0, 0,0, 6, 22,0,0,0,0,0 },   // position 2
                    
		  /* 3 */   { 0,0, 0, 0, 15, 10, 0,0,0,0,0,0 },   // position 3
                   /* 4 */  { 0,0, 0, 0, 0, 0, 0,0,7,10,0,0 },   // position 4
		   /* 5 */  { 0,0, 0, 0, 0, 0, 0 ,0,10,15,15,0},   // position 5
                  /* 6 */   { 0,0, 0, 0, 0, 0, 0,22,0,0,0,0 },   // position 6
                        
                  /* 7 */   { 0,0, 0, 0, 0, 0, 0,0,0,10,10,0 },  // position 7
                   /* 8 */  { 0,0, 0, 0, 0, 0, 0,0,0,0,0,18 },   // position 8
	          /* 9 */   { 0, 0, 0, 0, 0, 0,0,0,0,0,0,15 },   // position 9
                    /* 10 */ { 0, 0, 0, 0, 0, 0,0,0,0,0,0,20 },   // position 10
	          /* 12 */   { 0, 0, 0, 0, 0, 0,0,0,0,0,0,0 }   // position 11
                        
                        // les positions sont utiliser dans l'appel de notre algorithme 
                        // dans notre cas apartir de noeud 11 jusqau 12 (voir exo3) => position 0 et 11 
		};
		Exo03_opl m = new Exo03_opl();
                
                

		System.out.println("Le flot max est "
						+ m.fordFulkerson(graph, 0,11 ));
	
        }
}
