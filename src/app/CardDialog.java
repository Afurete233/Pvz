package app;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

public class CardDialog extends JDialog implements WindowListener {

    int[] Card_select = new int[2];
    boolean isSelecting = true;

    CardDialog() {

        setTitle("卡牌获取");
        setAlwaysOnTop(true);
        setVisible(true);
        setLocation(400, 100);
        setSize(600, 600);
        setLayout(null);
        addWindowListener(this);

        addTielte();
        addCard();
    }

    public void addTielte() {
        JLabel title = new JLabel("选择你需要的牌");
        title.setLocation(120, 20);
        title.setSize(getWidth(), 80);
        title.setFont(new Font("黑体", Font.PLAIN, 50));
        add(title);
    }

    public void addCard() {
        ImageIcon[] Cards = RandomCard();
        int x = -20;
        for (int i = 0; i < Cards.length; i++) {
            
            int Card_ = i;
            ImageIcon icon = Cards[i];
            icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth() * 3, icon.getIconHeight() * 3,
                    Image.SCALE_SMOOTH));
            JLabel car_JLabel = new JLabel(icon);
            car_JLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
            car_JLabel.setLocation(x += icon.getIconWidth() + 20,
                    getHeight() / 2 - icon.getIconHeight() / 2);
            car_JLabel.addMouseListener(new MouseListener() {
                Boolean isClicked = true;

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isClicked) {
                        isClicked = false;
                        car_JLabel.setLocation(car_JLabel.getX(), car_JLabel.getY() - 20);
                    } else {
                        isClicked = true;
                        car_JLabel.setLocation(car_JLabel.getX(), car_JLabel.getY() + 20);
                    }
                    if (e.getClickCount() >= 2) {

                        Loding.Updatejsondata(Card_select[Card_]);

                        isSelecting = false;
                        dispose();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub

                }
            });
            add(car_JLabel);
            repaint();
        }
    }

    public ImageIcon[] RandomCard() {
        ImageIcon[] Cards = new ImageIcon[2];
        for (int i = 0; i < Cards.length; i++) {
            int Card_id = new Random().nextInt(GetAllSprite.getAllCard_name().length)+1;
            int num = i;
            Card_select[num] = Card_id;
            ImageIcon Card = new ImageIcon(
                    "draw/image/plant/"
                            + "card_" + Card_id + ".png");
            Cards[num] = Card;

        }

        return Cards;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        isSelecting = false;
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

}
