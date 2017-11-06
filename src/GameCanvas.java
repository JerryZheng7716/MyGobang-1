import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class GameCanvas extends JPanel implements MouseListener {

    public static final int MARGIN = 30;//边距
    public static final int GRID_SPAN = 35;//网格间距
    public static final int ROWS = 15;//棋盘行数
    public static final int COLS = 15;//棋盘列数

    ChessPoint[] chessPoints = new ChessPoint[(ROWS + 1) * (COLS + 1)]; // 16*16个交点 16*16个棋子
    boolean isBlack = true; //黑棋先手
    public static int chessCount = 0; // 棋盘上棋子的个数
    private int xIndex, yIndex; // 最后一枚棋子的坐标
    private Color colorTemp; // 棋子的颜色

    Image img;

    // 构造方法
    public GameCanvas() {
        addMouseListener(this);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int x = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                int y = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                if (xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > 0) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);//画棋盘

//        int imgWidth= img.getWidth(this);
//        int imgHeight=img.getHeight(this);//获得图片的宽度与高度
//        int FWidth=getWidth();
//        int FHeight=getHeight();//获得窗口的宽度与高度
//        int x=(FWidth-imgWidth)/2;
//        int y=(FHeight-imgHeight)/2;
//        g.drawImage(img, x, y, null);

        // 画棋盘
        for (int i = 0; i <= ROWS; i++) {//画横线
            g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
        }
        for (int i = 0; i <= COLS; i++) {//画竖线
            g.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);
        }

        // 画棋子
        for (int i = 0; i < chessCount; i++) {
            // 交叉点的坐标
            int xPos = chessPoints[i].getX() * GRID_SPAN + MARGIN; // x
            int yPos = chessPoints[i].getY() * GRID_SPAN + MARGIN; // y
            RadialGradientPaint paint;
            Graphics2D g2D = (Graphics2D) g;
            colorTemp = chessPoints[i].getColor();
            if (colorTemp == Color.BLACK) {
                paint = new RadialGradientPaint(xPos - ChessPoint.DIAMETER / 2 + 25, yPos - ChessPoint.DIAMETER / 2 + 10, 20, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
                g2D.setPaint(paint);
            }
            if (colorTemp == Color.WHITE) {
                paint = new RadialGradientPaint(xPos - ChessPoint.DIAMETER / 2 + 25, yPos - ChessPoint.DIAMETER / 2 + 10, 70, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
                g2D.setPaint(paint);
            }

            Ellipse2D e2D = new Ellipse2D.Float(xPos - ChessPoint.DIAMETER / 2, yPos - ChessPoint.DIAMETER / 2, 35, 35); // 圆形
            g2D.fill(e2D);
        }
    }

    // 调用鼠标单击事件
    // 将坐标转化为索引
    //
    // 调用isExistChess 是否能落子
    // 能落子将棋子加入数组
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO
        // 游戏结束不能下

        String colorName = isBlack ? "黑棋" : "白棋";

        xIndex = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
        yIndex = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;

        // 超出行和列的范围就不能下
        if (xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS) {
            return;
        }
        System.out.println("点击 (" + xIndex + "\t, " + yIndex + ")");
        // TODO
        // 已经有棋子的地方也不能下
        if (isExistChess(xIndex, yIndex)) {
            return;
        }

        ChessPoint chessPoint = new ChessPoint(xIndex, yIndex, isBlack ? Color.BLACK : Color.WHITE);
        chessPoints[chessCount] = chessPoint;
        chessCount++;
        // 重画面板
        repaint();

        // TODO
        // 如果赢了就弹出提示信息

        // 交换落子方
        isBlack = !isBlack;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // 判断这个坐标是否存在棋子
    // 存在返回 true
    // 不存在返回 false
    private boolean isExistChess(int x, int y) {
        for (int i = 0; i < chessPoints.length; i++) {
            if (chessPoints[i] != null && chessPoints[i].getX() == x && chessPoints[i].getY() == y) {
                return true;
            }
        }
        return false;
    }

    //
    private boolean isWin() {
        return true;
    }

    public void Back() {
        if (chessCount == 0) {
            return;
        }
        chessPoints[chessCount - 1] = null;
        chessCount--;
        if (chessCount > 0) {
            xIndex = chessPoints[chessCount - 1].getX();
            yIndex = chessPoints[chessCount - 1].getY();
        }
        isBlack = !isBlack;
        repaint();
    }

    public void restartGame(){
        for (int i = 0; i < chessPoints.length; i++){
            chessPoints[i] = null;
        }
        isBlack = true;
        chessCount = 0;
        repaint();
    }
}
