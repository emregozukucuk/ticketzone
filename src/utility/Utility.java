package utility;

public class Utility {
	
	// reverseDate takes date as an argument, then reverses it.
	// It splits date to it's components with given splitter,
	// then reverses it by joining with the given delimiter
	public static String reverseDate(String date, String splitter, String delimiter) {
		String[] components = date.split(splitter);
		
		int j = components.length;
		for (int i = 0; i < components.length / 2; i++) {
			j--;
			
			String temp = components[i];
			components[i] = components[j];
			components[j] = temp;
		}
		
		return String.join(delimiter, components);
	}
}
