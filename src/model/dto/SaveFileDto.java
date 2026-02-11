package model.dto;

import java.util.ArrayList;

public class SaveFileDto {
    private int user_no;
    private int current_round;
    private int current_hp;
    private int current_money;
    private int current_score;

    //각각 카드번호와 아이템번호가 들어가 있는 배열리스트
    private ArrayList<Card> cards;
    private ArrayList<Item> items;

    public SaveFileDto(int user_no, int current_round, int current_hp, int current_money, int current_score, ArrayList<Card> cards, ArrayList<Item> items) {
        this.user_no = user_no;
        this.current_round = current_round;
        this.current_hp = current_hp;
        this.current_money = current_money;
        this.current_score = current_score;
        this.cards = cards;
        this.items = items;
    }

    public int getUser_no() {
        return user_no;
    }
    public int getCurrent_round() {
        return current_round;
    }
    public int getCurrent_hp() {
        return current_hp;
    }
    public int getCurrent_money() {
        return current_money;
    }
    public int getCurrent_score() {
        return current_score;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public ArrayList<Item> getItems() { return items; }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }
    public void setCurrent_round(int current_round) {
        this.current_round = current_round;
    }
    public void setCurrent_hp(int current_hp) {
        this.current_hp = current_hp;
    }
    public void setCurrent_money(int current_money) {
        this.current_money = current_money;
    }
    public void setCurrent_score(int current_score) {
        this.current_score = current_score;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "SaveFileDto{" +
                "user_no=" + user_no +
                ", current_round=" + current_round +
                ", current_hp=" + current_hp +
                ", current_money=" + current_money +
                ", current_score=" + current_score +
                ", cards=" + cards +
                ", items=" + items +
                '}';
    }
}
