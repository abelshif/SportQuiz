package Klient.gui;

import Server.Score;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
        JLabel endScore1;
        JLabel endScore2;


        public ScorePanel() {
            endScore1 = new JLabel();
            endScore2 = new JLabel();

            setLayout(null);
            setBackground(new Color(127, 61, 61));

            endScore1.setBounds(30, 100, 155, 100);
            endScore2.setBounds(30, 300, 155, 100);

            endScore1.setFont(new Font("Serif", Font.PLAIN, 20));
            endScore2.setFont(new Font("Serif", Font.PLAIN, 20));

            add(endScore1);
            add(endScore2);



            setLocation(600, 90);
            setVisible(true);
            setSize(400, 500);

        }

        public void setScoreLabel(Score score){
            System.out.println(score.getYourScore() + " " + score.getOpponentScore());
            endScore1.setText("Player1 score: "+ score.getYourScore());
            endScore2.setText("Player2 score: "+ score.getOpponentScore());
        }
}
