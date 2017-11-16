import java.awt.*;

public class AI {
    GameCanvas gameCanvas;
    int xPosition; // 临时变量 x的位置
    int yPosition; // 临时变量 y的位置
    int count = 1; //连续棋子的个数
    int qiPan = 23;
    int[][] chessMap = new int[qiPan][qiPan];//0 1 2 3||19 20 21 22为墙
    int[][] chessMaptt = new int[qiPan][qiPan];
    int[][] chessMap1 = new int[15][15];
    public static int xChess=-1, yChess=-1;

    int score = 0;
    int maxSocre = -100000;
    int tiaoshi = 0;

    public void Ai(GameCanvas gameCanvas) {
        this.gameCanvas = gameCanvas;
        pressHighChess();
//        pressLowChess();

        // System.out.println("x"+xChess+"y"+yChess);
//        System.out.println("全局评估最高分" + maxSocre);
//        System.out.println("调试图像");
//        System.out.println(tiaoshi);
//        System.out.println(xChess + "ffff" + yChess);
//        for (int s = 0; s < qiPan; s++) {
//            for (int p = 0; p < qiPan; p++) {
//                System.out.print(chessMap[s][p]);
//            }
//            System.out.println("");
//        }

    }

//    private void getMap(){//调试地图
//        for (xPosition=0; xPosition<15;xPosition++) {
//            for (yPosition=0;yPosition<15;yPosition++){
//                chessMap1[xPosition][yPosition]=2;
//            }
//        }
//        for (int i = 0; i < qiPan; i++) {
//            for (int j = 0; j < qiPan; j++) {
//                if (i == 0 || i == 1 || i == 2 || i == 3 || i == 19 || i == 20 || i == 21 || i == 22) {
//                    chessMap[i][j]=4;
//                }else if (j == 0 || j == 1 || j == 2 || j == 3 || j == 19 || j == 20 || j == 21 || j == 22) {
//                    chessMap[i][j]=4;
//                }else
//                    chessMap[i][j]=chessMap1[j-4][i-4];
//                //System.out.print(chessMap[i][j]);
//            }
//            //System.out.println("");
//        }
//                        chessMap[10][10]=1;
//                    chessMap[11][9]=1;
//                chessMap[12][8]=1;
//            chessMap[13][7]=1;
//        chessMap[14][6]=1;
//    }

//isWin+990000000;  00000
//isHuosi+1000000;  200002
//isSisiA+2500; 200001
//isHuosan+3000;    2200022
//isSisanA+500; 220001
//isHuoer+650;  22200222
//isSierA+150;  222001
    private void printMap(){
        for (int s = 0; s < qiPan; s++) {
            for (int p = 0; p < qiPan; p++) {
                System.out.print(chessMap[s][p]);
            }
            System.out.println("");
        }
    }


    private void getMap(){
        for (xPosition=0; xPosition<15;xPosition++) {
            for (yPosition=0;yPosition<15;yPosition++){
                chessMap1[xPosition][yPosition]=gameCanvas.getChessColor(xPosition,yPosition);
            }
        }
        for (int i = 0; i < qiPan; i++) {
            for (int j = 0; j < qiPan; j++) {
                if (i == 0 || i == 1 || i == 2 || i == 3 || i == 19 || i == 20 || i == 21 || i == 22) {
                    chessMap[i][j]=4;
                }else if (j == 0 || j == 1 || j == 2 || j == 3 || j == 19 || j == 20 || j == 21 || j == 22) {
                    chessMap[i][j]=4;
                }else
                    chessMap[i][j]=chessMap1[j-4][i-4];
                //System.out.print(chessMap[i][j]);
            }
            //System.out.println("");
        }
    }

