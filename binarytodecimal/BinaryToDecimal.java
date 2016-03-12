class BinaryToDecimal {
	public static void main(String[]args) {

	    if (args.length < 1){
	      System.out.println("You need to type atleast a number ");
	     return;
	     }
		// Declare and instanciate variables.
		long binary = Long.parseLong(args[0]);
		String binaryString = Long.toString(binary);
		int decimal = 0;

		for(int i = 0; i < binaryString.length(); i++){

			char binarySlice = binaryString.charAt(i);
			decimal = decimal * 2 + binarySlice;
		}

		System.out.println(decimal);
	}
}