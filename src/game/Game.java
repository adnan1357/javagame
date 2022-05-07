package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Your main game entry point
 */
public class Game {
    private GameWorld level;
    private GameView view;
    private PlayerController playerController;
    private Mouse mouse;
    private Tracker tracker;
    private GiveFocus giveFocus;
    private boolean mute = false;
    private JFrame frame;
    private ControlPanel buttons;


    /** Initialise a new Game. */
    public Game() {

        //1. make an empty game world
        level = new Level1(this);
        level.populate();


        //3. make a view to look into the game world
        view = new GameView(level, 1280, 720);

        //view.setGridResolution(1);

        //make a mouse for the character to respond to the mouse and add to vuew
        mouse = new Mouse(level.getPlayer(), view);
        view.addMouseMotionListener(mouse);

        //make a givefocus so the game can respond to mouse events, add to view
        giveFocus = new GiveFocus(view, level);
        view.addMouseListener(giveFocus);

        //make a player controller so the player can control the character, add to view
        playerController = new PlayerController(level.getPlayer(), this);
        view.addKeyListener(playerController);

        //Add a step listener, tracker, so the background process can run, add to view
        tracker = new Tracker(level, this);
        level.addStepListener(tracker);


        //3. create a Java window (frame) and add the game
        //   view to it
        frame = new JFrame("City Game");
        frame.add(view);

        //create a button panel and add to game
        buttons = new ControlPanel(this);//this will resize window
        frame.add(buttons.getMainPanel(), BorderLayout.WEST);

        //BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        //Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        //frame.getContentPane().setCursor(blankCursor);




        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        //optional: uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(world, 1000, 500);

        // start our game world simulation!
        level.start();

    }

    public void goToNextLevel(){
        if (level instanceof Level1){
            //stop the level
            level.stop();
            level.getGameMusic().stop(); //stop music
            level = new Level2(this); //set new gameworld to level
            level.populate(); // add zombies
            //level now refer to the new level
            view.setWorld(level); //change the level to view so we can see the level
            view.updateWorld(level); //update world, similar to setworld but needed for the methods
            //update level, mouse, tracker, focus, and controller, and start level
            mouse.update(level);
            tracker.setTracker(level);
            giveFocus.updateFocus(level);
            level.addStepListener(tracker);
            playerController.updatePlayer(level.getPlayer());
            level.start();
            updateLevel(level);
        }
        else if (level instanceof Level2){
            level.stop();
            level.getGameMusic().stop();
            level = new Level3(this);
            level.populate();
            //level now refer to the new level
            view.setWorld(level);
            view.updateWorld(level);
            mouse.update(level);
            tracker.setTracker(level);
            giveFocus.updateFocus(level);
            level.addStepListener(tracker);
            playerController.updatePlayer(level.getPlayer());
            level.start();
            updateLevel(level);
        }
        else{
            System.out.println("well done, you completed the game!");
            System.exit(0);
        }
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }

    public GameView getView(){
        return view;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public GiveFocus getGiveFocus() {
        return giveFocus;
    }

    public GameWorld getLevel(){
        return level;
    }

    public boolean isMute(){
        return mute;
    }

    public void muteMusic(){
        level.getGameMusic().pause(); //pause music
        mute = true;

    }

    public void unmuteMusic(){
        level.getGameMusic().resume(); //resume music
        mute = false;
    }

    public void updateLevel(GameWorld w){
        level = w;
    }

    public void pause(){
        if (level.isRunning()){
            //frame.remove(view);
            //frame.add(buttons.getMainPanel(), BorderLayout.NORTH);
            //frame.pack();
            level.stop(); //stop game

        }
        else{
            //frame.remove(buttons.getMainPanel());
            //frame.add(view);
            //frame.pack();
            level.start(); //start game
        }
    }



}
