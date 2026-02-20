package model.dto;

import java.util.ArrayList;

public class SaveFileDto {
    private int user_no;
    private int current_round;
    private int current_hp;
    private int current_discard;
    private int current_money;
    private int current_score;
    private String cards;
    private String items;
    private String grave;
    private String hand;

    public SaveFileDto(int user_no, int current_round, int current_hp, int current_discard, String grave, String hand, int current_money, int current_score, String cards, String items) {
        this.user_no = user_no;
        this.current_round = current_round;
        this.current_hp = current_hp;
        this.grave = grave;
        this.hand = hand;
        this.current_discard = current_discard;
        this.current_money = current_money;
        this.current_score = current_score;
        this.cards = cards;
        this.items = items;
    }

    public String getGrave() {
        return grave;
    }

    public String getHand() {
        return hand;
    }

    public void setGrave(String grave) {
        this.grave = grave;
    }

    public void setHand(String hand) {
        this.hand = hand;
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

    public int getCurrent_discard() {
        return current_discard;
    }

    public int getCurrent_money() {
        return current_money;
    }
    public int getCurrent_score() {
        return current_score;
    }
    public String getCards() { return cards; }
    public String getItems() { return items; }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }
    public void setCurrent_round(int current_round) {
        this.current_round = current_round;
    }
    public void setCurrent_hp(int current_hp) {
        this.current_hp = current_hp;
    }

    public void setCurrent_discard(int current_discard) {
        this.current_discard = current_discard;
    }

    public void setCurrent_money(int current_money) {
        this.current_money = current_money;
    }
    public void setCurrent_score(int current_score) {
        this.current_score = current_score;
    }
    public void setCards(String cards) { this.cards = cards; }
    public void setItems(String items) { this.items = items; }

    @Override
    public String toString() {
        return "SaveFileDto{" +
                "user_no=" + user_no +
                ", current_round=" + current_round +
                ", current_hp=" + current_hp +
                ", current_discard=" + current_discard +
                ", current_money=" + current_money +
                ", current_score=" + current_score +
                ", cards='" + cards + '\'' +
                ", items='" + items + '\'' +
                ", grave='" + grave + '\'' +
                ", hand='" + hand + '\'' +
                '}';
    }
}
