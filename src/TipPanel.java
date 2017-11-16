import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

// 提示面板
public class TipPanel extends JPanel{
    // 设置棋子的颜色和提示板的颜色
    private Color WhiteChessColor = Color.WHITE;
    private Color BlackChessColor = Color.BLACK;
    private Color BackgroudColor = Color.yellow;
    private GameCanvas gameCanvas;
    boolean isBlack = false;             //黑棋先手

    // 棋子
    private ChessPoint chessPoint = new ChessPoint();
    // 是否绘制好
    private boolean isTiped = false;
    // 获取长宽
    private int boxWidth, boxHeight;

    public TipPanel() {

    }

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

//        g.drawLine(10,10,45,10); // 第一条横线
//        g.drawLine(10,45,45,45); // 第二条横线
//        g.drawLine(10,10,10,45); // 第一条竖线
//        g.drawLine(45,10,45,45); // 第二条竖线
        RadialGradientPaint paint;
        Graphics2D g2D = (Graphics2D) g;
        //让圆形变得更加精细
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isBlack == false) {
            paint = new RadialGradientPaint(85 - ChessPoint.DIAMETER / 2 + 25, 40 - ChessPoint.DIAMETER / 2 + 10, 20, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
            g2D.setPaint(paint);
        }
        if (isBlack == true) {
            paint = new RadialGradientPaint(85 - ChessPoint.DIAMETER / 2 + 25, 40 - ChessPoint.DIAMETER / 2 + 10, 70, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
            g2D.setPaint(paint);
        }

        Ellipse2D e2D = new Ellipse2D.Float(85 - ChessPoint.DIAMETER / 2, 40 - ChessPoint.DIAMETER / 2, 35, 35); // 圆形
        g2D.fill(e2D);

    }

    public void paintChess(boolean isBlack){
        this.isBlack = isBlack;
        System.out.println(this.isBlack);
        repaint();
    }
}
