package model.dto;

public class RoundDto {
    private int roundNo;
    private String roundName;
    private int targetScore;

    public RoundDto(){}

    public RoundDto(int roundNo, String roundName, int targetScore) {
        this.roundNo = roundNo;
        this.roundName = roundName;
        this.targetScore = targetScore;
    }

    public int getRoundNo() {return roundNo;}
    public void setRoundNo(int roundNo) {this.roundNo = roundNo;}
    public String getRoundName() {return roundName;}
    public void setRoundName(String roundName) {this.roundName = roundName;}
    public int getTargetScore() {return targetScore;}
    public void setTargetScore(int targetScore) {this.targetScore = targetScore;}

    @Override
    public String toString() {
        return "RoundDto{" +
                "roundNo=" + roundNo +
                ", roundName='" + roundName + '\'' +
                ", targetScore=" + targetScore +
                '}';
    }
}
