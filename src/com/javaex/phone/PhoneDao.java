package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// 생성자
	// 메소드-gs

	// 메소드-일반

	// --DB연결 메소드
	public void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리 메소드
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	// 1. 리스트 메소드
	public List<PhoneVo> phoneSelect() {
		List<PhoneVo> phoneList = new ArrayList<PhoneVo>();
		getConnection();
		try {
			//SQL문 준비
			String query = "";
			query += " select person_id, ";
			query += "        name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			System.out.println(query);
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			//ResultSet 가져오기
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			//반목문으로 Vo만들기 / list에 추가하기
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String Name = rs.getString("name");
				String Hp = rs.getString("hp");
				String Company = rs.getString("company");
				
				PhoneVo phoneVo = new PhoneVo(personId, Name, Hp, Company);
				phoneList.add(phoneVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return phoneList;
	}
	
	// 2. 등록 메소드
	public int phoneInsert(PhoneVo phoneVo) {
		int count = -1;
		getConnection();
		try {
			// SQL문 준비
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phoneVo.getName());
			pstmt.setString(2, phoneVo.getHp());
			pstmt.setString(3, phoneVo.getCompany());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	
	// 3. 수정 메소드
	public int personUpdate(PhoneVo phoneVo) {
		int count = -1;
		getConnection();
		try {
			// SQL문
			String query = "";
			query += " update person ";
			query += " set name = ?, ";
			query += " set hp = ?, ";
			query += "     company = ? ";
			query += " where person_id = ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phoneVo.getName());
			pstmt.setString(2, phoneVo.getHp());
			pstmt.setString(3, phoneVo.getCompany());
			pstmt.setInt(4, phoneVo.getPersonId());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 수정되었습니다.");

		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}
	
	
	// 4. 삭제 메소드
	public int personDelete(PhoneVo phoneVo) {
		int count = -1;
		getConnection();
		try {
			// SQL문 준비
			String query = "";
			query += " delete from person ";
			query += " where person_id= ? ";
			System.out.println(query);

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, phoneVo.getPersonId());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return count;
	}
	
	
}
