package Booking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class bookDAO {
	private static bookDAO md = null;

	public bookDAO() {
	}

	public static bookDAO getInstance() {
		if (md == null) {
			md = new bookDAO();
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

	public void insert(bookDTO inOne) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			ppst = conn.prepareStatement("insert into booking values(b_sequence.nextval, ?, ?, ?, ?)");
			ppst.setInt(1, inOne.getHnum());
			ppst.setString(2, inOne.getCid());
			ppst.setString(3, inOne.getCcid());
			ppst.setString(4, inOne.getDay());

			ppst.executeQuery();

		} catch (Exception e) {
			System.out.println("SQL Error2");

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

	public String select(int ser1, String ser2) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		try {

			ppst = conn.prepareStatement("select * from booking where hnum=? and day=?");
			ppst.setInt(1, ser1);
			ppst.setString(2, ser2);
			rs = ppst.executeQuery();

			String confirm = "예약있음";
			String confirm2 = "예약없음";

			if (rs.next()) {
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
		return null;

	}

	public ArrayList<bookDTO> select(String id) {
		{
			Connection conn = getConnection();
			PreparedStatement ppst = null;
			ResultSet rs = null;
			ArrayList<bookDTO> blist = new ArrayList<>();
			try {

				ppst = conn.prepareStatement("select * from booking where cid=?");
				ppst.setString(1, id);

				rs = ppst.executeQuery();

				if (rs.next()) {
					blist = new ArrayList<bookDTO>();

					do {
						bookDTO dto = new bookDTO();

						dto.setNum(rs.getInt("num"));
						dto.setHnum(rs.getInt("hnum"));
						dto.setDay(rs.getString("day"));

						blist.add(dto);
//					for (int i = 0; i < memberDTOList.size(); i++) {
//						System.out.println(memberDTOList.get(i).getMemberId() + "/" + memberDTOList.get(i).getMemberName());
//				}
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
			return blist;

		}
	}

	public ArrayList<bookDTO> selectcc(String id) {
		{
			Connection conn = getConnection();
			PreparedStatement ppst = null;
			ResultSet rs = null;
			ArrayList<bookDTO> blist = new ArrayList<>();
			try {

				ppst = conn.prepareStatement("select * from booking where ccid=?");
				ppst.setString(1, id);

				rs = ppst.executeQuery();

				if (rs.next()) {
					blist = new ArrayList<bookDTO>();

					do {
						bookDTO dto = new bookDTO();
						dto.setCcid("ccid");
						dto.setNum(rs.getInt("num"));
						dto.setHnum(rs.getInt("hnum"));
						dto.setDay(rs.getString("day"));

						blist.add(dto);
//					for (int i = 0; i < memberDTOList.size(); i++) {
//						System.out.println(memberDTOList.get(i).getMemberId() + "/" + memberDTOList.get(i).getMemberName());
//				}
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
			return blist;

		}
	}

}
