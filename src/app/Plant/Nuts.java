package app.Plant;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.CardSkillData.CardSkillData;
import app.Spirit.Dave;

public class Nuts {

    static JPanel main_JPanel;
    static JPanel nuts_JPanel;
    static Dave dave;
    static public List<CardSkillData> list;
    static ImageIcon WallNut = new ImageIcon("draw/img/plant/WallNut.gif");// 土豆
    static ImageIcon Wallnut_slight_cracked = new ImageIcon("draw/img/plant/Wallnut_slight_cracked.gif");// 土豆轻微破损
    static ImageIcon Wallnut_serious_cracked = new ImageIcon("draw/img/plant/Wallnut_serious_cracked.gif");// 土豆严重破损

    static List<JLabel> nutList = new ArrayList<>();
    static List<Integer> nuts_state = new ArrayList<>();

    public static void RunStartNuts_JPanel(Dave dave_, JPanel main_JPanel_) {
        // list = CardSkillData;
        dave = dave_;
        main_JPanel = main_JPanel_;
        nuts_JPanel = new JPanel();
        nuts_JPanel.setBackground(null);
        nuts_JPanel.setOpaque(false);
        nuts_JPanel.setLayout(null);
        nuts_JPanel.setLocation(420, 200);
        nuts_JPanel.setSize(100, 400);
        main_JPanel_.add(nuts_JPanel);
    }

    public static void addNuts() {
        JLabel Nut = new JLabel(WallNut);
        Nut.setSize(WallNut.getIconWidth(), WallNut.getIconHeight());
        Nut.setLocation(0, 0);
        Nut.setOpaque(false);
        nutList.add(Nut);
        int DEF = getNutDEF();
        nuts_state.add(DEF / 100);
        dave.setDEF(dave.getDEF() + DEF);
        dave.DEFbar.setMaximum(dave.getDEF());
        dave.DEFbar.setValue(dave.getDEF());
        dave.DEFbar.setVisible(true);
        lodingNuts();
    }

    private static int getNutDEF() {
        CardSkillData cardSkillData = list.get(3);
        return cardSkillData.getDEF();
    }

    private static void lodingNuts() {
        nuts_JPanel.removeAll();
        for (int i = 0; i < nutList.size(); i++) {
            nutList.get(i).setLocation(10, WallNut.getIconHeight() * i + 5);
            nuts_JPanel.add(nutList.get(i));
        }
    }

    public static void Nut_cracked(int DMG) {
        int wasDMG = DMG / 100;
        if (nutList.size() != 0)
            for (int i = 0; i < nutList.size() && i < wasDMG; i++) {
                nuts_state.set(i, nuts_state.get(i) - 1);

                switch (nuts_state.get(i)) {
                    case 0:
                        nuts_JPanel.remove(nutList.get(i));
                        nuts_state.remove(i);
                        nutList.remove(i);
                        nuts_JPanel.repaint();
                        break;
                    case 1:
                        nutList.get(i).setIcon(Wallnut_serious_cracked);
                        break;
                    case 2:
                        nutList.get(i).setIcon(Wallnut_slight_cracked);
                        break;
                }
                main_JPanel.repaint();
            }
    }

}
