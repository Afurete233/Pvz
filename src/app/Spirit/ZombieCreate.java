package app.Spirit;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import app.Anime;
import app.GameDefaultSettingData;

public class ZombieCreate {

    ImageIcon Zombieation = new ImageIcon("draw/img/zombieation/zombie1.gif");

    ImageIcon Head = new ImageIcon("draw/img/zombieation/ZombieHead2.gif");
    ImageIcon Die = new ImageIcon("draw/img/zombieation/ZombieDie2.gif");
    int Zombieswidth;
    public JProgressBar HPbar;
    List<JLabel> array;

    public List<JLabel> RandomZombie(int round) {
        array = new ArrayList<>();
        int ZombieNum = new Random().nextInt(GameDefaultSettingData.DefaultZombieMax
                + round) + 1;
        // int ZombieNum = 3;
        if (ZombieNum == 0)
            ZombieNum = 1;

        Zombieswidth = Zombieation.getIconWidth() * ZombieNum + Zombieation.getIconWidth() / 3 * 2 * ZombieNum;

        for (int i = 0; i < ZombieNum; i++) {
            ImageIcon Zombieation = new ImageIcon(
                    "draw/img/zombieation/zombie" + (new Random().nextInt(3) + 1) + ".gif");
            JLabel jLabel = new JLabel(Zombieation);
            jLabel.setSize(Zombieation.getIconWidth(), Zombieation.getIconHeight());
            jLabel.setLocation(
                    GameDefaultSettingData.GAME_WIN_WIDTH / 6 * 5 - Zombieswidth / 2
                            + Zombieation.getIconWidth() / 3 * 2 * i,
                    GameDefaultSettingData.GAME_WIN_HEIGHT / 2 - Zombieation.getIconHeight() / 2);
            array.add(jLabel);
        }
        return array;
    }

    public JProgressBar Zombie_HP(List<JLabel> list, int HP) {
        HPbar = null;
        HPbar = new JProgressBar(0, HP);
        HPbar.setStringPainted(true);
        HPbar.setForeground(Color.RED);
        HPbar.setBackground(null);
        HPbar.setOpaque(false);
        HPbar.setValue(HP);
        HPbar.setSize(Zombieswidth / 2, 13);
        HPbar.setLocation(GameDefaultSettingData.GAME_WIN_WIDTH / 6 * 5 - Zombieswidth / 2,
                GameDefaultSettingData.GAME_WIN_HEIGHT / 2 + Zombieation.getIconHeight() / 2);
        return HPbar;
    }

    public void zombie_Attack(Zombie zombie, Dave dave) {
        int ATK = zombie.getATK();
        int DMG = Math.abs(dave.getDEF() - ATK);

        if (dave.getDEF() - ATK <= 0) {
            dave.setDEF(0);
        } else {
            dave.setDEF(DMG);
        }

        Anime.ZombieWalkAnime_L(array.get(0),
                GameDefaultSettingData.GAME_WIN_WIDTH / 6 * 5 - Zombieswidth - 100)
                .run();

        dave.DEFbar.setValue(dave.getDEF());
        dave.setHP(dave.getHP() - DMG);
        Anime.hit_animeThread(dave.DEFbar, ATK).start();
        Anime.hit_animeThread(dave.HPbar, DMG).start();

        Anime.ZombieWalkAnime_R(array.get(0),
                GameDefaultSettingData.GAME_WIN_WIDTH / 6 * 5 - Zombieswidth - 100)
                .run();
        if (dave.getHP() <= 0) {
            dave.Dave_die(); 
        }
    }

    public Thread zombie_Attack_Thread(Zombie zombie, Dave dave) {
        return new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(GameDefaultSettingData.ZombieAttackSleepTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (!Anime.Anime_isbarRun && !Anime.Anime_isRun && array.size() != 0)
                    zombie_Attack(zombie, dave);

            }
        });
    }

    public void zombie_hit(int DMG, Zombie zombie, JPanel jPanel) {
        zombie.setHP(zombie.getHP() - DMG);

        int wasDMG = DMG / 100;
        if (array.size() != 0)
            new Thread(() -> {
                for (int i = 0; i < wasDMG; i++) {
                    Anime.hit_animeThread(HPbar, DMG).start();
                    Anime.ZombieDie_Anime(array.get(i)).run();
                    jPanel.remove(array.get(i));
                    jPanel.repaint();
                    array.remove(0);
                }
            }).run();

    }

}