    private void SeachLandscape(int color,int othetColor) {
        //活4死4A的横向搜索
        //对黑子进行向左边的搜索
        doEach:
        for (int i = 4; i < 19; i++) {
            int count = 0;
            doFour:
            for (int j = 4; j < 19; j++) {
                if (chessMap[i][j] == color) {
                    count++;
//                    System.out.println("找到第一个黑子" + count);
                    for (int k = j - 1; k > j - 6; k--) {
                        if (chessMap[i][k] == 4) break doFour;
//                        if (chessMap[i][k]==othetColor) {
//                            break ;
//                            count=0;
//                            System.out.println("找到白子，向右搜索");
//                            for (int l = k+1; l < k+6; l++) {
//                                if (chessMap[i][l]==4)break doFour;
//                                if (chessMap[i][l]==2||chessMap[i][l]==othetColor||chessMap[i][l]==4) {
//                                    count=0;
//                                    System.out.println("找到非本子，跳出1循环");
//                                    break ;
//                                }
//                                if (chessMap[i][l]==color) {
//                                    count++;
//                                    System.out.println("找到黑子"+count);
//                                }
//                                if (count==4){
//                                    if (chessMap[i][l-4]==2&&chessMap[i][l+1]==2){
//                                        isHuosi();
//                                    }
//                                    if (chessMap[i][l-4]==2&&(chessMap[i][l+1]==othetColor||chessMap[i][l+1]==4)){
//                                        isSisiA();
//                                    }
//                                    if ((chessMap[i][l-4]==othetColor||chessMap[i][l-4]==4)&&chessMap[i][l+1]==2){
//                                        isSisiA();
//                                    }
//                                }
//                                if (count==3){
//                                    if (chessMap[i][l-4]==2&&chessMap[i][l-3]==2&&chessMap[i][l+1]==2&&chessMap[i][l+2]==2){
//                                        isHuosan();
//                                    }
//                                    if (chessMap[i][l-4]==2&&chessMap[i][l-3]==2&&(chessMap[i][l+1]==othetColor||chessMap[i][l+1]==4)){
//                                        isSisanA();
//                                    }
//                                    if (chessMap[i][l+1]==2&&chessMap[i][l+2]==2&&(chessMap[i][l-3]==othetColor||chessMap[i][l-3]==4)){
//                                        isSisanA();
//                                    }
//                                }
//                                if (count==2){
//                                    if (chessMap[i][l-4]==2&&chessMap[i][l-3]==2&&chessMap[i][l-2]==2&&chessMap[i][l+1]==2&&chessMap[i][l+2]==2&&chessMap[i][l+3]==2){
//                                        isHuoer();
//                                    }
//                                    if (chessMap[i][l-4]==2&&chessMap[i][l-3]==2&&chessMap[i][l-2]==2&&(chessMap[i][l+1]==othetColor||chessMap[i][l+1]==4)){
//                                        isSierA();
//                                    }
//                                    if (chessMap[i][l+1]==2&&chessMap[i][l+2]==2&&chessMap[i][l+3]==2&&(chessMap[i][l-2]==othetColor||chessMap[i][l-2]==4)){
//                                        isSierA();
//                                    }
//                                }
//                                if (count==5){
//                                    isWin();
//                                    break doEach;
//                                }
//                            }
//                        }
                        if (chessMap[i][k] == 2 || chessMap[i][k] == othetColor || chessMap[i][k] == 4) {
                            count = 0;
//                            System.out.println("找到非本子，跳出1循环");
                            break;
                        }
                        if (chessMap[i][k] == color) {
                            count++;
//                            System.out.println("找到黑子" + count);
                        }
                        if (count == 4) {
                            if (chessMap[i][k - 1] == 2 && chessMap[i][k + 4] == 2) {
                                isHuosi();
                                if (color==1){
                                    score=score-100000;
                                }
                            }
                            if (chessMap[i][k - 1] == 2 && (chessMap[i][k + 4] == othetColor || chessMap[i][k + 4] == 4)) {
                                isSisiA();
                            }
                            if ((chessMap[i][k - 1] == othetColor || chessMap[i][k - 1] == 4) && chessMap[i][k + 4] == 2) {
                                isSisiA();
                            }
                        }
                        if (count == 3) {
                            if (chessMap[i][k + 4] == 2 && chessMap[i][k + 3] == 2 && chessMap[i][k - 1] == 2 && chessMap[i][k - 2] == 2) {
                                isHuosan();
                                if (color==1){
                                    score=score-1000;
                                }
                            }
                            if (chessMap[i][k + 4] == 2 && chessMap[i][k + 3] == 2 && (chessMap[i][k - 1] == othetColor || chessMap[i][k - 1] == 4)) {
                                isSisanA();
                            }
                            if (chessMap[i][k - 1] != 4){
                                if (chessMap[i][k + 4] == 2 && chessMap[i][k + 3] == 2 && chessMap[i][k - 1] == 2&&(chessMap[i][k - 2] == othetColor || chessMap[i][k - 2] == 4)) {
                                    isSisanA();
                                }
                            }
                            if (chessMap[i][k - 1] == 2 && chessMap[i][k - 2] == 2 && (chessMap[i][k + 3] == othetColor || chessMap[i][k + 3] == 4)) {
                                isSisanA();
                            }
                            if (chessMap[i][k + 3] != 4){
                                if (chessMap[i][k - 1] == 2 && chessMap[i][k - 2] == 2 && chessMap[i][k + 3] == 2 && (chessMap[i][k + 4] == othetColor || chessMap[i][k + 4] == 4)) {
                                    isSisanA();
                                }
                            }
                        }

                        if (count == 2) {
                            if (chessMap[i][k + 4] == 2 && chessMap[i][k + 3] == 2 && chessMap[i][k + 2] == 2 && chessMap[i][k - 1] == 2 && chessMap[i][k - 2] == 2 && chessMap[i][k - 3] == 2) {
                                isHuoer();
                            }
                            if (chessMap[i][k + 4] == 2 && chessMap[i][k + 3] == 2 && chessMap[i][k + 2] == 2 && (chessMap[i][k - 1] == othetColor || chessMap[i][k - 1] == 4)) {
                                isSierA();
                            }
                            if (chessMap[i][k - 1] == 2 && chessMap[i][k - 2] == 2 && chessMap[i][k - 3] == 2 && (chessMap[i][k + 2] == othetColor || chessMap[i][k + 2] == 4)) {
                                isSierA();
                            }
                        }
                        if (count == 5) {
                            isWin();
                            if (color==1){
                                score=score-90000000;
                            }
                            break doEach;
                        }

                    }
                }
            }
        }
        //System.out.println("全局估算得分："+score);
    }//横向搜索

