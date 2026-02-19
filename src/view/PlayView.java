package view;

import model.dto.Card;
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
        System.out.println("  2. 💾 이어하기 (Continue)");
        System.out.println("  0. ❌ 종료하기 (Exit)");
        System.out.println("================================================");
        System.out.print(">> 선택 : ");
        return getInputNumber();
    }

    public void printGameStatus(RoundDto boss, int currentScore, int submitLeft, int discardLeft, ArrayList<Card> hand){
        System.out.println("\n\n\n\n\n");
        System.out.println("================================================");
        System.out.printf("[ Round %d  %s : 목표 점수 %d점 ]\n", boss.getRoundNo(), boss.getRoundName(), boss.getTargetScore());
        System.out.printf("현재 점수: %d | 남은 손패 횟수: %d | 버리기 횟수: %d\n", currentScore, submitLeft, discardLeft);
        System.out.println("================================================");

        // 아이템 기능 구현 전
        System.out.println("소유한 아이템");
        System.out.println("부적: 호랑이 기운");
        System.out.println("점괘: 신령님의 계시(0), 산신령의 축복(1)");
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
        System.out.print(">> 선택 : ");
        return getInputNumber();
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
            String[] parts = sc.nextLine().split(" ");
            int[] indexes = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                indexes[i] = Integer.parseInt(parts[i]);
            }
            return indexes;
        } catch (Exception e) {
            System.out.println("⚠️ 입력 형식이 잘못되었습니다.");
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
