package Pieces;

public class Knight extends Piece{
    public Knight(int xp,int yp,boolean isWhite){
        white=isWhite;
        x=xp;
        y=yp;
        acitve=true;
        Symbol=isWhite? CP.wKnight.getS(): CP.bKnight.getS();
    }

    public boolean check(int xp,int yp){
        if (xp>7 || yp>7 || xp<0 || yp<0) return false;
        if (box.get(xp,yp)!=null && box.get(xp,yp).white==this.white) return false;

        if ( !(Math.abs(x-xp)==2 && Math.abs(y-yp)==1) && !(Math.abs(x-xp)==1 && Math.abs(y-yp)==2) ) return false;

        return true;
    }

    public boolean moveable(){
        if (check(x+2,y+1)) return true;
        if (check(x+2,y-1)) return true;
        if (check(x-2,y+1)) return true;
        if (check(x-2,y-1)) return true;
        if (check(x+1,y-2)) return true;
        if (check(x-1,y-2)) return true;
        if (check(x+1,y+2)) return true;
        if (check(x-1,y+2)) return true;
        else return false;
    }
}
