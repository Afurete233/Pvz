package app;

import javax.swing.JFrame;

public class App extends JFrame {

    App() {
        new UI_Demo(this);
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
