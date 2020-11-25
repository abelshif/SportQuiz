package Klient;

import Server.DAO;
import Server.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Philip Zamayeri
 * Date: 2020-11-25
 * Time: 14:29
 * Project: SportQuiz
 * Copyright: MIT
 */
public class GUI extends JFrame {
    DAO dao = new DAO();

    public List<String> qToCliendHandler = new ArrayList<>();
    List<Question> category;

    JButton[] buttons = new JButton[]
            {new JButton("b1"), new JButton("b2"), new JButton("b3"), new JButton("b4")};
    JButton[] categoryButton = new JButton[]
            {new JButton("Geografi"), new JButton("Sport"), new JButton("Nöje"), new JButton("Matematik")};

    private int correctAnswers = 0;
    private int result = 0;
    private int index = 0;


    JFrame frame = new JFrame("QUIZ");

    JPanel mainPanel = new JPanel();
    JPanel gamePanel = new JPanel();
    JPanel categoriesPanel = new JPanel();


    JButton newGame = new JButton("Nytt spel");


    JLabel question;


    public GUI() {

        add(mainPanel);
        mainPanel.setSize(400, 500);
        mainPanel.setBackground(new Color(127, 61, 61));
        mainPanel.setLayout(null);

        newGame.setBounds(50, 150, 300, 50);

        newGame.setBackground(new Color(0x9252260E, true));
        newGame.setOpaque(true);

        newGame.addMouseListener(newGameListener);

        mainPanel.add(newGame);


        setVisible(true);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void startNewGame() {

        categoriesPanel.setVisible(false);
        add(gamePanel);


        gamePanel.add(buttons[0]);
        gamePanel.add(buttons[1]);
        gamePanel.add(buttons[2]);
        gamePanel.add(buttons[3]);
        gamePanel.add(question);


        question.setLocation(50, 100);
        question.setSize(300, 50);
        question.setVisible(true);

        buttons[0].setBounds(30, 200, 155, 50);
        buttons[1].setBounds(215, 200, 155, 50);
        buttons[2].setBounds(30, 250, 155, 50);
        buttons[3].setBounds(215, 250, 155, 50);

        gamePanel.setBackground(new Color(127, 61, 61));
        gamePanel.setLayout(null);

        setVisible(true);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void chooseCategory() {
        for (JButton button : categoryButton) {
            button.addMouseListener(chooseCategoryListener);
            categoriesPanel.add(button);
        }

        mainPanel.setVisible(false);
        add(categoriesPanel);

        categoriesPanel.setBackground(new Color(127, 61, 61));
        categoriesPanel.setLayout(null);

        categoryButton[0].setBounds(50, 150, 300, 50);
        categoryButton[1].setBounds(50, 210, 300, 50);
        categoryButton[2].setBounds(50, 270, 300, 50);
        categoryButton[3].setBounds(50, 330, 300, 50);

        setVisible(true);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void whichCategory(MouseEvent e) {
        if (categoryButton[0] == e.getSource()) {
            questionSetup(dao.geopgraphy);
        } else if (categoryButton[1] == e.getSource()) {
            questionSetup(dao.sport);
        } else if (categoryButton[2] == e.getSource()) {
            questionSetup(dao.pleasure);
        } else {
            questionSetup(dao.mathematics);
        }
    }


    public void questionSetup(List<Question> category) {
        Collections.shuffle(category);
        this.category = category;
        int counter = 0;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(category.get(0).getAlternatives().get(counter++));
            buttons[i].addMouseListener(chooseAlternativesListener);
        }
        question = new JLabel(category.get(0).getQuestion(), SwingConstants.CENTER);
        qToCliendHandler.add(category.get(0).getQuestion());
        startNewGame();

    }

    public void nextQuestion() {
        int counter = 0;
        if (index == 1) {
            for (JButton button : buttons) {
                button.setBackground(Color.white);
                button.setText(category.get(1).getAlternatives().get(counter++));
            }
            question.setText(category.get(1).getQuestion());
            qToCliendHandler.add(category.get(1).getQuestion());
        } else if (index == 2) {
            chooseCategory();
            index = 0;
            System.out.println(correctAnswers);
        } else if (result == 4) {
            System.out.println("show result");
        }
    }


    public void checkAnswer(JButton button) {
        if (button.getText().trim().equalsIgnoreCase(dao.sportAnswer()) ||
                button.getText().trim().equalsIgnoreCase(dao.mathAnswer()) ||
                button.getText().trim().equalsIgnoreCase(dao.geoAnswer()) ||
                button.getText().trim().equalsIgnoreCase(dao.pleasureAnswer()) ||
                button.getText().trim().equalsIgnoreCase(dao.sportAnswer2()) ||
                button.getText().trim().equalsIgnoreCase(dao.mathAnswer2()) ||
                button.getText().trim().equalsIgnoreCase(dao.geoAnswer2()) ||
                button.getText().trim().equalsIgnoreCase(dao.pleasureAnswer2())) {

            System.out.println("Korrekt!");
            button.setBackground(Color.GREEN);
            button.setOpaque(true);
            button.setBorderPainted(true);
            correctAnswers++;
            try {
                Thread.sleep(2000);
                button.setBackground(Color.white);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        } else {
            System.out.println("Fel svar!");
            button.setBackground(Color.red);
            button.setOpaque(true);
            button.setBorderPainted(true);
            try {
                Thread.sleep(2000);
                button.setBackground(Color.white);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        index++;
        result++;

        nextQuestion();

    }


    MouseAdapter chooseAlternativesListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            checkAnswer((JButton) e.getSource());
        }
    };

    MouseAdapter chooseCategoryListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            whichCategory(e);
        }
    };

    MouseAdapter newGameListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            chooseCategory();
        }
    };


    public static void main(String[] args) {
        new GUI();
    }
}

