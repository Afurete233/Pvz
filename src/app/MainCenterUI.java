package app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseListener;
import java.awt.Font;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Image;

public class MainCenterUI extends JPanel implements MouseListener {

    JPanel jPanel;
    Image BG;
    Image Main_character;
    int Width = GameDefaultSettingData.GAME_WIN_WIDTH;
    int Height = GameDefaultSettingData.GAME_WIN_HEIGHT;

    ImageIcon character = new ImageIcon(
            "draw/img/sprite/"
                    + GetAllSprite.getAllSprite_name()[new Random().nextInt(GetAllSprite.getAllSprite_name().length)]);
    ImageIcon icon = new ImageIcon("draw/image/menu2.jpg");
    ImageIcon communication_img = new ImageIcon("draw/img/communication.png");

    JFrame Main_jf;

    Gacha gacha;
    Attack attack;

    MainCenterUI(JFrame frame) {
        Loding.startjsondata();
        Main_jf = frame;
        jPanel = this;

        gacha = new Gacha(frame, this);
        attack = new Attack(frame, this);

        addMain_character();
        setAttack_button();
        // setgacha_button();
        setLoginBg();
        // communication();

        setLayout(null);
    }

    void setLoginBg() {
        BG = icon.getImage();
    }

    JLabel jLabel;

    void addMain_character() {
        // character.setImage(character.getImage().getScaledInstance(character.getIconWidth()
        // / 2,
        // character.getIconHeight() / 2, Image.SCALE_SMOOTH));
        jLabel = new JLabel(character);
        jLabel.setSize(character.getIconWidth(), character.getIconHeight());
        jLabel.setLocation(0, 200);
        jLabel.addMouseListener(this);
        add(jLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(BG, 0, 0, this.getWidth(), this.getHeight(), this);

    }

    JLabel jLabel_com;
    Boolean iscommunication = true;

    void communication() {
        if (iscommunication)
            if (jLabel_com == null) {
                Font f = new Font("黑体", Font.PLAIN, 20);
                jLabel_com = new JLabel(communication_img);
                jLabel_com.setText(GameDefaultSettingData.communication_data[new Random()
                        .nextInt(GameDefaultSettingData.communication_data.length)]);
                jLabel_com.setVerticalTextPosition(JLabel.CENTER);
                jLabel_com.setHorizontalTextPosition(JLabel.CENTER);
                jLabel_com.setFont(f);
                jLabel_com.setSize(communication_img.getIconWidth(), communication_img.getIconHeight());
                jLabel_com.setLocation(Width / 2, Height / 2 - communication_img.getIconHeight());
                jLabel_com.setVisible(false);
                add(jLabel_com);
            } else {
                jLabel_com.setText(GameDefaultSettingData.communication_data[new Random()
                        .nextInt(GameDefaultSettingData.communication_data.length)]);
                jLabel_com.setVisible(true);
                new Thread(() -> {
                    iscommunication = false;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    jLabel_com.setVisible(false);
                    iscommunication = true;
                }).start();
            }
    }

    public void setgacha_button() {
        JButton button = new JButton("Gacha");
        button.setLocation(0, 0);
        button.setSize(200, 60);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main_jf.remove(jPanel);
                Main_jf.add(gacha);
                Main_jf.validate();
                Main_jf.invalidate();
                Main_jf.repaint();
            }
        });
        add(button);
    }

    boolean main_sw = true;

    public void setAttack_button() {
        ImageIcon bt = new ImageIcon("draw/image/gamestart2.png");
        bt.setImage(bt.getImage().getScaledInstance(bt.getIconWidth() * 4 / 3 + 100,
                bt.getIconHeight() * 4 / 3, Image.SCALE_SMOOTH));
        ImageIcon bt2 = new ImageIcon("draw/image/gamestart1.png");
        bt2.setImage(bt2.getImage().getScaledInstance(bt2.getIconWidth() * 4 / 3 + 100,
                bt2.getIconHeight() * 4 / 3, Image.SCALE_SMOOTH));

        JButton button = new JButton("出击", bt);
        button.setPressedIcon(bt2);
        button.setRolloverIcon(bt2);
        button.setLocation(620, 53);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setSize(bt.getIconWidth(), bt.getIconHeight());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!Anime.Anime_isRun)
                    new Thread(() -> {
                        add_zombiehand.run();
                        Main_jf.remove(jPanel);
                        Main_jf.add(attack);
                        Main_jf.validate();
                        Main_jf.invalidate();
                        Main_jf.repaint();
                        if (main_sw) {
                            attack.main_Thread.start();
                            main_sw = false;
                        }
                    }).start();

            }
        });
        add(button);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(jLabel)) {
            // communication();
            Anime.Anime_jump_down(jLabel, 20, 5);
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

    Thread add_zombiehand = new Thread(() -> {
        Anime.Anime_isRun = true;
        JLabel label = new JLabel();
        Voice.anime_music(Voice.hahaha_sound).start();
        label.setSize(330, 330);
        label.setLocation(GameDefaultSettingData.GAME_WIN_WIDTH / 2 - 330 / 2,
                GameDefaultSettingData.GAME_WIN_HEIGHT - 330);
        add(label);
        for (int i = 1; i <= 7; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ImageIcon icon = new ImageIcon("draw/image/zombiehand/" + i + ".jpg");
            label.setIcon(icon);
        }
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        Anime.Anime_isRun = false;
    });

}
