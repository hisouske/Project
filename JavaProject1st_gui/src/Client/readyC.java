package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class readyC {

	Socket client1 = null;
	Scanner in = new Scanner(System.in);
	InputStream is = null;
	OutputStream os = null;

	readyC() {
		ready();
		set();
		startmsg();

	}

	private void set() {
		try {
			os = client1.getOutputStream();
			is = client1.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void startmsg() {
		try {

			byte[] read = new byte[100];
			is.read(read);
			String num = new String(read);
			System.out.println(num);

			String choice = in.nextLine();
			os.write(choice.getBytes());

			switch (choice) {
			case "1":
				login();
				break;
			case "2":
				join();
				break;
			case "3":
				break;

			}

		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void join() {
		try {
			for (int i = 0; i < 1; i++) {
				byte[] read = new byte[100];
				is.read(read);
				String num = new String(read);
				System.out.println(num);

				os.write(in.nextLine().getBytes());

				byte[] read0 = new byte[100];
				is.read(read0);
				String msg = new String(read0).trim();
				System.out.println(msg);

				os.write(in.nextLine().getBytes());

				byte[] read1 = new byte[100];
				is.read(read1);
				String msg1 = new String(read1).trim();
				System.out.println(msg1);

				os.write(in.nextLine().getBytes());

				byte[] read2 = new byte[100];
				is.read(read2);
				String msg2 = new String(read2).trim();
				System.out.println(msg2);

				os.write(in.nextLine().getBytes());

				byte[] read3 = new byte[100];
				is.read(read3);
				String msg3 = new String(read3).trim();
				System.out.println(msg3);
				os.write(in.nextLine().getBytes());
			}
			login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void login() {

		try {
			for (int i = 0; i < 1; i++) {
				byte[] read2 = new byte[100];
				is.read(read2);
				String msg2 = new String(read2).trim();
				System.out.println(msg2);
				os.write(in.nextLine().getBytes());

				byte[] read3 = new byte[100];
				is.read(read3);
				String msg3 = new String(read3).trim();
				System.out.println(msg3);
				os.write(in.nextLine().getBytes());

				byte[] read4 = new byte[100];
				is.read(read4);
				String msg4 = new String(read4).trim();
				System.out.println(msg4);

				byte[] read7 = new byte[4];
				is.read(read7);
				int msg7 = Integer.parseInt(new String(read7).trim());
				System.out.println(msg7); // portnum

				if (msg4.equals("다시 로그인해주세요")) {
					i--;
					continue;
				}
				if (!msg4.equals("다시 로그인해주세요")) {
					byte[] read5 = new byte[100];
					is.read(read5);
					String msg5 = new String(read5).trim();
//					System.out.println(msg5);

					if (msg5.equals("o")) {
						System.out.println(msg7);
						new connectCC(this, msg7);

					} else if (msg5.equals("x")) {

						new connectC(this, msg7);

					}
				}
			}
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ready() {
		try {
			client1 = new Socket("127.0.0.1", 8888);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
