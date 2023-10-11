package app;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Spirit.Dave;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SunProduce {

    Boolean yg = false;
    int x1;
    int y1;

    JPanel jPanel;

    Dave dave;
    JLabel Sundisplay;

    ImageIcon Sun_img = new ImageIcon("draw/img/plant/Sun.gif");

    public SunProduce(JPanel jPanel) {
        this.jPanel = jPanel;
        Sun_img.setImage(Sun_img.getImage().getScaledInstance(Sun_img.getIconWidth(), Sun_img.getIconHeight(),
                Image.SCALE_REPLICATE));

    }

    public void Lodingdata(Dave dave, JLabel Sundisplay) {
        this.dave = dave;
        this.Sundisplay = Sundisplay;
    }

    public void addSunCont() {
        dave.setCont(dave.getCont() + GameDefaultSettingData.SunProduceSunDefaultCont);
        Sundisplay.setText(dave.getCont() + "");
    }

    public Thread RunSunProduce() {
        return new Thread(() -> {

            while (true) {

                sunProduce_Thread();

                try {
                    Thread.sleep(GameDefaultSettingData.SunProduceSunDefaultTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
    }

    public static int random_num(int rand_max, int rand_min) {
        Random random = new Random();
        while (true) {
            int num1 = random.nextInt(rand_min);
            int num2 = random.nextInt(rand_max);
            int num3 = random.nextInt(2);
            if (num2 > num1 && num3 != 0) {
                return (num2 + num1) / num3;
            }
        }
    }

    public void sunProduce_Thread() {
        int x_down = random_num(800, 400);// 随机在x轴任意点位生成阳光
        int y_down = random_num(400, 100);// 随机在y轴定义下落高度最大值
        Sun_img = new ImageIcon("draw/img/plant/Sun.gif");
        Sun_img.setImage(Sun_img.getImage().getScaledInstance(Sun_img.getIconWidth(), Sun_img.getIconHeight(),
                Image.SCALE_REPLICATE));

        JLabel Sun = new JLabel(Sun_img);
        Sun.setSize(Sun_img.getIconWidth(), Sun_img.getIconHeight());
        Sun.setLocation(x_down, 0);

        jPanel.add(Sun, 1);

        new Thread(() -> {
            yg = false;
            boolean back = false;
            int k = 0;
            for (; k < y_down; k++) {
                if (yg == true) {
                    // int temp = 80;
                    while (true) {
                        try {
                            x1 -= x1 / y1;
                            y1 -= 1;
                            // if (temp < 60 && temp > 40) {
                            //     temp -= 3;
                            //     Sun_img.setImage(Sun_img.getImage().getScaledInstance(temp,
                            //             temp,
                            //             Image.SCALE_DEFAULT));
                            // }
                            // if (temp > 60) {
                            //     temp -= (temp - 60) / 10;
                            //     Sun_img.setImage(Sun_img.getImage().getScaledInstance(temp,
                            //             temp,
                            //             Image.SCALE_DEFAULT));
                            // }
                        } catch (ArithmeticException e) {
                            break;
                        }
                        Sun.setLocation(x1, y1);
                        Sun.repaint();
                        if (x1 < -50 && y1 < -50) {
                            break;
                        }
                        try {
                            back = true;
                            Thread.sleep(10);// 回到初始点延迟
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(10);// 删除阳光延迟
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    jPanel.remove(Sun);
                    jPanel.repaint();
                    System.out.println("线程停止");
                    break;
                }
                Sun.setLocation(x_down, k);

                if (back != true) {
                    try {
                        int fall = (450 - y_down) / 10;
                        // System.out.println(fall);
                        if (fall > 18) {
                            fall = 18;
                        }
                        if (fall < 10) {
                            fall = 10;
                        }
                        Thread.sleep(fall);// 下落延迟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(800);// 消失延迟
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if (back != true) {
                jPanel.remove(Sun);
                jPanel.repaint();
                System.out.println("超时消失");
            }
        }).start();

        Sun.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x1 = Sun.getX();
                y1 = Sun.getY();
                yg = true;
                Voice.anime_music(Voice.GetSun_sound).start();
                addSunCont();
            }
        });

        // jPanel.addMouseListener(new MouseAdapter() {
        //     public void mousePressed(MouseEvent e) {
        //         int x = e.getX();
        //         int y = e.getY();
        //         x1 = Integer.valueOf(Sun.toString().split("defaultIcon=")[0].split(",")[1])
        //                 .intValue();
        //         y1 = Integer.valueOf(Sun.toString().split("defaultIcon=")[0].split(",")[2])
        //                 .intValue();
        //         if (x >= x1 + 0 && x <= x1 + 75 && yg == false) {
        //             if (y >= y1 + 30 && y <= y1 + 105) {
        //                 yg = true;
        //                 System.out.println("获取阳光");
        //                 addSunCont();
        //             }
        //         }
        //     }
        // });
    }

}
