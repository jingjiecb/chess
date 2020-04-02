package Pieces.Error;

public class LevelUp extends Exception {
    public int x,y;
    public boolean isWhite;
    public LevelUp(int xp,int yp,boolean w){
        x=xp;
        y=yp;
        isWhite=w;
    }
}
