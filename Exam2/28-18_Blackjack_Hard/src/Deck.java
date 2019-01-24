import java.util.Random;

/**
 * The Deck class provides basic functions you would expect of a deck.
 */
public class Deck {
    private final char[] suits = {'H','D','S','C'};
    private final int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    private final int numCards = 52;
    private int nextCard;
    private Card[] cards;

    /**
     * The Deck constructor will setup all of the cards and hold the deck
     */
    public Deck(){
        nextCard = -1;
        cards = new Card[numCards];

        int counter = 0;
        for (int i = 0; i < suits.length; i++){
            for (int j = 0; j < values.length; j++){
                cards[counter] = new Card(suits[i], values[j]);
                counter++;
            }
        }
    }

    /**
     * shuffles the deck in a sudo-random order
     */
    public void shuffle(){
        nextCard = -1;
        Random rand = new Random();
        for (int i=0;i<numCards*3;i++){
            int trade1 = rand.nextInt(numCards);
            int trade2 = rand.nextInt(numCards);
            Card temp =  cards[trade1];
            cards[trade1] = cards[trade2];
            cards[trade2] = temp;
        }

    }

    /**
     * print out the order of the deck (helper function)
     */
    public void printDeck(){
        System.out.println("\n=======================");
        for (int i = 0; i<numCards;i++){
            System.out.println(cards[i]);
        }
        System.out.println("=======================\n");
    }

    /**
     * Takes the next card from the top of the deck and returns it to the user
     * @return next card on the deck
     */
    public Card dealCard(){
        if(nextCard<numCards) nextCard++;
        System.out.println(cards[nextCard]);
        return cards[nextCard];
    }
}
