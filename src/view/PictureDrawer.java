package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureDrawer extends JPanel {
    Image image;

    public PictureDrawer() {
        try {
            File file = new File("images/1.jpg");
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImage() throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fliter = new FileNameExtensionFilter("图片文件", "jpg", "jpeg", "png");
        chooser.setFileFilter(fliter);
        int ret = chooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            this.image = ImageIO.read(file);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int width = this.getWidth();
        int height = this.getHeight();

        g.drawImage(image,0,0,760,760,null);
    }
}
