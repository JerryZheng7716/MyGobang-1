import java.awt.*;
// 单个棋子
public class ChessPoint {
    private int x;//棋盘中的x索引
    private int y;//棋盘中的y索引
    private Color color;//颜色
    public static final int DIAMETER=30;//直径

    public ChessPoint(){
        // pass
    }

    public ChessPoint(int x,int y,Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    }

    public int getX(){//拿到棋盘中x的索引
        return x;
    }
    public int getY(){
        return y;
    }
    public Color getColor(){//获得棋子的颜色
        return color;
    }
}
