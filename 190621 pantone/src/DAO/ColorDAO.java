package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.ColorDTO;

public class ColorDAO {
	private static ColorDAO md = null;

	private ColorDAO() {

	}

	public static ColorDAO getInstance() {
		if (md == null) {
			md = new ColorDAO();
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

	public ArrayList<ColorDTO> selectCol(String col) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<ColorDTO> ColorDTOList = null;
		try {
			// 쿼리 작성단계
			ppst = conn.prepareStatement("select * from pantone where coltype=?");
			ppst.setString(1, col);
			// 쿼리 수행단계
			rs = ppst.executeQuery();

			// 쿼리의 결과가 있는가?
			if (rs.next()) {
				ColorDTOList = new ArrayList<ColorDTO>();

				do {
					ColorDTO dto = new ColorDTO();

					dto.setColname(rs.getString("colname"));
					dto.setColtype(rs.getString("coltype"));
					dto.setR(rs.getString("r"));
					dto.setG(rs.getString("g"));
					dto.setB(rs.getString("b"));
					dto.setC(rs.getString("c"));
					dto.setM(rs.getString("m"));
					dto.setY(rs.getString("y"));
					dto.setK(rs.getString("k"));
					ColorDTOList.add(dto);
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

				return ColorDTOList;
	}

	public void insert(ColorDTO inOne) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {
			// 쿼리 작성단계
			ppst = conn.prepareStatement("insert into pantone values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ppst.setString(1, inOne.getColname());
			ppst.setString(2, inOne.getColtype());
			ppst.setString(3, inOne.getR());
			ppst.setString(4, inOne.getG());
			ppst.setString(5, inOne.getB());
			ppst.setString(6, inOne.getC());
			ppst.setString(7, inOne.getM());
			ppst.setString(8, inOne.getY());
			ppst.setString(9, inOne.getK());
			// 쿼리 수행단계
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
	
	public ArrayList<ColorDTO> getList() {

		// 드라이버로드 + connection 만드는 단계
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		ResultSet rs = null;
		ArrayList<ColorDTO> ColorDTOList = null;

		try {
			// 쿼리 작성단계
			ppst = conn.prepareStatement("select * from pantone");
			// 쿼리 수행단계
			rs = ppst.executeQuery();

			// 쿼리의 결과가 있는가?
			if (rs.next()) {
				ColorDTOList = new ArrayList<ColorDTO>();

				do {
					ColorDTO dto = new ColorDTO();

					dto.setColname(rs.getString("colname"));
					dto.setColtype(rs.getString("coltype"));
					dto.setR(rs.getString("r"));
					dto.setG(rs.getString("g"));
					dto.setB(rs.getString("b"));
					dto.setC(rs.getString("c"));
					dto.setM(rs.getString("m"));
					dto.setY(rs.getString("y"));
					dto.setK(rs.getString("k"));
					ColorDTOList.add(dto);
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

		return ColorDTOList;
	}

//	public void updata(ColorDTO inOne) {
//
//		Connection conn = getConnection();
//		PreparedStatement ppst = null;
//
//		try {
//
//			ppst = conn.prepareStatement("update word set kor=? where eng=?");
//
//			ppst.setString(1, inOne.getColtype());
//			ppst.setString(2, inOne.getColname());
//			// 쿼리 수행단계
//			ppst.executeUpdate();
//		} catch (Exception e) {
//
//		} finally {
//			try {
//				if (ppst != null)
//					ppst.close();
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("connection close error");
//			}
//		}
//
//	}

	public void del(String delcol) {
		
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		try {
			System.out.println("실행");
			ppst = conn.prepareStatement("delete from pantone where coltype=?");
			System.out.println("실행");
			ppst.setString(1, delcol);

			System.out.println("실행");
			int k = ppst.executeUpdate();
			
		} catch (Exception e) {

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
