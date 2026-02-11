package service;

import constant.GameConst;
import model.dto.Card;
import model.dto.JokboDto;
import model.dto.RoundDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameService {
    private ArrayList<Card> deck; // 남은덱
    private ArrayList<Card> hand; // 내 손패
    private ArrayList<Card> grave; // 무덤


    private int currentRound;  // 현재 라운드
    private int currentScore;  // 현재 점수
    private int targetScore; // 목표 점수
    private int submitLeft; // 남은 카드 내기 기회
    private int discardLeft; // 남은 카드 버리기 기회

    public GameService() {
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.grave = new ArrayList<>();
    }

    public void initDeck(){
        this.deck.clear();
        this.deck.addAll(GameConst.BASIC_DECK);
        Collections.shuffle(this.deck);
    }

    public RoundDto startRound(int roundNo){
        this.currentRound = roundNo;
        this.currentScore = 0;

        RoundDto boss = GameConst.ROUND_LIST.get(roundNo-1);
        this.targetScore = boss.getTargetScore();

        this.submitLeft = 5;
        this.discardLeft = 3;

        Collections.shuffle(this.deck);
        return boss;
    }

    public void recycleGrave(){
        if(this.grave.isEmpty()){
            return;
        }
        this.deck.addAll(this.grave);
        this.grave.clear();
        Collections.shuffle(this.deck);

        // 반환값을 boolean 으로 바꿔서 view에서 출력해도 됨
        System.out.println("\"\uD83D\uDD04 덱이 다 떨어져서 버린 패를 섞었습니다!\"");
    }

    public ArrayList<Card> drawCard(int count){
        ArrayList<Card> newlyDrawn = new ArrayList<>();

        for(int i = 0; i < count; i++){
            if(this.deck.isEmpty()){
                recycleGrave();

                if (this.deck.isEmpty()){
                    System.out.println("⚠️ 더 이상 뽑을 카드가 없습니다!");
                    break;
                }
            }

            Card drawnCard = this.deck.remove(0);
            this.hand.add(drawnCard);
            newlyDrawn.add(drawnCard);
        }
        return newlyDrawn;
    }


    public ArrayList<Card> discardHand(int[] indexes){
        if (this.discardLeft <= 0){
            System.out.println("⚠️ 패 버리기 기회를 모두 소모했습니다!");
            return new ArrayList<>();
        }

        this.discardLeft--;
        Arrays.sort(indexes);
        for (int i = indexes.length-1; i>=0; i--){
            int idx = indexes[i];
            Card trashedCard = this.hand.remove(idx);
            this.grave.add(trashedCard);
        }

        int dropCount = indexes.length;
        ArrayList<Card> newlyDrawn = drawCard(dropCount);
        System.out.println("🗑️ 카드 " + dropCount + "장을 버리고 새로 뽑았습니다. (남은 기회: " + this.discardLeft + ")");

        return newlyDrawn;
    }


    public JokboDto checkJokbo(ArrayList<Card> submittedCards){
        int kwangCount=0, yulCount =0, ddiCount = 0, piCount = 0;
        ArrayList<Integer> kwangMonths = new ArrayList<>();
        ArrayList<Integer> yulMonths = new ArrayList<>();
        ArrayList<Integer> ddiMonths = new ArrayList<>();

        for(Card card : submittedCards){
            String type = card.getType();
            int month = card.getMonth();

            if (type.equals("광")){
                kwangCount++;
                kwangMonths.add(month);
            } else if (type.equals("열")){
                yulCount++;
                yulMonths.add(month);
            } else if (type.equals("띠")){
                ddiCount++;
                ddiMonths.add(month);
            } else if (type.equals("피")){
                piCount++;
            }
        }

        // 1. 오광 (광 5개)
        if (kwangCount == 5) return GameConst.JOKBO_LIST.get(0);
        // 2. 사광 (광 4개)
        if (kwangCount == 4) return GameConst.JOKBO_LIST.get(1);
        // 3. 삼광 (광 3개)
        if (kwangCount == 3) return GameConst.JOKBO_LIST.get(2);
        // 4. 띠 모음 (띠 5개)
        if (ddiCount == 5) return GameConst.JOKBO_LIST.get(3);
        // 5. 멍텅구리 (열 5개)
        if (yulCount == 5) return GameConst.JOKBO_LIST.get(4);

        // 6. 38광땡 (3월 광, 8월 광 포함)
        if (kwangMonths.contains(3) && kwangMonths.contains(8)) return GameConst.JOKBO_LIST.get(5);
        // 7. 18광땡 (1월 광, 8월 광 포함)
        if (kwangMonths.contains(1) && kwangMonths.contains(8)) return GameConst.JOKBO_LIST.get(6);
        // 8. 13광땡 (1월 광, 3월 광 포함)
        if (kwangMonths.contains(1) && kwangMonths.contains(3)) return GameConst.JOKBO_LIST.get(7);

        // 9. 고도리 (2월, 4월, 8월 열 포함)
        if (yulMonths.contains(2) && yulMonths.contains(4) && yulMonths.contains(8)) return GameConst.JOKBO_LIST.get(8);

        // 10. 홍단 (1월, 2월, 3월 띠 포함)
        if (ddiMonths.contains(1) && ddiMonths.contains(2) && ddiMonths.contains(3)) return GameConst.JOKBO_LIST.get(9);
        // 11. 청단 (6월, 9월, 10월 띠 포함)
        if (ddiMonths.contains(6) && ddiMonths.contains(9) && ddiMonths.contains(10)) return GameConst.JOKBO_LIST.get(10);
        // 12. 초단 (4월, 5월, 7월 띠 포함)
        if (ddiMonths.contains(4) && ddiMonths.contains(5) && ddiMonths.contains(7)) return GameConst.JOKBO_LIST.get(11);

        // 13. 피바다 (피 5개)
        if (piCount == 5) return GameConst.JOKBO_LIST.get(12);

        // 아무 족보도 아닐 경우
        return null;

    }


}
