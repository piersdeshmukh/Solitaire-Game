package com.patience;

import java.util.*;

public class Arena {

    Card card;
    private Pack pack;
    private Stack<Card> pile;
    private List<Stack<Card>> lanes,suits;
    public static final int TOTAL_SUITS = Suit.values().length;
    public static final int TOTAL_LANES = 7;

    Arena() {
        pile = new Stack<>();
        pack = new Pack();
        suits =  new ArrayList<>(TOTAL_SUITS);
        for (int i=0; i<TOTAL_SUITS; i++) {
            suits.add(new Stack<>());			// every element of the above created list is a stack to hold specific suit tyoe
        }
        lanes =  new ArrayList<>(TOTAL_LANES);
        for (int i=0; i<TOTAL_LANES; i++) {
            lanes.add(new Stack<>());		    // every element of the above created list is a stack to act as lane
            for (int j=0; j<i; j++) {
                lanes.get(i).push(pack.pop());
            }
            card = pack.pop();
            card.flipUp();						//each lane has to have 1 "last" card with faced up
            lanes.get(i).push(card);
        }
    }

    public boolean isMoveAllowed (Command command) {
        boolean isAllowed = false;
        if (command.isSourcePile() && command.isDestinationSuit()) {
            if (!pile.empty()) {
                Stack<Card> suit = suits.get(command.getDestinationIndex());
                if ((!suit.empty() && suit.peek().isNextInSuit(pile.peek())) || suit.empty()) {
                    isAllowed = true;
                }
            }
        } else if (command.isSourcePile() && command.isDestinationLane()) {
            if (!pile.empty()) {
                Stack<Card> lane = lanes.get(command.getDestinationIndex());
                if ((!lane.empty() && lane.peek().isNextInLane(pile.peek())) || lane.empty()) {
                    isAllowed = true;
                }
            }
        } else if (command.isSourceSuit() && command.isDestinationLane()) {
            Stack<Card> suit = suits.get(command.getSourceIndex());
            if (!suit.empty()) {
                Stack<Card> lane = lanes.get(command.getDestinationIndex());
                if ((!lane.empty() && lane.peek().isNextInLane(suit.peek())) || lane.empty()) {
                    isAllowed = true;
                }
            }
        } else if (command.isSourceLane() && command.isDestinationSuit()) {
            Stack<Card> lane = lanes.get(command.getSourceIndex());
            if (!lane.empty()) {
                Stack<Card> suit = suits.get(command.getDestinationIndex());
                if ((!suit.empty() && suit.peek().isNextInSuit(lane.peek())) || suit.empty()) {
                    isAllowed = true;
                }
            }
        }
        else {  // lane to lane
            List<Card> sourceLane = lanes.get(command.getSourceIndex());     // use List interface to the Stack
            if (sourceLane.size() >= command.getNoOfCardsToTransfer() &&
                    sourceLane.get(sourceLane.size()-command.getNoOfCardsToTransfer()).isFlippedUp()) //if lane has enough cards to move the reqd. no of cards from that lane & if all the cards to be moved are actually face up cards
            {
                Card card = sourceLane.get(sourceLane.size()-command.getNoOfCardsToTransfer());
                Stack<Card> destinationLane = lanes.get(command.getDestinationIndex());
                if ((!destinationLane.empty() && destinationLane.peek().isNextInLane(card)) || destinationLane.empty()) {
                    isAllowed = true;
                }
            }
        }
        return isAllowed;
    }
    public boolean isDrawAllowed () {
        return !pile.empty() || !pack.empty();
    }
    public void draw () {
        if (!pack.empty())
        {
            Card card = pack.pop();
            card.flipUp();
            pile.add(card);
        } else { 							// refilling the pack(once exhausted) with cards in the pile
            int noOfCards = pile.size();
            for (int i=0; i<noOfCards; i++) {
                Card card = pile.pop();
                card.flipDown();
                pack.add(card);
            }
        }
    }
    public void move (Command command) {
        if (command.isSourcePile() && command.isDestinationSuit())
        {
            suits.get(command.getDestinationIndex()).push(pile.pop());
        }
        else if (command.isSourcePile() && command.isDestinationLane())
        {
            lanes.get(command.getDestinationIndex()).push(pile.pop());
        }
        else if (command.isSourceSuit() && command.isDestinationLane())
        {
            Card card = suits.get(command.getSourceIndex()).pop();
            lanes.get(command.getDestinationIndex()).push(card);
        }
        else if (command.isSourceLane() && command.isDestinationSuit())
        {
            Stack<Card> lane = lanes.get(command.getSourceIndex());
            Card card = lane.pop();
            if (!lane.empty() && lane.peek().isFlippedDown())
            {
                lane.peek().flipUp();
            }
            suits.get(command.getDestinationIndex()).push(card);
        }
        else { 																	// when source and destination is lane itself
            List<Card> sourceLane = lanes.get(command.getSourceIndex());
            int indexToMove = sourceLane.size()-command.getNoOfCardsToTransfer();
            for (int i=0; i<command.getNoOfCardsToTransfer(); i++) {
                Card card = sourceLane.get(indexToMove);
                sourceLane.remove(indexToMove);
                lanes.get(command.getDestinationIndex()).push(card);
            }
            if (sourceLane.get(sourceLane.size()-1).isFlippedDown() && !sourceLane.isEmpty()) {
                sourceLane.get(sourceLane.size()-1).flipUp();
            }
        }
    }

    public boolean isGameOver () {
        for (int i = 0; i < suits.size(); i++) {
            Stack<Card> suit = suits.get(i);
            if (suit.size() != FaceValue.values().length) {
                return false;
            }
        }
        System.out.println("You win!"); 					// when all the suits contain all the facevalues
        return true;
    }
    public int longestLaneSize () {
        int longestLaneSize = 0;
        for(int i = 0; i < lanes.size(); i++) {
            Stack<Card> lane = lanes.get(i);
            if (lane.size() > longestLaneSize) {
                longestLaneSize = lane.size();
            }
        }
        return longestLaneSize;
    }
    public Pack getPack () {
        return pack;
    }
    public Stack<Card> getLane (int index) {
        return lanes.get(index);
    }
    public Stack<Card> getSuit (int index) {
        return suits.get(index);
    }
    public Stack<Card> getPile () {
        return pile;
    }
}
