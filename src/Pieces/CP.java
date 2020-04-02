package Pieces;

public enum CP {
    wKing('♔'),bKing('♚'),wQueen('♕'),bQueen('♛'),wBishop('♗'),bBishop('♝'),wKnight('♘'),bKnight('♞'),wRook('♖'),bRook('♜'),wPawn('♙'),bPawn('♟');

    private char s;

    private CP(char symbol){
       this.s=symbol;
    }

    public char getS() {
        return s;
    }
}
