import Box.Box;
import Pieces.Error.GameOver;
import Pieces.Error.IllegalInput;
import Pieces.Error.NoMove;
import Pieces.Piece;

import java.util.Scanner;

public class Chess {
    static Box box= Box.getBox();

    public static void main(String[] args){
        box.ready();
        box.print();
        boolean isWhite=true;
        Scanner scanner=new Scanner(System.in);
        int x1,x2,y1,y2;
        String str;
        System.out.println("白方先开始着子！");
        while (true){

            System.out.print("请选择要移动棋子位置：");
            str=scanner.next();
            x1=(int)(str.charAt(0))-(int)'a';
            y1=str.charAt(1)-'1';
            scanner.nextLine();

            Piece piece=box.get(x1,y1);
            if (piece==null || piece.white!=isWhite || !piece.moveable()) {
                System.out.println("选择不当，棋子不存在或者无法移动，请重选！");
                continue;
            }

            System.out.print("请选择要移动的位置：");
            str=scanner.next();
            x2=(int)(str.charAt(0))-(int)'a';
            y2=str.charAt(1)-'1';
            scanner.nextLine();

            try {
                box.move(x1,y1,x2,y2,isWhite);
            }catch (IllegalInput e){
                System.out.println("走法不当，请重选！");
                continue;
            }catch (GameOver e){
                if (e.getWinner()){
                    System.out.println("白方获胜！");
                    break;
                }
                else {
                    System.out.println("黑方获胜！");
                    break;
                }
            }catch (NoMove e){
                System.out.println("逼和！");
                break;
            }

            isWhite=isWhite?false:true;

            if (isWhite)System.out.println("轮到白方：");
            else System.out.println("轮到黑方：");
        }
    }

}
