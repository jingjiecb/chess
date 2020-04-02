package Box;

import Pieces.*;
import Pieces.Error.*;


import java.util.Scanner;

public class Box {

    private static Piece[][] pieces;

    public Piece[] whites=new Piece[24];
    int wCounter=16;
    public Piece[] blacks=new Piece[24];
    int bCounter=16;

    private Box(){
        pieces=new Piece[8][8];
    }
    private static Box boxInstance = new Box();

    public static Box getBox(){
        return boxInstance;
    }

    public void set(int x,int y,Piece piece){
        pieces[x][y]=piece;
    }

    public void set(Piece piece){
        pieces[piece.x][piece.y]=piece;
    }

    public Piece get(int x,int y){
        return pieces[x][y];
    }

    public void ready(){
        whites[0]=new King(4,0,true);
        whites[1]=new Queen(3,0,true);
        whites[2]=new Rook(0,0,true);
        whites[3]=new Rook(7,0,true);
        whites[4]=new Knight(1,0,true);
        whites[5]=new Knight(6,0,true);
        whites[6]=new Bishop(2,0,true);
        whites[7]=new Bishop(5,0,true);
        for (int i=0;i<8;i++){
            whites[i+8]=new Pawn(i,1,true);
        }

        blacks[0]=new King(4,7,false);
        blacks[1]=new Queen(3,7,false);
        blacks[2]=new Rook(0,7,false);
        blacks[3]=new Rook(7,7,false);
        blacks[4]=new Knight(1,7,false);
        blacks[5]=new Knight(6,7,false);
        blacks[6]=new Bishop(2,7,false);
        blacks[7]=new Bishop(5,7,false);
        for (int i=0;i<8;i++){
            blacks[i+8]=new Pawn(i,6,false);
        }

        for (int i=0;i<16;i++){
            set(whites[i]);
        }
        for (int i=0;i<16;i++){
            set(blacks[i]);
        }
    }

    public boolean controlled(int x,int y, boolean isWhite){
        if (isWhite) {
            for (int i=0;i<24;i++){
                if (whites[i]==null || whites[i].acitve==false) continue;
                if (whites[i].control(x,y)) return true;
            }
            return false;
        }
        else {
            for (int i=0;i<24;i++){
                if (blacks[i]==null || blacks[i].acitve==false) continue;
                if (blacks[i].control(x,y)) return true;
            }
            return false;
        }
    }

    public void print(){
        int row, column;
        for (row=8;row>0;row--){
            System.out.print(" "+row+"  ");
            for (column=1;column<=8;column++){
                if (boxInstance.pieces[column-1][row-1]!=null) System.out.print(" "+ boxInstance.pieces[column-1][row-1].getS());
                else if ((column+row)%2==1) System.out.print(" □");
                else System.out.print(" ■");
            }
            System.out.println();
        }
        int a=(int)'a';
        System.out.print("    ");
        for (column=0;column<8;column++){
            System.out.print(" "+(char)(a+column)+" ");
        }
        System.out.println();

        for (int i=0;i<24;i++){
            if (whites[i]==null || whites[i].acitve) continue;
            System.out.print(whites[i].getS());
        }
        System.out.println();
        for (int i=0;i<24;i++){
            if (blacks[i]==null || blacks[i].acitve) continue;
            System.out.print(blacks[i].getS());
        }
        System.out.println();
    }

    public boolean NoMove(boolean isWhite){
        if (isWhite){
            for (int i=15;i>=0;i--){
                if (whites[i].moveable()==true) return false;
            }
            return false;
        }
        else {
            for (int i=15;i>=0;i--){
                if (blacks[i].moveable()==true) return false;
            }
            return false;
        }
    }

    boolean moveable(boolean isWhite){
        if (isWhite){
            for (int i=0;i<24;i++){
                if (whites[i]==null || !whites[i].acitve || whites[i].x>10) continue;
                if (whites[i].moveable()) return true;
            }
            return false;
        }
        else {
            for (int i=0;i<24;i++){
                if (blacks[i]==null || !blacks[i].acitve || blacks[i].x>10) continue;
                if (blacks[i].moveable()) return true;
            }
            return false;
        }
    }

    public void move(int x1,int y1,int x2,int y2,boolean isWhite)throws IllegalInput,GameOver,NoMove{
        Piece piece=get(x1,y1);

        try {
            piece.go(x2,y2);

        } catch (LevelUp e){
            int x=piece.x;
            int y=piece.y;
            System.out.println("请选择升变种类：1-后，2-车，3-马，4-象");
            Scanner scanner=new Scanner(System.in);
            int cat=scanner.nextInt();
            Piece upPiece;
            switch (cat){
                case 1: upPiece= new Queen(x,y,piece.white);break;
                case 2: upPiece= new Rook(x,y,piece.white);break;
                case 3: upPiece= new Knight(x,y,piece.white);break;
                case 4: upPiece= new Bishop(x,y,piece.white);break;
                default: upPiece= new Queen(x,y,piece.white);
            }
            set(upPiece);
            if (piece.white){
                whites[wCounter]=upPiece;
                wCounter++;
            }
            else {
                blacks[bCounter]=upPiece;
                bCounter++;
            }
            piece.x=999;
            piece.y=999;
        }
        finally {
            print();
        }

        if (!moveable(!isWhite)) throw new NoMove();
    }



}
