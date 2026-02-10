package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBDao {
    public static Connection getConnection() {
        Connection conn = null;
        try{
            String url = "jdbc:mysql://localhost:3306/hwaturo";
            String user = "root"; String password = "1234";
            Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 라이브러리 객체 메모리할당/불러오기
            conn = DriverManager.getConnection( url, user, password );
            System.out.println("[준비] 데이터베이스 연동 성공");
        }catch (Exception e){
            System.out.println("[경고] 데이터베이스 연동 실패 : 관리자에게 문의");
        }
        return conn;
    }
}

//추후에 DB를 사용할 때 아래와 같이 사용.
//public class CardDao {
//    // 게시물 쓰기 함수
//    public void write() {
//        // 필요할 때 딱 불러와서 씀
//        Connection conn = DBConnectionManager.getConnection();
//        try {
//            // ... SQL 실행 로직 ...
//        } catch(Exception e) {
//        }
//    }
//}