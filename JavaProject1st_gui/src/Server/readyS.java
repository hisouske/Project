package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import Client.clientDAO;
import Client.clientDTO;
import House.houseDAO;
import House.picDAO;

public class readyS {

	ServerSocket ss = null;
	Socket client0 = null;
	InputStream is = null;
	OutputStream os = null;

	Scanner in = new Scanner(System.in);

	ArrayList<clientDTO> clist = new ArrayList<>();
	int port = 8889;
	ArrayList<String> lid = new ArrayList<>();
	ArrayList<String> cid = new ArrayList<>();
	ArrayList<connectSS> lsk = new ArrayList<>();
	ArrayList<connectS> csk = new ArrayList<>();

	clientDAO cdao = clientDAO.getInstance();
	houseDAO hdao = houseDAO.getInstance();
	picDAO pdao = picDAO.getInstance();

	readyS() {
		listset();
		ready();

	}

	private void listset() {

		clist = cdao.getList();

	}

	private void set() {
		try {
			os = client0.getOutputStream();
			is = client0.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void startmsg() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (;;) {
					String num = taka();
					System.out.println(num);
					switch (num) {
					case "로그인":
						login();
						break;
					case "회원가입":
						join(client0);
						break;

					}
				}
			}
		}).start();

	}

	private void login() {

	

			String idpw = taka();
			StringTokenizer slash = new StringTokenizer(idpw, "/");

			String id = slash.nextToken();
			System.out.println(id);
			String pw = slash.nextToken();
			System.out.println(pw);

			String result = cdao.select2(id, pw);
			if (result != null) {
				String result2 = "<" + result + "> 님의 마이페이지  ";
				tiki(result2);
				String portt = Integer.toString(port);

				for (int j = 0; j < clist.size(); j++) {
					if (id.equals(clist.get(j).getId())) {
						tiki((portt + "/" + clist.get(j).getSort()));
						if (clist.get(j).getSort().equals("x")) {
							connectS newc = new connectS(this, id, port);
							cid.add(id);
							csk.add(newc);

							break;
						} else if (clist.get(j).getSort().equals("o")) {
							connectSS newcc = new connectSS(this, id, port);

							lid.add(id);
							lsk.add(newcc);

							break;
						}
					}
				}
			} else {
				tiki("다시");
				System.out.println("불일치");
		
			}
		}

	

	private void join(Socket client0) {

		for (;;) {
			String num = taka();

			switch (num) {
			case "ID확인":
				String id = taka();
				String an = cdao.select(id);
				tiki(an);
				break;
			case "가입하기":

				clientDTO nc = new clientDTO();
				nc.setSocket(client0);
				String msg1 = taka();
				System.out.println(msg1);
				StringTokenizer slash = new StringTokenizer(msg1, "/");

				String id1 = slash.nextToken();
				nc.setId(id1);
				String pw = slash.nextToken();
				nc.setPw(pw);
				String name = slash.nextToken();
				nc.setName(name);
				String sort = slash.nextToken();
				nc.setSort(sort);

				System.out.println(id1 + pw + name + sort);

				cdao.insert(nc);
				listset();
				login();
				break;
			}
		}

	}

	public void ready() {
		try {
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress("127.0.0.1", 8888));
			for (;;) {
				System.out.println("대기중");
				client0 = ss.accept();
				port++;
				System.out.println("입장");
				set();
				startmsg();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String taka() {
		String tiki = null;
		try {

			byte[] read1 = new byte[500];
			is.read(read1);
			tiki = new String(read1).trim();
			return tiki;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return tiki;
	}

	private void tiki(String tiki) {
		try {
			os.write(tiki.trim().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
