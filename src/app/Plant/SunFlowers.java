package app.Plant;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Anime;
import app.GameDefaultSettingData;
import app.Spirit.Dave;

public class SunFlowers {

    static ImageIcon SunFlower_normal = new ImageIcon("draw/img/plant/SunFlower.gif");// 向日葵未发光状态
    static ImageIcon SunFlower_light = new ImageIcon("draw/img/plant/SunFlower2.gif");// 向日葵发光状态
    static List<JLabel> SunFlowers = new ArrayList<>();

    static Dave dave;
    static JPanel main_JPanel;
    static JPanel sunFlower_JPanel;
    static JLabel SumCont_Display;

    public static void SunFlowersJPanel(Dave dave_, JPanel main_JPanel_, JLabel SunDisplay) {
        dave = dave_;
        main_JPanel = main_JPanel_;
        SumCont_Display = SunDisplay;
        sunFlower_JPanel = new JPanel();
        sunFlower_JPanel.setBackground(null);
        sunFlower_JPanel.setOpaque(false);
        sunFlower_JPanel.setLocation(320, 100);
        sunFlower_JPanel.setSize(GameDefaultSettingData.GAME_WIN_WIDTH, 100);
        sunFlower_JPanel.setLayout(null);
        main_JPanel.add(sunFlower_JPanel);
    }

    public static void addSunFlowers() {
        // sunFlower_JPanel.removeAll();

        JLabel Sun_Flower = new JLabel(SunFlower_normal);
        Sun_Flower.setSize(SunFlower_normal.getIconWidth(), SunFlower_normal.getIconHeight());
        Sun_Flower.setLocation(0, 10);
        Sun_Flower.setOpaque(false);
        SunFlower_ProduceSun(Sun_Flower).start();

        SunFlowers.add(Sun_Flower);

        for (int i = 0; i < SunFlowers.size(); i++) {
            SunFlowers.get(i).setLocation(SunFlower_normal.getIconWidth() * i + 10, 10);
        }

        sunFlower_JPanel.add(Sun_Flower);
        sunFlower_JPanel.repaint();
        main_JPanel.repaint();
    }

    public static Thread SunFlower_ProduceSun(JLabel jLabel) {
        return new Thread(() -> {
            while (true) {

                dave.setCont(dave.getCont() + GameDefaultSettingData.SunProduceSunDefaultCont);
                SumCont_Display.setText(dave.getCont() + "");
                Anime.SunFlowerSunProduceAnime(jLabel).start();

                try {
                    Thread.sleep(GameDefaultSettingData.SunFlowerProduceSunDefaultTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }

    // public static JLabel SunFlower() {

    // return Sun_Flower;
    // }

}
