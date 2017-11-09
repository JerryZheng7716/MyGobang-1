import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

// 控制面板
public class ControlPanel extends JPanel {
    private JPanel emptyJpanel;
    private JPanel toolBar,tipBar;
    private JButton startButton, backButton, restartButton;
    private JButton emptyButton_one, emptyButton_two, emptyButton_three;
    private TipPanel tipPanel;

//    private Border border=new EtchedBorder(EtchedBorder.RAISED, Color.white, new Color(148,145,140));

    MyItemListener myItemListener;

    private GameCanvas gameCanvas;
    private Image img;

    ImageIcon icon_on_start = new ImageIcon("img/on_start.png");
    ImageIcon icon_press_start = new ImageIcon("img/press_start.png");
    ImageIcon icon_on_restart = new ImageIcon("img/on_restart.png");
    ImageIcon icon_press_restart = new ImageIcon("img/press_restart.png");
    ImageIcon icon_on_back = new ImageIcon("img/on_back.png");
    ImageIcon icon_press_back = new ImageIcon("img/press_back.png");



    public ControlPanel(GameCanvas gameCanvas) {
        this.setPreferredSize(new Dimension(164, 800));// 设置自身JPanel的大小

        img = Toolkit.getDefaultToolkit().getImage("img/ControlBoard.jpg"); // 获取背景图

        this.gameCanvas = gameCanvas;

        this.setLayout(new GridLayout(2,1));

        tipBar = new JPanel();

        emptyJpanel = new JPanel();
        emptyJpanel.setBackground(null);
        emptyJpanel.setOpaque(false);
        emptyJpanel.setPreferredSize(new Dimension(164,220));

        tipPanel = new TipPanel(gameCanvas);
        tipPanel.setBackground(null);
        tipPanel.setOpaque(false);
        tipPanel.setPreferredSize(new Dimension(164,170));

        tipBar.add(emptyJpanel, BorderLayout.NORTH);
        tipBar.add(tipPanel, BorderLayout.SOUTH);

        toolBar = new JPanel();
//        new GridLayout(4,1)

        startButton = new JButton();
        startButton.setIcon(icon_on_start);
        // 去掉小框
        startButton.setUI(new BasicButtonUI());
        // 设置尺寸
        startButton.setPreferredSize(new Dimension(118, 58));
        // 改透明度
        startButton.setContentAreaFilled(false);
        // 外边距
        startButton.setMargin(new Insets(0, 23, 0, 23));
        // 去掉外边框
        startButton.setBorderPainted(false);
        startButton.setBorder(null);


        backButton = new JButton();
        backButton.setIcon(icon_on_back);
        backButton.setUI(new BasicButtonUI());
        backButton.setPreferredSize(new Dimension(118, 58));
        backButton.setContentAreaFilled(false);
        backButton.setMargin(new Insets(0, 23, 0, 23));
        backButton.setBorderPainted(false);
        backButton.setBorder(null);


        restartButton = new JButton();
        restartButton.setIcon(icon_on_restart);
        restartButton.setUI(new BasicButtonUI());
        restartButton.setPreferredSize(new Dimension(118, 58));
        restartButton.setContentAreaFilled(false);
        restartButton.setMargin(new Insets(0, 23, 0, 23));
        restartButton.setBorderPainted(false);
        restartButton.setBorder(null);

        emptyButton_three = new JButton();
        emptyButton_three.setPreferredSize(new Dimension(118, 58));
        emptyButton_three.setContentAreaFilled(false);
        emptyButton_three.setMargin(new Insets(0, 0, 0, 0));
        emptyButton_three.setBorderPainted(false);
        emptyButton_three.setBorder(null);

        toolBar.add(startButton);
        toolBar.add(backButton);
        toolBar.add(restartButton);
        toolBar.add(emptyButton_three);

        myItemListener = new MyItemListener();
        startButton.addActionListener(myItemListener);
        backButton.addActionListener(myItemListener);
        restartButton.addActionListener(myItemListener);


        tipBar.setBackground(null);
        tipBar.setOpaque(false);
        toolBar.setBackground(null);
        toolBar.setOpaque(false);
        add(tipBar, BorderLayout.NORTH);
        add(toolBar, BorderLayout.SOUTH);
        // repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, this); //画背景图
    }

    private class MyItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Object obj=e.getSource();//获得事件源
            if(obj==startButton){
                // 开始游戏
                System.out.println("开始游戏");
                // restartGame();
            }
            else if (obj==backButton){
                // 悔棋
                System.out.println("悔棋");
                gameCanvas.Back();
            }
            else if (obj==restartButton){
                // 重新开始
                System.out.println("重新开始");
                gameCanvas.restartGame();
            }
        }
    }
}
