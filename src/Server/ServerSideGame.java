package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Philip Zamayeri
 * Date: 2020-11-25
 * Time: 14:27
 * Project: SportQuiz
 * Copyright: MIT
 */
public class ServerSideGame extends Thread{
    ClientHandler currentPlayer;
    ClientHandler player1;
    ClientHandler player2;

    DAO dao;
    Question question;


    public ServerSideGame(DAO dao){
        this.dao = dao;
    }

    public void game(Object input){

        player1.sendMessage("Välj kategori");
        player2.sendMessage("Avvakta medans player1 väljer kategori");

        if(((String)input).equals("Geografi")){
            question = dao.g1;
            dao.handleQuestion((String)input);
            player1.sendMessage(question);
        }
        else if(((String)input).equals("Nöje")){
            question = dao.p1;
            player1.sendMessage(question);
        }
        else if(((String)input).equals("Sport")){
            question = dao.s1;
            player1.sendMessage(question);
        }
        else if(((String)input).equals("Matematik")){
            question = dao.m1;
            player1.sendMessage(question);
        }
        else if (((String)input).equals(question.getAnswer())){
            player1.sendMessage("Svaret är korrekt! " + input);
            //player2.sendMessage("Svaret är korrekt! " + input);
            player1.sendMessage("End of game");
            //player2.sendMessage("End of game");
        } else {
            player1.sendMessage("Svaret är fel! " + input);
           // player2.sendMessage("Svaret är fel! " + input);
            player1.sendMessage("End of game");
            //player2.sendMessage("End of game");
        }

    }

    public void setPlayer1(ClientHandler player1) {
        this.player1 = player1;
    }

    public void setPlayer2(ClientHandler player2) {
        this.player2 = player2;
    }
}