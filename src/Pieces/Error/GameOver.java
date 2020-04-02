package Pieces.Error;

public class GameOver extends Exception{
    boolean isWhiteWin;
    public GameOver(boolean isWhite){
        isWhiteWin=isWhite;
    }
    public boolean getWinner(){
        return isWhiteWin;
    }
}
