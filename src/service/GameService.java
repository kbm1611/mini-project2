package service;

import constant.GameConst;
import model.dto.Card;
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





}
