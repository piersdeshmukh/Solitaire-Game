package com.patience;

public class Points {

    private static final int CARD_LANE_TO_SUIT = 20;
    private static final int CARD_PILE_TO_SUIT = 10;
    private static final int CARD_LANE_TO_LANE  = 5;
    private int points;
    private int numberOfTurns;

    Points() {
        numberOfTurns = 0;
        points = 0;
    }
    public void actionTaken (Command command) {
        numberOfTurns++;
        if (command.isSourcePile() && command.isDestinationSuit())
        {
            points += CARD_PILE_TO_SUIT;
        }
        else if (command.isSourceLane() && command.isDestinationLane())
        {
            points += CARD_LANE_TO_LANE;
        }
        else
        {
            points += CARD_LANE_TO_SUIT;
        }
    }
    public void actionTaken () {numberOfTurns++;} 						// if action is just a draw from pile
    public int getPoints() {
        return points;
    }
    public int getNumberOfTurns () {
        return numberOfTurns;
    }
}