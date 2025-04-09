public class Card {
    private int suite;//1-4
    private int faceVal;//2-13
    private int val;//1-11
    private boolean faceDown;

    public Card(int suite, int fundamentalVal, boolean isHidden){
        this.faceDown = isHidden;
        this.suite = suite;
        this.faceVal = fundamentalVal;
        if(faceVal >9&&fundamentalVal<14)
            val = 10;
        else if(faceVal ==14)
            val = 11;
        else
            val = faceVal;
    }

    public void setAceVal(int val){this.val = val;}
    public void setHidden(boolean isHidden){this.faceDown = isHidden;}

    public int getVal(){return val;}
    public boolean getIsHidden(){return faceDown;}

    public void printCard(){//change to return toString?
        String[] suites = {"Spade", "Diamond", "Club", "Heart"};
        String[] blackJackValues = {"Ace","Two", "Three", "Four", "Five","Six", "Seven", "Eight", "Nine", "Ten","Ace"};
        String[] fundamentalValues = {"Ace","Two", "Three", "Four", "Five","Six", "Seven", "Eight", "Nine", "Ten","Jack","Queen","King", "Ace"};
        if(faceDown){
            System.out.print("Card is Face Down");
        }else {
            for (int i = 1; i < suites.length + 1; i++) {
                if (suite == i) {
                    for (int j = 1; j < fundamentalValues.length + 1; j++) {
                        if (faceVal == j)
                            System.out.print(fundamentalValues[j - 1] + " (val=" + getVal() + ") of " + suites[i - 1] + "s");
                    }
                }
            }
        }
    }
}
