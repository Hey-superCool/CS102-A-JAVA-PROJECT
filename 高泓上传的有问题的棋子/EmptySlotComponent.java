package model;

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * 这个类表示棋盘上的空位置
 */
public class EmptySlotComponent extends ChessComponent {
    private static final Color[] BACKGROUND_COLORS = {new Color(255,255,255,150), new Color(0,0,0,150)};
    private Chessboard chessboard;
    private int canBeMoveTo = 0;

    public int getCanBeMoveTo() {
        return canBeMoveTo;
    }

    public void setCanBeMoveTo(int canBeMoveTo) {
        this.canBeMoveTo = canBeMoveTo;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size);
    }

    @Override
    public List<ChessboardPoint> points(ChessComponent[][] chessComponents) {
        return null;
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }

    @Override
    public void loadResource() throws IOException {
        //No resource!
    }
//    @Override
//    public void paintComponent(Graphics g){
//        super.paintComponents(g);
////        Color squareColor = BACKGROUND_COLORS[(this.getX() + this.getY()) % 2];
////        g.setColor(squareColor);
////        g.fillRect(0, 0, this.getWidth(), this.getHeight());
//
//        if (canBeMoveTo == 1) {
//            g.setColor(new Color(191, 255, 113, 221));
//            g.fillRect(0, 0, getWidth(), getHeight());
//        }
//    }

}
