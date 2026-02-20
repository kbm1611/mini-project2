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
        boolean isGameReady = false;

        while (!isGameReady) {
            int menuChoice = PV.printTitleMenu();

            if (menuChoice == 1) {
                // 🆕 [새로하기] : 데이터 초기화 & 기본 덱 지급
                GS.startNewGame();
                service.GameSaveService.getInstance().saveGame();
                isGameReady = true;

            } else if (menuChoice == 2) {
                // 💾 [이어하기] : 저장된 데이터 확인
                if (model.dto.PlayerDto.getInstance().getCurrent_hp() > 0) {
                    PV.printMessage("💾 저장된 게임을 불러왔습니다! ("
                            + model.dto.PlayerDto.getInstance().getCurrent_round() + "라운드부터 시작)");
                    isGameReady = true;
                } else {
                    PV.printMessage("🚫 저장된 데이터가 없거나 이미 파산했습니다. '새로하기'를 선택하세요.");
                }

            } else if (menuChoice == 0) {
                // ❌ [종료]
                PV.printMessage("게임을 종료합니다. 안녕히 가세요! 👋");
                return; // 프로그램 종료

            } else {
                PV.printMessage("⚠️ 잘못된 입력입니다. 다시 선택해 주세요.");
            }
        }

        PV.printMessage("============== 🎴 화투로 시작 🎴 ==============");

        int currentStage = model.dto.PlayerDto.getInstance().getCurrent_round();

        while (true){
            RoundDto boss = GS.startRound(currentStage);

            while (true){
                model.dto.PlayerDto player = model.dto.PlayerDto.getInstance();

                PV.printGameStatus(
                        boss,
                        player.getCurrent_score(),    // 현재 점수
                        player.getCurrent_hp(),       // 남은 손패 내기 기회
                        player.getCurrent_discard(),  // 남은 버리기 기회
                        player.getCurrent_hand()              // 현재 내 손패
                );
                int choice = PV.printMenu();
                if (choice == 1){
                    ArrayList<model.dto.Item> myItems = model.dto.PlayerDto.getInstance().getItem();
                    int itemChoice = PV.printActiveItemMenu(myItems);

                    if (itemChoice == 4) {
                        service.ItemUseService.getInstance().useRevelationOfSpirit(); // 신령님 발동!
                    } else if (itemChoice == 5) {
                        service.ItemUseService.getInstance().useBottomDealing(); // 밑장 빼기 발동!
                    }else if (itemChoice == 6){
                        service.ItemUseService.getInstance().ancestorHelp(); // 조상님의 도움 발동
                    }else if (itemChoice == 10){
                        service.ItemUseService.getInstance().magic(); // 아수라발발타 발동
                    } else if (itemChoice != -1 && itemChoice != 0) {
                        PV.printMessage("⚠️ 잘못된 점괘 번호입니다.");
                    }
                } else if (choice == 2) {
                    // 부적 효과 보기
                    ArrayList<model.dto.Item> myItems = model.dto.PlayerDto.getInstance().getItem();
                    PV.printPassiveItems(myItems);
                } else if (choice == 3) {
                    processDiscardHand();
                } else if (choice == 4) {
                    processSubmitHand();
                } else if (choice == 5) {
                    processViewDeck();
                } else if (choice == 6) {
                    service.GameSaveService.getInstance().saveGame();
                    PV.printMessage("💾 게임이 성공적으로 저장되었습니다. 안녕히 가세요!");
                    return;
                } else {PV.printMessage("⚠️ 잘못된 입력입니다. 다시 선택해 주세요.");}

                if(GS.checkRoundClear()){
                    PV.printMessage("\n🎉 축하합니다! [" + boss.getRoundName() + "] 라운드를 클리어했습니다!");
                    GS.resetRound();
                    currentStage++;
                    model.dto.PlayerDto.getInstance().setCurrent_round(currentStage);
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
                    GS.startNewGame();
                    service.GameSaveService.getInstance().saveGame();
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
