package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    private UserDao(){}
    private static final UserDao instance = new UserDao();
    public static UserDao getInstance(){return instance;}


    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // Dao 회원가입
    public boolean register(String uid, String upw, String unickname){
       try {
           Connection conn = DBDao.getConnection();
        String sql = "insert into user(uid, upw, unickname) values(?, ?, ?)";
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, uid);
           ps.setString(2, upw);
           ps.setString(3, unickname);

           int count =ps.executeUpdate();
           if(count == 1){return true;}
           else {return false;}
    }catch (Exception e){
           System.out.println("[시스템] SQL "+ e);
       }return false;
}
    // Dao 로그인
    public boolean login(String uid, String upw){
        try{
            Thread.sleep(1000);
            Connection conn = DBDao.getConnection();
            String sql = "select uid,upw,unickname from user where uid = ? and upw = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setString(2, upw);

            int count = ps.executeUpdate();
            if(count == 1){return true;}
            else {return false;}
        }catch (Exception e){
            System.out.println("[시스템] SQL"+e);
        }return false;
    }
}



