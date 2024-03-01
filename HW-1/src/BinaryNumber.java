import java.util.Arrays;

public class BinaryNumber {
	
	private int data[];
	private boolean overflow;

	
	// Constructor to make store a binary number which at this instant filled with 0's in the array.
	public BinaryNumber(int length) {
		data = new int[length];
		for(int i=0; i<length; i++) {
			data[i] = 0;
		}
	}
	
	
	// Constructor to store a given binary number in the form of a string input into the data array. 
	public BinaryNumber(String str) {
		
		//Initializing the size of array at the run time in heap.
		int size = str.length();
		data = new int[size];
		
		for (int i = 0; i < size; i++) {
			// Check whether any improper input is provided
			if (str.charAt(i) == '1' || str.charAt(i) == '0') {
				data[i] = Character.getNumericValue(str.charAt(i));
			} else {
				// If improper input is provided, then notify user
				System.out.println("Error! Entered value is not binary. Please input proper binary number");
				break;
			}
		}
	}
	
	// Method to return the length of binary number stored in the array
	public int getLength(){
		
		return data.length;
	}
	
	// Method to get the digit at the specified index of the array
	public int getDigit(int index) {
		
		if(index < 0 || index >= data.length) {
			System.out.println("Please enter a valid index");
			return index;
		}
		
		else {
			int num = data[index];
			return num;
		}
		
	}
	
	// Method to shift the given binary number right by the given number and insert 0 in it's place.
	public void shiftR(int amount) {
		data = Arrays.copyOf(data,(data.length + amount));
		int t = data.length;
		for(int a=0; a<amount; a++) {
			int temp = data[t-1];
			for(int i=t-1; i>0; i--) {
				data[i] = data[i-1];
			}
			data[0] = temp;}
		
//		for(int i=0; i < reference.length; i++) {
//			System.out.println(reference[i]);
//		}
		
	}
	
	// Method to add two binary numbers
	public void add(BinaryNumber aBinaryNumber) {
		int data1[] = new int[aBinaryNumber.getLength()];
		data1 = aBinaryNumber.getBinary();
		int carry = 0;
		if(data.length == data1.length) {
			for(int i=0; i<data.length; i++) {
				int t = (carry + data[i] + data1[i]);
				if(t == 0) {
					data[i] = 0;
					carry = 0;
				} else if(t == 1) {
					data[i] = 1;
					carry = 0;
				} else if(t == 2) {
					data[i] = 0;
					carry = 1;
				} else if(t == 3) {
					data[i] = 1;
					carry = 1;
				}
			}
			if(carry == 1) {
				overflow = true;				
			}
		}
		else {
			System.out.println("The lengths of the binary numbers do not coincide.");
		}
	}
	
	// Method to get the binary number stored in the array
	private int[] getBinary() {
		return data;
	}
	
	// Converting binary number to string and printing the output in the case overflow is false.
	public String toString(){
		if(overflow) {
			return "overflow";
		}
		else {
			for (int i=0; i<data.length; i++) {
        	System.out.print(data[i]);
        }
		return ".";
		}
	}
	
	// Method to convert the given binary number into decimal format.
	public int toDecimal() {
		int decimal = 0;
		int j = 0;
		for (int i = 0; i < data.length; i++) {
			j = data[i];
			decimal += (j * Math.pow(2, i));
		}

		return decimal;
	}
	
	// Method to clear overflow
	public void clearOverflow() {
		overflow = false;
	}
	
	

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
			System.out.println("Fourth digit of binary number num2 is " + num2.getDigit(3)+"\n\n");
			
			System.out.println("num2 in decimal format " + num2.toDecimal()+"\n\n");
			
//			//num2.shiftR(3);
//			
//			System.out.print("After Right Shift: ");
//			System.out.println(num2.toString()+"\n");
			
			
			BinaryNumber num3 = new BinaryNumber("0100");
			
			System.out.println("Adding num2 and num3");
			num2.add(num3);
			System.out.print("Output : ");
			System.out.println(num2.toString());
			System.out.print("Output in decimal : ");
			System.out.println(num2.toDecimal());
		}

	

}