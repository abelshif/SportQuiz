package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Philip Zamayeri
 * Date: 2020-11-25
 * Time: 14:27
 * Project: SportQuiz
 * Copyright: MIT
 */
public class Server {
    DAO questions = new DAO();

    public Server() throws IOException {
        int portnr = 57777;
        ServerSocket socket = new ServerSocket(portnr);

        try {
            while (true) {
                ClientHandler player1
                        = new ClientHandler(socket.accept(), questions);
                ClientHandler player2
                        = new ClientHandler(socket.accept(), questions);

                ServerSideGame game = new ServerSideGame(player1,player2);

                player1.setOpponent(player2);
                player2.setOpponent(player1);
                game.currentPlayer = player1;
                player1.start();
                player2.start();
            }
        } finally {
            socket.close();
        }
    }


    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }
}