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

public class QueenChessComponent extends ChessComponent {
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

    private static Image QUEEN_WHITE;
    private static Image QUEEN_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image queenImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (QUEEN_WHITE == null) {
            QUEEN_WHITE = ImageIO.read(new File("./images/queen-white.png"));
        }

        if (QUEEN_BLACK == null) {
            QUEEN_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }
    }

    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateQueenImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                queenImage = QUEEN_WHITE;
            } else if (color == ChessColor.BLACK) {
                queenImage = QUEEN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateQueenImage(color);
    }

    public List<ChessboardPoint> points(ChessComponent[][] chessComponents) {
        ChessboardPoint source = getChessboardPoint();
        List<ChessboardPoint> Queen = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (source.getX() + i <= 7 && source.getY() + i <= 7) {
                if (chessComponents[source.getX() + i][source.getY() + i].getChessColor() == ChessColor.NONE) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i, source.getY() + i);
                    Queen.add(n);
                } else if (chessComponents[source.getX() + i][source.getY() + i].getChessColor() == this.getChessColor()) {
                    break;
                } else if (chessComponents[source.getX() + i][source.getY() + i].getChessColor() != this.getChessColor()) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i, source.getY() + i);
                    Queen.add(n);
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getX() + i <= 7 && source.getY() - i >= 0) {
                if (chessComponents[source.getX() + i][source.getY() - i].getChessColor() == ChessColor.NONE) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i, source.getY() - i);
                    Queen.add(n);
                } else if (chessComponents[source.getX() + i][source.getY() - i].getChessColor() == this.getChessColor()) {
                    break;
                } else if (chessComponents[source.getX() + i][source.getY() - i].getChessColor() != this.getChessColor()) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i, source.getY() - i);
                    Queen.add(n);
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getX() - i >= 0 && source.getY() - i >= 0) {
                if (chessComponents[source.getX() - i][source.getY() - i].getChessColor() == ChessColor.NONE) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i, source.getY() - i);
                    Queen.add(n);
                } else if (chessComponents[source.getX() - i][source.getY() - i].getChessColor() == this.getChessColor()) {
                    break;
                } else if (chessComponents[source.getX() - i][source.getY() - i].getChessColor() != this.getChessColor()) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i, source.getY() - i);
                    Queen.add(n);
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getX() - i >= 0 && source.getY() + i <= 7) {
                if (chessComponents[source.getX() - i][source.getY() + i].getChessColor() == ChessColor.NONE) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i, source.getY() + i);
                    Queen.add(n);
                } else if (chessComponents[source.getX() - i][source.getY() + i].getChessColor() == this.getChessColor()) {
                    break;
                } else if (chessComponents[source.getX() - i][source.getY() + i].getChessColor() != this.getChessColor()) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i, source.getY() + i);
                    Queen.add(n);
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getX() + i <= 7) {
                if (chessComponents[source.getX() + i][source.getY()].getChessColor() == ChessColor.NONE) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i, source.getY());
                    Queen.add(n);
                } else {
                    if (chessComponents[source.getX() + i][source.getY()].getChessColor() != this.getChessColor()) {
                        ChessboardPoint n = new ChessboardPoint(source.getX() + i, source.getY());
                        Queen.add(n);
                        break;
                    }
                    break;
                }

            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getY() - i >= 0) {
                if (chessComponents[source.getX()][source.getY() - i].getChessColor() == ChessColor.NONE) {
                    ChessboardPoint n = new ChessboardPoint(source.getX(), source.getY() - i);
                    Queen.add(n);
                } else {
                    if (chessComponents[source.getX()][source.getY() - i].getChessColor() != this.getChessColor()) {
                        ChessboardPoint n = new ChessboardPoint(source.getX(), source.getY() - i);
                        Queen.add(n);
                        break;
                    }

                    break;
                }

            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getX() - i >= 0) {
                if (chessComponents[source.getX() - i][source.getY()].getChessColor() == ChessColor.NONE) {
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i, source.getY());
                    Queen.add(n);
                } else {
                    if (chessComponents[source.getX() - i][source.getY()].getChessColor() != this.getChessColor()) {
                        ChessboardPoint n = new ChessboardPoint(source.getX() - i, source.getY());
                        Queen.add(n);
                        break;
                    }
                    break;
                }

            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getY() + i <= 7) {
                if (chessComponents[source.getX()][source.getY() + i].getChessColor() == ChessColor.NONE) {
                    ChessboardPoint n = new ChessboardPoint(source.getX(), source.getY() + i);
                    Queen.add(n);
                } else {
                    if (chessComponents[source.getX()][source.getY() + i].getChessColor() != this.getChessColor()) {
                        ChessboardPoint n = new ChessboardPoint(source.getX(), source.getY() + i);
                        Queen.add(n);
                        break;
                    }
                    break;
                }
            }
        }
        return Queen;
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
        g.drawImage(queenImage, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(new Color(108, 153, 153, 150));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.drawImage(queenImage, -10, -10, getWidth() + 20, getHeight() + 20, this);
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

