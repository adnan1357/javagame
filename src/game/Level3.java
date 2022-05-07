package game;

import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Level3 extends GameWorld{
    //game music
    private static SoundClip music;

    static {
        try {
            music = new SoundClip("data/level3.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public Level3(Game game) {
        //setting up the level
        super(game);
        setBackground(new ImageIcon("data/map3.jpg").getImage());
        setLevelName("Level3");
        setGameMusic(music);

        //platform
        Platform p1 = new Platform(this, new Vec2(-13.7f,6.7f), 6f, 1f, 0);
        Platform p2 = new Platform(this, new Vec2(-18.35f,-4.15f), 12f, 1f, 90);
        Platform p3 = new Platform(this, new Vec2(-7.65f,-16.25f), 10f, 1f, 0);
        Platform p4 = new Platform(this, new Vec2(3.45f,-10.6f), 6f, 1f, 90);
        Platform p5 = new Platform(this, new Vec2(3.35f,4.1f), 3f, 1f, 90);
        Platform p6 = new Platform(this, new Vec2(0.8f,6.7f), 3f, 1f, 0);
        Platform p7 = new Platform(this, new Vec2(-22f,-1.0f), 19f, 0.5f, 90);
        Platform p8 = new Platform(this, new Vec2(22.0f,-0.6f), 19f, 0.5f, 90);
        Platform p9 = new Platform(this, new Vec2(-0.65f,17.8f), 23f, 0.5f, 0);
        Platform p10 = new Platform(this, new Vec2(1f,-17.95f), 23f, 0.5f, 0);


        setZombies(3);

    }

    @Override
    public void populate(){
        //adding the zombies and positions
        for (int i = 0; i<getZombie().length-1; i++){
            getZombie()[i] = new GreenZombie(this);
            ZombieCollision x = new ZombieCollision(getZombie()[i]);
            getZombie()[i].addCollisionListener(x);
        }

        getZombie()[2] = new RedZombie(this);
        ZombieCollision x = new ZombieCollision(getZombie()[2]);
        getZombie()[2].addCollisionListener(x);

        getZombie()[0].setPosition(new Vec2(17,15));
        getZombie()[1].setPosition(new Vec2(16,15));
        getZombie()[2].setPosition(new Vec2(15,15));
    }
}
