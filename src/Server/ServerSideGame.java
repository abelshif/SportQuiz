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
public class ServerSideGame {
    ClientHandler currentPlayer;
    ClientHandler player1;
    ClientHandler player2;

    public ServerSideGame(ClientHandler player1, ClientHandler player2){
        this.player1 = player1;
        this.player2 = player2;
    }
}
