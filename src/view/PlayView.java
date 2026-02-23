package view;

import model.dto.Card;
import model.dto.Item;
import model.dto.ResultDto;
import model.dto.RoundDto;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayView {
    private PlayView(){}
    private static PlayView instance = new PlayView();
    public static PlayView getInstance(){return instance;}
    private Scanner sc = new Scanner(System.in);


    public int printTitleMenu() {
        System.out.println("================================================");
        System.out.println("                 🃏 화투로 🃏                    ");
        System.out.println("================================================");
        System.out.println("  1. 🆕 새로하기 (New Game)");
        if(service.GameSaveService.getInstance().hasSaveData(model.dto.PlayerDto.getInstance().getUser_no())) { //save파일이 있다면 데이터 있음 출력
            System.out.println("  2. 💾 이어하기(데이터 있음) (Continue)");
        }else{
            System.out.println("  2. 💾 이어하기 (Continue)");
        }
        System.out.println("  0. ❌ 종료하기 (Exit)");
        System.out.println("================================================");
        System.out.print(">> 선택 : ");
        return getInputNumber();
    }

    public void printGameStatus(RoundDto boss, int currentScore, int submitLeft, int discardLeft, ArrayList<Card> hand){
        model.dto.PlayerDto player = model.dto.PlayerDto.getInstance();
        int currentMoney = player.getCurrent_money();
        ArrayList<model.dto.Item> myItems = player.getItem();
        System.out.println("\n\n\n\n\n");
        System.out.println("================================================");
        System.out.printf("[ Round %d  %s : 목표 점수 %d점 ]\n", boss.getRoundNo(), boss.getRoundName(), boss.getTargetScore());
        System.out.printf("현재 점수: %d | 💵 잔액: %d원 | 남은 손패 횟수: %d | 버리기 횟수: %d\n",
                currentScore, currentMoney, submitLeft, discardLeft);
        System.out.println("================================================");

        String amulets = "";     // 부적 (패시브)
        String divinations = ""; // 점괘 (액티브)

        if (myItems != null && !myItems.isEmpty()) {
            for (model.dto.Item item : myItems) {
                if ("부적".equals(item.getType())) {
                    amulets += "[" + item.getName() + "] ";
                } else if ("점괘".equals(item.getType())) {
                    divinations += item.getName() + "(" + item.getItem_no() + ") ";
                }
            }
        }

        // 아무것도 없을 때의 처리
        if (amulets.isEmpty()) amulets = "없음";
        if (divinations.isEmpty()) divinations = "없음";

        // 3. 분류한 아이템 출력
        System.out.println("[ 소유한 아이템 ]");
        System.out.println("🛡️ 부적: " + amulets);
        System.out.println("🔮 점괘: " + divinations);
        System.out.println("================================================");

        System.out.println("[ 나의 손패 (" + hand.size() + "장) ]");
        drawCardList(hand);
        System.out.println("================================================");
    }

    private void  drawCardList(ArrayList<Card> hand){
        int cardsPerRow = 4;
        int totalCards = hand.size();

        for (int i = 0; i < totalCards; i += cardsPerRow){
            int end = Math.min(i + cardsPerRow, totalCards);

            // 1. 카드 번호
            for (int j = i; j < end; j++) {
                System.out.printf("   %-3s    ", j + ".");
            }
            System.out.println();

            // 2. 박스 뚜껑 (절대 안 깨지는 + 와 - 조합)
            for (int j = i; j < end; j++) {
                System.out.print(" +------+ ");
            }
            System.out.println();

            // 3. 월 정보
            for (int j = i; j < end; j++) {
                int month = hand.get(j).getMonth();
                if (month < 10) {
                    System.out.print(" |  " + month + "월 | ");
                } else {
                    System.out.print(" | " + month + "월 | ");
                }
            }
            System.out.println();

            // 4. 타입 정보 (홍단/청단/초단 변환)
            for (int j = i; j < end; j++) {
                String typeStr = hand.get(j).getType();
                int month = hand.get(j).getMonth();

                if (typeStr.equals("띠")) {
                    if (month == 1 || month == 2 || month == 3) typeStr = "홍단";
                    else if (month == 6 || month == 9 || month == 10) typeStr = "청단";
                    else if (month == 4 || month == 5 || month == 7) typeStr = "초단";
                }

                // 여백 완벽 계산
                if(typeStr.length() == 1) {
                    System.out.print(" |  " + typeStr + "  | ");
                } else if (typeStr.length() == 2) {
                    System.out.print(" | " + typeStr + " | ");
                } else {
                    System.out.print(" |" + typeStr + "| ");
                }
            }
            System.out.println();

            // 5. 박스 바닥
            for (int j = i; j < end; j++) {
                System.out.print(" +------+ ");
            }
            System.out.println();
            System.out.println();
        }
    }
    public int getInputNumber() {
        try {
            String input = sc.nextLine();
            if(input.trim().equals("")) return -1;
            return Integer.parseInt(input);
        } catch (Exception e) {
            return -1;
        }
    }
    public int printMenu(){
        System.out.println("1. 점괘 적용   2. 부적 효과 확인   3. 카드 버리기");
        System.out.println("4. 손패 내기   5. 덱에 남은 카드 보기   6. 저장하고 나가기");
        System.out.println("7. 족보 보기");
        System.out.print(">> 선택 : ");
        return getInputNumber();
    }

    public int printActiveItemMenu(ArrayList<Item> items) {
        System.out.println("\n================================================");
        System.out.println("              [ 🔮 보유 중인 점괘 ]              ");
        System.out.println("================================================");

        boolean hasActive = false;
        if (items != null) {
            for (Item item : items) {
                if ("점괘".equals(item.getType())) {
                    System.out.println(" [" + item.getItem_no() + "] "
                            + item.getName() + " : " + item.getDescription());
                    hasActive = true;
                }
            }
        }

        if (!hasActive) {
            System.out.println("  사용 가능한 점괘가 없습니다.");
            System.out.println("================================================");
            System.out.println("엔터를 치면 돌아갑니다...");
            sc.nextLine();
            return -1;
        }

        System.out.println("================================================");
        System.out.println("사용할 점괘의 번호를 입력하세요. (0: 취소)");
        System.out.print(">> 선택: ");
        return getInputNumber();
    }

    public void printPassiveItems(ArrayList<Item> items) {
        System.out.println("\n================================================");
        System.out.println("              [ 🛡️ 적용 중인 부적 ]              ");
        System.out.println("================================================");

        boolean hasPassive = false;
        if (items != null) {
            for (Item item : items) {
                if ("부적".equals(item.getType())) {
                    System.out.println(" [" + item.getName() + "] : " + item.getDescription());
                    hasPassive = true;
                }
            }
        }

        if (!hasPassive) {
            System.out.println("  가진 게 없습니다... 상점에서 부적을 구매하세요!");
        }
        System.out.println("================================================");
        System.out.println("엔터를 치면 돌아갑니다...");
        sc.nextLine();
    }

    public void printSubmitResult(ResultDto result) {
        if (!result.isSuccess()) {
            System.out.println("\n🚫 " + result.getMessage());
            return;
        }
        System.out.println("\n------------------------------------------------");
        System.out.println("[ 🎴 조합 결과 ]");
        System.out.println(">>> ⚠️ [" + result.getJokboName() + "] 완성!!");

        // 기본점수 * 배율 부분

        System.out.println("\n💥 쾅! " + result.getGainedScore() + "점을 획득했습니다.");
        System.out.println("💰 현재 총 점수: " + result.getTotalScore());
        System.out.println("------------------------------------------------");


        System.out.println("엔터를 치면 계속합니다...");
        sc.nextLine();
    }

    public void printClearReceipt(int round, int base, int bonus, int interest, int total, int currentMoney) {
        System.out.println("\n\n");
        System.out.println("  🎉  R O U N D  " + round + "  C L E A R !  🎉  ");
        System.out.println("┌──────────────────────────────────────┐");
        System.out.println("│              💸 정 산 표             │");
        System.out.println("├──────────────────────────────────────┤");
        System.out.printf("│  1. 라운드 기본급          + %4d원   │\n", base);
        System.out.printf("│  2. 남은 기회 보너스       + %4d원   │\n", bonus);
        System.out.printf("│  3. 저축 이자 (최대 250)   + %4d원   │\n", interest);
        System.out.println("├──────────────────────────────────────┤");
        System.out.printf("│  💰 총 획득 금액           + %4d원   │\n", total);
        System.out.println("└──────────────────────────────────────┘");
        System.out.println("   👉 현재 소지금: [ " + currentMoney + " 원 ]");
        System.out.println("\n엔터를 치면 다음 라운드로 이동합니다...");
        sc.nextLine(); // 사용자 입력 대기
    }

    public int[] getInputIndexes(String msg) {
        System.out.println(msg);
        System.out.print(">> 입력: ");
        try {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) return null; // 그냥 엔터만 쳤을 때 취소 처리

            String[] parts = input.split("\\s+"); // 띄어쓰기를 여러 번 해도 하나로 인식
            int[] indexes = new int[parts.length];

            // 내 손패가 지금 몇 장인지 확인
            int handSize = model.dto.PlayerDto.getInstance().getCurrent_hand().size();

            for (int i = 0; i < parts.length; i++) {
                int idx = Integer.parseInt(parts[i]);

                // 방어 1: 손패 범위를 벗어난 숫자 (예: 8장인데 9 입력)
                if (idx < 0 || idx >= handSize) {
                    System.out.println("⚠️ 없는 번호입니다! (0 ~ " + (handSize - 1) + " 사이로 입력하세요)");
                    return null;
                }
                indexes[i] = idx;
            }

            // 방어 2: 똑같은 번호 중복 입력 방지 (예: 1 1 2 입력 방지)
            long distinctCount = java.util.Arrays.stream(indexes).distinct().count();
            if (distinctCount != indexes.length) {
                System.out.println("⚠️ 같은 카드 번호를 중복해서 낼 수 없습니다!");
                return null;
            }

            return indexes;

        } catch (Exception e) {
            System.out.println("⚠️ 숫자만 띄어쓰기로 구분해서 정확히 입력해주세요.");
            return null;
        }
    }
    public void printMessage(String msg) {
        System.out.println(msg);
    }

    public void printDeckInfo(ArrayList<Card> deck) {
        System.out.println("\n[ 덱에 남은 카드 (" + deck.size() + "장) ]");
        int count = 0;
        for(Card c : deck) {
            System.out.print("[" + c.getName() + "] ");
            if(++count % 6 == 0) System.out.println();
        }
        System.out.println("\n엔터를 치면 돌아갑니다.");
        sc.nextLine();
    }
}
