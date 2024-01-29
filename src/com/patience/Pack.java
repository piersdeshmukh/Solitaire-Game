package com.patience;

import java.util.*;

public class Pack extends Stack<Card> {
    Pack() {
        super();
        Suit[] suits = Suit.values();
        FaceValue[] faceValues = FaceValue.values();
        for(int i = 0; i < suits.length; i++)
        {
            for(int j = 0; j < faceValues.length; j++)
            {
                super.add(new Card(suits[i], faceValues[j], false));		//adding cards to the pack
            }
        }
        Collections.shuffle(this);											//shuffling the pack after being added serially above
    }
}
