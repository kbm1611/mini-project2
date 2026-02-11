package controller;

import model.dto.GameLogDto;
import service.RankService;

import java.util.ArrayList;

public class RankController {
    private RankController(){}
    private static final RankController instance = new RankController();
    public static RankController getInstance(){
        return instance;
    }
    private RankService rsv = RankService.getInstance();

    public ArrayList<GameLogDto> printRanking(){
        return rsv.printRanking();
    }
}
