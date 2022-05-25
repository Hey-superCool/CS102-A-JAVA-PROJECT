package view;

import controller.ClickController;
import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private Chessboard chessboard;
    private ClickController clickController;
    private PictureDrawer pictureDrawer;


    public ChessGameFrame(int width, int height, PictureDrawer pictureDrawer,Chessboard chessboard) {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;
        pictureDrawer.setSize(760, 760);
        this.pictureDrawer = pictureDrawer;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard();
//        addLabel();
//        addHelloButton();
        addResetButton();
        addLoadButton();
        addSaveButton();
        add(pictureDrawer);
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        this.chessboard = chessboard;
        setClickController(new ClickController(this.chessboard));
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);
        JLabel player = new JLabel("当前玩家:");
        player.setSize(200, 60);
        player.setLocation(HEIGTH, HEIGTH / 10);
        player.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(player);

        String currentColor = String.valueOf(chessboard.getCurrentColor());
        chessboard.getViewCurrentPlayer().setText(currentColor);
        chessboard.getViewCurrentPlayer().setSize(new Dimension(200, 60));
        chessboard.getViewCurrentPlayer().setLocation(HEIGTH + 100, HEIGTH / 10);
        chessboard.getViewCurrentPlayer().setFont(new Font("Rockwell", Font.BOLD, 20));
        add(chessboard.getViewCurrentPlayer());
    }

    /**
     * 在游戏面板中添加标签
     */
//    private void addLabel() {
//        JLabel statusLabel = new JLabel("Sample label");
//        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
//        statusLabel.setSize(200, 60);
//        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(statusLabel);
//    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

//    private void addHelloButton() {
//        JButton button = new JButton("Show Hello Here");
//        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
//        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
//        button.setSize(200, 60);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(button);
//    }
    private void addResetButton() {
        JButton button = new JButton("Reset");
        button.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = new ChessGameFrame(1000, 760,pictureDrawer,chessboard);
                mainFrame.setVisible(true);
                this.setVisible(false);
            });
        });
        button.setSize(200, 60);
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会
     */
    //此处应该调用完成后的load方法
    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            try {
                this.chessboard = chessboard.loadChessboard(path);
                System.out.println(chessboard.save(chessboard.getChessComponents()));
                this.setVisible(false);
                ChessGameFrame newChessGameFrame = new ChessGameFrame(1000,760,pictureDrawer,chessboard);
                newChessGameFrame.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    //下面是存档按钮

    private void addSaveButton(){
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 320);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            Chessboard.saveAsFileWriter(chessboard.save(chessboard.getChessComponents()));//调用当前棋盘的存储方法
        });
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public ClickController getClickController() {
        return clickController;
    }

    public void setClickController(ClickController clickController) {
        this.clickController = clickController;
    }


}
