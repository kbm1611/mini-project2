package model.dto;

public class ResultDto {
    private boolean success;
    private String message;
    private String jokboName;
    private int gainedScore;
    private int totalScore;

    public ResultDto() {}

    public ResultDto(boolean success, String message, String jokboName, int gainedScore, int totalScore) {
        this.success = success;
        this.message = message;
        this.jokboName = jokboName;
        this.gainedScore = gainedScore;
        this.totalScore = totalScore;
    }

    public boolean isSuccess() {return success;}
    public void setSuccess(boolean success) {this.success = success;}
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
    public String getJokboName() {return jokboName;}
    public void setJokboName(String jokboName) {this.jokboName = jokboName;}
    public int getGainedScore() {return gainedScore;}
    public void setGainedScore(int gainedScore) {this.gainedScore = gainedScore;}
    public int getTotalScore() {return totalScore;}
    public void setTotalScore(int totalScore) {this.totalScore = totalScore;}
}
