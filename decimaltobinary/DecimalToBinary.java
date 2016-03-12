class DecimalToBinary {
	public static void main(String[]args) {

	    if (args.length < 1){
	      System.out.println("You need to type atleast a number ");
	     return;
	     }
		// Declare and instanciate variables.
		int vaartus = Integer.parseInt(args[0]);

		boolean lopp = false;
		String result = "";
		// Handle default cases: 0 and 1.
		if(vaartus == 1 || vaartus == 0){
			System.out.println(vaartus);
		} else {
			while(!lopp){
				result = (vaartus % 2) + result;
				vaartus = (vaartus / 2);
				if(vaartus == 1){
					lopp = true;
					result = 1 + result;					
				}
			}
		}
		System.out.println(result);
	}
}