package Cardsharpers;

import Cardsharpers.FunctionalClasses.Utils;
import Cardsharpers.Players.Cardsharper;
import Cardsharpers.Players.HonestPlayer;

import static java.lang.Thread.sleep;


public class Main {

    public static void main(String[] args) throws Exception {
        Boolean checker = false;
        while (!checker) {
            Integer[] sizes = new Integer[2];
            Utils.read(sizes);
            HonestPlayer[] honestPlayers = new HonestPlayer[sizes[0]];
            Cardsharper[] cardsharpers = new Cardsharper[sizes[1]];
            Utils.createArrays(honestPlayers, cardsharpers);
            sleep(1000 * 10);
            Utils.stop(honestPlayers, cardsharpers);
            Utils.maximum(honestPlayers, cardsharpers);
            checker = Utils.goOn();
        }
        System.out.println("See you next time!");
    }
}
