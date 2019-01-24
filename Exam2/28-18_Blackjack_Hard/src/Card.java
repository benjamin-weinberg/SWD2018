/**
 * Card class holds the values used for the cards
 */
public class Card{
    private int value;
    private final char face;
    private final char suit;

    /**
     * Constructor for a card
     * @param suit the suit of the card
     * @param value the numeric value of the card
     */
    public Card(char suit, int value){
        this.value = value;
        this.suit = suit;

        if (value == 1) this.face = 'A';
        else if (value == 2) this.face = '2';
        else if (value == 3) this.face = '3';
        else if (value == 4) this.face = '4';
        else if (value == 5) this.face = '5';
        else if (value == 6) this.face = '6';
        else if (value == 7) this.face = '7';
        else if (value == 8) this.face = '8';
        else if (value == 9) this.face = '9';
        else if (value == 10) this.face = 'T';
        else if (value == 11) this.face = 'J';
        else if (value == 12) this.face = 'Q';
        else if (value == 13) this.face = 'K';
        else face = ' ';

        if (value >= 10) this.value = 10;
    }

    public int getValue() { return value; }
    public char getSuit() { return suit; }
    public char getFace() { return face; }
    public String toString() {return face + " of " + suit;}
}