    private void SeachPortrait(int color,int othetColor) {
        //活4死4A的纵向搜索
        //对黑子进行向左边的搜索
        doEach:
        for (int i = 4; i < 19; i++) {
            doFour:
            for (int j = 4; j < 19; j++) {
                count=0;
                if (chessMap[i][j] == color) {
                    count++;
                    //System.out.println("找到第一个黑子"+count);
                    for (int k = i - 1; k > i - 6; k--) {
                        if (chessMap[k][j] == 4) break doFour;
//                        if (chessMap[k][j]==othetColor) {
//                            count=0;
//                            //System.out.println("找到白子，向上搜索");
//                            for (int l = k+1; l < k+6; l++) {
//                                if (chessMap[l][j]==4)break doFour;
//                                if (chessMap[l][j]==2||chessMap[l][j]==othetColor||chessMap[l][j]==4) {
//                                    count=0;
//                                    //System.out.println("找到空子，跳出1循环");
//                                    break ;
//                                }
//
//                                if (chessMap[l][j]==color) {
//                                    count++;
//                                    //System.out.println("找到黑子"+count);
//                                }
//                                if (count==4){
//                                    if (chessMap[l-4][j]==2&&chessMap[l+1][j]==2){
//                                        isHuosi();
//                                    }
//                                    if (chessMap[l-4][j]==2&&(chessMap[l+1][j]==othetColor||chessMap[l+1][j]==4)){
//                                        isSisiA();
//                                    }
//                                    if ((chessMap[l-4][j]==othetColor && chessMap[l-4][j]==4)&&chessMap[l+1][j]==2){
//                                        isSisiA();
//                                    }
//                                }
//
//                                if (count==3){
//                                    if (chessMap[l-4][j]==2&&chessMap[l-3][j]==2&&chessMap[l+1][j]==2&&chessMap[l+2][j]==2){
//                                        isHuosan();
//                                    }
//                                    if (chessMap[l-4][j]==2&&chessMap[l-3][j]==2&&(chessMap[l+1][j]==othetColor||chessMap[l+1][j]==4)){
//                                        isSisanA();
//                                    }
//                                    if ((chessMap[l-3][j]==othetColor && chessMap[l-3][j]==4)&&chessMap[l+1][j]==2&&chessMap[l+2][j]==2){
//                                        isSisanA();
//                                    }
//                                }
//
//                                if (count==2){
//
//                                    if (chessMap[l-4][j]==2&&chessMap[l-3][j]==2&&chessMap[l-2][j]==2&&chessMap[l+1][j]==2&&chessMap[l+2][j]==2&&chessMap[l+3][j]==2){
//                                        isHuoer();
//                                    }
//                                    if (chessMap[l-4][j]==2&&chessMap[l-3][j]==2&&chessMap[l-2][j]==2&&(chessMap[l+1][j]==othetColor||chessMap[l+1][j]==4)){
//                                        isSierA();
//                                    }
//                                    if ((chessMap[l-2][j]==othetColor && chessMap[l-2][j]==4)&&chessMap[l+1][j]==2&&chessMap[l+2][j]==2&&chessMap[l+3][j]==2){
//                                        isSierA();
//                                    }
//                                }
//
//                                if (count==5){
//                                    isWin();
//                                    break doEach;
//                                }
//                            }
//                        }
                        if (chessMap[k][j] == 2 || chessMap[k][j] == othetColor || chessMap[k][j] == 4) {
                            count = 0;
                            //System.out.println("找到空子，跳出1循环");
                            break;
                        }

                        if (chessMap[k][j] == color) {
                            count++;
                            //System.out.println("找到黑子"+count);
                        }
                        if (count == 4) {
                            if (chessMap[k - 1][j] == 2 && chessMap[k + 4][j] == 2) {
                                isHuosi();
                                if (color==1){
                                    score=score-100000;
                                }
                            }
                            if (chessMap[k - 1][j] == 2 && (chessMap[k + 4][j] == othetColor || chessMap[k + 4][j] == 4)) {
                                isSisiA();
                            }
                            if ((chessMap[k - 1][j] == othetColor || chessMap[k - 1][j] == 4) && chessMap[k + 4][j] == 2) {
                                isSisiA();
                            }
                        }
                        if (count == 3) {
                            if (chessMap[k - 1][j] == 2 && chessMap[k - 2][j] == 2 && chessMap[k + 4][j] == 2 && chessMap[k + 3][j] == 2) {
                                isHuosan();
                                if (color==1){
                                    score=score-1000;
                                }
                            }
                            if (chessMap[k - 1][j] == 2 && chessMap[k - 2][j] == 2 && (chessMap[k + 3][j] == othetColor || chessMap[k + 3][j] == 4)) {
                                isSisanA();
                            }
                            if (chessMap[k + 4][j] != 4){
                                if (chessMap[k - 1][j] == 2 && chessMap[k - 2][j] == 2 && chessMap[k + 3][j] == 2 && (chessMap[k + 4][j] == othetColor || chessMap[k + 4][j] == 4)) {
                                    isSisanA();
                                }
                            }
                            if ((chessMap[k - 1][j] == othetColor || chessMap[k - 1][j] == 4) && chessMap[k + 4][j] == 2 && chessMap[k + 3][j] == 2) {
                                isSisanA();
                            }
                            if (chessMap[k - 1][j] != 4){
                                if ((chessMap[k - 2][j] == othetColor || chessMap[k - 2][j] == 4) && chessMap[k - 1][j] == 2 && chessMap[k + 4][j] == 2 && chessMap[k + 3][j] == 2) {
                                    isSisanA();
                                }
                            }
                        }

                        if (count == 2) {
                            if (chessMap[k - 1][j] == 2 && chessMap[k - 2][j] == 2 && chessMap[k - 3][j] == 2 && chessMap[k + 4][j] == 2 && chessMap[k + 3][j] == 2 && chessMap[k + 2][j] == 2) {
                                isHuoer();
                            }
                            if (chessMap[k - 1][j] == 2 && chessMap[k - 2][j] == 2 && chessMap[k - 3][j] == 2 && (chessMap[k + 2][j] == othetColor || chessMap[k + 2][j] == 4)) {
                                isSierA();
                            }
                            if ((chessMap[k - 1][j] == othetColor || chessMap[k - 1][j] == 4) && chessMap[k + 4][j] == 2 && chessMap[k + 3][j] == 2 && chessMap[k + 2][j] == 2) {
                                isSierA();
                            }
                        }
                        if (count == 5) {
                            isWin();
                            if (color==1){
                                score=score-90000000;
                            }
                            break doEach;
                        }

                    }
                }
            }
        }
//        System.out.println("全局估算得分：" + score);
    }//纵向搜索

