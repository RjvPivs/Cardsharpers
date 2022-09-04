package Cardsharpers.Players;

import Cardsharpers.FunctionalClasses.Table;

import java.util.Random;

/**
 * A class of a sharper player.
 */
public class Cardsharper extends Thread {
    private int score;
    private HonestPlayer[] honestPlayers;
    private int index;

    public Cardsharper(HonestPlayer[] honestPlayers, int index) {
        this.index = ++index;
        this.honestPlayers = honestPlayers;
        score = 0;
    }

    /**
     * Returns the current score.
     *
     * @return The score.
     */
    public int getScore() {
        return score;
    }

    public void run() {
        while (!isInterrupted()) {
            Random random = new Random();
            int sleep;
            if (random.nextInt(10) < 4) {
                int honest = random.nextInt(honestPlayers.length);
                takeTheCard(honestPlayers[honest].stealTheScore(random.nextInt(9)));
                sleep = 180 + random.nextInt(121);
            } else {
                takeTheCard(Table.giveCard());
                sleep = 100 + random.nextInt(101);
            }
            try {
                sleep(sleep);
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * Takes the card.
     *
     * @param score The new card to be added.
     */
    public void takeTheCard(int score) {
        this.score += score;
    }

    /**
     * Returns the name of a player.
     *
     * @return The name.
     */
    public String getTheName() {
        return "Sharper " + index;
    }

}

