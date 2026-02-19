package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dto.UserDto;
public class UserDao {

    private UserDao(){}
    private static final UserDao instance = new UserDao();
    public static UserDao getInstance(){return instance;}


    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // Dao 회원가입
    public boolean register(String uid, String upwd, String nickname){
       try {
           Connection conn = DBDao.getConnection();
        String sql = "insert into user(uid, upwd, nickname) values(?, ?, ?)";
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setString(1, uid);
           ps.setString(2, upwd);
           ps.setString(3, nickname);

           int count =ps.executeUpdate();
           if(count == 1){ return true; }
           else {return false;}
    }catch (Exception e){
           System.out.println("[시스템] SQL "+ e);
       }return false;
}
    // Dao 로그인
    public int login(String uid, String upwd){
        try{
            Connection conn = DBDao.getConnection();
            String sql = "select user_no from user where uid = ? and upwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setString(2, upwd);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {

               return rs.getInt("user_no");
            }

        }catch (Exception e){
            System.out.println("[시스템] SQL"+e);
        }return -1;
    }

    //닉네임 불러오기
    public String printNickName(int user_no){
        String nickname = "";
        try {
            conn = DBDao.getConnection();
            String sql = "select nickname from user where user_no = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, user_no);

            rs = ps.executeQuery();
            if( rs.next() ){
                nickname = rs.getString("nickname");
                if(nickname != null){
                    return nickname;
                }else{
                    return "";
                }
            }
        }catch (Exception e){
            System.out.println("[시스템] SQL"+e);
        }finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return nickname;
    }


}



