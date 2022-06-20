package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DefaultButton {

    public static void ComeBackButton(JPanel jPanel, JPanel LastjJPanel, JFrame frame) {
        JButton button = new JButton();
        button.setLocation(10, 10);
        ImageIcon back = new ImageIcon("draw/img/back.png");
        back.setImage(back.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setIcon(back);
        button.setSize(30, 30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(jPanel);
                frame.add(LastjJPanel);
                frame.validate();
                frame.invalidate();
                frame.repaint();
            }
        });
        jPanel.add(button);
    }

}
