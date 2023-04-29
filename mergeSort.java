import java.util.*;

public class mergeSort {
	public static void main(String[] args) {
		//whenever you want to test either randomly ordered or almost sorted arrays, just change arr to arr2 and vice versa, also would have to remove the for loop, or you could use it to test both.
		//it is very hard coded to be honest, but it works for the purposes of just testing.
		//this main function just ended up this way because i had an original testing setup that i just added on to and was too lazy to change it for ease of use. not that it really matters

		//random array generation, just manually change size and range of the array for testing
		Random rand = new Random();
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(10);
		}

		//almost sorted array testing
		for(int i = 0; i < 4; i++) {
			int[] tests = {10, 100, 1000, 10000};
			int[] arr2 = generateAlmostSortedArray(tests[i]);
			System.out.print("Array size: " + arr2.length);
			long elapsedTime = 0;

			//this for loop is just to get the average time of 10 runs commented out prints, but you can add them back if you want
			for (int j = 0; j < 10; j++) {
				//System.out.println("Before: ");
				//printArray(arr);
				long startTime = System.nanoTime();
				mergesort(arr2); //change this to arr if you want to test random arrays
				long endTime = System.nanoTime();
				elapsedTime += (endTime - startTime);
				//System.out.println("\nAfter (" + elapsedTime + " ns):");
				//printArray(arr);
			}
			System.out.println("\nAverage time: " + elapsedTime / 10 + " ns");
		}
	}

	public static int[] generateAlmostSortedArray(int n) { // Generate an almost sorted array.
		int[] arr = new int[n];
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = i + random.nextInt(3); // Add a small random number to the value of i.
		}
		return arr;
	}

	public static void mergesort(int[] inpArr){ //mergesort function
		int length = inpArr.length;
		if(length < 2){
			return;
		}
		int mid = length/2;
		int[] left = new int[mid];
		int[] right = new int[length-mid];
		for(int i = 0; i < mid; i++){ //splitting the array into two halves
			left[i] = inpArr[i];
		}
		for(int i = mid; i < length; i++){ //splitting the array into two halves
			right[i-mid] = inpArr[i];
		}
		mergesort(left); //recursively calling mergesort on the two halves
		mergesort(right);

		merge(inpArr, left, right);

	}

	private static void merge(int[] inpArr, int[] left, int[] right){
		int lSize = left.length;
		int rSize = right.length;
		int i = 0, j = 0, k = 0;
		while(i < lSize && j < rSize){ //merging the two halves back together
			if(left[i] < right[j]){
				inpArr[k] = left[i];
				i++;
			}
			else{
				inpArr[k] = right[j];
				j++;
			}
			k++;
		}
		while(i < lSize){ //if one of the halves is longer than the other, just add the rest of the elements to the end of the array
			inpArr[k++] = left[i++];
		}
		while(j < rSize){ //if one of the halves is longer than the other, just add the rest of the elements to the end of the array
			inpArr[k++] = right[j++];
		}
	}

	private static void printArray(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}
}
