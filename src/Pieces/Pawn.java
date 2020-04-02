package Pieces;

import Pieces.Error.GameOver;
import Pieces.Error.IllegalInput;
import Pieces.Error.LevelUp;

public class Pawn extends Piece {
    boolean hasPassedBy=false;
    int passByX=-1;
    int passByY=-1;


    public Pawn(int xp,int yp,boolean isWhite){
        white=isWhite;
        x=xp;
        y=yp;
        acitve=true;
        everMove=false;
        Symbol=isWhite? CP.wPawn.getS(): CP.bPawn.getS();
    }

    public boolean check(int xp,int yp){
        if (xp>7 || yp>7 || xp<0 || yp<0) return false;
        if (box.get(xp,yp)!=null && box.get(xp,yp).white==this.white) return false;

        if (!everMove && x==xp && ((white && yp==y+2) || (!white && yp==y-2)) && box.get(xp,yp)==null && ((white &&box.get(x,y+1)==null) ||(!white && box.get(x,y-1)==null)) ) {
            if (white) {
                for (int i = 8; i < 16; i++) {
                    Pawn pawn=(Pawn) box.blacks[i];
                    if (pawn.control(x,y+1)){
                        pawn.hasPassedBy=true;
                        pawn.passByX=x;
                        pawn.passByY=y+1;
                    }
                }
                return true;
            }
            else {
                for (int i = 8; i < 16; i++) {
                    Pawn pawn=(Pawn) box.whites[i];
                    if (pawn.control(x,y-1)){
                        pawn.hasPassedBy=true;
                        pawn.passByX=x;
                        pawn.passByY=y-1;
                    }
                }
                return true;
            }
        }

        if (white && yp==y+1 && x==xp && box.get(xp,yp)==null) return true;
        if (!white && yp==y-1 && x==xp && box.get(xp,yp)==null) return true;
        if (white && yp==y+1 && Math.abs(x-xp)==1 && box.get(xp,yp)!=null) return true;
        if (!white && yp==y-1 && Math.abs(x-xp)==1 && box.get(xp,yp)!=null) return true;
        else return false;
    }

    public void go(int xP,int yP) throws IllegalInput, GameOver, LevelUp {
        if (hasPassedBy){
            if (xP==passByX && yP==passByY) {
                leave();
                x=xP;
                y=yP;
                arrive();

                Piece pawn=white?box.get(x,y-1):box.get(x,y+1);
                pawn.leave();
                pawn.inactive();

                hasPassedBy=false;
                return;
            }
        }

        if (check(xP,yP)) {
            leave();
            x=xP;
            y=yP;
            arrive();
            hasPassedBy=false;
            everMove=true;
        }
        else throw new IllegalInput();
    }

    void arrive() throws GameOver, LevelUp {
        Piece piece=box.get(x,y);
        if (piece!=null) piece.inactive();
        if ((white && y==7)||(!white && y==0)) throw new LevelUp(x,y,white);
        box.set(x,y,this);
    }

    public boolean control(int xp,int yp){
        if (white && yp==y+1 && (xp==x+1||xp==x-1)) return true;
        if (!white && yp==y-1 && (xp==x+1||xp==x-1)) return true;
        else return false;
    }

    @Override
    public boolean moveable() {
        if (white){
            for (int i=-1;i<=1;i++){
                if (check(x+i,y+1)) return true;
            }
            return false;
        }
        else {
            for (int i=-1;i<=1;i++){
                if (check(x+i,y-1)) return true;
            }
            return false;
        }
    }
}
