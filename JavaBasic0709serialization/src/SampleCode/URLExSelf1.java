package SampleCode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class URLExSelf1 {
	static URLDAO urldao = null;

	private static void init() {
		urldao = URLDAO.getInstance();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		ArrayList<String> t = new ArrayList<>();
		ArrayList<String> t1 = new ArrayList<>();
		String testUrl = "http://www.human.or.kr:80";
		// ��������(protocol), hostname(ȣ��Ʈ�� �̸� ), ��Ʈ��ȣ(portnumber)
		// ���񽺹�� ���񽺹�ȣ
		try {
			URL url = new URL(testUrl);
			System.out.println(url.getProtocol());
			System.out.println(url.getHost());
			System.out.println(url.getPort());

			InputStream ins = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(ins));

			String str = "";
			int lineCnt = 0;
			int fromIndex = -1;
			String searchWord = "jpg";
			while ((str = br.readLine()) != null) {
				while ((fromIndex = str.indexOf(searchWord, fromIndex + 1)) >= 0) {
					lineCnt++;
//					System.out.println("***"+lineCnt);
					for (int i = fromIndex; i > 0; i--) {
						if (str.charAt(i) == ':') {
							URLDTO a = new URLDTO();
							a.setUrladdr((String) str.subSequence(i - 4, fromIndex + searchWord.length()));

							urldao.insert(a);
							t.add((String) str.subSequence(i - 4, fromIndex + searchWord.length()));
							System.out.println((String) str.subSequence(i - 4, fromIndex + searchWord.length()));

							break;
						}

					}
					for (int i = fromIndex; i > 0; i--) {
						if (str.charAt(i) == '/') {

							t1.add((String) str.subSequence(i + 1, fromIndex));
							System.out.println((String) str.subSequence(i + 1, fromIndex - 1));

							break;
						}
					}
				}
			}
			br.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		for (int i = 0; i < t.size() + 1; i++) {
			try {
				URL url = new URL(t.get(i));
				InputStream in = url.openStream();
				BufferedInputStream bi = new BufferedInputStream(in);
				FileOutputStream fo = new FileOutputStream(t1.get(i) + "jpg");

				byte buff[] = new byte[1024];
				int imgData = 0;
				int cnt = 0;

				URLConnection conn = url.openConnection();
				int size = conn.getContentLength();
//				System.out.println("image size :" + size);
				while ((imgData = bi.read(buff)) != -1) {
					fo.write(buff, 0, imgData);
					cnt += imgData;

					if (((cnt * 100) / size) == 100) {
						System.out.println(t.get(i) + "<성공>");
					}
					fo.flush();

				}

				in.close();
				bi.close();
				fo.close();
			}

			catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
