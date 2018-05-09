//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.geom.Ellipse2D;
//
//// 游戏场景
//public class GameCanvas extends JPanel implements MouseListener {
//
//    public static final int MARGIN_LEFT = 75;   // 左边距
//    public static final int MARGIN_TOP = 195;   // 左边距
//    public static final int GRID_SPAN = 40;     // 网格间距
//    public static final int ROWS = 15;  // 棋盘行数
//    public static final int COLS = 15;  // 棋盘列数
//    public static int chessCount = 0;   // 棋盘上棋子的个数
//    public static boolean isBlack = true;             // 黑棋先手
//    public static boolean isGameOver = false;         // 游戏是否结束
//    ChessPoint[] chessPoints = new ChessPoint[(ROWS) * (COLS)]; // 15*15个交点 15*15个棋子
//    public static int chessSize = 35;
//    private int xIndex;                 // 最后一枚棋子的横坐标
//    private int yIndex;                 // 最后一枚棋子的纵坐标
//    private Color colorChess;           // 棋子的颜色
//    private Image img;                  // 场景背景图
//    ControlPanel controlPanel;
//    AI ai =new AI();
//
//
//    // 构造方法
//    public GameCanvas(ControlPanel controlPanel) {
//        img = new ImageIcon("img/ChessBoard.jpg").getImage();
//        int imageWidth = img.getWidth(this);
//        int imageHeight = img.getHeight(this);
//        this.setPreferredSize(new Dimension(imageWidth, imageHeight)); // 设置JPanel的大小
//
//        this.controlPanel=controlPanel;
//
//        addMouseListener(this);
//        addMouseMotionListener(new MouseMotionListener() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                // 鼠标变换 指针和小手的变换
//                int xIndex = (e.getX() - MARGIN_LEFT + GRID_SPAN / 2) / GRID_SPAN;
//                int yIndex = (e.getY() - MARGIN_TOP + GRID_SPAN / 2) / GRID_SPAN;
//
//                if (xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS || isExistChess(xIndex, yIndex)) {
//                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//                } else {
//                    setCursor(new Cursor(Cursor.HAND_CURSOR));
//                }
//            }
//        });
//    }
//
//
//    public void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//
//        g.drawImage(img, 0, 0, this); // 画背景图
//
////        // 画棋盘
////        for (int i = 0; i < ROWS; i++) {
////            //画横线 g.drawLine(x1,y1,x2,y2)
////            g.drawLine(MARGIN_LEFT, MARGIN_TOP + i * GRID_SPAN, MARGIN_LEFT + (COLS - 1) * GRID_SPAN, MARGIN_TOP + i * GRID_SPAN);
////        }
////        for (int i = 0; i < COLS; i++) {
////            //画竖线
////            g.drawLine(MARGIN_LEFT + i * GRID_SPAN, MARGIN_TOP, MARGIN_LEFT + i * GRID_SPAN, MARGIN_TOP + (ROWS - 1) * GRID_SPAN);
////        }
//
//        // 画棋子
//        for (int i = 0; i < chessCount; i++) {
//            // 交叉点的坐标
//
//
//            int xPosition = chessPoints[i].getX() * GRID_SPAN + MARGIN_LEFT; // x
//            int yPosition = chessPoints[i].getY() * GRID_SPAN + MARGIN_TOP; // y
//
//            int xBox = xPosition - ChessPoint.DIAMETER / 2; // 红色框的起点横坐标
//            int yBox = yPosition - ChessPoint.DIAMETER / 2; // 红色框的起点纵坐标
//
//            RadialGradientPaint paint;
//            Graphics2D g2D = (Graphics2D) g;
//
//            // 让圆形变得更加精细
//            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//            colorChess = chessPoints[i].getColor();
//            if (colorChess == Color.BLACK) {
//                paint = new RadialGradientPaint(xPosition - ChessPoint.DIAMETER / 2 + 25, yPosition - ChessPoint.DIAMETER / 2 + 10, 20, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
//                g2D.setPaint(paint);
//            }
//            if (colorChess == Color.WHITE) {
//                paint = new RadialGradientPaint(xPosition - ChessPoint.DIAMETER / 2 + 25, yPosition - ChessPoint.DIAMETER / 2 + 10, 70, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
//                g2D.setPaint(paint);
//            }
//
//            Ellipse2D e2D = new Ellipse2D.Float(xPosition - ChessPoint.DIAMETER / 2, yPosition - ChessPoint.DIAMETER / 2, chessSize, chessSize); // 圆形
//            g2D.fill(e2D);
//
//            //设置最后一个子红色边框
//            if (i==chessCount-1){
//                Graphics2D g2d = (Graphics2D) g;
//                g2d.setColor(Color.red);
//                g2d.draw3DRect(xBox, yBox, chessSize, chessSize,true);
//            }
//
//        }
//
//    }
//
//    // 调用鼠标单击事件
//    // 将坐标转化为索引
//    //
//    // 调用isExistChess 是否能落子
//    // 能落子将棋子加入数组
//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//        // 游戏结束不能下
//        if (isGameOver) {
//            return;
//        }
//
//        String colorName = isBlack ? "黑棋" : "白棋";
//
//        xIndex = (e.getX() - MARGIN_LEFT + GRID_SPAN / 2) / GRID_SPAN;
//        yIndex = (e.getY() - MARGIN_TOP + GRID_SPAN / 2) / GRID_SPAN;
//
//        // 超出行和列的范围就不能下
//        if (xIndex < 0 || xIndex > ROWS - 1 || yIndex < 0 || yIndex > COLS - 1) {
//            return;
//        }
//
//        System.out.println("点击 (" + xIndex + "\t, " + yIndex + ")");
//
//        // 已经有棋子的地方也不能下
//        if (isExistChess(xIndex, yIndex)) {
//            return;
//        }
//        //ChessPoint chessPoint = new ChessPoint(xIndex, yIndex, isBlack ? Color.BLACK : Color.WHITE);
//        ChessPoint chessPoint = new ChessPoint(xIndex, yIndex, Color.WHITE);
//        chessPoints[chessCount] = chessPoint;
//        chessCount++;
//        PlaySound playSound=new PlaySound();
//        playSound.PlaySound("chess");
//
//        // 重新画提示面板的棋子  // TODO
//        controlPanel.goTipnel();
//        ai.Ai(this);
//        if (chessCount==1){
//            ChessPoint aichessPoint = new ChessPoint(7,7,Color.black);
//            chessPoints[chessCount] = aichessPoint;
//            chessCount++;
//        }
//        if (ai.xChess!=0 || ai.yChess!=0){
//            System.out.println(ai.xChess+"cccc"+ai.yChess);
//            ChessPoint aichessPoint = new ChessPoint(ai.xChess,ai.yChess,Color.black);
//            chessPoints[chessCount] = aichessPoint;
//            chessCount++;
//            ai.xChess=0;ai.yChess=0;
//        }
//        // 重画面板
//        repaint();
//
//
//        // 如果赢了就弹出提示信息
//        if (is成五()){
//            JOptionPane.showMessageDialog(this, "恭喜 " + colorName + " 赢了");
//            isGameOver = true;
//        }
//
//        // 交换落子方
//        isBlack = !isBlack;
//
//
//    }
//
//    public boolean getIsBlack(boolean isBlack) {
//        return isBlack;
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    // 判断这个坐标是否存在棋子
//    // 存在返回 true
//    // 不存在返回 false
//    private boolean isExistChess(int x, int y) {
//        for (int i = 0; i < chessPoints.length; i++) {
//            if (chessPoints[i] != null && chessPoints[i].getX() == x && chessPoints[i].getY() == y) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // 判断这个坐标是否存在同一颜色的棋子
//    // 存在返回 true
//    // 不存在返回 false
//    private ChessPoint getChess(int x, int y, Color color) {
//        for (int i = 0; i < chessPoints.length; i++) {
//            if (chessPoints[i] != null && chessPoints[i].getX() == x
//                    && chessPoints[i].getY() == y && chessPoints[i].getColor() == color) {
//                return chessPoints[i];
//            }
//        }
//        return null;
//    }
//
//    public int  getChessColor(int x,int y){
//        for (int i = 0; i < chessPoints.length; i++) {
//            if (chessPoints[i] != null && chessPoints[i].getX() == x
//                    && chessPoints[i].getY() == y && chessPoints[i].getColor() == Color.black) {
//                return 0;
//            }else if (chessPoints[i] != null && chessPoints[i].getX() == x
//                    && chessPoints[i].getY() == y && chessPoints[i].getColor() == Color.white) {
//                return 1;
//            }
//        }
//        return 2;
//
//    }
//
//
//    private boolean is成五() {
////        System.out.println(xIndex);
////        System.out.println(yIndex);
//
//        int xPosition; // 临时变量 x的位置
//        int yPosition; // 临时变量 y的位置
//        int count = 1; //连续棋子的个数
//
//        // 直线寻找
//
//        // 向左寻找
//        for (xPosition = xIndex - 1; xPosition >= 0; xPosition--) {
//            Color color = isBlack ? Color.BLACK : Color.WHITE;
//            if (getChess(xPosition, yIndex, color) != null) {
//                count++;
//            }
//            else {
//                break;
//            }
//        }
//
//        // 向右寻找
//        for (xPosition = xIndex + 1; xPosition <= COLS - 1; xPosition++) {
//            Color color = isBlack ? Color.BLACK : Color.WHITE;
//            if (getChess(xPosition, yIndex, color) != null) {
//                count++;
//            }
//            else {
//                break;
//            }
//        }
//        if (count >= 5) {
//            return true;
//        }
//        else {
//            count = 1;
//        }
//
//        // 向上寻找
//        for (yPosition = yIndex - 1; yPosition >= 0 ; yPosition--) {
//            Color color = isBlack ? Color.BLACK : Color.WHITE;
//            if (getChess(xIndex, yPosition, color) != null) {
//                count++;
//            }
//            else {
//                break;
//            }
//        }
//
//        // 向下寻找
//        for (yPosition = yIndex + 1; yPosition <= ROWS - 1; yPosition++) {
//            Color color = isBlack ? Color.BLACK : Color.WHITE;
//            if (getChess(xIndex, yPosition, color) != null) {
//                count++;
//            }
//            else {
//                break;
//            }
//        }
//        if (count >= 5) {
//            return true;
//        }
//        else {
//            count = 1;
//        }
//
//        // 向 右下 寻找
//        for (xPosition = xIndex + 1, yPosition = yIndex + 1; xPosition <= COLS -1 && yPosition <= ROWS - 1;xPosition++, yPosition++) {
//            Color color = isBlack ? Color.BLACK : Color.WHITE;
//            if (getChess(xPosition, yPosition, color) != null) {
//                count++;
//            }
//            else {
//                break;
//            }
//        }
//
//        // 向 左上 寻找
//        for (xPosition = xIndex - 1, yPosition = yIndex - 1; xPosition >= 0 && yPosition >= 0;xPosition--, yPosition--) {
//            Color color = isBlack ? Color.BLACK : Color.WHITE;
//            if (getChess(xPosition, yPosition, color) != null) {
//                count++;
//            }
//            else {
//                break;
//            }
//        }
//        if (count >= 5) {
//            return true;
//        }
//        else {
//            count = 1;
//        }
//
//        // 向 右上 寻找
//        for (xPosition = xIndex + 1, yPosition = yIndex - 1; xPosition <= COLS -1 && yPosition >= 0; xPosition++, yPosition--) {
//            Color color = isBlack ? Color.BLACK : Color.WHITE;
//            if (getChess(xPosition, yPosition, color) != null) {
//                count++;
//            }
//            else {
//                break;
//            }
//        }
//
//
//        // 向 左下 寻找
//        for (xPosition = xIndex - 1, yPosition = yIndex + 1; xPosition >= 0 && yPosition <= ROWS - 1; xPosition--, yPosition++) {
//            Color color = isBlack ? Color.BLACK : Color.WHITE;
//            if (getChess(xPosition, yPosition, color) != null) {
//                count++;
//            }
//            else {
//                break;
//            }
//        }
//        if (count >= 5) {
//            return true;
//        }
//        else {
//            count = 1;
//        }
//
//
//        return false;
//    }
//
//    public void Start(){
//        if (isGameOver == false) {
//            return;
//        }
//
//        for (int i = 0; i < chessPoints.length; i++) {
//            chessPoints[i] = null;
//        }
//
//        isBlack = true;
//        isGameOver = false;
//        chessCount = 0;
//        repaint();
//    }
//
//    public void Back() {
//        if (isGameOver == true) {
//            return;
//        }
//
//        if (chessCount == 0) {
//            return;
//        }
//
//        chessPoints[chessCount - 1] = null;
//        chessCount--;
//        if (chessCount > 0) {
//            xIndex = chessPoints[chessCount - 1].getX();
//            yIndex = chessPoints[chessCount - 1].getY();
//        }
//        isBlack = !isBlack;
//        repaint();
//    }
//
//    public void restartGame() {
//        if (isGameOver == true) {
//            return;
//        }
//
//        for (int i = 0; i < chessPoints.length; i++) {
//            chessPoints[i] = null;
//        }
//
//        isGameOver = false;
//        isBlack = true;
//        chessCount = 0;
//        repaint();
//    }
//
//}


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.lang.*;

