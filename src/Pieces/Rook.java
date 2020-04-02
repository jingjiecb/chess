package Pieces;

public class Rook extends Piece {
    public Rook(int xp,int yp,boolean isWhite){
        white=isWhite;
        x=xp;
        y=yp;
        acitve=true;
        Symbol=isWhite? CP.wRook.getS(): CP.bRook.getS();
    }

    public boolean check(int xp,int yp){
        if (xp>7 || yp>7 || xp<0 || yp<0) return false;
        if (box.get(xp,yp)!=null && box.get(xp,yp).white==this.white) return false;

        if (xp!=x && yp!=y) return false;

        return pathIsFree(x,y,xp,yp);
    }

    public boolean moveable(){
        if (check(x+1,y)) return true;
        if (check(x-1,y)) return true;
        if (check(x,y+1)) return true;
        if (check(x,y-1)) return true;
        else return false;
    }
}
