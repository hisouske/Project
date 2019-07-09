package Ex1;

import java.util.ArrayList;

public class Ex1 {

	public static void main(String[] args) {
		int confirm[] = new int[5100];

		int sum = 0;

		for (int i = 0; i < 5000; i++) {
			int count1 = i % 10;
			int count10 = i % 100 / 10;
			int count100 = i % 1000 / 100;
			int count1000 = i / 1000;

			if (i < 10000) {
				confirm[i + count1 + count10 + count100 + count1000] = 1;
				
			} else if (i < 1000) {
				confirm[i + count1 + count10 + count100] = 1;

			} else if (i < 100) {
				confirm[i + count1 + count10] = 1;

			} else if (i < 10) {
				confirm[i + count1] = 1;
			}
		}

		for (int i = 0; i < 5000; i++) {
			if (confirm[i] != 1) {
				sum = sum + i;
			}
		}
		System.out.println(sum);

	}

}
