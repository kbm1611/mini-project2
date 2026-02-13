package service;

import model.dao.GameLogDao;

public class GameSaveService {
    private GameSaveService(){}
    private static final GameSaveService instance = new GameSaveService();
    public static GameSaveService getInstance(){ return instance; }
    private GameLogDao gld = GameLogDao.getInstance();



    //저장하는 기능

    //불러오기 기능
}
