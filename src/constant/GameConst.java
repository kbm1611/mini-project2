package constant;

import model.dto.Card;
import model.dto.JokboDto;
import model.dto.RoundDto;

import java.util.ArrayList;

public class GameConst {
    public static final ArrayList<Card> BASIC_DECK = new ArrayList<>();
    static {
        // 1월 (송학)
        BASIC_DECK.add(new Card(1, 1, "광", 20, "1월_광"));
        BASIC_DECK.add(new Card(2, 1, "띠", 5, "1월_홍단"));
        BASIC_DECK.add(new Card(3, 1, "피", 1, "1월_피"));
        BASIC_DECK.add(new Card(4, 1, "피", 1, "1월_피"));

        // 2월 (매조)
        BASIC_DECK.add(new Card(5, 2, "열", 10, "2월_열"));
        BASIC_DECK.add(new Card(6, 2, "띠", 5, "2월_홍단"));
        BASIC_DECK.add(new Card(7, 2, "피", 1, "2월_피"));
        BASIC_DECK.add(new Card(8, 2, "피", 1, "2월_피"));

        // 3월 (벚꽃)
        BASIC_DECK.add(new Card(9, 3, "광", 20, "3월_광"));
        BASIC_DECK.add(new Card(10, 3, "띠", 5, "3월_홍단"));
        BASIC_DECK.add(new Card(11, 3, "피", 1, "3월_피"));
        BASIC_DECK.add(new Card(12, 3, "피", 1, "3월_피"));

        // 4월 (흑싸리)
        BASIC_DECK.add(new Card(13, 4, "열", 10, "4월_열"));
        BASIC_DECK.add(new Card(14, 4, "띠", 5, "4월_초단"));
        BASIC_DECK.add(new Card(15, 4, "피", 1, "4월_피"));
        BASIC_DECK.add(new Card(16, 4, "피", 1, "4월_피"));

        // 5월 (난초)
        BASIC_DECK.add(new Card(17, 5, "열", 10, "5월_열"));
        BASIC_DECK.add(new Card(18, 5, "띠", 5, "5월_초단"));
        BASIC_DECK.add(new Card(19, 5, "피", 1, "5월_피"));
        BASIC_DECK.add(new Card(20, 5, "피", 1, "5월_피"));

        // 6월 (모란)
        BASIC_DECK.add(new Card(21, 6, "열", 10, "6월_열"));
        BASIC_DECK.add(new Card(22, 6, "띠", 5, "6월_청단"));
        BASIC_DECK.add(new Card(23, 6, "피", 1, "6월_피"));
        BASIC_DECK.add(new Card(24, 6, "피", 1, "6월_피"));

        // 7월 (홍싸리)
        BASIC_DECK.add(new Card(25, 7, "열", 10, "7월_열"));
        BASIC_DECK.add(new Card(26, 7, "띠", 5, "7월_초단"));
        BASIC_DECK.add(new Card(27, 7, "피", 1, "7월_피"));
        BASIC_DECK.add(new Card(28, 7, "피", 1, "7월_피"));

        // 8월 (공산)
        BASIC_DECK.add(new Card(29, 8, "광", 20, "8월_광"));
        BASIC_DECK.add(new Card(30, 8, "열", 10, "8월_열"));
        BASIC_DECK.add(new Card(31, 8, "피", 1, "8월_피"));
        BASIC_DECK.add(new Card(32, 8, "피", 1, "8월_피"));

        // 9월 (국화)
        BASIC_DECK.add(new Card(33, 9, "열", 10, "9월_열"));
        BASIC_DECK.add(new Card(34, 9, "띠", 5, "9월_청단"));
        BASIC_DECK.add(new Card(35, 9, "피", 1, "9월_피"));
        BASIC_DECK.add(new Card(36, 9, "피", 1, "9월_피"));

        // 10월 (단풍)
        BASIC_DECK.add(new Card(37, 10, "열", 10, "10월_열"));
        BASIC_DECK.add(new Card(38, 10, "띠", 5, "10월_청단"));
        BASIC_DECK.add(new Card(39, 10, "피", 1, "10월_피"));
        BASIC_DECK.add(new Card(40, 10, "피", 1, "10월_피"));

        // 11월 (오동)
        BASIC_DECK.add(new Card(41, 11, "광", 20, "11월_광"));
        BASIC_DECK.add(new Card(42, 11, "열", 10, "11월_열"));
        BASIC_DECK.add(new Card(43, 11, "띠", 5, "11월_띠"));
        BASIC_DECK.add(new Card(44, 11, "피", 2, "11월_쌍피"));

        // 12월 (비)
        BASIC_DECK.add(new Card(45, 12, "광", 20, "12월_광"));
        BASIC_DECK.add(new Card(46, 12, "피", 1, "12월_피"));
        BASIC_DECK.add(new Card(47, 12, "피", 1, "12월_피"));
        BASIC_DECK.add(new Card(48, 12, "피", 2, "12월_쌍피"));
    }

