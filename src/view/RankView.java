package view;

import controller.RankController;
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
                """);
        }
        System.out.println("================================================");
        System.out.println("[0] 뒤로 가기");
        System.out.print(">>입력:"); int ch = scan.nextInt();

        if(ch == 0){
            //추후에 메인메뉴화면으로 넘어가기
        }
    }
    public void myLogView(){

    }
}
