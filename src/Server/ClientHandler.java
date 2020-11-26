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
    ServerSideGame game;

    ObjectOutputStream oos;
    ObjectInputStream ois;

    public ClientHandler(Socket clientSocket, DAO questionsDatabase, ServerSideGame game) {
        this.clientSocket = clientSocket;
        this.questionsDatabase = questionsDatabase;
        this.game = game;
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
            this.oos = writer;
            this.ois = reader;


            Object input;
            while ((input = reader.readObject()) != null) {
                game.game(input);
                System.out.println("Get message " + input);

            }
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(Object obj){
        try{
            oos.writeObject(obj);
            oos.flush();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
