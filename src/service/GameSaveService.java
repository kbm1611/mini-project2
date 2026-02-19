package service;

import constant.GameConst;
import model.dao.GameSave;
import model.dao.ItemDao;
import model.dto.Card;
import model.dto.Item;
import model.dto.PlayerDto;
import model.dto.SaveFileDto;

import java.util.ArrayList;
import java.util.Arrays;

public class GameSaveService {
    private GameSaveService(){}
    private static final GameSaveService instance = new GameSaveService();
    public static GameSaveService getInstance(){ return instance; }
    private GameSave gs = GameSave.getInstance();
    private PlayerDto player = PlayerDto.getInstance();
    private ItemDao itd = ItemDao.getInstance();


    //저장하는 기능 -> 게임종료 시 저장됨
    public void saveGame(){
        ArrayList<Card> cardList = player.getCard();
        ArrayList<Item> itemList = player.getItem();
        String cardsStr = "";
        String itemsStr = "";

        int user_no = player.getUser_no();
        int current_round = player.getCurrent_round();
        int current_hp = player.getCurrent_hp();
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

        boolean savefile = gs.saveGame(user_no, current_round, current_hp, current_money, current_score, cardsStr, itemsStr); //저장
    }
    //불러오기 기능 -> 로그인 시 불러옴
    public void loadGame(int user_no){
        SaveFileDto loadfile = gs.loadGame(user_no); //유저정보 DB에서 가져오기
        // playerDto에 현재 플레이어 정보 넣기
        player.setCurrent_round(loadfile.getCurrent_round());
        player.setCurrent_hp(loadfile.getCurrent_hp());
        player.setCurrent_money(loadfile.getCurrent_money());
        player.setCurrent_score(loadfile.getCurrent_score());

        //card, item은 파싱해서 객체로 만들어줘야 함.

        //신규 유저일 경우 비교
        String cardsStr;
        String itemsStr;
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

        ArrayList<Card> cardList = new ArrayList<>();
        ArrayList<Item> itemList = new ArrayList<>();

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
