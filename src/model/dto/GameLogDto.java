package model.dto;

public class GameLogDto {
    private int log_no;
    private String nickname;
    private String play_date;
    private int final_score;
    private int final_round;
    private int final_money;

    public GameLogDto(int log_no, String nickname, String play_date, int final_score, int final_round, int final_money) {
        this.log_no = log_no;
        this.nickname = nickname;
        this.play_date = play_date;
        this.final_score = final_score;
        this.final_round = final_round;
        this.final_money = final_money;
    }

    public GameLogDto(int log_no, String nickname, int final_score, String play_date) {
        this.log_no = log_no;
        this.final_score = final_score;
        this.nickname = nickname;
        this.play_date = play_date;
    }

    public GameLogDto(int log_no, int final_score, int final_round, int final_money, String play_date) {
        this.log_no = log_no;
        this.final_score = final_score;
        this.final_round = final_round;
        this.final_money = final_money;
        this.play_date = play_date;
    }

    public int getLog_no() {
        return log_no;
    }
    public String getNickname() { return nickname; }
    public String getPlay_date() {
        return play_date;
    }
    public int getFinal_score() {
        return final_score;
    }
    public int getFinal_round() {
        return final_round;
    }
    public int getFinal_money() { return final_money; }
    public void setLog_no(int log_no) {
        this.log_no = log_no;
    }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setPlay_date(String play_date) {
        this.play_date = play_date;
    }
    public void setFinal_score(int final_score) {
        this.final_score = final_score;
    }
    public void setFinal_round(int final_round) {
        this.final_round = final_round;
    }
    public void setFinal_money(int final_money) { this.final_money = final_money; }

    @Override
    public String toString() {
        return "GameLogDto{" +
                "log_no=" + log_no +
                ", nickname='" + nickname + '\'' +
                ", play_date='" + play_date + '\'' +
                ", final_score=" + final_score +
                ", final_round=" + final_round +
                ", final_money=" + final_money +
                '}';
    }
}
