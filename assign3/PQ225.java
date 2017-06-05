



import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.NoSuchElementException;



//graph stuff if i can figure it out
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public final class PQ225 extends Application{
	// global varibles
	private final List<Integer> heapArray;
	public static int value=0;
	public static int diff=0;
	public static int display =0;
	public static int numnum=0;
	public static int trial_1=0;
	public static int trial_2=0;
	public static int trial_3=0;
	public static int trial_4=0;
	public static int trial_5=0;
	public static int trial_6=0;
	public static int trial_7=0;
	public static int trial_8=0;
	public static int trial_9=0;	
	
	final static String heaper = "makeHeap()";
    final static String deleter = "deleteMin()";
    final static String sorter = "heapSort()";
	
	// constructor
	public PQ225(){
		this.heapArray = new ArrayList<Integer>();
	}
	//find the parent
	public int parent(int k)	{
		return (k-1)/2;
	}
	//find the left
    public int left(int k){
		return (k*2)+1;
	}
	//find the right
    public int right(int k){
		return (k*2)+2;
	}
	//determine which child is smaller and check to see if there is only one child
	public int smaller_child(int k) {
		if (left(k) > heapArray.size() - 1) {
			value = value +1 ;
			return -1;
		}
		if (right(k) > heapArray.size() - 1){
			value = value +1 ;
			return left(k);
		}
		if (heapArray.get(left(k))<= heapArray.get(right(k))){
			value = value +1 ;
			return left(k);
		}else{
			value = value +1 ;
			return right(k);
		}
	}
	//swaps the two indexs if required
	public void replace(int a, int b) {
        int temp = heapArray.get(a);
        heapArray.set(a, heapArray.get(b));
        heapArray.set(b, temp);
    }
	// trikles the value from the current index down to where it belongs
	public void trikle(int k) {
        int smaller = smaller_child(k);
        while (smaller != -1 && heapArray.get(smaller) < heapArray.get(k)) {
			value = value +2 ;
            replace(smaller, k);
            k = smaller;
            smaller = smaller_child(k);
        }
    }
	//bubbles up the value in the index it is currently at to wherever it belongs
	public void bubbler(int k) {
        int parent_position = parent(k);
        while (k > 0 && heapArray.get(parent_position) > heapArray.get(k)) {
			value = value +2;
            replace(parent_position, k);
            k = parent_position;
            parent_position = parent(k);
        }
    }

	//randomly generates the numbers and puts them into a array list
	public void ranGen(int N, int LOW, int HIGH){
		int k = 0;
		Random r = new Random(System.currentTimeMillis());
		int rn;
		while (k<N){
			do{
				rn = r.nextInt();
				numnum = numnum +1;
				}while (rn< LOW || rn > HIGH );
					insert(rn);
					k++;
				
		}
	}
	//finds the size of my array list
	public int size(){
		int size_of_thing = heapArray.size();
		
		return size_of_thing;
	}
	// inserts my new interger at the end of the arraylist
	public void insert(int i){
		heapArray.add(i);
	}
	// deletes the root (which is the smallest element) and replaces it with the second smallest element
	public int deleteMin(){
		int temp = heapArray.get(0);
        heapArray.set(0, heapArray.get(heapArray.size() - 1));
        heapArray.remove(heapArray.size() - 1);
		trikle(0);

		
		return temp;
	}
	
	//trikles all the values down required to make a heap out of the set of numbers given to us
	public void makeHeap(){		
		int k;
		for(k=1;k<(size()-1)/2;k=k*2){}
			while(k>=0){
				trikle(k);
				k--;
			}
			
	}
	// sorts the array list into a proper min heap
	public int heapsort(){
		for(int i =0;i<heapArray.size();i++){
			value = value +1;
			trikle(i);
			bubbler(i);
		}
		return 0;
	}

	//tests and writes what my program does
	public void test(){
			try{
	PrintWriter writer = new PrintWriter("pq_test.txt", "UTF-8");

	if(heapArray.isEmpty()){
			writer.println("This is our set of numbers");
		}else{
			writer.println("The Heap is not empty");
		}
		
		ranGen(63,0,1000);
		writer.println("Number of numbers cycled through to get this set of numbers: "+numnum);
		writer.println("Testing to see if this set of numbers is a heap: "+ isHeap());
		for(int i=0; i<size();i++){
			writer.println("Index: "+i +", Contains: "+ heapArray.get(i));
		}
		writer.println();
		
	//	heapsort();
		makeHeap();
		writer.println("After calling makeHeap()");
		display = value - diff;
		diff=display+diff;
		trial_3=display;
		writer.println("Number of comparisons: "+display);
		writer.println("Testing to see if this set of numbers is a heap: "+ isHeap());
		for(int i=0; i<size();i++){
			writer.println("Index: "+i +", Contains: "+ heapArray.get(i));
		}
		writer.println();	
		
		int size = heapArray.size();
		writer.println("The size of our heap is: "+size);
		writer.println("Testing to see if this set of numbers is a heap: "+ isHeap());
		writer.println();	
		
		
		int deleted = deleteMin();
		writer.println("After deleting the smallest element, which is: "+ deleted);
		display = value -diff;
		diff = diff+display;
		trial_2=display;
		writer.println("Number of comparisons: "+display);
		writer.println("Testing to see if this set of numbers is a heap: "+ isHeap());
		for(int i=0; i<size();i++){
			writer.println("Index: "+i +", Contains: "+ heapArray.get(i));
		}
		writer.println();	

		
		
		heapsort();
		writer.println("After calling heapsort()");
		display = value - diff;
		diff = diff+display;
		trial_1=display;
		writer.println("Number of comparisons: "+display);
		writer.println("Testing to see if this set of numbers is a heap: "+ isHeap());
		for(int i=0; i<size();i++){
			writer.println("Index: "+i +", Contains: "+ heapArray.get(i));
		}
		writer.println();

		
	    writer.close();
		} catch (Exception e) {}
		
	}
	
		public void test_extra(){		
		
		ranGen(27,0,1000);

	//	heapsort();
		makeHeap();
		display = value - diff;
		diff=display+diff;
		trial_6=display;
		int deleted = deleteMin();
		display = value -diff;
		diff = diff+display;
		trial_5=display;
		heapsort();
		display = value - diff;
		diff = diff+display;
		trial_4=display;


		
/*		
		ranGen(12,0,1000);
		for(int i=0; i<size();i++){
		makeHeap();
		display = value - diff;
		diff=display+diff;
		trial_6=display;
		int deleted = deleteMin();
		display = value -diff;
		diff = diff+display;
		trial_5=display;
		heapsort();
		display = value - diff;
		diff = diff+display;
		trial_4=display;
		}*/
		}

	
		public void test_extra_1(){		
		ranGen(32,0,1000);

	//	heapsort();
		makeHeap();
		display = value - diff;
		diff=display+diff;
		trial_9=display;
		int deleted = deleteMin();
		display = value -diff;
		diff = diff+display;
		trial_8=display;
		heapsort();
		display = value - diff;
		diff = diff+display;
		trial_7=display;
		}
	
	
	
	//tests to see if the set of numbers is a heap
    public boolean isHeap() {
        for (int i = 1; i < heapArray.size(); ++i) {
            if (parent(i) >= 0) {
                if (heapArray.get(parent(i)) > heapArray.get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
	
	
	//trying the graph thing
	    @Override public void start(Stage stage) {
        stage.setTitle("comparisons");
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number,String> bc = new BarChart<Number,String>(xAxis,yAxis);
		bc.setBarGap(3);
		bc.setCategoryGap(15);
        bc.setTitle("Comparison Summary");
        xAxis.setLabel("number of comparisons");  
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Operation");        
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Test 3");       
        series1.getData().add(new XYChart.Data(trial_7, heaper));
        series1.getData().add(new XYChart.Data(trial_8, deleter));
        series1.getData().add(new XYChart.Data(trial_9, sorter));       
       
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Test 2");
        series2.getData().add(new XYChart.Data(trial_4, heaper));
        series2.getData().add(new XYChart.Data(trial_5, deleter));
        series2.getData().add(new XYChart.Data(trial_6, sorter));  
     	 

		XYChart.Series series3 = new XYChart.Series();
        series3.setName("Test 1");
        series3.getData().add(new XYChart.Data(trial_1, heaper));
        series3.getData().add(new XYChart.Data(trial_2, deleter));
        series3.getData().add(new XYChart.Data(trial_3, sorter));
 
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1,series2,series3);
        stage.setScene(scene);
        stage.show();
    }

	//main
	public static void main(String[] args) {
				PQ225 tester = new PQ225();
				tester.test();
				PQ225 tester_1 = new PQ225();
				tester_1.test_extra();
				PQ225 tester_2 = new PQ225();
				tester_2.test_extra_1();		
				System.out.println("The pop up is a comparison of the number of comparisons of 3 tests.");	
				System.out.println();	
				System.out.println("The file pq_test.txt contains the results of Test 1.");	

				launch(args);

	}
}