package model.dao;

import model.dto.Item;
import model.dto.SaveFileDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDao {
    private ItemDao(){}
    private static final ItemDao instance = new ItemDao();
    public static ItemDao getInstance(){ return instance; }
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    // 부적 리스트 가져오는 함수
    public ArrayList<Item> getAmulet(){
        ArrayList<Item> items = new ArrayList<>();
        try{
            conn = DBDao.getConnection();
            String sql = "select * from item_info where type = '부적'";
            ps = conn.prepareStatement( sql );

            rs = ps.executeQuery();
            while( rs.next() ){
                int item_no1 = rs.getInt("item_no"); String name = rs.getString("name");
                String type = rs.getString("type"); int price = rs.getInt("price");
                String description = rs.getString("description"); String effect_code = rs.getString("effect_code");
                Item item = new Item(item_no1, name, type, price, description, effect_code);
                items.add( item );
            }

            if( items != null ){ return items; }
            else{ return null; }
        }catch (SQLException e){
            System.out.println("[시스템오류] SQL 문법 문제 발생");
        }finally { // DB자원 반납
            try{ if( rs != null) rs.close(); } catch (Exception e){}
            try{ if( ps != null) ps.close(); } catch (Exception e){}
            try{ if( conn != null) conn.close(); } catch (Exception e){}
        }
        return null;
    }

    //점괘 리스트 가져오는 함수
    public ArrayList<Item> getFortune(){
        ArrayList<Item> items = new ArrayList<>();
        try{
            conn = DBDao.getConnection();
            String sql = "select * from item_info where type = '점괘'";
            ps = conn.prepareStatement( sql );

            rs = ps.executeQuery();
            while( rs.next() ){
                int item_no1 = rs.getInt("item_no"); String name = rs.getString("name");
                String type = rs.getString("type"); int price = rs.getInt("price");
                String description = rs.getString("description"); String effect_code = rs.getString("effect_code");
                Item item = new Item(item_no1, name, type, price, description, effect_code);
                items.add( item );
            }

            if( items != null ){ return items; }
            else{ return null; }
        }catch (SQLException e){
            System.out.println("[시스템오류] SQL 문법 문제 발생");
        }finally { // DB자원 반납
            try{ if( rs != null) rs.close(); } catch (Exception e){}
            try{ if( ps != null) ps.close(); } catch (Exception e){}
            try{ if( conn != null) conn.close(); } catch (Exception e){}
        }
        return null;
    }
    public Item getItem(int item_no){
        Item item = null;
        try {
            conn = DBDao.getConnection();
            String sql = "select * from item_info where item_no = ?";
            ps = conn.prepareStatement( sql );
            ps.setInt(1, item_no);

            rs = ps.executeQuery();
            if(rs.next()){
                int item_no1 = rs.getInt("item_no"); String name = rs.getString("name");
                String type = rs.getString("type"); int price = rs.getInt("price");
                String description = rs.getString("description"); String effect_code = rs.getString("effect_code");
                item = new Item(item_no1, name, type, price, description, effect_code);
            }

            if( item != null){ return item; }
            else{ return null; }
        }catch (SQLException e){
            System.out.println("[시스템오류] SQL 문법 문제 발생");
        }finally { // DB자원 반납
            try{ if( rs != null) rs.close(); } catch (Exception e){}
            try{ if( ps != null) ps.close(); } catch (Exception e){}
            try{ if( conn != null) conn.close(); } catch (Exception e){}
        }
        return null;
    }

}
