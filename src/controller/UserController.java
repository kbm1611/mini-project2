package controller;


import model.dao.UserDao;

public class UserController {
    private UserController(){}
    private static final UserController instance = new UserController();
    public static UserController getInstance(){return instance;}

    private UserDao ud = UserDao.getInstance();

    // controller 회원가입
    public boolean register(String uid, String upw, String unickname){
        boolean result = ud.register(uid, upw, unickname);
        return result;
    }

    //  controller 로그인
    public  boolean login(String uid, String upw){
        boolean result = ud.login(uid, upw);
        return result;
    }
}
