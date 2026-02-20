package service;

import model.dao.GameLogDao;
import model.dto.GameLogDto;
import model.dto.PlayerDto;

import java.util.ArrayList;

public class RankService {
    private RankService(){}
    private static final RankService instance = new RankService();
    public static RankService getInstance(){
        return instance;
    }
    private GameLogDao gld = GameLogDao.getInstance();
    private PlayerDto player = PlayerDto.getInstance();

    public boolean AddGameLog(){
        int user_no = player.getUser_no();
        int final_score = player.getCurrent_score();
        int final_round = player.getCurrent_round();
        int final_money = player.getCurrent_money();
        return gld.AddGameLog(user_no, final_score, final_round, final_money); }
    public ArrayList<GameLogDto> printRanking(){
        return gld.printRanking();
    }
    public ArrayList<GameLogDto> printMyLog(int user_id){ return gld.printMyLog(user_id); }
}
