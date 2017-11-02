import javax.swing.*;
import java.awt.*;

public class GobangGame extends JFrame {
    private ControlPanel controlPanel;
    private GameCanvas gameCanvas;
    private GameMenu gameMenu;
    public GobangGame(){
        controlPanel = new ControlPanel();
        gameCanvas = new GameCanvas();
        gameMenu = new GameMenu();

        // 将游戏场景 控制面板 菜单加进窗口
        this.add(gameCanvas, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.EAST);
        setJMenuBar(gameMenu);

        // 设置尺寸和不可改变大小
        this.setSize(900,650);
        this.setResizable(false);

        // 设置界面关闭事件
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        play();
    }
    public void play(){
        Thread thread = new Thread();
        thread.start();
//        controlPanel.requestFocus();
    }
    public static void main(String[] args) {
        GobangGame gobangGame = new GobangGame();
        gobangGame.setVisible(true);
    }
}
