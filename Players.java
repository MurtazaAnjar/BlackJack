import java.util.ArrayList;

public class Players {
    public static Deck deckOfCards;
    private ArrayList<Card> hand;

    private String name;
    private static double balance = 1000;


    Players(String name) {
        this.name = name;
    }

    public int getTotal(){
        int sum = 0;
        for(Card n: hand) {
            if (!n.getIsHidden())
                sum += n.getVal();
        }
        return sum;
    }

    public ArrayList<Card> getHand() {return hand;}
    public void resetHand(){
        hand = new ArrayList<>();
    }

    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}


    public void createDeck(){deckOfCards = new Deck(true);}

    public void hit(boolean faceDown){hand.add(deckOfCards.deal(faceDown));}

    public boolean softHand(){
        for (Card n: hand){
            if(n.getVal()==11 && getTotal()>21)
                n.setAceVal(1);
        }
        if(getTotal()<21)
            return true;
        else
            return false;
    }

    public void printHand(){
        System.out.println(name+"'s hand is: ");
        for(Card val: hand) {
            val.printCard();
            System.out.print(", ");
        }
        System.out.println();
        System.out.println("hand total: "+getTotal());
        System.out.println();
    }
}
