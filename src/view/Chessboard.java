package view;


import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Locale;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;

    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor = ChessColor.WHITE;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;

    private JLabel viewCurrentPlayer = new JLabel();

    public void setViewCurrentPlayer(ChessColor color) {
        String currentColor = String.valueOf(this.getCurrentColor());
        viewCurrentPlayer.setText(currentColor);
    }

    public JLabel getViewCurrentPlayer() {
        return viewCurrentPlayer;
    }

    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        initiateEmptyChessboard();

        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);
        initKingOnBoard(0, 4, ChessColor.BLACK);
        initKingOnBoard(7, 4, ChessColor.WHITE);
        initPawnOnBoard(1, 0, ChessColor.BLACK);
        initPawnOnBoard(1, 1, ChessColor.BLACK);
        initPawnOnBoard(1, 2, ChessColor.BLACK);
        initPawnOnBoard(1, 3, ChessColor.BLACK);
        initPawnOnBoard(1, 4, ChessColor.BLACK);
        initPawnOnBoard(1, 5, ChessColor.BLACK);
        initPawnOnBoard(1, 6, ChessColor.BLACK);
        initPawnOnBoard(1, 7, ChessColor.BLACK);
        initPawnOnBoard(6, 0, ChessColor.WHITE);
        initPawnOnBoard(6, 1, ChessColor.WHITE);
        initPawnOnBoard(6, 2, ChessColor.WHITE);
        initPawnOnBoard(6, 3, ChessColor.WHITE);
        initPawnOnBoard(6, 4, ChessColor.WHITE);
        initPawnOnBoard(6, 5, ChessColor.WHITE);
        initPawnOnBoard(6, 6, ChessColor.WHITE);
        initPawnOnBoard(6, 7, ChessColor.WHITE);
        initKnightOnBoard(0, 1, ChessColor.BLACK);
        initKnightOnBoard(7, 1, ChessColor.WHITE);
        initKnightOnBoard(0, 6, ChessColor.BLACK);
        initKnightOnBoard(7, 6, ChessColor.WHITE);
        initBishopOnBoard(0, 2, ChessColor.BLACK);
        initBishopOnBoard(7, 2, ChessColor.WHITE);
        initBishopOnBoard(0, 5, ChessColor.BLACK);
        initBishopOnBoard(7, 5, ChessColor.WHITE);
        initQueenOnBoard(0, 3, ChessColor.BLACK);
        initQueenOnBoard(7, 3, ChessColor.WHITE);


    }

    public ChessComponent[][] getChessComponents() {
        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;
        if (chess1 instanceof PawnChessComponent&&chess1.getChessboardPoint().getX()==7&&chess1.getChessColor()==ChessColor.BLACK){
            remove(chess1);
            add(chess1=new QueenChessComponent(chess1.getChessboardPoint(),chess1.getLocation(),ChessColor.BLACK,clickController,CHESS_SIZE));
        }
        if (chess1 instanceof PawnChessComponent&&chess1.getChessboardPoint().getX()==0&&chess1.getChessColor()==ChessColor.WHITE){
            remove(chess1);
            add(chess1=new QueenChessComponent(chess1.getChessboardPoint(),chess1.getLocation(),ChessColor.WHITE,clickController,CHESS_SIZE));
        }
        chess1.repaint();
        chess2.repaint();
    }
    public boolean win(ChessboardPoint destination,ChessComponent[][]chessComponents){
        if (chessComponents[destination.getX()][destination.getY()] instanceof KingChessComponent){
            System.out.println("win");
            return true;
        }
        else {
            System.out.println("fail");return false;}
    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }

    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }

    private void initKingOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initPawnOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initKnightOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initBishopOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initQueenOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    private Point calculatePoint(int row, int col) {//保存为string格式
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }
    public String save(){
       StringBuilder m = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessComponents[i][j] instanceof EmptySlotComponent){
                    m.append("_");
                }
                else if (chessComponents[i][j] instanceof QueenChessComponent){
                    if (chessComponents[i][j].getChessColor()==ChessColor.WHITE){
                        m.append("q");
                    }
                    else {
                        m.append("Q");
                    }
                }
                else if (chessComponents[i][j] instanceof KingChessComponent){
                    if (chessComponents[i][j].getChessColor()==ChessColor.WHITE){
                        m.append("k");
                    }
                    else {
                        m.append("K");
                    }
                }
                else if (chessComponents[i][j] instanceof KnightChessComponent){
                    if (chessComponents[i][j].getChessColor()==ChessColor.WHITE){
                        m.append("n");
                    }
                    else {
                        m.append("N");
                    }
                }
                else if (chessComponents[i][j] instanceof RookChessComponent){
                    if (chessComponents[i][j].getChessColor()==ChessColor.WHITE){
                        m.append("r");
                    }
                    else {
                        m.append("R");
                    }
                }
                else if (chessComponents[i][j] instanceof PawnChessComponent){
                    if (chessComponents[i][j].getChessColor()==ChessColor.WHITE){
                        m.append("p");
                    }
                    else {
                        m.append("P");
                    }
                }
                else if (chessComponents[i][j] instanceof BishopChessComponent){
                    if (chessComponents[i][j].getChessColor()==ChessColor.WHITE){
                        m.append("b");
                    }
                    else {
                        m.append("B");
                    }
                }

            }
        }
        if (getCurrentColor()==ChessColor.WHITE){
            m.append("w");
        }else {
            m.append("b");
        }
        return m.toString();
    }
    public static int saveChess=0;
    public static void saveAsFileWriter(String content) {
        String a="D:\\txq\\Chessboard"+saveChess+".txt";//这是文件路径
        FileWriter fwriter = null;
        try {//存储为txt的方法
            fwriter = new FileWriter(a);
            fwriter.write(content);
            saveChess++;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
//    public static String read(String src)throws IOException{
////        String src ="D:\\txq\\Chessboard"+saveChess+".txt";
//        File file = new File(src);
//        FileReader fileReader = new FileReader(file);
//        BufferedReader br = new BufferedReader(fileReader);
//        StringBuilder sb = new StringBuilder();
//        String temp = "";
//        while ((temp = br.readLine()) != null) {
//            sb.append(temp);
//        }
//        br.close();
//        String js = sb.toString();
//        return js;
//    }
    public boolean checkString(File file)throws IOException{
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        String js = sb.toString();
        String j = "KkQqRrPpBbNn_";
        for (int i = 0; i < js.length(); i++) {
           int result = j.indexOf(js.charAt(i));
           if (result==-1){
               return false;
           }
           else if (js.length()!=65||js.charAt(js.length()-1)!='w'||js.charAt(js.length()-1)!='b'){
               return false;
           }
        }
        return true;
    }
    public Chessboard loadChessboard(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        br.close();
        String js = sb.toString();
        Chessboard m = new Chessboard(608,608);
        m.initiateEmptyChessboard();
        char[] n = js.toCharArray();
        if (checkString(file)){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    switch (n[8 * i + j]) {
                        case 'k':
                            m.initKingOnBoard(i, j, ChessColor.WHITE);
                            break;
                        case 'K':
                            m.initKingOnBoard(i, j, ChessColor.BLACK);
                            break;
                        case 'n':
                            m.initKnightOnBoard(i, j, ChessColor.WHITE);
                            break;
                        case 'N':
                            m.initKnightOnBoard(i, j, ChessColor.BLACK);
                            break;
                        case 'b':
                            m.initBishopOnBoard(i, j, ChessColor.WHITE);
                            break;
                        case 'B':
                            m.initBishopOnBoard(i, j, ChessColor.BLACK);
                            break;
                        case 'q':
                            m.initQueenOnBoard(i, j, ChessColor.WHITE);
                            break;
                        case 'Q':
                            m.initQueenOnBoard(i, j, ChessColor.BLACK);
                            break;
                        case 'p':
                            m.initPawnOnBoard(i, j, ChessColor.WHITE);
                            break;
                        case 'P':
                            m.initPawnOnBoard(i, j, ChessColor.BLACK);
                            break;
                        case 'r':
                            m.initRookOnBoard(i, j, ChessColor.WHITE);
                            break;
                        case 'R':
                            m.initRookOnBoard(i, j, ChessColor.BLACK);
                            break;

                    }
                }

            }
            if (n[n.length - 1] == 'w') {
                m.setCurrentColor(ChessColor.WHITE);
            } else {
                m.setCurrentColor(ChessColor.BLACK);
            }
            return m;
        }
        else {
            return this;
        }
    }
}


//    public void loadGame(String js)  {
//
//        //这里需要加载成棋盘
////        chessData.forEach(System.out::println);
//    }
//}
