package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import app.CardSkillData.CardSkillBuff;
import app.CardSkillData.CardSkillData;
import app.Spirit.Dave;

public class CardSkill {

    List<CardSkillData> list;
    CardSkillBuff cardSkillBuff;
    Dave dave;

    public CardSkill(Dave dave) {
        this.dave = dave;
        cardSkillBuff = new CardSkillBuff();
        list = read_CardSkill();

    }

    public int PayCont(int Card_id) {
        CardSkillData cardSkillData = list.get(Card_id);
        int SumCont = dave.getCont() - cardSkillData.getCONT();
        if (SumCont >= 0) {
            dave.setCont(SumCont);
        }
        return dave.getCont();
    }

    public Boolean ishaveCont(int Card_id) {
        CardSkillData cardSkillData = list.get(Card_id);
        int SumCont = dave.getCont() - cardSkillData.getCONT();
        if (SumCont >= 0) {
            return true;
        }
        return false;
    }

    public int get_ATK(int Card_id) {
        CardSkillData cardSkillData = list.get(Card_id);
        int DMG = cardSkillData.getATK();
        int DEF = cardSkillData.getDEF();
        return DMG;
    }

    public int up_SunCont(int Card_id) {
        int Sun = dave.getCont();
        CardSkillData cardSkillData = list.get(Card_id);
        if (cardSkillData.getName().toString().equals("Sunflower")) {
            System.out.println(cardSkillData.getName().toString());
            Sun += 200;
            dave.setCont(Sun);
        }
        return dave.getCont();
    }

    public void play_anime(int Card_id, JPanel jPanel) {
        CardSkillData cardSkillData = list.get(Card_id);
        if (cardSkillData.getName().equals("Peashooter")) {
            Anime.Peashooter_Attack(jPanel, 600).run();
        }
    }




    public List<CardSkillData> read_CardSkill() {
        try {
            File f = new File("draw/data/CardSkill.json");
            FileReader reader = new FileReader(f);
            Gson gson = new GsonBuilder().create();

            List<CardSkillData> datas = gson.fromJson(reader, new TypeToken<List<CardSkillData>>() {
            }.getType());

            return datas;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
