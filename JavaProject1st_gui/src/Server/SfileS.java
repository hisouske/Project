package Server;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SfileS {
	ServerSocket ss = null;
	Socket sk = null;
	InputStream is = null;
	OutputStream os = null;
	FileInputStream fn = null;
	BufferedInputStream br = null;

	SfileS(int port, String fname) {

		ready(port);

		picset(fname);
		picsend();
		closeAll();

	}

	private void ready(int port) {
		try {
		
			System.out.println(port);
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress("127.0.0.1", port));

			System.out.println("대기중4");
			sk = ss.accept();
			System.out.println("입장4");
			os = sk.getOutputStream();
			is = sk.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void picset(String filename) {
		try {
			System.out.println(filename);
			fn = new FileInputStream(filename);
			br = new BufferedInputStream(fn);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void picsend() {
		int n = 0;
		try {
			byte buff[] = new byte[256];
			while ((n = fn.read(buff)) != -1) {
				os.write(buff, 0, n);
				System.out.println(n);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("보내짐");
		}
	}

	public void closeAll() {
		try {
			if (fn != null)
				fn.close();
			if (br != null)
				br.close();
			if (ss != null)
				ss.close();
			if (sk != null)
				sk.close();
			if (os != null)
				os.close();
			if (is != null)
				is.close();
			System.out.println("out");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
