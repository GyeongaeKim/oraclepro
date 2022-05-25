package com.javaex.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneApp {

	public static void main(String[] args) {
		
		PhoneDao phoneDao = new PhoneDao();
		List<PhoneVo> phonelist = new ArrayList<PhoneVo>();
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
			
			if(menuNo==5) {break;}
			
			switch(menuNo) {
				case 1: //리스트 
					System.out.println("<1.리스트>");
					List<PhoneVo> dbList = phoneDao.phoneSelect();
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
					PhoneVo phoneVo = new PhoneVo ();
					
					System.out.print("이름 > ");
					phoneVo.setName(sc.nextLine());
					System.out.print("휴대전화 > ");
					phoneVo.setHp(sc.nextLine());
					System.out.print("회사번호 > ");
					phoneVo.setCompany(sc.nextLine());
					
					phoneDao.phoneInsert(phoneVo);
					System.out.println("[등록되었습니다.]");
					System.out.println();
					break;
					
				case 3: //수정
					System.out.println("<3.수정>");
					PhoneVo phoneUpdate = new PhoneVo();
					
					System.out.print("번호 > ");
					phoneUpdate.setPersonId(sc.nextInt());
					System.out.print("이름 > ");
					phoneUpdate.setName(sc.nextLine());
					System.out.print("휴대전화 > ");
					phoneUpdate.setHp(sc.nextLine());
					System.out.print("회사번호 > ");
					phoneUpdate.setCompany(sc.nextLine());
					
					phonelist.add(phoneUpdate);
					System.out.println("[수정되었습니다.]");
					System.out.println("");
					break;
				case 4: //삭제
					System.out.println("<4.삭제>");
					PhoneVo phoneDelete = new PhoneVo();
					
					System.out.print("번호 > ");
					phoneDelete.setPersonId(sc.nextInt());
					phoneDao.phoneDelete(phoneDelete);
					System.out.println("[삭제되었습니다.]");
					System.out.println("");
					break;
					
				case 5: //검색
					System.out.println("<5.검색>");
					
					System.out.println("검색어 > ");
					String search = sc.nextLine();
					
					List<PhoneVo> phoneSearch = phoneDao.phoneSearch(search);
					
					for(int i=0; i<phoneSearch.size(); i++) {
						int personId = phoneSearch.get(i).getPersonId();
						String sName = phoneSearch.get(i).getName();
						String sHp = phoneSearch.get(i).getHp();
						String sCompany = phoneSearch.get(i).getCompany();
						
						System.out.println(personId + ".\t" + sName + "\t" + sHp + "\t" + sCompany);
					}
					
					
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
		}
		sc.close();

	}

}
