package app;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class Voice {
    static String SoundCherrBoom = "draw/sound/CherryBomb.mp3";// 樱桃炸弹爆炸
    static String SoundZombieAttack = "draw/sound/chomp.mp3";// 僵尸攻击
    static String SoundPlant = "draw/sound/plant1.mp3";// 植物种植
    static String SoundPlantAttack = "draw/sound/tap.mp3";// 豌豆射手攻击
    static String SoundZombieWon = "draw/sound/zombiegroup.mp3";// 僵尸胜利
    static String BackGroundMusic1 = "draw/sound/Faster.mp3";// 背景音乐1
    static String BackGroundMusic2 = "draw/sound/Loonboon.mp3";// 背景音乐2
    static String zombieComing = "draw/sound/zombiecoming.mp3";// 僵尸来
    static String loding_sound = "draw/sound/anime_loging.mp3";// 读条动画
    static String GetSun_sound = "draw/sound/sun.mp3";// 读条动画
    static String hahaha_sound = "draw/sound/hahaha.mp3";// 读条动画

    static boolean isMusicRun = false;

    public static Thread BackGroundmusic(String file) {
        return new Thread(() -> {
            Player player;
            String music;
            music = file;
            try {
                while (true) {
                    BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
                    player = new Player(buffer);
                    player.play();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JavaLayerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }// 背景音乐

    public static Thread anime_music_loop(String file) {
        return new Thread(() -> {
            isMusicRun = true;
            Player player;
            String music;
            music = file;
            try {
                while (isMusicRun) {
                    BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
                    player = new Player(buffer);
                    player.play();
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JavaLayerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            isMusicRun = false;
        });
    }

    public static Thread anime_music(String file) {
        return new Thread(() -> {
            Player player;
            String music;
            music = file;
            try {
                BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
                player = new Player(buffer);
                player.play();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JavaLayerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    public static Player outplayer;

    public static Thread anime_music_palyer(String file) {
        return new Thread(() -> {
            Player player;
            String music;
            music = file;
            try {
                BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
                player = new Player(buffer);
                outplayer = player;
                player.play();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JavaLayerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

}
