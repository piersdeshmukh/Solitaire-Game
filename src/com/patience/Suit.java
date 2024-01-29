package com.patience;

public enum Suit {
    DIAMOND (Colours.RED.getColour() + "♦", Colours.RED),
    HEART (Colours.RED.getColour() + "♥︎", Colours.RED),
    CLUB (Colours.BLACK.getColour() + "♣︎", Colours.BLACK),
    SPADE (Colours.BLACK.getColour() + "♠︎", Colours.BLACK);

    private String symbol;
    private Colours colour;

    Suit (String symbol, Colours colour) {
        this.symbol = symbol;
        this.colour = colour;
    }

    public Colours getColour () {
        return colour;
    }

    public String toString () {
        return symbol;
    }
}