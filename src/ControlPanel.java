import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// 控制面板
public class ControlPanel extends JPanel {
    private JPanel emptyJpanel;
    private JPanel toolBar,tipBar;
    private JButton startButton, backButton, restartButton,soundMusic,backMusic;
    private JButton emptyButton_one, emptyButton_two, emptyButton_three,emptyButton_four, emptyButton_five;
    private TipPanel tipPanel;
    public static boolean isPlayBackMusic=true;
    MusicTread musicTread = new MusicTread("000");
    Thread thread =new Thread(musicTread);


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
    //音效按钮4张图片
    ImageIcon icon_on_openSound = new ImageIcon("img/on_openSound.png");
    ImageIcon icon_on_closeSound = new ImageIcon("img/on_closeSound.png");
    ImageIcon icon_press_openSound = new ImageIcon("img/press_openSound.png");
    ImageIcon icon_press_closeSound = new ImageIcon("img/press_closeSound.png");
    //音乐按钮4张图片
    ImageIcon icon_on_openMusic = new ImageIcon("img/on_openMusic.png");
    ImageIcon icon_on_closeMusic = new ImageIcon("img/on_closeMusic.png");
    ImageIcon icon_press_openMusic = new ImageIcon("img/press_openMusic.png");
    ImageIcon icon_press_closeMusic = new ImageIcon("img/press_closeMusic.png");


