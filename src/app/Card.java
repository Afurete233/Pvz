package app;

public class Card {

    private int[] Card_data;
    private int Card_MAX;

    public int getCard_MAX() {
        return Card_MAX;
    }

    public void setCard_MAX(int card_MAX) {
        Card_MAX = card_MAX;
    }

    public int[] getCard_data() {
        return Card_data;
    }

    public void setCard_data(int[] card_data) {
        Card_data = card_data;
    }

    @Override
    public String toString() {
        String data = "";
        for (int i = 0; i < Card_data.length; i++) {
            data+=Card_data[i];
            if(i!=Card_data.length-1)
            data+="/";
        }
        return data;
    }


}
