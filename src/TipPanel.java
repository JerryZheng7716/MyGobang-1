import javax.swing.*;
import java.awt.*;

// 提示面板
public class TipPanel extends JPanel{
    // 设置棋子的颜色和提示板的颜色
    private Color WhiteChessColor = Color.WHITE;
    private Color BlackChessColor = Color.BLACK;
    private Color BackgroudColor = Color.yellow;
    private GameCanvas gameCanvas;

    // 棋子
    private ChessPoint chessPoint = new ChessPoint();
    // 是否绘制好
    private boolean isTiped = false;
    // 获取长宽
    private int boxWidth, boxHeight;

    public void setWhichChess(ChessPoint chessPoint){
        this.chessPoint = chessPoint;
    }

    public TipPanel(GameCanvas gameCanvas){
        this.gameCanvas = gameCanvas;
        // 构造方法，画旗子
        // TODO
    }

    public void paintTipChess(boolean isBlack){
        Color chessColor = isBlack ? BlackChessColor : WhiteChessColor;
    }


    // 获取窗体宽度
    public void fanning(){

        boxWidth = this.getWidth();
        boxHeight = this.getHeight();
        isTiped = true;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!isTiped){
            fanning();
        }

        g.drawLine(10,10,45,10); // 第一条横线
        g.drawLine(10,45,45,45); // 第二条横线
        g.drawLine(10,10,10,45); // 第一条竖线
        g.drawLine(45,10,45,45); // 第二条竖线

    }
}
