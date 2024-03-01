
// Niteesh Kulhari
// CS - 501 WS1
// The project is about calculating a set of number by using a different class and using that class in the main method.


import java.util.Scanner;
public class Operations {

	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in); // Scanner class to get input
		
		System.out.printf("The Calculator Program\n\n");
		
		// Input for Number 1
		System.out.printf("Enter Number 1: ");
		double num1 = scan.nextDouble();
		
		// Input for Number 2
		System.out.printf("Enter Number 2: ");
		double num2 = scan.nextDouble();
		
		scan.close(); // Closing the scanner object
		
		Calculator calc = new Calculator(); // instantiating object of Calculator class
		
		
		//Operation to add, divide, subtract, multiply, clear and display the output
		System.out.printf("\n\nOutput : \n\n");
		System.out.println(String.format("%,.1f", calc.getValue()));
		calc.add(num1);
		System.out.println(String.format("%,.1f", calc.getValue()));
		calc.multiply(3);
		System.out.println(String.format("%,.1f", calc.getValue()));
		calc.subtract(num2);
		System.out.println(String.format("%,.1f", calc.getValue()));
		calc.divide(2);
		System.out.println(String.format("%,.1f", calc.getValue()));
		calc.clear();
		System.out.println(String.format("%,.1f", calc.getValue()));
		
		System.out.println("\n\nEnd of results.");

	}

}
