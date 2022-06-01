package controller;


import model.ChessComponent;
import model.KingChessComponent;
import view.Chessboard;
import view.ChessboardPoint;
import view.GameOverFrame;

import javax.swing.*;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    

    public void onClick(ChessComponent chessComponent) {
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (first.canMoveTo(chessboard.getChessComponents(),new ChessboardPoint(i,j))){
                            chessboard.getChessComponents()[i][j].setCanBeMovedTo(1);
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
                first.repaint();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (first.canMoveTo(chessboard.getChessComponents(),new ChessboardPoint(i,j))){
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(chessComponent)) {
                //repaint in swap chess method.
                if (chessboard.win(chessComponent.getChessboardPoint(), chessboard.getChessComponents())){
                    GameOverFrame a = new GameOverFrame(500,500,chessboard.getCurrentColor());
                    a.setVisible(true);
                }
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (first.canMoveTo(chessboard.getChessComponents(),new ChessboardPoint(i,j))){
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();
                chessboard.setViewCurrentPlayer(chessboard.getCurrentColor());
                first.setSelected(false);
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        ChessComponent chessComponent1 = chessboard.getChessComponents()[i][j];
                        if (chessComponent1.alarm(chessboard.getChessComponents())){
                            JOptionPane.showMessageDialog(null,"王正在被攻击");
                        }
                    }
                }
                first = null;
            }
        }
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }
}
