package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

public class UI_Demo extends JPanel {

    static JFrame Main_jf;

    static MainCenterUI mainui;

    Image BG;

    UI_Demo(JFrame frame) {
        mainui = new MainCenterUI(frame);
        setLoginBg();
        Main_jf = frame;
        Strat_Button(this);
        setLayout(null);
        Main_jf.add(this);


    }

    String Start_ui_button_name[] = { "" };

    void Strat_Button(JPanel jPanel) {
        Font f = new Font("黑体", Font.PLAIN, 20);
        // ImageIcon icon = new ImageIcon("draw/img/CraftNode_Base.png");
        // icon.setImage(icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        for (int i = 0; i < Start_ui_button_name.length; i++) {
            JButton button = new JButton(Start_ui_button_name[i]);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setSize(500, 40);
            button.setFont(f);
            button.setForeground(Color.PINK);
            button.setLocation(GameDefaultSettingData.GAME_WIN_WIDTH / 2 - 500 / 2,
                    GameDefaultSettingData.GAME_WIN_HEIGHT-100);
            int ID = i;
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (ID) {
                        case 0:
                            Main_jf.remove(jPanel);
                            Main_jf.add(mainui);
                            Main_jf.validate();
                            Main_jf.invalidate();
                            Main_jf.repaint();
                            // add_zombiehand.start();
                            break;
                        case 1:
                            break;
                        case 2:
                            System.exit(0);
                            break;
                    }
                }
            });
            jPanel.add(button);
        }
    }

    void setLoginBg() {
        ImageIcon icon = new ImageIcon("draw/image/menu.png");
        BG = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(BG, 0, 0, this.getWidth(), this.getHeight(), this);
    }


}