    private void SeachSlant(int color,int othetColor) {
        //活4死4A的纵向搜索
        //对黑子进行向左边的搜索
        doEach:
        for (int i = 4; i < 19; i++) {
            for (int j = 4; j < 19; j++) {
                int count = 0;
                if (chessMap[i][j] == color) {
                    count++;
                    //System.out.println("找到第一个黑子"+count);
                    //进行右下搜索
                    for (int k = 1; k < 5; k++) {
                        if (chessMap[i + k][j + k] == 2 || chessMap[i + k][j + k] == othetColor || chessMap[i + k][j + k] == 4) {
                            break;//找到非本子。停止搜索
                        }

                        if (chessMap[i + k][j + k] == color) {
                            count++;
                            //System.out.println("找到黑子"+count);
                        }
                    }

                    if (count == 4) {
                        if (chessMap[i - 1][j - 1] == 2 && chessMap[i + 4][j + 4] == 2) {
                            isHuosi();
                            if (color==1){
                                score=score-100000;
                            }
                        }
                        if (chessMap[i - 1][j - 1] == 2 && (chessMap[i + 4][j + 4] == othetColor || chessMap[i + 4][j + 4] == 4)) {
                            isSisiA();
                        }
                        if ((chessMap[i - 1][j - 1] == othetColor || chessMap[i - 1][j - 1] == 4) && chessMap[i + 4][j + 4] == 2) {
                            isSisiA();
                        }
                    }
                    if (count == 3) {
                        if (chessMap[i - 1][j - 1] == 2 && chessMap[i - 2][j - 2] == 2 && chessMap[i + 4][j + 4] == 2 && chessMap[i + 3][j + 3] == 2) {
                            isHuosan();
                            if (color==1){
                                score=score-1000;
                            }
                        }
                        if (chessMap[i - 1][j - 1] == 2 && chessMap[i - 2][j - 2] == 2 && (chessMap[i + 3][j + 3] == othetColor || chessMap[i + 3][j + 3] == 4)) {
                            isSisanA();//1000221
                        }
                        if (chessMap[i + 3][j + 3] != 4) {
                            if (chessMap[i - 1][j - 1] == 2 && chessMap[i - 2][j - 2] == 2 && chessMap[i + 3][j + 3] == 2 && (chessMap[i + 4][j + 4] == othetColor || chessMap[i + 4][j + 4] == 4)) {
                                isSisanA();//12000221
                            }
                        }
                        if ((chessMap[i - 1][j - 1] == othetColor || chessMap[i - 1][j - 1] == 4) && chessMap[i + 4][j + 4] == 2 && chessMap[i + 3][j + 3] == 2) {
                            isSisanA();
                        }
                        if (chessMap[i - 1][j - 1] != 4) {
                            if ((chessMap[i - 2][j - 2] == othetColor || chessMap[i - 2][j - 2] == 4) && chessMap[i - 1][j - 1] == 2 && chessMap[i + 4][j + 4] == 2 && chessMap[i + 3][j + 3] == 2) {
                                isSisanA();
                            }
                        }

                    }

                    if (count == 2) {
                        if (chessMap[i - 1][j - 1] == 2 && chessMap[i - 2][j - 2] == 2 && chessMap[i - 3][j - 3] == 2 && chessMap[i + 4][j + 4] == 2 && chessMap[i + 3][j + 3] == 2 && chessMap[i + 2][j + 2] == 2) {
                            isHuoer();
                        }
                        if (chessMap[i - 1][j - 1] == 2 && chessMap[i - 2][j - 2] == 2 && chessMap[i - 3][j - 3] == 2 && (chessMap[i + 2][j + 2] == othetColor || chessMap[i + 2][j + 2] == 4)) {
                            isSierA();
                        }
                        if ((chessMap[i - 1][j - 1] == othetColor || chessMap[i - 1][j - 1] == 4) && chessMap[i + 4][j + 4] == 2 && chessMap[i + 3][j + 3] == 2 && chessMap[i + 2][j + 2] == 2) {
                            isSierA();
                        }
                    }
                    if (count == 5) {
                        isWin();
                        if (color==1){
                            score=score-90000000;
                        }
                        break doEach;

                    }

                    //以上右下搜索结束↑↑↑↑↑↑
                    //进行左下搜索
                    count=1;
                    for (int k = 1; k < 6; k++) {
                        if (chessMap[i + k][j - k] == 2 || chessMap[i + k][j - k] == othetColor || chessMap[i + k][j - k] == 4) {
//                            System.out.println("找到非本子。停止搜索");
                            break;//找到非本子。停止搜索

                        }

                        if (chessMap[i + k][j - k] == color) {
                            count++;
//                            System.out.println("找到黑子" + count);
                        }
                    }
                    if (count == 4) {
                        if (chessMap[i - 1][j + 1] == 2 && chessMap[i + 4][j - 4] == 2) {
                            isHuosi();
                            if (color==1){
                                score=score-100000;
                            }
                        }
//                        System.out.println(chessMap[i - 1][j + 1] + " 4子连珠 " + chessMap[i + 4][j - 4]);
                        if (chessMap[i - 1][j + 1] == 2 && (chessMap[i + 4][j - 4] == othetColor || chessMap[i + 4][j - 4] == 4)) {
                            isSisiA();
                        }
                        if ((chessMap[i - 1][j + 1] == othetColor || chessMap[i - 1][j + 1] == 4) && chessMap[i + 4][j - 4] == 2) {
                            isSisiA();
                        }
                    }
                    if (count == 3) {
                        if (chessMap[i - 1][j + 1] == 2 && chessMap[i - 2][j + 2] == 2 && chessMap[i + 4][j - 4] == 2 && chessMap[i + 3][j - 3] == 2) {
                            isHuosan();
                            if (color==1){
                                score=score-1000;
                            }
                        }

                        if (chessMap[i - 1][j + 1] == 2 && chessMap[i - 2][j + 2] == 2 && (chessMap[i + 3][j - 3] == othetColor || chessMap[i + 3][j - 3] == 4)) {
                            isSisanA();
                        }
                        if (chessMap[i + 3][j - 3] !=4){
                            if (chessMap[i - 1][j + 1] == 2 && chessMap[i - 2][j + 2] == 2 && chessMap[i + 3][j - 3] == 2&& (chessMap[i + 4][j - 4] == othetColor || chessMap[i + 4][j - 4] == 4)) {
                                isSisanA();
                            }
                        }
                        if ((chessMap[i - 1][j + 1] == othetColor || chessMap[i - 1][j + 1] == 4) && chessMap[i + 4][j - 4] == 2 && chessMap[i + 3][j - 3] == 2) {
                            isSisanA();
                        }
                        if (chessMap[i - 1][j + 1] != 4){
                            if ((chessMap[i - 2][j + 2] == othetColor || chessMap[i - 2][j + 2] == 4) && chessMap[i - 1][j + 1] == 2 && chessMap[i + 4][j - 4] == 2 && chessMap[i + 3][j - 3] == 2) {
                                isSisanA();
                            }
                        }
                    }

                    if (count == 2) {
                        if (chessMap[i - 1][j + 1] == 2 && chessMap[i - 2][j + 2] == 2 && chessMap[i - 3][j + 3] == 2 && chessMap[i + 4][j - 4] == 2 && chessMap[i + 3][j - 3] == 2 && chessMap[i + 2][j - 2] == 2) {
                            isHuoer();
                        }
                        if (chessMap[i - 1][j + 1] == 2 && chessMap[i - 2][j + 2] == 2 && chessMap[i - 3][j + 3] == 2 && (chessMap[i + 2][j - 2] == othetColor || chessMap[i + 2][j - 2] == 4)) {
                            isSierA();
                        }
                        if ((chessMap[i - 1][j + 1] == othetColor || chessMap[i - 1][j + 1] == 4) && chessMap[i + 4][j - 4] == 2 && chessMap[i + 3][j - 3] == 2 && chessMap[i + 2][j - 2] == 2) {
                            isSierA();
                        }
                    }
                    if (count == 5) {
                        isWin();
                        if (color==1){
                            score=score-90000000;
                        }
                        break doEach;
                    }
                }
            }
        }
//        System.out.println("全局估算得分：" + score);
    }//斜向搜索slant


