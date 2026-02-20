package view;

import controller.RankController;
import controller.UserController;
import model.dto.GameLogDto;
import model.dto.PlayerDto;

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

        int count = 1;
        System.out.print("""
                ====================================================
                         🏆 [ 명 예 의  전 당 ] TOP 5 🏆
                ====================================================
                 순위 |   닉네임   |  최고 점수  |         달성일
                ----------------------------------------------------
                """);
        for(GameLogDto rank : ranks){ //컨트롤러에서 랭킹 함수를 가져와 출력
            System.out.printf("""
                %3d  |%6s    |%6d점   | %s
                """, count, rank.getNickname(), rank.getFinal_score(), rank.getPlay_date());
            count++;
        }
        System.out.println("====================================================");
        System.out.println("[0] 뒤로 가기");
        System.out.print(">>입력:"); int ch = scan.nextInt();

        if(ch == 0){
            UserView.getInstance().mainview();
        }
    }
    public void myLogView(){
        PlayerDto player = PlayerDto.getInstance();
        int playCount = 0;
        double winCount = 0;

        int user_id = player.getUser_no(); //!!추후에 로그인 정보 넣어주기
        ArrayList<GameLogDto> myLogs = rc.printMyLog(user_id);

        //만약 신규유저라면(로그가 없음)
        if (myLogs == null || myLogs.isEmpty()) {
            System.out.println("=======================================================");
            System.out.println("👤 플레이어 : [ " + player.getNickname() + " ] 님의 지난 기록");
            System.out.println("=======================================================");
            System.out.println("   아직 플레이 기록이 없습니다. 게임을 먼저 즐겨보세요! 🎴");
            System.out.println("=======================================================");
            System.out.println("\n[0] 뒤로 가기");
            System.out.print(">> 입력: ");
            int ch = scan.nextInt();
            if(ch == 0){
                UserView.getInstance().mainview();
            }
            return;
        }

        System.out.printf("""
                =======================================================
                👤 플레이어 : [ %s ] 님의 지난 기록
                =======================================================
                  최종 점수 | 도달 라운드 | 남은 엽전 |      달성일자
                -------------------------------------------------------
                """, myLogs.getFirst().getNickname() );
        for(GameLogDto mylog : myLogs){
            System.out.printf("""
                   %6d 점  |%5d R    |%5d 냥  | %s
                   """, mylog.getFinal_score(), mylog.getFinal_round(), mylog.getFinal_money(), mylog.getPlay_date());
            playCount++;
            if(mylog.getFinal_round() > 8){
                winCount++;
            }
        }
        winCount = (winCount / playCount) * 100; //승률계산
        System.out.println("\n\uD83D\uDCCA [ 전적 요약 ]");
        System.out.printf("▶ 총 플레이 : %d 판      ▶ 승률 : %.1f%%", playCount, winCount);
        System.out.print("""
                
                ===================================================================
                [0] 뒤로 가기
                >> 입력 :""");
        System.out.print(" ");
        int ch = scan.nextInt();
        if(ch == 0){
            UserView.getInstance().mainview();
        }

    }
}
