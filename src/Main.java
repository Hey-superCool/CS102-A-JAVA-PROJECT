import view.ChessGameFrame;
import view.SelectionFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SelectionFrame selectionFrame = new SelectionFrame(1000,760);
            selectionFrame.setVisible(true);
        });
    }
}
