package model.dao;

import model.dto.Card;
import model.dto.GameLogDto;
import model.dto.Item;
import model.dto.SaveFileDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameSave {
    private GameSave(){}
    private static final GameSave instance = new GameSave();
    public static GameSave getInstance(){
        return instance;
    }
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean saveGame(int user_no, int current_round, int current_hp, int current_money, int current_score, String card, String item){
        try{
            conn = DBDao.getConnection(); //DBDao에서 연결 설정 가져오기.
            String sql = ""; //update문 작성
            ps = conn.prepareStatement( sql );
            ps.setInt(1, user_no); ps.setInt(2, final_score);
            ps.setInt(3, final_round); ps.setInt(4, final_money);

            int count = ps.executeUpdate();
            if( count == 1){ return true; }
            else{ return false; }
        }catch (SQLException e){
            System.out.println("[시스템오류] SQL 문법 문제 발생");
        }
        return false;
    }

    public SaveFileDto loadGame(int user_no){
        SaveFileDto game = null;
        try{
            conn = DBDao.getConnection(); //DBDao에서 연결 설정 가져오기.
            //닉네임,최고점수,달성일를 가져온 뒤 최고점수 기준으로 정렬 후 상위 5개만 가져옴
            String sql = "select * from save_file where user_no = ?";
            ps = conn.prepareStatement( sql );
            ps.setInt(1, user_no);

            rs = ps.executeQuery();
            while( rs.next() ){
                int user_no1 = rs.getInt("user_no");  int current_round = rs.getInt("current_round");
                int current_hp = rs.getInt("current_hp"); int current_money = rs.getInt("current_money");
                int current_score = rs.getInt("current_score");
                String card = rs.getString("card"); String item = rs.getString("item");
                game = new SaveFileDto(user_no1, current_round, current_hp, current_money, current_score, card, item);
            }
            if( game != null ){ return game; }
            else{ return null; }
        }catch (SQLException e){
            System.out.println("[시스템오류] SQL 문법 문제 발생");
        }
        return game;
    }
}
