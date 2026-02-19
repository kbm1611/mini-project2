package model.dto;

public class UserDto {
    private int user_no;
    private String uid;
    private String upwd;
    private String nickname;

    public UserDto(){}
    public UserDto(int user_no, String uid, String upwd, String nickname) {
        this.user_no = user_no;
        this.uid = uid;
        this.upwd = upwd;
        this.nickname = nickname;
    }

    public int getUser_no() {return user_no;}
    public void setUser_no(int user_no) {this.user_no = user_no;}

    public String getUid() {return uid;}
    public void setUid(String uid) {this.uid = uid;}

    public String getupwd() {return upwd;}
    public void setupwd(String upwd) {this.upwd = upwd;}

    public String getnickname() {return nickname;}
    public void setnickname(String nickname) {this.nickname = nickname;}

    @Override
    public String toString() {
        return "UserDto{" +
                "user_no=" + user_no +
                ", uid='" + uid + '\'' +
                ", upwd='" + upwd + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
