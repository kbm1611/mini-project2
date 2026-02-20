import controller.PlayController;
import model.dto.PlayerDto;
import service.GameSaveService;
import service.GameService;
import view.ShopView;
import view.UserView;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
//        UserView.getInstance().index();
//        ShopView.getInstance().printShopView();

        //게임 기능 테스트
        GameService gs = GameService.getInstance();
        PlayerDto player = PlayerDto.getInstance();
        player.setUser_no(); //유저번호
        player.setCurrent_round(); // 현재 라운드
        player.setCurrent_hp(); // 현재 체력
        player.setCurrent_discard(); // 현재 버리기 횟수
        player.setCurrent_grave(); // 현재 무덤
        player.setCurrent_hand(); // 현재 핸드
        player.setCurrent_money(); // 현재 돈
        player.setCurrent_score(); // 현재 점수
        player.setCard(); // 현재 카드
        player.setItem(); // 현재 아이템


    }
}