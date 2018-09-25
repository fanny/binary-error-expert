package util;

public class Converter {

	public static int[] convertToIntArray(String string) {
		int[] array = new int[string.length()];
		
		for (int index = 0; index < string.length(); index++) {
			array[index] = Character.getNumericValue(string.charAt(index));
		}
		
		return array;
	}

	public static String convertToString(int[] array) {
		String string = "";
		
		for (int index = 0; index < array.length; index++) {
			string += array[index];
		}
		
		return string;
	}

}
