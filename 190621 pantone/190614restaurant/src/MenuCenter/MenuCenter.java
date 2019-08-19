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
		System.out.println("�����Ͻ� �޴� ��ȣ�� �Է����ּ���");
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
		System.out.println("�߰��Ͻ� �޴��� �Է����ּ���");
		String menu = in.nextLine();
		newM.setMenuName(menu);

		mList.add(newM);

	}

	private void menu() {
		System.out.println("�޴��� �����ϼ���");
		System.out.println("1.�޴��߰�");
		System.out.println("2.�޴�����Ʈ");
		System.out.println("3.�޴�����");

	}

}
