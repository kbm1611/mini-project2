package model.dto;

public class JokboDto {
    private int jokboNo;
    private String jokboName;
    private int jokboRatio;
    private int jokboScore;

    public JokboDto() {}

    public JokboDto(int jokboNo, String jokboName, int jokboRatio, int jokboScore) {
        this.jokboNo = jokboNo;
        this.jokboName = jokboName;
        this.jokboRatio = jokboRatio;
        this.jokboScore = jokboScore;
    }

    public int getJokboNo() {return jokboNo;}
    public void setJokboNo(int jokboNo) {this.jokboNo = jokboNo;}
    public String getJokboName() {return jokboName;}
    public void setJokboName(String jokboName) {this.jokboName = jokboName;}
    public int getJokboRatio() {return jokboRatio;}
    public void setJokboRatio(int jokboRatio) {this.jokboRatio = jokboRatio;}
    public int getJokboScore() {return jokboScore;}
    public void setJokboScore(int jokboScore) {this.jokboScore = jokboScore;}

    @Override
    public String toString() {
        return "JokboDto{" +
                "jokboNo=" + jokboNo +
                ", jokboName='" + jokboName + '\'' +
                ", jokboRatio=" + jokboRatio +
                ", jokboScore=" + jokboScore +
                '}';
    }
}
