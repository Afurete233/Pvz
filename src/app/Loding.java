package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Loding {

    public static void Loding_Card() {
        readjson();
    }

    public static Card readjson() {

        try {
            File f = new File("draw/data/Data.json");
            FileReader reader = new FileReader(f);
            Gson gson = new GsonBuilder().create();
            Card card = gson.fromJson(reader, Card.class);

            return card;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void startjsondata() {
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
            // TODO: handle exception
        }
    }

    public static int ReadCardType(int[] old, int Card_id) {
        if (Card_id + 1 > old.length)
            return Card_id;
        return old.length;
    }

    public static void Updatejsondata(int Card_id) {

        Card card = new Card();
        int[] old = Loding.readjson().getCard_data();

        int Card_Type = ReadCardType(old, Card_id);

        int[] num;
        if (Card_id >= Card_Type) {
            num = new int[Card_id];
        } else {
            num = new int[Card_Type];
        }
        Arrays.fill(num, 0);

        for (int i = 0; i < old.length; i++) {
            num[i] = old[i];
        }

        num[Card_id-1]++;

        card.setCard_data(num);
        card.setCard_MAX(num.length);
        try {
            Writer writer = new FileWriter("draw/data/Data.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(card, writer);
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}