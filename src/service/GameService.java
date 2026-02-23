package service;

import constant.GameConst;
import model.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GameService {
    private static GameService instance = new GameService();
    public static GameService getInstance(){return instance;}

    private ArrayList<Card> deck;  // 남은 뽑기 덱
    private int targetScore;       // 이번 라운드 목표 점수

    public ArrayList<Card> getDeck() {return deck;}
    public void setDeck(ArrayList<Card> deck) {this.deck = deck;}
    public int getTargetScore() {return targetScore;}
    public void setTargetScore(int targetScore) {this.targetScore = targetScore;}

    public ArrayList<Card> getCurrent_hand() { return PlayerDto.getInstance().getCurrent_hand(); }
    public ArrayList<Card> getCurrent_grave() { return PlayerDto.getInstance().getCurrent_grave(); }

    private GameService() {
        this.deck = new ArrayList<>();
    }

    private RankService rs = RankService.getInstance();

    public void startNewGame() {
        PlayerDto player = PlayerDto.getInstance();

        player.setCurrent_round(1);
        player.setCurrent_score(0);
        player.setCurrent_money(0);
        player.setCurrent_hp(5);
        player.setCurrent_discard(3);

        player.setCard(new ArrayList<>(GameConst.BASIC_DECK));
        player.setItem(new ArrayList<>());
        player.setCurrent_hand(new ArrayList<>());  // 손패 초기화
        player.setCurrent_grave(new ArrayList<>()); // 무덤 초기화

        System.out.println("🆕 새로운 타짜의 길을 걷습니다. (기본 화투패 48장 지급 완료)");
    }

    public boolean loadGame() {
        PlayerDto player = PlayerDto.getInstance();

        if (player.getCard() != null && !player.getCard().isEmpty()) {
            System.out.println("💾 저장된 게임을 불러왔습니다! (" + player.getCurrent_round() + "라운드부터 시작)");
            return true;
        } else {
            System.out.println("🚫 저장된 데이터가 없습니다. 새로하기를 선택해 주세요.");
            return false;
        }
    }

    public RoundDto startRound(int roundNo){
        PlayerDto player = PlayerDto.getInstance();
        RoundDto boss = GameConst.ROUND_LIST.get(roundNo-1);
        this.targetScore = boss.getTargetScore();

        // 만약 손패에 카드가 이미 있다면 (이어하기)
        if (player.getCurrent_hand() != null && !player.getCurrent_hand().isEmpty()) {
            System.out.println("💾 저장된 손패와 무덤을 복구하여 라운드를 이어갑니다.");

            this.deck.clear();
            this.deck.addAll(player.getCard()); // 1. 전체 덱 복사

            // 손패에 있는 카드들 이름 기준으로 딱 1장씩만 덱에서 제거
            for (Card handCard : player.getCurrent_hand()) {
                for (int i = 0; i < this.deck.size(); i++) {
                    if (this.deck.get(i).getName().equals(handCard.getName())) {
                        this.deck.remove(i);
                        break; // 1장 지웠으면 멈추고 다음 손패 카드로 넘어감
                    }
                }
            }

            // 무덤에 있는 카드들 이름 기준으로 딱 1장씩만 덱에서 제거
            for (Card graveCard : player.getCurrent_grave()) {
                for (int i = 0; i < this.deck.size(); i++) {
                    if (this.deck.get(i).getName().equals(graveCard.getName())) {
                        this.deck.remove(i);
                        break;
                    }
                }
            }

            Collections.shuffle(this.deck);
            return boss;
        }

        //손패가 비어 있다면 (새로운 라운드)
        player.setCurrent_round(roundNo);
        player.setCurrent_score(0);
        player.setCurrent_hp(5);
        player.setCurrent_discard(3);

        this.deck.clear();
        this.deck.addAll(player.getCard());
        Collections.shuffle(this.deck);

        player.setCurrent_hand(new ArrayList<>());
        player.setCurrent_grave(new ArrayList<>());

        drawCard(8);

        return boss;
    }

    public void recycleGrave(){
        PlayerDto player = PlayerDto.getInstance();
        if(player.getCurrent_grave().isEmpty()){
            return;
        }
        this.deck.addAll(player.getCurrent_grave());
        player.getCurrent_grave().clear();
        Collections.shuffle(this.deck);

        System.out.println("\uD83D\uDD04 덱이 다 떨어져서 버린 패를 섞었습니다!");
    }

    public ArrayList<Card> drawCard(int count){
        PlayerDto player = PlayerDto.getInstance();
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
            player.getCurrent_hand().add(drawnCard); // PlayerDto의 손패에 추가!
            newlyDrawn.add(drawnCard);
        }
        return newlyDrawn;
    }

    public ArrayList<Card> discardHand(int[] indexes){
        PlayerDto player = PlayerDto.getInstance();
        if (player.getCurrent_discard() <= 0){
            System.out.println("⚠️ 패 버리기 기회를 모두 소모했습니다!");
            return new ArrayList<>();
        }

        player.setCurrent_discard(player.getCurrent_discard() - 1); // 기회 감소
        Arrays.sort(indexes);
        ArrayList<Card> trashedCards = new ArrayList<>();
        for (int i = indexes.length-1; i>=0; i--){
            int idx = indexes[i];
            Card trashedCard = player.getCurrent_hand().remove(idx); // PlayerDto에서 제거
            trashedCards.add(trashedCard);
        }

        ItemUseService.getInstance().revertCardIfNeeded(trashedCards);
        player.getCurrent_grave().addAll(trashedCards); // PlayerDto 무덤에 추가

        int dropCount = indexes.length;
        ArrayList<Card> newlyDrawn = drawCard(dropCount);
        System.out.println("🗑️ 카드 " + dropCount + "장을 버리고 새로 뽑았습니다. (남은 기회: " + player.getCurrent_discard() + ")");

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

        if (kwangCount == 5) return GameConst.JOKBO_LIST.get(0);    // 5광
        if (kwangCount == 4) return GameConst.JOKBO_LIST.get(1);    // 4광
        if (kwangCount == 3) return GameConst.JOKBO_LIST.get(2);    // 3광
        if (ddiCount == 5) return GameConst.JOKBO_LIST.get(3);      // 띠 모음
        if (yulCount == 5) return GameConst.JOKBO_LIST.get(4);      // 멍텅구리
        if (kwangMonths.contains(3) && kwangMonths.contains(8)) return GameConst.JOKBO_LIST.get(5);     //38광땡
        if (kwangMonths.contains(1) && kwangMonths.contains(8)) return GameConst.JOKBO_LIST.get(6);     // 18광땡
        if (kwangMonths.contains(1) && kwangMonths.contains(3)) return GameConst.JOKBO_LIST.get(7);     // 13광땡
        if (yulMonths.contains(2) && yulMonths.contains(4) && yulMonths.contains(8)) return GameConst.JOKBO_LIST.get(8);    // 고도리
        if (ddiMonths.contains(1) && ddiMonths.contains(2) && ddiMonths.contains(3)) return GameConst.JOKBO_LIST.get(9);    // 홍단
        if (ddiMonths.contains(6) && ddiMonths.contains(9) && ddiMonths.contains(10)) return GameConst.JOKBO_LIST.get(10);  // 청단
        if (ddiMonths.contains(4) && ddiMonths.contains(5) && ddiMonths.contains(7)) return GameConst.JOKBO_LIST.get(11);   // 초단
        if (piCount == 5) return GameConst.JOKBO_LIST.get(12);      // 피바다

        return null;
    }

    private int getCardScore(Card card) {
        String type = card.getType();
        if (type.equals("광")) {
            int multi = ItemUseService.getInstance().getKwangMultiplier();
            if (multi > 1) {
                System.out.println("✨ [광끼 부적 발동] 번쩍! '광' 카드의 점수가 2배가 됩니다! (20점 ➡️ 40점)");
            }

            return 20 * multi;
        }
        if (type.equals("열")) return 10;
        if (type.equals("띠")) return 5;
        return 1;
    }

    public int calculateScore(ArrayList<Card> submittedCards, JokboDto jokbo){
        int totalChips = jokbo.getJokboScore();
        int totalMult = jokbo.getJokboRatio();

        for(Card card : submittedCards){
            int cardScore = getCardScore(card);
            totalChips += cardScore;
        }

        totalChips += ItemUseService.getInstance().getAnimalBoostScore(submittedCards);

        totalMult += ItemUseService.getInstance().getAncestorMultiplier();
        totalMult += ItemUseService.getInstance().redBand(jokbo);
        totalMult += ItemUseService.getInstance().blueBand(jokbo);

        int finalScore = totalChips * totalMult;

        System.out.println("🧮 계산 결과: (" + totalChips + " 칩) x (" + totalMult + " 배) = " + finalScore + "점");
        return finalScore;
    }

    public ResultDto submitHand(int[] indexes){
        PlayerDto player = PlayerDto.getInstance();

        if(player.getCurrent_hp() <= 0){
            return new ResultDto(false, "❌ 남은 기회가 없습니다!", "없음", 0, player.getCurrent_score());
        }

        Arrays.sort(indexes);
        ArrayList<Card> submittedCards = new ArrayList<>();

        // 7번 아이템(동작 그만)이 발동 중인지 먼저 확인
        boolean isKeepHandActive = ItemUseService.getInstance().getItemstate();

        if (isKeepHandActive) {
            System.out.println("🛑 [동작 그만] 효과 발동! 낸 패가 소모되지 않고 손에 그대로 남습니다!");
            // 아이템 발동 중: remove가 아니라 get으로 '복사'만 해옴 (뒤에서부터 뺄 필요도 없음)
            for (int i = 0; i < indexes.length; i++){
                int idx = indexes[i];
                Card card = player.getCurrent_hand().get(idx);
                submittedCards.add(card);
            }
        } else {
            // 평소: 손패에서 진짜로 없앱니다 (인덱스 밀림 방지를 위해 뒤에서부터 remove)
            for (int i = indexes.length-1; i >= 0; i--){
                int idx = indexes[i];
                Card card = player.getCurrent_hand().remove(idx);
                submittedCards.add(card);
            }
        }

        JokboDto jokbo = checkJokbo(submittedCards);
        if (jokbo == null){
            jokbo = new JokboDto(0, "족보 없음(꽝)", 1, 0);
        }

        int gainedScore = calculateScore(submittedCards, jokbo);


        player.setCurrent_score(player.getCurrent_score() + gainedScore);
        String msg = "🎉 [" + jokbo.getJokboName() + "] 완성! " + gainedScore + "점을 획득했습니다.";


        if (!isKeepHandActive) {
            drawCard(submittedCards.size());
        }

        player.setCurrent_hp(player.getCurrent_hp() - 1); // 기회 깎기


        ItemUseService.getInstance().revertCardIfNeeded(submittedCards);


        if (!isKeepHandActive) {
            player.getCurrent_grave().addAll(submittedCards);
        }

        return new ResultDto(true, msg, jokbo.getJokboName(), gainedScore, player.getCurrent_score());
    }

    public boolean checkRoundClear() {
        PlayerDto player = PlayerDto.getInstance();

        // 내 점수가 타겟 점수보다 크거나 같은지 확인
        if (player.getCurrent_score() >= this.targetScore) {
            System.out.println("🎉 [클리어] 목표 점수 " + this.targetScore + "점 달성! 다음 라운드로 갑니다.");

            int baseMoney = 100 + (player.getCurrent_round() * 50);
            int bonusMoney = player.getCurrent_hp() * 20; // 남은 HP 기준
            int interestMoney = (int)(player.getCurrent_money() * 0.1);
            if (interestMoney > 250) interestMoney = 250;

            int totalEarned = baseMoney + bonusMoney + interestMoney;
            totalEarned = ItemUseService.getInstance().applyWealthAmulet(totalEarned);

            int newBalance = player.getCurrent_money() + totalEarned;
            player.setCurrent_money(newBalance);

            view.PlayView.getInstance().printClearReceipt(
                    player.getCurrent_round(),
                    baseMoney,
                    bonusMoney,
                    interestMoney,
                    totalEarned,
                    newBalance
            );
            return true;
        }

        return false;
    }

    public boolean isGameOver() {
        PlayerDto player = PlayerDto.getInstance();

        // 기회가 0 이하인데, 점수가 안 될 때
        if (player.getCurrent_hp() <= 0 && player.getCurrent_score() < this.targetScore) {
            System.out.println("💀 [게임 오버] 기회를 모두 사용했는데 목표 점수에 도달하지 못했습니다...");
            rs.AddGameLog();
            return true;
        }
        return false;
    }

    public ArrayList<Card> getDeckInfo(){
        ArrayList<Card> sortedDeck = new ArrayList<>(this.deck);
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

    public void resetRound(){
        PlayerDto player = PlayerDto.getInstance();
        ItemUseService.getInstance().clearBuff();

        if (!player.getCurrent_hand().isEmpty()){
            this.deck.addAll(player.getCurrent_hand());
            player.getCurrent_hand().clear();
        }

        if (!player.getCurrent_grave().isEmpty()){
            this.deck.addAll(player.getCurrent_grave());
            player.getCurrent_grave().clear();
        }

        Collections.shuffle(this.deck);
    }
}