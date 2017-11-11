import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

// 游戏场景
public class GameCanvas extends JPanel implements MouseListener {

    public static final int MARGIN_LEFT = 75;   // 左边距
    public static final int MARGIN_TOP = 195;   // 左边距
    public static final int GRID_SPAN = 40;//网格间距
    public static final int ROWS = 15;//棋盘行数
    public static final int COLS = 15;//棋盘列数

    boolean isBlack = true;             //黑棋先手
    public static int chessCount = 0;   // 棋盘上棋子的个数
    private int xIndex;                 // 最后一枚棋子的横坐标
    private int yIndex;                 // 最后一枚棋子的纵坐标
    private Color colorChess; // 棋子的颜色
    ChessPoint[] chessPoints = new ChessPoint[(ROWS) * (COLS)]; // 15*15个交点 15*15个棋子


    private Image img;

    // 构造方法
    public GameCanvas() {
        this.setPreferredSize(new Dimension(666, 800));//关键代码,设置JPanel的大小
        img = Toolkit.getDefaultToolkit().getImage("img/ChessBoard.jpg");
        addMouseListener(this);
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // 鼠标变换 指针和小手的变换
                int xIndex = (e.getX() - MARGIN_LEFT + GRID_SPAN / 2) / GRID_SPAN;
                int yIndex = (e.getY() - MARGIN_TOP + GRID_SPAN / 2) / GRID_SPAN;

                if (xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS || isExistChess(xIndex,yIndex)) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                else {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, this); //画背景图

        // 画棋盘
//        for (int i = 0; i < ROWS; i++) {
//            //画横线 g.drawLine(x1,y1,x2,y2)
//            g.drawLine(MARGIN_LEFT, MARGIN_TOP + i * GRID_SPAN, MARGIN_LEFT + (COLS-1) * GRID_SPAN, MARGIN_TOP + i * GRID_SPAN);
//        }
//        for (int i = 0; i < COLS; i++) {
//            //画竖线
//            g.drawLine(MARGIN_LEFT + i * GRID_SPAN, MARGIN_TOP, MARGIN_LEFT + i * GRID_SPAN, MARGIN_TOP + (ROWS-1) * GRID_SPAN);
//        }

        // 画棋子
        for (int i = 0; i < chessCount; i++) {
            // 交叉点的坐标
            int xPosition = chessPoints[i].getX() * GRID_SPAN + MARGIN_LEFT; // x
            int yPosition = chessPoints[i].getY() * GRID_SPAN + MARGIN_TOP; // y
            int x = xPosition - ChessPoint.DIAMETER / 2;
            int y = yPosition - ChessPoint.DIAMETER / 2;

            RadialGradientPaint paint;
            Graphics2D g2D = (Graphics2D) g;
            //让圆形变得更加精细
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            colorChess = chessPoints[i].getColor();
            if (colorChess == Color.BLACK) {
                paint = new RadialGradientPaint(x + 25, y + 10, 20, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
                g2D.setPaint(paint);
            }
            if (colorChess == Color.WHITE) {
                paint = new RadialGradientPaint(x + 25, y + 10, 70, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
                g2D.setPaint(paint);
            }
            Ellipse2D e2D = new Ellipse2D.Float(x, y, 35, 35); // 圆形
            g2D.fill(e2D);
            //设置最后一个子红色边框
            if (i==chessCount-1){
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.red);
                g2d.draw3DRect(x - 3, y - 3, 40, 40,true);
            }

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

        xIndex = (e.getX() - MARGIN_LEFT + GRID_SPAN / 2) / GRID_SPAN;
        yIndex = (e.getY() - MARGIN_TOP + GRID_SPAN / 2) / GRID_SPAN;

        // 超出行和列的范围就不能下
        if (xIndex < 0 || xIndex > ROWS - 1 || yIndex < 0 || yIndex > COLS - 1) {
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
        PlaySound playSound =new PlaySound();
        playSound.PlaySound("chess");
        // 重画面板
        repaint();

        // TODO
        // 如果赢了就弹出提示信息
        isWin();

        // 交换落子方
        isBlack = !isBlack;
    }

    public boolean getIsBlack(boolean isBlack){
        return isBlack;
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

    // 判断这个坐标是否存在同一颜色的棋子
    // 存在返回 true
    // 不存在返回 false
    private ChessPoint getChess(int x, int y, Color color){
        for (int i = 0; i < chessPoints.length; i++) {
            if (chessPoints[i] != null && chessPoints[i].getX() == x
                    && chessPoints[i].getY() == y && chessPoints[i].getColor() == color) {
                return chessPoints[i];
            }
        }
        return null;
    }


    private boolean isWin() {
        System.out.println(xIndex);
        System.out.println(yIndex);

        int xPosition; // 临时变量 x的位置
        int yPosition; // 临时变量 y的位置
        int count = 1; //连续棋子的个数


        // 向左寻找
        for (xPosition = xIndex - 1; xPosition >= 0; xPosition--){
            Color color = isBlack?Color.BLACK:Color.WHITE;
            if (getChess(xPosition,yIndex, color) != null){
                count++;
            }
        }


        return false;
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
