package House;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class picDAO {

	private static picDAO md = null;

	public picDAO() {
	}

	public static picDAO getInstance() {
		if (md == null) {
			md = new picDAO();
		}
		return md;
	}

	public Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1111");

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Connection Faile");
		}

		return conn;
	}

	public ArrayList<picDTO> getList() {

		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<picDTO> picList = null;

		try {
			ppst = conn.prepareStatement("select * from pic");
			rs = ppst.executeQuery();

			if (rs.next()) {
				picList = new ArrayList<picDTO>();
				do {
					picDTO dto = new picDTO();
					dto.setPicname(rs.getString("picname"));
					picList.add(dto);
				} while (rs.next());
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("SQL Error");
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}
		return picList;
	}

	public void insert(String picname, String id) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {

			ppst = conn.prepareStatement("insert into pic values (?, ?)");
			ppst.setString(1, 2+picname);
			ppst.setString(2, id);
			System.out.println(picname);
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

	public ArrayList<String> search(String ser) {

		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<String> myhouse = null;

		try {

			ppst = conn.prepareStatement("select * from pic where region=?");

			ppst.setString(1, ser);

			ppst.executeUpdate();
			rs = ppst.executeQuery();

			if (rs.next()) {
				myhouse = new ArrayList<String>();

				do {

					myhouse.add(rs.getString("picname"));

				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("SQL Error");
		} finally {
			try {
				if (ppst != null)
					ppst.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				System.out.println("connection close error");
			}
		}

		return myhouse;

	}

}
