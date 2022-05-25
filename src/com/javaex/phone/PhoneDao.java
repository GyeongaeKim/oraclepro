package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	public List<PersonVo> phoneSelect() {
		List<PersonVo> phoneList = new ArrayList<PersonVo>();
		getConnection();
		
		try {
			String query = "";
			query += " select person_id, ";
			query += "     	  name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			query += " order by person_id asc ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				PersonVo phoneVo = new PersonVo(personId, name, hp, company);
				phoneList.add(phoneVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return phoneList;
	}

	// 2. 등록 메소드
	public int phoneInsert(PersonVo personVo) {
		int count = -1;
		getConnection();
		try {
			String query = "";
			query += " insert into person ";
			query += " values(seq_person_id.nextval, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 등록되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// 3. 수정 메소드
	public int phoneUpdate(PersonVo personVo) {
		int count = -1;
		getConnection();
		try {
			String query = "";
			query += " update person ";
			query += " set name = ? ";
			query += "    ,hp = ? ";
			query += "    ,company = ? ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, personVo.getName());
			pstmt.setString(2, personVo.getHp());
			pstmt.setString(3, personVo.getCompany());
			pstmt.setInt(4, personVo.getPersonId());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 수정되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}

	// 4. 삭제 메소드
	public int phoneDelete(PersonVo personVo) {
		int count = -1;
		getConnection();
		try {
			String query = "";
			query += " delete from person ";
			query += " where person_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personVo.getPersonId());

			count = pstmt.executeUpdate();
			System.out.println("["+ count + "건 삭제되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		close();
		return count;
	}

	// 5. 검색 메소드
	public List<PersonVo> phoneSearch(String searchKey) {
		List<PersonVo> phoneSearchList = new ArrayList<PersonVo>();
		Scanner sc = new Scanner(System.in);
		getConnection();
		try {
			String query = "";
			query += " select person_id, ";
			query += "     	  name, ";
			query += "        hp, ";
			query += "        company ";
			query += " from person ";
			query += " order by person_id asc ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			//결과처리
			while(rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");
				
				PersonVo personVo = new PersonVo(personId, name, hp, company);
				phoneSearchList.add(personVo);
			}
			
			for(int i=0; i<phoneSearchList.size(); i++) {
				if(phoneSearchList.get(i).getName().contains(searchKey)
						||phoneSearchList.get(i).getHp().contains(searchKey)
						||phoneSearchList.get(i).getCompany().contains(searchKey)==true) {
					System.out.println(phoneSearchList.get(i).getPersonId()
							+", "+ phoneSearchList.get(i).getName()
							+", "+ phoneSearchList.get(i).getHp()
							+", "+ phoneSearchList.get(i).getCompany());
				}
			}
			System.out.println("");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return phoneSearchList;
	}
}

