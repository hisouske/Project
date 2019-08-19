package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import Booking.bookDAO;
import Booking.bookDTO;
import House.houseDAO;
import House.houseDTO;
import House.picDAO;
import House.picDTO;

public class connectS {

	ServerSocket ss = null;
	readyS s = null;
	String id = null;
	Socket sk = null;
	int port;
	InputStream is = null;
	OutputStream os = null;

	Scanner in = new Scanner(System.in);

	houseDAO hdao = houseDAO.getInstance();
	bookDAO bdao = bookDAO.getInstance();
	picDAO pdao = picDAO.getInstance();

	ArrayList<houseDTO> hlist = new ArrayList<>();
	ArrayList<picDTO> plist = new ArrayList<>();

	connectS(readyS readyS, String id, int port) {
		this.s = readyS;
		this.id = id;
		this.port = port;
		ready(port);
		set();
		send(port - 5000);

		menu();
	}

	public void send(int port) {

		tiki(Integer.toString(plist.size()));
		System.out.println(plist.size());
		for (int i = 0; i < plist.size(); i++) {
			System.out.println(plist.get(i));
			tiki(plist.get(i).getPicname());
			new SfileS(port, plist.get(i).getPicname());
			tiki("전송완료");
		}

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
			plist = pdao.getList();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void menu() {
		messageS cMsg = null;

		boolean flag = true;

		while (flag) {
			System.out.println("야야야");
			String num = taka();
			System.out.println(num);

			switch (num) {
			case "시작":
				viewhouse();
				break;
			case "찾기":
				searhouse();
				break;
			case "예약":
				bohouse();
				break;
			case "예약내역":
				bolist();
				break;
			case "Message":
				if (cMsg == null) {
					cMsg = new messageS(id, s, this);
				}
				flag = false;
				break;
			default:
				break;
			}
		}

	}

	private void bolist() {
		try {
			String sum = "";
			ArrayList<bookDTO> myblist = bdao.select(id);
			if (myblist.size() != 0) {
				for (int i = 0; i < myblist.size(); i++) {
					String one = " < *숙소번호 :" + Integer.toString(myblist.get(i).getHnum()) + "   *숙소이름 :"
							+ hdao.search(myblist.get(i).getHnum()) + "   *예약일자 :" + myblist.get(i).getDay() + ">";
					sum = sum + one;
					if (i == myblist.size() - 1) {
						os.write(sum.getBytes());
					}
				}
			} else {
				os.write("예약내역이 없습니다".getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void bohouse() {

		ArrayList<String> dayl = new ArrayList<>();

		String booking = taka();
		StringTokenizer slash = new StringTokenizer(booking, "/");

		int search1 = Integer.parseInt(slash.nextToken());
		String search2 = (slash.nextToken()); // 숙박시작
		int search3 = Integer.parseInt(slash.nextToken());
		System.out.println(booking);
		String cnt = "";
		dayl = caldate(search2, search3);

		for (int i = 0; i < dayl.size(); i++) {
			String result = bdao.select(search1, dayl.get(i));
			if (result.equals("예약있음")) {
				String a = dayl.get(i);
				cnt = cnt + "<" + a + ">";
			}
			if (i == dayl.size() - 1 && cnt != "") {
				tiki(cnt + "일은 이미 예약이 있습니다");
			}

			else if (i == dayl.size() - 1 && cnt == "") {
				for (int j = 0; j < dayl.size(); j++) {
					bookDTO newb = new bookDTO();
					newb.setCid(id);
					newb.setHnum(search1);
					newb.setDay(dayl.get(j));
					newb.setCcid(hdao.search2(search1));
					bdao.insert(newb);
				}
				tiki("예약이 확정되었습니다. 즐거운여행되세요 :-)");
			}
		}
	}

	private ArrayList<String> caldate(String sday, int eday) {
		ArrayList<String> dayl = new ArrayList<>();
		String startDt = sday;
		int endDt = eday;
		int startYear = Integer.parseInt(startDt.substring(0, 4));
		int startMonth = Integer.parseInt(startDt.substring(4, 6));
		int startDate = Integer.parseInt(startDt.substring(6, 8));

		Calendar cal = Calendar.getInstance();

		cal.set(startYear, startMonth - 1, startDate);

		while (true) {

			dayl.add(getDateByString(cal.getTime()));
			cal.add(Calendar.DATE, 1); // one day increment
			if (getDateByInteger(cal.getTime()) > endDt)
				break;
		}
		return dayl;

	}

	private int getDateByInteger(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(sdf.format(date));
	}

	private String getDateByString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");
		return sdf.format(date);
	}

	private void searhouse() {
		String sum = "";
		ArrayList<houseDTO> searlist = hdao.search(taka());
		try {
			for (int i = 0; i < searlist.size(); i++) {
				String a = "  <" + (searlist.get(i).getNum()) + "번." + searlist.get(i).getName() + "|"
						+ Integer.toString(searlist.get(i).getPrice()) + ">  ";
				sum = sum + a;
				System.out.println(searlist.size());
				if (i == searlist.size() - 1) {
					os.write(sum.getBytes());
					System.out.println(sum);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void viewhouse() {
		try {
			hlist = hdao.getList();

			String sumsum = "";
			if (hlist != null) {
				for (int i = 0; i < hlist.size(); i++) {

					String sum = "   <" + Integer.toString(hlist.get(i).getNum()) + "번." + hlist.get(i).getName() + "|"
							+ hlist.get(i).getRegion() + "|" + Integer.toString(hlist.get(i).getPrice()) + ">   ";
					sumsum = sumsum + sum;
					if (i == hlist.size() - 1) {
						os.write(sumsum.getBytes());
					}
				}
			} else {
				os.write("조회 된 내용이 없습니다".getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private String tikitaka(String tiki) {
//		try {
//			os.write(tiki.trim().getBytes());
//			byte[] read = new byte[100];
//			is.read(read);
//			String taka = new String(read).trim();
//
//			return taka;
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		return tiki;
//
//	}

	private String taka() {
		String tiki = null;
		try {

			byte[] read1 = new byte[1024];
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
