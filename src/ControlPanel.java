import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ControlPanel extends JPanel {
    private JLabel next;
    private JPanel toolBar,tipBar;
    private JButton startButton, backButton, restartButton;
    private TipPanel tipPanel;

    private Border border=new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148,145,140));

    MyItemListener myItemListener;

    public ControlPanel() {
        this.setLayout(new GridLayout(2,1));

        tipBar = new JPanel(new GridLayout(2,1));
        next = new JLabel("<html><H1>下一手</H1></html>");
        tipPanel = new TipPanel();
        tipBar.add(next, BorderLayout.NORTH);
        tipBar.add(tipPanel, BorderLayout.SOUTH);
        tipBar.setBorder(border);


        toolBar = new JPanel(new GridLayout(3,1,0,20));
        startButton = new JButton("开始");
        backButton = new JButton("悔棋");
        restartButton = new JButton("重新开始");
        toolBar.add(startButton);
        toolBar.add(backButton);
        toolBar.add(restartButton);

        myItemListener = new MyItemListener();
        startButton.addActionListener(myItemListener);
        backButton.addActionListener(myItemListener);
        restartButton.addActionListener(myItemListener);

//        toolBar.setLayout(new FlowLayout( FlowLayout.LEFT,20,40));

        add(tipBar, BorderLayout.NORTH);
        add(toolBar, BorderLayout.SOUTH);
        this.setBorder(border);
//        this.setSize(200,650);
    }

    private class MyItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Object obj=e.getSource();//获得事件源
            if(obj==startButton){
                // 开始游戏
                //JFiveFrame.this内部类引用外部类
                System.out.println("开始游戏");
//                chessBoard.restartGame();
            }
            else if (obj==restartButton){
                // 重新开始
                System.out.println("重新开始");
            }
            else if (obj==backButton){
                // 悔棋
                System.out.println("悔棋");
//                chessBoard.goback();
            }
        }
    }
}