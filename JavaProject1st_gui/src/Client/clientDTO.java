package Client;

import java.net.Socket;

public class clientDTO {
	String id = null;
	String pw = null;
	String name = null;
	int point = 0; // 평점
	String sort = "x"; // 0이면 guest 1이면 master
	Socket socket = null;

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String msg3) {
		this.sort = msg3;
	}
}
