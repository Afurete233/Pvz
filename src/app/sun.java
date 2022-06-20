import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class sun2 {
        static Boolean yg = false;
        static int x1;
        static int y1;

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

        public static void summon_sun() {
                int x_down = random_num(800, 400);// 随机在x轴任意点位生成阳光
                int y_down = random_num(400, 100);// 随机在y轴定义下落高度最大值
                System.out.printf("阳光生成: x:%s y:%s\n", x_down, y_down);

                JFrame jf = new JFrame("sun");
                jf.setSize(1280, 700);
                jf.setLocation(0, 0);
                JLabel jl = new JLabel("", JLabel.LEFT);
                ;
                ImageIcon image;
                image = new ImageIcon("img/Sun(1).gif");
                Image img = image.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
                image.setImage(img);
                JPanel jp = new JPanel();
                jl.setIcon(image);
                jl.setLocation(x_down, 0);
                jp.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
                jp.add(jl);
                jf.add(jp);
                jp.setSize(1280, 700);
                jp.setLocation(x_down, 0);
                new Thread(() -> {
                        boolean back = false;
                        int k = 0;
                        for (; k < y_down; k++) {
                                if (yg == true) {
                                        int temp = 80;
                                        while (true) {
                                                try {
                                                        x1 -= x1 / y1;
                                                        y1 -= 1;
                                                        if (temp < 60 && temp > 40) {
                                                                temp -= 2;
                                                                image.setImage(image.getImage().getScaledInstance(temp,
                                                                                temp,
                                                                                Image.SCALE_DEFAULT));
                                                        }
                                                        if (temp > 60) {
                                                                temp -= (temp - 60) / 10;
                                                                image.setImage(image.getImage().getScaledInstance(temp,
                                                                                temp,
                                                                                Image.SCALE_DEFAULT));
                                                        }
                                                } catch (ArithmeticException e) {
                                                        break;
                                                }
                                                jl.setLocation(x1, y1);
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
                                        jp.remove(jl);
                                        jp.repaint();
                                        System.out.println("线程停止");
                                        break;
                                }
                                jl.setLocation(x_down, k);

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
                                jp.remove(jl);
                                jp.repaint();
                                System.out.println("超时消失");
                        }

                }).start();
                jf.addMouseListener(new MouseAdapter() {

                        public void mousePressed(MouseEvent e) {
                                int x = e.getX();
                                int y = e.getY();
                                x1 = Integer.valueOf(jl.toString().split("defaultIcon=")[0].split(",")[1])
                                                .intValue();
                                y1 = Integer.valueOf(jl.toString().split("defaultIcon=")[0].split(",")[2])
                                                .intValue();
                                if (x >= x1 + 0 && x <= x1 + 75 && yg == false) {
                                        if (y >= y1 + 30 && y <= y1 + 105) {
                                                yg = true;
                                                System.out.println("获取阳光");
                                        }
                                }
                        }

                });
                jf.setVisible(true);
                jf.setLocationRelativeTo(null);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public static void main(String[] args) throws Exception {
                summon_sun();
        }
}
