package com.patience;

import java.util.*;

public class View {

    private final static String BLANK = "   ";

    Command command;

    View (Arena arena,Points point_tally) {
        presentInfo(point_tally);
        presentInfo(arena);
    }

    public void presentInfo(Points point_tally) {
        System.out.println("\nPoints:" + point_tally.getPoints() +"                                              No. of Moves:" + point_tally.getNumberOfTurns());
        System.out.println("---------------------------------------------------------------------");
    }

    public void presentInfo(Arena arena) {
        System.out.println(" P     1     2     3     4     5     6      7    D     H     C     S\n---------------------------------------------------------------------");
        int numberRows = Math.max(arena.longestLaneSize(),2);		// only longest lane's size number of rows are to be printed
        for (int row=0; row<numberRows; row++) {
            if (row==0) {                                           // for first row, first element
                Pack pack = arena.getPack();
                if (pack.isEmpty()) {
                    System.out.print(BLANK);
                } else {
                    System.out.print(pack.peek());				//size is 24 initially because we had 1+2+3+4+5+6+7 time pack.pop() before in arena's ctor & symbol of peeked card will always be a emoji as by def all have FlipUp as false unless explicitly negated
                }
            } else if (row==1) {                                // for 2nd row first element
                Stack<Card> pile = arena.getPile();
                if (pile.isEmpty()) {
                    System.out.print(BLANK);
                } else {
                    System.out.print(pile.peek());
                }
            } else {
                System.out.print(BLANK);
            }
            System.out.print(BLANK);										// for spacing
            for (int laneIndex = 0; laneIndex< Arena.TOTAL_LANES; laneIndex++) {
                List<Card> lane = arena.getLane(laneIndex);					// fetching each lane
                if (row > lane.size()-1) {									// if lane is completed skip it in the remaining rows
                    System.out.print(BLANK);
                } else {
                    System.out.print(lane.get(row));
                }
                System.out.print(BLANK);						           // spacing to separate each lane column
            }
            if (row==0) {
                for (int suitIndex = 0; suitIndex< Arena.TOTAL_SUITS; suitIndex++) {
                    Stack<Card> suit = arena.getSuit(suitIndex);
                    if (suit.isEmpty()) {
                        System.out.print(BLANK);
                    } else {
                        System.out.print(suit.peek());
                    }
                    System.out.print(BLANK);					// spacing to separate each suit column
                }
            }
            System.out.println();								// spacing between rows
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public void displayCommandInfeasible () {
        System.err.println("This play cannot be made. Please try another.");
    }

    public Command getInputCommand () {
        boolean commandReceived = false;
        while (!commandReceived)
        {
            System.out.print("Your turn: ");
            Scanner in = new Scanner(System.in);
            String input = in.next();
            if (Command.isValid(input.toUpperCase().trim())) {
                command = new Command(input.toUpperCase().trim());
                commandReceived = true;
            } else {
                System.err.println("Invalid command. Please Try again.");
            }
        }
        return command;
    }

    public void displayGameOver () {
        System.out.println("Game over!");
    }

    public void displayQuit () {
        System.out.println(" You Quited!");
    }

}
