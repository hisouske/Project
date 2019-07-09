package KaKao;

import java.util.ArrayList;
import java.util.Scanner;

public class GameRoom {
	ArrayList<OneGame> myGame = new ArrayList<>();
	OneGame o = new OneGame();
	Scanner in = new Scanner(System.in);
	int finalPoint = 0;

	GameRoom() {
		menu();
		menu();
		menu();

		for (int i = 0; i < myGame.size(); i++) {
			finalPoint = finalPoint + myGame.get(i).cal();
		}
		System.out.println(finalPoint);
	}

	public void menu() {

		int gameNum = myGame.size()+1;
		System.out.println(gameNum + "��° �����Դϴ�");

		OneGame o = new OneGame();
		System.out.println("�����Է� :");
		int p = in.nextInt();
		in.nextLine();

		System.out.println("���ʽ��Է� <s,d,t> :");
		String b = in.nextLine();

		System.out.println("�ɼ� <*,#> :");
		String op = in.nextLine();

		if (gameNum > 1 && op.equals("*")) {
			myGame.get(gameNum - 1 - 1).setAddOption(op);
		}
		o.setPoint(p);
		o.setBonus(b);
		o.setAddOption(op);
		myGame.add(o);
	}

}