    private void isWin() {
        score = score + 990000000;
    }

    private void isHuosi() {
        score = score + 1000000;
    }

    private void isSisiA() {
        score = score + 2500;
    }

    private void isHuosan() {
        score = score + 3000;
    }

    private void isSisanA() {
        score = score + 500;
    }

    private void isHuoer() {
        score = score + 650;
    }

    private void isSierA() {
        score = score + 150;
    }

    private int[] getGoodChess(int who){
        int scoreMe =0;
        int scoreHe =0;
        int xRenChess=0;
        int yRenChess=0;
        int color,otherColor;
        int goodScore=-1000000000;
        if (who==0){
            color=0;
            otherColor=1;
        }else {
            color=1;
            otherColor=0;
        }
        int[] zuoBiao = new int[3 ];
        for (int k = 4; k < 19; k++) {
            for (int l = 4; l < 19; l++) {
                if (chessMap[k][l] == 2) {
                    chessMap[k][l]=color;
                    SeachLandscape(color,otherColor);
                    SeachPortrait(color,otherColor);
                    SeachSlant(color,otherColor);
                    scoreMe=score;
                    score=0;

                    SeachLandscape(otherColor,color);
                    SeachPortrait(otherColor,color);
                    SeachSlant(otherColor,color);
                    scoreHe=score;
                    score=0;

                    if (scoreMe-scoreHe > goodScore) {
                        goodScore= scoreMe-scoreHe;
                        xRenChess = l;
                        yRenChess = k;
                    }
                    chessMap[k][l]=2;
                    score=0;
                }
            }
        }
//        System.out.println(goodScore);
        zuoBiao[0]=xRenChess;
        zuoBiao[1]=yRenChess;
        zuoBiao[2]=goodScore;
        return zuoBiao;

    }

