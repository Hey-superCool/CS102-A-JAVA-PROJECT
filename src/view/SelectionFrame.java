package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class SelectionFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGHT;

    /*
    创建游戏初始选择界面
     */

    public SelectionFrame(int width, int height) {
        setTitle("Welcome to Our Chess");
        WIDTH = width;
        HEIGHT = height;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addStartButton();
    }



    private void addStartButton() {
        JButton button = new JButton("Start");
        button.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
                mainFrame.setVisible(true);
                this.setVisible(false);
            });
        });
        button.setLocation(WIDTH / 2 - 100, HEIGHT / 2 - 30);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
}
