package correction;

import java.util.Scanner;

public class Hamming {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		final int EXTRA_BITS = 4;

		String user_input = sc.next();
		int user_input_size = user_input.length();
		int transmitted_data_size = user_input_size + EXTRA_BITS;
		int[] transmitted_data = calculate_parity_array(user_input, transmitted_data_size);
		for(int data: transmitted_data) {
			System.out.print(data);
		}
		System.out.println("\n");
		int[] a = fix_error(new int[] {1,0,1,0}, new int[] {0,1,1,1,0,0,1,0,1,1,1,0});
		for(int data: a) {
			System.out.print(data);
		}
		System.out.println("\n");
		sc.close();
	}
	
	

	public static int[] calculate_parity_array(String input, int size) {
		int[] parity_data = new int[size];
		int index_input = 0;
		for (int i = 0; i < size; i++) {
			if (!is_index_power_2(i+1)) {
				parity_data[i] = Integer.parseInt(Character.toString(input.charAt(index_input++)));
			}
		}
		for (int i = 0; i < size; i++) {
			if (is_index_power_2(i+1)) {
				parity_data[i] = calculate_extra_bit_value(i+1, size, parity_data);
			}
		}
		
		return parity_data;

	}
	
	private static boolean is_index_power_2(int index) {
		return (index & (index - 1)) == 0;
	}
	
	public static int calculate_extra_bit_value(int pos, int transmitted_data_size, int[] transmitted_data) {
		int result = 0;
		for (int i = pos-1; i < transmitted_data_size;) {
			int j = i;
			for (; j < i + pos && j < transmitted_data_size; j++) {
				result = result ^ transmitted_data[j] ;
			}
			i = j + pos;
		}
		return result;
	}

	public static boolean is_equals(int[] transmitted_data, int[] received_data) {
		int k[] = new int[4];
		for (int i = 0; i < 4; i++) {
			k[i] = calculate_extra_bit_value(i + 1, received_data.length, received_data);
		}
		return k == new int[] { 0, 0, 0, 0 };
	}

	public static int[] fix_error(int[] received_parity_bits, int[] received_data) {
		int sum = 0;
		for(int i = 0; i < received_parity_bits.length; i++) {
			int bit = received_parity_bits[i];
			sum += (Math.pow(2, i+1) * bit);
		}
		// Se eu tiver 0 and 0 = 1, se eu tiver 1 and 0 = 0
		received_data[sum-1] = received_data[sum-1] & 0;
		return received_data;
	}

}
