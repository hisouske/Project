package Client;

import java.io.IOException;
import java.util.Scanner;

public class messageC {
	readyC c = null;
	connectC cc = null;
	Scanner in = new Scanner(System.in);

	messageC(readyC c, connectC cc) {
		this.c = c;
		this.cc = cc;
		taka();
		tiki();

	}

	private void tiki() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (;;) {
						cc.os.write(in.nextLine().getBytes());
						// c.os.write(msg.getBytes());
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}).start();
	}

	private void taka() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (;;) {

						byte[] read1 = new byte[100];
						cc.is.read(read1);
						String tiki = new String(read1).trim();
						System.out.println(tiki);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
