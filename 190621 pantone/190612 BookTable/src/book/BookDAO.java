package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class BookDAO {

	private static BookDAO daoInstance;

	private BookDAO() {
	};

	public static BookDAO getInstance() {
		if (daoInstance == null) {
			daoInstance = new BookDAO();
		}
		return daoInstance;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "1111");
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Connection Fail");
		}
		return conn;
	}

	public void insert(BookDTO bookDTO) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {

			// 쿼리작성 여기부터
			ppst = conn.prepareStatement("insert into book_rent values(isbn.nextVal, ?, ?, sysdate, ?)");

			ppst.setString(1, bookDTO.getName());
			ppst.setString(2, bookDTO.getAuthor());
			ppst.setString(3, bookDTO.getRent_id());

			// 여기부터 쿼리수행
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

	public void mod(BookDTO bookDTO) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {

			// 쿼리작성 여기부터
			ppst = conn.prepareStatement("update book_rent set name=?, author=?,rent_id=? where ISBN =?");

			ppst.setString(1, bookDTO.getName());
			ppst.setString(2, bookDTO.getAuthor());
			ppst.setString(3, bookDTO.getRent_id());
			ppst.setInt(4, bookDTO.getIsbn());

			// 여기부터 쿼리수행
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

	public void delete(BookDTO bookDTO) {
		Connection conn = getConnection();
		PreparedStatement ppst = null;

		try {

			// 쿼리작성 여기부터
			ppst = conn.prepareStatement("delete book_rent where ISBN =?");

			ppst.setInt(1, bookDTO.getIsbn());

			// 여기부터 쿼리수행
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
	public List<BookDTO> getlist(){// list<객체>로 입력받겠다
		Connection conn= getConnection();
		PreparedStatement ppst  = null;
		ResultSet rs = null;
		List<BookDTO> bookDTOList= null;
		
		try {
			ppst = conn.prepareStatement("select*from book_rent");
			rs= ppst.executeQuery();
			
			if (rs.next()) {
				bookDTOList=new ArrayList<BookDTO>();
			do {
				BookDTO dto = new BookDTO();
				dto.setName(rs.getString("name")); //컬럼명
				dto.setAuthor(rs.getString("author")); //컬럼명
				dto.setRent_id(rs.getString("rent_id")); //컬럼명
				
				
				bookDTOList.add(dto);
			
			
				System.out.println(dto.getName());
				System.out.println(dto.getAuthor());
				System.out.println(dto.getRent_id()+"\n");
			} while(rs.next());
			
			}
		}catch(Exception e){
			
			System.out.println("SQL Error");
	}finally	{
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

	return bookDTOList;
	}

	
		}
