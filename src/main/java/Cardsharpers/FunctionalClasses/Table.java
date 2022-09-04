package Cardsharpers.FunctionalClasses;

import java.util.Random;

/**
 * Class of a gaming table.
 */
public class Table {
    /**
     * Creates a new card.
     *
     * @return A new card.
     */
    synchronized public static int giveCard() {
        Random random = new Random();
        return 1 + random.nextInt(10);
    }
}

