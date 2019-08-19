package ShoppingUser;

import java.util.ArrayList;
import java.util.Scanner;

import GoodsADM.ShopADM;
import Object.ByInfo;
import Object.Goods;
import Object.Member;

public class MemberShopping {
	private Scanner in = new Scanner(System.in);
	private Member nowUser = null;
	private ShopADM shopLink = null;

	public MemberShopping(Member m) {
		this.nowUser = m;
		shopLink = ShopADM.getInstance();
		infoUser();
		boolean pFlag = true;
		while (pFlag) {
			menu();
			int selNum = in.nextInt();
			in.nextLine();
			switch (selNum) {
			case 1:
				list(m);
				break;
			case 2:
				viewBasket(m);
				break;
			// case 3: del(); break;
			default:
				pFlag = false;
			}
		}
	}

	private void viewBasket(Member m) {
		ArrayList<ByInfo> basket = nowUser.getMyBasket();
		ArrayList<Goods> glist = shopLink.getgList();
		for (int i = 0; i < basket.size(); i++) {
			String nowName = basket.get(i).getGoodsName();
			System.out.println(i + "�� : " +nowName+ "/" + basket.get(i).getCnt());
		
			for(int j=0; j < glist.size(); j++) {
				if(glist.get(j).getName().equals(nowName)) {
					Goods imsiTemp = glist.get(j);
					
					System.out.println("������");
					System.out.println("�̸�:"+imsiTemp.getName());
					System.out.println("��������:"+imsiTemp.getCnt());
					break;
				}
			}
		
		
		}

	}


	private void list(Member m) {
		ArrayList<Goods> glist = shopLink.getgList();

		for (int i = 0; i < glist.size(); i++) {
			System.out.println(i + "�� : " + glist.get(i).getName() + "/" + glist.get(i).getCnt());
		}
		System.out.println("�����Ͻ� ������ ��ȣ�� �Է��ϼ���-- : ������ x�� �Է��ϼ���");
		int gnum = in.nextInt();
		in.nextLine();
		
		ByInfo b= new ByInfo();
		b.setGoodsName(glist.get(gnum).getName());
		
		System.out.println("������ �����ض�");
		int gcnt = in.nextInt();
		in.nextLine();
		b.setCnt(gcnt);
		glist.get(gnum).setCnt(glist.get(gnum).getCnt()-gcnt);
		
		m.getMyBasket().add(b);

	}

	private void menu() {
		// TODO Auto-generated method stub
		System.out.println("1. ��ǰ����");
		System.out.println("2. ��ٱ��Ϻ���");
		System.out.println("3. �����޴�");
	}

	private void infoUser() {
		System.out.println(nowUser.getId() + "/" + nowUser.getName() + "�� ");
	}
}