// 游戏场景
public class GameCanvas extends JPanel implements MouseListener {

    public static final int MARGIN_LEFT = 75;   // 左边距
    public static final int MARGIN_TOP = 195;   // 左边距
    public static final int GRID_SPAN = 40;     // 网格间距
    public static final int ROWS = 15;  // 棋盘行数
    public static final int COLS = 15;  // 棋盘列数
    public static int chessCount = 0;   // 棋盘上棋子的个数
    public static boolean isBlack = true;             // 黑棋先手
    public static boolean isGameOver = false;         // 游戏是否结束
    ChessPoint[] chessPoints = new ChessPoint[(ROWS) * (COLS)]; // 15*15个交点 15*15个棋子
    public static int chessSize = 35;
    public static int xIndex;                 // 最后一枚棋子的横坐标
    public static int yIndex;                 // 最后一枚棋子的纵坐标
    private Color colorChess;           // 棋子的颜色
    private Image img;                  // 场景背景图
    ControlPanel controlPanel;          // 定义控制面，调用paintTipChess()
    AiTread aiTread = new AiTread(this);
    public static boolean isAistop = true;
    public int testX1 = 0, testY1 = 0, testX2 = 0, testY2 = 0, testX3 = 0, testY3 = 0, testX4 = 0, testY4 = 0;
    boolean isAi = true;
    boolean aiIsBlack=true;
    // 构造方法
    public GameCanvas() {
        // 设置背景图片
        // 获取背景图片的尺寸
        // 用背景图的尺寸 设置背景图
        img = new ImageIcon("img/ChessBoard.jpg").getImage();
        int imageWidth = img.getWidth(this);
        int imageHeight = img.getHeight(this);
        this.setPreferredSize(new Dimension(imageWidth, imageHeight));
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

                if (xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS || isExistChess(xIndex, yIndex)) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
        if (isAi&&aiIsBlack) {
            Thread thread = new Thread(aiTread);//启动AI
            thread.start();
            isBlack=!isBlack;
        }
    }

