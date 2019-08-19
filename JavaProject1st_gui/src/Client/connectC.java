package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class connectC {
	readyC c = null;
	String myid = null;
	Socket s = null;
	InputStream is = null;
	OutputStream os = null;
	Scanner in = new Scanner(System.in);
	messageC mymsg = null;

	connectC(readyC c, int msg7) {
		this.c = c;
		ready(msg7);
		set();
		sendm();
	}

	private void set() {
		try {
			os = s.getOutputStream();
			is = s.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ready(int port) {
		try {
			s = new Socket("127.0.0.1", port);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendm() {

		try {
			boolean flag = true;
			while (flag) {
				taka2();
				String num = in.nextLine().trim();
				os.write(num.getBytes());

				switch (num) {
				case "1":
					taka2();
					break;
				case "2":
					taka2();
					tiki();
					taka2();
					break;
				case "3":
					taka2();
					tiki();
					taka2();
					tiki();
					taka2();
					tiki();
					taka2();
					break;
				case "4":
					taka2();
					break;
				case "5":
					mymsg = new messageC(c, this);
					
					flag = false;
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void taka2() {

		try {

			System.out.println("server2>");
			byte[] read1 = new byte[200];
			is.read(read1);
			System.out.println(new String(read1));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void tiki() {
		try {
			os.write(in.nextLine().trim().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
