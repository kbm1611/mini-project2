package service;

import model.dto.Card;
import model.dto.Item;
import model.dto.JokboDto;

import java.util.ArrayList;
import model.dto.PlayerDto;
import java.util.Random;

public class ItemUseService {
        // 1. DB에서 아이템 번호 가져오기
        private ItemUseService() { }
        private static final ItemUseService instance = new ItemUseService();
        public static ItemUseService getInstance() { return instance; }
        ArrayList<JokboDto> jokbo = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();

        private PlayerDto player = PlayerDto.getInstance();
        private GameService gameService = GameService.getInstance();

        private Card buffedCard = null;
        private String originalType = "";

        //[공통] 유저가 특정 번호의 아이템을 가지고 있는지 확인하는 메서드
        public boolean hasItem(int itemId) {
                if (player.getItem() == null) return false;
                for (Item item : player.getItem()) {
                        if (item.getItem_no() == itemId) {
                                return true;
                        }
                }
                return false;
        }

        public boolean useRevelationOfSpirit() {
                ArrayList<Card> hand = gameService.getHand();

                // 현재 손패에서 '광'이 아닌 카드만 추려냄
                ArrayList<Card> nonKwangCards = new ArrayList<>();
                for (Card c : hand) {
                        if (!c.getType().equals("광")) {
                                nonKwangCards.add(c);
                        }
                }

                // 광이 아닌 카드가 하나도 없다면? (이미 다 광이거나 패가 없음)
                if (nonKwangCards.isEmpty()) {
                        System.out.println("⚠️ 손패에 변환할 수 있는 카드(광이 아닌 카드)가 없습니다!");
                        return false; // 사용 취소 (아이템 소모 안 함)
                }

                // 2. 광이 아닌 카드 중에서 랜덤하게 1장 뽑기
                Random rand = new Random();
                Card targetCard = nonKwangCards.get(rand.nextInt(nonKwangCards.size()));

                // 3. 원래 타입을 기억해두고 '광'으로 임시 변환
                buffedCard = targetCard;
                originalType = targetCard.getType();
                targetCard.setType("광");

                System.out.println("\n✨ [신령님의 계시 발동!] 손패의 카드가 찬란하게 빛납니다...");
                System.out.println("👉 이번 턴에만 [" + targetCard.getMonth() + "월 " + originalType + "] 카드가 [광]으로 취급됩니다!");
                return true;
        }

        public void revertCardIfNeeded(ArrayList<Card> movedCards) {
                if (buffedCard == null) return; // 변환했던 카드가 없으면 패스

                // 제출하거나 버린 카드 목록(movedCards)에 내가 변환했던 카드가 들어있다면?
                if (movedCards.contains(buffedCard)) {
                        buffedCard.setType(originalType); // 원래 타입(피, 띠, 열)으로 원상복구!
                        buffedCard = null; // 기억 지우기
                        originalType = "";
                }
        }

        public void clearBuff() {
                if (buffedCard != null) {
                        buffedCard.setType(originalType);
                        buffedCard = null;
                        originalType = "";
                }
        }

        public boolean useBottomDealing() {
                ArrayList<Card> hand = gameService.getHand();
                if (hand.isEmpty()) {
                        System.out.println("⚠️ 버릴 손패가 없습니다!");
                        return false;
                }

                int handSize = hand.size();

                // 손패를 모두 무덤으로
                gameService.getGrave().addAll(hand);
                hand.clear();

                // 버린 만큼 새로 덱에서 뽑기
                gameService.drawCard(handSize);

                System.out.println("\n🃏 [밑장 빼기 발동!] 손패 " + handSize + "장을 모두 버리고 은밀하게 새로 뽑았습니다!");
                return true;
        }

        public int getAnimalBoostScore(ArrayList<Card> submittedCards) {
                if (!hasItem(1)) return 0; // 아이템 없으면 0점

                int bonus = 0;
                for (Card card : submittedCards) {
                        if (card.getType().equals("열")) {
                                bonus += 10;
                        }
                }
                if (bonus > 0) {
                        System.out.println("🐯 [호랑이 기운 발동] 으르렁! '열' 카드 보너스 +" + bonus + "점!");
                }
                return bonus;
        }

        public int applyWealthAmulet(int totalMoney) {
                if (!hasItem(2)) return totalMoney; // 아이템 없으면 원래 돈 그대로

                int boostedMoney = (int) (totalMoney * 1.5);
                System.out.println("💸 [재물 부적 발동] 엽전이 복사됩니다! 획득 금액 1.5배 증가!");
                return boostedMoney;
        }

        public int getKwangMultiplier() {
                if (hasItem(3)) {
                        return 2; // 아이템 있으면 광 점수 2배!
                }
                return 1; // 없으면 평범하게 1배
        }
        //==============================================

    // 아이템 번호 6 (조상님의 도움)(점괘)
    public void ancestorHelp(){
        // 다음 족보 배수를 +3배 추가한다
        if(!hasItem(6)){
            return;
        }

    }
    // 아이템 번호 7 (동작 그만)(점괘)
    public void moveStop(){
        //지금 패를 다음 판에도 유지한다
    }

    // 아이템 번호 8 (붉은 띠)(부적)
    public void redBand(ArrayList<JokboDto> applyJokbo){
        // 홍단 점수 +3배
        if(hasItem(8)) {

            jokbo.set(9, new JokboDto(12, "홍단", 6, 30));
            System.out.println("[붉은 띠 발동] 홍단 점수가 +3배 ");
        }else if(!hasItem(8)){
            jokbo.set(9, new JokboDto(12, "홍단", 3, 30));
        }
    }

    // 아이템 번호 9 (푸른 띠)(부적)
    public void blueBand() {
        // 청단 점수 +3배
        if (hasItem(9)) {
            jokbo.set(10, new JokboDto(12, "청단", 6, 30));
            System.out.println(" [푸른 띠 발동] 청단 점수가 +3배");
        }else if(!hasItem(8)){
            jokbo.set(10, new JokboDto(12, "청단", 3, 30));
        }
    }
    /*
    // 아이템 번호 10 (아수라발발타)(점괘)
    public boolean magic(){
        // 목숨을 3개로 만든다.
        if (player.getCurrent_hp() != 3){
           player.getCurrent_hp() = 3;
        }
    }*/
}


