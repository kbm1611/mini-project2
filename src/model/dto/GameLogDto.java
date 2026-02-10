package model.dto;

public class GameLogDto {
    private int log_no;
    private int user_no;
    private String play_date;
    private int final_score;
    private int final_round;

    public GameLogDto(int log_no, int user_no, String play_date, int final_score, int final_round) {
        this.log_no = log_no;
        this.user_no = user_no;
        this.play_date = play_date;
        this.final_score = final_score;
        this.final_round = final_round;
    }

    public int getLog_no() {
        return log_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public String getPlay_date() {
        return play_date;
    }

    public int getFinal_score() {
        return final_score;
    }

    public int getFinal_round() {
        return final_round;
    }

    public void setLog_no(int log_no) {
        this.log_no = log_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public void setPlay_date(String play_date) {
        this.play_date = play_date;
    }

    public void setFinal_score(int final_score) {
        this.final_score = final_score;
    }

    public void setFinal_round(int final_round) {
        this.final_round = final_round;
    }

    @Override
    public String toString() {
        return "GameLogDto{" +
                "log_no=" + log_no +
                ", user_no=" + user_no +
                ", play_date='" + play_date + '\'' +
                ", final_score=" + final_score +
                ", final_round=" + final_round +
                '}';
    }
}
