package Klient.gui;

import Server.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Philip Zamayeri
 * Date: 2020-11-25
 * Time: 14:26
 * Project: SportQuiz
 * Copyright: MIT
 */
public class QuestionPanel extends JPanel {

    Font questionFont = new Font("Tahoma", Font.BOLD, 15);
    Font alternativesFont = new Font("Tahoma", Font.PLAIN, 25);

    JLabel label;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;

    JButton lastClickedButton;

    private ObjectOutputStream oos;

    public QuestionPanel(Question newQuestion) {
        setLayout(new BorderLayout());
        setBackground(new Color(127, 61, 61));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        add(buttonPanel);
        buttonPanel.setBackground(new Color(127, 61, 61));

        label= new JLabel(newQuestion.getQuestion(), SwingConstants.CENTER);

        b1 = new JButton(newQuestion.getAlternatives().get(0));
        b2 = new JButton(newQuestion.getAlternatives().get(1));
        b3 = new JButton(newQuestion.getAlternatives().get(2));
        b4 = new JButton(newQuestion.getAlternatives().get(3));

        b1.addActionListener(new InternalAnswerListener());
        b2.addActionListener(new InternalAnswerListener());
        b3.addActionListener(new InternalAnswerListener());
        b4.addActionListener(new InternalAnswerListener());


        b1.setBounds(30, 200, 155, 50);
        b2.setBounds(215, 200, 155, 50);
        b3.setBounds(30, 250, 155, 50);
        b4.setBounds(215, 250, 155, 50);

        //label.setLocation(50, 100);


        b1.setFont(alternativesFont);
        b2.setFont(alternativesFont);
        b3.setFont(alternativesFont);
        b4.setFont(alternativesFont);

        add(label, BorderLayout.NORTH);
        label.setFont(questionFont);


        buttonPanel.add(b1);
        buttonPanel.add(b2);
        buttonPanel.add(b3);
        buttonPanel.add(b4);
    }

    public void addQuestionToPanel(Question question) {
        label.setText(question.getQuestion());
        label.revalidate();
        label.repaint();
        b1.setText(question.getAlternatives().get(0));
        b2.setText(question.getAlternatives().get(1));
        b3.setText(question.getAlternatives().get(2));
        b4.setText(question.getAlternatives().get(3));
        System.out.println(question.getQuestion());
        validate();
    }

    public void setClickedButtonColor(Color color) {
        if(lastClickedButton != null) {
            lastClickedButton.setBackground(color);
            lastClickedButton.revalidate();
            lastClickedButton.repaint();
        }
    }

    public void setObjectOutputStream(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public class InternalAnswerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JButton) {
                JButton button = (JButton)e.getSource();
                lastClickedButton = button;
                System.out.println(button.getText());
                try {
                    if(oos != null)
                        oos.writeObject(button.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
