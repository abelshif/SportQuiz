package Server;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PlayersData {
    public final List<PlayersDAO> playerListOne= new ArrayList<>();
    public final List<PlayersDAO> playerListTwo=new ArrayList<>();
    //players list one
    PlayersDAO player1 = new PlayersDAO("John");
    PlayersDAO player2 = new PlayersDAO("Abel");
    PlayersDAO player3= new PlayersDAO("Xavi");
    PlayersDAO player4= new PlayersDAO("Mike");
    PlayersDAO player5 = new PlayersDAO("Bob");

    //players list two
    PlayersDAO player6 = new PlayersDAO("Leo");
    PlayersDAO player7 = new PlayersDAO("David");
    PlayersDAO player8 = new PlayersDAO("Maria");
    PlayersDAO player9 = new PlayersDAO("Chris");
    PlayersDAO player10 = new PlayersDAO("Joe");

    public PlayersData() {
        playerListOne.add(player1);
        playerListOne.add(player2);
        playerListOne.add(player3);
        playerListOne.add(player4);
        playerListOne.add(player5);

        playerListTwo.add(player6);
        playerListTwo.add(player7);
        playerListTwo.add(player8);
        playerListTwo.add(player9);
        playerListTwo.add(player10);
    }
    public PlayersDAO getRandomPlayer1() {
        // Random random = new Random();
        int randomPlayer = (int) (Math.random() * 5);
        return playerListOne.get(randomPlayer);
    }

    public PlayersDAO getRandomPlayer2() {
        //Random random = new Random();
        int randomPlayer = (int) (Math.random() * 5);
        return playerListTwo.get(randomPlayer);
    }
    public static void main(String[] args) {
        PlayersData pDAO=new PlayersData();

    }
}
