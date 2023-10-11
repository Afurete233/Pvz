package app;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Image;



public class App extends JFrame {

    ImageIcon icon = new ImageIcon("draw/img/Pvz.jpg");    

    App() {
        new UI_Demo(this);

        setIconImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        setResizable(false);
        setLocation(100, 100);
        setSize(GameDefaultSettingData.GAME_WIN_WIDTH, GameDefaultSettingData.GAME_WIN_HEIGHT);
        setTitle(GameDefaultSettingData.GAME_TITLE);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception {

        new App();
    }

}
