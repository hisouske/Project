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
			System.out.println(i + "번 : " +nowName+ "/" + basket.get(i).getCnt());
		
			for(int j=0; j < glist.size(); j++) {
				if(glist.get(j).getName().equals(nowName)) {
					Goods imsiTemp = glist.get(j);
					
					System.out.println("상세정보");
					System.out.println("이름:"+imsiTemp.getName());
					System.out.println("남은수랑:"+imsiTemp.getCnt());
					break;
				}
			}
		
		
		}

	}


	private void list(Member m) {
		ArrayList<Goods> glist = shopLink.getgList();

		for (int i = 0; i < glist.size(); i++) {
			System.out.println(i + "번 : " + glist.get(i).getName() + "/" + glist.get(i).getCnt());
		}
		System.out.println("구매하실 물건의 번호를 입력하세요-- : 없으면 x를 입력하세요");
		int gnum = in.nextInt();
		in.nextLine();
		
		ByInfo b= new ByInfo();
		b.setGoodsName(glist.get(gnum).getName());
		
		System.out.println("수량을 선택해라");
		int gcnt = in.nextInt();
		in.nextLine();
		b.setCnt(gcnt);
		glist.get(gnum).setCnt(glist.get(gnum).getCnt()-gcnt);
		
		m.getMyBasket().add(b);

	}

	private void menu() {
		// TODO Auto-generated method stub
		System.out.println("1. 물품구경");
		System.out.println("2. 장바구니보기");
		System.out.println("3. 이전메뉴");
	}

	private void infoUser() {
		System.out.println(nowUser.getId() + "/" + nowUser.getName() + "님 ");
	}
}