    // 防止主方法内互相调用导致空指针，重新建立set方法
    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    // 分级调用controlPanel.paintTipChess()
    public void paintTipChess() {
        if (chessCount == 0) {
            controlPanel.paintTipChess(false);
            return;
        }
        if (isAistop) {
            if (chessCount % 2 == 1) {
                controlPanel.paintTipChess(false);
            } else {
                controlPanel.paintTipChess(true);
            }
        } else {
            if (chessCount % 2 == 0) {
                controlPanel.paintTipChess(false);
            } else {
                controlPanel.paintTipChess(true);
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (isAi){
            RadialGradientPaint paint1;
            paint1 = new RadialGradientPaint(50, 50, 20, new float[]{0.0f, 1.0f}, new Color[]{Color.RED, Color.RED});
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(paint1);
            Ellipse2D e2D1 = new Ellipse2D.Float(testX1 * GRID_SPAN + MARGIN_LEFT - 8, testY1 * GRID_SPAN + MARGIN_TOP - 8, 10, 10); // 圆形
            Ellipse2D e2D2 = new Ellipse2D.Float(testX2 * GRID_SPAN + MARGIN_LEFT - 8, testY2 * GRID_SPAN + MARGIN_TOP - 8, 10, 10); // 圆形
            Ellipse2D e2D3 = new Ellipse2D.Float(testX3 * GRID_SPAN + MARGIN_LEFT - 8, testY3 * GRID_SPAN + MARGIN_TOP - 8, 10, 10); // 圆形
            Ellipse2D e2D4 = new Ellipse2D.Float(testX4 * GRID_SPAN + MARGIN_LEFT - 8, testY4 * GRID_SPAN + MARGIN_TOP - 8, 10, 10); // 圆形
            g2d.fill(e2D1);
            g2d.fill(e2D2);
            g2d.fill(e2D3);
            g2d.fill(e2D4);
        }
    }

    // 棋盘和棋子
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this); // 画背景图
        // 画棋子
        for (int i = 0; i < chessCount; i++) {
            // 交叉点的坐标
            int xPosition = chessPoints[i].getX() * GRID_SPAN + MARGIN_LEFT; // x
            int yPosition = chessPoints[i].getY() * GRID_SPAN + MARGIN_TOP; // y

            int xBox = xPosition - ChessPoint.DIAMETER / 2; // 红色框的起点横坐标
            int yBox = yPosition - ChessPoint.DIAMETER / 2; // 红色框的起点纵坐标

            RadialGradientPaint paint;
            Graphics2D g2D = (Graphics2D) g;

            // 让圆形变得更加精细
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            colorChess = chessPoints[i].getColor();
            if (colorChess == Color.BLACK) {
                paint = new RadialGradientPaint(xPosition - ChessPoint.DIAMETER / 2 + 25, yPosition - ChessPoint.DIAMETER / 2 + 10, 20, new float[]{0.0f, 1.0f}, new Color[]{Color.GRAY, Color.BLACK});
                g2D.setPaint(paint);
            }
            if (colorChess == Color.WHITE) {
                paint = new RadialGradientPaint(xPosition - ChessPoint.DIAMETER / 2 + 25, yPosition - ChessPoint.DIAMETER / 2 + 10, 70, new float[]{0.0f, 1.0f}, new Color[]{Color.WHITE, Color.BLACK});
                g2D.setPaint(paint);
            }
            Ellipse2D e2D = new Ellipse2D.Float(xPosition - ChessPoint.DIAMETER / 2, yPosition - ChessPoint.DIAMETER / 2, chessSize, chessSize); // 圆形
            g2D.fill(e2D);
            //设置最后一个子红色圆点
            if (i == chessCount - 1) {
                RadialGradientPaint paint1;
                paint1 = new RadialGradientPaint(xBox, yBox, 20, new float[]{0.0f, 1.0f}, new Color[]{Color.RED, Color.RED});
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(paint1);
                Ellipse2D e2D1 = new Ellipse2D.Float(xBox + 10, yBox + 10, 15, 15); // 圆形
                g2d.fill(e2D1);
            }

            //设置最后一个子红色圆点

            String value = "0";
            value = String.valueOf(i);
            Font font = new Font("微软雅黑", Font.BOLD, 14); // 创建字体对象
            Graphics graphicsText = (Graphics) g;
            graphicsText.setFont(font); // 设置字体
            Color color = new Color(255, 255, 255);
            if (i % 2 == 0) {
                color = new Color(255, 255, 255);
            } else {
                color = new Color(0, 0, 0);
            }
            graphicsText.setColor(color);
            if (i < 10) {
                graphicsText.drawString(value, xBox + 13, yBox + 23); // 绘制文本
            } else if (i < 100) {
                graphicsText.drawString(value, xBox + 8, yBox + 23); // 绘制文本
            } else {
                graphicsText.drawString(value, xBox + 3, yBox + 23); // 绘制文本
            }
            if (i == 224) {
                JOptionPane.showMessageDialog(null, "噢，我的天，瞧瞧这个优秀的对局，我亲爱的上帝，\n这是汤姆斯·陈独秀先生的奖杯，是谁把它拿到这儿来的。\n来，我亲爱的汤姆斯，这是你的，摸它之前记得用蒂花之秀洗手液，\n这会让您显得庄重一些，如果您觉得不够，还可以来个橘子.", "我的天呐！和棋了！", JOptionPane.INFORMATION_MESSAGE);
                isGameOver = true;
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
        AlphaBetaCutBranch alphaBetaCutBranch = new AlphaBetaCutBranch(0, 2,1, -999990000, 999990000, 1,this);
        if (isAistop) {
            paintTipChess();
            // 游戏结束不能下
            if (isGameOver) {
                return;
            }
            xIndex = (e.getX() - MARGIN_LEFT + GRID_SPAN / 2) / GRID_SPAN;
            yIndex = (e.getY() - MARGIN_TOP + GRID_SPAN / 2) / GRID_SPAN;

            // 超出行和列的范围就不能下
            if (xIndex < 0 || xIndex > ROWS - 1 || yIndex < 0 || yIndex > COLS - 1) {
                return;
            }
            // 已经有棋子的地方也不能下
            if (isExistChess(xIndex, yIndex)) {
                return;
            }
//            if (isAi) {
//                if (!isBlack) {
//                    return;
//                }
//                isBlack = true;
//            }
            ChessPoint chessPoint = new ChessPoint(xIndex, yIndex, isBlack ? Color.BLACK : Color.WHITE);
            chessPoints[chessCount] = chessPoint;
            chessCount++;
            PlaySound playSound = new PlaySound();
            playSound.PlaySound("chess");
            String colorName = isBlack ? "黑棋" : "白棋";
            if (alphaBetaCutBranch.isWin()) {
                JOptionPane.showMessageDialog(this, "恭喜 " + colorName + " 赢了");
                isGameOver = true;
            }
            repaint();
            isAistop = true;
            if (isAi) {
                isBlack = !isBlack;
                System.out.println("before aiThread "+isBlack);
                Thread thread = new Thread(aiTread);//启动AI
                thread.start();
            }
            isBlack = !isBlack;
        }
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
    private ChessPoint getChess(int x, int y, Color color) {
        for (int i = 0; i < chessPoints.length; i++) {
            if (chessPoints[i] != null && chessPoints[i].getX() == x && chessPoints[i].getY() == y && chessPoints[i].getColor() == color) {
                return chessPoints[i];
            }
        }
        return null;
    }

    // 判断输赢
    // 赢了return true
    // 输了return false
    public boolean is成五() {
        int xPosition; // 临时变量 x的位置
        int yPosition; // 临时变量 y的位置
        int count = 1; //连续棋子的个数

        // 直线寻找

        // 向左寻找
        for (xPosition = xIndex - 1; xPosition >= 0; xPosition--) {
            Color color = isBlack ? Color.BLACK : Color.WHITE;
            if (getChess(xPosition, yIndex, color) != null) {
                count++;
            } else {
                break;
            }
        }

        // 向右寻找
        for (xPosition = xIndex + 1; xPosition <= COLS - 1; xPosition++) {
            Color color = isBlack ? Color.BLACK : Color.WHITE;
            if (getChess(xPosition, yIndex, color) != null) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        } else {
            count = 1;
        }

        // 向上寻找
        for (yPosition = yIndex - 1; yPosition >= 0; yPosition--) {
            Color color = isBlack ? Color.BLACK : Color.WHITE;
            if (getChess(xIndex, yPosition, color) != null) {
                count++;
            } else {
                break;
            }
        }

        // 向下寻找
        for (yPosition = yIndex + 1; yPosition <= ROWS - 1; yPosition++) {
            Color color = isBlack ? Color.BLACK : Color.WHITE;
            if (getChess(xIndex, yPosition, color) != null) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        } else {
            count = 1;
        }

        // 斜向寻找

        // 向 右下 寻找
        for (xPosition = xIndex + 1, yPosition = yIndex + 1; xPosition <= COLS - 1 && yPosition <= ROWS - 1; xPosition++, yPosition++) {
            Color color = isBlack ? Color.BLACK : Color.WHITE;
            if (getChess(xPosition, yPosition, color) != null) {
                count++;
            } else {
                break;
            }
        }

        // 向 左上 寻找
        for (xPosition = xIndex - 1, yPosition = yIndex - 1; xPosition >= 0 && yPosition >= 0; xPosition--, yPosition--) {
            Color color = isBlack ? Color.BLACK : Color.WHITE;
            if (getChess(xPosition, yPosition, color) != null) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        } else {
            count = 1;
        }

        // 向 右上 寻找
        for (xPosition = xIndex + 1, yPosition = yIndex - 1; xPosition <= COLS - 1 && yPosition >= 0; xPosition++, yPosition--) {
            Color color = isBlack ? Color.BLACK : Color.WHITE;
            if (getChess(xPosition, yPosition, color) != null) {
                count++;
            } else {
                break;
            }
        }

        // 向 左下 寻找
        for (xPosition = xIndex - 1, yPosition = yIndex + 1; xPosition >= 0 && yPosition <= ROWS - 1; xPosition--, yPosition++) {
            Color color = isBlack ? Color.BLACK : Color.WHITE;
            if (getChess(xPosition, yPosition, color) != null) {
                count++;
            } else {
                break;
            }
        }
        if (count >= 5) {
            return true;
        } else {
            count = 1;
        }
        return false;
    }


    // 游戏结束后开始游戏
    // 将游戏参数返回初始状态
    public void Start() {
        if (isGameOver == false) {
            return;
        }

        for (int i = 0; i < chessPoints.length; i++) {
            chessPoints[i] = null;
        }

        isBlack = true;
        isGameOver = false;
        chessCount = 0;
        repaint();
        paintTipChess();
    }

    // 游戏进行时悔棋
    // 游戏结束或者棋盘上没有旗子时不能悔棋
    // 悔棋后相关操作
    public void Back() {
        if (isGameOver == true) {
            isGameOver = false;
        }

        if (chessCount == 0) {
            return;
        }

        chessPoints[chessCount - 1] = null;
        chessPoints[chessCount - 2] = null;
        chessCount--;
        chessCount--;
        if (chessCount > 0) {
            xIndex = chessPoints[chessCount - 1].getX();
            yIndex = chessPoints[chessCount - 1].getY();
            xIndex = chessPoints[chessCount - 2].getX();
            yIndex = chessPoints[chessCount - 2].getY();
        }
        paintTipChess();
//        isBlack = !isBlack;
        repaint();
    }

    // 游戏进行时重新开始游戏
    // 游戏结束 重新开始游戏没有效果
    public void restartGame() {
//        if (isGameOver == true) {
//            return;
//        }

        for (int i = 0; i < chessPoints.length; i++) {
            chessPoints[i] = null;
        }
        isGameOver = false;
        isBlack = true;
        chessCount = 0;
        if (isAi&&aiIsBlack) {
            Thread thread = new Thread(aiTread);//启动AI
            thread.start();
            isBlack=!isBlack;
        }
        repaint();
        paintTipChess();
    }

    public int getChessColor(int x, int y) {
        for (int i = 0; i < chessPoints.length; i++) {
            if (chessPoints[i] != null && chessPoints[i].getX() == x
                    && chessPoints[i].getY() == y && chessPoints[i].getColor() == Color.black) {
                return 0;
            } else if (chessPoints[i] != null && chessPoints[i].getX() == x
                    && chessPoints[i].getY() == y && chessPoints[i].getColor() == Color.white) {
                return 1;
            }
        }
        return 2;

    }


}
