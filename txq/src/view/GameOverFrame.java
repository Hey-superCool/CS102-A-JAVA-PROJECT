package view;

import model.ChessColor;

import javax.swing.*;
import java.awt.*;

    public class GameOverFrame extends JFrame {
        private final int WIDTH;
        private final int HEIGHT;
        private ChessColor color;
        PictureDrawer pictureDrawer = new PictureDrawer();

        public GameOverFrame(int width, int height,ChessColor color) {
            setTitle("Game Over");
            WIDTH = width;
            HEIGHT = height;
            pictureDrawer.setSize(760, 760);
            setSize(WIDTH, HEIGHT);
            setLocationRelativeTo(null); // Center the window.
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
            setLayout(null);
            addCongLabel(color);
        }

        public ChessColor getColor() {
            return color;
        }

        public void setColor(ChessColor color) {
            this.color = color;
        }

        public void addCongLabel(ChessColor color){
            String str;
            if (color == ChessColor.WHITE){
                str = "白色赢了！";
            }else {
                str = "黑色赢了！";
            }
            JLabel over = new JLabel(str);
            over.setLocation(130, 200);
            over.setSize(350, 60);
            over.setFont(new Font("Rockwell", Font.BOLD, 50));
            add(over);
        }
    }