    private int juShiPingGu(int who){
        int scoreMe =0;
        int scoreHe =0;
        int xRenChess=0;
        int yRenChess=0;
        int color,otherColor;
        int goodScore=-1000000000;
        if (who==0){
            color=0;
            otherColor=1;
        }else {
            color=1;
            otherColor=0;
        }
        SeachLandscape(color,otherColor);
        SeachPortrait(color,otherColor);
        SeachSlant(color,otherColor);
        scoreMe=score;
        score=0;

        SeachLandscape(otherColor,color);
        SeachPortrait(otherColor,color);
        SeachSlant(otherColor,color);
        scoreHe=score;
        score=0;

        if (scoreMe-scoreHe > goodScore) {
            goodScore= scoreMe-scoreHe;
        }
        return goodScore;
    }

    private boolean goCutTree(int i,int cutTree[]){
        if (i==cutTree[i-4]) {
            if (i==4){
                if (i+1==cutTree[i-4+1]&&i+2==cutTree[i-4+2])
                    return true;
            }else if (i==5){
                if (i-1==cutTree[i-4-1]&&i+1==cutTree[i-4+1]&&i+2==cutTree[i-4+2])
                    return true;
            }else if (i==18){
                if (i-1==cutTree[i-4-1]&&i-2==cutTree[i-4-2])
                    return true;
            }else if (i==17){
                if (i-1==cutTree[i-4-1]&&i+1==cutTree[i-4+1]&&i-2==cutTree[i-4-2])
                    return true;
            }
            else {
                if (i-1==cutTree[i-4-1]&&i+1==cutTree[i-4+1]&&i-2==cutTree[i-4-2]&&i+2==cutTree[i-4+2])
                    return true;
            }
        }
        return false;
    }

