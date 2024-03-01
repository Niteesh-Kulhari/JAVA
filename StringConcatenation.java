public class StringConcatenation {

	public static void main(String[] args) {
		//Concate String on multiple lines
		System.out.println("The String concatenation operator" +
							"can be used to" +
							"concate String on multiple lines");

		//String concatenation can join various data types.
		System.out.println("We can join a string to " +
							"a number like this: " + 5);
		/*	The Concatenation operator can be used to format 
		 * 	complex String objects.
		 */
		System.out.println("The following will be printed "
                + "in a tabbed format: "
                + "\n\tFirst = " + 5 * 6 + ","
                + "\n\tSecond = " + (6 + 4) + ","
                + "\n\tThird = " + 16.7 + "."); 
	}
}
