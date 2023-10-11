package app;

import java.io.File;

public class GetAllSprite {

    public static String[] getAllSprite_name() {// 获取立绘
        File file = new File("draw/img/sprite");
        File[] files = file.listFiles();
        if (files != null) {
            String datas[] = new String[files.length];

            for (int i = 0; i < files.length; i++) {
                datas[i] = files[i].getName();
            }
            return datas;
        } else {
            return new String[0];
        }
    }

    public static String[] getAllCard_name() {// 获取卡牌
        File file = new File("draw/image/plant/");
        File[] files = file.listFiles();
        if (files != null) {
            String datas[] = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                datas[i] = files[i].getName();
            }
            return datas;
        } else {
            return new String[0];
        }
    }

    public static String[] getAllportrait_name() {
        File file = new File("draw/img/portrait");
        File[] files = file.listFiles();
        if (files != null) {
            String datas[] = new String[files.length];

            for (int i = 0; i < files.length; i++) {
                datas[i] = files[i].getName();
            }
            return datas;
        } else {
            return new String[0];
        }
    }

}
