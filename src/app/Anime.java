package app;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.awt.Graphics2D;

public class Anime {

    public static boolean Anime_isRun = false;
    public static boolean Anime_isbarRun = false;

    public static void Anime_jump_down(JLabel jLabel, int up, int sleep) {
        Anime_isRun = true;
        new Thread(() -> {
            int contup = 0;
            int y = jLabel.getY();

            while (contup <= up) {
                contup++;
                jLabel.setLocation(jLabel.getX(), y - contup);
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            int d = y - contup;
            contup = 0;
            while (contup <= up) {
                contup++;
                jLabel.setLocation(jLabel.getX(), d + contup);
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            jLabel.setLocation(jLabel.getX(), y);

            Anime_isRun = false;
        }).start();
    }

    public static Thread Anime_bg_loging(JLabel jLabel, int MAX) {
        Anime_isRun = true;
        return new Thread(() -> {
            try {
                Voice.anime_music_palyer(Voice.loding_sound).start();;
                int min = 1;
                int old_x = jLabel.getX();
                // int old_y = jLabel.getY();
                while (min < MAX) {
                    jLabel.setLocation(old_x - min, jLabel.getY());
                    min++;
                    Thread.sleep(10);
                }
                Thread.sleep(1000);

                int new_x = jLabel.getX();

                while (new_x <= old_x) {
                    jLabel.setLocation(new_x++, jLabel.getY());
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            Voice.outplayer.close();
            Anime_isRun = false;
        });
    }

    public static Thread hit_animeThread(JProgressBar bar, int DMG) {
        Anime_isbarRun = true;
        return new Thread(() -> {
            try {
                int lost = bar.getValue();
                while (bar.getValue() > lost - DMG) {
                    bar.setValue(bar.getValue() - 1);
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            Anime_isbarRun = false;
        });
    }

    public static Thread ZombieDie_Anime(JLabel jLabel) {
        Anime_isRun = true;
        ImageIcon Die = new ImageIcon("draw/img/zombieation/ZombieDie_all.gif");
        Die.setImage(Die.getImage().getScaledInstance(Die.getIconWidth() + 40, Die.getIconHeight() + 40,
                Image.SCALE_REPLICATE));
        return new Thread(() -> {
            jLabel.setLocation(jLabel.getX() - 40, jLabel.getY() - 40);
            jLabel.setSize(Die.getIconWidth(), Die.getIconHeight());
            jLabel.setIcon(Die);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Anime_isRun = false;
        });
    }

    public static Thread ZombieBoomDie_Anime(JLabel jLabel) {
        Anime_isRun = true;
        ImageIcon Die = new ImageIcon("draw/img/zombieation/ZombieBoomDie.gif");
        Die.setImage(Die.getImage().getScaledInstance(Die.getIconWidth() + 10, Die.getIconHeight() + 10,
                Image.SCALE_REPLICATE));
        return new Thread(() -> {
            jLabel.setLocation(jLabel.getX() - 10, jLabel.getY() - 10);
            jLabel.setSize(Die.getIconWidth(), Die.getIconHeight());
            jLabel.setIcon(Die);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Anime_isRun = false;
        });
    }

    public static Thread ZombieWalkAnime(JLabel ZombieWalkAnime, int Walk) {
        Anime_isRun = true;
        ImageIcon ZombieWalk = new ImageIcon("draw/img/zombieation/ZombieWalkAnime.gif");
        return new Thread(() -> {
            int w = 0;
            int old_x = ZombieWalkAnime.getX();
            int old_y = ZombieWalkAnime.getY();
            Icon old = ZombieWalkAnime.getIcon();
            ZombieWalkAnime.setSize(ZombieWalk.getIconWidth(), ZombieWalk.getIconHeight());
            ZombieWalkAnime.setIcon(ZombieWalk);
            for (; w < Walk; w++) {
                ZombieWalkAnime.setLocation(old_x + Walk - w, old_y + 15);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (w == Walk) {
                ZombieWalkAnime.setLocation(old_x + Walk - w, old_y);
                ZombieWalkAnime.setSize(old.getIconWidth(), old.getIconHeight());
                ZombieWalkAnime.setIcon(old);
            }
            Anime_isRun = false;
        });
    }

    public static Thread ZombieWalkAnime_L(JLabel ZombieWalkAnime, int Walk) {
        Anime_isRun = true;
        Voice.isMusicRun = true;
        int old_x = ZombieWalkAnime.getX();
        int old_y = ZombieWalkAnime.getY();
        Icon old = ZombieWalkAnime.getIcon();
        ImageIcon ZombieWalk = new ImageIcon("draw/img/zombieation/ZombieWalkAnime.gif");
        ImageIcon ImgZombieAttack = new ImageIcon("draw/img/zombieation/ZombieAttack1.gif");
        return new Thread(() -> {
            ZombieWalkAnime.setIcon(ZombieWalk);
            for (int i = 0; i <= Walk; i++) {
                ZombieWalkAnime.setLocation(old_x - i, old_y + 5);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Voice.anime_music_loop(Voice.SoundZombieAttack).start();
            ZombieWalkAnime.setIcon(ImgZombieAttack);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            ZombieWalkAnime.setIcon(old);
            Anime_isRun = false;
            Voice.isMusicRun = false;
        });
    }

    public static Thread ZombieWalkAnime_R(JLabel ZombieWalkAnime, int Walk) {
        Anime_isRun = true;
        int old_x = ZombieWalkAnime.getX();
        int old_y = ZombieWalkAnime.getY();
        Icon old = ZombieWalkAnime.getIcon();
        ImageIcon ZombieWalk = new ImageIcon("draw/img/zombieation/ZombieWalkAnime.gif");
        // ImageIcon ImgZombieAttack = new
        // ImageIcon("draw/img/zombieation/ZombieAttack1.gif");
        return new Thread(() -> {
            ZombieWalkAnime.setIcon(ZombieWalk);
            for (int i = 0; i <= Walk; i++) {
                ZombieWalkAnime.setLocation(old_x + i, old_y);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ZombieWalkAnime.setLocation(ZombieWalkAnime.getX(), old_y - 5);
            ZombieWalkAnime.setIcon(old);
            Anime_isRun = false;
        });
    }

    public static Thread CherryBoom_anime(JPanel jPanel, JLabel zombie) {
        Anime_isRun = true;
        ImageIcon CherryBoom = new ImageIcon("draw/img/plant/CherryBoom2.gif");
        JLabel Cherry = new JLabel(CherryBoom);
        Cherry.setSize(CherryBoom.getIconWidth(), CherryBoom.getIconHeight());
        Cherry.setLocation(zombie.getX() - 100, zombie.getY() - 50);
        Cherry.setOpaque(false);
        jPanel.add(Cherry, 1);
        return new Thread(() -> {
            try {
                Thread.sleep(1000);
                Voice.anime_music(Voice.SoundCherrBoom).start();
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jPanel.remove(Cherry);
            jPanel.repaint();
            Anime_isRun = false;
        });
    }

    public static Thread Peashooter_Attack(JPanel jPanel, int way) {
        Anime_isRun = true;
        ImageIcon Image_Peashooter = new ImageIcon("draw/img/plant/Peashooter.gif");// 豌豆射手
        ImageIcon Peas = new ImageIcon("draw/img/plant/bullet1.gif");
        ImageIcon ImgPeasHit = new ImageIcon("draw/img/plant/PeaBulletHit.gif");

        int location_x = 300;
        int location_y = 350;

        JLabel Peashooter = new JLabel(Image_Peashooter);

        Peashooter.setSize(Image_Peashooter.getIconWidth(), Image_Peashooter.getIconHeight());
        Peashooter.setLocation(location_x,
                location_y);
        Peashooter.setOpaque(false);

        JLabel Lpeas = new JLabel();
        Lpeas.setSize(Peas.getIconWidth(), Peas.getIconHeight());
        Lpeas.setLocation(location_x + 20, location_y);
        Lpeas.setOpaque(false);
        Lpeas.setIcon(Peas);

        jPanel.add(Lpeas);
        jPanel.add(Peashooter);
        return new Thread(() -> {
            for (int x = 20; x <= way + 5; x++) {
                Lpeas.setLocation(location_x + 20 + x, location_y);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (x == way) {
                    Lpeas.setSize(ImgPeasHit.getIconWidth(), ImgPeasHit.getIconHeight());
                    Lpeas.setIcon(ImgPeasHit);
                }
            }
            Voice.anime_music(Voice.SoundPlantAttack).start();
            jPanel.remove(Peashooter);
            jPanel.remove(Lpeas);
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            jPanel.repaint();
            Anime_isRun = false;
        });
    }

    public static Thread SunFlowerSunProduceAnime(JLabel jLabel) {
        ImageIcon SunFlower_light = new ImageIcon("draw/img/plant/SunFlower2.gif");// 向日葵发光状态
        Icon old = jLabel.getIcon();
        return new Thread(() -> {
            jLabel.setIcon(SunFlower_light);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jLabel.setIcon(old);
        });
    }

    public static Thread DeathAnimation(JPanel jPanel) {
        Anime_isRun = true;
        ImageIcon ImgDeath = new ImageIcon("draw/img/ZombiesWon.gif");
        JLabel DeathAnimation = new JLabel(ImgDeath);
        DeathAnimation.setLocation(GameDefaultSettingData.GAME_WIN_WIDTH / 2 - ImgDeath.getIconWidth() / 2,
                GameDefaultSettingData.GAME_WIN_HEIGHT / 2 - ImgDeath.getIconHeight() / 2);
        jPanel.add(DeathAnimation, 1);
        DeathAnimation.setSize(ImgDeath.getIconWidth(), ImgDeath.getIconHeight());
        DeathAnimation.setOpaque(false);
        Voice.anime_music(Voice.SoundZombieWon).start();
        return new Thread(() -> {
            // ImgDeath.setImage(ImgDeath.getImage().getScaledInstance(40, 40,
            // Image.SCALE_DEFAULT));

            for (int x = 400; x < 450; x += 5) {
                ImgDeath.setImage(ImgDeath.getImage().getScaledInstance(x, x, Image.SCALE_SMOOTH));
                jPanel.repaint();

                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
