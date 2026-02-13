package service;

import constant.GameConst;
import model.dto.Card;
import model.dto.JokboDto;
import model.dto.ResultDto;
import model.dto.RoundDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GameService {
    private static GameService instance = new GameService();
    public static GameService getInstance(){return instance;}

    private ArrayList<Card> deck; // 남은덱
    private ArrayList<Card> hand; // 내 손패
    private ArrayList<Card> grave; // 무덤


    private int currentRound;  // 현재 라운드
    private int currentScore;  // 현재 점수
    private int targetScore; // 목표 점수
    private int submitLeft; // 남은 카드 내기 기회
    private int discardLeft; // 남은 카드 버리기 기회
    private GameService() {
        this.deck = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.grave = new ArrayList<>();
        this.currentRound = 1;
        this.currentScore = 0;
    }

    public void initDeck(){
        this.deck.clear(); // 덱 비우기
        this.deck.addAll(GameConst.BASIC_DECK); // 상수 덱가져오기 (48장의 카드로 이루어진)
        Collections.shuffle(this.deck); // 덱 섞기
    }

    public RoundDto startRound(int roundNo){ // 라운드 번호를 매개변수로 받음
        this.currentRound = roundNo; // 현제 라운드는 받은 라운드 번호
        this.currentScore = 0; // 점수 초기화

        RoundDto boss = GameConst.ROUND_LIST.get(roundNo-1); // 보스 불러오기
        this.targetScore = boss.getTargetScore(); // 목표 점수 설정

        this.submitLeft = 5; // 제출기회 초기화
        this.discardLeft = 3; // 버리기 기회 초기화

        Collections.shuffle(this.deck); // 덱 섞기
        return boss; // 보스 객체를 리턴해 프론트에서 몇라운드 보스이름 목표점수 출력
    }

    public void recycleGrave(){
        if(this.grave.isEmpty()){ // 무덤이 비었으면(덱으로 되돌릴 카드가 없음)
            return; //아무것도 리턴 안함
        }
        this.deck.addAll(this.grave); // 무덤에 있는 카드들을 덱으로 옮김
        this.grave.clear(); // 무덤 비우기
        Collections.shuffle(this.deck); // 덱섞기

        // 반환값을 boolean 으로 바꿔서 view에서 출력해도 됨
        System.out.println("\"\uD83D\uDD04 덱이 다 떨어져서 버린 패를 섞었습니다!\"");
    }

    public ArrayList<Card> drawCard(int count){ //뽑아야 하는 카드 수를 매개변수로 받음
        ArrayList<Card> newlyDrawn = new ArrayList<>(); // 뽑은 패를 놓아놓는 AraayList

        for(int i = 0; i < count; i++){ //뽑아야 하는 카드 수만큼 반복
            if(this.deck.isEmpty()){ // 덱이 비어있다면
                recycleGrave(); // 무덤에 있는 카드들을 덱으로 이동

                if (this.deck.isEmpty()){ // 그래도 덱이 비어있다면 무덤도 비어있고 덱도 비어있는 엄청 안나오는 특이한 상황
                    System.out.println("⚠️ 더 이상 뽑을 카드가 없습니다!");
                    break;
                }
            }

            Card drawnCard = this.deck.remove(0); // 덱에서 제일 첫번째 카드를 뽑아옴
            this.hand.add(drawnCard); // 핸드에 추가
            newlyDrawn.add(drawnCard); // 뽑은 패를 놓아놓는 리스트에 추가
        }
        return newlyDrawn; //뽑은 목록 반환
    }


    public ArrayList<Card> discardHand(int[] indexes){ // 버릴 카드의 위치 indexes를 매개변수로 받음
        if (this.discardLeft <= 0){ // 버리기 기회를 이미 다쓴 상황
            System.out.println("⚠️ 패 버리기 기회를 모두 소모했습니다!");
            return new ArrayList<>(); // 빈배열 반환
        }

        this.discardLeft--; // 버리기 기회 소모
        Arrays.sort(indexes); // 버리는 인덱스들을 정렬함 이유는 0번 버리고 1번 버리면 0번 버리고 나서 1번이 0번 위치로 가기 때문
        for (int i = indexes.length-1; i>=0; i--){ //버리는 인덱스 배열의 길이 = 버릴 카드의 수 만큼 반복
            int idx = indexes[i]; // 버릴 카드의 위치 저장 변수
            Card trashedCard = this.hand.remove(idx); //버리는 카드 저장하는 객체 = 핸드에서 idx 번째를 뽑은 객체
            this.grave.add(trashedCard); // 이것을 무덤에 추가
        }

        int dropCount = indexes.length; // 버린 카드의 수  = 버리는 인덱스 길이
        ArrayList<Card> newlyDrawn = drawCard(dropCount); // 버려진 만큼 카드를 뽑아서 배열에 저장
        System.out.println("🗑️ 카드 " + dropCount + "장을 버리고 새로 뽑았습니다. (남은 기회: " + this.discardLeft + ")");

        return newlyDrawn; // 뽑은 카드들을 저장해 놓은 배열을 리턴
    }


    public JokboDto checkJokbo(ArrayList<Card> submittedCards){ // 제출하는 카드들의 모임인 배열이 매개변수
        int kwangCount=0, yulCount =0, ddiCount = 0, piCount = 0; // 각각의 타입 카운트 변수들
        ArrayList<Integer> kwangMonths = new ArrayList<>(); // 월 계산 변수
        ArrayList<Integer> yulMonths = new ArrayList<>(); // ""
        ArrayList<Integer> ddiMonths = new ArrayList<>(); // ""

        for(Card card : submittedCards){ //제출한 카드만큼 반복
            String type = card.getType(); // 카드의 타입 저장 지역 변수
            int month = card.getMonth(); // 카드의 월 저장 지역 변수

            if (type.equals("광")){ // 만약 카드의 타입이 광일경우
                kwangCount++; // 광의 카운트 증가
                kwangMonths.add(month); // 월계산 배열에 해당 월 넣기
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

    private int getCardScore(Card card) { // 카드의 기본 점수 가져오는 함수 매개변수는 카드 객체
        String type = card.getType(); // 카드객체의 type 값 저장하는 문자열 변수
        if (type.equals("광")) return 20;
        if (type.equals("열")) return 10;
        if (type.equals("띠")) return 5;
        return 1;
    }
    public int calculateScore(ArrayList<Card> submittedCards, JokboDto jokbo){ //점수 계산 하는 함수 매개변수는 제출된 카드 배열, 족보 계산해서 넘어온 족보
        int totalChips = jokbo.getJokboScore(); // 일단 족보의 기본점수를 총합 점수 변수에 저장
        int totalMult = jokbo.getJokboRatio(); // 일단 족보의 기본 배율을 종합 배율 변수에 저장

        for(Card card : submittedCards){ // 제출된 카드 객체를 돌며
            int cardScore = getCardScore(card); // 카드 점수는 객체의 카드 점수
            totalChips += cardScore; // 총합 점수에 카드 점수 더해주기
        }

        /*

        아이템 적용 구간 나중에 구현

        */


        int finalScore = totalChips * totalMult; // 최종 점수 계산

        System.out.println("🧮 계산 결과: (" + totalChips + " 칩) x (" + totalMult + " 배) = " + finalScore + "점");

        return finalScore;
    }


    public ResultDto submitHand(int[] indexes){ // 카드 제출 함수 제출하는 손패에서의 카드 인덱스 번호들을 배열로 매개변수로 받음
        if(this.submitLeft <= 0){ // 만약 제출 기회가 없다면
            return new ResultDto(false, "❌ 남은 기회가 없습니다!", "없음", 0, this.currentScore); //이 결과 객체 반환
        }

        Arrays.sort(indexes); // 제출한 카드들을 순서대로 정렬 0번을 빼고 1번을 빼면 0번을뺏을때 1번이 0번 자리로가서 1번을 빼지를 못해서
        ArrayList<Card> submittedCards = new ArrayList<>(); // 제출한 카드들을 저장할 변수

        for (int i = indexes.length-1; i >= 0; i--){ // 제출한 배열의 길이만큼 반복 == 카드수만큼 반복
            int idx = indexes[i]; // 인덱스값 가져오는 변수
            Card card = this.hand.remove(idx); // 패에서 카드를 가져와서 card 객체에 저장
            submittedCards.add(card); // 패에서 가져온 카드를 제출 배열에 삽입
        }

        JokboDto jokbo = checkJokbo(submittedCards); // 제출한 카드들의 배열로 족보 판별을 해서 저장
        if (jokbo == null){ // 판별한 족보가 null이라면 족보가 없는 경우임
            jokbo = new JokboDto(0, "족보 없음(꽝)", 1, 0);
        }

        int gainedScore = calculateScore(submittedCards, jokbo); // 얻을 점수 계산

        this.currentScore += gainedScore; // 현재 점수 갱신
        drawCard(submittedCards.size()); // 제출한 카드 수만큼 카드 뽑기 진행
        String msg = "🎉 [" + jokbo.getJokboName() + "] 완성! " + gainedScore + "점을 획득했습니다.";
        System.out.println(msg);

        return new ResultDto(true, msg, jokbo.getJokboName(), gainedScore, this.currentScore);

    }


    public boolean checkRoundClear(){
        if (this.currentScore >= this.targetScore) {
            System.out.println("🎉 [클리어] 목표 점수 " + this.targetScore + "점 달성! 다음 라운드로 갑니다.");
            return true;
        }

        return false;
    }

    public boolean isGameOver(){
        if (this.submitLeft <= 0 && this.currentScore < this.targetScore){
            System.out.println("💀 [게임 오버] 기회를 모두 사용햇는데 목표 점수에 도달하지 못했습니다...");
            return true;
        }
        return false;
    }

    public ArrayList<Card> getDeckInfo(){ // 현재 덱을 정렬해서 보여주는 함수

        ArrayList<Card> sortedDeck = new ArrayList<>(this.deck); //현재 덱 복사
        Collections.sort(sortedDeck, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                if (c1.getMonth() != c2.getMonth()){
                    return c1.getMonth() - c2.getMonth();
                }
                return c1.getName().compareTo(c2.getName());
            }
        });

        return sortedDeck;
    }

    public void resetRound(){ // 라운드 종료후 다음 라운드 세팅하는 함수
        if (!this.hand.isEmpty()){ // 손패에 카드가 있다면
            this.deck.addAll(this.hand); // 덱에 손패 카드들을 모두 더함
            this.hand.clear(); // 손패 비우기
        }

        if (!this.grave.isEmpty()){ // 무덤에 카드가 있다면
            this.deck.addAll(this.grave); // 덱에 무덤 카드들을 모두 더함
            this.grave.clear(); // 무덤 비우기
        }

        Collections.shuffle(this.deck); // 덱섞기
    }

}
