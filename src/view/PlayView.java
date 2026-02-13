package view;

import java.util.Scanner;

public class PlayView {
    private PlayView(){}
    private static PlayView instance = new PlayView();
    public static PlayView getInstance(){return instance;}
    private Scanner scan = new Scanner(System.in);
}
