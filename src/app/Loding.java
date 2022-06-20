package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Loding {

    public static void Loding_Card() {
        readjson();
    }

    public static Card readjson() {


            try {
                File f=new File("draw/data/Data.json");
                FileReader reader=new FileReader(f);
                Gson gson=new GsonBuilder().create();
                Card card = gson.fromJson(reader, Card.class);
                
                return card;
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
    }

    public static void startjsondata(){
        Card card = new Card();
        int[] num = GameDefaultSettingData.DefaultCard_data;
        card.setCard_data(num);
        card.setCard_MAX(num.length);
        try {
            Writer writer = new FileWriter("draw/data/Data.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(card, writer);
            writer.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }




}