package Server;

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

    int index = 0;

    DAO dao;
    Question question;


    public ServerSideGame(DAO dao){
        this.dao = dao;
    }
     // clienthandler som parameter för att veta vilken spelare som har skickat objectet(input)
    public void game(Object input, ClientHandler player){

        //player1.sendMessage("Välj kategori");
        //player2.sendMessage("Avvakta medans player1 väljer kategori");

        if(((String)input).equals("Geografi")){
            question = dao.g1;
            player1.sendMessage(question);
            player2.sendMessage(question);//skickar frågan till player två också
        }
        else if(((String)input).equals("Nöje")){
            question = dao.p1;
            player1.sendMessage(question);
            player2.sendMessage(question);
        }
        else if(((String)input).equals("Sport")){
            question = dao.s1;
            player1.sendMessage(question);
            player2.sendMessage(question);
        }
        else if(((String)input).equals("Matematik")){
            question = dao.m1;
            player1.sendMessage(question);
            player2.sendMessage(question);
        }
        //kollar vilken spelare som skickade svaret.
        else if (((String)input).equals(question.getAnswer())){
            if(player == player1) {
                player1.sendMessage("Svaret är korrekt! " + input);
                player1.sendMessage("End of game");
            } else {
                player2.sendMessage("Svaret är korrekt! " + input);
                player2.sendMessage("End of game");
            }
        } else {
            if(player == player1) {
                player1.sendMessage("Svaret är fel! " + input);
                player1.sendMessage("End of game");
            } else {
                player2.sendMessage("Svaret är fel! " + input);
                player2.sendMessage("End of game");
            }
        }
    }

    public void setPlayer1(ClientHandler player1) {
        this.player1 = player1;
    }

    public void setPlayer2(ClientHandler player2) {
        this.player2 = player2;
    }
// add setReady metod för att skicka första medelandet när de är redo.
    public void setReady(ClientHandler player) {
        if(player == player1) {
            player1.sendMessage("Välj kategori");
        } else {
            player2.sendMessage("Avvakta medans player1 väljer kategori");
        }
    }
}