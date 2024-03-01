
public class Complexity {
	//Global variable for method 6
	private static int counter1 = 1;

	//Implementation for Time complexity O(n)
	public static void method0(int n) {
		int counter = 0;
		for(int i=0; i<n; i++) {
			System.out.println("Operation O(n):- " + counter);
			counter ++;
		}
	}
	
	//Implementation for Time complexity O(n^2)
	public static void method1(int n) {
		int counter = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.println("Operation O(n^2):- " + counter);
				counter ++;
			}
		}
	}
	
	//Implementation for Time complexity O(n^3)
	public static void method2(int n) {
		int counter = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					System.out.println("Operation O(n^3) :- " + counter);
					counter ++;
				}
			}
		}
	}
	
	//Implementation for Time complexity O(logn)
	public static void method3(int n) {
		int counter = 0;
		
		for(int i=1; i<=n; i*=2) {
			System.out.println("Operation O(logn):- " + counter);
			counter ++;
		}
	}
	
	//Implementation for Time complexity O(nlogn)
	public static void method4(int n) {
		int counter = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=1; j<=n; j*=2) {
				System.out.println("Operation O(nlogn):- " + counter);
				counter ++;
			}
		}
	}
	
	//Implementation for Time complexity O(loglogn)
	public static void method5(int n) {
		int counter = 0;
		
		for(int i=2; i<=n; i*=i) {
			System.out.println("Operation O(loglogn):- " + counter);
			counter ++;
		}
	}
	
	//Implementation for Time complexity O(2^n)
	public static int method6(int n) {
		
		System.out.println("Operation O(2^n):- " + counter1);
//		System.out.println("n):- " + n);
		counter1 ++;
		
		
		if(n<=1) {
			return n;
		}
		else {
			return method6(n-1) + method6(n-1);
		}
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Complexity.method0(5);
		Complexity.method1(5);
		Complexity.method2(5);
		Complexity.method3(16);
		Complexity.method4(8);
		Complexity.method5(16);
		Complexity.method6(4);
	}

}
