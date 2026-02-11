package model.dto;

public class UserDto {
    private int user_no;
    private String uid;
    private String upw;
    private String unickname;

    public UserDto(){}

    public UserDto(int user_no, String uid, String upw, String unickname) {
        this.user_no = user_no;
        this.uid = uid;
        this.upw = upw;
        this.unickname = unickname;
    }

    public int getUser_no() {return user_no;}
    public void setUser_no(int user_no) {this.user_no = user_no;}

    public String getUid() {return uid;}
    public void setUid(String uid) {this.uid = uid;}

    public String getUpw() {return upw;}
    public void setUpw(String upw) {this.upw = upw;}

    public String getUnickname() {return unickname;}
    public void setUnickname(String unickname) {this.unickname = unickname;}

    @Override
    public String toString() {
        return "UserDto{" +
                "user_no=" + user_no +
                ", uid='" + uid + '\'' +
                ", upw='" + upw + '\'' +
                ", unickname='" + unickname + '\'' +
                '}';
    }
}
