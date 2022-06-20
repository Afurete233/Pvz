package app.Plant;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.GameDefaultSettingData;
import app.Spirit.Dave;

public class SunFlowers {

    ImageIcon SunFlower_normal = new ImageIcon("draw/img/plant/SunFlower.gif");// 向日葵未发光状态
    ImageIcon SunFlower_light = new ImageIcon("draw/img/plant/SunFlower2.gif");// 向日葵发光状态
    List<JLabel> SunFlowers = new ArrayList<>();

    Dave dave;
    JPanel main_JPanel;
    JPanel sunFlower_JPanel;

    public SunFlowers(Dave dave, JPanel main_JPanel) {
        this.dave = dave;
        this.main_JPanel = main_JPanel;
        sunFlower_JPanel = new JPanel();
        sunFlower_JPanel.setBackground(null);
        sunFlower_JPanel.setOpaque(false);
        sunFlower_JPanel.setLocation(100, 300);
        sunFlower_JPanel.setSize(GameDefaultSettingData.GAME_WIN_WIDTH, 100);
        sunFlower_JPanel.setLayout(null);
        main_JPanel.add(sunFlower_JPanel);
    }

    public void addSunFlowers() {

    }

    public Thread SunFlower_ProduceSun() {
        SunFlowers.add(SunFlower());
        return new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(GameDefaultSettingData.SunFlowerProduceSunDefaultTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
    }

    public JLabel SunFlower() {
        JLabel Sun_Flower = new JLabel();
        Sun_Flower.setSize(SunFlower_normal.getIconWidth(), SunFlower_normal.getIconHeight());
        Sun_Flower.setLocation(50, 100);
        Sun_Flower.setOpaque(false);
        Sun_Flower.setIcon(SunFlower_normal);// 未发光状态
        return Sun_Flower;
    }

}
