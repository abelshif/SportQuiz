package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Philip Zamayeri
 * Date: 2020-11-25
 * Time: 14:26
 * Project: SportQuiz
 * Copyright: MIT
 */
public class ClientHandler extends Thread{
    Socket clientSocket;
    DAO questionsDatabase;
    ClientHandler opponent;

    public ClientHandler(Socket clientSocket, DAO questionsDatabase) {
        this.clientSocket = clientSocket;
        this.questionsDatabase = questionsDatabase;
    }

    public void setOpponent(ClientHandler opponent) {
        this.opponent = opponent;
    }

    public ClientHandler getOpponent() {
        return opponent;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());

            Question question = (Question) questionsDatabase.handleQuestion(null);

            //Skickar fråga till Clienten
            writer.writeObject("Välj catagorie");

            Object input;
            while ((input = reader.readObject()) != null) {
                System.out.println("Get message " + input);

                if(input.equals("Geografi")){
                    question = questionsDatabase.g1;
                    writer.writeObject(question);
                }
                else if(input.equals("Nöje")){
                    question = questionsDatabase.p1;
                    writer.writeObject(question);
                }
                else if(input.equals("Sport")){
                    question = questionsDatabase.s1;
                    writer.writeObject(question);
                }
                else if(input.equals("Matematik")){
                    question = questionsDatabase.m1;
                    writer.writeObject(question);
                }
                else if (input.equals(question.getAnswer())){
                    writer.writeObject("Svaret är korrekt! " + input);
                    writer.writeObject("End of game");
                } else {
                    writer.writeObject("Svaret är fel! " + input);
                    writer.writeObject("End of game");
                }

            }
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}