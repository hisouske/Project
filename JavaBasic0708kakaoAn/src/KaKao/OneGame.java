package KaKao;

import java.util.ArrayList;

public class OneGame {

	private int point;
	private String bonus;
	private ArrayList<String> option = new ArrayList<>();
	private int myOneGamePoint;

	public void setPoint(int point) {
		this.point = point;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public void setAddOption(String op) {
		option.add(op);
	}

	public int cal() {
		switch (bonus) {
		case "s":
		case "S":
			myOneGamePoint = point;
			break;
		case "d":
		case "D":
			myOneGamePoint = point * point;
		case "t":
		case "T":
			myOneGamePoint = point * point * point;
			break;
		}
		
		
		for (int i = 0; i <option.size(); i ++) {
			switch (option.get(i)) {
			case "*":myOneGamePoint*=2;break;
			case "#":myOneGamePoint*=-1;
		}
	}
		return myOneGamePoint;
	
}
}