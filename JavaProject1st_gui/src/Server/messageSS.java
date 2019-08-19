package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class messageSS {
	readyS sss = null;
	String id = null;
	connectSS cs = null;

	messageSS(String id, readyS ss, connectSS connectSS) {

		this.sss = ss;
		this.id = id;
		this.cs = connectSS;

		sendid();
		taka();


	}

	private void sendid() {
		String sum = "";

		if (sss.cid.size() != 0) {
			for (int i = 0; i < sss.cid.size(); i++) {
				String a = sss.cid.get(i) + "/";
				sum = sum + a;
				if (i == sss.cid.size() - 1) {
					cs.tiki(sum);

				}                                                           
			}
		} else {
			cs.tiki("--접속자없음--/");
		}
	}

	private void taka() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				for (;;) {
					try {
						byte[] read1 = new byte[1024];
						cs.is.read(read1);
						String taka = new String(read1).trim();
						System.out.println(taka);
					
						ana(taka);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	private void ana(String taka) {

		String msg = taka;
		String sendid = null;
		String sendmsg = null;

		if (msg.equals("<<<종료>>>")) {
			// new connectS(cs.s, cs.id, cs.port);
			cs.menu();

		} else {
			StringTokenizer st = new StringTokenizer(msg, ">");
			sendid = st.nextToken();
			sendmsg = st.nextToken();

			for (int j = 0; j < sss.cid.size(); j++) {
				if (sss.cid.get(j).equals(sendid)) {
					sss.csk.get(j).tiki(id + ":" + sendmsg);
				}
			}
		}
	}
}
