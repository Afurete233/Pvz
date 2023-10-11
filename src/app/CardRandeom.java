package app;

import java.lang.reflect.Array; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CardRandeom {


    int[] isgetCard;
    int[] Card_home;
    int card_hand_num = 0;
    int Card_max = 0;

    CardRandeom(){
        Card card = Loding.readjson();
        Card_home = card.getCard_data();
        isgetCard = new int[Card_home.length];
        Arrays.fill(isgetCard, 0);
    }

    
    // public void LoadingCard() {
    //     Card card = Loding.readjson();
    //     Card_home = card.getCard_data();
    //     isgetCard = new int[Card_home.length];
    //     Arrays.fill(isgetCard, 0);
    // }

    public int getRandomCard() {

        int one_card =  new Random().nextInt(Card_home.length);
            if(isgetCard[one_card]!=Card_home[one_card]){
                isgetCard[one_card]++;
            }else{
                while(isgetCard[one_card]==Card_home[one_card]){
                one_card =  new Random().nextInt(Card_home.length);
                }
                if(isgetCard[one_card]!=Card_home[one_card])
                isgetCard[one_card]++;
            }

        return one_card+1;
    }

        public boolean ishaveCard(){
            for (int i = 0; i < isgetCard.length; i++) {
                if(isgetCard[i]!=Card_home[i]){
                    return true;
                }
            }
            return false;
        }

        public int[] RandomCard(){
            int[] Card_hand = new int[5];  
            Arrays.fill(Card_hand, 0); 
            for (int i = 0; i < 5; i++) {
                if(ishaveCard()){
                    Card_hand[i]=getRandomCard();
                    card_hand_num++;
                }
            }
            return Card_hand;
        }
        

}
