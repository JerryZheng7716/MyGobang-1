import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JPanel{
    public static final int MARGIN=30;//边距
    public static final int GRID_SPAN=35;//网格间距
    public static final int ROWS=15;//棋盘行数
    public static final int COLS=15;//棋盘列数


    Image img;

    public void paintComponent(Graphics g){

        super.paintComponent(g);//画棋盘

//        int imgWidth= img.getWidth(this);
//        int imgHeight=img.getHeight(this);//获得图片的宽度与高度
//        int FWidth=getWidth();
//        int FHeight=getHeight();//获得窗口的宽度与高度
//        int x=(FWidth-imgWidth)/2;
//        int y=(FHeight-imgHeight)/2;
//        g.drawImage(img, x, y, null);


        for(int i=0;i<=ROWS;i++){//画横线
            g.drawLine(MARGIN, MARGIN+i*GRID_SPAN, MARGIN+COLS*GRID_SPAN, MARGIN+i*GRID_SPAN);
//            g.drawLine();
        }
        for(int i=0;i<=COLS;i++){//画竖线
            g.drawLine(MARGIN+i*GRID_SPAN, MARGIN, MARGIN+i*GRID_SPAN, MARGIN+ROWS*GRID_SPAN);

        }
    }
}
