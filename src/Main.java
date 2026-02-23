import constant.GameConst;
import model.dto.Card;
import model.dto.Item;
import model.dto.PlayerDto;
import service.GameService;
import view.UserView;

import java.util.ArrayList;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        UserView.getInstance().index();
//        ShopView.getInstance().printShopView();

//        //게임 기능 테스트
//        GameService gs = GameService.getInstance();
//        PlayerDto player = PlayerDto.getInstance();
//        player.setUser_no(1); //유저번호
//        player.setCurrent_round(2); // 현재 라운드
//        player.setCurrent_hp(3); // 현재 내는 횟수
//        player.setCurrent_discard(3); // 현재 버리기 횟수
//        ArrayList<Card> grave = new ArrayList<>();
//        ArrayList<Card> hand= new ArrayList<>();
//        ArrayList<Card> card = new ArrayList<>(GameConst.BASIC_DECK);
//
//        hand.add(new Card(17, 5, "열", 10, "5월_열"));
//        hand.add(new Card(18, 5, "띠", 5, "5월_초단"));
//        hand.add(new Card(28, 7, "피", 1, "7월_피"));
//        hand.add(new Card(35, 9, "피", 1, "9월_피"));
//        hand.add(new Card(41, 11, "광", 20, "11월_광"));
//        hand.add(new Card(45, 12, "광", 20, "12월_광"));
//        hand.add(new Card(1, 1, "광", 20, "1월_광"));
//        hand.add(new Card(6, 2, "띠", 5, "2월_홍단"));
//
//        ArrayList<Item> item = new ArrayList<>();
//        item.add(new Item(2, "재물 부적", "부적", 200, "판이 끝날 때 마다 획득 엽전 1.5배", "PASSIVE_MONEY_UP"));
//
//        player.setCurrent_grave(grave); // 현재 무덤
//        player.setCurrent_hand(hand); // 현재 핸드
//        player.setCurrent_money(120); // 현재 돈
//        player.setCurrent_score(370); // 현재 점수
//        player.setCard(card); // 현재 카드
//        player.setItem(item); // 현재 아이템
    }
}