package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BishopChessComponent extends ChessComponent{
    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image bishopImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }

    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateBishopImage(color);
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
        ChessboardPoint source = getChessboardPoint();
        List<ChessboardPoint> Bishop = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            if (source.getX() + i <= 7&&source.getY()+i<=7) {
                if (chessComponents[source.getX() + i ][source.getY()+i].getChessColor()==ChessColor.NONE){
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i ,source.getY()+i);
                    Bishop.add(n);
                }
                else if (chessComponents[source.getX() + i ][source.getY()+i].getChessColor()==this.getChessColor()){
                    break;
                }
                else if (chessComponents[source.getX() + i ][source.getY()+i].getChessColor()!=this.getChessColor()){
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i ,source.getY()+i);
                    Bishop.add(n);
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getX() + i <= 7&&source.getY()-i>=0) {
                if (chessComponents[source.getX() + i ][source.getY()-i] .getChessColor()==ChessColor.NONE){
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i ,source.getY()-i);
                    Bishop.add(n);
                }
                else if (chessComponents[source.getX() + i ][source.getY()-i].getChessColor()==this.getChessColor()){
                    break;
                }
                else if (chessComponents[source.getX() + i ][source.getY()-i].getChessColor()!=this.getChessColor()){
                    ChessboardPoint n = new ChessboardPoint(source.getX() + i ,source.getY()-i);
                    Bishop.add(n);
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getX() -i >=0&&source.getY()-i>=0) {
                if (chessComponents[source.getX() - i ][source.getY()-i].getChessColor()==ChessColor.NONE){
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i ,source.getY()-i);
                    Bishop.add(n);
                }
                else if (chessComponents[source.getX() - i ][source.getY()-i].getChessColor()==this.getChessColor()){
                    break;
                }
                else if (chessComponents[source.getX() - i ][source.getY()-i].getChessColor()!=this.getChessColor()){
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i ,source.getY()-i);
                    Bishop.add(n);
                    break;
                }
            }
        }
        for (int i = 1; i < 8; i++) {
            if (source.getX() - i >= 0&&source.getY()+i<=7) {
                if (chessComponents[source.getX() - i ][source.getY()+i].getChessColor()==ChessColor.NONE){
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i ,source.getY()+i);
                    Bishop.add(n);
                }
                else if (chessComponents[source.getX() - i ][source.getY()+i].getChessColor()==this.getChessColor()){
                    break;
                }
                else if (chessComponents[source.getX() - i ][source.getY()+i].getChessColor()!=this.getChessColor()){
                    ChessboardPoint n = new ChessboardPoint(source.getX() - i ,source.getY()+i);
                    Bishop.add(n);
                    break;
                }
            }
        }
        for (int i = 0; i < Bishop.size(); i++) {
            if (destination.getX()==Bishop.get(i).getX()&&destination.getY()==Bishop.get(i).getY()){
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
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
