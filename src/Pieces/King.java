package Pieces;

import Pieces.Error.GameOver;
import Pieces.Error.IllegalInput;
import Pieces.Error.LevelUp;

public class King extends Piece{
    public King(int xp,int yp,boolean isWhite){
        white=isWhite;
        x=xp;
        y=yp;
        acitve=true;
        Symbol=isWhite? CP.wKing.getS(): CP.bKing.getS();
    }

    public boolean check(int xp,int yp){
        if (xp>7 || yp>7 || xp<0 || yp<0) return false;
        if (Math.abs(x-xp)!=1 || Math.abs(y-yp)!=1) return false;
        if (box.get(xp,yp)==null) return true;
        if (box.get(xp,yp).white==this.white) return false;
        else return true;
    }

    public void go(int xp,int yp)throws LevelUp,GameOver,IllegalInput{
        if (wangjuyiwei(xp,yp)) return;
        if (check(xp,yp)) {
            leave();
            x=xp;
            y=yp;
            arrive();
        }
        else throw new IllegalInput();
    }

    void inactive()throws GameOver {
        throw new GameOver(!this.white);
    }

    public boolean moveable(){
        if (check(x+1,y) && !box.controlled(x+1,y,!white)) return true;
        if (check(x+1,y+1) && !box.controlled(x+1,y+1,!white)) return true;
        if (check(x,y+1) && !box.controlled(x,y+1,!white)) return true;
        if (check(x-1,y+1) && !box.controlled(x-1,y+1,!white)) return true;
        if (check(x-1,y) && !box.controlled(x-1,y,!white)) return true;
        if (check(x-1,y-1) && !box.controlled(x-1,y-1,!white)) return true;
        if (check(x,y-1) && !box.controlled(x,y-1,!white)) return true;
        if (check(x+1,y-1) && !box.controlled(x+1,y-1,!white)) return true;
        else return false;
    }

    private boolean wangjuyiwei(int xp,int yp) throws LevelUp,GameOver {
        if (everMove) return false;

        if (white){
            if (xp==x+2) {
                Piece rook=box.whites[3];
                if (rook.everMove) return false;
                for (int i=4;i<7;i++){
                    if (box.controlled(i,0,false)) return false;
                }

                leave();
                x=6;
                arrive();
                rook.leave();
                rook.x=5;
                rook.arrive();
                return true;
            }

            if (xp==x-2){
                Piece rook=box.whites[2];
                if (rook.everMove) return false;
                for (int i=1;i<=4;i++){
                    if (box.controlled(i,0,false)) return false;
                }

                leave();
                x=2;
                arrive();
                rook.leave();
                rook.x=3;
                rook.arrive();
                return true;
            }

            return false;
        }
        else {
            if (xp==x+2) {
                Piece rook=box.blacks[3];
                if (rook.everMove) return false;
                for (int i=4;i<7;i++){
                    if (box.controlled(i,0,true)) return false;
                }

                leave();
                x=6;
                arrive();
                rook.leave();
                rook.x=5;
                rook.arrive();
                return true;
            }

            if (xp==x-2){
                Piece rook=box.blacks[2];
                if (rook.everMove) return false;
                for (int i=1;i<=4;i++){
                    if (box.controlled(i,0,true)) return false;
                }

                leave();
                x=2;
                arrive();
                rook.leave();
                rook.x=3;
                rook.arrive();
                return true;
            }

            return false;
        }

    }


}
