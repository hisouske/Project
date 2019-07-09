package SampleCode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class URLDAO {

	private static URLDAO urlDAO = null;

	private URLDAO() {

	}

	public static URLDAO getInstance() {
		if (urlDAO == null) {
			urlDAO = new URLDAO();
		}
		return urlDAO;
	}

	public Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1111");

		} catch (Exception e) {
			// e.getStackTrace();
			System.out.println("Connection Faile");
			System.out.println(e);
		}

		return conn;
	}

	public void insert(URLDTO inOne) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			// ���� �ۼ��ܰ�
			ppst = conn.prepareStatement("insert into url values(?)");

			ppst.setString(1, inOne.getUrladdr());

			// ���� ����ܰ�
			ppst.executeUpdate();

		} catch (Exception e) {
			System.out.println("SQL Error");
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}

	}
}
