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

public class SfileR {

	ServerSocket ss = null;
	Socket sk = null;

	InputStream is = null;
	OutputStream os = null;

	FileOutputStream fo = null;
	BufferedOutputStream bw = null;
	ArrayList<String> pic = null;

	SfileR(int port, String filename) {

		ready(port);
		picset(filename);
		savefile();

	}

	public void ready(int port) {
		try {
			System.out.println(port);
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress("127.0.0.1", port));

			System.out.println("대기중3");
			sk = ss.accept();
			System.out.println("입장3");
			os = sk.getOutputStream();
			is = sk.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void picset(String filename) {
		try {

			fo = new FileOutputStream(filename);
			bw = new BufferedOutputStream(fo);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void savefile() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {
					try {
						byte[] reBuf = new byte[256];
						int size = is.read(reBuf);
						fo.write(reBuf, 0, size);
						fo.flush();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}).start();
	}

	public void closeAll() {
		try {
			if (fo != null)
				fo.close();
			if (bw != null)
				bw.close();
			if (os != null)
				os.close();
			if (ss != null)
				ss.close();
			if (sk != null)
				sk.close();
			System.out.println("out");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
