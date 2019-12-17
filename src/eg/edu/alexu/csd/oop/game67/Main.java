package eg.edu.alexu.csd.oop.game67;

import eg.edu.alexu.csd.oop.game.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Main   {



    public static void main(String[] args) {
         JFrame f =new JFrame();
        f.setLayout(null);
       f.getContentPane().setBackground(Color.BLUE);
        f.setTitle("DISNEY");
        f.setSize(600, 1000);
      f.setDefaultCloseOperation(EXIT_ON_CLOSE);
       f.setVisible(true);
       f.setResizable(false);
        ImageIcon icon = new ImageIcon("d2.jpg");
        JLabel label = new JLabel("");
        label.setIcon(icon);
     f.add(label);
        label.setBounds(0, 0, 600, 1000);
        JButton L1 = new JButton();
        ImageIcon icon2 = new ImageIcon("mickey2.png");
        L1.setIcon(icon2);
       f.add(L1);
        L1.setBounds(0, 800, 100, 100);

        L1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
f.setVisible(false);
                JMenuBar menuBar = new JMenuBar();;
                JMenu menu = new JMenu("File");
                JMenuItem newMenuItem = new JMenuItem("New");
                JMenuItem pauseMenuItem = new JMenuItem("Pause");
                JMenuItem resumeMenuItem = new JMenuItem("Resume");
                menu.add(newMenuItem);
                menu.addSeparator();
                menu.add(pauseMenuItem);
                menu.add(resumeMenuItem);
                menuBar.add(menu);

                final GameEngine.GameController gameController = GameEngine.start("Disney Plates <33", new MyGameWorld(550, 700), menuBar, Color.BLACK);

                newMenuItem.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        gameController.changeWorld(new MyGameWorld(800, 700));
                    }
                });
                pauseMenuItem.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        gameController.pause();
                    }
                });
                resumeMenuItem.addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        gameController.resume();
                    }
                });
            }
        });


    }


}