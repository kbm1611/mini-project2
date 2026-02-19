package service;

import model.dto.Card;
import model.dto.JokboDto;

import java.util.ArrayList;

public class ItemUseService {
        // 1. DB에서 아이템 번호 가져오기
        private ItemUseService(){}
        private static final ItemUseService instance = new ItemUseService();
        public  static ItemUseService getInstance(){return instance;}
        ArrayList< JokboDto > jokbo = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();

// 2. 가져온 아이템 분류(부적, 점괘)
// 2-1) 점괘은 즉시 적용
// 2-2) 부적은 계속 효과유지
// 3. 가지고 있는 아이템을 확인해서 사용시 적용



    // 아이템 번호 6 (조상님의 도움)(점괘)
    public void ancestorHelp(){
        // 다음 족보 배수를 +3배 추가한다


    }
    // 아이템 번호 7 (동작 그만)(점괘)
    public void moveStop(){
        //지금 패를 다음 판에도 유지한다
    }

    // 아이템 번호 8 (붉은 띠)(부적)
    public void redBand(){
        // 홍단 점수 +3배
       /* if() {
            jokbo.set(9, new JokboDto(12, "홍단", 3, 30));
            System.out.println("붉은 띠 적용완료");
        }*/
    }

    // 아이템 번호 9 (푸른 띠)(부적)
    public void blueBand(){
        // 청단 점수 +3배
        jokbo.set(10,new JokboDto(12, "청단", 3, 30));
    }

    // 아이템 번호 10 (아수라발발타)(점괘)
    public void magic(){
        // 목숨을 3개로 만든다.
    }
}


