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

public class PawnChessComponent extends ChessComponent {
    private Chessboard chessboard;
    private int canBeMovedTo = 0;

    public int getCanBeMovedTo() {
        return canBeMovedTo;
    }

    public void setCanBeMovedTo(int canBeMoveTo) {
        this.canBeMovedTo = canBeMoveTo;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image pawnImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }

    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }

    public List<ChessboardPoint> points(ChessComponent[][] chessComponents) {
        ChessboardPoint source = getChessboardPoint();
        List<ChessboardPoint> Pawn = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (source.getX() == 1 && this.chessColor == ChessColor.BLACK) {
                    if ((i - source.getX() == 1 || i - source.getX() == 2) && source.getY() == j &&
                            chessComponents[i][j].getChessColor() == ChessColor.NONE) {
                        ChessboardPoint n = new ChessboardPoint(i, j);
                        Pawn.add(n);//buchiziqiekeyizouliangbu
                    }
                    if (i - source.getX() == 1 && Math.abs(source.getY() - j) == 1 &&
                            chessComponents[i][j].getChessColor() != this.getChessColor() &&
                            chessComponents[i][j].getChessColor() != ChessColor.NONE) {
                        ChessboardPoint n = new ChessboardPoint(i, j);
                        Pawn.add(n);//chizizouyibu
                    }
                } else if (source.getX() == 6 && this.chessColor == ChessColor.WHITE) {
                    if ((source.getX() - i == 1 || source.getX() - i == 2) && source.getY() == j &&
                            chessComponents[i][j].getChessColor() == ChessColor.NONE) {
                        ChessboardPoint n = new ChessboardPoint(i, j);
                        Pawn.add(n);//buchiziqiekeyizouliangbu
                    }
                    if (source.getX() - i == 1 && Math.abs(source.getY() - j) == 1 &&
                            chessComponents[i][j].getChessColor() != this.getChessColor() &&
                            chessComponents[i][j].getChessColor() != ChessColor.NONE) {
                        ChessboardPoint n = new ChessboardPoint(i, j);
                        Pawn.add(n);//chizizouyibu
                    }
                } else if (this.getChessColor() == ChessColor.WHITE) {
                    if (source.getX() - i == 1 && source.getY() == j &&
                            chessComponents[i][j].getChessColor() == ChessColor.NONE) {
                        ChessboardPoint n = new ChessboardPoint(i, j);
                        Pawn.add(n);//buchiziqiekeyizouliangbu
                    }
                    if (source.getX() - i == 1 && Math.abs(source.getY() - j) == 1 &&
                            chessComponents[i][j].getChessColor() != this.getChessColor() &&
                            chessComponents[i][j].getChessColor() != ChessColor.NONE) {
                        ChessboardPoint n = new ChessboardPoint(i, j);
                        Pawn.add(n);//chizizouyibu
                    }

                } else if (this.getChessColor() == ChessColor.BLACK) {
                    if (i - source.getX() == 1 && source.getY() == j &&
                            chessComponents[i][j].getChessColor() == ChessColor.NONE) {
                        ChessboardPoint n = new ChessboardPoint(i, j);
                        Pawn.add(n);//buchiziqiekeyizouliangbu
                    }
                    if (i - source.getX() == 1 && Math.abs(source.getY() - j) == 1 &&
                            chessComponents[i][j].getChessColor() != this.getChessColor() &&
                            chessComponents[i][j].getChessColor() != ChessColor.NONE) {
                        ChessboardPoint n = new ChessboardPoint(i, j);
                        Pawn.add(n);//chizizouyibu
                    }
                }
            }
        }
        return Pawn;
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
        g.drawImage(pawnImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(new Color(108, 153, 153, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(pawnImage, -10, -10, getWidth() + 20, getHeight() + 20, this);
            List<ChessboardPoint> points = points(chessboard.getChessComponents());
            for (int i = 0; i < points.size(); i++) {
                int a = points.size();
                System.out.println(a);
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
