package game;

import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Level1 extends GameWorld{
    //The music for the level
    private static SoundClip music;
    static {
        try {
            music = new SoundClip("data/gamemusic.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Level1(Game game){
        super(game);

        //setting up the level
        setBackground(new ImageIcon("data/map.jpeg").getImage());
        setLevelName("Level1");
        setGameMusic(music);

        //creating the platforms
        Platform p = new Platform(this, new Vec2(5.15f,-11.75f), 13, 0.5f, 0);
        Platform p2 = new Platform(this, new Vec2(-18.85f,16.050001f), 10f, 0.5f, 0);
        Platform p3 = new Platform(this, new Vec2(-28.0f,7.5f), 10f, 0.5f, 90);
        Platform p4 = new Platform(this, new Vec2(3.1f,11.5f), 13.75f, 0.5f, 0);
        Platform p5 = new Platform(this, new Vec2(-18.15f,-12.85f), 6, 0.5f, 0);
        Platform p6 = new Platform(this, new Vec2(-4.35f,7.15f), 6, 0.5f, 0);
        Platform p7 = new Platform(this, new Vec2(10.75f,7.05f), 6, 0.5f, 0);
        Platform p8 = new Platform(this, new Vec2(27.5f,8.3f), 6, 0.5f, 90);
        Platform p9 = new Platform(this, new Vec2(-12.400001f,-7.3f), 6, 0.5f, 90);
        Platform p10 = new Platform(this, new Vec2(-23.550001f,-7.3f), 6, 0.5f, 90);
        Platform p11 = new Platform(this, new Vec2(-10.2f,2.6000001f), 5, 0.5f, 90);
        Platform p12 = new Platform(this, new Vec2(-7.5f,-4.65f), 6.5f, 0.5f, 90);
        Platform p13 = new Platform(this, new Vec2(17.75f,-4.65f), 6.5f, 0.5f, 90);
        Platform p14 = new Platform(this, new Vec2(21.85f,2.85f), 5.5f, 0.5f, 0);
        Platform p15 = new Platform(this, new Vec2(-24f, -1.65f), 4.5f, 0.5f, 0);
        Platform p16 = new Platform(this, new Vec2(-13.05f,-1.8f), 3f, 0.5f, 0);
        Platform p17 = new Platform(this, new Vec2(11.3f,1.4f), 6.5f, 0.5f, 0);
        Platform p18 = new Platform(this, new Vec2(-3.1f,1.6f), 4.5f, 0.5f, 0);
        Platform p19 = new Platform(this, new Vec2(1.25f,4.15f), 3f, 0.5f, 90);
        Platform p20 = new Platform(this, new Vec2(5.35f,4.15f), 3f, 0.5f, 90);
        Platform p21 = new Platform(this, new Vec2(16.4f,4.5f), 3f, 0.5f, 90);
        Platform p22 = new Platform(this, new Vec2(18.7f,16.15f), 2.5f, 0.5f, 90);
        Platform p23 = new Platform(this, new Vec2(23.1f,16.1f), 2.5f, 0.5f, 90);
        Platform p24 = new Platform(this, new Vec2(-10.15f,13.6f), 2.5f, 0.5f, 90);
        Platform p25 = new Platform(this, new Vec2(25.2f,13.75f), 2.5f, 0.5f, 0);
        Platform p26 = new Platform(this, new Vec2(16.5f,12.6f), 1.5f, 0.5f, 90);
        Platform p27 = new Platform(this, new Vec2(17.25f,13.8f), 1.5f, 0.5f, 0);
        Platform p28 = new Platform(this, new Vec2(20.8f,19f), 3f, 0.5f, 0);

        //initialises the zombies
        setZombies(3);

    }

    //populates the level with zombies
    @Override
    public void populate(){
        getPlayer().setPosition(new Vec2(-10,9));

        setZombies(3);

        //for loop which iterates the zombie array and adds a zombie
        for (int i = 0; i<getZombie().length; i++){
            getZombie()[i] = new GreenZombie(this);
            ZombieCollision x = new ZombieCollision(getZombie()[i]);
            getZombie()[i].addCollisionListener(x);
        }

        //sets positions
        getZombie()[0].setPosition(new Vec2(15,0));
        getZombie()[1].setPosition(new Vec2(13,0));
        getZombie()[2].setPosition(new Vec2(11,0));
    }
}
