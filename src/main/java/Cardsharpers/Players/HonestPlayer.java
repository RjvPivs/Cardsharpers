package Cardsharpers.Players;

import Cardsharpers.FunctionalClasses.Table;

import java.util.Random;

/**
 * An honest player class.
 */
public class HonestPlayer extends Thread {
    private int score;
    private int index;

    public HonestPlayer(int index) {
        score = 0;
        this.index = ++index;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            takeTheCard(Table.giveCard());
            try {
                Random random = new Random();
                sleep(100 + random.nextInt(101));
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * Returns the score.
     *
     * @return The score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds a new card.
     *
     * @param score A new card.
     */
    synchronized public void takeTheCard(int score) {
        this.score += score;
    }

    /**
     * Gives an opportunity for a sharper to steal some score points.
     *
     * @param score A stealing points number.
     * @return Sealing points number.
     */
    synchronized public int stealTheScore(int score) {
        if (this.score < score) {
            int temporary = this.score;
            this.takeTheCard(-score);
            return temporary;
        } else {
            this.takeTheCard(-score);
            return score;
        }
    }

    /**
     * Returns the name of a player.
     *
     * @return The name.
     */
    public String getTheName() {
        return "Honest player " + index;
    }
}

