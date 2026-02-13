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



    public void printGameStatus(RoundDto boss, int currentScore, int submitLeft, int discardLeft, ArrayList<Card> hand){
        System.out.println("\n\n\n\n\n");
        System.out.println("================================================");
        System.out.printf("[ Round %d : 목표 점수 %d점 ]\n", boss.getRoundNo(), boss.getTargetScore());
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

            // 1. 카드 번호 출력 (1.   2.   3.   4.)
            for (int j = i; j < end; j++) {
                System.out.printf("   %d.      ", j); // 인덱스 그대로 보여줌 (0번부터면 j, 1번부터면 j+1)
            }
            System.out.println();

            // 2. 박스 뚜껑 (┌───┐)
            for (int j = i; j < end; j++) System.out.print(" ┌───┐    ");
            System.out.println();

            // 3. 월 정보 (│ 1월 │)
            for (int j = i; j < end; j++) {
                String monthStr = String.format("%2d월", hand.get(j).getMonth());
                System.out.print(" │" + monthStr + " │    ");
            }
            System.out.println();

            // 4. 중간 공백 (│    │)
            for (int j = i; j < end; j++) System.out.print(" │    │    ");
            System.out.println();

            // 5. 타입 정보 (│ 광 │)
            for (int j = i; j < end; j++) {
                String typeStr = hand.get(j).getType();
                // 한글 2글자(피, 광, 띠, 열) 길이를 맞추기 위한 공백 처리
                if(typeStr.length() == 1) System.out.print(" │ " + typeStr + "  │    ");
                else System.out.print(" │ " + typeStr + " │    ");
            }
            System.out.println();

            // 6. 박스 바닥 (└───┘)
            for (int j = i; j < end; j++) System.out.print(" └───┘    ");
            System.out.println();

            // 줄바꿈 (다음 4장을 위해)
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