    public ControlPanel(GameCanvas gameCanvas) throws IOException {
        this.setPreferredSize(new Dimension(164, 800));// 设置自身JPanel的大小
        thread.start();

        img = Toolkit.getDefaultToolkit().getImage("img/ControlBoard.jpg"); // 获取背景图

        this.gameCanvas = gameCanvas;

        //this.setLayout(new GridLayout(2,1));

        tipBar = new JPanel();
        tipBar.setPreferredSize(new Dimension(164,300));

        emptyJpanel = new JPanel();
        emptyJpanel.setBackground(null);
        emptyJpanel.setOpaque(false);
        emptyJpanel.setPreferredSize(new Dimension(164,200));

        tipPanel = new TipPanel(gameCanvas);
        tipPanel.setBackground(null);
        tipPanel.setOpaque(false);
        tipPanel.setPreferredSize(new Dimension(164,100));

        tipBar.add(emptyJpanel, BorderLayout.NORTH);
        tipBar.add(tipPanel, BorderLayout.SOUTH);

        toolBar = new JPanel();
//        new GridLayout(4,1)
        toolBar.setPreferredSize(new Dimension(164,480));
        //加入边框便于调试
//        toolBar.setBorder(BorderFactory.createLineBorder(Color.red));
//        tipBar.setBorder(BorderFactory.createLineBorder(Color.red));



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

        emptyButton_one = new JButton();
        emptyButton_one.setPreferredSize(new Dimension(118, 20));
        emptyButton_one.setContentAreaFilled(false);
        emptyButton_one.setMargin(new Insets(0, 0, 0, 0));
        emptyButton_one.setBorderPainted(false);
        emptyButton_one.setBorder(null);

        backButton = new JButton();
        backButton.setIcon(icon_on_back);
        backButton.setUI(new BasicButtonUI());
        backButton.setPreferredSize(new Dimension(118, 58));
        backButton.setContentAreaFilled(false);
        backButton.setMargin(new Insets(0, 23, 0, 23));
        backButton.setBorderPainted(false);
        backButton.setBorder(null);

        emptyButton_two = new JButton();
        emptyButton_two.setPreferredSize(new Dimension(118, 20));
        emptyButton_two.setContentAreaFilled(false);
        emptyButton_two.setMargin(new Insets(0, 0, 0, 0));
        emptyButton_two.setBorderPainted(false);
        emptyButton_two.setBorder(null);

        restartButton = new JButton();
        restartButton.setIcon(icon_on_restart);
        restartButton.setUI(new BasicButtonUI());
        restartButton.setPreferredSize(new Dimension(118, 58));
        restartButton.setContentAreaFilled(false);
        restartButton.setMargin(new Insets(0, 23, 0, 23));
        restartButton.setBorderPainted(false);
        restartButton.setBorder(null);

        emptyButton_three = new JButton();
        emptyButton_three.setPreferredSize(new Dimension(118, 20));
        emptyButton_three.setContentAreaFilled(false);
        emptyButton_three.setMargin(new Insets(0, 0, 0, 0));
        emptyButton_three.setBorderPainted(false);
        emptyButton_three.setBorder(null);

        soundMusic = new JButton();
        soundMusic.setIcon(icon_on_closeSound);
        soundMusic.setUI(new BasicButtonUI());
        soundMusic.setPreferredSize(new Dimension(118, 58));
        soundMusic.setContentAreaFilled(false);
        soundMusic.setMargin(new Insets(0, 23, 0, 23));
        soundMusic.setBorderPainted(false);
        soundMusic.setBorder(null);

        emptyButton_four = new JButton();
        emptyButton_four.setPreferredSize(new Dimension(118, 20));
        emptyButton_four.setContentAreaFilled(false);
        emptyButton_four.setMargin(new Insets(0, 0, 0, 0));
        emptyButton_four.setBorderPainted(false);
        emptyButton_four.setBorder(null);

        backMusic = new JButton();
        backMusic.setIcon(icon_on_closeMusic);
        backMusic.setUI(new BasicButtonUI());
        backMusic.setPreferredSize(new Dimension(118, 58));
        backMusic.setContentAreaFilled(false);
        backMusic.setMargin(new Insets(0, 23, 0, 23));
        backMusic.setBorderPainted(false);
        backMusic.setBorder(null);

        emptyButton_five = new JButton();
        emptyButton_five.setPreferredSize(new Dimension(118, 20));
        emptyButton_five.setContentAreaFilled(false);
        emptyButton_five.setMargin(new Insets(0, 0, 0, 0));
        emptyButton_five.setBorderPainted(false);
        emptyButton_five.setBorder(null);

        toolBar.add(emptyButton_five);
        toolBar.add(startButton);
        toolBar.add(emptyButton_one);
        toolBar.add(backButton);
        toolBar.add(emptyButton_two);
        toolBar.add(restartButton);
        toolBar.add(emptyButton_three);
        toolBar.add(soundMusic);
        toolBar.add(emptyButton_four);
        toolBar.add(backMusic);




        myItemListener = new MyItemListener();
        startButton.addMouseListener(myItemListener);
        backButton.addMouseListener(myItemListener);
        restartButton.addMouseListener(myItemListener);
        soundMusic.addMouseListener(myItemListener);
        backMusic.addMouseListener(myItemListener);


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

    private class MyItemListener implements MouseListener{
        PlaySound playSound=new PlaySound();
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            Object obj=e.getSource();//获得事件源
            if(obj==startButton){
                // 开始游戏
                playSound.PlaySound("button");
                System.out.println("开始游戏");
                startButton.setIcon(icon_press_start);
                // restartGame();
            }
            else if (obj==backButton){
                // 悔棋
                playSound.PlaySound("button");
                System.out.println("悔棋");
                gameCanvas.Back();
                backButton.setIcon(icon_press_back);
            }
            else if (obj==restartButton){
                // 重新开始
                playSound.PlaySound("button");
                System.out.println("重新开始");
                gameCanvas.restartGame();
                restartButton.setIcon(icon_press_restart);
            }else if (obj==soundMusic){
                if (PlaySound.isPlaySound){
                    PlaySound.isPlaySound =false;
                    playSound.PlaySound("button");
                    soundMusic.setIcon(icon_press_closeSound);
                }
                else {
                    PlaySound.isPlaySound =true;
                    playSound.PlaySound("button");
                    soundMusic.setIcon(icon_press_openSound);
                }
                System.out.println(PlaySound.isPlaySound);
            }else if (obj==backMusic){
                if (isPlayBackMusic){
                    try {
                        thread.wait();
                        isPlayBackMusic=false;
                        backMusic.setIcon(icon_press_openMusic);
                        System.out.println("关闭音乐");
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }else {
                    thread.start();
                    backMusic.setIcon(icon_press_closeMusic);
                    System.out.println("打开音乐");
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Object obj=e.getSource();//获得事件源
            if(obj==startButton){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                startButton.setIcon(icon_on_start);
            }
            else if (obj==backButton){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                backButton.setIcon(icon_on_back);
            }
            else if (obj==restartButton){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                restartButton.setIcon(icon_on_restart);
            }else if (obj==soundMusic){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if (PlaySound.isPlaySound)
                    soundMusic.setIcon(icon_on_closeSound);
                else soundMusic.setIcon(icon_on_openSound);
            }else if (obj==backMusic){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if (isPlayBackMusic)
                    backMusic.setIcon(icon_on_closeMusic);
                else backMusic.setIcon(icon_on_openMusic);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(new Cursor(Cursor.HAND_CURSOR));

        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

}