    public static final ArrayList<JokboDto> JOKBO_LIST = new ArrayList<>();
    static {
        JOKBO_LIST.add(new JokboDto(1, "오광", 15, 200));       // get(0)
        JOKBO_LIST.add(new JokboDto(2, "사광", 10, 120));       // get(1)
        JOKBO_LIST.add(new JokboDto(3, "삼광", 6, 60));         // get(2)
        JOKBO_LIST.add(new JokboDto(4, "띠 모음", 4, 40));      // get(3)
        JOKBO_LIST.add(new JokboDto(5, "멍텅구리", 4, 40));     // get(4)
        JOKBO_LIST.add(new JokboDto(6, "38광땡", 8, 150));      // get(5)
        JOKBO_LIST.add(new JokboDto(7, "18광땡", 6, 100));      // get(6)
        JOKBO_LIST.add(new JokboDto(8, "13광땡", 5, 80));       // get(7)
        JOKBO_LIST.add(new JokboDto(9, "고도리", 5, 70));       // get(8)
        JOKBO_LIST.add(new JokboDto(10, "홍단", 3, 30));        // get(9)
        JOKBO_LIST.add(new JokboDto(11, "청단", 3, 30));        // get(10)
        JOKBO_LIST.add(new JokboDto(12, "초단", 3, 30));        // get(11)
        JOKBO_LIST.add(new JokboDto(13, "피바다", 2, 20));      // get(12)
    }

    public static final ArrayList<RoundDto> ROUND_LIST = new ArrayList<>();
    static {
        ROUND_LIST.add(new RoundDto(1, "호구", 100));
        ROUND_LIST.add(new RoundDto(2, "곽철용", 1200));
        ROUND_LIST.add(new RoundDto(3, "광팔이", 2500));
        ROUND_LIST.add(new RoundDto(4, "정마담", 5000));
        ROUND_LIST.add(new RoundDto(5, "평경장", 10000));
        ROUND_LIST.add(new RoundDto(6, "짝귀", 20000));
        ROUND_LIST.add(new RoundDto(7, "아귀", 30000));
        ROUND_LIST.add(new RoundDto(8, "고니", 50000));
        ROUND_LIST.add(new RoundDto(9,"도박왕",Integer.MAX_VALUE));
    }
    public static void jokboView(){
        System.out.println("\n=======================================");
        System.out.println("오광 : 광이 5장 (족보점수 200점, 배율 15배)");
        System.out.println("사광 : 광이 4장 (족보점수 120점, 배율 10배)");
        System.out.println("삼광 : 광이 3장 (족보점수 60점, 배율 6배)");
        System.out.println("띠 모음 : 띠가 5장  (족보점수 40점, 배율 4배)");
        System.out.println("멍텅구리 : 열이 5장 (족보점수 40점, 배율 4배)");
        System.out.println("38광땡 : 3광 + 8광 (족보점수 150점, 배율 8배)");
        System.out.println("18광땡 : 1광 + 8광 (족보점수 100점, 배율 6배)");
        System.out.println("13광땡 : 1광 + 3광 (족보점수 80점, 배율 5배)");
        System.out.println("고도리 : 2·4·8월 열 (족보점수 70점, 배율 5배)");
        System.out.println("홍단 : 홍단 3장 (족보점수 30점, 배율 3배)");
        System.out.println("청단 : 청단 3장 (족보점수 30점, 배율 3배)");
        System.out.println("초단 : 초단 3장 (족보점수 30점, 배율 3배)");
        System.out.println("피바다 : 피5장 (족보점수 20점, 배율 2배)");
        System.out.println("=======================================");
    }
}

