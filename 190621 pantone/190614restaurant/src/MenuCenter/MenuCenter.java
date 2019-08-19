package MenuCenter;

import java.util.ArrayList;
import java.util.Scanner;

import Object.Menu_;

public class MenuCenter {
	private Scanner in = new Scanner(System.in);
	private static MenuCenter menucenter = null;
	ArrayList<Menu_> mList = new ArrayList<>();
	
	public ArrayList<Menu_> getmList() {
		return mList;
	}

	private MenuCenter() {
	}

	public static MenuCenter getInstace() {
		if (menucenter == null) {
			menucenter = new MenuCenter();
		}
		return menucenter;
	}

	public void menuprocess() {
		
		boolean pFlag = true;
		while (pFlag) {

			menu();
			int selNum = in.nextInt();
			in.nextLine();
			switch (selNum) {
			case 1:
				menuadd();
				break;
			case 2:
				menulist();
				break;
			case 3:
				menudel();
				break;

			default:
				pFlag = false;
			}
		}

	}

	private void menudel() {
		System.out.println("삭제하실 메뉴 번호를 입력해주세요");
		for (int i = 0; i < mList.size(); i++) {
			System.out.println(i + "/" + mList.get(i).getMenuName());
		}
		int delNum = in.nextInt();

		mList.remove(delNum);

	}

	private void menulist() {
		for (int i = 0; i < mList.size(); i++) {
			System.out.println(i + "/" + mList.get(i).getMenuName());
		}
	}

	private void menuadd() {
		Menu_ newM = new Menu_();
		System.out.println("추가하실 메뉴를 입력해주세요");
		String menu = in.nextLine();
		newM.setMenuName(menu);

		mList.add(newM);

	}

	private void menu() {
		System.out.println("메뉴를 선택하세요");
		System.out.println("1.메뉴추가");
		System.out.println("2.메뉴리스트");
		System.out.println("3.메뉴삭제");

	}

}
