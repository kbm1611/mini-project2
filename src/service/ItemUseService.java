package service;

public class ItemUseService {
        // 1. DB에서 아이템 번호 가져오기
        private static ItemUseService instance = new ItemUseService();
        public  static ItemUseService getInstance(){return instance;}
        }

// 2. 가져온 아이템 분류(부적, 점괘)
// 2-1) 점괘은 즉시 적용
// 2-2) 부적은 계속 효과유지
// 3. 가지고 있는 아이템을 확인해서 사용시 적용