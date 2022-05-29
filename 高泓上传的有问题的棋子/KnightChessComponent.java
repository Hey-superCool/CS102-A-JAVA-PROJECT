package model;

import controller.ClickController;
import view.Chessboard;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KnightChessComponent extends ChessComponent {
    private Chessboard chessboard;
    private int canBeMovedTo = 0;

    public int getCanBeMovedTo() {
        return canBeMovedTo;
    }

    public void setCanBeMovedTo(int canBeMovedTo) {
        this.canBeMovedTo = canBeMovedTo;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    private static Image KNIGHT_WHITE;
    private static Image KNIGHT_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image knightImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (KNIGHT_WHITE == null) {
            KNIGHT_WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (KNIGHT_BLACK == null) {
            KNIGHT_BLACK = ImageIO.read(new File("./images/knight-black.png"));
        }
    }

    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                knightImage = KNIGHT_WHITE;
            } else if (color == ChessColor.BLACK) {
                knightImage = KNIGHT_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateKnightImage(color);
    }

    public List<ChessboardPoint> points(ChessComponent[][] chessComponents) {
        ChessboardPoint source = getChessboardPoint();
        List<ChessboardPoint> Knight = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Math.abs(source.getX() - i) == 1 && Math.abs(source.getY() - j) == 2 &&
                        chessComponents[i][j].getChessColor() != this.getChessColor()) {
                    ChessboardPoint n = new ChessboardPoint(i, j);
                    Knight.add(n);

                } else if (Math.abs(source.getX() - i) == 2 && Math.abs(source.getY() - j) == 1 &&
                        chessComponents[i][j].getChessColor() != this.getChessColor()) {
                    ChessboardPoint n = new ChessboardPoint(i, j);
                    Knight.add(n);
                }
            }
        }
        return Knight;
    }

    public boolean alarm(ChessComponent[][] chessComponents) {
        for (int i = 0; i < points(chessComponents).size(); i++) {
            if (chessComponents[points(chessComponents).get(i).getX()][points(chessComponents).get(i).getY()] instanceof KingChessComponent) {
                return true;
            }
        }
        return false;
    }

    /**
     * 车棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 车棋子移动的合法性
     */

    @Override

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        for (int i = 0; i < points(chessComponents).size(); i++) {
            if (destination.getX() == points(chessComponents).get(i).getX() && destination.getY() == points(chessComponents).get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(knightImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(new Color(108, 153, 153, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(knightImage, -10, -10, getWidth() + 20, getHeight() + 20, this);
            List<ChessboardPoint> points = points(chessboard.getChessComponents());
            for (int i = 0; i < points.size(); i++) {
                chessboard.getChessComponents()[points.get(i).getX()][points.get(i).getY()].setCanBeMovedTo(1);
                chessboard.getChessComponents()[points.get(i).getX()][points.get(i).getY()].repaint();
                chessboard.getChessComponents()[points.get(i).getX()][points.get(i).getY()].setCanBeMovedTo(0);
            }
        }
        if (canBeMovedTo == 1) {
            g.setColor(new Color(191, 255, 113, 221));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
