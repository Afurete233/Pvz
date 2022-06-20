package app;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;

public class Gacha extends JPanel {

    ImageIcon bg = new ImageIcon("draw/img/BG_Millennium_Collection.png");
    Image BG;

    JFrame Main_jf;

    Gacha(JFrame frame,JPanel lastJPanel) {
        Main_jf = frame;
        BG = bg.getImage();
        setLayout(null);

        DefaultButton.ComeBackButton(this, lastJPanel, frame);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(BG, 0, 0, this.getWidth(),this.getHeight(),this);
    }

}
