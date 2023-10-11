package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.Plant.Nuts;
import app.Plant.SunFlowers;
import app.Spirit.Dave;
import app.Spirit.Zombie;
import app.Spirit.ZombieCreate;

import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Attack extends JPanel {

    JFrame Main_jf;
    JLabel bG_JLabel;

    int round = 0;

    ImageIcon Dave_img = new ImageIcon(
            "draw/img/sprite/"
                    + GetAllSprite.getAllSprite_name()[new Random().nextInt(GetAllSprite.getAllSprite_name().length)]);
    JPanel Card_panel;
    ZombieCreate zombieCreate;
    SunProduce sunProduce;

    Attack(JFrame frame, JPanel lastJPanel) {
        Main_jf = frame;
        // BG = bg.getImage();
        setLayout(null);
        Attack_data_loging();

        setbgloging();
        addMain_character();
        addCard_panel();
        addWashCard();
        addSunCont();
        addSeedBank();
        zombieCreate = new ZombieCreate(this);
        sunProduce = new SunProduce(this);
        SunFlowers.SunFlowersJPanel(main_Dave, this, SumCont_Display);
        sunProduce.Lodingdata(main_Dave, SumCont_Display);
        DefaultButton.ComeBackButton(this, lastJPanel, frame);
        Loding.readjson();
    }

    CardSkill cardSkill;
    Dave main_Dave;
    Zombie zombie;

    void Attack_data_loging() {
        main_Dave = new Dave();
        zombie = new Zombie();
        cardSkill = new CardSkill(main_Dave, this);
    }

    JLabel SumCont_Display;

    public void addSunCont() {
        SumCont_Display = new JLabel(main_Dave.getCont() + "");
        SumCont_Display.setLocation(30, 75);
        SumCont_Display.setSize(50, 50);
        Card_panel.add(SumCont_Display);
    }

    boolean bg_anime_sw = true;

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        g.drawImage(BG, 0, 0, bg.getIconWidth(), GameDefaultSettingData.GAME_WIN_HEIGHT, this);
    }

    int bg_x = 0;

    Image BG;
    ImageIcon bg = new ImageIcon("draw/image/background4.jpg");

    public void setbgloging() {
        // ImageIcon bg = new ImageIcon("draw/image/background4.jpg");
        bg.setImage(bg.getImage().getScaledInstance(bg.getIconWidth() + 300, GameDefaultSettingData.GAME_WIN_HEIGHT,
                Image.SCALE_SMOOTH));
        BG = bg.getImage();
        ;
        bG_JLabel = new JLabel(bg);
        bG_JLabel.setLocation(0, 0);
        bG_JLabel.setSize(bg.getIconWidth(), bg.getIconHeight());
        add(bG_JLabel);
    }

    JLabel Dave;
    List<JLabel> zombiList;

    void addZombie(int round) {
        if (zombieCreate.HPbar != null)
            remove(zombieCreate.HPbar);
        zombiList = null;
        zombiList = zombieCreate.RandomZombie(round);
        zombie.setHP(zombiList.size() * 100);
        add(zombieCreate.Zombie_HP(zombiList, zombie.getHP()));
        for (int i = 0; i < zombiList.size(); i++) {
            add(zombiList.get(i));
            Anime.ZombieWalkAnime(zombiList.get(i), 400).start();
        }
        if (main_Dave.HPbar == null) {
            add(main_Dave.Dave_HPBar());
            add(main_Dave.dave_DEFBar());
        }

        repaint();

    }

    void addMain_character() {
        // character.setImage(character.getImage().getScaledInstance(character.getIconWidth()
        // / 2,
        // character.getIconHeight() / 2, Image.SCALE_SMOOTH));
        Dave = new JLabel(Dave_img);
        Dave.setSize(Dave_img.getIconWidth(), Dave_img.getIconHeight());
        Dave.setLocation(0, 200);
        Dave.setVisible(false);
        add(Dave);
    }

    JLabel SeedBank;
    ImageIcon SeedBank_img = new ImageIcon("draw/image/SeedBank.png");

    void addSeedBank() {
        SeedBank_img.setImage(SeedBank_img.getImage().getScaledInstance(SeedBank_img.getIconWidth(),
                SeedBank_img.getIconHeight(), Image.SCALE_SMOOTH));
        SeedBank = new JLabel(SeedBank_img);
        SeedBank.setLocation(GameDefaultSettingData.GAME_WIN_WIDTH / 2 - SeedBank_img.getIconWidth() / 2,
                GameDefaultSettingData.GAME_WIN_HEIGHT - SeedBank_img.getIconHeight() - 50);
        SeedBank.setSize(SeedBank_img.getIconWidth(), SeedBank_img.getIconHeight());
        SeedBank.setVisible(false);
        add(SeedBank);
    }

    public void addCard_panel() {
        Card_panel = new JPanel();
        Card_panel.setBackground(null);
        Card_panel.setOpaque(false);
        Card_panel.setLocation(GameDefaultSettingData.GAME_WIN_WIDTH / 2 - SeedBank_img.getIconWidth() / 2,
                GameDefaultSettingData.GAME_WIN_HEIGHT - SeedBank_img.getIconHeight() - 80);
        Card_panel.setSize(SeedBank_img.getIconWidth(), SeedBank_img.getIconHeight() + 50);
        Card_panel.setLayout(null);
        Card_panel.add(addCardMessge());
        add(Card_panel);
    }

    public JLabel addCardMessge() {
        JLabel jLabel = new JLabel("you");
        jLabel.setLocation(100, 0);
        return jLabel;
    }

    Thread main_Thread = new Thread(() -> { // 运行线程


        Thread Anime_bg = Anime.Anime_bg_loging(bG_JLabel, 200);
        if (!Anime_bg.isAlive())
            Anime_bg.run();

        Voice.BackGroundmusic(Voice.BackGroundMusic1).start();
        Voice.anime_music(Voice.zombieComing).start();

        remove(bG_JLabel);
        GetCard();
        Dave.setVisible(true);
        SeedBank.setVisible(true);
        addZombie(round);
        Nuts.RunStartNuts_JPanel(main_Dave, this);
        zombieCreate.zombie_Attack_Thread(zombie, main_Dave).start();
        sunProduce.RunSunProduce().start();
    });

    public void addWashCard() {
        ImageIcon Button = new ImageIcon("draw/img/Button.png");
        JButton washCard = new JButton("<html>" + GameDefaultSettingData.washCardDefaultCont + "洗牌</html>", Button);
        washCard.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签文字之间的距离
        // washCard.setBorder(BorderFactory.createLineBorder(Color.green));//设置边框
        washCard.setHorizontalTextPosition(SwingConstants.CENTER);
        washCard.setBackground(null);
        washCard.setSize(Button.getIconWidth(), Button.getIconHeight());
        washCard.setLocation(900, 620);
        washCard.setForeground(Color.green);
        washCard.setFont(new Font("黑体", Font.PLAIN, 20));
        washCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cardSkill.ishaveCont_WashCard(GameDefaultSettingData.washCardDefaultCont) && !Anime.Anime_isRun)
                    new Thread(() -> {
                        main_Dave.setCont(main_Dave.getCont() - GameDefaultSettingData.washCardDefaultCont);
                        SumCont_Display.setText(main_Dave.getCont() + "");

                        for (int j = 0; j < card_JLabels.size(); j++) {
                            Card_panel.remove(card_JLabels.get(j));
                        }
                        card_JLabels.clear();
                        cardRandeom = null;
                        cardRandeom = new CardRandeom();
                        GetCard();
                    }).start();

            }
        });

        add(washCard);
    }

    List<JLabel> card_JLabels = new ArrayList<>();

    CardRandeom cardRandeom = new CardRandeom();

    public void GetCard() {
        JPanel panel = this;
        int[] Card_hand = cardRandeom.RandomCard();
        int x = 50;
        for (int i = 0; i < Card_hand.length; i++) {
            int Card_id = i;

            System.out.println(Card_hand[i]);

            if (Card_hand[i] == 0)
                continue;

            ImageIcon icon = new ImageIcon("draw/image/plant/card_" + Card_hand[i] + ".png");
            JLabel jLabel = new JLabel(icon);
            jLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
            jLabel.setLocation(x += icon.getIconWidth() + 10, 35);
            jLabel.addMouseListener(new MouseListener() {
                Boolean isClicked = true;

                @Override
                public void mouseClicked(MouseEvent e) {

                    int Card_ = Card_hand[Card_id] - 1;

                    if (isClicked) {
                        isClicked = false;
                        jLabel.setLocation(jLabel.getX(), jLabel.getY() - 20);
                    } else {
                        isClicked = true;
                        jLabel.setLocation(jLabel.getX(), jLabel.getY() + 20);
                    }
                    if (e.getClickCount() >= 2 && !Anime.Anime_isRun) {
                        if (cardSkill.ishaveCont(Card_)) {
                            new Thread(() -> {
                                new Thread(() -> {
                                    Card_panel.remove(jLabel);
                                    SumCont_Display.setText(cardSkill.PayCont(Card_) + "");
                                    SumCont_Display.setText(cardSkill.up_SunCont(Card_) + "");
                                    cardSkill.play_anime(Card_, panel, zombieCreate.array);
                                    zombieCreate.zombie_hit(cardSkill.get_ATK(Card_), zombie, panel);
                                    if (card_JLabels.size() != 0)
                                        card_JLabels.remove(jLabel);
                                    if (card_JLabels.size() == 0) {
                                        if (zombie.getHP() == 0) {
                                            cardRandeom = null;
                                            cardRandeom = new CardRandeom();
                                        }
                                        GetCard();
                                    }
                                    repaint();
                                }).run();
                                if (zombie.getHP() == 0) {

                                    for (int j = 0; j < card_JLabels.size(); j++) {
                                        Card_panel.remove(card_JLabels.get(j));
                                        // System.out.println(card_JLabels.get(j).getText());
                                    }
                                    card_JLabels.clear();

                                    new Thread(() -> {
                                        CardDialog cardDialog = new CardDialog();
                                        while (cardDialog.isSelecting) {
                                            try {
                                                Thread.sleep(500);
                                            } catch (InterruptedException e1) {
                                                // TODO Auto-generated catch block
                                                e1.printStackTrace();
                                            }
                                        }
                                        cardRandeom = null;
                                        cardRandeom = new CardRandeom();
                                        GetCard();
                                        addZombie(++round);
                                    }).start();

                                }
                            }).start();

                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub

                }

            });
            card_JLabels.add(jLabel);

            Card_panel.add(jLabel);
        }
        repaint();
    }

}
