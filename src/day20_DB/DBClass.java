package day20_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//db와 연동
public class DBClass {
	//url 객체 생성(오라클 정보,아이디, 비밀번호)
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "kjs";
	private String pwd = "1234";
	public DBClass() {
		try {
		//1.자바에서 오라클에 관련된 기능을 사용 할 수 있게 기능을 등록하는 것
	Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			//try catch쓸때는 무조건 넣어줘야함(에러나타냄)
			e.printStackTrace();
		}
	}
	public ArrayList<StudentDTO> getList() {
		ArrayList<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			//2. 데이터베이스 연결(con 은 DB에 연결된 객체다.)//DB정보,아이디,비밀번호
			Connection con = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결이 잘  이뤄졌습니다 !");
		
		//3. 데이버테이스에 연결된 객체를 이용해서 명령어를 수행할 수 있는 객체를 얻어 온다.
								//* == newst로부터 모든 데이터 가져오기
			String sql = "select * from newst";
			PreparedStatement ps = con.prepareStatement(sql);
			
			//4. 명령어를 수행할 수 있는 객체를 이용해서 명령어 수행
			//5. 수행 결과를 저장한다.
			ResultSet rs = ps.executeQuery();
			// re = //bof//id n a // id n a // id n a // eof//로 구성됨
			// rs.next이용시 한칸씩 앞으로 가며 출력함
			while(rs.next()) {
				//getList는 db관련만 다루기에 빼버림
//				System.out.println("id : "+rs.getString("id"));
//				System.out.println("name : "+rs.getString("name"));
//				System.out.println("age : "+rs.getInt("age"));
//				System.out.println("-----------------------------------");
				StudentDTO dto = new StudentDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		}
								//id가 중복돼서 오류, this로 교체
	public StudentDTO searchST(String id ) {
		//select * from newst where id = '222';
		String sql = "select * from newst where id = '"+id+"'";
				//dto = null 일시 데이터를 가져올 수 없음. 즉 찾고자하는 id 가 없다는 뜻임
			//dto != null일시 찾고자하는 데이터가 있다. 즉 찾는 id 있음
		StudentDTO dto = null;
		try {
			//1. 디비 연결
			Connection con = DriverManager.getConnection(url,this.id,pwd);
			System.out.println("-----연결 확인-----");
			//2. 명령어(쿼리문)전송 객체 생성
			PreparedStatement ps = con.prepareStatement(sql);
			//3. 전송 후 결과값 저장
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				dto = new StudentDTO();
										//변수 아님 ! rs가 db에서 id를 가져오는거 
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public void saveData(String userId, String userName, int userAge) {
		//insert into newst values('aaa','김개똥',33);
											//userId, userName, userAge
		//String sql = "insert into newst values('"+userId+"','"+userName+"','"+33+")";
											//?로 잠시 표시해 놓기(나중에 넣기)
		String sql = "insert into newst values(? , ? , ?)";
		int result = 0;
		try {
			//1. 데이터 베이스 연결 객체 얻어오기
			Connection con = DriverManager.getConnection(url, id, pwd);
		//2. 쿼리문 전송객체 얻어오기
			PreparedStatement ps = con.prepareStatement(sql);
			//? 자리 채우기 () 1,2,3의 숫자는 ?자리 순서임
			ps.setString(1, userId);
			ps.setString(2, userName);
			ps.setInt(3, userAge);
			//4. 쿼리문 전송(실행)
			//select가 들어오는건 exequteQuery로 받아줌 insert는 executeUpdate이용
			//ResultSet rs = ps.executeQuery();
			//select를 제외한 다른 쿼리문은 executeUdate()를 사용
			//execyteUpdate는 int형태의 값을 돌려준다. 성공1 , 실패0 또는 에러
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
	}
}








