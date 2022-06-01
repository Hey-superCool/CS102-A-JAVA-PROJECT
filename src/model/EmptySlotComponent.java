package model;

import view.ChessboardPoint;
import controller.ClickController;

import java.awt.*;
import java.io.IOException;

/**
 * 这个类表示棋盘上的空位置
 */
public class EmptySlotComponent extends ChessComponent {
    private static final Color[] BACKGROUND_COLORS = {new Color(255,255,255,150), new Color(0,0,0,150)};

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }

    @Override
    public void loadResource() throws IOException {
        //No resource!
    }

    @Override
    public boolean alarm(ChessComponent[][] chessComponents) {
        return false;
    }
    private int canBeMovedTo;

    public int getCanBeMovedTo() {
        return canBeMovedTo;
    }

    public void setCanBeMovedTo(int canBeMovedTo) {
        this.canBeMovedTo = canBeMovedTo;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Color squareColor = BACKGROUND_COLORS[(getChessboardPoint().getX() + getChessboardPoint().getY()) % 2];
        g.setColor(squareColor);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        if (canBeMovedTo == 1){
            System.out.println("空棋子也想变绿");
            g.setColor(new Color(191, 255, 113, 221));
            g.fillRect(0, 0, getWidth(), getHeight());
            this.setCanBeMovedTo(0);
        }
    }

}
