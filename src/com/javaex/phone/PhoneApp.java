package com.javaex.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneApp {

	public static void main(String[] args) {
		
		PhoneDao phoneDao = new PhoneDao();
		List<PhoneVo> pVolist = new ArrayList<PhoneVo>();
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
					
				
				case 2: //등록
					System.out.println("<2.등록>");
					int counter=0;
					PhoneVo pInsert = new PhoneVo();
					pInsert.setPersonId(counter++);
					
					System.out.print("이름 > ");
					pInsert.setName(sc.nextLine());
					
					System.out.print("휴대전화 > ");
					pInsert.setHp(sc.nextLine());
					
					System.out.print("회사번호 > ");
					pInsert.setCompany(sc.nextLine());
					
					pVolist.add(pInsert);
					break;
				case 3: //수정
					System.out.println("<3.수정>");
					PhoneVo pUpdate = new PhoneVo();
					
					System.out.print("번호 > ");
					pUpdate.setPersonId(sc.nextInt());
					
					System.out.print("이름 > ");
					pUpdate.setName(sc.nextLine());
					
					System.out.print("휴대전화 > ");
					pUpdate.setHp(sc.nextLine());
					
					System.out.print("회사번호 > ");
					pUpdate.setCompany(sc.nextLine());
					
					pVolist.add(pUpdate);
					break;
				case 4: //삭제
					System.out.println("<4.삭제>");
					System.out.print("번호 > ");
					
					
				case 5: //검색
				case 6: //종료
				
				//default
			}
		}
		sc.close();

	}

}
