import java.util.Scanner;

public class BlackJackGame {

    private static Players user = new Players("Player1");
    private static Players dealer = new Players("Dealer");
    private Scanner scan = new Scanner(System.in);
    private String response;
    private double pool;

    public BlackJackGame(){
        if(user.deckOfCards == null || user.deckOfCards.getSize()<10 )
            user.createDeck();
        user.resetHand();
        dealer.resetHand();
        System.out.println("Your Balance "+ user.getBalance()+"$\nPlace a bet (2-500)");
        pool = scan.nextDouble();
        user.setBalance(user.getBalance()-pool);

        user.hit(false);
        dealer.hit(false);
        user.hit(false);
        dealer.hit(true);
        if (dealer.getHand().get(0).getVal() > 9)
            dealer.getHand().get(1).setHidden(false);
        thePlay();
    }

    public void dealerPlay(){
        dealer.getHand().get(1).setHidden(false);
        while (dealer.softHand() && dealer.getTotal()<17){
            dealer.hit(false);
            dealer.printHand();
        }
        findWinner();
    }

    public void thePlay(){
        Boolean aBoolean = true;
        while(user.softHand() && aBoolean) {
            System.out.println("Deck size: " + user.deckOfCards.getSize());
            dealer.printHand();
            user.printHand();
            System.out.println("Hit or Stand?");
            response = scan.next();
            if (response.equalsIgnoreCase("Hit"))
                user.hit(false);
            else
                aBoolean = false;
        }
        if (aBoolean)
            user.printHand();
        if(user.getTotal()>21)
            findWinner();
        else
            dealerPlay();
    }


    public void findWinner(){
        System.out.println("Deck size: "+user.deckOfCards.getSize());
        dealer.printHand();
        user.printHand();

        if(user.getTotal()>21 || dealer.getTotal()>21) {
            if (dealer.getTotal() > 21) {
                user.setBalance(user.getBalance()+(pool*2));
                System.out.println("Dealer Busts!");
            } else if (user.getTotal() > 21) {
                pool=0;
                System.out.println("User Busts!");
            }
        }else if(user.getTotal()==21 || dealer.getTotal()==21) {
            if ((user.getTotal() == 21) && (dealer.getTotal() == 21)) {
                user.setBalance(user.getBalance() + pool);
                pool = 0;
                System.out.println("Double Blackjacks!!");
            } else if (dealer.getTotal() == 21) {
                pool = 0;
                System.out.println("Dealer Blackjack!!");
            } else if (user.getTotal() == 21) {
                user.setBalance(user.getBalance() + (pool * 3));
                System.out.println("User BlackJack!!");
            }
        }else{
            if(user.getTotal()==dealer.getTotal()){
                user.setBalance(user.getBalance()+(pool));
                System.out.println("Tie!!");
            }
            else if(user.getTotal()>dealer.getTotal()){
                user.setBalance(user.getBalance()+(pool*2));
                System.out.println("User Wins with greater total!!");
            }else{
                pool = 0;
                System.out.println("Dealer Wins with greater total!!");
            }
        }
        System.out.println("Balance = "+user.getBalance());
        System.out.println("Play again(y/n)?");
        response = scan.next();
        if(response.equals("y"))
            new BlackJackGame();
    }
}
