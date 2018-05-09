import javax.swing.*;
import java.awt.*;

public class AiTread implements Runnable {
    private GameCanvas gameCanvas;
    public AiTread(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
    }


    public void run() {
        System.out.println("aiThread "+gameCanvas.isBlack);
        AI ai = new AI();
        ai.Ai(gameCanvas);
        if (gameCanvas.chessCount == 0) {
            ChessPoint aichessPoint = new ChessPoint(ai.xChess, ai.yChess, Color.BLACK);
            gameCanvas.xIndex = 9;
            gameCanvas.yIndex = 9;
            gameCanvas.chessPoints[gameCanvas.chessCount] = aichessPoint;
        }
        if ((ai.xChess != -1 || ai.yChess != -1) && gameCanvas.isAistop && gameCanvas.chessCount != 0) {
            gameCanvas.paintTipChess();
            gameCanvas.isAistop = false;
            ChessPoint aichessPoint = new ChessPoint(ai.xChess, ai.yChess, gameCanvas.aiIsBlack ? Color.BLACK : Color.WHITE);
            gameCanvas.xIndex = ai.xChess;
            gameCanvas.yIndex = ai.yChess;
            gameCanvas.chessPoints[gameCanvas.chessCount] = aichessPoint;
            gameCanvas.chessCount++;
            String colorName = gameCanvas.aiIsBlack ? "黑棋" : "白棋";
            AlphaBetaCutBranch alphaBetaCutBranch = new AlphaBetaCutBranch(0, 2,1, -999990000, 999990000, 1,gameCanvas);
            if (alphaBetaCutBranch.isWin()) {
                JOptionPane.showMessageDialog(gameCanvas, "恭喜 " + colorName + " 赢了");
                gameCanvas.isGameOver = true;
            }
            ai.xChess = 0;
            ai.yChess = 0;
            gameCanvas.repaint();
            PlaySound playSound = new PlaySound();
            playSound.PlaySound("chess");
            gameCanvas.isAistop = true;
            TipPanel.isBlack = false;
        }
        if (gameCanvas.chessCount==0){
            gameCanvas.chessCount++;
        }
    }
}