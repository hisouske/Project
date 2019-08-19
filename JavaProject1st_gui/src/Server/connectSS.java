package Server;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import Booking.bookDAO;
import Booking.bookDTO;
import Client.clientDAO;
import House.houseDAO;
import House.houseDTO;
import House.picDAO;

public class connectSS {
	ServerSocket ss = null;
	readyS s = null;
	String id = null;
	Socket sk = null;
	int port;

	InputStream is = null;
	OutputStream os = null;

	FileOutputStream fo = null;
	BufferedOutputStream bw;

	Scanner in = new Scanner(System.in);

	houseDAO hdao = houseDAO.getInstance();
	bookDAO bdao = bookDAO.getInstance();

	ArrayList<houseDTO> hlist = new ArrayList<>();
	picDAO pdao = picDAO.getInstance();

	ArrayList<String> myhouse = null;

	connectSS(readyS readyS, String id, int port) {
		this.s = readyS;
		this.id = id;
		this.port = port;
		this.myhouse = pdao.search(id);
		ready(port);
		set();
		menu();
	}

	public void ready(int port) {
		try {
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress("127.0.0.1", port));

			System.out.println("대기중2");
			sk = ss.accept();
			System.out.println("입장2");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void set() {
		try {
			os = sk.getOutputStream();
			is = sk.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void menu() {
		messageSS cMsg = null;
		boolean flag = true;

		while (flag) {
			System.out.println("야야야");
			String num = taka();
			System.out.println(num);

			switch (num) {
			case "예약현황":
				blist();
				break;
			case "등록현황":
				myhlist();
				break;
			case "등록":
				houser();
				break;
			case "삭제":
				delhouse();
				break;
			case "Message":
				if (cMsg == null) {
					cMsg = new messageSS(id, s, this);

				}
				flag = false;
				break;

			case "사진등록":
				String picname = taka();
				System.out.println(picname);
				SfileR newfile = new SfileR(port - 5000, 1 + picname);
				pdao.insert(1 + picname, id);

				if (taka().equals("파일전송완료")) {
					System.out.println("ddd");
					newfile.closeAll();
				}
				break;
			default:
				break;
			}
		}

	}

	private void delhouse() {
		String delresult = hdao.delete(Integer.parseInt(taka()), id);
		System.out.println(delresult);
		if (delresult.equals("ok")) {
			tiki("삭제가 완료되었습니다");
		} else {
			tiki("삭제 할 수 없는 숙소입니다");
		}

	}

	private void myhlist() {
		String sum = "";
		ArrayList<houseDTO> myhlist = hdao.getList();

		if (myhlist.size() != 0) {
			for (int i = 0; i < myhlist.size(); i++) {
				if (myhlist.get(i).getId().equals(id)) {
					String a = "   <" + myhlist.get(i).getNum() + " 숙소이름 :" + myhlist.get(i).getName() + " 지역 :"
							+ myhlist.get(i).getRegion() + " 1박 가격 :" + myhlist.get(i).getPrice() + " 숙소평점 :"
							+ myhlist.get(i).getPoint() + ">   ";
					sum = sum + a;
				}
				if (i == myhlist.size() - 1) {
					tiki(sum);
				}
			}
		} else {
			tiki("조회 된 내용이 없습니다");
		}
	}

	private void houser() {
		try {
			String nhouse = taka();
			StringTokenizer slash = new StringTokenizer(nhouse, "/");

			houseDTO newh = new houseDTO();
			newh.setId(id);
			newh.setName(slash.nextToken());
			newh.setRegion(slash.nextToken());
			newh.setPrice(Integer.parseInt(slash.nextToken()));
			hdao.insert(newh);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void blist() {

		String sum = "";
		ArrayList<bookDTO> blist = bdao.selectcc(id);
		if (blist.size() != 0) {
			for (int i = 0; i < blist.size(); i++) {

				String a = "   <  예약번호: " + blist.get(i).getNum() + " 숙소이름 : " + hdao.search(blist.get(i).getHnum())
						+ " 예약고객ID: " + blist.get(i).getCcid() + " 예약일자: " + blist.get(i).getDay() + ">   ";
				sum = sum + a;
				if (i == blist.size() - 1) {
					tiki(sum);
				}
			}
		} else {
			tiki("예약내역이 없습니다");
		}
	}

	private String taka() {
		String tiki = null;
		try {

			byte[] read1 = new byte[500];
			is.read(read1);
			String taka = new String(read1).trim();
			return taka;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tiki;
	}

	void tiki(String sendmsg) {
		try {
			os.write(sendmsg.trim().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
