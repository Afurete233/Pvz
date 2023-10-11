package app.Spirit;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import app.Anime;
import app.GameDefaultSettingData;
import app.Voice;

import java.awt.Color;

public class Dave {

    private int HP = 200;

    public int getHP() {
        return HP;
    }

    public void setHP(int hP) {
        HP = hP;
    }

    private int DEF = 0;

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int dEF) {
        DEF = dEF;
    }

    private int Cont = GameDefaultSettingData.DaveDefaultSunCont;

    public int getCont() {
        return Cont;
    }

    public void setCont(int cont) {
        Cont = cont;
    }

    public JProgressBar HPbar;

    public JProgressBar Dave_HPBar() {
        HPbar = new JProgressBar(0, getHP());
        HPbar.setStringPainted(true);
        HPbar.setForeground(Color.RED);
        HPbar.setBackground(null);
        HPbar.setOpaque(false);
        HPbar.setValue(HP);
        HPbar.setLocation(10, 180);
        HPbar.setSize(200, 13);
        return HPbar;
    }

    public JProgressBar DEFbar;

    public JProgressBar dave_DEFBar() {
        DEFbar = new JProgressBar(0, getDEF());
        DEFbar.setStringPainted(true);
        DEFbar.setForeground(Color.ORANGE);
        DEFbar.setBackground(null);
        DEFbar.setOpaque(false);
        DEFbar.setValue(HP);
        DEFbar.setLocation(10, 165);
        DEFbar.setSize(200, 13);
        if (getDEF() <= 0)
            DEFbar.setVisible(false);
        return DEFbar;
    }

    public void Dave_die(JPanel jPanel) {
        Anime.DeathAnimation(jPanel).start();
    }

}