    public void  pressHighChess(){
        getMap();
        int goodScore = -1000000000;
        score = 0;
        int[] aiChessZuoBiao=new int[3];
        int[] huManZuoBiao1 = new int[3];
        int count=0;
        for (int i = 4; i < 19; i++) {
            for (int j = 4; j < 19; j++) {
                if (chessMap[i][j]==1||chessMap[i][j]==0){
                    count++;
                }
            }
        }
        for (int i = 4; i < 19; i++) {
            for (int j = 4; j < 19; j++) {
                if (chessMap[i][j] == 2) {
                    chessMap[i][j]=1;
                    huManZuoBiao1=getGoodChess(0);
//                    for (int k = 4; k < 19; k++) {
//                        for (int l = 4; l < 19; l++) {
//                            if (chessMap[k][l] == 2) {
//                                chessMap[k][l]=0;
//                                int color=0;int othetColor=1;
//                                SeachLandscape(color,othetColor);
//                                SeachPortrait(color,othetColor);
//                                SeachSlant(color,othetColor);
////                                System.out.println("ren时候的score"+score);
//                                if (scoreRen < score) {
//                                    scoreRen = score;
//                                    xRenChess = l;
//                                    yRenChess = k;//得出对手最优解
//                                }
//                                chessMap[k][l]=2;
//                                score=0;
//                            }
//                        }
//                    }
                    chessMap[huManZuoBiao1[1]][huManZuoBiao1[0]]=0;//将对手最优解输入地图
                    int fengshu = juShiPingGu(1);
                    if (goodScore<fengshu){
                        goodScore=fengshu;
                        xChess=j-4;
                        yChess=i-4;
                    }

                    chessMap[huManZuoBiao1[1]][huManZuoBiao1[0]]=2;
                    chessMap[i][j] = 2;
                }
            }

        }
        System.out.println(xChess+"ai计算后坐标"+""+yChess);

//        for (int i = 4; i < 19; i++) {
//            for (int j = 4; j < 19; j++) {
//                if (chessMap[i][j] == 2) {
//                    chessMap[i][j]=color;
//                    System.out.println("模拟图像");
//                    for (int s = 0; s < qiPan; s++) {
//                        for (int p = 0; p < qiPan; p++) {
//                            System.out.print(chessMap[s][p]);
//                        }
//                        System.out.println("");
//                    }
//                    SeachLandscape();
//                    SeachPortrait();
//                    SeachSlant();
//                    if (score > maxSocre) {
//                        maxSocre = score;
//                        System.out.println("max" + maxSocre);
//                        xChess = j - 4;
//                        yChess = i - 4;
//                    }
//                    chessMap[i][j] = 2;
//                    score = 0;
//                }
//            }
//        }
        if (count==1)
            noAiChessVoid();
    }


