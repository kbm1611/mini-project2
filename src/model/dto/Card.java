package model.dto;

public class Card {
    private int card_no;
    private int month;
    private String type;
    private int score;
    private String name;

    public Card() {}

    public Card(int card_no, int month, String type, int score, String name) {
        this.card_no = card_no;
        this.month = month;
        this.type = type;
        this.score = score;
        this.name = name;
    }

    public int getCard_no() {return card_no;}
    public void setCard_no(int card_no) {this.card_no = card_no;}
    public int getMonth() {return month;}
    public void setMonth(int month) {this.month = month;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return "Card{" +
                "card_no=" + card_no +
                ", month=" + month +
                ", type='" + type + '\'' +
                ", score=" + score +
                ", name='" + name + '\'' +
                '}';
    }
}
