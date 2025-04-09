import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private static ArrayList<Card> deck = new ArrayList<>();
    public Deck(boolean createNew){
        if (createNew) {
            while(!deck.isEmpty())
                deck.remove(0);
            for (int i = 1; i < 5; i++) {
                for (int j = 2; j < 15; j++)
                    deck.add(new Card(i,j, false));
            }
        }
    }

    public int getSize(){return deck.size();}

    public void shuffle(){
        for (int i = 0; i < 8; i++) {
            Collections.shuffle(deck);
        }
    }

    public Card deal(boolean faceDown){
        shuffle();
        Card give = deck.get(0);
        deck.remove(0);
        give.setHidden(faceDown);
        return give;
    }
}
