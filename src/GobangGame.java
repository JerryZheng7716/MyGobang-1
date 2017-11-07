import javax.swing.*;
import java.awt.*;

// 主类
public class GobangGame extends JFrame {
    private ControlPanel controlPanel; // 左侧控制面板
    private GameCanvas gameCanvas;     // 右侧游戏场景
    private GameMenu gameMenu;         // 上方游戏菜单

    public GobangGame(){

        gameCanvas = new GameCanvas();
        gameMenu = new GameMenu(gameCanvas);
        controlPanel = new ControlPanel(gameCanvas);

        // 将游戏场景 控制面板 菜单加进窗口
        this.add(gameCanvas, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.EAST);
        setJMenuBar(gameMenu);

        // 设置尺寸和不可改变大小
        this.setSize(1000,1012);
        this.setResizable(false);

        // 设置界面关闭事件
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        play();
    }

    public void play(){
        Thread thread = new Thread();
        thread.start();
        // controlPanel.requestFocus();
    }

    // 主函数
    public static void main(String[] args) {
        GobangGame gobangGame = new GobangGame();
        gobangGame.setVisible(true);
    }
}
