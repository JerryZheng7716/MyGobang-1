import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// 游戏菜单
public class GameMenu extends JMenuBar{
    private JMenu mGame = new JMenu("游戏");
    private JMenu mWindowStyle = new JMenu("窗体风格");
    private JMenu mInfo = new JMenu("相关信息");

    private JMenuItem miStartButton = new JMenuItem("开始游戏");
    private JMenuItem miBackButton = new JMenuItem("悔棋");
    private JMenuItem mirestartButton = new JMenuItem("重新开始");

    private JMenuItem miAuthor = new JMenuItem("作者");
    private JMenuItem miGithub = new JMenuItem("Github");


    private JCheckBoxMenuItem miAsWindows = new JCheckBoxMenuItem("Windows风格");
    private JCheckBoxMenuItem miAsMotif = new JCheckBoxMenuItem("Motif风格");
    private JCheckBoxMenuItem miAsMetal = new JCheckBoxMenuItem("Metal风格", true);

    MyItemListener myItemListener;
    private GameCanvas gameCanvas;

    public GameMenu(GameCanvas gameCanvas){
        this.gameCanvas = gameCanvas;
        this.add(mGame);
        this.add(mWindowStyle);
        this.add(mInfo);

        mGame.add(miStartButton);
        mGame.add(miBackButton);
        mGame.add(mirestartButton);

        myItemListener = new MyItemListener();
        miStartButton.addActionListener(myItemListener);
        miBackButton.addActionListener(myItemListener);
        mirestartButton.addActionListener(myItemListener);

        mInfo.add(miAuthor);
        mInfo.add(miGithub);

        mWindowStyle.add(miAsWindows);
        mWindowStyle.add(miAsMotif);
        mWindowStyle.add(miAsMetal);

    }
    private class MyItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Object obj=e.getSource();//获得事件源
            if(obj==miStartButton){
                // 开始游戏
                System.out.println("开始游戏");
            }
            else if (obj==mirestartButton){
                // 悔棋
                System.out.println("悔棋");
                gameCanvas.Back();
            }
            else if (obj==miBackButton){
                // 重新开始
                System.out.println("重新开始");
                gameCanvas.restartGame();
            }
        }
    }
}
