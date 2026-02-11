package service;

import constant.GameConst;
import model.dto.Card;
import model.dto.RoundDto;

import java.util.ArrayList;
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

    public void startRound(int roundNo){
        this.currentRound = roundNo;
        this.currentScore = 0;

        RoundDto boss = GameConst.ROUND_LIST.get(roundNo-1);
        this.targetScore = boss.getTargetScore();

        this.submitLeft = 5;
        this.discardLeft = 3;

        Collections.shuffle(this.deck);
    }

}
