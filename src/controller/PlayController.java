package controller;

import service.GameService;
import view.PlayView;

import java.util.Scanner;

public class PlayController {
    private PlayController(){}
    private static final PlayController instance = new PlayController();
    public static PlayController getInstance(){return instance;}
    private GameService GS = GameService.getInstance();
    private PlayView pv = PlayView.getInstance();

}
