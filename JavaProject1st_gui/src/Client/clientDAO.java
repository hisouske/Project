package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class clientDAO {

	private static clientDAO md = null;

	public clientDAO() {
	}

	public static clientDAO getInstance() {
		if (md == null) {
			md = new clientDAO();
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

	public void insert(clientDTO inOne) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {

			ppst = conn.prepareStatement("insert into client values(?, ?, ?, ?, ?)");
			ppst.setString(1, inOne.getId());
			ppst.setString(2, inOne.getPw());
			ppst.setString(3, inOne.getName());
			ppst.setInt(4, inOne.getPoint());
			ppst.setString(5, inOne.getSort());

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

	public ArrayList<clientDTO> getList() {

		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<clientDTO> clientDTOList = null;

		try {
			ppst = conn.prepareStatement("select * from client");
			rs = ppst.executeQuery();

			if (rs.next()) {
				clientDTOList = new ArrayList<clientDTO>();
				do {
					clientDTO dto = new clientDTO();
					dto.setId(rs.getString("id"));
					dto.setPw(rs.getString("pw"));
					dto.setName(rs.getString("name"));
					dto.setPoint(rs.getInt("point"));
					dto.setSort(rs.getString("sort"));

					clientDTOList.add(dto);
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
		return clientDTOList;
	}

	
	
	public String select(String ser) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;

		try {

			ppst = conn.prepareStatement("select * from client where id=?");
			ppst.setString(1, ser);

			rs = ppst.executeQuery();

			String confirm = "이미 존재하는 id 입니다";
			String confirm2 = "사용 가능한 id 입니다";

			if (rs.next()) {
//				System.out.println(confirm);
				return confirm;
			

			} else {
				return confirm2;
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
		return ser;

	}

	
	
	public String select2(String id, String pw) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;

		try {

			ppst = conn.prepareStatement("select pw from client where id=?");
			ppst.setString(1, id);
			rs = ppst.executeQuery();

			if (rs.next()) {

				if (pw.equals(rs.getString("pw"))) {
					ppst = conn.prepareStatement("select name from client where id=?");
					ppst.setString(1, id);
					rs = ppst.executeQuery();

					if (rs.next()) {
						String confirm = rs.getString("name");
						return confirm;
					}
				}
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
		return null;

	}

}
