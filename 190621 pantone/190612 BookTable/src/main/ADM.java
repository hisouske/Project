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
		System.out.println("���Ͻô� �޴��� ��������");
		System.out.println("1. ���");
		System.out.println("2. ����");
		System.out.println("3. ����");
		System.out.println("4. �뿩 ��ȸ");
		
	}

	public void Insert1() {

		BookDTO dto = new BookDTO();

		System.out.println("å �̸��� �Է��ϼ���");
		dto.setName(sc.nextLine());
	
		System.out.println("���ڸ� �Է��ϼ���");
		dto.setAuthor(sc.nextLine());
	
		System.out.println("�뿩 ������ �Է��ϼ���");
		dto.setRent_id(sc.nextLine());

		dao.insert(dto);
	}

	public void delete1() {

		BookDTO dto = new BookDTO();

		System.out.println("���� �� ���� ��ȣ�� �Է����ּ���");
		dto.setIsbn(sc.nextInt());

		dao.delete(dto);
	}

	public void mod1() {

		BookDTO dto = new BookDTO();

		System.out.println("���� �� ���� ��ȣ�� �Է����ּ���");
		dto.setIsbn(sc.nextInt());

		System.out.println("<����> �̸�");
		dto.setName(sc.nextLine());
		
		System.out.println("<����> ����");
		dto.setAuthor(sc.nextLine());

		System.out.println("<����> �������");
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
