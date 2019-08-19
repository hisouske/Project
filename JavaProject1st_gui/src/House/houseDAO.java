package House;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class houseDAO {

	private static houseDAO md = null;

	public houseDAO() {
	}

	public static houseDAO getInstance() {
		if (md == null) {
			md = new houseDAO();
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

	public void insert(houseDTO inOne) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {

			ppst = conn.prepareStatement("insert into house values(house_num.nextval, ?, ?, ?, ?, ?)");
			ppst.setString(1, inOne.getName());
			ppst.setString(2, inOne.getRegion());
			ppst.setString(3, inOne.getId());
			ppst.setInt(4, inOne.getPrice());
			ppst.setInt(5, inOne.getPoint());

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

	public ArrayList<houseDTO> getList() {

		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<houseDTO> clientDTOList = null;

		try {
			ppst = conn.prepareStatement("select * from house");
			rs = ppst.executeQuery();

			if (rs.next()) {
				clientDTOList = new ArrayList<houseDTO>();
				do {
					houseDTO dto = new houseDTO();
					dto.setNum(rs.getInt("num"));
					dto.setName(rs.getString("name"));
					dto.setRegion(rs.getString("region"));
					dto.setId(rs.getString("id"));
					dto.setPrice(rs.getInt("price"));
					dto.setPoint(rs.getInt("point"));

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

	public ArrayList<houseDTO> search(String ser) {

		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<houseDTO> replyDTOList = null;

		try {

			ppst = conn.prepareStatement("select * from house where region=?");

			ppst.setString(1, ser);

			ppst.executeUpdate();
			rs = ppst.executeQuery();

			if (rs.next()) {
				replyDTOList = new ArrayList<houseDTO>();

				do {
					houseDTO dto = new houseDTO();

					dto.setNum(rs.getInt("num"));
					dto.setName(rs.getString("name"));
					dto.setId(rs.getString("id"));
					dto.setPrice(rs.getInt("price"));
					dto.setPoint(rs.getInt("point"));
					replyDTOList.add(dto);

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

		return replyDTOList;

	}

	public String search(int hnum) {

		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		String hname;

		try {
			ppst = conn.prepareStatement("select * from house where num=?");
			ppst.setInt(1, hnum);
			rs = ppst.executeQuery();

			if (rs.next()) {

				do {
					hname = rs.getString("name");
					return hname;
				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("SQL2 Error");
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

	public String search2(int hnum) {

		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		String ccid;

		try {

			ppst = conn.prepareStatement("select * from house where num=?");
			ppst.setInt(1, hnum);
			rs = ppst.executeQuery();

			if (rs.next()) {
				do {
					ccid = rs.getString("id");
					return ccid;
				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("SQL Error3");
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

	public String delete(int i, String id) {

		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			ppst = conn.prepareStatement("delete house where num=? and id=?");
			ppst.setInt(1, i);
			ppst.setString(2, id);
			System.out.println(i + id);
			ppst.executeUpdate();
			return "ok";

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
		return "no";
	}

}
