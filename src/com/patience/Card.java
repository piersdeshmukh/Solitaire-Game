package com.patience;

public class Card {

    private boolean isFlippedUp;
    private FaceValue value;
    private Suit suit;

    Card (Suit suit, FaceValue value, boolean flipUp) {
        this.suit = suit;
        this.value = value;
        this.isFlippedUp = flipUp;
    }

    private boolean isFromSameSuit  (Card card) {
        return suit == card.getSuit();
    }

    private boolean isLowerValue (Card card) {
        return this.value.ordinal()-1 == card.value.ordinal();
    }

    private boolean isHigherValue (Card card) {
        return this.value.ordinal()+1 == card.value.ordinal();
    }

    private boolean isOfSameColour (Card card) {
        return this.suit.getColour() == card.suit.getColour();
    }

    public boolean isFlippedUp() {
        return isFlippedUp;
    }

    public boolean isFlippedDown() {
        return !isFlippedUp;
    }

    public void flipUp() {
        isFlippedUp = true;
    }

    public void flipDown() {
        isFlippedUp = false;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isNextInLane (Card card) {
        return  this.isLowerValue(card) && !this.isOfSameColour(card);
    }

    public boolean isNextInSuit (Card card) {
        return this.isHigherValue(card) && this.isFromSameSuit(card);
    }

    public String toString() {
        if (!isFlippedUp) {
            return " \uD83C\uDCBE " + Colours.WHITE.getColour();
        }
        else {
            return suit.toString() + value.toString() + Colours.WHITE.getColour();		// to print face up cards
        }
    }
}