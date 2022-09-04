package SharperTest;

import Cardsharpers.FunctionalClasses.Utils;
import Cardsharpers.Players.Cardsharper;
import Cardsharpers.Players.HonestPlayer;

import static org.junit.jupiter.api.Assertions.*;

import Cardsharpers.FunctionalClasses.Table;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Tests {
    @Test
    void testing() {
        HonestPlayer honest = new HonestPlayer(0);
        honest.takeTheCard(15);
        assertEquals(15, honest.getScore());
        HonestPlayer[] array = new HonestPlayer[1];
        array[0] = honest;
        Cardsharper sharper = new Cardsharper(array, 0);
        sharper.takeTheCard(15);
        assertEquals(15, sharper.getScore());
        sharper.takeTheCard(honest.stealTheScore(8));
        assertEquals(23, sharper.getScore());
        sharper.takeTheCard(honest.stealTheScore(30));
        assertEquals(30, sharper.getScore());
        assertEquals("Honest player 1", honest.getTheName());
        assertEquals("Sharper 1", sharper.getTheName());
    }

    @Test
    void desk() {
        assertEquals(true, Table.giveCard() < 11);
    }

    @Test
    void utilsCheckCreate() throws InterruptedException {
        HonestPlayer[] honest = new HonestPlayer[2];
        Cardsharper[] sharper = new Cardsharper[2];
        Utils.createArrays(honest, sharper);
        assertEquals("Sharper 1", sharper[0].getTheName());
        Utils.stop(honest, sharper);
        assertEquals(false, sharper[0].isInterrupted());
    }

    @Test
    void utilsCheckMax() {
        HonestPlayer[] honest = new HonestPlayer[2];
        Cardsharper[] sharper = new Cardsharper[2];
        for (int i = 0; i < 2; ++i) {
            honest[i] = new HonestPlayer(i);
            sharper[i] = new Cardsharper(honest, i);
        }
        honest[0].takeTheCard(15);
        honest[1].takeTheCard(15);
        ArrayList<String> output = new ArrayList<String>();
        int max = Utils.max(honest, sharper, output);
        assertEquals(15, max);
        assertEquals(2, output.size());
        sharper[0].takeTheCard(22);
        sharper[1].takeTheCard(22);
        max = Utils.max(honest, sharper, output);
        assertEquals(22, max);
    }

}
