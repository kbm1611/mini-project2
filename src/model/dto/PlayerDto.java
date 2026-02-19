package model.dto;

import java.util.ArrayList;

public class PlayerDto {
    private PlayerDto(){}
    private static final PlayerDto instance = new PlayerDto();
    public static PlayerDto getInstance(){ return instance; }
    private int user_no;
    private String nickname;
    private int current_round;
    private int current_hp; // 남은 내기 기회
    private int current_discard; // 남은 버리기 기회
    private int current_money;
    private int current_score;
    private ArrayList<Card> card;
    private ArrayList<Item> item;


    public int getUser_no() {
        return user_no;
    }
    public String getNickname() {
        return nickname;
    }
    public int getCurrent_round() {
        return current_round;
    }
    public int getCurrent_hp() {
        return current_hp;
    }
    public int getCurrent_discard() { return current_discard; }
    public int getCurrent_money() {
        return current_money;
    }
    public int getCurrent_score() {
        return current_score;
    }
    public ArrayList<Card> getCard() {
        return card;
    }
    public ArrayList<Item> getItem() {
        return item;
    }
    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public void setCurrent_round(int current_round) {
        this.current_round = current_round;
    }
    public void setCurrent_hp(int current_hp) {
        this.current_hp = current_hp;
    }
    public void setCurrent_discard(int current_discard) { this.current_discard = current_discard; }
    public void setCurrent_money(int current_money) {
        this.current_money = current_money;
    }
    public void setCurrent_score(int current_score) {
        this.current_score = current_score;
    }
    public void setCard(ArrayList<Card> card) {
        this.card = card;
    }
    public void setItem(ArrayList<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "user_no=" + user_no +
                ", nickname='" + nickname + '\'' +
                ", current_round=" + current_round +
                ", current_hp=" + current_hp +
                ", current_money=" + current_money +
                ", current_score=" + current_score +
                ", card=" + card +
                ", item=" + item +
                '}';
    }
}
