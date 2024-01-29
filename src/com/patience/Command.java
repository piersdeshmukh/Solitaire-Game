package com.patience;

public class Command {

    private CommandName name;
    private char source, destination;
    private String noOfCards;

    Command (String query) {
        if (query.equals("D")) {
            name = CommandName.DRAW;
        } else if (query.equals("Q")) {
            name = CommandName.QUIT;
        } else {
            name = CommandName.MOVE;
            source = query.charAt(0);
            destination = query.charAt(1);
            noOfCards = "";
            if (query.length()>2) {
                noOfCards = query.substring(2);					// by convention no of cards are provided at the end of the user input
            }
        }
    }

    public static boolean isValid (String query) {
        return query.matches("[P1-7DHCS][1-7DHCS][0-9]*") || query.equals("D") || query.equals("Q");
    }

    public CommandName getName() {
        return name;
    }

    public boolean isSourcePile () {
        return source == 'P';
    }

    public boolean isSourceLane () {
        return Character.toString(source).matches("[1-7]");
    }

    public boolean isDestinationLane () {
        return Character.toString(destination).matches("[1-7]");
    }

    public boolean isSourceSuit () {
        return Character.toString(source).matches("[DHCS]");
    }

    public boolean isDestinationSuit () {
        return Character.toString(destination).matches("[DHCS]");
    }
    public int getNoOfCardsToTransfer() {
        if (noOfCards.equals("") || noOfCards.equals("1")) {
            return 1;
        } else {
            return Integer.valueOf(noOfCards);
        }
    }

    private int mapSuitToIndex(char character) {						//assigning index to each suit column
        if (character == 'D') {
            return 0;
        } else if (character == 'H') {
            return 1;
        } else if (character == 'C') {
            return 2;
        } else if (character == 'S') {
            return 3;
        } else {
            return 0;
        }
    }

    public int getSourceIndex () {
        if (isSourceLane()) {
            return Character.getNumericValue(source) - 1;
        } else { 															// if source is a suit
            return mapSuitToIndex(source);
        }
    }
    public int getDestinationIndex () {
        if (isDestinationLane()) {
            return Character.getNumericValue(destination) - 1;
        } else { 															// if destination is a suit
            return mapSuitToIndex(destination);
        }
    }

}