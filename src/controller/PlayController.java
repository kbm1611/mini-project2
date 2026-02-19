package controller;

import model.dto.Card;
import model.dto.ResultDto;
import model.dto.RoundDto;
import service.GameService;
import view.PlayView;
import view.ShopView;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayController {
    private PlayController(){}
    private static final PlayController instance = new PlayController();
    public static PlayController getInstance(){return instance;}
    private GameService GS = GameService.getInstance();
    private PlayView PV = PlayView.getInstance();

    public void play(){
        PV.printMessage("============== 🎴 화투로 시작 🎴 ==============");

        int currentStage = 1;

        while (true){
            RoundDto boss = GS.startRound(currentStage);

            while (true){
                PV.printGameStatus(boss,GS.getCurrentScore(),GS.getSubmitLeft(),GS.getDiscardLeft(),GS.getHand());
                int choice = PV.printMenu();
                if (choice == 1){
                    // 점괘 사용하기
                } else if (choice == 2) {
                    // 부적 효과 보기
                } else if (choice == 3) {
                    processDiscardHand();
                } else if (choice == 4) {
                    processSubmitHand();
                } else if (choice == 5) {
                    processViewDeck();
                } else if (choice == 6) {
                    //저장하고 나가기
                } else {PV.printMessage("⚠️ 잘못된 입력입니다. 다시 선택해 주세요.");}

                if(GS.checkRoundClear()){
                    PV.printMessage("\n🎉 축하합니다! [" + boss.getRoundName() + "] 라운드를 클리어했습니다!");
                    GS.resetRound();
                    currentStage++;
                    ShopView.getInstance().printShopView();
                    if (currentStage > 8){
                        PV.printMessage("\uD83C\uDFC6 전설의 타짜가 되셨습니다! 게임 승리!");
                        // 게임 결과 저장하고 메인화면으로 나가지기
                        return;
                    }

                    PV.printMessage("엔터를 치면 다음 라운드로 넘어갑니다...");
                    new java.util.Scanner(System.in).nextLine();
                    break;
                }
                if (GS.isGameOver()){
                    PV.printMessage("\n💀 게임 오버... [" + boss.getRoundName() + "]에게 패배했습니다.");
                    // 게임 결과 저장하고 나가기
                    return;
                }
            }

        }
    }

    private void processSubmitHand() {
        int[] indexes = PV.getInputIndexes("내실 카드 번호를 입력하세요");
        if (indexes == null) return;
        ResultDto result = GS.submitHand(indexes);
        PV.printSubmitResult(result);
    }

    private void processViewDeck() {
        ArrayList<Card> sortedDeck = GS.getDeckInfo();
        PV.printDeckInfo(sortedDeck);
    }

    private void processDiscardHand() {

        int[] indexes = PV.getInputIndexes("버릴 카드 번호를 입력하세요");
        if (indexes == null) return;
        GS.discardHand(indexes);
        PV.printMessage("🗑️ 카드를 버리고 새로 뽑았습니다.");
    }
}
