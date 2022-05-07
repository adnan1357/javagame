package game;

import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;


public class Level2 extends GameWorld{
    //level music
    private static SoundClip music;
    static {
        try {
            music = new SoundClip("data/level2.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public Level2(Game game) {
        //sets up the levels
        super(game);
        setBackground(new ImageIcon("data/map2.jpg").getImage());
        setLevelName("Level2");
        setGameMusic(music);

        //platforms

        Platform p1 = new Platform(this, new Vec2(-0.12f,6.75f), 17, 0.25f, 0);
        Platform p2 = new Platform(this, new Vec2(-0.12f,-4f), 17, 0.25f, 0);
        Platform p3 = new Platform(this, new Vec2(-16.85f,1.5f), 5.5f, 0.25f, 90);
        Platform p4 = new Platform(this, new Vec2(16.75f,1.5f), 5.5f, 0.25f, 90);
        Platform p5 = new Platform(this, new Vec2(-10.9f,4f), 2.75f, 0.1f, 90);
        Platform p6 = new Platform(this, new Vec2(2.4f,-0.4f), 3.5f, 0.1f, 90);
        Platform p7 = new Platform(this, new Vec2(13.35f,2.65f), 3.2f, 0.1f, 0);
        Platform p8 = new Platform(this, new Vec2(-16.1f,1.25f), 0.5f, 0.1f, 0);
        Platform p9 = new Platform(this, new Vec2(-11.5f,1.25f), 0.5f, 0.1f, 0);
        Platform p10 = new Platform(this, new Vec2(6.65f,4.55f), 2f, 0.1f, 90);
        Platform p11 = new Platform(this, new Vec2(11.3f,0.65f), 2f, 0.1f, 90);

        setZombies(1);

    }

    //populating zombies
    @Override
    public void populate(){
        for (int i = 0; i<getZombie().length; i++){
            getZombie()[i] = new RedZombie(this);
            ZombieCollision x = new ZombieCollision(getZombie()[i]);
            getZombie()[i].addCollisionListener(x);
        }

        getZombie()[0].setPosition(new Vec2(12.7f,4.7000003f));
    }


}
