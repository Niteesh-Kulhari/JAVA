
public class Main {

	public static void main(String[] args) {
		
		// Creating a new binary number with all digits equal to 0
		BinaryNumber num1 = new BinaryNumber(5);
		BinaryNumber num2 = new BinaryNumber("0001");
		
		System.out.print("num1 :- ");
		System.out.print(num1.toString()+"\n");
		
		System.out.print("num2 :- ");
		System.out.print(num2.toString()+"\n\n");

		
		System.out.println("Length of binary number num1 is " + num1.getLength());
		System.out.println("Length of binary number num2 is " + num2.getLength()+"\n\n");
		
		
		System.out.println("First digit of binary number num1 is " + num1.getDigit(0));
		System.out.println("Second digit of binary number num2 is " + num2.getDigit(1)+"\n\n");
		
		System.out.println("num2 in decimal format " + num2.toDecimal()+"\n\n");
		
//		//num2.shiftR(3);
//		
//		System.out.print("After Right Shift: ");
//		System.out.println(num2.toString()+"\n");
		
		
		BinaryNumber num3 = new BinaryNumber("0100");
		
		System.out.println("Adding num2 and num3");
		num2.add(num3);
		System.out.print("Output : ");
		System.out.println(num2.toString());
	}

}
