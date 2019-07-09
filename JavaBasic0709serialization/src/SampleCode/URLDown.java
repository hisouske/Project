package SampleCode;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLDown {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		String t = "http://www.human.or.kr/theme/basic/img/human/qm_counsel.png";

		try {
			URL url = new URL(t);
			InputStream in = url.openStream();
			BufferedInputStream bi = new BufferedInputStream(in);

			FileOutputStream fo = new FileOutputStream("my.jpg");

			byte buff[] = new byte[1024];
			int imgData = 0;
			int cnt = 0;

			URLConnection conn = url.openConnection();
			int size = conn.getContentLength();
			System.out.println("image size :" + size);

			while ((imgData = bi.read(buff)) != -1) {
				fo.write(buff, 0, imgData);
				cnt += imgData;
				System.out.println(((cnt * 100) / size) + "%");
				fo.flush();

			}

			in.close();
			bi.close();
			fo.close();
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}

}
