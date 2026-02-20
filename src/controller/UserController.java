package controller;


import model.dao.GameLogDao;
import model.dao.UserDao;
import model.dto.PlayerDto;
import service.GameSaveService;

public class UserController {
    private UserController(){}
    private static final UserController instance = new UserController();
    public static UserController getInstance(){return instance;}

    private UserDao ud = UserDao.getInstance();
    private GameLogDao gld = GameLogDao.getInstance();
    private GameSaveService gss = GameSaveService.getInstance();

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
            GameSaveService.getInstance().loadGame(result); //로그인 성공 시 게임정보 불러오기

            //닉네임 설정
            String nickname = ud.printNickName(result);
            PlayerDto.getInstance().setNickname(nickname);

            // 현재 플레이어 번호 설정
            PlayerDto.getInstance().setUser_no(result);

            return true;
        }
        return false;
    }

    //내 정보 저장 함수
    public boolean saveGame(){
        return gss.saveGame();
    }

    //내 최고기록 출력함수
    public int myBestScore(int user_no){
        return gld.myBestScore(user_no);
    }



}
