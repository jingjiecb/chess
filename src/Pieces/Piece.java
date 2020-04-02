package Pieces;

import Box.Box;
import Pieces.Error.GameOver;
import Pieces.Error.IllegalInput;
import Pieces.Error.LevelUp;

public abstract class Piece {
    public boolean white=false;//设置布尔值表示这个棋子是不是白方的棋子。是白方的：true；是黑方的：false。
    public int x=-1;
    public int y=-1;
    public char Symbol=' ';
    public boolean acitve=false;
    Box box=Box.getBox();

    public boolean everMove=false;//检查棋子是否移动过。用来判断王車易位的条件，以及兵的第一步。


    public char getS(){
        return this.Symbol;
    }

    public void go(int xP,int yP) throws IllegalInput, GameOver, LevelUp{
        if (check(xP,yP)) {
            leave();
            x=xP;
            y=yP;
            arrive();
            everMove=true;
        }
        else throw new IllegalInput();
    }

    void leave(){
        box.set(x,y,null);
        everMove=true;
    }

    void arrive() throws GameOver, LevelUp {
        Piece piece=box.get(x,y);
        box.set(x,y,this);
        if (piece!=null) piece.inactive();
    }

    boolean pathIsFree(int x1,int y1, int x2,int y2){
        int t;
        boolean cx=false;
        boolean cy=false;
        if (x2<x1){
            t=x2;
            x2=x1;
            x1=t;
            cx=true;
        }
        if (y2<y1){
            t=y2;
            y2=y1;
            y1=t;
            cy=true;
        }

        if (x1==x2){
            for (int i=y1+1;i<y2;i++){
                if (box.get(x1,i)!=null) return false;
            }
            return true;
        }

        if (y1==y2){
            for (int i=x1+1;i<x2;i++){
                if (box.get(i,y1)!=null) return false;
            }
            return true;
        }

        if ((y2-y1)==(x2-x1)){
            if (cx!=cy){
                t=y2;
                y2=y1;
                y1=t;
                for (int i=1;i<x2-x1;i++){
                    if (box.get(x1+i,y1-i)!=null) return false;
                }
            }
            for (int i=1;i<x2-x1;i++){
                if (box.get(x1+i,y1+i)!=null) return false;
            }
            return true;
        }

        else return false;
    }

    public boolean control(int x,int y){
        return check(x,y);
    }

    void inactive() throws GameOver{
        this.acitve=false;
    }



    public abstract boolean check(int xP,int yP);

    public abstract boolean moveable();



}
