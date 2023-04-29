import java.util.*;

public class quickSort {
	public static void main(String[] args) {
		Random rand = new Random();
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10);
		}
		for(int i = 0; i < 4; i++) {
			int[] tests = {10, 100, 1000, 10000};
			int[] arr2 = generateAlmostSortedArray(tests[i]);
			long elapsedTime = 0;
			//this for loop is just to get the average time of 10 runs commented out prints, but you can add them back if you want
			for (int j = 0; j < 10; j++) {
				//System.out.println("Before: ");
				//printArray(arr);
				long startTime = System.nanoTime();
				quicksort(arr2, 0, arr2.length - 1); //change this to arr if you want to test random arrays, make sure you change arr2.length to arr.length as well
				long endTime = System.nanoTime();
				elapsedTime += (endTime - startTime);
				//System.out.println("\nAfter (" + elapsedTime + " ns):");
				//printArray(arr);
			}
			System.out.println("\nAverage time of array size " + arr2.length + ": " + elapsedTime / 10 + " ns");
		}
	}

	public static int[] generateAlmostSortedArray(int n) {
		int[] arr = new int[n];
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = i + random.nextInt(4); // Add a small random number to the value of i.
		}
		return arr;
	}

	public static void quicksort(int[] arr, int low, int high){
		int size = high - low + 1;
		//pivot
		if(size <= 15){
			insertionSort(arr, low, high);
		}
		else{ //partition the array
			int median = medianOf3(arr, low, high);
			int partition = partitionIt(arr, low, high, median);
			quicksort(arr, low, partition-1);
			quicksort(arr, partition+1, high);
		}
	}

	//find median of three elements
	public static int medianOf3(int[] arr, int low, int high){
		int mid = (low+high)/2; //middle index
		if(arr[low] > arr[mid]){ //swap low and mid
			swap(arr, low, mid);
		}
		if(arr[low] > arr[high]){ //swap low and high
			swap(arr, low, high);
		}
		if(arr[mid] > arr[high]){ //swap mid and high
			swap(arr, mid, high);
		}
		swap(arr, mid, high - 1); //put median in high - 1
		return arr[high - 1];
	}

	//swap two elements in an array for ease of use
	public static void swap(int[] arr, int ind1, int ind2){
		int temp = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = temp;
	}

	//partition the array
	public static int partitionIt(int[] arr, int low, int high, int pivot){
		int left = low;
		int right = high - 1;
		while(true){
			while(arr[++left] < pivot) //find bigger element
				;
			while(arr[--right] > pivot) //find smaller element
				;
			if(left >= right) //if pointers cross, partition done
				break;
			else
				swap(arr, left, right); //swap elements
		}
		swap(arr, left, high - 1); //restore pivot
		return left;
	}

	//simple insertion sort
	public static void insertionSort(int[] arr, int low, int high){
		int size = high - low + 1;
		if(size <= 1){
			return;
		}
		if(size == 2){
			if(arr[low] > arr[high]){
				swap(arr, low, high);
			}
			return;
		}
		else{
			for(int i = low + 1; i <= high; i++){
				int temp = arr[i];
				int j = i;
				while(j > low && arr[j-1] > temp){
					arr[j] = arr[j-1];
					j--;
				}
				arr[j] = temp;
			}
		}
	}

	private static void printArray(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}
}
