package view;

import controller.PlayController;
import controller.UserController;
import model.dto.PlayerDto;


import java.util.InputMismatchException;
import java.util.Scanner;

public class UserView {
    private UserView() { }
    private static final UserView instance = new UserView();
    public static UserView getInstance() {
        return instance;
    }
    private UserController uc = UserController.getInstance();
    private Scanner userscan = new Scanner(System.in);

    public void index() {
        for (; ; ) {
            System.out.println("======================================");
            System.out.println("덱 빌링 로그라이크 : 화투로 ");
            System.out.println("Deck Building Hwaturo v1.0");
            System.out.println("██╗  ██╗██╗    ██╗ █████╗ ████████╗██╗   ██╗██████╗  ██████╗ \n" +
                    "██║  ██║██║    ██║██╔══██╗╚══██╔══╝██║   ██║██╔══██╗██╔═══██╗\n" +
                    "███████║██║ █╗ ██║███████║   ██║   ██║   ██║██████╔╝██║   ██║\n" +
                    "██╔══██║██║███╗██║██╔══██║   ██║   ██║   ██║██╔══██╗██║   ██║\n" +
                    "██║  ██║╚███╔███╔╝██║  ██║   ██║   ╚██████╔╝██║  ██║╚██████╔╝\n" +
                    "╚═╝  ╚═╝ ╚══╝╚══╝ ╚═╝  ╚═╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝ ╚═════╝ ");
            System.out.println("======================================\n");
            System.out.println("  섯다보다 쫄깃하고 고스톱보다 전략적이다!\n");
            System.out.println(" [1] 회원가입(Register)");
            System.out.println(" [2] 로그인(Login)");
            System.out.println(" [3] 게임 종료(Exit)\n");
            System.out.println(">> 메뉴 선택 ex) 1");

            try {
                int ch = userscan.nextInt();
                if (ch == 1) {register();}
                else if (ch == 2) { login();}
                else if (ch == 3) {break;}
            } catch (NullPointerException e1) {
                System.out.println("숫자를 입력하세요.");

            } catch (InputMismatchException e2) {
                userscan.nextLine();
                System.out.println("숫자만 입력해 주세요\n" + e2);
            } catch (Exception e3) {
                System.out.println("입력오류" + e3);
            }
        }
    }

    // 1. 회원가입
    public void register() {
        System.out.println("==================================");
        System.out.println("             [회원가입]");
        System.out.println("==================================\n");
        System.out.println("사용할 아이디와 비밀번호를 입력하세요");

        System.out.print(">> 아이디 : ");
        String uid = userscan.next();
        System.out.print(">> 비밀번호 : ");
        String upwd = userscan.next();
        System.out.print(">> 닉네임 : ");
        String nickname = userscan.next();

        boolean result = uc.register(uid, upwd, nickname);
        if (result) {
            System.out.println("(시스템)환영합니다. 가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입 실패");
        }
    }
    // 2. 로그인
    public void login(){
        System.out.println("==================================");
        System.out.println("             [로그인]");
        System.out.println("==================================\n");
        System.out.print(">> 아이디 : "); String uid = userscan.next();
        System.out.print(">> 비밀번호 : "); String upwd = userscan.next();

        boolean result = uc.login(uid, upwd);
        if(result){
            System.out.println("(시스템) 로그인 성공 ");
            mainview();
        }else {
            System.out.println("(시스템) 로그인 실패 ");
        }
    }

    // main 화면(로그인 후)

    public void mainview(){
        while(true){
            PlayerDto player = PlayerDto.getInstance();
            System.out.println("==================================");
            System.out.printf("환영합니다.%s\n", player.getNickname());
            System.out.printf("나의 최고 기록 :%s\n", uc.myBestScore(player.getUser_no()));
            System.out.println("==================================\n");
            System.out.println(" [1] 게임 시작");
            System.out.println(" [2] 전당 보기(랭킹확인)");
            System.out.println(" [3] 지난 기록");
            System.out.println(" [4] 로그아웃");
            System.out.println("==================================");
            System.out.print(">>선택");
            try{
                int ch = userscan.nextInt();
                if (ch == 1){ PlayController.getInstance().play(); }
                else if(ch == 2 ){RankView.getInstance().rankingView();}
                else if(ch == 3){RankView.getInstance().myLogView();}
                else if(ch == 4){
                    //저장 후 로그아웃
                    boolean result = uc.saveGame();
                    if(result){ System.out.println("(시스템)저장 성공"); }
                    else{ System.out.println("(시스템)저장 실패"); }
                    //처음 화면으로 이동
                    return ;
                }
            }catch (Exception e){
                System.out.println("main 화면 선택 오류"+e);
            }
        }
    }


}
