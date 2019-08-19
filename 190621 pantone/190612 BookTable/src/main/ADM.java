package main;

import java.util.List;
import java.util.Scanner;

import book.BookDAO;
import book.BookDTO;

public class ADM {
	Scanner sc = new Scanner(System.in);
	int selectedMenuNumber;
	BookDAO dao;

	ADM() {
		printMenu();
		selectedMenuNumber = sc.nextInt();
		sc.nextLine();
		

		dao = BookDAO.getInstance();

		switch (selectedMenuNumber) {
		case 1:
			Insert1();
			break;
		case 2:
			delete1();
			break;
		case 3:
			mod1();
			break;
		case 4:
			list();
			break;
		}
	}

	public void printMenu() {
		System.out.println("원하시는 메뉴를 누르세요");
		System.out.println("1. 등록");
		System.out.println("2. 삭제");
		System.out.println("3. 수정");
		System.out.println("4. 대여 조회");
		
	}

	public void Insert1() {

		BookDTO dto = new BookDTO();

		System.out.println("책 이름을 입력하세요");
		dto.setName(sc.nextLine());
	
		System.out.println("저자를 입력하세요");
		dto.setAuthor(sc.nextLine());
	
		System.out.println("대여 계정을 입력하세요");
		dto.setRent_id(sc.nextLine());

		dao.insert(dto);
	}

	public void delete1() {

		BookDTO dto = new BookDTO();

		System.out.println("삭제 할 도서 번호를 입력해주세요");
		dto.setIsbn(sc.nextInt());

		dao.delete(dto);
	}

	public void mod1() {

		BookDTO dto = new BookDTO();

		System.out.println("수정 할 도서 번호를 입력해주세요");
		dto.setIsbn(sc.nextInt());

		System.out.println("<수정> 이름");
		dto.setName(sc.nextLine());
		
		System.out.println("<수정> 저자");
		dto.setAuthor(sc.nextLine());

		System.out.println("<수정> 빌린사람");
		dto.setRent_id(sc.nextLine());
		dao.mod(dto);
	}
public void list() {

	List<BookDTO> dtoList = dao.getlist();
	for(int i =0; i<dtoList.size();i++) {
		System.out.println(dtoList.get(i).getName());
		System.out.println(dtoList.get(i).getAuthor());
		System.out.println(dtoList.get(i).getRent_id()+"\n");
	}
}
	
}
