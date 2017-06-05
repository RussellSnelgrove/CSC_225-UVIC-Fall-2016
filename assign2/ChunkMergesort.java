/*
 * University of Victoria
 * CSC 225 - Fall 2016
 * Assignment 2 - Template for ChunkMergesort
 * 
 * This template includes some testing code to help verify the implementation.
 * To interactively provide test inputs, run the program with:
 * 
 *     java ChunkMergesort
 * 
 * To conveniently test the algorithm with a large input, create a text file
 * containing space-separated integer values and run the program with:
 * 
 *     java ChunkMergesort file.txt
 * 
 * where file.txt is replaced by the name of the text file.
 * 
 * Miguel Jimenez
 * 
 */
 
 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Do not change the name of the ChunkMergesort class
public final class ChunkMergesort {
	
	/**
	 * Use this class to return two lists.
	 * 
	 * Example of use:
	 * 
	 * Chunks p = new Chunks(S1, S2); // where S1 and S2 are lists of integers
	 */
	public static final class Chunks {
		
		private final List<Integer> left;
		private final List<Integer> right;
		
		public Chunks(List<Integer> left, List<Integer> right) {
			this.left = left;
			this.right = right;
		}
		
		public List<Integer> left() {
			return this.left;
		}
		
		public List<Integer> right() {
			return this.right;
		}
	}

	/**
	 * The list containing the integer comparisons in order of occurrence. Use
	 * this list to persist the comparisons; the method report will be invoked
	 * to write a text file containing this information.
	 * 
	 * Example of use (when comparing 1 and 9):
	 * 
	 * Integer[] d = new Integer[2];
	 * d[0] = 1;
	 * d[1] = 9;
	 * this.comparisons.add(d);
	 * 
	 * or just:
	 * 
	 * this.comparisons.add(new Integer[]{1, 9});
	 */
	private final List<Integer[]> comparisons;

	public ChunkMergesort() {
		this.comparisons = new ArrayList<Integer[]>();
	}

	
	public int chunkcounter(List<Integer> S){
		int num = 1;
		int a =1;
		while(a<S.size()){
			if(S.get(a-1)< S.get(a)){
			this.comparisons.add(new Integer[]{S.get(a-1),S.get(a)});
				a++;
			}else{
			this.comparisons.add(new Integer[]{S.get(a-1),S.get(a)});
				a++;
				num++;
			}
			
			
		}
		
		return num;
	}
	
	public List<Integer> chunkMergesort(List<Integer> S) {

			int b = chunkcounter(S);

			if(b <=1){
				return S;
			} else{
				
				Chunks chaka = chunkDivide(S,b);
				List <Integer> S1= chunkMergesort(chaka.left);
				List <Integer> S2= chunkMergesort(chaka.right);
				List <Integer> end = merge (S1,S2);
				return end;
				
			}

			

	
		
	}

	public Chunks chunkDivide(List<Integer> S, int c) {
		// TODO: remove this statement
	
		List<Integer> s2= new ArrayList<Integer>();
		List<Integer> s1= new ArrayList<Integer>();
		
		
		
		int n =((c/2)+(c%2));
		int j=0;
		int z=1;
		s1.add(S.get(z-1));
		
		while(j<n){
			if(S.get(z-1)<S.get(z)){
			this.comparisons.add(new Integer[]{S.get(z-1),S.get(z)});
				s1.add(S.get(z));
				z++;
			}else{
			this.comparisons.add(new Integer[]{S.get(z-1),S.get(z)});
				s1.add(S.get(z));
				j++;
				z++;
			}
			
			
		}
		s1.remove(z-1);
		s2 = S.subList(z-1,S.size());
		
			Chunks chak = new Chunks(s1,s2);
			
			return chak;

		
	}

	public List<Integer> merge(List<Integer> S1, List<Integer> S2) {
		List<Integer> end = new ArrayList<Integer>();

	int a=0;
	while(!S1.isEmpty() && !S2.isEmpty() ){
		if (S1.get(a) < S2.get(a)){
			this.comparisons.add(new Integer[]{S1.get(0),S2.get(0)});
			end.add(S1.get(a));
			S1.remove(a);
		}else{
			this.comparisons.add(new Integer[]{S1.get(0),S2.get(0)});
			end.add(S2.get(a));
			S2.remove(a);			
		}
	}
	
	while(!S1.isEmpty()){
		end.add(S1.get(a));
		S1.remove(a);
	}

	while(!S2.isEmpty()){
		end.add(S2.get(a));
		S2.remove(a);
	}
	
	return end;

		
	}

	/**
	 * Writes a text file containing all the integer comparisons in order of
	 * ocurrence.
	 * 
	 * @throws IOException
	 *             If an I/O error occurs
	 */
	public void report() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("comparisons.txt", false));
		for (Integer[] data : this.comparisons)
			writer.append(data[0] + " " + data[1] + "\n\n");
		writer.close();
	}

	/**
	 * Contains code to test the chunkMergesort method. Nothing in this method
	 * will be marked. You are free to change the provided code to test your
	 * implementation, but only the contents of the methods above will be
	 * considered during marking.
	 */
	public static void main(String[] args) {
		Scanner s;
		if (args.length > 0) {
			try {
				s = new Scanner(new File(args[0]));
			} catch (java.io.FileNotFoundException e) {
				System.out.printf("Unable to open %s\n", args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n", args[0]);
		} else {
			s = new Scanner(System.in);
			System.out.printf("Enter a list of integers:\n");
		}
		List<Integer> inputList = new ArrayList<Integer>();

		int v;
		while (s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputList.add(v);

		s.close();
		System.out.printf("Read %d values.\n", inputList.size());

		long startTime = System.currentTimeMillis();

		ChunkMergesort mergesort = new ChunkMergesort();
		/* List<Integer> sorted = */ mergesort.chunkMergesort(inputList);

		long endTime = System.currentTimeMillis();
		double totalTimeSeconds = (endTime - startTime) / 1000.0;

		System.out.printf("Total Time (seconds): %.2f\n", totalTimeSeconds);
		
		try {
			mergesort.report();
			System.out.println("Report written to comparisons.txt");
		} catch (IOException e) {
			System.out.println("Unable to write file comparisons.txt");
			return;
		}
	}


}