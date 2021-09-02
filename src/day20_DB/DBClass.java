package day20_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
	public void getList() {
		//2. 데이터베이스 연결(con 은 DB에 연결된 객체다.)
		try {
														//DB정보,아이디,비밀번호
			Connection con = DriverManager.getConnection(url, id, pwd);
			System.out.println("연결이 잘 이뤄졌습니다 !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//3. 데이버테이스에 연결된 객체를 이용해서 명령어를 수행할 수 있는 객체를 얻어 온다.
		//4. 명령어를 수행할 수 있는 객체를 이용해서 명령어 수행
		//5. 수행 결과를 저장한다.
	}
}