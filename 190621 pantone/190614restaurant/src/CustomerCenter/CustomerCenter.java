package CustomerCenter;

import java.util.ArrayList;
import java.util.Scanner;

import MenuCenter.MenuCenter;
import Object.CustomOrder_;
import Object.Customer_;
import Object.Menu_;

public class CustomerCenter {

	private Scanner in = new Scanner(System.in);
	private static CustomerCenter customercenter = null;
	private ArrayList<Customer_> cList = new ArrayList<>();
	private MenuCenter MenuLink = null;
	private Customer_ nowCus = null;
	private String[] table = new String[5];
	static int nowtable;
	CustomOrder_ c = new CustomOrder_();

	private CustomerCenter() {
	}

	public static CustomerCenter getInstance() {
		if (customercenter == null) {
			customercenter = new CustomerCenter();
		}
		return customercenter;
	}

	public void customerprocess() {
		MenuLink = MenuCenter.getInstace();
		boolean pFlag = true;
		while (pFlag) {

			menu();
			int selNum = in.nextInt();
			in.nextLine();
			switch (selNum) {
			case 1:
				order(nowCus);
				break;
			case 2:
				orderdel();
				break;
			case 3:
				orderview(nowCus);
				break;

			default:
				pFlag = false;
			}
		}
	}

	public Customer_ tablenum() {
		System.out.println("손님 성함을 입력해주세요");
		String name = in.nextLine();
		nowCus = new Customer_();

		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				nowCus.setCustomerName(name);
				nowCus.setTableNum(i);
				cList.add(nowCus);
				table[i] = name;
				nowCus = cList.get(i);
				System.out.println(i + "번 테이블");
				System.out.println(name + "고객님");

				break;

			}

		}
		return nowCus;

	}

	public void nowtable2() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				nowtable++;
				System.out.print("잔여석 <" + i + ">");
			} else {
				System.out.print(" 예약석-" + i + table[i]);
			}
		}
		System.out.println("<잔여석>총" + nowtable + "석 남았습니다");
		nowtable = 0;
	}

	private void orderview(Customer_ nowCus) {
		ArrayList<CustomOrder_> basket = nowCus.getOrderList();
		for (int i = 0; i < basket.size(); i++) {
			System.out.println(basket.get(i).getMenu() + basket.get(i).getMenuNum());
		}
	}

	private void orderdel() {
		ArrayList<CustomOrder_> basket = nowCus.getOrderList();

		for (int i = 0; i < basket.size(); i++) {
			System.out.println(i + "번 :" + basket.get(i).getMenu() + basket.get(i).getMenuNum());
		}

		System.out.println("삭제 할 메뉴의 번호를 입력해주세요");
		int num = in.nextInt();
		for (int i = 0; i < basket.size(); i++) {
			basket.remove(num);
		}

	}

	private void order(Customer_ nowCus) {
		CustomOrder_ c = new CustomOrder_();
		ArrayList<Menu_> mlist = MenuLink.getmList();
		for (int i = 0; i < mlist.size(); i++) {
			System.out.println(i + "번 : " + mlist.get(i).getMenuName());
		}
		System.out.println("주문하실 메뉴 번호를 입력하세요: ");
		int mnum = in.nextInt();
		in.nextLine();
		c.setMenu(mlist.get(mnum).getMenuName());
		System.out.println("수량을 입력하세요: ");
		int mcnt = in.nextInt();
		in.nextLine();
		c.setMenuNum(mcnt);
		nowCus.getOrderList().add(c);

		System.out.println(mlist.get(mnum).getMenuName() + mcnt);
	}

	private void menu() {
		System.out.println("메뉴를 선택해주세요");
		System.out.println("1.주문하기");
		System.out.println("2.주문삭제");
		System.out.println("3.주문보기");

	}

}
