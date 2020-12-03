package Klient.gui;

import Server.Score;
import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    JLabel endScore1;
    JLabel endScore2;

    public ScorePanel() {

        endScore1 = new JLabel("", SwingConstants.CENTER);
        endScore2 = new JLabel("", SwingConstants.CENTER);

        setLayout(null);
        setBackground(new Color(127, 61, 61));

        endScore1.setBounds(30, 200, 155, 50);
        endScore2.setBounds(215, 200, 155, 50);

        endScore1.setFont(new Font("Serif", Font.PLAIN, 25));
        endScore2.setFont(new Font("Serif", Font.PLAIN, 25));

        add(endScore1);
        add(endScore2);

        setLocation(600, 90);
        setVisible(true);
        setSize(400, 500);

    }

    public void setScoreLabel(Score score){
        System.out.println(score.getYourScore() + " " + score.getOpponentScore());
        endScore1.setText("P1 score: "+ score.getYourScore());
        endScore2.setText("P2 score: "+ score.getOpponentScore());
    }

}