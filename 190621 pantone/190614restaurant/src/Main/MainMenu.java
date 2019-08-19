package Main;

import java.util.Scanner;

import CustomerCenter.CustomerCenter;
import MenuCenter.MenuCenter;

public class MainMenu {
	private Scanner in = new Scanner(System.in);
	private CustomerCenter cc = null;
	private MenuCenter mc = null;

//	private MemberCenter mc=null;
//	private ShopADM shopM = null;
//	
	MainMenu() {
		boolean pFlag = true;
		while (pFlag) {
			menu();
			int selNum = in.nextInt();
			in.nextLine();
			switch (selNum) {
			case 1:
				custable();
				break;
			case 2:
				nowtable();
				break;
			case 3:
				foodmenu();
				break;
			case 4:
				pFlag = false;
			}
		}

	}

	private void foodmenu() {
		mc = MenuCenter.getInstace();
		mc.menuprocess();
	}

	private void nowtable() {
		cc = CustomerCenter.getInstance();
		cc.nowtable2();

	}

	private void menu() {
		System.out.println(" 메뉴를 선택하세요 ");
		System.out.println("1. 손님자리배정");
		System.out.println("2. 잔여석정보");
		System.out.println("3. 메뉴관리");
		System.out.println("4. 종료");
	}

	private void custable() {
		cc = CustomerCenter.getInstance();
		cc.tablenum();
		cc.customerprocess();

	}

}
