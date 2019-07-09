package Ex1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex2 {

	public static void main(String[] args) {

		ArrayList<ExDTO> game = new ArrayList<ExDTO>();
		Scanner in = new Scanner(System.in);
		int gameCount = 0;
		int sum = 0;

		while (gameCount < 3) {
			System.out.println(gameCount + 1 + "¹øÂ° DartResult");
			String result = in.nextLine();
			ExDTO one = new ExDTO();
			gameCount++;
			int start = 0;
			int end = 0;

			for (int i = 0; i < result.length(); i++) {

				char r = result.charAt(i);

				if (r == '0' || r == '2' || r == '3' || r == '4' || r == '5' || r == '6' || r == '7' || r == '8'
						|| r == '9' || r == '1') {
					end = i + 1;
				}
			}
			one.setScore(Integer.parseInt(result.substring(start, end)));
//			System.out.println(one.getScore());

			for (int i = 0; i < result.length(); i++) {

				char r = result.charAt(i);

				if (r == 's' || r == 'd' || r == 't') {
					start = end;
					end = i + 1;
				}
			}
			one.setBonus(result.substring(start, end));
//			System.out.println(one.getBonus());

			for (int i = 0; i < result.length(); i++) {

				char r = result.charAt(i);

				if (r == '*' || r == '#') {
					start = end;
					end = i + 1;
					one.setMyOption(result.substring(start, end));
				}
				if (r == 's' || r == 'd' || r == 't') {
					one.setMyOption("0");
				}
			}
//			System.out.println(one.getMyOption());
			game.add(one);

			int one1;
			int bonus = 1;

			if (gameCount == 3) {

				for (int i = 0; i < gameCount; i++) {
					if (game.get(i).getBonus().equals("d")) {
						bonus = 2;
						one1 = (int) Math.pow(game.get(i).getScore(), bonus);
						game.get(i).setScore(one1);

					} else if (game.get(i).getBonus().equals("t")) {
						bonus = 3;
						one1 = (int) Math.pow(game.get(i).getScore(), bonus);
						game.get(i).setScore(one1);
					}

//				sum = sum + game.get(i).getScore();

				}

				for (int i = 0; i < gameCount; i++) {

					if (game.get(i).getMyOption().equals("*")) {
						int score = game.get(i).getScore();
						game.get(i).setScore((score) * 2);
						if (i > 0) {
							int score1 = game.get(i - 1).getScore();
							game.get(i - 1).setScore(score1 * 2);
						}

					}
					if (game.get(i).getMyOption().equals("#")) {
						int score = game.get(i).getScore();
						game.get(i).setScore(score * -1);
					}

				}
				for (int i = 0; i < gameCount; i++) {
					System.out.println(game.get(i).getScore());

				}
			}
		}
	}
}
