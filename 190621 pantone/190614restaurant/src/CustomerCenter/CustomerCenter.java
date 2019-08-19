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
		System.out.println("�մ� ������ �Է����ּ���");
		String name = in.nextLine();
		nowCus = new Customer_();

		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				nowCus.setCustomerName(name);
				nowCus.setTableNum(i);
				cList.add(nowCus);
				table[i] = name;
				nowCus = cList.get(i);
				System.out.println(i + "�� ���̺�");
				System.out.println(name + "����");

				break;

			}

		}
		return nowCus;

	}

	public void nowtable2() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				nowtable++;
				System.out.print("�ܿ��� <" + i + ">");
			} else {
				System.out.print(" ���༮-" + i + table[i]);
			}
		}
		System.out.println("<�ܿ���>��" + nowtable + "�� ���ҽ��ϴ�");
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
			System.out.println(i + "�� :" + basket.get(i).getMenu() + basket.get(i).getMenuNum());
		}

		System.out.println("���� �� �޴��� ��ȣ�� �Է����ּ���");
		int num = in.nextInt();
		for (int i = 0; i < basket.size(); i++) {
			basket.remove(num);
		}

	}

	private void order(Customer_ nowCus) {
		CustomOrder_ c = new CustomOrder_();
		ArrayList<Menu_> mlist = MenuLink.getmList();
		for (int i = 0; i < mlist.size(); i++) {
			System.out.println(i + "�� : " + mlist.get(i).getMenuName());
		}
		System.out.println("�ֹ��Ͻ� �޴� ��ȣ�� �Է��ϼ���: ");
		int mnum = in.nextInt();
		in.nextLine();
		c.setMenu(mlist.get(mnum).getMenuName());
		System.out.println("������ �Է��ϼ���: ");
		int mcnt = in.nextInt();
		in.nextLine();
		c.setMenuNum(mcnt);
		nowCus.getOrderList().add(c);

		System.out.println(mlist.get(mnum).getMenuName() + mcnt);
	}

	private void menu() {
		System.out.println("�޴��� �������ּ���");
		System.out.println("1.�ֹ��ϱ�");
		System.out.println("2.�ֹ�����");
		System.out.println("3.�ֹ�����");

	}

}
