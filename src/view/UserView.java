package view;

import controller.UserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserView {
    private UserView() {
    }

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
            System.out.println("Deck Duilding Hwaturo v1.0");
            System.out.println("======================================\n");
            System.out.println("  섯다보다 쫄깃하고 고스톱보다 전략적이다!\n");
            System.out.println(" [1] 회원가입(Register)");
            System.out.println(" [2] 로그인(Login)");
            System.out.println(" [3] 게임 종료(Exit)\n");
            System.out.println(">> 메뉴 선택 ex) 1");

            try {
                int ch = userscan.nextInt();
                if (ch == 1) {
                    register();
                } else if (ch == 2) {
                } else if (ch == 3) {
                }
            } catch (NullPointerException e1) {
                System.out.println("숫자를 입력하세요.");

            } catch (InputMismatchException e2) {
                System.out.println("숫자만 입력해 주세요\n" + e2);
            } catch (Exception e3) {
                System.out.println("입력오류" + e3);
            }
        }
    }

    // 회원가입
    public void register() {
        System.out.println("==================================");
        System.out.println("             [회원가입]");
        System.out.println("==================================");
        System.out.println("사용할 아이디와 비밀번호를 입력하세요");

        System.out.print(">> 아이디 : ");
        String uid = userscan.next();
        System.out.print(">> 비밀번호 : ");
        String upw = userscan.next();
        System.out.print(">> 닉네임 : ");
        String unickname = userscan.next();

        boolean result = uc.register(uid, upw, unickname);
        if (result) {
            System.out.println("(시스템)환영합니다. 가입이 완료되었습니다.");
        } else {
            System.out.println("회원가입 실패");
        }

    }
}
