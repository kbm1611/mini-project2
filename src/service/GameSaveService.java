package service;

public class GameSaveService {
    private GameSaveService(){}
    private static final GameSaveService instance = new GameSaveService();
    public static GameSaveService getInstance(){
        return instance;
    }
}
