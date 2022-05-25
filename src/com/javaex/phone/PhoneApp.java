package com.javaex.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneApp {

	public static void main(String[] args) {
		
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> phonelist = new ArrayList<PersonVo>();
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("**************************************");
		System.out.println("*       전화번호 관리 프로그램       *");
		System.out.println("**************************************");
		System.out.println("");
		
		while(true) {
			System.out.println("1.리스트 2.등록 3.수정 4.삭제 5.검색 6.종료");
			System.out.println("--------------------------------------");
			System.out.print(">메뉴번호: ");
			int menuNo = sc.nextInt();
			
			switch(menuNo) {
				case 1: //리스트 
					System.out.println("<1.리스트>");
					List<PersonVo> dbList = phoneDao.phoneSelect();
					for(int i=0; i<dbList.size(); i++) {
						int personId = dbList.get(i).getPersonId();
						String name = dbList.get(i).getName();
						String hp = dbList.get(i).getHp();
						String company = dbList.get(i).getCompany();
						
						System.out.println(personId + ".\t" + name + "\t" + hp + "\t" + company);
					}
					System.out.println("");
					break;
					
				case 2: //등록
					System.out.println("<2.등록>");
					System.out.print("이름 > ");
					sc.nextLine();
					String insertName = sc.nextLine();
					System.out.print("휴대전화 > ");
					String insertHp = sc.nextLine();
					System.out.print("회사번호 > ");
					String insertCompany = sc.nextLine();
					
					PersonVo dbInsert = new PersonVo(insertName, insertHp, insertCompany);
					phoneDao.phoneInsert(dbInsert);
					break;
					
				case 3: //수정
					System.out.println("<3.수정>");
					System.out.print("번호 > ");
					int updateNum = sc.nextInt();
					System.out.print("이름 > ");
					sc.nextLine();
					String updateName = sc.nextLine();
					System.out.print("휴대전화 > ");
					String updateHp = sc.nextLine();
					System.out.print("회사번호 > ");
					String updateCompany = sc.nextLine();
					
					PersonVo dbUpdate = new PersonVo(updateNum, updateName, updateHp, updateCompany);
					phoneDao.phoneUpdate(dbUpdate);
					break;
				case 4: //삭제
					System.out.println("<4.삭제>");
					System.out.print(">번호 : ");
					int deleteNum = sc.nextInt();
					
					PersonVo dbDelete = new PersonVo(deleteNum);
					phoneDao.phoneDelete(dbDelete);
					break;
					
				case 5: //검색
					System.out.println("<5.검색>");
					System.out.print("검색어 > ");
					sc.nextLine();
					String searchKey = sc.nextLine();
					
					phoneDao.phoneSearch(searchKey);
					break;
					
				case 6: //종료
					System.out.println("**************************************");
					System.out.println("*             감사합니다             *");
					System.out.println("**************************************");
					System.out.println("");
					break;
				default : 
					System.out.println("[다시 입력해주세요.]");
					break;
			}
			if(menuNo==6) {break;}
		}
	}
}