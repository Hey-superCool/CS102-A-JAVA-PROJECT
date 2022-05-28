package view;

import controller.ClickController;
import controller.GameController;
import model.ChessColor;
import model.ChessComponent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

        addChessboard(chessboard);
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
    private void addChessboard(Chessboard chessboard) {
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
                ChessGameFrame mainFrame = new ChessGameFrame(1000, 760,pictureDrawer,new Chessboard(608,608));
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
//    private void addLoadButton() {
//        JButton button = new JButton("Load");
//        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
//        button.setSize(200, 60);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(button);
//
//        button.addActionListener(e -> {
//            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this, "Input Path here");
//            try {
//                this.chessboard = chessboard.loadChessboard(path);
//                System.out.println(chessboard.save(chessboard.getChessComponents()));
//                this.setVisible(false);
//                ChessGameFrame newChessGameFrame = new ChessGameFrame(1000,760,pictureDrawer,chessboard);
//                newChessGameFrame.setVisible(true);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        });
//    }
    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter fliter = new FileNameExtensionFilter("棋盘文件", "txt");
            chooser.setFileFilter(fliter);
            System.out.println("Click load");
            File file = null;
            int ret = chooser.showOpenDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
            }
            try {
                this.chessboard = chessboard.loadChessboard(file);
                System.out.println(chessboard.save());
                this.setVisible(false);
                ChessGameFrame newChessGameFrame = new ChessGameFrame(1000, 760, pictureDrawer, chessboard);
                newChessGameFrame.setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
//    public static String txt2String(File file){
//        StringBuilder result = new StringBuilder();
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
//            String s = null;
//            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
//                result.append(System.lineSeparator()+s);
//            }
//            br.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return result.toString();
//    }
    //下面是存档按钮

    private void addSaveButton(){
        JButton button = new JButton("Save");
        button.setLocation(HEIGTH, HEIGTH / 10 + 320);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);

        button.addActionListener(e -> {
            Chessboard.saveAsFileWriter(chessboard.save());//调用当前棋盘的存储方法
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
