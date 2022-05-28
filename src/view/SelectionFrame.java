package view;

import controller.ClickController;
import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SelectionFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;
    PictureDrawer pictureDrawer = new PictureDrawer();


    /*
    创建游戏初始选择界面
     */

    public SelectionFrame(int width, int height) {
        setTitle("Welcome to Our Chess");
        WIDTH = width;
        HEIGHT = height;
        pictureDrawer.setSize(760, 760);

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addStartButton();
        addSelectBackgroundButton();
        add(pictureDrawer);
    }

    /**
     * 在主面板中增加一个按钮，如果按下的话就会开始游戏
     */

    private void addStartButton() {
        JButton button = new JButton("Start");
        button.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1000, 760,this.getPictureDrawer(),new Chessboard(608,608));
                mainFrame.setVisible(true);
                this.setVisible(false);
            });
        });
        button.setLocation(WIDTH / 2 - 100, HEIGHT / 2 - 80);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addSelectBackgroundButton(){
        JButton button = new JButton("Select Background");
        button.addActionListener((e) -> {
            try {
                pictureDrawer.setImage();
                pictureDrawer.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        button.setLocation(WIDTH / 2 - 150, HEIGHT / 2 + 10);
        button.setSize(300, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    public PictureDrawer getPictureDrawer() {
        return pictureDrawer;
    }
}
