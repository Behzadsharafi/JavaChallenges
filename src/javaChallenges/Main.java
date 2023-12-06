package javaChallenges;


import java.util.Arrays;

public class Main {

	

	  public static int greedy(int[] dice) {
		  int n[]= new int[7];
		  for (int d:dice) n[d]++;
		  return n[1]/3*1000 + n[6]/3*600+ n[5]/3*500+ n[4]/3*400+n[3]/3*300+ n[2]/3*200+ n[1]%3*100+n[5]%3*50;

		  }

	    public static void main(String[] args) {
//	        int n[] = new int[7];

	        // Assign values to individual elements
//	        n[0] = 10;
//	        n[1] = 20;
//	        n[2] = 30;
//	        n[3] = 40;
//	        n[4] = 50;
//	        n[5] = 60;
//	        n[6] = 70;

	        // Print the entire array using Arrays.toString()

	        int[] throw1 = {5, 1, 3, 4, 1};
	        int[] throw2 = {1, 1, 1, 3, 1};
	        int[] throw3 = {2, 4, 4, 5, 4};

	        System.out.println("Throw 1 Score: " + greedy(throw1)); // Should return 250
	        System.out.println("Throw 2 Score: " + greedy(throw2)); // Should return 1100
	        System.out.println("Throw 3 Score: " + greedy(throw3)); // Should return 450
	    	
	    }
}

//int number = 0;


 //i=0
//-----------------
//number=2

//i=1
//-----------------
//temp=2
//number=2-2=0
//number= 0 + 2*100= 200

//i=2
//-----------------
//nothing

//i=3
//-----------------
//number = 200+60 = 260

//i=4
//-----------------


