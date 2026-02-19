package model.dao;

import model.dto.GameLogDto;
import model.dto.SaveFileDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameLogDao {
    private GameLogDao(){}
    private static final GameLogDao instance = new GameLogDao();
    public static GameLogDao getInstance(){
        return instance;
    }
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    //로그 저장 함수
    public boolean AddGameLog(int user_no, int final_score, int final_round, int final_money){
        try{
            conn = DBDao.getConnection(); //DBDao에서 연결 설정 가져오기.
            String sql = "insert into game_log ( user_no, final_score, final_round, final_money ) values( ?, ?, ?, ?)";
            ps = conn.prepareStatement( sql );
            ps.setInt(1, user_no); ps.setInt(2, final_score);
            ps.setInt(3, final_round); ps.setInt(4, final_money);

            int count = ps.executeUpdate();
            if( count == 1){ return true; }
            else{ return false; }
        }catch (SQLException e){
            System.out.println("[시스템오류] SQL 문법 문제 발생");
        }finally { // DB자원 반납
            try{ if( rs != null) rs.close(); } catch (Exception e){}
            try{ if( ps != null) ps.close(); } catch (Exception e){}
            try{ if( conn != null) conn.close(); } catch (Exception e){}
        }
        return false;
    }

    //랭킹 출력 함수
    public ArrayList<GameLogDto> printRanking(){
        ArrayList<GameLogDto> ranks = new ArrayList<>();
        try{
            conn = DBDao.getConnection(); //DBDao에서 연결 설정 가져오기.
            //닉네임,최고점수,달성일를 가져온 뒤 최고점수 기준으로 정렬 후 상위 5개만 가져옴
            String sql = "select log_no, nickname, play_date, final_score from game_log join user on user.user_no = game_log.user_no order by final_score desc limit 5";
            ps = conn.prepareStatement( sql );

            rs = ps.executeQuery();
            while( rs.next() ){
                int log_no = rs.getInt("log_no");   String nickname = rs.getString("nickname");
                int final_score = rs.getInt("final_score");   String play_date = rs.getString("play_date");
                GameLogDto rank = new GameLogDto(log_no, nickname, final_score, play_date);

                ranks.add( rank );
            }
            if( ranks != null ){ return ranks; }
            else{ return null; }
        }catch (SQLException e){
            System.out.println("[시스템오류] SQL 문법 문제 발생");
        }finally { // DB자원 반납
            try{ if( rs != null) rs.close(); } catch (Exception e){}
            try{ if( ps != null) ps.close(); } catch (Exception e){}
            try{ if( conn != null) conn.close(); } catch (Exception e){}
        }
        return ranks;
    }

    //내 로그 확인 함수
    public ArrayList<GameLogDto> printMyLog(int user_no){ //login에 해당 사용자의 user_no를 받아온다
        ArrayList<GameLogDto> myLogs = new ArrayList<>();
        try{
            conn = DBDao.getConnection(); //DBDao에서 연결 설정 가져오기.
            //닉네임,최고점수,달성일를 가져온 뒤 날짜 기준으로 정렬 후 상위 10개만 가져옴
            String sql = "select log_no, nickname, final_score, final_round, final_money, play_date from game_log join user on user.user_no = game_log.user_no where game_log.user_no = ? order by play_date limit 10";
            ps = conn.prepareStatement( sql );
            ps.setInt(1, user_no);

            rs = ps.executeQuery();
            while( rs.next() ){
                int log_no = rs.getInt("log_no");   int final_score = rs.getInt("final_score");
                int final_round = rs.getInt("final_round"); int final_money = rs.getInt("final_money");
                String play_date = rs.getString("play_date"); String nickname = rs.getString("nickname");
                GameLogDto myLog = new GameLogDto(log_no, nickname, final_score, final_round, final_money, play_date);

                myLogs.add( myLog );
            }
            if( myLogs != null ){ return myLogs; }
            else{ return null; }
        }catch (SQLException e){
            System.out.println("[시스템오류] SQL 문법 문제 발생");
        }finally { // DB자원 반납
            try{ if( rs != null) rs.close(); } catch (Exception e){}
            try{ if( ps != null) ps.close(); } catch (Exception e){}
            try{ if( conn != null) conn.close(); } catch (Exception e){}
        }
        return myLogs;
    }

    //내 최고기록 반환 함수
    public int myBestScore(int user_no){
        try{
            conn = DBDao.getConnection(); //DBDao에서 연결 설정 가져오기.
            //닉네임,최고점수,달성일를 가져온 뒤 최고점수 기준으로 정렬 후 상위 5개만 가져옴
            String sql = "select MAX(final_score) as final_score from game_log where user_no = ?";
            ps = conn.prepareStatement( sql );
            ps.setInt(1, user_no);

            rs = ps.executeQuery();
            rs.next();
            int final_score = rs.getInt("final_score");
            return final_score;
        }catch (SQLException e){
            System.out.println("[DB 에러 원인]" + e.getMessage());
        }
        return 0;
    }


}
