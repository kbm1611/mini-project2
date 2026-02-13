package view;

import controller.RankController;
import controller.UserController;
import model.dto.GameLogDto;

import java.util.ArrayList;
import java.util.Scanner;

public class RankView {
    private RankView(){}
    private static final RankView instance = new RankView();
    public static RankView getInstance(){
        return instance;
    }
    private RankController rc = RankController.getInstance();
    private Scanner scan = new Scanner(System.in);

    public void rankingView(){
        ArrayList<GameLogDto> ranks = rc.printRanking(); //함수 선언

        System.out.println("""
                ================================================
                         🏆 [ 명 예 의  전 당 ] TOP 5 🏆
                ================================================
                 순위    |    닉네임    |    최고 점수   |   달성일
                ------------------------------------------------
                """);
        for(GameLogDto rank : ranks){ //컨트롤러에서 랭킹 함수를 가져와 출력
            System.out.printf("""
                  %d    |   %s     |   %d점    | %s
                \n""");
        }
        System.out.println("================================================");
        System.out.println("[0] 뒤로 가기");
        System.out.print(">>입력:"); int ch = scan.nextInt();

        if(ch == 0){
            //!!추후에 메인메뉴화면으로 넘어가기
        }
    }
    public void myLogView(){
        int playCount = 0;
        int winCount = 0;

        int user_id = 1; //!!추후에 로그인 정보 넣어주기
        ArrayList<GameLogDto> myLogs = rc.printMyLog(user_id);

        System.out.printf("""
                =====================================================
                👤 플레이어 : [ %s ] 님의 지난 기록
                =====================================================
                 최종 점수    |   도달 라운드   |   남은 엽전   |   달성일자
                ------------------------------------------------------
                \n""", myLogs.get(0).getNickname() );
        for(GameLogDto mylog : myLogs){
            System.out.printf("""
                   %d 점  |     %d R         |    %d 냥   |   %s
                   \n""", mylog.getFinal_score(), mylog.getFinal_round(), mylog.getFinal_money(), mylog.getPlay_date());
            playCount++;
            if(mylog.getFinal_round() > 8){
                winCount++;
            }
        }
        System.out.println("\uD83D\uDCCA [ 전적 요약 ]");
        System.out.printf("▶ 총 플레이 : %d 판      ▶ 승률 : %d %\n", playCount, winCount);
        System.out.println("""
                ===================================================================
                [0] 뒤로 가기
                >> 입력 :
                """);
        int ch = scan.nextInt();
        if(ch == 0){
            //!!추후에 메인메뉴화면으로 넘어가기
        }

    }
}
