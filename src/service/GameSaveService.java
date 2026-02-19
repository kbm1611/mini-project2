package service;

import constant.GameConst;
import model.dao.GameSave;
import model.dao.ItemDao;
import model.dto.Card;
import model.dto.Item;
import model.dto.PlayerDto;
import model.dto.SaveFileDto;

import java.util.ArrayList;

public class GameSaveService {
    private GameSaveService(){}
    private static final GameSaveService instance = new GameSaveService();
    public static GameSaveService getInstance(){ return instance; }
    private GameSave gs = GameSave.getInstance();
    private PlayerDto player = PlayerDto.getInstance();
    private ItemDao itd = ItemDao.getInstance();


    //저장하는 기능 -> 게임종료(로그아웃, 플레이 중 저장하고 나가기 선택) 시 저장됨
    public boolean saveGame(){
        ArrayList<Card> cardList = player.getCard();
        ArrayList<Item> itemList = player.getItem();
        String cardsStr = "";
        String itemsStr = "";

        int user_no = player.getUser_no();
        int current_round = player.getCurrent_round();
        int current_hp = player.getCurrent_hp();
        int current_discard = player.getCurrent_discard();
        int current_money = player.getCurrent_money();
        int current_score = player.getCurrent_score();

        if( cardList != null && !cardList.isEmpty() ){ //cardList가 null이 아니고 동시에 비어있지 않을 때
            for(Card card : cardList ){
                if(cardsStr.isEmpty()) {
                    cardsStr += card.getCard_no(); // 첫 번째는 콤마 없이
                } else {
                    cardsStr += "," + card.getCard_no(); // 두 번째부터 콤마 추가
                }
            }
        }


        if( itemList != null && !itemList.isEmpty() ){ //itemList가 null이 아니고 동시에 비어있지도 않을 때
            for(Item item : itemList){
                if(itemsStr.isEmpty()){
                    itemsStr += item.getItem_no(); // 첫 번째는 콤마 없이
                }else{
                    itemsStr += "," + item.getItem_no(); // 두 번째부터 콤마 추가
                }
            }
        }

        boolean savefile = gs.saveGame(user_no, current_round, current_hp, current_discard, current_money, current_score, cardsStr, itemsStr); //저장
        return savefile;
    }

    //불러오기 기능 -> 로그인 시 불러옴
    public void loadGame(int user_no){
        SaveFileDto loadfile = gs.loadGame(user_no); //유저정보 DB에서 가져오기
        // playerDto에 현재 플레이어 정보 넣기
        if(loadfile == null){ //처음 사용자라면
            player.setCurrent_round(1); //시작 라운드
            player.setCurrent_hp(3);    // 시작 체력
            player.setCurrent_discard(3); //시작 버리기 횟수
            player.setCurrent_money(0); // 시작 돈
            player.setCurrent_score(0); // 시작 점수
            player.setCard(new ArrayList<>(GameConst.BASIC_DECK)); //기본 덱 지급
            player.setItem(new ArrayList<>()); // 빈 아이템 창 지급
            return; //신규 유저 세팅 종료
        }

        // 기존유저의 경우
        player.setCurrent_round(loadfile.getCurrent_round());
        player.setCurrent_hp(loadfile.getCurrent_hp());
        player.setCurrent_discard(loadfile.getCurrent_discard());
        player.setCurrent_money(loadfile.getCurrent_money());
        player.setCurrent_score(loadfile.getCurrent_score());

        //card, item은 파싱해서 객체로 만들어줘야 함.
        String cardsStr;
        String itemsStr;
        ArrayList<Card> cardList = new ArrayList<>();
        ArrayList<Item> itemList = new ArrayList<>();

        if(loadfile.getCards() == null){
            cardsStr = "";
        }else{
            cardsStr = loadfile.getCards().replaceAll(" ", ""); //공백 제거
        }
        if(loadfile.getItems() == null){
            itemsStr = "";
        }else{
            itemsStr = loadfile.getItems().replaceAll(" ", ""); //공백 제거
        }

        if( cardsStr.isBlank() ){ //카드리스트가 비어있다면 기본덱 제공
            cardList = new ArrayList<>(GameConst.BASIC_DECK);
        }else{
            for(String cardNo : cardsStr.split(",")){
                int no = Integer.parseInt(cardNo);
                cardList.add(GameConst.BASIC_DECK.get(no-1));
            }
        }
        player.setCard(cardList); //카드리스트 추가

        if( !itemsStr.isBlank() ){
            for(String itemNo : itemsStr.split(",")){
                int no = Integer.parseInt(itemNo);
                itemList.add(itd.getItem(no));
            }
        }
        player.setItem(itemList); //아이템리스트 추가
    }
}
