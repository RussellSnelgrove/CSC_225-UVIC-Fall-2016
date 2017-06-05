
/*
 * University of Victoria
 * CSC 225 - Fall 2016
 * Code template for assignment 4
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.Arrays;
import java.util.Stack;
// DO NOT CHANGE THE CLASS NAME OR PACKAGE
public class Graph225 {

	public static int row_size=0;
	public static int col_size=0;
	public static int g_row=0;
	public static int g_col=0;
	public static int g_size=0;
	public static int size=0;
	public static int g_edges=0;
	public static String file;
	public static int components=0;
	public static boolean cycler =false;
	public static int[][] russell;

	/**
	 * Simple representation of an undirected graph, using a square, symmetric
	 * adjacency matrix.
	 * <p>
	 * An adjacency matrix M represents a graph G=(V,E) where V is a set of n
	 * vertices and E is a set of m edges. The size of the matrix is {@code n},
	 * where {@code n} is in the range {@code [4, 15]} only. Thus, the rows and
	 * columns of the matrix are in the range {@code [0, n-1]} representing
	 * vertices. The elements of the matrix are 1 if the edge exists in the
	 * graph and 0 otherwise. Since the graph is undirected, the matrix is
	 * symmetric and contains 2m 1’s.
	 */
	public static class Graph {

		/**
		 * An adjacency matrix representation of this graph
		 */
		private int[][] adjacencyMatrix;
		private int[][] adjacencyMatrix_1;
		private int[][] adjacencyMatrix_2;
		private int[][] adjacencyMatrix_3;

		private	int t[];


		/*
		 * You are free to add constructors, but the empty constructor is the
		 * only one invoked during marking.
		 */
		public Graph() {
			// YOUR CODE HERE (if needed)
		}

		/**
		 * Generate a random graph as specified in the assignment statement.
		 * 
		 * @param n
		 *            The size of the graph
		 * @param density
		 *            The density of the graph
		 */
		public void generate(int n, int density) {
			if (density==1){
				g_edges = (7*n)/5;
			}else if(density==2){
				g_edges = n*n/4;
			}else if(density==3){
				g_edges = 2*n*n/5;
			}
		int m =g_edges;
		g_col = n;//(int) Math.sqrt(n);
		g_row = g_col;
		g_size = g_col*g_col;
		adjacencyMatrix_1 = new int[g_row][g_col];
		Random r = new Random(System.currentTimeMillis());
		int k = 0;
		while(k<m){
			int rn = Math.abs(r.nextInt()%n);
			int ln = Math.abs(r.nextInt()%n);
			if(adjacencyMatrix_1[ln][rn]==1){
				
			}else if(ln==rn){
				
			}else{
				adjacencyMatrix_1[ln][rn] = 1;
				adjacencyMatrix_1[rn][ln]=1;
				k++;
			}
		}
		
		
		
		}
		public void generate_1(int n, int density) {
			if (density==1){
				g_edges = (7*n)/5;
			}else if(density==2){
				g_edges = n*n/4;
			}else if(density==3){
				g_edges = 2*n*n/5;
			}
		int m =g_edges;
		g_col = n;//(int) Math.sqrt(n);
		g_row = g_col;
		g_size = g_col*g_col;
		adjacencyMatrix_2 = new int[g_row][g_col];
		Random r = new Random(System.currentTimeMillis());
		int k = 0;
		while(k<m){
			int rn = Math.abs(r.nextInt()%n);
			int ln = Math.abs(r.nextInt()%n);
			if(adjacencyMatrix_2[ln][rn]==1){
				
			}else if(ln==rn){
				
			}else{
				adjacencyMatrix_2[ln][rn] = 1;
				adjacencyMatrix_2[rn][ln]=1;
				k++;
			}
		}
		
		
		
		}

		public void generate_2(int n, int density) {
			if (density==1){
				g_edges = (7*n)/5;
			}else if(density==2){
				g_edges = n*n/4;
			}else if(density==3){
				g_edges = 2*n*n/5;
			}
		int m =g_edges;
		g_col = n;//(int) Math.sqrt(n);
		g_row = g_col;
		g_size = g_col*g_col;
		adjacencyMatrix_3 = new int[g_row][g_col];
		Random r = new Random(System.currentTimeMillis());
		int k = 0;
		while(k<m){
			int rn = Math.abs(r.nextInt()%n);
			int ln = Math.abs(r.nextInt()%n);
			if(adjacencyMatrix_3[ln][rn]==1){
				
			}else if(ln==rn){
				
			}else{
				adjacencyMatrix_3[ln][rn] = 1;
				adjacencyMatrix_3[rn][ln]=1;
				k++;
			}
		}
		
		
		
		}
		/**
		 * Reads an adjacency matrix from the specified file, and updates this
		 * graph's data. For the file structure please refer to the sample input
		 * file {@code testadjmat.txt}).
		 * 
		 * @param file
		 *            The input file
		 * @throws IOException
		 *             If something bad happens while reading the input file.
		 */
		public void read(String file)throws IOException {
			try{			
			//	System.out.println("The file in read is "+file + " which consitist of:");
				Scanner input = new Scanner (new File(file));
				// pre-read in the number of rows/columns
					while(input.hasNextLine()){
						++row_size;
						input.nextLine();
					}			
					col_size=row_size;
					adjacencyMatrix = new int[row_size][col_size];
					input.close();
					// read in the data
					input = new Scanner(new File(file));
					for(int i = 0; i < row_size; ++i){
						for(int j = 0; j < col_size; ++j){
							if(input.hasNextInt()){
								adjacencyMatrix[i][j] = input.nextInt();
							}
						}
					}
					input.close();
					size = row_size*row_size;
				
			}catch(IOException e){
				throw new IOException("not working in read");
			}	
		}

		/**
		 * Writes the adjacency matrix representing this graph in the specified
		 * file.
		 * 
		 * @param file
		 *            The path of the output file
		 * @throws IOException
		 *             If something bad happens while writing the file.
		 */
		public void write(String file) throws IOException {
			try{
				PrintWriter writer = new PrintWriter("fn.txt", "UTF-8");
				writer.println("The file in read is "+file + " which consitist of:");
				writer.println();

				for(int i = 0; i < row_size; ++i){
					for(int j = 0; j < col_size; ++j){
						writer.print(adjacencyMatrix[i][j]+ " ");
					}
					writer.println();
				}
				writer.println();
				writer.println("We generated the matrix:");
				writer.println();
				for(int i = 0; i < g_row; ++i){
					for(int j = 0; j < g_col; ++j){
						writer.print(adjacencyMatrix_1[i][j]+ " ");
					}
					writer.println();
				}
				writer.println();
				writer.println("This is the vector representing which nodes the fifth node can touch:");
				writer.println();
				for(int i=0;i<g_col;i++){
					writer.print(t[i]+ " ");
				}
				writer.println();
				writer.println();
				writer.println("The number of connected Components are: " + components);

				writer.println();
				writer.println("Is there a cycle: " + cycler);

				writer.println();
				for(int i = 0; i < g_row; ++i){
					for(int j = 0; j < g_col; ++j){
						writer.print(russell[i][j]+ " ");
					}
					writer.println();
				}
				
				
				
				writer.println("We generated the matrix:");
				writer.println();
				for(int i = 0; i < g_row; ++i){
					for(int j = 0; j < g_col; ++j){
						writer.print(adjacencyMatrix_2[i][j]+ " ");
					}
					writer.println();
				}
								writer.println("We generated the matrix:");
				writer.println();
				for(int i = 0; i < g_row; ++i){
					for(int j = 0; j < g_col; ++j){
						writer.print(adjacencyMatrix_3[i][j]+ " ");
					}
					writer.println();
				}

				writer.close();
				
				
				
				
				
			}catch(IOException e){
				throw new IOException("not working in Write");
			}
			
			
		}

		/**
		 * @return an adjacency matrix representation of this graph
		 */
		public int[][] getAdjacencyMatrix() {
			return this.adjacencyMatrix;
		}
		public int[][] getAdjacencyMatrix_1() {
			return this.adjacencyMatrix_1;
		}
		/**
		 * Updates this graph's adjacency matrix
		 * 
		 * @param m
		 *            The adjacency matrix representing the new graph
		 */
		public void setAdjacencyMatrix(int[][] m) {
			this.adjacencyMatrix = m;
		}

	}

	/**
	 * Traverses the given graph starting at the specified vertex, using the
	 * depth first search graph traversal algorithm.
	 * <p>
	 * <b>NOTICE</b>: adjacent vertices must be visited in strictly increasing
	 * order (for automated marking)
	 * 
	 * @param graph
	 *            The graph to traverse
	 * @param vertex
	 *            The starting vertex (as per its position in the adjacency
	 *            matrix)
	 * @return a vector R of n elements where R[j] is 1 if vertex j can be
	 *         reached from {@code vertex} and 0 otherwise
	 */
	public int[] reach(Graph graph, int vertex) {
	int R[] = new int[g_col];
	int a= vertex;
			for(int j = 0; j < g_row; ++j){
				if(graph.adjacencyMatrix_1[j][a]==1){
					R[j]=1;
				}else{
					R[j]=0;
				}
			}
	return R;
	//	throw new UnsupportedOperationException("This method has not been implemented yet.");
	}

	/**
	 * Computes the number connected components of a given graph.
	 * <p>
	 * <b>NOTICE</b>: adjacent vertices must be visited in strictly increasing
	 * order (for automated marking)
	 * 
	 * @param graph
	 *            The graph
	 * @return The number of connected component in {@code graph}
	 */
	public int connectedComponents(Graph graph) {
		int counter=0;
		int R[] = new int[g_col];
		int Y[] = new int[g_col];
		
		R= reach(graph,0);
		Y =R;
		for(int i=1;i<g_col;i++){
			if(Y[i]==1){
				R=reach(graph,i);
				for(int j=0;j<g_row;j++){
					if(R[j]==1){
						Y[j]=1;
					}
				}
			}
		}		
		
		
		for(int e=0; e < g_col; ++e){
				//	System.out.println("This is start of first for");

					if(Y[e]==1){
						counter++;
					}else{
				
					}
			
			}		
		return counter;

	//	throw new UnsupportedOperationException("This method has not been implemented yet.");
	}

	/**
	 * Determines whether a given graph contains at least one cycle.
	 * <p>
	 * <b>NOTICE</b>: adjacent vertices must be visited in strictly increasing
	 * order (for automated marking)
	 * 
	 * @param graph
	 *            The graph
	 * @return whether or not {@code graph} contains at least one cycle
	 */
	public boolean hasCycle(Graph graph) {
		int R[] = new int[g_col];
		int Y[] = new int[g_col];
		
		R= reach(graph,0);
		Y =R;
		for(int i=1;i<g_col;i++){
			if(Y[i]==1){
				R=reach(graph,i);
				for(int j=0;j<g_row;j++){
					if(R[j]==1){
						if(R[j]==Y[j]){
							return true;
						}else{
						Y[j]=1;
						}
					}
				}
			}
		}
		return false;
		
		
		
//		throw new UnsupportedOperationException("This method has not been implemented yet.");
	}

	
	
	
	public Vector<Integer> preOrder_1(Graph graph, int h, int[] isVisited, Vector<Integer> lister){
		int[] ver = reach(graph,h);
		isVisited[h]=1;
		lister.add(h);
		for(int m=0;m<ver.length;m++){
			if(isVisited[ver[m]]==0){
				preOrder_1(graph,ver[m],isVisited,lister);
			}
		}
		return lister;
	}
	
	
	/**
	 * Computes the pre-order for each vertex in the given graph.
	 * Empty spots in the array, if any, are to be filled with -1.
	 * <p>
	 * <b>NOTICE</b>: adjacent vertices must be visited in strictly increasing
	 * order (for automated marking)
	 * 
	 * @param graph
	 *            The graph
	 * @return a vector R of n elements of each vertex, representing the pre-order of
	 *         {@code graph}
	 */
	public int[][] preOrder(Graph graph) {
		
		int len = graph.adjacencyMatrix_1.length;
		int[][]snel = new int [g_col][g_row];
		int[]isVisited = new int[g_col];
		Vector<Integer> lister = new Vector<Integer>();
		Vector<Integer> order = new Vector<Integer>();
		
		
		
		
		for(int i=0;i<g_col;i++){
			for(int j=0;j<g_row;j++){
				snel[i][j]=-1;
			}
		}
		
		
		
		
		for(int f=0;f<g_col;f++){
			isVisited = new int[g_col];
			lister = new Vector<Integer>();
			order = new Vector<Integer>();
			order = preOrder_1(graph,f,isVisited,lister);
			for(int g=0;g<order.size();g++){
				snel[f][g]=order.get(g).intValue();
			}
		}
		return snel;
	}
		

	/**
	 * Computes the post-order for each vertex in the given graph.
	 * Empty spots in the array, if any, are to be filled with -1.
	 * <p>
	 * <b>NOTICE</b>: adjacent vertices must be visited in strictly increasing
	 * order (for automated marking)
	 * 
	 * @param graph
	 *            The graph
	 * @return a vector R of n elements of each vertex, representing the post-order of
	 *         {@code graph}
	 */
	public int[][] postOrder(Graph graph) {
		throw new UnsupportedOperationException("This method has not been implemented yet.");
	}

	/**
	 * test and exercise the algorithms and data structures developed for the
	 * first five parts of this assignment extensively. The output generated by
	 * this method must convince the marker that the algorithms and data
	 * structures are implemented as specified. For example:
	 * <ul>
	 * <li>Generate graphs of different sizes and densities
	 * <li>Test the algorithms for different graphs
	 * <li>Test your algorithms using the sample input file testadjmat.txt
	 * 
	 * @throws Exception
	 *             if something bad happens!
	 */
	public void test() throws Exception {
		try{
		Graph graph = new Graph();
		graph.read(file);

		graph.generate(10,3);	
		graph.t = reach( graph,5);
		components = connectedComponents(graph);

		cycler = hasCycle(graph);
		russell = preOrder(graph);
		
		
		
		Graph graph_1 = new Graph();
		graph_1.generate_1(10,2);	
		graph_1.t = reach( graph_1,5);
		components = connectedComponents(graph_1);
		cycler = hasCycle(graph);
		russell = preOrder(graph);
		
		Graph graph_2 = new Graph();
		graph_2.generate_2(10,1);	
		graph_2.t = reach( graph,5);
		components = connectedComponents(graph_2);
		cycler = hasCycle(graph_2);
		russell = preOrder(graph_2);
		
		graph.write("fn");	
		} catch(Exception e){
		throw new Exception("not working");
		}
		}

	
	public static void main(String[] args) {
		Graph225 trial = new Graph225();
		trial.file = args[0];
		try{
		trial.test();
	    } catch (Exception exc) {
        // TODO: handle exception
		}
		
		/*
		
		BufferedReader br = new BufferedReader(new FileReader("file.txt"));
try {
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();

    while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
    }
    String everything = sb.toString();
} finally {
    br.close();
}
		
		*/
	//	File filer = new File(args[0]);
	//		trial.read(filer);
	//		trial.test();
	
	}
}