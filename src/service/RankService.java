package service;

import model.dao.GameLogDao;
import model.dto.GameLogDto;

import java.util.ArrayList;

public class RankService {
    private RankService(){}
    private static final RankService instance = new RankService();
    public static RankService getInstance(){
        return instance;
    }
    private GameLogDao gld = GameLogDao.getInstance();

    public ArrayList<GameLogDto> printRanking(){
        return gld.printRanking();
    }
    public ArrayList<GameLogDto> printMyLog(){
        return gld.printMyLog();
    }
}