    public void pressLowChess() {
        getMap();
        maxSocre = -1000000000;
        score = 0;
        int goodScore=-1000000000;
        int scoreRen =0;
        int scoreAi =0;
        int cutTree[] = new int[15];
        int nullChess=0;
        int[] aiChessZuoBiao = new int[2];
        int[] huManZuoBiao1 = new int[2];
        int[] huManZuoBiao2 = new int[2];
        for (int i = 4; i < 19; i++) {
            nullChess=0;
            for (int j = 4; j < 19; j++) {
                if (chessMap[i][j] == 2) {
                    nullChess++;
                    if (nullChess == 15) {
                        cutTree[i-4] = i;
                    }
                }
            }
        }
        for (int i = 0; i < 15; i++) {
            System.out.print(cutTree[i]);
            System.out.print(" ");
        }


        for (int i = 4; i < 19; i++) {
            if (goCutTree(i,cutTree)){
                continue ;
            }
            System.out.println("这里执行了"+i);
            for (int j = 4; j < 19; j++) {
                if (chessMap[i][j]==2){
                    chessMap[i][j]=1;
                    huManZuoBiao1=getGoodChess(0);
//                    for (int k = 4; k < 19; k++) {
//                        for (int l = 4; l < 19; l++) {
//                            if (chessMap[k][l] == 2) {
//                                chessMap[k][l]=0;
//                                int color=0;int othetColor=1;
//                                SeachLandscape(color,othetColor);
//                                SeachPortrait(color,othetColor);
//                                SeachSlant(color,othetColor);
////                                System.out.println("ren时候的score"+score);
//                                if (scoreRen < score) {
//                                    scoreRen = score;
//                                    xRenChess = l;
//                                    yRenChess = k;//得出对手最优解
//                                }
//                                chessMap[k][l]=2;
//                                score=0;
//                            }
//                        }
//                    }
                    chessMap[huManZuoBiao1[1]][huManZuoBiao1[0]]=0;//将对手最优解输入地图
                    for (int k = i-2; k < 19; k++) {
                        if (chessMap[k][8] == 4)
                            break;
                        if (goCutTree(k,cutTree)){
                            continue;
                        }
                        for (int l = j-2; l < 19; l++) {
                            if (chessMap[k][l] == 2) {
                                chessMap[k][l]=1;
                                huManZuoBiao2=getGoodChess(0);
                                chessMap[huManZuoBiao2[1]][huManZuoBiao2[0]]=0;//将对手最优解输入地图
                                int fengshu =juShiPingGu(1);
                                if (goodScore<fengshu){
                                    goodScore=fengshu;
                                    xChess=j-4;
                                    yChess=i-4;
                                }
//                                int color1=0;int othetColor1=1;
//                                SeachLandscape(color1,othetColor1);
//                                SeachPortrait(color1,othetColor1);
//                                SeachSlant(color1,othetColor1);
////                                System.out.println("得分"+score);
//                                scoreRen=score;
//                                score=0;
//
//                                int color=1;int othetColor=0;
//                                SeachLandscape(color,othetColor);
//                                SeachPortrait(color,othetColor);
//                                SeachSlant(color,othetColor);
////                                System.out.println("得分"+score);
//                                scoreAi=score;
//                                score=0;
//
//                                if (scoreAi-scoreRen > maxSocre) {
////                        System.out.println(scoreAi-scoreRen+"ai-ren最大分"+maxSocre);
//                                    maxSocre = scoreAi-scoreRen;
////                        System.out.println("max" + maxSocre);
//                                    xAiChess = j;
//                                    yAiChess = i;
//                                }
                                chessMap[k][l]=2;
                                chessMap[huManZuoBiao2[1]][huManZuoBiao2[0]]=2;
                            }
                        }

                    }
                    chessMap[huManZuoBiao1[1]][huManZuoBiao1[0]]=2;
                    chessMap[i][j] = 2;
                }
//                    System.out.println("ai得分"+scoreAi+"人类得分"+scoreRen+"max得分"+maxSocre);
//                    chessMap[i][j] = 2;
//                    chessMap[huManZuoBiao1[1]][huManZuoBiao1[0]]=2;
//
//                    chessMap[yAiChess][xAiChess] = 1;
//                    chessMap[yRenChess][xRenChess]=2;
//                    scoreRen=0;
            }

        }
        System.out.println(xChess+"ai计算后坐标"+""+yChess);
//        for (int i = 4; i < 19; i++) {
//            for (int j = 4; j < 19; j++) {
//                if (chessMap[i][j] == 2) {
//                    chessMap[i][j]=color;
//                    System.out.println("模拟图像");
//                    for (int s = 0; s < qiPan; s++) {
//                        for (int p = 0; p < qiPan; p++) {
//                            System.out.print(chessMap[s][p]);
//                        }
//                        System.out.println("");
//                    }
//                    SeachLandscape();
//                    SeachPortrait();
//                    SeachSlant();
//                    if (score > maxSocre) {
//                        maxSocre = score;
//                        System.out.println("max" + maxSocre);
//                        xChess = j - 4;
//                        yChess = i - 4;
//                    }
//                    chessMap[i][j] = 2;
//                    score = 0;
//                }
//            }
//        }
//        noAiChessVoid();

    }

    private void noAiChessVoid(){
        noAi:
        for (int i = 4; i < 19; i++) {
            for (int j = 4; j < 19; j++) {
                if (chessMap[i][j] ==0 ) {//othetColor
                    System.out.println("在无ai算法中获得第一个黑子坐标" + i + "" + j);
                    for (int k = j - 1; k > 0; k--) {
                        if (chessMap[i][k] == 2) {
                            xChess = k - 4;
                            yChess = i - 4;
                            break noAi;
                        }
                    }
                    for (int k = j + 1; k < 19; k++) {
                        if (chessMap[i][k] == 2) {
                            xChess = k - 4;
                            yChess = i - 4;
                            break noAi;
                        }
                    }
                }

            }
        }
    }

}
