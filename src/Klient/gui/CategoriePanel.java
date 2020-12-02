package Klient.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Philip Zamayeri
 * Date: 2020-11-25
 * Time: 14:25
 * Project: SportQuiz
 * Copyright: MIT
 */
public class CategoriePanel extends JPanel {

    private ObjectOutputStream oos;

    JButton categoryButton1 = new JButton("Geografi");
    JButton categoryButton2 = new JButton("Sport");
    JButton categoryButton3 = new JButton("Nöje");
    JButton categoryButton4 = new JButton("Matematik");

    public CategoriePanel() {

        categoryButton1.addActionListener(new InternalAnswerListener());
        categoryButton2.addActionListener(new InternalAnswerListener());
        categoryButton3.addActionListener(new InternalAnswerListener());
        categoryButton4.addActionListener(new InternalAnswerListener());

        setBackground(new Color(127, 61, 61));
        setLayout(null);

        add(categoryButton1);
        add(categoryButton2);
        add(categoryButton3);
        add(categoryButton4);

        categoryButton1.setBounds(50, 150, 300, 50);
        categoryButton2.setBounds(50, 210, 300, 50);
        categoryButton3.setBounds(50, 270, 300, 50);
        categoryButton4.setBounds(50, 330, 300, 50);
    }

    public void setObjectOutputStream(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public class InternalAnswerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JButton) {
                JButton button = (JButton)e.getSource();
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