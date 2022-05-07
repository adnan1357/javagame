package game;

import city.cs.engine.*;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class GameWorld extends World{


    private Player player;
    private Zombie[] zombies;
    private Image background;
    private Boolean complete = false;
    private SoundClip gameMusic;
    private String levelName;
    private static SoundClip die;

    //sound for when player dies
    static {
        try {
            die = new SoundClip("data/dead.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public GameWorld(Game game){
        setGravity(0); //set gravity to 0
        player = new Player(this); //initialise player

//        try {
//            gameMusic = new SoundClip("data/gamemusic.wav");   // Open an audio input stream
//            gameMusic.loop();                              // Set it to continous playback (looping)
//        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
//            //code in here will deal with any errors
//            //that might occur while loading/playing sound
//            System.out.println(e);
//        }
        if (game.isMute()){
            gameMusic.stop(); //mutes music
        }

    }
    public void populate(){

    }

    public Player getPlayer(){
        return this.player;
    }

    public Zombie[] getZombie(){
        return this.zombies;
    }

    public Image getBackground(){
        return background;
    }

    //maths to count how many zombies have died
    public int getDead(){
        int x = 0;
        for (int i = 0; i<zombies.length; i++){
            if (!zombies[i].isAlive()){
                x++;
            }
        }
        return x;
    }

    
    public void setBackground(Image bg){
        this.background = bg;
    }

    public void complete(){
        this.complete = true;
    }
    public boolean completed(){
        return complete;
    }

    public void setZombies(int count){
        this.zombies = new Zombie[count];
    }

    public SoundClip getGameMusic() {
        return gameMusic;
    }


    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelName(){
        return levelName;
    }

    public void dead(){
        die.play();
    }

    @Override
    public void stop(){
        super.stop();
        gameMusic.pause();
    }

    @Override
    public void start(){
        super.start();
        gameMusic.resume();
    }

    public void setGameMusic(SoundClip music){
        gameMusic = music;
    }

}
