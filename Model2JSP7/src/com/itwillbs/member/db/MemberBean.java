package com.itwillbs.member.db;

import java.sql.Timestamp;

public class MemberBean {
	
	// 자바빈 -> 회원정보를 저장해서 이동시키기 위한 객체 
		// DTO (데이터 전송 객체) 
		// DB의 테이블의 컬럼을 자바빈의 멤버변수로 생성 
		
		
		private String id;
		private String pw;
		private String name;
		private int age;
		private String gender;
		private String email;
		private Timestamp reg_date;
		
		// set / get
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPw() {
			return pw;
		}
		public void setPw(String pw) {
			this.pw = pw;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Timestamp getReg_date() {
			return reg_date;
		}
		public void setReg_date(Timestamp reg_date) {
			this.reg_date = reg_date;
		}
		
		// toString
		
		@Override
		public String toString() {
			return "MemberBean [id=" + id + ", pw=" + pw + ", name=" + name + ", age=" + age + ", gender=" + gender
					+ ", email=" + email + ", reg_date=" + reg_date + "]";
		}
	
	

}
