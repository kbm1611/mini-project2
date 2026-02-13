package controller;


import model.dao.UserDao;

public class UserController {
    private UserController(){}
    private static final UserController instance = new UserController();
    public static UserController getInstance(){return instance;}

    private UserDao ud = UserDao.getInstance();

    private int loginSession=0;
    public int getLoginSession(){return loginSession;}


    // controller 회원가입
    public boolean register(String uid, String upwd, String nickname){
        boolean result = ud.register(uid, upwd, nickname);
        return result;
    }



    //  controller 로그인
    public boolean login(String uid, String upwd){
        int result = ud.login(uid, upwd);
        if(result !=-1){
            loginSession = result;
                return true;
        }
        return false;
    }



}
